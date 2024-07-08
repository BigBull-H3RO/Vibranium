package de.bigbull.moregems.data.lang;

import de.bigbull.moregems.item.ModItems;
import de.bigbull.moregems.main;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnLangProvider extends LanguageProvider {
    public ModEnLangProvider(PackOutput output) {
        super(output, main.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItem(ModItems.VIBRANIUM, "Vibranium");
    }
}
