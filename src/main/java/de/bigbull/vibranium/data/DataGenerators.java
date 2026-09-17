package de.bigbull.vibranium.data;

import de.bigbull.vibranium.data.loot.ModGlobalLootModifiersProvider;
import de.bigbull.vibranium.data.loottable.ModLootTables;
import de.bigbull.vibranium.data.recipe.ModRecipeProvider;
import de.bigbull.vibranium.data.tag.ModBlockTagsProvider;
import de.bigbull.vibranium.data.tag.ModItemTagsProvider;
import de.bigbull.vibranium.data.texture.ModModelProvider;
import de.bigbull.vibranium.data.worldgen.ModBiomesModifiers;
import de.bigbull.vibranium.data.worldgen.ModConfiguredFeatures;
import de.bigbull.vibranium.data.worldgen.ModPlacedFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DataGenerators {
    public static void gatherData(GatherDataEvent.Client event) {
        // Models
        event.createProvider(ModModelProvider::new);

        // Tags
        event.createProvider(ModBlockTagsProvider::new);
        event.createProvider(ModItemTagsProvider::new);

        // Global Loot Modifiers
        event.createProvider(ModGlobalLootModifiersProvider::new);

        // Reloadable Registries (Loot Tables, Recipes)
        event.createReloadableRegistryObjects(
                new RegistrySetBuilder()
                        .add(Registries.LOOT_TABLE, new ModLootTables())
                        .add(RecipeProvider.asBootstrap(ModRecipeProvider::new))
        );

        // WorldGen Registries (Features, Placed Features, Biome Modifiers)
        event.createWorldRegistryObjects(
                new RegistrySetBuilder()
                        .add(Registries.FEATURE, ModConfiguredFeatures::bootstrap)
                        .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomesModifiers::bootstrap)
        );
    }
}
