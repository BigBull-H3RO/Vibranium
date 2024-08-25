package de.bigbull.vibranium.main;

import com.mojang.logging.LogUtils;
import de.bigbull.vibranium.config.VibraniumConfig;
import de.bigbull.vibranium.data.DataGenerators;
import de.bigbull.vibranium.data.loot.ModLootModifiers;
import de.bigbull.vibranium.entity.MobEnities;
import de.bigbull.vibranium.event.ClientEventProviders;
import de.bigbull.vibranium.event.VibraGolemEvent;
import de.bigbull.vibranium.event.EventRegisters;
import de.bigbull.vibranium.init.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
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

        ArmorMaterialsInit.MATERIAL.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        MobEnities.ENTITIES.register(modEventBus);
        CreativeTabInit.CREATIVE_MODE_TABS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(DataGenerators::gatherData);
        modEventBus.addListener(EventRegisters::registerEntityAttributes);
        modEventBus.addListener(EventRegisters::registerSpawnPlacements);
        modEventBus.addListener(ClientEventProviders::registerEnityRenderers);

        modContainer.registerConfig(ModConfig.Type.COMMON, VibraniumConfig.SPEC, "vibranium.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}