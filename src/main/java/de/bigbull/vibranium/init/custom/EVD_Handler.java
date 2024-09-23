package de.bigbull.vibranium.init.custom;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
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
        BlockState blockState = event.getLevel().getBlockState(pos);
        ItemStack heldItem = player.getItemInHand(hand);

        // Überprüfen, ob der Spieler Raw Vibranium hält und der Block Dirt ist
        if (heldItem.getItem() == ItemInit.RAW_VIBRANIUM.get() && blockState.is(Blocks.DIRT)) {
            // Ändere den Block zu Enriched Vibranium Dirt
            event.getLevel().setBlock(pos, BlockInit.ENRICHED_VIBRANIUM_DIRT.get().defaultBlockState(), 3);
            // Verbrauch das Item
            if (!player.isCreative()) {
                heldItem.shrink(1);
            }
            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
        }
    }
}
