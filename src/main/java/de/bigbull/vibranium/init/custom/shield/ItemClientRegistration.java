package de.bigbull.vibranium.init.custom.shield;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = Vibranium.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ItemClientRegistration {

    @SubscribeEvent
    static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ShieldRenderer.instance;
            }
        },
                ItemInit.VIBRANIUM_SHIELD.get()
        );
    }
}