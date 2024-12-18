package de.bigbull.vibranium.data.worldgen.structure;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import de.bigbull.vibranium.data.worldgen.ModConfiguredFeatures;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.custom.block.HSHBushBlock;
import net.minecraft.Util;
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

public class CustomGeodeFeature extends Feature<GeodeConfiguration>  {
    private static final Direction[] DIRECTIONS = Direction.values();

    public CustomGeodeFeature(Codec<GeodeConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<GeodeConfiguration> context) {
        GeodeConfiguration geodeconfiguration = context.config();
        RandomSource random = context.random();
        BlockPos blockpos = context.origin();
        WorldGenLevel world = context.level();
        int i = geodeconfiguration.minGenOffset;
        int j = geodeconfiguration.maxGenOffset;
        List<Pair<BlockPos, Integer>> list = Lists.newLinkedList();
        int k = geodeconfiguration.distributionPoints.sample(random);
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(world.getSeed()));
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
        double d5 = 1.0 / Math.sqrt(geodecracksettings.baseCrackSize + random.nextDouble() / 2.0 + (k > 3 ? d0 : 0.0));
        boolean generateCrack = (double)random.nextFloat() < geodecracksettings.generateCrackChance;
        int l = 0;

        TreeMap<Integer, List<BlockPos>> innerLayerPositionsByY = new TreeMap<>();

        for (int i1 = 0; i1 < k; i1++) {
            int j1 = geodeconfiguration.outerWallDistance.sample(random);
            int k1 = geodeconfiguration.outerWallDistance.sample(random);
            int l1 = geodeconfiguration.outerWallDistance.sample(random);
            BlockPos blockpos1 = blockpos.offset(j1, k1, l1);
            BlockState blockstate = world.getBlockState(blockpos1);
            if (blockstate.isAir() || blockstate.is(BlockTags.GEODE_INVALID_BLOCKS)) {
                if (++l > geodeconfiguration.invalidBlocksThreshold) {
                    return false;
                }
            }

            list.add(Pair.of(blockpos1, geodeconfiguration.pointOffset.sample(random)));
        }

