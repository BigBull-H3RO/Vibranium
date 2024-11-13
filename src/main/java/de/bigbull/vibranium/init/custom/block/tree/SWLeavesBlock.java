package de.bigbull.vibranium.init.custom.block.tree;

import com.mojang.serialization.MapCodec;
import de.bigbull.vibranium.init.ParticleInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SWLeavesBlock extends LeavesBlock {
    public static final MapCodec<SWLeavesBlock> CODEC = simpleCodec(SWLeavesBlock::new);

    @Override
    public MapCodec<SWLeavesBlock> codec() {
        return CODEC;
    }

    public SWLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(blockState, level, pos, random);
        if (random.nextInt(10) == 0) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
                ParticleUtils.spawnParticleBelow(level, pos, random, ParticleInit.SOULWOOD_LEAVES.get());
            }
        }
    }
}
