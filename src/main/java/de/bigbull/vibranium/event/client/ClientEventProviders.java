package de.bigbull.vibranium.event.client;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.entity.client.VibraGolemModel;
import de.bigbull.vibranium.init.ModelLayersInit;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Vibranium.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventProviders {

    @SubscribeEvent
    public static void registerEnityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayersInit.VIBRAGOLEM_LAYER, VibraGolemModel::createBodyLayer);
    }
}