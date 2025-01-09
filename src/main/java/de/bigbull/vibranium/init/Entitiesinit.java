package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.entity.custom.VibraGolemEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Locale;

public class Entitiesinit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Vibranium.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<Boat>> SOULWOOD_BOAT = register(
            "soulwood_boat",
            EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, ItemInit.SOULWOOD_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10)
    );
    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> SOULWOOD_CHEST_BOAT = register(
            "soulwood_chest_boat",
            EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, ItemInit.SOULWOOD_CHEST_BOAT), MobCategory.MISC)
                    .noLootTable()
                    .sized(1.375F, 0.5625F)
                    .eyeHeight(0.5625F)
                    .clientTrackingRange(10)
    );
    public static final DeferredHolder<EntityType<?>, EntityType<VibraGolemEntity>> VIBRAGOLEM = register(
            "vibra_golem",
            EntityType.Builder.of(VibraGolemEntity::new, MobCategory.CREATURE)
                    .sized(1.5F, 3.8F)
                    .clientTrackingRange(10)
    );

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> register(String name, EntityType.Builder<E> builder) {
        return ENTITY_TYPES.register(name, () -> builder.build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name.toLowerCase(Locale.ROOT)))));
    }
}
