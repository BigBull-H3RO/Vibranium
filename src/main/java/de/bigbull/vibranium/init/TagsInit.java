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

        //Blocks
        public static final TagKey<Item> BLOCK_OF_RAW_VIBRANIUM_TAG = ItemTags.create(createBlockLocation("block_of_raw_vibranium"));
        public static final TagKey<Item> DEEPSLATE_VIBRANIUM_ORE_TAG = ItemTags.create(createOreLocation("deepslate_vibranium_ore"));
        public static final TagKey<Item> VIBRANIUM_BLOCK_TAG = ItemTags.create(createBlockLocation("vibranium_block"));
    }

    public static class ToolTagsInit {
        public static final TagKey<Item> VIRABNIUM_MACE_TAG = ItemTags.create(createGenericItemsLocation("vibranium_mace"));
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

        public static final TagKey<Block> INCORRECT_FOR_VIBRANIUM_TOOL = BlockTags.create(createBlockLocation("incorrect_for_vibranium_tool"));
        public static final TagKey<Block> NEEDS_VIBRANIUM_TOOL = BlockTags.create(createBlockLocation("needs_vibranium_tool"));
    }
}