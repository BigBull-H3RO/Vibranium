package de.bigbull.gemsofpower.data.lang;

import de.bigbull.gemsofpower.init.BlockInit;
import de.bigbull.gemsofpower.init.ItemInit;
import de.bigbull.gemsofpower.init.CreativeTabInit;
import de.bigbull.gemsofpower.main.ModInfo;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnLangProvider extends LanguageProvider {
    public ModEnLangProvider(PackOutput output) {
        super(output, ModInfo.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Items
        addItem(ItemInit.RAW_VIBRANIUM, "Raw Vibranium");
        addItem(ItemInit.VIBRANIUM_INGOT, "Vibranium Ingot");
        addItem(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, "Smithing Template");

        //Blocks
        addBlock(BlockInit.BLOCK_OF_RAW_VIBRANIUM, "Block of Raw Vibranium");

        //Ores
        addBlock(BlockInit.DEPPSLATE_VIBRANIUM_ORE, "Deepslate Vibranium Ore");

        //Armors
        addItem(ItemInit.VIBRANIUM_BOOTS, "Vibranium Boots");
        addItem(ItemInit.VIBRANIUM_LEGGINGS, "Vibranium Leggings");
        addItem(ItemInit.VIBRANIUM_CHESTPLATE, "Vibranium Chestplate");
        addItem(ItemInit.VIBRANIUM_HELMET, "Vibranium Helmet");

        //others
        add(CreativeTabInit.MAIN_TAB_ONE_TITLE, "Gems of Power");
    }
}
