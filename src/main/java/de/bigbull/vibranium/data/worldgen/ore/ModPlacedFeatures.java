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

    private static final int VEINS_PER_CHUNK = VibraniumConfigValues.VEINS_PER_CHUNK;
    private static final int MAX_HEIGHT = VibraniumConfigValues.MAX_HEIGHT;
    private static final int MIN_HEIGHT = VibraniumConfigValues.MIN_HEIGHT;

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> holder = configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_VIBRANIUM_ORE);

        register(context, VIBRANIUM_ORE, holder,
                ModOrePlacement.commonOrePlacements(VEINS_PER_CHUNK, HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(MIN_HEIGHT), VerticalAnchor.absolute(MAX_HEIGHT))
                )
        );
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
        context.register(key, new PlacedFeature(feature, placementModifiers));
    }
}
