package de.bigbull.vibranium.data.worldgen.structure;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import de.bigbull.vibranium.data.worldgen.ModConfiguredFeatures;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.custom.block.HSHBushBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.FluidState;

import java.util.*;
import java.util.function.Predicate;

public class VibraniumGeodeFeature extends Feature<GeodeConfiguration> {
    private static final Direction[] DIRECTIONS = Direction.values();

    public VibraniumGeodeFeature(Codec<GeodeConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<GeodeConfiguration> context) {
        GeodeConfiguration geodeconfiguration = context.config();
        RandomSource randomsource = context.random();
        BlockPos blockpos = context.origin();
        WorldGenLevel worldgenlevel = context.level();
        int i = geodeconfiguration.minGenOffset;
        int j = geodeconfiguration.maxGenOffset;
        List<Pair<BlockPos, Integer>> list = Lists.newLinkedList();
        int k = geodeconfiguration.distributionPoints.sample(randomsource);
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(worldgenlevel.getSeed()));
        NormalNoise normalnoise = NormalNoise.create(worldgenrandom, -4, 1.0);
        List<BlockPos> list1 = Lists.newLinkedList();
        double d0 = (double)k / (double)geodeconfiguration.outerWallDistance.getMaxValue();
        GeodeLayerSettings geodelayersettings = geodeconfiguration.geodeLayerSettings;
        GeodeBlockSettings geodeblocksettings = geodeconfiguration.geodeBlockSettings;
        GeodeCrackSettings geodecracksettings = geodeconfiguration.geodeCrackSettings;
        double d1 = 1.0 / Math.sqrt(geodelayersettings.filling);
        double d2 = 1.0 / Math.sqrt(geodelayersettings.innerLayer + d0);
        double d3 = 1.0 / Math.sqrt(geodelayersettings.middleLayer + d0);
        double d4 = 1.0 / Math.sqrt(geodelayersettings.outerLayer + d0);
        double d5 = 1.0 / Math.sqrt(geodecracksettings.baseCrackSize + randomsource.nextDouble() / 2.0 + (k > 3 ? d0 : 0.0));
        boolean flag = (double)randomsource.nextFloat() < geodecracksettings.generateCrackChance;
        int l = 0;

        Map<Integer, List<BlockPos>> innerLayerPositionsByY = new HashMap<>();
        int lowestY = Integer.MAX_VALUE;

        for (int i1 = 0; i1 < k; i1++) {
            int j1 = geodeconfiguration.outerWallDistance.sample(randomsource);
            int k1 = geodeconfiguration.outerWallDistance.sample(randomsource);
            int l1 = geodeconfiguration.outerWallDistance.sample(randomsource);
            BlockPos blockpos1 = blockpos.offset(j1, k1, l1);
            BlockState blockstate = worldgenlevel.getBlockState(blockpos1);
            if (blockstate.isAir() || blockstate.is(BlockTags.GEODE_INVALID_BLOCKS)) {
                if (++l > geodeconfiguration.invalidBlocksThreshold) {
                    return false;
                }
            }

            list.add(Pair.of(blockpos1, geodeconfiguration.pointOffset.sample(randomsource)));
        }

        if (flag) {
            int i2 = randomsource.nextInt(4);
            int j2 = k * 2 + 1;
            if (i2 == 0) {
                list1.add(blockpos.offset(j2, 7, 0));
                list1.add(blockpos.offset(j2, 5, 0));
                list1.add(blockpos.offset(j2, 1, 0));
            } else if (i2 == 1) {
                list1.add(blockpos.offset(0, 7, j2));
                list1.add(blockpos.offset(0, 5, j2));
                list1.add(blockpos.offset(0, 1, j2));
            } else if (i2 == 2) {
                list1.add(blockpos.offset(j2, 7, j2));
                list1.add(blockpos.offset(j2, 5, j2));
                list1.add(blockpos.offset(j2, 1, j2));
            } else {
                list1.add(blockpos.offset(0, 7, 0));
                list1.add(blockpos.offset(0, 5, 0));
                list1.add(blockpos.offset(0, 1, 0));
            }
        }

        List<BlockPos> list2 = Lists.newArrayList();
        Predicate<BlockState> predicate = isReplaceable(geodeconfiguration.geodeBlockSettings.cannotReplace);

