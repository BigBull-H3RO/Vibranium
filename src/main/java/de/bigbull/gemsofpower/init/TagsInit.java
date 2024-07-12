package de.bigbull.gemsofpower.init;

import de.bigbull.gemsofpower.main.ModInfo;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagsInit {
    public static ResourceLocation createOreLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "ores/" + name);
    }

    public static ResourceLocation createBlockLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, name);
    }

    public static ResourceLocation createGenericItemsLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, name);
    }

    public static ResourceLocation createRawItemsLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(ModInfo.MODID,"raw/" + name);
    }

    public static TagKey<Block> createToolTag(String name) {
        return TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, name));
    }

    public static class ItemTagsInit {
        //Items
        public static final TagKey<Item> VIBRANIUM_INGOT_TAG = ItemTags.create(createGenericItemsLocation("vibranium_ingot"));
        public static final TagKey<Item> VIBRANIUM_UPGRADE_SMITHING_TEMPLATE_TAG = ItemTags.create(createGenericItemsLocation("vibranium_upgrade_smithing_template"));

        //Blocks
        public static final TagKey<Item> BLOCK_OF_RAW_VIBRANIUM_TAG = ItemTags.create(createBlockLocation("block_of_raw_vibranium"));

        //Ores/Raws
        public static final TagKey<Item> RAW_VIBRANIUM_TAG = ItemTags.create(createRawItemsLocation("raw_vibranium"));
        public static final TagKey<Item> DEEPSLATE_VIBRANIUM_ORE_TAG = ItemTags.create(createOreLocation("deepslate_vibranium_ore"));

    }

    public static class BlockTagsInit {
        public static final TagKey<Block> BLOCK_OF_RAW_VIBRANIUM_TAG = BlockTags.create(createBlockLocation("block_of_raw_vibranium"));
        public static final TagKey<Block> DEEPSLATE_VIBRANIUM_ORE_TAG = BlockTags.create(createOreLocation("deepslate_vibranium_ore"));
    }

    //materials tags
    public static final TagKey<Item> VIRANMIUM_TAG = ItemTags.create(ResourceLocation.fromNamespaceAndPath("gemsofpower", "materials/vibranium"));

}
