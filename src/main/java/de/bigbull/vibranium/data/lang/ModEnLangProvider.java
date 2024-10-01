package de.bigbull.vibranium.data.lang;

import de.bigbull.vibranium.init.*;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnLangProvider extends LanguageProvider {
    public ModEnLangProvider(PackOutput output) {
        super(output, Vibranium.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //Items
        addItem(ItemInit.RAW_VIBRANIUM, "Raw Vibranium");
        addItem(ItemInit.VIBRANIUM_INGOT, "Vibranium Ingot");
        addItem(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, "Smithing Template");
        addItem(ItemInit.VIBRA_GOLEM_SPAWN_EGG, "Vibra Golem Spawn Egg");
        addItem(ItemInit.VIBRANIUM_CORE, "Vibranium Core");
        addItem(ItemInit.HEART_SHAPED_HERB, "Heart Shaped Herb");

        //Blocks
        addBlock(BlockInit.BLOCK_OF_RAW_VIBRANIUM, "Block of Raw Vibranium");
        addBlock(BlockInit.DEPPSLATE_VIBRANIUM_ORE, "Deepslate Vibranium Ore");
        addBlock(BlockInit.Vibranium_Block, "Vibranium Block");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_DIRT, "Enriched Vibranium Dirt");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_FARMLAND, "Enriched Vibranium Farmland");

        //Tools
        addItem(ItemInit.VIBRANIUM_SWORD, "Vibranium Sword");
        addItem(ItemInit.VIBRANIUM_PICKAXE, "Vibranium Pickaxe");
        addItem(ItemInit.VIBRANIUM_AXE, "Vibranium Axe");
        addItem(ItemInit.VIBRANIUM_SHOVEL, "Vibranium Shovel");
        addItem(ItemInit.VIBRANIUM_HOE, "Vibranium Hoe");
        addItem(ItemInit.VIBRANIUM_MACE, "Vibranium Mace");
        addItem(ItemInit.VIBRANIUM_SHIELD, "Vibranium Shield");

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

        //Potion
        addItem(ItemInit.VIBRANIUM_ENRICHED_HERB_ELIXIR, "Vibranium Enriched Herb Elixir");
        addItem(ItemInit.VIBRANIUM_ENRICHED_HERB_ELIXIR_EXTENDED, "Vibranium Enriched Herb Elixir");
        addItem(ItemInit.VIBRANIUM_ENRICHED_HERB_ELIXIR_ENHANCED, "Vibranium Enriched Herb Elixir");
        add("effect.vibranium.kinetic_redistribution", "Kinetic Redistribution");

        //KeyBinding
        add("key.categories.vibranium", "Vibranium");
        add("key.vibranium.toggle_outline", "Toggle Outline");

        //JEI
        add("jei.vibranium.heart_shaped_herb.desc",
                "The Heart-Shaped Herb can be found at Ruined Portals. Be cautious, though consuming it comes with both beneficial and harmful effects");
        add("jei.vibranium.vibranium_upgrade_smithing_template.desc",
                "This smithing template can be found in a Trial Chamber, tucked away in a barrel near some beds.");
        add("jei.vibranium.enriched_vibranium_dirt.desc",
                "To obtain Enriched Vibranium Dirt, right-click on regular dirt with Raw Vibranium.");
        add("jei.vibranium.vibranium_core.desc",
                "This mysterious core emits the same energy as the Vibra Golems. What might happen if they come into contact?");
        add("jei.vibranium.enriched_vibranium_farmland.desc",
                "The mysterious energy within Enriched Vibranium Farmland seems to accelerate the growth of plants.");

        //Entities
//        add("entity.vibra_golem.vibra_golem", "Vibra Golem");

        //Vibranium Mace
        add("item.vibranium_mace.tooltip", "Can mine 3x3 blocks");

        //others
        add(CreativeTabInit.MAIN_TAB_ONE_TITLE, "Vibranium");
    }
}
