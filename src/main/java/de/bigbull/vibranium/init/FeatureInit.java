package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FeatureInit {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Vibranium.MODID);

    public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> ENRICHED_VIBRANIUM_TREE = FEATURES.register("enriched_vibranium_tree", () -> new TreeFeature(TreeConfiguration.CODEC));
}
