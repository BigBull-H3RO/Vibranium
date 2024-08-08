package de.bigbull.vibranium.event;

import de.bigbull.vibranium.entity.MobEnities;
import de.bigbull.vibranium.entity.custom.VibraGolemEntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

public class EventRegisters {
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(MobEnities.VIBRAGOLEM.value(), VibraGolemEntity.setAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(MobEnities.VIBRAGOLEM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, level, spawnType, pos, random) -> {
            return pos.getY() >= -64 && pos.getY() <= -15 && TamableAnimal.checkAnimalSpawnRules(entityType, level, spawnType, pos, random);
        }, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
