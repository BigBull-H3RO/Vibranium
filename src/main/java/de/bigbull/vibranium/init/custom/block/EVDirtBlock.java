package de.bigbull.vibranium.init.custom.block;

import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.custom.test.EVDirtBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import javax.annotation.Nullable;

public class EVDirtBlock extends Block implements EntityBlock {
    public EVDirtBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility ability, boolean simulate) {
        if (ability == ItemAbilities.HOE_TILL) {
            Level level = context.getLevel();
            BlockPos pos = context.getClickedPos();
            if (context.getClickedFace() != Direction.DOWN && level.getBlockState(pos.above()).isAir()) {
                return BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get().defaultBlockState();
            }
        }
        return null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EVDirtBlockEntity(pos, state);
    }
}
