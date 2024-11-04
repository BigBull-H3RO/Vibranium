package de.bigbull.vibranium.init.custom.block.tree;

import de.bigbull.vibranium.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SoulTreeSaplingBlock extends SaplingBlock {
    public SoulTreeSaplingBlock(TreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
    }

    @Override
    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        BlockPos groundPos = pos.below();
        BlockState groundState = level.getBlockState(groundPos);

        if (groundState.is(BlockInit.ENRICHED_VIBRANIUM_DIRT.get())) {
            super.advanceTree(level, pos, state, random);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if (itemStack.is(Items.BONE_MEAL)) {
            BlockPos groundPos = pos.below();
            BlockState groundState = level.getBlockState(groundPos);
            if (!groundState.is(BlockInit.ENRICHED_VIBRANIUM_DIRT.get())) {
                if (!level.isClientSide) {
                    ServerLevel serverLevel = (ServerLevel) level;

                    serverLevel.sendParticles(ParticleTypes.SMOKE,
                            pos.getX() + 0.5, pos.getY() + 0.6, pos.getZ() + 0.5,
                            5, 0.25, 0.25, 0.25, 0.0);
                }
                return ItemInteractionResult.SUCCESS;
            }
        }
        return super.useItemOn(itemStack, blockState, level, pos, player, hand, blockHitResult);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        BlockPos groundPos = pos.below();
        BlockState groundState = level.getBlockState(groundPos);

        return groundState.is(BlockInit.ENRICHED_VIBRANIUM_DIRT.get());
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.advanceTree(level, pos, state, random);
    }
}
