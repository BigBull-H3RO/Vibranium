package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.data.worldgen.structure.CustomGeodeFeature;
import de.bigbull.vibranium.init.custom.SoulTreeTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FeatureInit {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Vibranium.MODID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Vibranium.MODID);

    public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> SOUL_TREE = FEATURES.register("soul_tree", () -> new TreeFeature(TreeConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<TreeConfiguration>> SOUL_TREE_SMALL = FEATURES.register("soul_tree_2", () -> new TreeFeature(TreeConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<GeodeConfiguration>> VIBRANIUM_STRUCTURE = FEATURES.register("vibranium_structure", () -> new CustomGeodeFeature(GeodeConfiguration.CODEC));

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<SoulTreeTrunkPlacer>> SOUL_TREE_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("soul_tree_trunk_placer", () -> new TrunkPlacerType<>(SoulTreeTrunkPlacer.CODEC));
}
