package de.bigbull.vibranium.event.client;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.entity.MobEnities;
import de.bigbull.vibranium.entity.client.VibraGolemRenderer;
import de.bigbull.vibranium.init.ParticleInit;
import de.bigbull.vibranium.init.custom.particle.CustomDripParticle;
import de.bigbull.vibranium.init.custom.particle.CustomLeavesParticle;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = Vibranium.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onclientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(MobEnities.VIBRAGOLEM.get(), VibraGolemRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSprite(ParticleInit.DRIPPING_VIBRANIUM.get(), CustomDripParticle::createVibraniumHangParticle);
        event.registerSprite(ParticleInit.FALLING_VIBRANIUM.get(), CustomDripParticle::createVibraniumFallParticle);
        event.registerSprite(ParticleInit.LANDING_VIBRANIUM.get(), CustomDripParticle::createVibraniumLandParticle);
        event.registerSpriteSet(ParticleInit.SOULWOOD_LEAVES.get(), CustomLeavesParticle.Provider::new);
    }
}