package de.bigbull.vibranium.data;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.data.lang.ModDeLangProvider;
import de.bigbull.vibranium.data.lang.ModEnLangProvider;
import de.bigbull.vibranium.data.loot.ModGlobalLootModifiersProvider;
import de.bigbull.vibranium.data.loottable.ModLootTables;
import de.bigbull.vibranium.data.recipe.ModRecipeProvider;
import de.bigbull.vibranium.data.tag.ModBlockTagsProvider;
import de.bigbull.vibranium.data.tag.ModItemTagsProvider;
import de.bigbull.vibranium.data.texture.ModModelProvider;
import de.bigbull.vibranium.data.worldgen.ModWorldGenProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class DataGenerators {
    public static void gatherDataClient(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        try {
            generator.addProvider(true, new ModEnLangProvider(output));
            generator.addProvider(true, new ModDeLangProvider(output));
            ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(output, lookupProvider);
            generator.addProvider(true, modBlockTagsProvider);
            generator.addProvider(true, new ModItemTagsProvider(output, lookupProvider, modBlockTagsProvider));
            generator.addProvider(true, new ModLootTables(output, lookupProvider));
            generator.addProvider(true, new ModModelProvider(output));
            generator.addProvider(true, new ModWorldGenProvider(output, lookupProvider));
            generator.addProvider(true, new ModGlobalLootModifiersProvider(output, lookupProvider));
            generator.addProvider(true, new ModRecipeProvider.Runner(output, lookupProvider));
        } catch (RuntimeException e) {
            Vibranium.logger.error("Failed to generate data", e);
        }
    }
}
