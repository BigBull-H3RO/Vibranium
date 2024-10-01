package de.bigbull.vibranium.event;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.config.VibraniumConfigValues;
import de.bigbull.vibranium.init.custom.item.VibraniumMaceItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Vibranium.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();
        Level level = event.getPlayer().level();
        BlockState middleBlockState = event.getLevel().getBlockState(event.getPos());

        if (mainHandItem.getItem() instanceof VibraniumMaceItem Item && player instanceof ServerPlayer serverPlayer) {
            if (!player.isShiftKeyDown()) {
                BlockPos initialBlockPos = event.getPos();
                TagKey<Block> requiredTool = getRequiredToolForBlock(middleBlockState);

                if (requiredTool != null && isValidBlockForTool(middleBlockState, requiredTool)) {
                    boolean middleBlockNeedsAdvancedTool = middleBlockState.is(BlockTags.NEEDS_DIAMOND_TOOL) || middleBlockState.is(Tags.Blocks.NEEDS_NETHERITE_TOOL);

                    if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
                        return;
                    }

                    List<BlockPos> affectedPositions = VibraniumMaceItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer);
                    affectedPositions.remove(initialBlockPos);

                    for (BlockPos pos : affectedPositions) {
                        BlockState targetBlockState = level.getBlockState(pos);

                        if ((middleBlockNeedsAdvancedTool && isValidBlockForTool(targetBlockState, requiredTool)) ||
                                (!middleBlockNeedsAdvancedTool && !targetBlockState.is(BlockTags.NEEDS_DIAMOND_TOOL) && !targetBlockState.is(Tags.Blocks.NEEDS_NETHERITE_TOOL) && isValidBlockForTool(targetBlockState, requiredTool))) {
                            HARVESTED_BLOCKS.add(pos);

                            if (VibraniumConfigValues.USE_FAST_MODE) {
                                serverPlayer.gameMode.destroyBlock(pos);
                            } else {
                                level.destroyBlock(pos, false);
                                Block.getDrops(targetBlockState, (ServerLevel) level, pos, null, player, mainHandItem)
                                        .forEach(drop -> Block.popResource(level, pos, drop));
                                mainHandItem.hurtAndBreak(1, serverPlayer, EquipmentSlot.MAINHAND);
                            }
                            HARVESTED_BLOCKS.remove(pos);
                        }
                    }
                }
            }
        }
    }

    private static TagKey<Block> getRequiredToolForBlock(BlockState state) {
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
            return BlockTags.MINEABLE_WITH_PICKAXE;
        }
        if (state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
            return BlockTags.MINEABLE_WITH_SHOVEL;
        }
        if (state.is(BlockTags.MINEABLE_WITH_AXE)) {
            return BlockTags.MINEABLE_WITH_AXE;
        }
        return null;
    }

    public static boolean isValidBlockForTool(BlockState state, TagKey<Block> requiredTool) {
        return state.isSolidRender(null, BlockPos.ZERO) &&
                state.is(requiredTool) &&
                state.getBlock() != Blocks.AIR;
    }
}