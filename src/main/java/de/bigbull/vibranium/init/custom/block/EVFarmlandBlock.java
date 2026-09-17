package de.bigbull.vibranium.init.custom.block;

import de.bigbull.vibranium.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class EVFarmlandBlock extends FarmlandBlock {
    public EVFarmlandBlock(Block baseBlock, BlockBehaviour.Properties properties) {
        super(baseBlock, properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int moisture = state.getValue(MOISTURE);
        if (!isNearWater(level, pos) && !level.isRainingAt(pos.above())) {
            if (moisture > 0) {
                level.setBlock(pos, state.setValue(MOISTURE, moisture - 1), 2);
            } else if (!shouldMaintainFarmland(level, pos)) {
                turnToBaseBlock(null, state, level, pos);
            }
        } else if (moisture < 7) {
            level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
        }

        BlockPos abovePos = pos.above();
        BlockState plantState = level.getBlockState(abovePos);

        if (plantState.getBlock() instanceof BonemealableBlock plant) {
            if (random.nextFloat() < 0.2F) {
                if (plant.isValidBonemealTarget(level, abovePos, plantState, BonemealSource.MOB)) {
                    plant.performBonemeal(level, random, abovePos, plantState, BonemealSource.MOB);
                }
            }
        }
    }

    @Override
    public void fallOn(Level level, BlockState blockState, BlockPos pos, Entity entity, double fallDistance) {
        if (level instanceof ServerLevel serverlevel
                && net.neoforged.neoforge.common.CommonHooks.onFarmlandTrample(serverlevel, pos, BlockInit.ENRICHED_VIBRANIUM_DIRT.get().defaultBlockState(), fallDistance, entity)) {
            turnToBaseBlock(entity, blockState, level, pos);
        }

        super.fallOn(level, blockState, pos, entity, fallDistance);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            turnToBaseBlock(null, state, level, pos);
        }
    }

    private static boolean shouldMaintainFarmland(BlockGetter world, BlockPos pos) {
        return world.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
    }

    private static boolean isNearWater(LevelReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (state.canBeHydrated(world, blockpos, world.getFluidState(blockpos))) {
                return true;
            }
        }

        return net.neoforged.neoforge.common.FarmlandWaterManager.hasBlockWaterTicket(world, pos);
    }
}