        for (BlockPos blockpos3 : BlockPos.betweenClosed(blockpos.offset(i, i, i), blockpos.offset(j, j, j))) {
            double d8 = normalnoise.getValue((double)blockpos3.getX(), (double)blockpos3.getY(), (double)blockpos3.getZ()) * geodeconfiguration.noiseMultiplier;
            double d6 = 0.0;
            double d7 = 0.0;

            for (Pair<BlockPos, Integer> pair : list) {
                d6 += Mth.invSqrt(blockpos3.distSqr(pair.getFirst()) + (double)pair.getSecond().intValue()) + d8;
            }

            for (BlockPos blockpos6 : list1) {
                d7 += Mth.invSqrt(blockpos3.distSqr(blockpos6) + (double)geodecracksettings.crackPointOffset) + d8;
            }

            if (!(d6 < d4)) {
                if (flag && d7 >= d5 && d6 < d1) {
                    this.safeSetBlock(worldgenlevel, blockpos3, Blocks.AIR.defaultBlockState(), predicate);

                    for (Direction direction1 : DIRECTIONS) {
                        BlockPos blockpos2 = blockpos3.relative(direction1);
                        FluidState fluidstate = worldgenlevel.getFluidState(blockpos2);
                        if (!fluidstate.isEmpty()) {
                            worldgenlevel.scheduleTick(blockpos2, fluidstate.getType(), 0);
                        }
                    }
                } else if (d6 >= d1) {
                    this.safeSetBlock(worldgenlevel, blockpos3, geodeblocksettings.fillingProvider.getState(randomsource, blockpos3), predicate);
                } else if (d6 >= d2) {
                    boolean flag1 = randomsource.nextFloat() < geodeconfiguration.useAlternateLayer0Chance;
                    BlockState innerLayerState = flag1
                            ? geodeblocksettings.alternateInnerLayerProvider.getState(randomsource, blockpos3)
                            : geodeblocksettings.innerLayerProvider.getState(randomsource, blockpos3);

                    if (innerLayerState.is(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get())) {
                        int y = blockpos3.getY();
                        innerLayerPositionsByY.computeIfAbsent(y, k2 -> new ArrayList<>()).add(blockpos3.immutable());
                        lowestY = Math.min(lowestY, y);
                    }

                    this.safeSetBlock(worldgenlevel, blockpos3, innerLayerState, predicate);

                    if ((!geodeconfiguration.placementsRequireLayer0Alternate || flag1)
                            && (double)randomsource.nextFloat() < geodeconfiguration.usePotentialPlacementsChance) {
                        list2.add(blockpos3.immutable());
                    }
                } else if (d6 >= d3) {
                    this.safeSetBlock(worldgenlevel, blockpos3, geodeblocksettings.middleLayerProvider.getState(randomsource, blockpos3), predicate);
                } else if (d6 >= d4) {
                    this.safeSetBlock(worldgenlevel, blockpos3, geodeblocksettings.outerLayerProvider.getState(randomsource, blockpos3), predicate);
                }
            }
        }

        List<BlockState> list3 = geodeblocksettings.innerPlacements;

