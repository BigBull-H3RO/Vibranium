package de.bigbull.vibranium.data.worldgen;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.data.worldgen.structure.VibraniumGeodeFeature;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.custom.SoulTreeTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
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
import net.minecraft.world.level.levelgen.feature.BlockReplacement;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<Feature> OVERWORLD_VIBRANIUM_ORE = createKey("overworld_vibranium_ore");
    public static final ResourceKey<Feature> SOUL_TREE = createKey("soul_tree");
    public static final ResourceKey<Feature> SOUL_TREE_SMALL = createKey("soul_tree_small");
    public static final ResourceKey<Feature> SOUL_TREE_MINI = createKey("soul_tree_mini");
    public static final ResourceKey<Feature> VIBRANIUM_GEODE = createKey("vibranium_geode");

    public static TreeFeature.Builder soulTree() {
        return new TreeFeature.Builder(
                BlockStateProvider.of(BlockInit.SOULWOOD_LOG.get()),
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
                                        .build()),
                        UniformInt.of(2, 5),
                        UniformInt.of(-4, -1),
                        UniformInt.of(-2, 2)),
                BlockStateProvider.of(BlockInit.SOULWOOD_LEAVES.get()),
                new CherryFoliagePlacer(
                        ConstantInt.of(4),
                        ConstantInt.of(0),
                        ConstantInt.of(5),
                        0.25F,
                        0.5F,
                        0.16666667F,
                        0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.holderOf(Blocks.DIRT)).ignoreVines();
    }

    public static TreeFeature.Builder soulTreeSmall() {
        return new TreeFeature.Builder(
                BlockStateProvider.of(BlockInit.SOULWOOD_LOG.get()),
                new ForkingTrunkPlacer(5, 2, 2),
                BlockStateProvider.of(BlockInit.SOULWOOD_LEAVES.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.holderOf(Blocks.DIRT)).ignoreVines();
    }

    public static TreeFeature.Builder soulTreeMini() {
        return new TreeFeature.Builder(
                BlockStateProvider.of(BlockInit.SOULWOOD_LOG.get()),
                new ForkingTrunkPlacer(3, 1, 0),
                BlockStateProvider.of(BlockInit.SOULWOOD_LEAVES.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1),
                BlockStateProvider.holderOf(Blocks.DIRT)).ignoreVines();
    }

    public static void bootstrap(BootstrapContext<Feature> context) {
        RuleTest stoneReplacable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<BlockReplacement> vibraniumOre = List.of(
                BlockReplacement.replace(stoneReplacable, BlockInit.DEEPSLATE_VIBRANIUM_ORE.get().defaultBlockState()),
                BlockReplacement.replace(deepslateReplacable, BlockInit.DEEPSLATE_VIBRANIUM_ORE.get().defaultBlockState()));

        context.register(OVERWORLD_VIBRANIUM_ORE, new OreFeature(vibraniumOre, 4));

        context.register(VIBRANIUM_GEODE,
                new VibraniumGeodeFeature(
                        new GeodeBlockSettings(
                                BlockStateProvider.holderOf(Blocks.AIR),
                                BlockStateProvider.holderOf(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get()),
                                BlockStateProvider.holderOf(BlockInit.BUDDING_VIBRANIUM_CRYSTAL.get()),
                                BlockStateProvider.holderOf(Blocks.CALCITE),
                                BlockStateProvider.holderOf(Blocks.SMOOTH_BASALT),
                                List.of(
                                        BlockInit.SMALL_VIBRANIUM_BUD.get().defaultBlockState(),
                                        BlockInit.MEDIUM_VIBRANIUM_BUD.get().defaultBlockState(),
                                        BlockInit.LARGE_VIBRANIUM_BUD.get().defaultBlockState(),
                                        BlockInit.VIBRANIUM_CLUSTER.get().defaultBlockState()),
                                context.lookup(Registries.BLOCK).getOrThrow(BlockTags.FEATURES_CANNOT_REPLACE),
                                context.lookup(Registries.BLOCK).getOrThrow(BlockTags.GEODE_INVALID_BLOCKS)),
                        new GeodeLayerSettings(1.7, 2.2, 3.2, 4.2),
                        new GeodeCrackSettings(0.5, 2.0, 2),
                        0.35,
                        0.083,
                        true,
                        UniformInt.of(4, 6),
                        UniformInt.of(3, 4),
                        UniformInt.of(1, 2),
                        -16,
                        16,
                        0.05,
                        1));

        context.register(SOUL_TREE, soulTree().build());
        context.register(SOUL_TREE_SMALL, soulTreeSmall().build());
        context.register(SOUL_TREE_MINI, soulTreeMini().build());
    }

    private static ResourceKey<Feature> createKey(String name) {
        return ResourceKey.create(Registries.FEATURE,
                Identifier.fromNamespaceAndPath(Vibranium.MODID, name));
    }
}
