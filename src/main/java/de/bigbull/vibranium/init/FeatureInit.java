package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.data.worldgen.custom.EnrichedVibraniumFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FeatureInit {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Vibranium.MODID);

    public static final DeferredHolder<Feature<?>, EnrichedVibraniumFeature> ENRICHED_VIBRANIUM_FEATURE =
            FEATURES.register("enriched_vibranium_feature", () -> new EnrichedVibraniumFeature(NoneFeatureConfiguration.CODEC));
}