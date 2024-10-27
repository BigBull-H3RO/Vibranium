package de.bigbull.vibranium.data.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import de.bigbull.vibranium.init.BlockInit;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class VibraniumStructure extends Feature<NoneFeatureConfiguration> {
    private static final Direction[] DIRECTIONS = Direction.values(); // Hier die DIRECTIONS hinzufügen
    private final NormalNoise noise;

    public VibraniumStructure(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
        this.noise = NormalNoise.create(RandomSource.create(), -4, 1.0);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        int baseRadius = 8;
        int variation = random.nextInt(10) - 5;
        int radius = baseRadius + variation;

        createStructure(level, origin, radius, random);

        return true;
    }

    private void createStructure(WorldGenLevel level, BlockPos origin, int radius, RandomSource random) {
        double noiseScale = 0.6;
        double shapeDistortionFactor = random.nextDouble() * 0.3 + 0.7;

        for (BlockPos pos : BlockPos.betweenClosed(origin.offset(-(radius + 3), -(radius + 3), -(radius + 3)),
                origin.offset(radius + 3, radius + 3, radius + 3))) {

            double distance = pos.distSqr(origin);
            double noiseValue = noise.getValue(pos.getX(), pos.getY(), pos.getZ()) * noiseScale;
            double adjustedDistance = distance * (1.0 + noiseValue) * shapeDistortionFactor;

            if (adjustedDistance <= (radius + 2) * (radius + 2) && adjustedDistance > (radius + 1) * (radius + 1)) {
                level.setBlock(pos, Blocks.SMOOTH_BASALT.defaultBlockState(), 3);
            }
            else if (adjustedDistance <= (radius + 1) * (radius + 1) && adjustedDistance > radius * radius) {
                level.setBlock(pos, Blocks.TUFF.defaultBlockState(), 3);
            }
            else if (adjustedDistance <= radius * radius) {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

                if (adjustedDistance >= (radius - 1) * (radius - 1)) {
                    level.setBlock(pos, BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get().defaultBlockState(), 3);

                    if (random.nextFloat() < 0.1) {
                        level.setBlock(pos, BlockInit.BUDDING_VIBRANIUM_CRYSTAL.get().defaultBlockState(), 3);
                    }
                }

                BlockState blockState = level.getBlockState(pos);

                if (pos.getY() == origin.getY() - radius || pos.getY() == origin.getY() - radius + 1 || pos.getY() == origin.getY() - radius + 2 || pos.getY() == origin.getY() - radius - 1) {
                    if (!blockState.is(Blocks.AIR) && (blockState.is(BlockInit.VIBRANIUM_CRYSTAL_BLOCK) || blockState.is(BlockInit.BUDDING_VIBRANIUM_CRYSTAL))) {
                        if (random.nextFloat() < 0.3) {
                            level.setBlock(pos, BlockInit.ENRICHED_VIBRANIUM_DIRT.get().defaultBlockState(), 3);
                        }
                    }
                }

                if (random.nextFloat() < 0.75) {
                    placeBudsOrCluster(level, pos, random);
                }
            }
        }
    }

    // Angepasste Methode zur Platzierung von Buds/Clustern
    private void placeBudsOrCluster(WorldGenLevel level, BlockPos pos, RandomSource random) {
        if (!level.getBlockState(pos).is(BlockInit.BUDDING_VIBRANIUM_CRYSTAL.get())) {
            return;
        }

        // Hier verwenden wir DIRECTIONS, um durch alle Richtungen zu iterieren
        for (Direction direction : DIRECTIONS) {
            BlockPos adjacentPos = pos.relative(direction);
            BlockState adjacentBlockState = level.getBlockState(adjacentPos);

            if (adjacentBlockState.isAir()) {
                BlockState budOrClusterState = getRandomBudOrCluster(random);

                // Setze die Ausrichtung des Buds/Clusters in die Richtung, in der er wachsen soll
                if (budOrClusterState.hasProperty(BlockStateProperties.FACING)) {
                    budOrClusterState = budOrClusterState.setValue(BlockStateProperties.FACING, direction);
                }

                level.setBlock(adjacentPos, budOrClusterState, 3);
                break; // Nach Platzierung abbrechen
            }
        }
    }

    // Methode zur zufälligen Auswahl von Bud oder Cluster
    private BlockState getRandomBudOrCluster(RandomSource random) {
        float chance = random.nextFloat();
        if (chance < 0.25f) {
            return BlockInit.VIBRANIUM_CLUSTER.get().defaultBlockState();
        } else if (chance < 0.5f) {
            return BlockInit.LARGE_VIBRANIUM_BUD.get().defaultBlockState();
        } else if (chance < 0.75f) {
            return BlockInit.MEDIUM_VIBRANIUM_BUD.get().defaultBlockState();
        } else {
            return BlockInit.SMALL_VIBRANIUM_BUD.get().defaultBlockState();
        }
    }
}
