package de.bigbull.vibranium.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class FoodPropertiesInit {
    public static final FoodProperties HEART_SHAPED_HERB = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.25f)
            .build();

    public static final Consumable HEART_SHAPED_HERB_EFFECT = Consumables.defaultFood().onConsume(
            new ApplyStatusEffectsConsumeEffect(
                    List.of(
                            new MobEffectInstance(MobEffects.REGENERATION, 400, 0, true, false),
                            new MobEffectInstance(MobEffects.ABSORPTION, 400, 0, true, false),
                            new MobEffectInstance(MobEffects.DARKNESS, 200, 0, true, false),
                            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 400, 0, true, false),
                            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0, true, false),
                            new MobEffectInstance(MobEffects.POISON, 200, 0, true, false),
                            new MobEffectInstance(MobEffects.CONFUSION, 250, 0, true, false)
                    ),
                    1.0F
            ))
            .build();
}
