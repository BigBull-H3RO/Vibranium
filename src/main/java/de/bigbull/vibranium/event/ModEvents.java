package de.bigbull.vibranium.event;

import com.mojang.blaze3d.platform.InputConstants;
import de.bigbull.vibranium.entity.client.ModModelLayers;
import de.bigbull.vibranium.entity.client.VibraGolemModel;
import de.bigbull.vibranium.entity.client.VibraGolemRenderer;
import de.bigbull.vibranium.entity.VibraGolemEntity;
import de.bigbull.vibranium.init.*;
import de.bigbull.vibranium.init.custom.particle.CustomDripParticle;
import de.bigbull.vibranium.init.custom.particle.CustomLeavesParticle;
import de.bigbull.vibranium.init.custom.renderer.SoulwoodBoatRenderer;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.lwjgl.glfw.GLFW;

public class ModEvents {
    public static KeyMapping toggleOutlineKey;

    public static final Component TOGGLE_OUTLINE = Component.translatable("key.vibranium.toggle_outline");
    public static final Component KEY_CATEGORIES = Component.translatable("key.categories.vibranium");

    public static void onclientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(TypesInit.SOULWOOD_WOODTYPE);
        });

        BlockEntityRenderers.register(EntitiesInit.SOULWOOD_SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(EntitiesInit.SOULWOOD_HANGING_SIGN.get(), HangingSignRenderer::new);
        EntityRenderers.register(EntitiesInit.SOULWOOD_BOAT.get(), (context) -> new SoulwoodBoatRenderer(context, false));
        EntityRenderers.register(EntitiesInit.SOULWOOD_CHEST_BOAT.get(), (context) -> new SoulwoodBoatRenderer(context, true));
        EntityRenderers.register(EntitiesInit.VIBRA_GOLEM.get(), VibraGolemRenderer::new);
    }

    public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
        event.registerSprite(ParticleInit.DRIPPING_VIBRANIUM.get(), CustomDripParticle::createVibraniumHangParticle);
        event.registerSprite(ParticleInit.FALLING_VIBRANIUM.get(), CustomDripParticle::createVibraniumFallParticle);
        event.registerSprite(ParticleInit.LANDING_VIBRANIUM.get(), CustomDripParticle::createVibraniumLandParticle);
        event.registerSpriteSet(ParticleInit.SOULWOOD_LEAVES.get(), CustomLeavesParticle.Provider::new);
    }

    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayersInit.SOULWOOD_BOAT, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModelLayersInit.SOULWOOD_CHEST_BOAT, ChestBoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.VIBRAGOLEM_LAYER, VibraGolemModel::createBodyLayer);
    }

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntitiesInit.VIBRA_GOLEM.get(), VibraGolemEntity.setAttributes().build());
    }

    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        toggleOutlineKey = new KeyMapping(
                "key.vibranium.toggle_outline",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                "key.categories.vibranium"
        );

        event.register(toggleOutlineKey);
    }
}