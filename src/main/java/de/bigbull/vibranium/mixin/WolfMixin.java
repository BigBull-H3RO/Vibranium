package de.bigbull.vibranium.mixin;

import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Wolf.class)
public abstract class WolfMixin extends TamableAnimal implements NeutralMob {
    protected WolfMixin(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void actuallyHurt(ServerLevel serverLevel, DamageSource damageSource, float v) {
        if (!this.canArmorAbsorb(damageSource)) {
            super.actuallyHurt(serverLevel, damageSource, v);
        } else {
            ItemStack itemstack = this.getBodyArmorItem();
            int i = itemstack.getDamageValue();
            int j = itemstack.getMaxDamage();
            itemstack.hurtAndBreak(Mth.ceil(v), this, EquipmentSlot.BODY);
            if ((Crackiness.WOLF_ARMOR.byDamage(i, j) != Crackiness.WOLF_ARMOR.byDamage(this.getBodyArmorItem()) && this.getBodyArmorItem().is(ItemInit.VIBRANIUM_WOLF_ARMOR))) {
                this.playSound(SoundEvents.WOLF_ARMOR_CRACK);
                serverLevel.sendParticles(
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
            } else if ((Crackiness.WOLF_ARMOR.byDamage(i, j) != Crackiness.WOLF_ARMOR.byDamage(this.getBodyArmorItem()) && this.getBodyArmorItem().is(Items.WOLF_ARMOR))) {
                this.playSound(SoundEvents.WOLF_ARMOR_CRACK);
                serverLevel.sendParticles(
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

    private boolean canArmorAbsorb(DamageSource damageSource) {
        return this.getBodyArmorItem().is(Items.WOLF_ARMOR) || this.getBodyArmorItem().is(ItemInit.VIBRANIUM_WOLF_ARMOR)  && !damageSource.is(DamageTypeTags.BYPASSES_WOLF_ARMOR);
    }
}