package de.bigbull.vibranium.init.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends DiggerItem {
    public HammerItem(Tier tier, TagKey<Block> tag, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            if (!player.isShiftKeyDown() || player.isCreative()) {
                if (isValidBlockForTool(state)) {
                    List<BlockPos> affectedPositions = getAffectedPositions(player);
                    for (BlockPos targetPos : affectedPositions) {
                        BlockState targetState = level.getBlockState(targetPos);
                        if (isValidBlockForTool(targetState)) {
                            level.destroyBlock(targetPos, true, entity);
                        }
                    }
                    return true;
                }
            }
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }

    public List<BlockPos> getAffectedPositions(Player player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hitResult = getPlayerPOVHitResult(player.level(), player, ClipContext.Fluid.NONE);

        if (hitResult instanceof BlockHitResult) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockPos center = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getDirection();
            positions.add(center);

            switch (direction) {
                case UP, DOWN -> {
                    positions.add(center.north());
                    positions.add(center.south());
                    positions.add(center.east());
                    positions.add(center.west());
                    positions.add(center.north().east());
                    positions.add(center.north().west());
                    positions.add(center.south().east());
                    positions.add(center.south().west());
                }
                case NORTH, SOUTH -> {
                    positions.add(center.above());
                    positions.add(center.below());
                    positions.add(center.east());
                    positions.add(center.west());
                    positions.add(center.east().above());
                    positions.add(center.west().above());
                    positions.add(center.east().below());
                    positions.add(center.west().below());
                }
                case EAST, WEST -> {
                    positions.add(center.above());
                    positions.add(center.below());
                    positions.add(center.north());
                    positions.add(center.south());
                    positions.add(center.north().above());
                    positions.add(center.south().above());
                    positions.add(center.north().below());
                    positions.add(center.south().below());
                }
            }
        }
        return positions;
    }

    public boolean isValidBlockForTool(BlockState state) {
        return state.isSolidRender(null, BlockPos.ZERO) &&
                (state.is(BlockTags.MINEABLE_WITH_PICKAXE) || state.is(BlockTags.MINEABLE_WITH_SHOVEL)) &&
                state.getBlock() != Blocks.AIR;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("item.vibranium_mace.tooltip").withStyle(ChatFormatting.GRAY));
    }
}