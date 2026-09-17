package de.bigbull.vibranium.data.worldgen.structure;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.bigbull.vibranium.data.worldgen.ModConfiguredFeatures;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.custom.block.HSHBushBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.synth.Noise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.FluidState;

import java.util.*;
import java.util.function.Predicate;

public record VibraniumGeodeFeature(
    GeodeBlockSettings blockSettings,
    GeodeLayerSettings layerSettings,
    GeodeCrackSettings crackSettings,
    double usePotentialPlacementsChance,
    double useAlternateLayer0Chance,
    boolean placementsRequireLayer0Alternate,
    IntProvider outerWallDistance,
    IntProvider distributionPoints,
    IntProvider pointOffset,
    int minGenOffset,
    int maxGenOffset,
    double noiseMultiplier,
    int invalidBlocksThreshold
) implements Feature {
    private static final NormalNoise NOISE_PARAMETERS = NormalNoise.createParity(-4, 1.0);
    public static final Codec<Double> CHANCE_RANGE = Codec.doubleRange(0.0, 1.0);
    public static final MapCodec<VibraniumGeodeFeature> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                GeodeBlockSettings.CODEC.fieldOf("blocks").forGetter(VibraniumGeodeFeature::blockSettings),
                GeodeLayerSettings.CODEC.fieldOf("layers").forGetter(VibraniumGeodeFeature::layerSettings),
                GeodeCrackSettings.CODEC.fieldOf("crack").forGetter(VibraniumGeodeFeature::crackSettings),
                CHANCE_RANGE.optionalFieldOf("use_potential_placements_chance", 0.35).forGetter(VibraniumGeodeFeature::usePotentialPlacementsChance),
                CHANCE_RANGE.optionalFieldOf("use_alternate_layer0_chance", 0.0).forGetter(VibraniumGeodeFeature::useAlternateLayer0Chance),
                Codec.BOOL.optionalFieldOf("placements_require_layer0_alternate", true).forGetter(VibraniumGeodeFeature::placementsRequireLayer0Alternate),
                IntProviders.codec(1, 20).optionalFieldOf("outer_wall_distance", UniformInt.of(4, 5)).forGetter(VibraniumGeodeFeature::outerWallDistance),
                IntProviders.codec(1, 20).optionalFieldOf("distribution_points", UniformInt.of(3, 4)).forGetter(VibraniumGeodeFeature::distributionPoints),
                IntProviders.codec(0, 10).optionalFieldOf("point_offset", UniformInt.of(1, 2)).forGetter(VibraniumGeodeFeature::pointOffset),
                Codec.INT.optionalFieldOf("min_gen_offset", -16).forGetter(VibraniumGeodeFeature::minGenOffset),
                Codec.INT.optionalFieldOf("max_gen_offset", 16).forGetter(VibraniumGeodeFeature::maxGenOffset),
                CHANCE_RANGE.optionalFieldOf("noise_multiplier", 0.05).forGetter(VibraniumGeodeFeature::noiseMultiplier),
                Codec.INT.fieldOf("invalid_blocks_threshold").forGetter(VibraniumGeodeFeature::invalidBlocksThreshold)
            )
            .apply(i, VibraniumGeodeFeature::new)
    );

    private static final Direction[] DIRECTIONS = Direction.values();

    @Override
    public MapCodec<VibraniumGeodeFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        List<Pair<BlockPos, Integer>> points = Lists.newLinkedList();
        int numPoints = this.distributionPoints.sample(random);
        Noise noise = NOISE_PARAMETERS.create(new WorldgenRandom(new LegacyRandomSource(level.getSeed())));
        List<BlockPos> crackPoints = Lists.newLinkedList();
        double crackSizeAdjustment = (double) numPoints / (double) this.outerWallDistance.maxInclusive();
        double innerAir = 1.0 / Math.sqrt(this.layerSettings.filling);
        double innermostBlockLayer = 1.0 / Math.sqrt(this.layerSettings.innerLayer + crackSizeAdjustment);
        double innerCrust = 1.0 / Math.sqrt(this.layerSettings.middleLayer + crackSizeAdjustment);
        double outerCrust = 1.0 / Math.sqrt(this.layerSettings.outerLayer + crackSizeAdjustment);
        double crackSize = 1.0 / Math.sqrt(this.crackSettings.baseCrackSize + random.nextDouble() / 2.0
                + (numPoints > 3 ? crackSizeAdjustment : 0.0));
        boolean shouldGenerateCrack = (double) random.nextFloat() < this.crackSettings.generateCrackChance;
        int numInvalidPoints = 0;

        Map<Integer, List<BlockPos>> innerLayerPositionsByY = new HashMap<>();
        int lowestY = Integer.MAX_VALUE;

        for (int i = 0; i < numPoints; i++) {
            int x = this.outerWallDistance.sample(random);
            int y = this.outerWallDistance.sample(random);
            int z = this.outerWallDistance.sample(random);
            BlockPos pos = origin.offset(x, y, z);
            BlockState state = level.getBlockState(pos);
            if (state.isAir() || state.is(this.blockSettings.invalidBlocks())) {
                if (++numInvalidPoints > this.invalidBlocksThreshold) {
                    return false;
                }
            }

            points.add(Pair.of(pos, this.pointOffset.sample(random)));
        }

        if (shouldGenerateCrack) {
            int offsetIndex = random.nextInt(4);
            int crackOffset = numPoints * 2 + 1;
            if (offsetIndex == 0) {
                crackPoints.add(origin.offset(crackOffset, 7, 0));
                crackPoints.add(origin.offset(crackOffset, 5, 0));
                crackPoints.add(origin.offset(crackOffset, 1, 0));
            } else if (offsetIndex == 1) {
                crackPoints.add(origin.offset(0, 7, crackOffset));
                crackPoints.add(origin.offset(0, 5, crackOffset));
                crackPoints.add(origin.offset(0, 1, crackOffset));
            } else if (offsetIndex == 2) {
                crackPoints.add(origin.offset(crackOffset, 7, crackOffset));
                crackPoints.add(origin.offset(crackOffset, 5, crackOffset));
                crackPoints.add(origin.offset(crackOffset, 1, crackOffset));
            } else {
                crackPoints.add(origin.offset(0, 7, 0));
                crackPoints.add(origin.offset(0, 5, 0));
                crackPoints.add(origin.offset(0, 1, 0));
            }
        }

        List<BlockPos> potentialCrystalPlacements = Lists.newArrayList();
        HolderSet<Block> cantReplace = this.blockSettings.cannotReplace();
        Predicate<BlockState> canReplace = s -> !s.is(cantReplace);

        for (BlockPos pointInside : BlockPos.betweenClosed(
                origin.offset(this.minGenOffset, this.minGenOffset, this.minGenOffset),
                origin.offset(this.maxGenOffset, this.maxGenOffset, this.maxGenOffset))) {
            double noiseOffset = noise.get((double) pointInside.getX(), (double) pointInside.getY(),
                    (double) pointInside.getZ()) * this.noiseMultiplier;
            double distSumShell = 0.0;
            double distSumCrack = 0.0;

            for (Pair<BlockPos, Integer> point : points) {
                distSumShell += Mth.invSqrt(pointInside.distSqr(point.getFirst()) + (double) point.getSecond().intValue()) + noiseOffset;
            }

            for (BlockPos point : crackPoints) {
                distSumCrack += Mth.invSqrt(pointInside.distSqr(point) + (double) this.crackSettings.crackPointOffset) + noiseOffset;
            }

            if (!(distSumShell < outerCrust)) {
                if (shouldGenerateCrack && distSumCrack >= crackSize && distSumShell < innerAir) {
                    this.safeSetBlock(level, pointInside, Blocks.AIR.defaultBlockState(), canReplace);

                    for (Direction direction : DIRECTIONS) {
                        BlockPos adjacentPos = pointInside.relative(direction);
                        FluidState adjacentFluidState = level.getFluidState(adjacentPos);
                        if (!adjacentFluidState.isEmpty()) {
                            level.scheduleTick(adjacentPos, adjacentFluidState.getType(), 0);
                        }
                    }
                } else if (distSumShell >= innerAir) {
                    this.safeSetBlock(level, pointInside,
                            this.blockSettings.fillingProvider().value().getState(level, random, pointInside),
                            canReplace);
                } else if (distSumShell >= innermostBlockLayer) {
                    boolean useAlternateLayer = random.nextFloat() < this.useAlternateLayer0Chance;
                    BlockState innerLayerState = useAlternateLayer
                            ? this.blockSettings.alternateInnerLayerProvider().value().getState(level, random, pointInside)
                            : this.blockSettings.innerLayerProvider().value().getState(level, random, pointInside);

                    if (innerLayerState.is(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get())) {
                        int y = pointInside.getY();
                        innerLayerPositionsByY.computeIfAbsent(y, k2 -> new ArrayList<>()).add(pointInside.immutable());
                        lowestY = Math.min(lowestY, y);
                    }

                    this.safeSetBlock(level, pointInside, innerLayerState, canReplace);

                    if ((!this.placementsRequireLayer0Alternate || useAlternateLayer)
                            && (double) random.nextFloat() < this.usePotentialPlacementsChance) {
                        potentialCrystalPlacements.add(pointInside.immutable());
                    }
                } else if (distSumShell >= innerCrust) {
                    this.safeSetBlock(level, pointInside,
                            this.blockSettings.middleLayerProvider().value().getState(level, random, pointInside),
                            canReplace);
                } else if (distSumShell >= outerCrust) {
                    this.safeSetBlock(level, pointInside,
                            this.blockSettings.outerLayerProvider().value().getState(level, random, pointInside),
                            canReplace);
                }
            }
        }

        List<BlockState> innerPlacements = this.blockSettings.innerPlacements();
        for (BlockPos crystalPos : potentialCrystalPlacements) {
            BlockState blockState = Util.getRandom(innerPlacements, random);

            for (Direction direction : DIRECTIONS) {
                if (blockState.hasProperty(BlockStateProperties.FACING)) {
                    blockState = blockState.setValue(BlockStateProperties.FACING, direction);
                }

                BlockPos placePos = crystalPos.relative(direction);
                BlockState placeState = level.getBlockState(placePos);
                if (blockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                    blockState = blockState.setValue(BlockStateProperties.WATERLOGGED,
                            placeState.getFluidState().isSource());
                }

                if (BuddingAmethystBlock.canClusterGrowAtState(placeState)) {
                    this.safeSetBlock(level, placePos, blockState, canReplace);
                    break;
                }
            }
        }

        List<BlockPos> enrichedDirtPositions = new ArrayList<>();
        if (lowestY != Integer.MAX_VALUE) {
            Integer airExposedLevel = null;

            for (int y = lowestY; y < lowestY + 10 && airExposedLevel == null; y++) {
                List<BlockPos> positions = innerLayerPositionsByY.get(y);
                if (positions == null)
                    continue;

                for (BlockPos pos : positions) {
                    BlockPos abovePos = pos.above();
                    if (level.isEmptyBlock(abovePos)) {
                        airExposedLevel = y;
                        break;
                    }
                }
            }

            if (airExposedLevel != null) {
                for (int y = airExposedLevel; y <= airExposedLevel + 2; y++) {
                    List<BlockPos> positions = innerLayerPositionsByY.get(y);
                    if (positions == null)
                        continue;

                    for (BlockPos pos : positions) {
                        BlockPos abovePos = pos.above();
                        if (level.isEmptyBlock(abovePos)) {
                            if (random.nextFloat() < 0.3F) {
                                this.safeSetBlock(level, pos,
                                        BlockInit.ENRICHED_VIBRANIUM_DIRT.get().defaultBlockState(), canReplace);
                                enrichedDirtPositions.add(pos.immutable());
                            }
                        }
                    }
                }
            }
        }

        placeVegetation(level, chunkGenerator, enrichedDirtPositions, random);

        return true;
    }

    private void placeVegetation(WorldGenLevel world, ChunkGenerator chunkGenerator, List<BlockPos> enrichedDirtPositions, RandomSource random) {
        if (enrichedDirtPositions.isEmpty())
            return;

        Collections.shuffle(enrichedDirtPositions, new Random(random.nextLong()));

        for (BlockPos dirtPos : enrichedDirtPositions) {
            BlockPos above = dirtPos.above();
            if (!world.isEmptyBlock(above))
                continue;

            if (random.nextFloat() < 0.35F && hasEnoughSpaceForTree(world, above)) {
                placeSoulTree(world, chunkGenerator, above, random);
            } else if (random.nextFloat() < 0.45F) {
                world.setBlock(above, BlockInit.HEART_SHAPED_HERB_BUSH.get().defaultBlockState()
                        .setValue(HSHBushBlock.AGE, random.nextInt(4)), 2);
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

    private void placeSoulTree(WorldGenLevel worldgenlevel, ChunkGenerator chunkGenerator, BlockPos soulTreePos, RandomSource random) {
        BlockState before = worldgenlevel.getBlockState(soulTreePos);

        TreeFeature soulTree;
        if (random.nextFloat() < 0.25F) {
            soulTree = ModConfiguredFeatures.soulTree().build();
        } else {
            soulTree = ModConfiguredFeatures.soulTreeSmall().build();
        }

        soulTree.place(worldgenlevel, chunkGenerator, random, soulTreePos);

        BlockState after = worldgenlevel.getBlockState(soulTreePos);
        if (before != after && !after.isAir()) {
            return;
        }

        if (random.nextFloat() < 0.2F) {
            TreeFeature miniTree = ModConfiguredFeatures.soulTreeMini().build();
            miniTree.place(worldgenlevel, chunkGenerator, random, soulTreePos);
        } else if (random.nextFloat() < 0.3F) {
            worldgenlevel.setBlock(soulTreePos, BlockInit.HEART_SHAPED_HERB_BUSH.get().defaultBlockState()
                    .setValue(HSHBushBlock.AGE, random.nextInt(2)), 2);
        } else if (random.nextFloat() < 0.1F) {
            worldgenlevel.setBlock(soulTreePos, BlockInit.SOULWOOD_SAPLING.get().defaultBlockState(), 2);
        }
    }
}
