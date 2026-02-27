package de.bigbull.vibranium.event;

import de.bigbull.vibranium.entity.VibraGolemEntity;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.EntitiesInit;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class ModCommonEvents {
    public static void addBlockEntityTypes(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN,
                BlockInit.SOULWOOD_SIGN.get(), BlockInit.SOULWOOD_WALL_SIGN.get());

        event.modify(BlockEntityType.HANGING_SIGN,
                BlockInit.SOULWOOD_HANGING_SIGN.get(), BlockInit.SOULWOOD_WALL_HANGING_SIGN.get());
    }

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntitiesInit.VIBRAGOLEM.get(), VibraGolemEntity.setAttributes().build());
    }
}

