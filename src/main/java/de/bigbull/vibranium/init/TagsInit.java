package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagsInit {
    public static ResourceLocation createOreLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "ores/" + name);
    }

    public static ResourceLocation createBlockLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name);
    }

    public static ResourceLocation createGenericItemsLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name);
    }

    public static ResourceLocation createRawItemsLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(Vibranium.MODID,"raw/" + name);
    }

    public static class ItemTagsInit {
        //Items
        public static final TagKey<Item> VIBRANIUM_UPGRADE_SMITHING_TEMPLATE_TAG = ItemTags.create(createGenericItemsLocation("vibranium_upgrade_smithing_template"));
        public static final TagKey<Item> VIBRANIUM_WOLF_ARMOR_TAG = ItemTags.create(createGenericItemsLocation("vibranium_wolf_armor"));
        public static final TagKey<Item> VIBRANIUM_HORSE_ARMOR_TAG = ItemTags.create(createGenericItemsLocation("vibranium_horse_armor"));
        public static final TagKey<Item> VIBRA_GOLEM_SPAWN_EGG_TAG = ItemTags.create(createGenericItemsLocation("vibra_golem_spawn_egg"));
        public static final TagKey<Item> VIBRANIUM_CORE_TAG = ItemTags.create(createGenericItemsLocation("vibranium_core"));
        public static final TagKey<Item> VIBRANIUM_HELMET_TAG = ItemTags.create(createGenericItemsLocation("vibranium_helmet"));
        public static final TagKey<Item> VIBRANIUM_CHESTPLATE_TAG = ItemTags.create(createGenericItemsLocation("vibranium_chestplate"));
        public static final TagKey<Item> VIBRANIUM_LEGGINGS_TAG = ItemTags.create(createGenericItemsLocation("vibranium_leggings"));
        public static final TagKey<Item> VIBRANIUM_BOOTS_TAG = ItemTags.create(createGenericItemsLocation("vibranium_boots"));
        public static final TagKey<Item> VIBRANIUM_INGOT_TAG = ItemTags.create(createGenericItemsLocation("vibranium_ingot"));
        public static final TagKey<Item> RAW_VIBRANIUM_TAG = ItemTags.create(createRawItemsLocation("raw_vibranium"));
        public static final TagKey<Item> HEART_SHAPED_HERB_TAG = ItemTags.create(createGenericItemsLocation("heart_shaped_herb"));
        public static final TagKey<Item> VIBRANIUM_ENRICHED_HERB_ELIXIR_TAG = ItemTags.create(createGenericItemsLocation("vibranium_enriched_herb_elixir"));
        public static final TagKey<Item> VIBRANIUM_ENRICHED_HERB_ELIXIR_EXTENDED_TAG = ItemTags.create(createGenericItemsLocation("vibranium_enriched_herb_elixir_extended"));
        public static final TagKey<Item> VIBRANIUM_ENRICHED_HERB_ELIXIR_ENHANCED_TAG = ItemTags.create(createGenericItemsLocation("vibranium_enriched_herb_elixir_enhanced"));
        public static final TagKey<Item> VIBRANIUM_CRYSTAL_SHARD_TAG = ItemTags.create(createGenericItemsLocation("vibranium_crystal_shard"));

        //Blocks
        public static final TagKey<Item> BLOCK_OF_RAW_VIBRANIUM_TAG = ItemTags.create(createBlockLocation("block_of_raw_vibranium"));
        public static final TagKey<Item> DEEPSLATE_VIBRANIUM_ORE_TAG = ItemTags.create(createOreLocation("deepslate_vibranium_ore"));
        public static final TagKey<Item> VIBRANIUM_BLOCK_TAG = ItemTags.create(createBlockLocation("vibranium_block"));
        public static final TagKey<Item> ENRICHED_VIBRANIUM_DIRT_TAG = ItemTags.create(createBlockLocation("enriched_vibranium_dirt"));
        public static final TagKey<Item> ENRICHED_VIBRANIUM_FARMLAND_TAG = ItemTags.create(createBlockLocation("enriched_vibranium_farmland"));
        public static final TagKey<Item> SOULWOOD_LOG_TAG = ItemTags.create(createBlockLocation("soulwood_log"));
        public static final TagKey<Item> SOULWOOD_SAPLING_TAG = ItemTags.create(createBlockLocation("soulwood_sapling"));
        public static final TagKey<Item> SOULWOOD_WOOD_TAG = ItemTags.create(createBlockLocation("soulwood_wood"));
        public static final TagKey<Item> STRIPPED_SOULWOOD_LOG_TAG = ItemTags.create(createBlockLocation("stripped_soulwood_log"));
        public static final TagKey<Item> STRIPPED_SOULWOOD_WOOD_TAG = ItemTags.create(createBlockLocation("stripped_soulwood_wood"));
        public static final TagKey<Item> SOULWOOD_PLANKS_TAG = ItemTags.create(createBlockLocation("soulwood_planks"));
        public static final TagKey<Item> SOULWOOD_LEAVES_TAG = ItemTags.create(createBlockLocation("soul_leaves"));
        public static final TagKey<Item> SOULWOOD_STAIRS_TAG = ItemTags.create(createBlockLocation("soulwood_stairs"));
        public static final TagKey<Item> SOULWOOD_FENCE_TAG = ItemTags.create(createBlockLocation("soulwood_fence"));
        public static final TagKey<Item> SOULWOOD_FENCE_GATE_TAG = ItemTags.create(createBlockLocation("soulwood_fence_gate"));
        public static final TagKey<Item> SOULWOOD_TRAPDOOR_TAG = ItemTags.create(createBlockLocation("soulwood_trapdoor"));
        public static final TagKey<Item> SOULWOOD_PRESSURE_PLATE_TAG = ItemTags.create(createBlockLocation("soulwood_pressure_plate"));
        public static final TagKey<Item> SOULWOOD_BUTTON_TAG = ItemTags.create(createBlockLocation("soulwood_button"));
        public static final TagKey<Item> SOULWOOD_SLAB_TAG = ItemTags.create(createBlockLocation("soulwood_slab"));
        public static final TagKey<Item> SOULWOOD_DOOR_TAG = ItemTags.create(createBlockLocation("soulwood_door"));
        public static final TagKey<Item> VIBRANIUM_CRYSTAL_BLOCK_TAG = ItemTags.create(createBlockLocation("vibranium_crystal_block"));
        public static final TagKey<Item> BUDDING_VIRBANIUM_CRYSTAL_TAG = ItemTags.create(createBlockLocation("budding_vibranium_crystal"));
        public static final TagKey<Item> LARGE_VIBRANIUM_BUD_TAG = ItemTags.create(createBlockLocation("large_vibranium_bud"));
        public static final TagKey<Item> MEDIUM_VIBRANIUM_BUD_TAG = ItemTags.create(createBlockLocation("medium_vibranium_bud"));
        public static final TagKey<Item> SMALL_VIBRANIUM_BUD_TAG = ItemTags.create(createBlockLocation("small_vibranium_bud"));

        //Custom Tags
        public static final TagKey<Item> SOULWOOD_LOGS = ItemTags.create(createGenericItemsLocation("soul_logs"));
    }

    public static class ToolTagsInit {
        public static final TagKey<Item> VIBRANIUM_MACE_TAG = ItemTags.create(createGenericItemsLocation("vibranium_mace"));
        public static final TagKey<Item> VIBRANIUM_SWORD_TAG = ItemTags.create(createGenericItemsLocation("vibranium_sword"));
        public static final TagKey<Item> VIBRANIUM_PICKAXE_TAG = ItemTags.create(createGenericItemsLocation("vibranium_pickaxe"));
        public static final TagKey<Item> VIBRANIUM_AXE_TAG = ItemTags.create(createGenericItemsLocation("vibranium_axe"));
        public static final TagKey<Item> VIBRANIUM_SHOVEL_TAG = ItemTags.create(createGenericItemsLocation("vibranium_shovel"));
        public static final TagKey<Item> VIBRANIUM_HOE_TAG = ItemTags.create(createGenericItemsLocation("vibranium_hoe"));
        public static final TagKey<Item> VIBRANIUM_SHIELD_TAG = ItemTags.create(createGenericItemsLocation("vibranium_shield"));
    }

    public static class BlockTagsInit {
        public static final TagKey<Block> BLOCK_OF_RAW_VIBRANIUM_TAG = BlockTags.create(createBlockLocation("block_of_raw_vibranium"));
        public static final TagKey<Block> DEEPSLATE_VIBRANIUM_ORE_TAG = BlockTags.create(createOreLocation("deepslate_vibranium_ore"));
        public static final TagKey<Block> VIBRANIUM_BLOCK_TAG = BlockTags.create(createBlockLocation("vibranium_block"));
        public static final TagKey<Block> HEART_SHAPED_HERB_BUSH_TAG = BlockTags.create(createBlockLocation("heart_shaped_herb_bush"));
        public static final TagKey<Block> ENRICHED_VIBRANIUM_DIRT_TAG = BlockTags.create(createBlockLocation("enriched_vibranium_dirt"));
        public static final TagKey<Block> ENRICHED_VIBRANIUM_FARMLAND_TAG = BlockTags.create(createBlockLocation("enriched_vibranium_farmland"));
        public static final TagKey<Block> SOULWOOD_LOG_TAG = BlockTags.create(createBlockLocation("soulwood_log"));
        public static final TagKey<Block> SOULWOOD_SAPLING_TAG = BlockTags.create(createBlockLocation("soulwood_sapling"));
        public static final TagKey<Block> SOULWOOD_WOOD_TAG = BlockTags.create(createBlockLocation("soulwood_wood"));
        public static final TagKey<Block> STRIPPED_SOULWOOD_LOG_TAG = BlockTags.create(createBlockLocation("stripped_soulwood_log"));
        public static final TagKey<Block> STRIPPED_SOULWOOD_WOOD_TAG = BlockTags.create(createBlockLocation("stripped_soulwood_wood"));
        public static final TagKey<Block> SOULWOOD_PLANKS_TAG = BlockTags.create(createBlockLocation("soulwood_planks"));
        public static final TagKey<Block> SOULWOOD_LEAVES_TAG = BlockTags.create(createBlockLocation("soul_leaves"));
        public static final TagKey<Block> SOULWOOD_STAIRS_TAG = BlockTags.create(createBlockLocation("soulwood_stairs"));
        public static final TagKey<Block> SOULWOOD_FENCE_TAG = BlockTags.create(createBlockLocation("soulwood_fence"));
        public static final TagKey<Block> SOULWOOD_FENCE_GATE_TAG = BlockTags.create(createBlockLocation("soulwood_fence_gate"));
        public static final TagKey<Block> SOULWOOD_TRAPDOOR_TAG = BlockTags.create(createBlockLocation("soulwood_trapdoor"));
        public static final TagKey<Block> SOULWOOD_PRESSURE_PLATE_TAG = BlockTags.create(createBlockLocation("soulwood_pressure_plate"));
        public static final TagKey<Block> SOULWOOD_BUTTON_TAG = BlockTags.create(createBlockLocation("soulwood_button"));
        public static final TagKey<Block> SOULWOOD_SLAB_TAG = BlockTags.create(createBlockLocation("soulwood_slab"));
        public static final TagKey<Block> SOULWOOD_DOOR_TAG = BlockTags.create(createBlockLocation("soulwood_door"));
        public static final TagKey<Block> VIBRANIUM_CRYSTAL_BLOCK_TAG = BlockTags.create(createBlockLocation("vibranium_crystal_block"));
        public static final TagKey<Block> BUDDING_VIRBANIUM_CRYSTAL_TAG = BlockTags.create(createBlockLocation("budding_vibranium_crystal"));
        public static final TagKey<Block> LARGE_VIBRANIUM_BUD_TAG = BlockTags.create(createBlockLocation("large_vibranium_bud"));
        public static final TagKey<Block> MEDIUM_VIBRANIUM_BUD_TAG = BlockTags.create(createBlockLocation("medium_vibranium_bud"));
        public static final TagKey<Block> SMALL_VIBRANIUM_BUD_TAG = BlockTags.create(createBlockLocation("small_vibranium_bud"));

        //Custom Tags
        public static final TagKey<Block> INCORRECT_FOR_VIBRANIUM_TOOL = BlockTags.create(createBlockLocation("incorrect_for_vibranium_tool"));
        public static final TagKey<Block> NEEDS_VIBRANIUM_TOOL = BlockTags.create(createBlockLocation("needs_vibranium_tool"));
        public static final TagKey<Block> SOULWOOD_LOGS = BlockTags.create(createBlockLocation("soul_logs"));
    }
}