package de.bigbull.vibranium.data.worldgen.ore;

import de.bigbull.vibranium.config.VibraniumConfigValues;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static ResourceKey<PlacedFeature> VIBRANIUM_ORE = createKey("vibranium_ore");
    public static ResourceKey<PlacedFeature> VIBRANIUM_STRUCTURE = createKey("vibranium_structure");
    public static ResourceKey<PlacedFeature> VIBRANIUM_STRUCTURE2 = createKey("vibranium_structure2");

    private static final int VEINS_PER_CHUNK = VibraniumConfigValues.VEINS_PER_CHUNK;
    private static final int MAX_HEIGHT = VibraniumConfigValues.MAX_HEIGHT;
    private static final int MIN_HEIGHT = VibraniumConfigValues.MIN_HEIGHT;

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> vibraniumOreFeature = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_VIBRANIUM_ORE);
        Holder<ConfiguredFeature<?, ?>> vibraniumStructureFeature = configuredFeatures.getOrThrow(ModConfiguredFeatures.VIBRANIUM_STRUCTURE);
        Holder<ConfiguredFeature<?, ?>> vibraniumStructure2Feature = configuredFeatures.getOrThrow(ModConfiguredFeatures.VIBRANIUM_STRUCTURE2);

        register(context, VIBRANIUM_ORE, vibraniumOreFeature,
                ModOrePlacement.commonOrePlacements(VEINS_PER_CHUNK, HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(MIN_HEIGHT), VerticalAnchor.absolute(MAX_HEIGHT))
                )
        );
        register(context, VIBRANIUM_STRUCTURE, vibraniumStructureFeature,
                List.of(RarityFilter.onAverageOnceEvery(80), // Rarity kann angepasst werden
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(20), VerticalAnchor.absolute(50)) // Tiefe unter der Erde
                ));
        register(context, VIBRANIUM_STRUCTURE2, vibraniumStructure2Feature,
                List.of(RarityFilter.onAverageOnceEvery(50),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(30)) // Tiefe unter der Erde
                ));
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
        context.register(key, new PlacedFeature(feature, placementModifiers));
    }
}
