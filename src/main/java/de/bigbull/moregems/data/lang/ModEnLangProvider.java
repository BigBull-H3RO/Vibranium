package de.bigbull.moregems.data.lang;

import de.bigbull.moregems.init.BlockInit;
import de.bigbull.moregems.init.ItemInit;
import de.bigbull.moregems.main.Main;
import de.bigbull.moregems.init.CreativeTabInit;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnLangProvider extends LanguageProvider {
    public ModEnLangProvider(PackOutput output) {
        super(output, Main.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Items
        addItem(ItemInit.RAW_VIBRANIUM, "Raw Vibranium");

        //Blocks
        addBlock(BlockInit.BLOCK_OF_RAW_VIBRANIUM, "Block of Raw Vibranium");

        //Ores
        addBlock(BlockInit.DEPPSLATE_VIBRANIUM_ORE, "Deepslate Vibranium Ore");

        //others
        add(CreativeTabInit.MAIN_TAB_ONE_TITLE, "MoreGems");
    }
}
