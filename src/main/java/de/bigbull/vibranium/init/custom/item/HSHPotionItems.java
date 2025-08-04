package de.bigbull.vibranium.init.custom.item;

import de.bigbull.vibranium.init.EffectInit;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;
import java.util.function.Consumer;

public class HSHPotionItems extends Item {
    private final int duration;
    private final int amplifier;

    public HSHPotionItems(Properties properties, int duration, int amplifier) {
        super(properties.component(DataComponents.CONSUMABLE, createSoulHerbElixer(duration, amplifier)));
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public static Consumable createSoulHerbElixer(int duration, int amplifier) {
        return Consumable.builder()
                .consumeSeconds(1.6F)
                .animation(ItemUseAnimation.DRINK)
                .sound(SoundEvents.GENERIC_DRINK)
                .hasConsumeParticles(false)
                .onConsume(new ApplyStatusEffectsConsumeEffect(
                        List.of(
                                new MobEffectInstance(EffectInit.KINETIC_REDISTRIBUTION, duration, amplifier),
                                new MobEffectInstance(MobEffects.REGENERATION, duration / 4, amplifier, false, false),
                                new MobEffectInstance(MobEffects.ABSORPTION, duration / 4, amplifier, false, false),
                                new MobEffectInstance(MobEffects.STRENGTH, duration / 4, amplifier, false, false),
                                new MobEffectInstance(MobEffects.SPEED, duration / 4, amplifier, false, false)
                        ),
                        1.0F
                ))
                .build();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag flag) {
        List<MobEffectInstance> effects = List.of(new MobEffectInstance(EffectInit.KINETIC_REDISTRIBUTION, duration, amplifier));
        PotionContents.addPotionTooltip(effects, consumer, 1.0F, tooltipContext.tickRate());
    }
}