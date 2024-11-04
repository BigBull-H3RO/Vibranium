package de.bigbull.vibranium.data.worldgen;

import de.bigbull.vibranium.config.ConfigValues;
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

import java.util.List;

public class ModPlacedFeatures {
    public static ResourceKey<PlacedFeature> VIBRANIUM_ORE = createKey("vibranium_ore");
    public static ResourceKey<PlacedFeature> VIBRANIUM_STRUCTURE = createKey("vibranium_structure");

    private static final int VEINS_PER_CHUNK = ConfigValues.VEINS_PER_CHUNK;
    private static final int MAX_HEIGHT = ConfigValues.MAX_HEIGHT;
    private static final int MIN_HEIGHT = ConfigValues.MIN_HEIGHT;

    private static final int GEODES_RARITY = ConfigValues.GEODES_RARITY;
    private static final int GEODES_MAX_HEIGHT = ConfigValues.GEODES_MAX_HEIGHT;
    private static final int GEODES_MIN_HEIGHT = ConfigValues.GEODES_MIN_HEIGHT;

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> vibraniumOreFeature = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_VIBRANIUM_ORE);
        Holder<ConfiguredFeature<?, ?>> vibraniumStructureFeature = configuredFeatures.getOrThrow(ModConfiguredFeatures.VIBRANIUM_STRUCTURE);

        register(context, VIBRANIUM_ORE, vibraniumOreFeature,
                ModOrePlacement.commonOrePlacements(VEINS_PER_CHUNK, HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(MIN_HEIGHT), VerticalAnchor.absolute(MAX_HEIGHT))
                )
        );
        register(context, VIBRANIUM_STRUCTURE, vibraniumStructureFeature,
                List.of(RarityFilter.onAverageOnceEvery(GEODES_RARITY),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(GEODES_MIN_HEIGHT), VerticalAnchor.absolute(GEODES_MAX_HEIGHT))
                ));
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
        context.register(key, new PlacedFeature(feature, placementModifiers));
    }
}
