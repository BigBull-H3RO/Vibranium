package de.bigbull.vibranium.event;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.entity.VibraGolemEntity;
import de.bigbull.vibranium.entity.client.VibraGolemModel;
import de.bigbull.vibranium.entity.client.VibraGolemRenderer;
import de.bigbull.vibranium.init.*;
import de.bigbull.vibranium.init.custom.ShieldRenderer;
import de.bigbull.vibranium.init.custom.particle.CustomDripParticle;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class ModEvents {
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(TypesInit.SOULWOOD_WOODTYPE);

            ItemBlockRenderTypes.setRenderLayer(BlockInit.SOULWOOD_DOOR.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(BlockInit.SOULWOOD_TRAPDOOR.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(BlockInit.SOULWOOD_SAPLING.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(BlockInit.VIBRANIUM_CLUSTER.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(BlockInit.SMALL_VIBRANIUM_BUD.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(BlockInit.MEDIUM_VIBRANIUM_BUD.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(BlockInit.LARGE_VIBRANIUM_BUD.get(), ChunkSectionLayer.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(BlockInit.POTTED_SOULWOOD_SAPLING.get(), ChunkSectionLayer.CUTOUT);

            EntityRenderers.register(Entitiesinit.VIBRAGOLEM.get(), VibraGolemRenderer::new);
        });
    }

    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleInit.DRIPPING_VIBRANIUM.get(), CustomDripParticle.VibraniumHangProvider::new);
        event.registerSpriteSet(ParticleInit.FALLING_VIBRANIUM.get(), CustomDripParticle.VibraniumFallProvider::new);
        event.registerSpriteSet(ParticleInit.LANDING_VIBRANIUM.get(), CustomDripParticle.VibraniumLandProvider::new);

        event.registerSpriteSet(ParticleInit.SOULWOOD_LEAVES.get(), FallingLeavesParticle.CherryProvider::new);
    }

    public static void onRegisterSpecialModelRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "vibranium_shield"), ShieldRenderer.Unbaked.MAP_CODEC);
    }

    public static void addBlockEntityTypes(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN,
                BlockInit.SOULWOOD_SIGN.get(), BlockInit.SOULWOOD_WALL_SIGN.get());

        event.modify(BlockEntityType.HANGING_SIGN,
                BlockInit.SOULWOOD_HANGING_SIGN.get(), BlockInit.SOULWOOD_WALL_HANGING_SIGN.get());
    }

    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Entitiesinit.SOULWOOD_BOAT.get(),
                context -> new BoatRenderer(context, ModelLayersInit.SOULWOOD_BOAT));
        event.registerEntityRenderer(Entitiesinit.SOULWOOD_CHEST_BOAT.get(),
                context -> new BoatRenderer(context, ModelLayersInit.SOULWOOD_CHEST_BOAT));
    }

    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayersInit.SOULWOOD_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(ModelLayersInit.SOULWOOD_CHEST_BOAT, BoatModel::createChestBoatModel);
        event.registerLayerDefinition(ModelLayersInit.VIBRAGOLEM_LAYER, VibraGolemModel::createBodyLayer);
    }

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(Entitiesinit.VIBRAGOLEM.get(), VibraGolemEntity.setAttributes().build());
    }

    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.registerCategory(ModKeybinds.VIBRANIUM_CATEGORY);
        event.register(ModKeybinds.TOGGLE_OUTLINE);
    }
}