        for (BlockPos blockpos4 : list2) {
            BlockState blockstate1 = net.minecraft.Util.getRandom(list3, randomsource);

            for (Direction direction : DIRECTIONS) {
                if (blockstate1.hasProperty(BlockStateProperties.FACING)) {
                    blockstate1 = blockstate1.setValue(BlockStateProperties.FACING, direction);
                }

                BlockPos blockpos5 = blockpos4.relative(direction);
                BlockState blockstate2 = worldgenlevel.getBlockState(blockpos5);
                if (blockstate1.hasProperty(BlockStateProperties.WATERLOGGED)) {
                    blockstate1 = blockstate1.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(blockstate2.getFluidState().isSource()));
                }

                if (BuddingAmethystBlock.canClusterGrowAtState(blockstate2)) {
                    this.safeSetBlock(worldgenlevel, blockpos5, blockstate1, predicate);
                    break;
                }
            }
        }

        List<BlockPos> enrichedDirtPositions = new ArrayList<>();
        if (lowestY != Integer.MAX_VALUE) {
            Integer airExposedLevel = null;
            
            for (int y = lowestY; y < lowestY + 10 && airExposedLevel == null; y++) {
                List<BlockPos> positions = innerLayerPositionsByY.get(y);
                if (positions == null) continue;

                for (BlockPos pos : positions) {
                    BlockPos abovePos = pos.above();
                    if (worldgenlevel.isEmptyBlock(abovePos)) {
                        airExposedLevel = y;
                        break;
                    }
                }
            }

            if (airExposedLevel != null) {
                for (int y = airExposedLevel; y <= airExposedLevel + 2; y++) {
                    List<BlockPos> positions = innerLayerPositionsByY.get(y);
                    if (positions == null) continue;

                    for (BlockPos pos : positions) {
                        BlockPos abovePos = pos.above();
                        if (worldgenlevel.isEmptyBlock(abovePos)) {
                            if (randomsource.nextFloat() < 0.3F) {
                                this.safeSetBlock(worldgenlevel, pos, BlockInit.ENRICHED_VIBRANIUM_DIRT.get().defaultBlockState(), predicate);
                                enrichedDirtPositions.add(pos.immutable());
                            }
                        }
                    }
                }
            }
        }

        placeVegetation(worldgenlevel, enrichedDirtPositions, randomsource, context);

        return true;
    }

    private void placeVegetation(WorldGenLevel world, List<BlockPos> enrichedDirtPositions, RandomSource random, FeaturePlaceContext<GeodeConfiguration> context) {
        if (enrichedDirtPositions.isEmpty()) return;

        Collections.shuffle(enrichedDirtPositions, new Random(random.nextLong()));

        for (BlockPos dirtPos : enrichedDirtPositions) {
            BlockPos above = dirtPos.above();
            if (!world.isEmptyBlock(above)) continue;

            if (random.nextFloat() < 0.35F && hasEnoughSpaceForTree(world, above)) {
                placeSoulTree(world, above, random, context);
            } else if (random.nextFloat() < 0.45F) {
                world.setBlock(above, BlockInit.HEART_SHAPED_HERB_BUSH.get().defaultBlockState().setValue(HSHBushBlock.AGE, random.nextInt(4)), 2);
            } else if (random.nextFloat() < 0.1F) {
                world.setBlock(above, BlockInit.SOULWOOD_SAPLING.get().defaultBlockState(), 2);
            }
        }
    }

    private boolean hasEnoughSpaceForTree(WorldGenLevel world, BlockPos pos) {
        for (int y = 0; y < 4; y++) {
            if (!world.isEmptyBlock(pos.above(y))) {
                return false;
            }
        }
        return true;
    }

    private void placeSoulTree(WorldGenLevel worldgenlevel, BlockPos soulTreePos, RandomSource random, FeaturePlaceContext<GeodeConfiguration> context) {
        BlockState before = worldgenlevel.getBlockState(soulTreePos);

        TreeConfiguration soulTreeConfig;
        if (random.nextFloat() < 0.25F) {
            soulTreeConfig = ModConfiguredFeatures.soulTree().build();
        } else {
            soulTreeConfig = ModConfiguredFeatures.soulTreeSmall().build();
        }

        Feature.TREE.place(
                new FeaturePlaceContext<>(
                        Optional.empty(),
                        worldgenlevel,
                        context.chunkGenerator(),
                        random,
                        soulTreePos,
                        soulTreeConfig
                )
        );

        BlockState after = worldgenlevel.getBlockState(soulTreePos);
        if (before != after && !after.isAir()) {
            return;
        }

        if (random.nextFloat() < 0.2F) {
            TreeConfiguration miniConfig = ModConfiguredFeatures.soulTreeMini().build();
            Feature.TREE.place(
                    new FeaturePlaceContext<>(
                            Optional.empty(),
                            worldgenlevel,
                            context.chunkGenerator(),
                            random,
                            soulTreePos,
                            miniConfig
                    )
            );
        } else if (random.nextFloat() < 0.3F) {
            worldgenlevel.setBlock(soulTreePos, BlockInit.HEART_SHAPED_HERB_BUSH.get().defaultBlockState().setValue(HSHBushBlock.AGE, random.nextInt(2)), 2);
        } else if (random.nextFloat() < 0.1F) {
            worldgenlevel.setBlock(soulTreePos, BlockInit.SOULWOOD_SAPLING.get().defaultBlockState(), 2);
        }
    }
}