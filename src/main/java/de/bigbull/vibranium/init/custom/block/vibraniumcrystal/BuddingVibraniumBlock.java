package de.bigbull.vibranium.init.custom.block.vibraniumcrystal;

import com.mojang.serialization.MapCodec;
import de.bigbull.vibranium.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;

public class BuddingVibraniumBlock extends AmethystBlock {
    public static final MapCodec<BuddingVibraniumBlock> CODEC = simpleCodec(BuddingVibraniumBlock::new);
    public static final Direction[] DIRECTIONS = Direction.values();
    private static final int GROWTH_CHANCE = 5;

    @Override
    public MapCodec<BuddingVibraniumBlock> codec() {
        return CODEC;
    }

    public BuddingVibraniumBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(GROWTH_CHANCE) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos adjacentPos = pos.relative(direction);
            BlockState adjacentState = level.getBlockState(adjacentPos);
            Block block = null;
            if (canClusterGrowAtState(adjacentState)) {
                block = BlockInit.SMALL_VIBRANIUM_BUD.get();
            } else if (adjacentState.is(BlockInit.SMALL_VIBRANIUM_BUD.get()) && adjacentState.getValue(BlockStateProperties.FACING) == direction) {
                block = BlockInit.MEDIUM_VIBRANIUM_BUD.get();
            } else if (adjacentState.is(BlockInit.MEDIUM_VIBRANIUM_BUD.get()) && adjacentState.getValue(BlockStateProperties.FACING) == direction) {
                block = BlockInit.LARGE_VIBRANIUM_BUD.get();
            } else if (adjacentState.is(BlockInit.LARGE_VIBRANIUM_BUD.get()) && adjacentState.getValue(BlockStateProperties.FACING) == direction) {
                block = BlockInit.VIBRANIUM_CLUSTER.get();
            }

            if (block != null) {
                BlockState newState = block.defaultBlockState()
                        .setValue(BlockStateProperties.FACING, direction)
                        .setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(state.getFluidState().getType() == Fluids.WATER));
                level.setBlockAndUpdate(adjacentPos, newState);
            }
        }
    }

    private boolean canClusterGrowAtState(BlockState state) {
        return state.isAir() || (state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8);
    }
}