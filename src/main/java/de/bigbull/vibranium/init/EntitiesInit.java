package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.custom.entity.SWHangingSignBlockEntity;
import de.bigbull.vibranium.init.custom.entity.SWSignBlockEntity;
import de.bigbull.vibranium.init.custom.entity.SWBoatEntity;
import de.bigbull.vibranium.init.custom.entity.SWChestBoatEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


import java.util.function.Supplier;

public class EntitiesInit {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Vibranium.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Vibranium.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<SWBoatEntity>> SOULWOOD_BOAT = ENTITY_TYPES.register("boat", () -> EntityType.Builder.<SWBoatEntity>of(SWBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build(ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "boat").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<SWChestBoatEntity>> SOULWOOD_CHEST_BOAT = ENTITY_TYPES.register("chest_boat", () -> EntityType.Builder.<SWChestBoatEntity>of(SWChestBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build(ResourceLocation.fromNamespaceAndPath(Vibranium.MODID,"chest_boat").toString()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SWSignBlockEntity>> SOULWOOD_SIGN = BLOCK_ENTITIES.register("soulwood_sign", () -> BlockEntityType.Builder.of(SWSignBlockEntity::new, BlockInit.SOULWOOD_SIGN.get(), BlockInit.SOULWOOD_WALL_SIGN.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SWHangingSignBlockEntity>> SOULWOOD_HANGING_SIGN = BLOCK_ENTITIES.register("soulwood_hanging_sign", () -> BlockEntityType.Builder.of(SWHangingSignBlockEntity::new, BlockInit.SOULWOOD_HANGING_SIGN.get(), BlockInit.SOULWOOD_WALL_HANGING_SIGN.get()).build(null));
}
