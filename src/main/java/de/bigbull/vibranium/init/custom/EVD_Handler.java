package de.bigbull.vibranium.init.custom;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Vibranium.MODID)
public class EVD_Handler {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        BlockState blockState = level.getBlockState(pos);
        ItemStack heldItem = player.getItemInHand(hand);

        if (heldItem.getItem() == ItemInit.RAW_VIBRANIUM.get()) {
            boolean transformed = false;
            if (blockState.is(Blocks.DIRT)) {
                level.setBlock(pos, BlockInit.ENRICHED_VIBRANIUM_DIRT.get().defaultBlockState(), 3);
                transformed = true;
            } else if (blockState.is(Blocks.FARMLAND)) {
                int moisture = blockState.getValue(FarmBlock.MOISTURE);
                BlockState enrichedFarmlandState = BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get()
                        .defaultBlockState()
                        .setValue(FarmBlock.MOISTURE, moisture);

                level.setBlock(pos, enrichedFarmlandState, 3);
                transformed = true;
            }

            if (transformed) {
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
                level.playSound(null, pos, SoundEvents.MUD_HIT, SoundSource.BLOCKS, 1.0F, 1.0F);

                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }
}