package de.bigbull.vibranium.data.lang;

import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.CreativeTabInit;
import de.bigbull.vibranium.main.ModInfo;
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
        addItem(ItemInit.VIBRA_GOLEM_SPAWN_EGG, "Vibra Golem Spawn Egg");
        addItem(ItemInit.VIBRANIUM_CORE, "Vibranium Core");
        addItem(ItemInit.VIBRANIUM_MACE, "Vibranium Mace");

        //Blocks
        addBlock(BlockInit.BLOCK_OF_RAW_VIBRANIUM, "Block of Raw Vibranium");
        addBlock(BlockInit.DEPPSLATE_VIBRANIUM_ORE, "Deepslate Vibranium Ore");

        //Tools
        addItem(ItemInit.VIBRANIUM_SWORD, "Vibranium Sword");
        addItem(ItemInit.VIBRANIUM_PICKAXE, "Vibranium Pickaxe");
        addItem(ItemInit.VIBRANIUM_AXE, "Vibranium Axe");
        addItem(ItemInit.VIBRANIUM_SHOVEL, "Vibranium Shovel");
        addItem(ItemInit.VIBRANIUM_HOE, "Vibranium Hoe");

        //Armors
        addItem(ItemInit.VIBRANIUM_BOOTS, "Vibranium Boots");
        addItem(ItemInit.VIBRANIUM_LEGGINGS, "Vibranium Leggings");
        addItem(ItemInit.VIBRANIUM_CHESTPLATE, "Vibranium Chestplate");
        addItem(ItemInit.VIBRANIUM_HELMET, "Vibranium Helmet");
        addItem(ItemInit.VIBRANIUM_WOLF_ARMOR, "Vibranium Wolf Armor");
        addItem(ItemInit.VIBRANIUM_HORSE_ARMOR, "Vibranium Horse Armor");

        //SmifhingTemplate
        add(ItemInit.VIBRANIUM_UPGRADE_APPLIES_TO.getString(), "Diamond Equipment");
        add(ItemInit.VIBRANIUM_UPGRADE_INGREDIENTS.getString(), "Vibranium Ingot");
        add(ItemInit.VIBRANIUM_UPGRADE.getString(), "Vibranium Upgrade");
        add(ItemInit.VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Add diamond armor, weapon, or tool");
        add(ItemInit.VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Add Vibranium Ingot");

        //Vibranium Mace
        add("item.vibranium_mace.tooltip", "Can mine 3x3 blocks");

        //others
        add(CreativeTabInit.MAIN_TAB_ONE_TITLE, "Vibranium");
    }
}
