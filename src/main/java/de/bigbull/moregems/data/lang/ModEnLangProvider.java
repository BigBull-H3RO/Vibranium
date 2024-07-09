package de.bigbull.moregems.data.lang;

import de.bigbull.moregems.item.ModItems;
import de.bigbull.moregems.main.Main;
import de.bigbull.moregems.ui.CreativeModeTabUI;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnLangProvider extends LanguageProvider {
    public ModEnLangProvider(PackOutput output) {
        super(output, Main.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Items
        addItem(ModItems.VIBRANIUM, "Vibranium");


        //others
        add(CreativeModeTabUI.MAIN_TAB_ONE_TITLE, "MoreGems");
    }
}
