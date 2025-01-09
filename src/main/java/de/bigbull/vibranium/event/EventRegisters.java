package de.bigbull.vibranium.event;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.entity.custom.VibraGolemEntity;
import de.bigbull.vibranium.init.Entitiesinit;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Vibranium.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EventRegisters {
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(Entitiesinit.VIBRAGOLEM.get(), VibraGolemEntity.setAttributes().build());
    }
}