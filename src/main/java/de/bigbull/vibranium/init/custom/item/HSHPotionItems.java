package de.bigbull.vibranium.init.custom.item;

import de.bigbull.vibranium.init.EffectInit;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class HSHPotionItems extends Item {
    private final int effectDuration;
    private final int effectAmplifier;

    public HSHPotionItems(Properties properties, int effectDuration, int effectAmplifier) {
        super(properties);
        this.effectDuration = effectDuration;
        this.effectAmplifier = effectAmplifier;
    }

    public static final Consumable SOUL_HERB_ELIXIR = Consumable.builder()
            .consumeSeconds(1.6F)
            .animation(ItemUseAnimation.DRINK)
            .sound(SoundEvents.GENERIC_DRINK)
            .hasConsumeParticles(false)
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    List.of(
                            new MobEffectInstance(EffectInit.KINETIC_REDISTRIBUTION, 3600, 0),
                            new MobEffectInstance(MobEffects.REGENERATION, 900, 0, false, false),
                            new MobEffectInstance(MobEffects.ABSORPTION, 900, 0, false, false),
                            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 900, 0, false, false),
                            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 900, 0, false, false)
                    ),
                    1.0F
            ))
            .build();

    public static final Consumable SOUL_HERB_ELIXIR_EXTENDED = Consumable.builder()
            .consumeSeconds(1.6F)
            .animation(ItemUseAnimation.DRINK)
            .sound(SoundEvents.GENERIC_DRINK)
            .hasConsumeParticles(false)
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    List.of(
                            new MobEffectInstance(EffectInit.KINETIC_REDISTRIBUTION, 9600, 0),
                            new MobEffectInstance(MobEffects.REGENERATION, 2400, 0, false, false),
                            new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0, false, false),
                            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 0, false, false),
                            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 0, false, false)
                    ),
                    1.0F
            ))
            .build();

    public static final Consumable SOUL_HERB_ELIXIR_ENHANCED = Consumable.builder()
            .consumeSeconds(1.6F)
            .animation(ItemUseAnimation.DRINK)
            .sound(SoundEvents.GENERIC_DRINK)
            .hasConsumeParticles(false)
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    List.of(
                            new MobEffectInstance(EffectInit.KINETIC_REDISTRIBUTION, 1800, 1),
                            new MobEffectInstance(MobEffects.REGENERATION, 450, 1, false, false),
                            new MobEffectInstance(MobEffects.ABSORPTION, 450, 1, false, false),
                            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 450, 1, false, false),
                            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 450, 1, false, false)
                    ),
                    1.0F
            ))
            .build();

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        List<MobEffectInstance> effects = List.of(new MobEffectInstance(EffectInit.KINETIC_REDISTRIBUTION, effectDuration, effectAmplifier));
        PotionContents.addPotionTooltip(effects, tooltip::add, 1.0F, context.tickRate());
    }
}