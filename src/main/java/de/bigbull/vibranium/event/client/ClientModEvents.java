package de.bigbull.vibranium.event.client;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ModelLayersInit;
import de.bigbull.vibranium.init.ParticleInit;
import de.bigbull.vibranium.init.TypesInit;
import de.bigbull.vibranium.init.custom.item.shield.ShieldRenderer;
import de.bigbull.vibranium.init.custom.particle.CustomDripParticle;
import de.bigbull.vibranium.init.custom.particle.CustomLeavesParticle;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

public class ClientModEvents {
//    @SubscribeEvent
//    public static void onclientSetup(FMLClientSetupEvent event) {
//        EntityRenderers.register(MobEnities.VIBRAGOLEM.get(), VibraGolemRenderer::new);
//    }

    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSprite(ParticleInit.DRIPPING_VIBRANIUM.get(), CustomDripParticle::createVibraniumHangParticle);
        event.registerSprite(ParticleInit.FALLING_VIBRANIUM.get(), CustomDripParticle::createVibraniumFallParticle);
        event.registerSprite(ParticleInit.LANDING_VIBRANIUM.get(), CustomDripParticle::createVibraniumLandParticle);
        event.registerSpriteSet(ParticleInit.SOULWOOD_LEAVES.get(), CustomLeavesParticle.Provider::new);
    }

    public static void onRegisterSpecialModelRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "vibranium_shield"), ShieldRenderer.Unbaked.MAP_CODEC);
    }

    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(TypesInit.SOULWOOD_WOODTYPE);
        });
    }

    public static void addBlockEntityTypes(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SIGN,
                BlockInit.SOULWOOD_SIGN.get(), BlockInit.SOULWOOD_WALL_SIGN.get());

        event.modify(BlockEntityType.HANGING_SIGN,
                BlockInit.SOULWOOD_HANGING_SIGN.get(), BlockInit.SOULWOOD_WALL_HANGING_SIGN.get());
    }

    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TypesInit.SOULWOOD_BOAT.get(),
                context -> new BoatRenderer(context, ModelLayersInit.SOULWOOD_BOAT));
        event.registerEntityRenderer(TypesInit.SOULWOOD_CHEST_BOAT.get(),
                context -> new BoatRenderer(context, ModelLayersInit.SOULWOOD_CHEST_BOAT));
    }

    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayersInit.SOULWOOD_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(ModelLayersInit.SOULWOOD_CHEST_BOAT, BoatModel::createChestBoatModel);
    }
}