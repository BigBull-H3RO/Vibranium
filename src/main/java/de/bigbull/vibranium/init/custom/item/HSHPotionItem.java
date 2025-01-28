package de.bigbull.vibranium.init.custom.item;

import de.bigbull.vibranium.init.EffectInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

import java.util.List;

public class HSHPotionItem extends Item {
    private static final int DRINK_DURATION = 32;
    private final int effectDuration;
    private final int effectAmplifier;

    public HSHPotionItem(Properties properties, int effectDuration, int effectAmplifier) {
        super(properties);
        this.effectDuration = effectDuration;
        this.effectAmplifier = effectAmplifier;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!level.isClientSide) {
            level.playSound(null, entity.blockPosition(), SoundEvents.GENERIC_DRINK, entity.getSoundSource(), 1.0F, 1.0F);

            entity.addEffect(new MobEffectInstance(EffectInit.KINETIC_REDISTRIBUTION, effectDuration, effectAmplifier));
            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, effectDuration/4, effectAmplifier, false, false));
            entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, effectDuration/4, effectAmplifier, false, false));
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, effectDuration/4, effectAmplifier, false, false));
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, effectDuration/4, effectAmplifier, false, false));
        }
        stack.shrink(1);
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return DRINK_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(world, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        List<MobEffectInstance> effects = List.of(new MobEffectInstance(EffectInit.KINETIC_REDISTRIBUTION, effectDuration, effectAmplifier));
        PotionContents.addPotionTooltip(effects, tooltip::add, 1.0F, context.tickRate());
    }
}