package de.bigbull.vibranium.data.worldgen;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.data.worldgen.ore.ModOrePlacement;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static ResourceKey<PlacedFeature> VIBRANIUM_ORE = createKey("vibranium_ore");
    public static ResourceKey<PlacedFeature> VIBRANIUM_GEODE = createKey("vibranium_geode");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> vibraniumOreFeature = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_VIBRANIUM_ORE);
        Holder<ConfiguredFeature<?, ?>> vibraniumGeodeFeature = configuredFeatures.getOrThrow(ModConfiguredFeatures.VIBRANIUM_GEODE);

        PlacementUtils.register(context, VIBRANIUM_ORE, vibraniumOreFeature,
                ModOrePlacement.commonOrePlacements(3, HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-10))
                )
        );

        PlacementUtils.register(context, VIBRANIUM_GEODE, vibraniumGeodeFeature,
                List.of(RarityFilter.onAverageOnceEvery(50),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-45), VerticalAnchor.absolute(15)),
                        BiomeFilter.biome())
        );
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Vibranium.MODID, name));
    }
}
