package de.bigbull.moregems.data;

import de.bigbull.moregems.data.loottable.ModLootTables;
import de.bigbull.moregems.data.tag.ModBlockTagsProvider;
import de.bigbull.moregems.data.tag.ModItemTagsProvider;
import de.bigbull.moregems.data.texture.ModBlockStateProvider;
import de.bigbull.moregems.data.texture.ModItemStateProvider;
import de.bigbull.moregems.data.worldgen.ModWorldGenProvider;
import de.bigbull.moregems.main.Main;
import de.bigbull.moregems.data.lang.ModEnLangProvider;
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
            generator.addProvider(true, new ModItemStateProvider(output, existingFileHelper));
            generator.addProvider(true, new ModBlockStateProvider(output, existingFileHelper));
            ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(output, event.getLookupProvider(), existingFileHelper);
            generator.addProvider(true, modBlockTagsProvider);
            generator.addProvider(true, new ModItemTagsProvider(output, event.getLookupProvider(), modBlockTagsProvider, existingFileHelper));
            generator.addProvider(true, new ModLootTables(output, event.getLookupProvider()));
            generator.addProvider(true, new ModWorldGenProvider(output, event.getLookupProvider()));
        } catch (RuntimeException e) {
            Main.logger.error("Failed to generate data", e);
        }
    }
}
