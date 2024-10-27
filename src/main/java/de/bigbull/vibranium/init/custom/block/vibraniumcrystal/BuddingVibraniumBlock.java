package de.bigbull.vibranium.init.custom.block.vibraniumcrystal;

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
    public static final Direction[] DIRECTIONS = Direction.values();
    private static final int GROWTH_CHANCE = 5;

    public BuddingVibraniumBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(GROWTH_CHANCE) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos adjacentPos = pos.relative(direction);
            BlockState adjacentState = level.getBlockState(adjacentPos);
            Block newBlock = null;

            if (canClusterGrowAtState(adjacentState)) {
                newBlock = BlockInit.SMALL_VIBRANIUM_BUD.get();
            } else if (adjacentState.is(BlockInit.SMALL_VIBRANIUM_BUD.get()) && adjacentState.getValue(BlockStateProperties.FACING) == direction) {
                newBlock = BlockInit.MEDIUM_VIBRANIUM_BUD.get();
            } else if (adjacentState.is(BlockInit.MEDIUM_VIBRANIUM_BUD.get()) && adjacentState.getValue(BlockStateProperties.FACING) == direction) {
                newBlock = BlockInit.LARGE_VIBRANIUM_BUD.get();
            } else if (adjacentState.is(BlockInit.LARGE_VIBRANIUM_BUD.get()) && adjacentState.getValue(BlockStateProperties.FACING) == direction) {
                newBlock = BlockInit.VIBRANIUM_CLUSTER.get();
            }

            if (newBlock != null) {
                BlockState newState = newBlock.defaultBlockState()
                        .setValue(BlockStateProperties.FACING, direction)
                        .setValue(BlockStateProperties.WATERLOGGED, adjacentState.getFluidState().getType() == Fluids.WATER);
                level.setBlockAndUpdate(adjacentPos, newState);
            }
        }
    }

    private boolean canClusterGrowAtState(BlockState state) {
        return state.isAir() || (state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8);
    }
}