        if (generateCrack) {
            int i2 = random.nextInt(4);
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
                if (generateCrack && d7 >= d5 && d6 < d1) {
                    this.safeSetBlock(world, blockpos3, Blocks.AIR.defaultBlockState(), predicate);

                    for (Direction direction1 : DIRECTIONS) {
                        BlockPos blockpos2 = blockpos3.relative(direction1);
                        FluidState fluidstate = world.getFluidState(blockpos2);
                        if (!fluidstate.isEmpty()) {
                            world.scheduleTick(blockpos2, fluidstate.getType(), 0);
                        }
                    }
                } else if (d6 >= d1) {
                    this.safeSetBlock(world, blockpos3, geodeblocksettings.fillingProvider.getState(random, blockpos3), predicate);
                } else if (d6 >= d2) {
                    boolean flag1 = random.nextFloat() < geodeconfiguration.useAlternateLayer0Chance;
                    BlockState innerLayerState = flag1
                            ? geodeblocksettings.alternateInnerLayerProvider.getState(random, blockpos3)
                            : geodeblocksettings.innerLayerProvider.getState(random, blockpos3);
                    this.safeSetBlock(world, blockpos3, innerLayerState, predicate);

                    if (innerLayerState.is(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get())) {
                        int y = blockpos3.getY();
                        innerLayerPositionsByY.computeIfAbsent(y, key -> new ArrayList<>()).add(blockpos3.immutable());
                    }

                    if ((!geodeconfiguration.placementsRequireLayer0Alternate || flag1)
                            && random.nextFloat() < geodeconfiguration.usePotentialPlacementsChance) {
                        list2.add(blockpos3.immutable());
                    }
                } else if (d6 >= d3) {
                    this.safeSetBlock(world, blockpos3, geodeblocksettings.middleLayerProvider.getState(random, blockpos3), predicate);
                } else if (d6 >= d4) {
                    this.safeSetBlock(world, blockpos3, geodeblocksettings.outerLayerProvider.getState(random, blockpos3), predicate);
                }
            }
        }

        List<BlockPos> enrichedDirtPositions = new ArrayList<>();
        boolean foundValidLayer = false;

        if (innerLayerPositionsByY.isEmpty()) {
            return false;
        }

        Integer firstY = innerLayerPositionsByY.firstKey();

        for (Integer y : innerLayerPositionsByY.keySet()) {
            List<BlockPos> positionsAtY = innerLayerPositionsByY.get(y);
            List<BlockPos> validPositionsAtY = new ArrayList<>();

            for (BlockPos pos : positionsAtY) {
                BlockPos abovePos = pos.above();
                BlockState aboveState = world.getBlockState(abovePos);
                if (aboveState.isAir()) {
                    validPositionsAtY.add(pos);
                }
            }

            if (!validPositionsAtY.isEmpty()) {
                foundValidLayer = true;
                int maxLevels = 3;
                for (int offsetY = 0; offsetY < maxLevels; offsetY++) {
                    int currentY = y + offsetY;
                    List<BlockPos> positionsAtCurrentY = innerLayerPositionsByY.get(currentY);

                    if (positionsAtCurrentY != null) {
                        double replacementChance = 0.3 - (offsetY * 0.05);
                        if (replacementChance < 0) {
                            replacementChance = 0;
                        }

                        for (BlockPos pos : positionsAtCurrentY) {
                            BlockPos abovePos = pos.above();
                            BlockState aboveState = world.getBlockState(abovePos);
                            if (!aboveState.isAir()) {
                                continue;
                            }

                            BlockState currentState = world.getBlockState(pos);
                            if (currentState.is(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get())) {
                                double randomValue = random.nextDouble();
                                if (randomValue < replacementChance) {
                                    this.safeSetBlock(world, pos, BlockInit.ENRICHED_VIBRANIUM_DIRT.get().defaultBlockState(), predicate);
                                    enrichedDirtPositions.add(pos.immutable());
                                }
                            }
                        }
                    }
                }
                break;
            }
        }

        if (!foundValidLayer) {
            int maxLevels = 3;
            for (int offsetY = 1; offsetY <= maxLevels; offsetY++) {
                int currentY = firstY + offsetY;
                List<BlockPos> positionsAtCurrentY = innerLayerPositionsByY.get(currentY);

                if (positionsAtCurrentY != null) {
                    List<BlockPos> validPositions = new ArrayList<>();

                    for (BlockPos pos : positionsAtCurrentY) {
                        BlockPos abovePos = pos.above();
                        BlockState aboveState = world.getBlockState(abovePos);
                        if (aboveState.isAir()) {
                            validPositions.add(pos);
                        }
                    }

                    if (!validPositions.isEmpty()) {
                        for (int offsetY2 = 0; offsetY2 < maxLevels; offsetY2++) {
                            int currentY2 = currentY + offsetY2;
                            List<BlockPos> positionsAtCurrentY2 = innerLayerPositionsByY.get(currentY2);

                            if (positionsAtCurrentY2 != null) {
                                double replacementChance = 0.3 - (offsetY2 * 0.05);

                                if (replacementChance < 0) {
                                    replacementChance = 0;
                                }

                                for (BlockPos pos : positionsAtCurrentY2) {
                                    BlockPos abovePos = pos.above();
                                    BlockState aboveState = world.getBlockState(abovePos);
                                    if (!aboveState.isAir()) {
                                        continue;
                                    }
                                    BlockState currentState = world.getBlockState(pos);
                                    if (currentState.is(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get())) {
                                        double randomValue = random.nextDouble();
                                        if (randomValue < replacementChance) {
                                            this.safeSetBlock(world, pos, BlockInit.ENRICHED_VIBRANIUM_DIRT.get().defaultBlockState(), predicate);
                                            enrichedDirtPositions.add(pos.immutable());
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }

        if (!enrichedDirtPositions.isEmpty()) {
            int treesToPlace = Mth.nextInt(random, 2, 5);
            Collections.shuffle(enrichedDirtPositions, new Random(random.nextLong()));
            List<BlockPos> treePositions = enrichedDirtPositions.subList(0, Math.min(treesToPlace, enrichedDirtPositions.size()));

            for (BlockPos treePos : treePositions) {
                BlockPos treePositionAbove = treePos.above();

                if (isSpaceAvailableForTree(world, treePositionAbove, 3)) {
                    placeSoulTree(world, treePositionAbove, random, context);
                }
            }

            for (BlockPos dirtPos : enrichedDirtPositions) {
                BlockPos bushPositionAbove = dirtPos.above();

                if (world.isEmptyBlock(bushPositionAbove) && random.nextDouble() < 0.2) {
                    int randomAge = random.nextInt(4);

                    BlockState bushState = BlockInit.HEART_SHAPED_HERB_BUSH.get().defaultBlockState().setValue(HSHBushBlock.AGE, randomAge);
                    world.setBlock(bushPositionAbove, bushState, 3);
                }
            }
        }

        List<BlockState> list3 = geodeblocksettings.innerPlacements;

        for (BlockPos blockpos4 : list2) {
            BlockState blockstate1 = Util.getRandom(list3, random);

            for (Direction direction : DIRECTIONS) {
                if (blockstate1.hasProperty(BlockStateProperties.FACING)) {
                    blockstate1 = blockstate1.setValue(BlockStateProperties.FACING, direction);
                }

                BlockPos blockpos5 = blockpos4.relative(direction);
                BlockState blockstate2 = world.getBlockState(blockpos5);
                if (blockstate1.hasProperty(BlockStateProperties.WATERLOGGED)) {
                    blockstate1 = blockstate1.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(blockstate2.getFluidState().isSource()));
                }

                if (BuddingAmethystBlock.canClusterGrowAtState(blockstate2)) {
                    this.safeSetBlock(world, blockpos5, blockstate1, predicate);
                    break;
                }
            }
        }
        return true;
    }

    private boolean isSpaceAvailableForTree(WorldGenLevel world, BlockPos pos, int treeHeight) {
        for (int yOffset = 0; yOffset < treeHeight; yOffset++) {
            BlockPos checkPos = pos.above(yOffset);
            if (!world.isEmptyBlock(checkPos)) {
                return false;
            }
        }
        return true;
    }

    private void placeSoulTree(WorldGenLevel worldgenlevel, BlockPos soulTreePos, RandomSource randomsource, FeaturePlaceContext<GeodeConfiguration> context) {
        TreeConfiguration soulTreeConfig;
        if (randomsource.nextFloat() < 0.4F) {
            soulTreeConfig = ModConfiguredFeatures.soulTree().build();
        } else {
            soulTreeConfig = ModConfiguredFeatures.soulTreeSmall().build();
        }
        Feature.TREE.place(
                new FeaturePlaceContext<>(
                        Optional.empty(),
                        worldgenlevel,
                        context.chunkGenerator(),
                        randomsource,
                        soulTreePos,
                        soulTreeConfig
                )
        );
    }
}