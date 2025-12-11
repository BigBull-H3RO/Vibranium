package de.bigbull.vibranium.data.worldgen;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.FeatureInit;
import de.bigbull.vibranium.init.custom.SoulTreeTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    protected static ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_VIBRANIUM_ORE = createKey("overworld_vibranium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_TREE = createKey("soul_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_TREE_SMALL = createKey("soul_tree_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_TREE_MINI = createKey("soul_tree_mini");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VIBRANIUM_GEODE = createKey("vibranium_geode");

    public static TreeConfiguration.TreeConfigurationBuilder soulTree() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockInit.SOULWOOD_LOG.get().defaultBlockState()),
                new SoulTreeTrunkPlacer(
                        6,
                        4,
                        1,
                        new WeightedListInt(
                                WeightedList.<IntProvider>builder()
                                        .add(ConstantInt.of(1), 1)
                                        .add(ConstantInt.of(2), 1)
                                        .add(ConstantInt.of(3), 1)
                                        .add(ConstantInt.of(4), 1)
                                        .build()
                        ),
                        UniformInt.of(2, 5),
                        UniformInt.of(-4, -1),
                        UniformInt.of(-2, 2)
                ),
                BlockStateProvider.simple(BlockInit.SOULWOOD_LEAVES.get().defaultBlockState()),
                new CherryFoliagePlacer(
                        ConstantInt.of(4),
                        ConstantInt.of(0),
                        ConstantInt.of(5),
                        0.25F,
                        0.5F,
                        0.16666667F,
                        0.33333334F
                ),
                new TwoLayersFeatureSize(1, 0, 2)
        ).ignoreVines();
    }

    public static TreeConfiguration.TreeConfigurationBuilder soulTreeSmall() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockInit.SOULWOOD_LOG.get()),
                new ForkingTrunkPlacer(5, 2, 2),
                BlockStateProvider.simple(BlockInit.SOULWOOD_LEAVES.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2)
        ).ignoreVines();
    }

    public static TreeConfiguration.TreeConfigurationBuilder soulTreeMini() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockInit.SOULWOOD_LOG.get()),
                new ForkingTrunkPlacer(3, 1, 0),
                BlockStateProvider.simple(BlockInit.SOULWOOD_LEAVES.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)
        ).ignoreVines();
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> VibraniumOre =
                List.of(OreConfiguration.target(stoneReplacable, BlockInit.DEPPSLATE_VIBRANIUM_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplacable, BlockInit.DEPPSLATE_VIBRANIUM_ORE.get().defaultBlockState()));

        FeatureUtils.register(context, OVERWORLD_VIBRANIUM_ORE, Feature.ORE, new OreConfiguration(VibraniumOre, 4));
        FeatureUtils.register(context, VIBRANIUM_GEODE, FeatureInit.VIBRANIUM_GEODE.get(), new GeodeConfiguration(
                new GeodeBlockSettings(
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get()),
                        BlockStateProvider.simple(BlockInit.BUDDING_VIBRANIUM_CRYSTAL.get()),
                        BlockStateProvider.simple(Blocks.CALCITE),
                        BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(
                                BlockInit.SMALL_VIBRANIUM_BUD.get().defaultBlockState(),
                                BlockInit.MEDIUM_VIBRANIUM_BUD.get().defaultBlockState(),
                                BlockInit.LARGE_VIBRANIUM_BUD.get().defaultBlockState(),
                                BlockInit.VIBRANIUM_CLUSTER.get().defaultBlockState()
                        ),
                        BlockTags.FEATURES_CANNOT_REPLACE,
                        BlockTags.GEODE_INVALID_BLOCKS
                ),
                new GeodeLayerSettings(1.7, 2.2, 3.2, 4.2),
                new GeodeCrackSettings(0.5, 2.0, 2),
                0.35,
                0.083,
                true,
                UniformInt.of(5, 6),
                UniformInt.of(4, 5),
                UniformInt.of(1, 4),
                -16,
                16,
                0.05,
                1
        ));

        FeatureUtils.register(context, SOUL_TREE, FeatureInit.SOUL_TREE.get(), soulTree().build());
        FeatureUtils.register(context, SOUL_TREE_SMALL, FeatureInit.SOUL_TREE_SMALL.get(), soulTreeSmall().build());
        FeatureUtils.register(context, SOUL_TREE_MINI, FeatureInit.SOUL_TREE_MINI.get(), soulTreeMini().build());
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Vibranium.MODID, name));
    }
}
