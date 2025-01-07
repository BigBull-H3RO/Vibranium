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
        addItem(ItemInit.VIBRANIUM_NUGGET, "Vibranium Nugget");
        addItem(ItemInit.VIBRANIUM_PLATE, "Vibranium Plate");
        addItem(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, "Smithing Template");
//        addItem(ItemInit.VIBRA_GOLEM_SPAWN_EGG, "Vibra Golem Spawn Egg");
        addItem(ItemInit.VIBRANIUM_CORE, "Vibranium Core");
        addItem(ItemInit.HEART_SHAPED_HERB, "Heart Shaped Herb");
        addItem(ItemInit.SOUL_HERB_MIXTURE, "Soul Herb Mixture");
        addItem(ItemInit.VIBRANIUM_CRYSTAL_SHARD, "Vibranium Crystal Shard");
        addItem(ItemInit.SOULWOOD_SIGN, "Soulwood Sign");
        addItem(ItemInit.SOULWOOD_HANGING_SIGN, "Soulwood Hanging Sign");
        addItem(ItemInit.SOULWOOD_BOAT, "Soulwood Boat");
        addItem(ItemInit.SOULWOOD_CHEST_BOAT, "Soulwood Boat with Chest");
        add("entity.vibranium.soulwood_chest_boat", "Soulwood Boat with Chest");

        //Blocks
        addBlock(BlockInit.BLOCK_OF_RAW_VIBRANIUM, "Block of Raw Vibranium");
        addBlock(BlockInit.DEPPSLATE_VIBRANIUM_ORE, "Deepslate Vibranium Ore");
        addBlock(BlockInit.VIBRANIUM_BLOCK, "Vibranium Block");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_DIRT, "Enriched Vibranium Dirt");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_FARMLAND, "Enriched Vibranium Farmland");
        addBlock(BlockInit.SOULWOOD_LOG, "Soulwood Log");
        addBlock(BlockInit.SOULWOOD_SAPLING, "Soulwood Sapling");
        addBlock(BlockInit.SOULWOOD_WOOD, "Soulwood");
        addBlock(BlockInit.STRIPPED_SOULWOOD_LOG, "Stripped Soulwood Log");
        addBlock(BlockInit.STRIPPED_SOULWOOD_WOOD, "Stripped Soulwood Wood");
        addBlock(BlockInit.SOULWOOD_PLANKS, "Soulwood Planks");
        addBlock(BlockInit.SOULWOOD_LEAVES, "Soul Leaves");
        addBlock(BlockInit.SOULWOOD_SLAB, "Soulwood Slab");
        addBlock(BlockInit.SOULWOOD_STAIRS, "Soulwood Stairs");
        addBlock(BlockInit.SOULWOOD_FENCE, "Soulwood Fence");
        addBlock(BlockInit.SOULWOOD_FENCE_GATE, "Soulwood Gate");
        addBlock(BlockInit.SOULWOOD_DOOR, "Soulwood Door");
        addBlock(BlockInit.SOULWOOD_TRAPDOOR, "Soulwood Trapdoor");
        addBlock(BlockInit.SOULWOOD_PRESSURE_PLATE, "Soulwood Plate");
        addBlock(BlockInit.SOULWOOD_BUTTON, "Soulwood Button");
        addBlock(BlockInit.VIBRANIUM_CRYSTAL_BLOCK, "Crystal Block of Vibranium");
        addBlock(BlockInit.BUDDING_VIBRANIUM_CRYSTAL, "Budding Crystal Vibranium");
        addBlock(BlockInit.VIBRANIUM_CLUSTER, "Vibranium Cluster");
        addBlock(BlockInit.SMALL_VIBRANIUM_BUD, "Small Crystal Vibranium Bud");
        addBlock(BlockInit.MEDIUM_VIBRANIUM_BUD, "Medium Crystal Vibranium Bud");
        addBlock(BlockInit.LARGE_VIBRANIUM_BUD, "Large Crystal Vibranium Bud");

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
        addItem(ItemInit.SOUL_HERB_ELIXIR, "Soul Herb Elixir");
        addItem(ItemInit.SOUL_HERB_ELIXIR_EXTENDED, "Soul Herb Elixir");
        addItem(ItemInit.SOUL_HERB_ELIXIR_ENHANCED, "Soul Herb Elixir");
        add("effect.vibranium.kinetic_redistribution", "Kinetic Redistribution");

        //KeyBinding
        add("key.categories.vibranium", "Vibranium");
        add("key.vibranium.toggle_outline", "Toggle Outline");

        //Enchantments
        add("enchantment.vibranium.universal_breaker", "Universal Breaker");
        add("enchantment.vibranium.universal_breaker.desc", "Enables the removal of all blocks in the area, regardless of the tool requirements.");

        //JEI
        add("jei.vibranium.heart_shaped_herb.desc",
                "The Heart-Shaped Herb can be found at Ruined Portals. Be cautious, though consuming it comes with both beneficial and harmful effects.");
        add("jei.vibranium.vibranium_upgrade_smithing_template.desc",
                "This smithing template can be found in a Trial Chamber, tucked away in a barrel near some beds.");
        add("jei.vibranium.enriched_vibranium_dirt.desc",
                "To obtain Enriched Vibranium Dirt, right-click on regular dirt with Raw Vibranium. You can separate the Raw Vibranium from the dirt by using a hoe.");
        add("jei.vibranium.vibranium_core.desc",
                "This mysterious core emits the same energy as the Vibra Golems. What might happen if they come into contact?");
        add("jei.vibranium.enriched_vibranium_farmland.desc",
                "The mysterious energy within Enriched Vibranium Farmland seems to accelerate the growth of plants. You can retrieve the Raw Vibranium from the farmland using a hoe.");

        //Trim Materials
        add("trim_material.vibranium.vibranium", "Vibranium Material");

        //Entities
//        add("entity.vibra_golem.vibra_golem", "Vibra Golem");

        //Vibranium Mace
        add("item.vibranium_mace.tooltip", "Can mine 3x3 blocks");

        //others
        add(CreativeTabInit.MAIN_TAB_ONE_TITLE, "Vibranium");
    }
}
