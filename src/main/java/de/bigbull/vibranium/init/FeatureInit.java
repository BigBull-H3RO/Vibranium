package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.data.worldgen.feature.CustomGeodeFeature;
import de.bigbull.vibranium.data.worldgen.feature.VibraniumStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FeatureInit {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Vibranium.MODID);

    public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> SOUL_TREE = FEATURES.register("soul_tree", () -> new TreeFeature(TreeConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> VIBRANIUM_STRUCTURE = FEATURES.register("vibranium_structure", () -> new VibraniumStructure(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<GeodeConfiguration>> VIBRANIUM_STRUCTURE2 = FEATURES.register("vibranium_structure2", () -> new CustomGeodeFeature(GeodeConfiguration.CODEC));
}
