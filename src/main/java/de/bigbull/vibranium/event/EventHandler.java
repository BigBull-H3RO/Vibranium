package de.bigbull.vibranium.event;

import de.bigbull.vibranium.entity.custom.VibraGolemEntity;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

public class EventHandler {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState blockState = event.getState();
        Player player = event.getPlayer();

        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }
        if (blockState.is(BlockInit.DEPPSLATE_VIBRANIUM_ORE.get())) {
            List<VibraGolemEntity> golems = level.getEntitiesOfClass(VibraGolemEntity.class, player.getBoundingBox().inflate(20));
            for (VibraGolemEntity vibraGolem : golems) {
                if (!vibraGolem.isTame()) {
                    vibraGolem.setTarget(player);
                    vibraGolem.setAggressive(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();

        if (!(player.level() instanceof ServerLevel level)) {
            return;
        }
        boolean hasRawVibranium = false;
        for (ItemStack itemStack : player.getInventory().items) {
            if (itemStack.getItem() == ItemInit.RAW_VIBRANIUM.get()) {
                hasRawVibranium = true;
                break;
            }
        }
        if (hasRawVibranium) {
            List<VibraGolemEntity> golems = level.getEntitiesOfClass(VibraGolemEntity.class, player.getBoundingBox().inflate(20));
            for (VibraGolemEntity vibraGolem : golems) {
                if (!vibraGolem.isTame()) {
                    vibraGolem.setTarget(player);
                    vibraGolem.setAggressive(true);
                }
            }
        }
    }
}
