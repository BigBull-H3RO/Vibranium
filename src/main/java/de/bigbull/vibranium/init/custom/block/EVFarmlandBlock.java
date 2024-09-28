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
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class EVFarmlandBlock extends FarmBlock {
    public EVFarmlandBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int moisture = state.getValue(MOISTURE);
        if (!isNearWater(level, pos) && !level.isRainingAt(pos.above())) {
            if (moisture > 0) {
                level.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(moisture - 1)), 2);
            } else if (!shouldMaintainFarmland(level, pos)) {
                turnToEnrichedDirt(null, state, level, pos);
            }
        } else if (moisture < 7) {
            level.setBlock(pos, state.setValue(MOISTURE, Integer.valueOf(7)), 2);
        }

        BlockPos abovePos = pos.above();
        BlockState plantState = level.getBlockState(abovePos);

        if (plantState.getBlock() instanceof BonemealableBlock plant) {
            if (random.nextFloat() < 0.15F) {
                if (plant.isValidBonemealTarget(level, abovePos, plantState)) {
                    plant.performBonemeal(level, random, abovePos, plantState);
                }
            }
        }
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!level.isClientSide && net.neoforged.neoforge.common.CommonHooks.onFarmlandTrample(level, pos, null, fallDistance, entity)) {
            turnToEnrichedDirt(entity, state, level, pos);
        }
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            turnToEnrichedDirt(null, state, level, pos);
        }
    }

    public static void turnToEnrichedDirt(@Nullable Entity entity, BlockState state, Level level, BlockPos pos) {
        BlockState enrichedDirt = BlockInit.ENRICHED_VIBRANIUM_DIRT.get().defaultBlockState();
        BlockState newState = pushEntitiesUp(state, enrichedDirt, level, pos);
        level.setBlockAndUpdate(pos, newState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, newState));
    }

    private static boolean shouldMaintainFarmland(BlockGetter world, BlockPos pos) {
        return world.getBlockState(pos.above()).is(BlockTags.MAINTAINS_FARMLAND);
    }

    private static boolean isNearWater(LevelReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (state.canBeHydrated(world, pos, world.getFluidState(blockpos), blockpos)) {
                return true;
            }
        }

        return net.neoforged.neoforge.common.FarmlandWaterManager.hasBlockWaterTicket(world, pos);
    }
}
