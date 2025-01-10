package de.bigbull.vibranium.mixin;


import de.bigbull.vibranium.init.ArmorMaterialsInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.custom.item.WolfArmorItem;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Wolf.class)
public abstract class WolfMixin extends TamableAnimal implements NeutralMob {

    @Shadow
    public abstract boolean hasArmor();

    @Shadow
    protected abstract boolean canArmorAbsorb(DamageSource source);

    protected WolfMixin(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void actuallyHurt(DamageSource source, float p_330695_) {
        if (!this.canArmorAbsorb(source)) {
            super.actuallyHurt(source, p_330695_);
        } else {
            ItemStack itemstack = this.getBodyArmorItem();
            int i = itemstack.getDamageValue();
            int j = itemstack.getMaxDamage();
            itemstack.hurtAndBreak(Mth.ceil(p_330695_), this, EquipmentSlot.BODY);
            if (Crackiness.WOLF_ARMOR.byDamage(i, j) != Crackiness.WOLF_ARMOR.byDamage(this.getBodyArmorItem())) {
                this.playSound(SoundEvents.WOLF_ARMOR_CRACK);
                if (this.level() instanceof ServerLevel serverlevel && this.getBodyArmorItem().is(ItemInit.VIBRANIUM_WOLF_ARMOR)) {
                    serverlevel.sendParticles(
                            new ItemParticleOption(ParticleTypes.ITEM, ItemInit.VIBRANIUM_INGOT.get().getDefaultInstance()),
                            this.getX(),
                            this.getY() + 1.0,
                            this.getZ(),
                            20,
                            0.2,
                            0.1,
                            0.2,
                            0.1
                    );
                } else if (this.level() instanceof ServerLevel serverlevel && this.getBodyArmorItem().is(Items.WOLF_ARMOR)) {
                    serverlevel.sendParticles(
                            new ItemParticleOption(ParticleTypes.ITEM, Items.ARMADILLO_SCUTE.getDefaultInstance()),
                            this.getX(),
                            this.getY() + 1.0,
                            this.getZ(),
                            20,
                            0.2,
                            0.1,
                            0.2,
                            0.1
                    );
                }
            }
        }
    }

    @Inject(method = "mobInteract", at = @At(value = "HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (!this.level().isClientSide && itemstack.getItem() instanceof WolfArmorItem) {
            if (this.isTame()) {
                if (this.isOwnedBy(player) && !this.hasArmor() && !this.isBaby()) {
                    this.setBodyArmorItem(itemstack.copyWithCount(1));
                    itemstack.consume(1, player);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                } else if (itemstack.canPerformAction(net.neoforged.neoforge.common.ItemAbilities.SHEARS_REMOVE_ARMOR)
                        && this.isOwnedBy(player)
                        && this.hasArmor()
                        && (!EnchantmentHelper.has(this.getBodyArmorItem(), EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE) || player.isCreative())) {
                    itemstack.hurtAndBreak(1, player, getSlotForHand(hand));
                    this.playSound(SoundEvents.ARMOR_UNEQUIP_WOLF);
                    ItemStack removedArmor  = this.getBodyArmorItem();
                    this.setBodyArmorItem(ItemStack.EMPTY);
                    this.spawnAtLocation(removedArmor);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                } else if (ArmorMaterialsInit.VIBRANIUM_MATERIAL.value().repairIngredient().get().test(itemstack)
                        && this.isInSittingPose()
                        && this.hasArmor()
                        && this.isOwnedBy(player)
                        && this.getBodyArmorItem().isDamaged()) {
                    itemstack.shrink(1);
                    this.playSound(SoundEvents.WOLF_ARMOR_REPAIR);
                    ItemStack armorStack  = this.getBodyArmorItem();
                    int repairAmount  = (int)((float)armorStack .getMaxDamage() * 0.125F);
                    armorStack .setDamageValue(Math.max(0, armorStack .getDamageValue() - repairAmount));
                    cir.setReturnValue(InteractionResult.SUCCESS);
                } else {
                    InteractionResult interactionresult = super.mobInteract(player, hand);
                    if (!interactionresult.consumesAction() && this.isOwnedBy(player)) {
                        this.setOrderedToSit(!this.isOrderedToSit());
                        this.jumping = false;
                        this.navigation.stop();
                        this.setTarget(null);
                        cir.setReturnValue(InteractionResult.SUCCESS_NO_ITEM_USED);
                    } else {
                        cir.setReturnValue(interactionresult);
                    }
                }
            }
        }
    }

    @Inject(method = "canArmorAbsorb", at = @At(value = "HEAD"), cancellable = true)
    private void canArmorAbsorb(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (this.getBodyArmorItem().getItem() instanceof WolfArmorItem && source.is(DamageTypeTags.BYPASSES_WOLF_ARMOR)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "hasArmor", at = @At(value = "HEAD"), cancellable = true)
    public void hasArmor(CallbackInfoReturnable<Boolean> callbackInfo) {
        if (this.getBodyArmorItem().getItem() instanceof WolfArmorItem) {
            callbackInfo.setReturnValue(true);
        }
    }
}