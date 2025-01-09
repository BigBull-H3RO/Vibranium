package de.bigbull.vibranium;

import com.mojang.logging.LogUtils;
import de.bigbull.vibranium.config.ClientConfig;
import de.bigbull.vibranium.config.ServerConfig;
import de.bigbull.vibranium.data.DataGenerators;
import de.bigbull.vibranium.data.loot.ModLootModifiers;
import de.bigbull.vibranium.event.VibraGolemEvent;
import de.bigbull.vibranium.event.client.ClientModEvents;
import de.bigbull.vibranium.init.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(Vibranium.MODID)
public class Vibranium {
    public static final String MODID = "vibranium";
    public static final Logger logger = LogUtils.getLogger();

    public Vibranium(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(VibraGolemEvent.class);

        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        CreativeTabInit.CREATIVE_MODE_TABS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        EffectInit.EFFECTS.register(modEventBus);
        FeatureInit.FEATURES.register(modEventBus);
        FeatureInit.TRUNK_PLACER_TYPES.register(modEventBus);
        ParticleInit.PARTICLES.register(modEventBus);
        Entitiesinit.ENTITY_TYPES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(DataGenerators::gatherDataClient);

        if (FMLLoader.getDist() == Dist.CLIENT) {
            modEventBus.addListener(ClientModEvents::onRegisterParticles);
            modEventBus.addListener(ClientModEvents::onRegisterSpecialModelRenderers);
            modEventBus.addListener(ClientModEvents::clientSetup);
            modEventBus.addListener(ClientModEvents::addBlockEntityTypes);
            modEventBus.addListener(ClientModEvents::onRegisterRenderers);
            modEventBus.addListener(ClientModEvents::onRegisterLayerDefinitions);
        }

        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.CLIENT_SPEC, "vibranium-client.toml");
        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfig.SERVER_SPEC, "vibranium-server.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockInit.SOULWOOD_SAPLING.getId(), BlockInit.POTTED_SOULWOOD_SAPLING);
        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}