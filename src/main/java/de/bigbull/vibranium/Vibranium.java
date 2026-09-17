package de.bigbull.vibranium;

import com.mojang.logging.LogUtils;
import de.bigbull.vibranium.config.ClientConfig;
import de.bigbull.vibranium.config.ServerConfig;
import de.bigbull.vibranium.data.DataGenerators;
import de.bigbull.vibranium.data.loot.ModLootModifiers;
import de.bigbull.vibranium.event.ModCommonEvents;
import de.bigbull.vibranium.init.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(Vibranium.MODID)
public class Vibranium {
    public static final String MODID = "vibranium";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Vibranium(IEventBus modEventBus, ModContainer modContainer) {
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        CreativeTabInit.CREATIVE_MODE_TABS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        EffectInit.EFFECTS.register(modEventBus);
        FeatureInit.FEATURES.register(modEventBus);
        FeatureInit.TRUNK_PLACER_TYPES.register(modEventBus);
        ParticleInit.PARTICLES.register(modEventBus);
        EntitiesInit.ENTITY_TYPES.register(modEventBus);

        modEventBus.addListener(DataGenerators::gatherData);
        modEventBus.addListener(ModCommonEvents::addBlockEntityTypes);
        modEventBus.addListener(ModCommonEvents::registerEntityAttributes);

        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.CLIENT_SPEC, "vibranium-client.toml");
        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfig.SERVER_SPEC, "vibranium-server.toml");
    }
}
