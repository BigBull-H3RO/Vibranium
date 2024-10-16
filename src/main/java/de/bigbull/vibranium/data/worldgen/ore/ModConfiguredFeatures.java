package de.bigbull.vibranium.data.worldgen.ore;

import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.FeatureInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    protected static ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_VIBRANIUM_ORE = createKey("overworld_vibranium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_TREE = createKey("enriched_vibranium_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_SOUL_TREE = createKey("fancy_enriched_vibranium_tree");

//    public static TreeConfiguration.TreeConfigurationBuilder createEnrichedVibraniumTree() {
//        return new TreeConfiguration.TreeConfigurationBuilder(
//                SimpleStateProvider.simple(BlockInit.ENRICHED_VIBRANIUM_LOG.get().defaultBlockState()),
//                new StraightTrunkPlacer(4, 2, 0),
//                SimpleStateProvider.simple(BlockInit.ENRICHED_VIBRANIUM_LEAVES.get().defaultBlockState()),
//                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
//                new TwoLayersFeatureSize(1, 0, 1)
//        );
//    }
//
//    public static TreeConfiguration.TreeConfigurationBuilder createFancyEnrichedVibraniumTree() {
//        return new TreeConfiguration.TreeConfigurationBuilder(
//                SimpleStateProvider.simple(BlockInit.ENRICHED_VIBRANIUM_LOG.get().defaultBlockState()),
//                new StraightTrunkPlacer(6, 3, 0),
//                SimpleStateProvider.simple(BlockInit.ENRICHED_VIBRANIUM_LEAVES.get().defaultBlockState()),
//                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
//                new TwoLayersFeatureSize(1, 0, 1)
//        );
//    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> VibraniumOre =
                List.of(OreConfiguration.target(stoneReplacable, BlockInit.DEPPSLATE_VIBRANIUM_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplacable, BlockInit.DEPPSLATE_VIBRANIUM_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_VIBRANIUM_ORE, Feature.ORE, new OreConfiguration(VibraniumOre, 4));

//        FeatureUtils.register(context, ENRICHED_VIBRANIUM_TREE, FeatureInit.ENRICHED_VIBRANIUM_TREE.get(), createEnrichedVibraniumTree().build());
//        FeatureUtils.register(context, FANCY_ENRICHED_VIBRANIUM_TREE, FeatureInit.ENRICHED_VIBRANIUM_TREE.get(), createFancyEnrichedVibraniumTree().build());
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
