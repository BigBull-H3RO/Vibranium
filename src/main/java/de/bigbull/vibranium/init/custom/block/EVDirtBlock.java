package de.bigbull.vibranium.init.custom.block;

import de.bigbull.vibranium.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class EVDirtBlock extends Block {
    public EVDirtBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.is(ItemTags.HOES) && hitResult.getDirection() != Direction.DOWN && level.getBlockState(pos.above()).isAir()) {
            level.playSound(player, pos, SoundEvents.HOE_TILL.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!level.isClientSide()) {
                level.setBlock(pos, BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get().defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE);
                stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
            }
            return InteractionResult.SUCCESS;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
