package de.bigbull.vibranium.event;

import de.bigbull.vibranium.entity.MobEnities;
import de.bigbull.vibranium.entity.client.VibraGolemRenderer;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Vibranium.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventProviders {

    @SubscribeEvent
    public static void registerEnityRenderers(FMLClientSetupEvent event) {
        EntityRenderers.register(MobEnities.VIBRAGOLEM.get(), VibraGolemRenderer::new);
    }
}