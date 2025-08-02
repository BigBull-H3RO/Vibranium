package de.bigbull.vibranium.data.worldgen;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.config.ServerConfig;
import de.bigbull.vibranium.data.worldgen.ore.ModOrePlacement;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.function.Supplier;

public class ModPlacedFeatures {
    public static ResourceKey<PlacedFeature> VIBRANIUM_ORE = createKey("vibranium_ore");
    public static ResourceKey<PlacedFeature> VIBRANIUM_GEODE = createKey("vibranium_geode");

    private static final Supplier<Integer> VEINS_PER_CHUNK = () -> getConfigValue(ServerConfig.VEINS_PER_CHUNK, 3);
    private static final Supplier<Integer> MAX_HEIGHT = () -> getConfigValue(ServerConfig.MAX_HEIGHT, -10);
    private static final Supplier<Integer> MIN_HEIGHT = () -> getConfigValue(ServerConfig.MIN_HEIGHT, -64);

    private static final Supplier<Integer> GEODES_RARITY = () -> getConfigValue(ServerConfig.GEODES_RARITY, 48);
    private static final Supplier<Integer> GEODES_MAX_HEIGHT = () -> getConfigValue(ServerConfig.GEODES_MAX_HEIGHT, 30);
    private static final Supplier<Integer> GEODES_MIN_HEIGHT = () -> getConfigValue(ServerConfig.GEODES_MIN_HEIGHT, 6);

    private static <T> T getConfigValue(ModConfigSpec.ConfigValue<T> configValue, T defaultValue) {
        try {
            return configValue.get();
        } catch (IllegalStateException e) {
            return defaultValue;
        }
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> vibraniumOreFeature = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_VIBRANIUM_ORE);
        Holder<ConfiguredFeature<?, ?>> vibraniumGeodeFeature = configuredFeatures.getOrThrow(ModConfiguredFeatures.VIBRANIUM_GEODE);

        register(context, VIBRANIUM_ORE, vibraniumOreFeature,
                ModOrePlacement.commonOrePlacements(VEINS_PER_CHUNK.get(), HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(MIN_HEIGHT.get()), VerticalAnchor.absolute(MAX_HEIGHT.get()))
                )
        );

        register(context, VIBRANIUM_GEODE, vibraniumGeodeFeature,
                List.of(RarityFilter.onAverageOnceEvery(GEODES_RARITY.get()),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(GEODES_MIN_HEIGHT.get()), VerticalAnchor.absolute(GEODES_MAX_HEIGHT.get())),
                        BiomeFilter.biome())
        );
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
        context.register(key, new PlacedFeature(feature, placementModifiers));
    }
}