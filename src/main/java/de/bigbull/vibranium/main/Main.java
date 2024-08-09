package de.bigbull.vibranium.main;

import com.mojang.logging.LogUtils;
import de.bigbull.vibranium.data.DataGenerators;
import de.bigbull.vibranium.data.loot.ModLootModifiers;
import de.bigbull.vibranium.entity.MobEnities;
import de.bigbull.vibranium.event.ClientEventProviders;
import de.bigbull.vibranium.event.EventHandler;
import de.bigbull.vibranium.event.EventRegisters;
import de.bigbull.vibranium.init.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(ModInfo.MODID)
public class Main
{
    public static Logger logger = LogUtils.getLogger();

    public Main(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(EventHandler.class);

        ArmorMaterialsInit.MATERIAL.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        MobEnities.ENTITIES.register(modEventBus);
        CreativeTabInit.CREATIVE_MODE_TABS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(DataGenerators::gatherData);

        //modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);

        modEventBus.addListener(EventRegisters::registerEntityAttributes);
        modEventBus.addListener(EventRegisters::registerSpawnPlacements);
        modEventBus.addListener(ClientEventProviders::registerEnityRenderers);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
}