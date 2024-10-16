package de.bigbull.vibranium.init.custom.block.tree;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.FeatureInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class VibraniumFeatureConfig {
//    public static final ResourceKey<ConfiguredFeature<?, ?>> ENRICHED_VIBRANIUM_TREE = createConfiguredKey("enriched_vibranium_tree");
//    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_ENRICHED_VIBRANIUM_TREE = createConfiguredKey("fancy_enriched_vibranium_tree");
//
//    public static ResourceKey<ConfiguredFeature<?, ?>> createConfiguredKey(String name) {
//        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name));
//    }
//
//    public static TreeConfiguration.TreeConfigurationBuilder createEnrichedVibraniumTree() {
//        return new TreeConfiguration.TreeConfigurationBuilder(
//                SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
//                new StraightTrunkPlacer(4, 2, 0),
//                SimpleStateProvider.simple(BlockInit.ENRICHED_VIBRANIUM_LEAVES.get().defaultBlockState()),
//                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
//                new TwoLayersFeatureSize(1, 0, 1)
//        );
//    }
//
//    public static TreeConfiguration.TreeConfigurationBuilder createFancyEnrichedVibraniumTree() {
//        return new TreeConfiguration.TreeConfigurationBuilder(
//                SimpleStateProvider.simple(Blocks.OAK_LOG.defaultBlockState()),
//                new StraightTrunkPlacer(6, 3, 0),
//                SimpleStateProvider.simple(BlockInit.ENRICHED_VIBRANIUM_LEAVES.get().defaultBlockState()),
//                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
//                new TwoLayersFeatureSize(1, 0, 1)
//        );
//    }
//
//    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
//        FeatureUtils.register(context, ENRICHED_VIBRANIUM_TREE, FeatureInit.ENRICHED_VIBRANIUM_TREE.get(), createEnrichedVibraniumTree().build());
//        FeatureUtils.register(context, FANCY_ENRICHED_VIBRANIUM_TREE, FeatureInit.ENRICHED_VIBRANIUM_TREE.get(), createFancyEnrichedVibraniumTree().build());
//    }
}