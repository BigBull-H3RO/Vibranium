package de.bigbull.vibranium.data.worldgen;

import de.bigbull.vibranium.Vibranium;
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

public class ModPlacedFeatures {
    public static ResourceKey<PlacedFeature> VIBRANIUM_ORE = createKey("vibranium_ore");
    public static ResourceKey<PlacedFeature> VIBRANIUM_GEODE = createKey("vibranium_geode");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> vibraniumOreFeature = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_VIBRANIUM_ORE);
        Holder<ConfiguredFeature<?, ?>> vibraniumGeodeFeature = configuredFeatures.getOrThrow(ModConfiguredFeatures.VIBRANIUM_GEODE);

        register(context, VIBRANIUM_ORE, vibraniumOreFeature,
                ModOrePlacement.commonOrePlacements(3, HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-10))
                )
        );

        register(context, VIBRANIUM_GEODE, vibraniumGeodeFeature,
                List.of(RarityFilter.onAverageOnceEvery(120),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-45), VerticalAnchor.absolute(15)),
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