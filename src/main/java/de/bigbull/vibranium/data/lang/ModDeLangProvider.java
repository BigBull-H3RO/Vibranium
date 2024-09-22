package de.bigbull.vibranium.data.lang;

import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.CreativeTabInit;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModDeLangProvider extends LanguageProvider {
    public ModDeLangProvider(PackOutput output) {super(output, Vibranium.MODID, "de_de");
    }

    @Override
    protected void addTranslations() {
        //Items
        addItem(ItemInit.RAW_VIBRANIUM, "Rohes Vibranium");
        addItem(ItemInit.VIBRANIUM_INGOT, "Vibranium Barren");
        addItem(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, "Schmiedevorlage");
        addItem(ItemInit.VIBRA_GOLEM_SPAWN_EGG, "Vibra Golem Spawnei");
        addItem(ItemInit.VIBRANIUM_CORE, "Vibranium Kern");
        addItem(ItemInit.HEART_SHAPED_HERB, "Herzformiges Kraut");

        //Blocks
        addBlock(BlockInit.BLOCK_OF_RAW_VIBRANIUM, "Block aus rohem Vibranium");
        addBlock(BlockInit.DEPPSLATE_VIBRANIUM_ORE, "Tiefenschiefer Vibraniumerz");
        addBlock(BlockInit.Vibranium_Block, "Vibranium Block");

        //Tools
        addItem(ItemInit.VIBRANIUM_SWORD, "Vibranium Schwert");
        addItem(ItemInit.VIBRANIUM_PICKAXE, "Vibranium Spitzhacke");
        addItem(ItemInit.VIBRANIUM_AXE, "Vibranium Axt");
        addItem(ItemInit.VIBRANIUM_SHOVEL, "Vibranium Schaufel");
        addItem(ItemInit.VIBRANIUM_HOE, "Vibranium Hacke");
        addItem(ItemInit.VIBRANIUM_MACE, "Vibranium Mace");
        addItem(ItemInit.VIBRANIUM_SHIELD, "Vibranium Schild");

        //Armors
        addItem(ItemInit.VIBRANIUM_BOOTS, "Vibranium Stiefel");
        addItem(ItemInit.VIBRANIUM_LEGGINGS, "Vibranium Beinschützer");
        addItem(ItemInit.VIBRANIUM_CHESTPLATE, "Vibranium Brustplatte");
        addItem(ItemInit.VIBRANIUM_HELMET, "Vibranium Helm");
        addItem(ItemInit.VIBRANIUM_WOLF_ARMOR, "Vibranium Wolfsrüstung");
        addItem(ItemInit.VIBRANIUM_HORSE_ARMOR, "Vibranium Pferderüstung");

        //SmifhingTemplate
        add(ItemInit.VIBRANIUM_UPGRADE_APPLIES_TO.getString(), "Diamantausrüstung");
        add(ItemInit.VIBRANIUM_UPGRADE_INGREDIENTS.getString(), "Vibranium Barren");
        add(ItemInit.VIBRANIUM_UPGRADE.getString(), "Vibranium Aufrüstung");
        add(ItemInit.VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Diamantrüstung, -waffe oder -werkzeug hinzufügen");
        add(ItemInit.VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Vibranium Barren hinzufügen");

        //Vibranium Mace
        add("item.vibranium_mace.tooltip", "Kann 3x3 Blöcke abbauen");

        //others
        add(CreativeTabInit.MAIN_TAB_ONE_TITLE, "Vibranium");
    }
}
