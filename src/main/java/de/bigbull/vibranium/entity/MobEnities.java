package de.bigbull.vibranium.entity;

import de.bigbull.vibranium.entity.custom.VibraGolemEntity;
import net.minecraft.core.registries.Registries;
import de.bigbull.vibranium.main.ModInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MobEnities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, ModInfo.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<VibraGolemEntity>> VIBRAGOLEM;

    static {
        VIBRAGOLEM = ENTITIES.register("vibra_golem", () -> EntityType.Builder.of(VibraGolemEntity::new, MobCategory.CREATURE).sized(1.5F, 3.8F).clientTrackingRange(10).build(ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "vibra_golem").toString()));
    }
}