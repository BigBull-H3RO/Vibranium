package de.bigbull.vibranium.event.client;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.entity.MobEnities;
import de.bigbull.vibranium.entity.client.VibraGolemRenderer;
import de.bigbull.vibranium.init.*;
import de.bigbull.vibranium.init.custom.particle.CustomDripParticle;
import de.bigbull.vibranium.init.custom.particle.CustomLeavesParticle;
import de.bigbull.vibranium.init.custom.renderer.SoulwoodBoatRenderer;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

@EventBusSubscriber(modid = Vibranium.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onclientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(TypesInit.SOULWOOD_WOODTYPE);
        });

        BlockEntityRenderers.register(EntitiesInit.SOULWOOD_SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(EntitiesInit.SOULWOOD_HANGING_SIGN.get(), HangingSignRenderer::new);
        EntityRenderers.register(EntitiesInit.SOULWOOD_BOAT.get(), (context) -> new SoulwoodBoatRenderer(context, false));
        EntityRenderers.register(EntitiesInit.SOULWOOD_CHEST_BOAT.get(), (context) -> new SoulwoodBoatRenderer(context, true));
        EntityRenderers.register(MobEnities.VIBRAGOLEM.get(), VibraGolemRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSprite(ParticleInit.DRIPPING_VIBRANIUM.get(), CustomDripParticle::createVibraniumHangParticle);
        event.registerSprite(ParticleInit.FALLING_VIBRANIUM.get(), CustomDripParticle::createVibraniumFallParticle);
        event.registerSprite(ParticleInit.LANDING_VIBRANIUM.get(), CustomDripParticle::createVibraniumLandParticle);
        event.registerSpriteSet(ParticleInit.SOULWOOD_LEAVES.get(), CustomLeavesParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayersInit.SOULWOOD_BOAT, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModelLayersInit.SOULWOOD_CHEST_BOAT, ChestBoatModel::createBodyModel);
    }
}