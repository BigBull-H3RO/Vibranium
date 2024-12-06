package de.bigbull.vibranium.init.custom.test;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Vibranium.MODID);

    public static final Supplier<BlockEntityType<EVDirtBlockEntity>> ENRICHED_VIBRANIUM_DIRT_ENTITY_TYPES = BLOCK_ENTITY_TYPES.register(
            "enriched_vibranium_dirt_entity",
            () -> new BlockEntityType<>(
                    EVDirtBlockEntity::new,
                    BlockInit.ENRICHED_VIBRANIUM_DIRT.get()
            )
    );
}
