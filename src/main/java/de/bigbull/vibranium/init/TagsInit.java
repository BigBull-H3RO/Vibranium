package de.bigbull.vibranium.init;

import de.bigbull.vibranium.main.Vibranium;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SimpleTier;

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
        public static final TagKey<Item> VIBRA_GOLEM_SPAWN_EGG_TAG = ItemTags.create(createGenericItemsLocation("vibra_golem_spawn_egg"));
        public static final TagKey<Item> VIBRANIUM_CORE_TAG = ItemTags.create(createGenericItemsLocation("vibranium_core"));

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

    public static class MaterialTagsInit {
        public static final SimpleTier VIBRANIUM = new SimpleTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2500, 10.0f, 2.5f, 18, () -> Ingredient.of(ToolTagsInit.VIBRANIUM_TAG));
    }

    public static class ToolTagsInit {
        public static final TagKey<Item> VIBRANIUM_TAG = ItemTags.create(ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "gems/vibranium"));
        public static final TagKey<Item> VIRABNIUM_MACE_TAG = ItemTags.create(ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "tools/vibranium_mace"));
    }
}
