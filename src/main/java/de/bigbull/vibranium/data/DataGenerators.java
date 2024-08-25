package de.bigbull.vibranium.data;

import de.bigbull.vibranium.data.lang.ModDeLangProvider;
import de.bigbull.vibranium.data.loot.ModGlobalLootModifiersProvider;
import de.bigbull.vibranium.data.loottable.ModLootTables;
import de.bigbull.vibranium.data.recipe.MainModRecipeProvider;
import de.bigbull.vibranium.data.tag.ModBlockTagsProvider;
import de.bigbull.vibranium.data.tag.ModItemTagsProvider;
import de.bigbull.vibranium.data.texture.ModBlockStateProvider;
import de.bigbull.vibranium.data.texture.ModItemStateProvider;
import de.bigbull.vibranium.data.worldgen.ModWorldGenProvider;
import de.bigbull.vibranium.main.Vibranium;
import de.bigbull.vibranium.data.lang.ModEnLangProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGenerators {

    public static void gatherData(GatherDataEvent event) {
        try {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

            generator.addProvider(true, new ModEnLangProvider(output));
            generator.addProvider(true, new ModDeLangProvider(output));
            generator.addProvider(true, new ModItemStateProvider(output, existingFileHelper));
            generator.addProvider(true, new ModBlockStateProvider(output, existingFileHelper));
            ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(output, event.getLookupProvider(), existingFileHelper);
            generator.addProvider(true, modBlockTagsProvider);
            generator.addProvider(true, new ModItemTagsProvider(output, event.getLookupProvider(), modBlockTagsProvider, existingFileHelper));
            generator.addProvider(true, new ModLootTables(output, event.getLookupProvider()));
            generator.addProvider(true, new ModWorldGenProvider(output, event.getLookupProvider()));
            generator.addProvider(true, new ModGlobalLootModifiersProvider(output, event.getLookupProvider()));
            generator.addProvider(true, new MainModRecipeProvider(generator, event.getLookupProvider()));
        } catch (RuntimeException e) {
            Vibranium.logger.error("Failed to generate data", e);
        }
    }
}
