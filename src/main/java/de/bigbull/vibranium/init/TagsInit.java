package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagsInit {
    public static class Items {
        public static final TagKey<Item> HEART_SHAPED_HERB_TAG = createItemTag("heart_shaped_herb");
        public static final TagKey<Item> SOULWOOD_LOGS = createItemTag("soulwood_logs");
        public static final TagKey<Item> VIBRANIUM_REPAIR = createItemTag("vibranium_repair");
    }

    public static class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_VIBRANIUM_TOOL = createBlockTag("incorrect_for_vibranium_tool");
        public static final TagKey<Block> NEEDS_VIBRANIUM_TOOL = createBlockTag("needs_vibranium_tool");
        public static final TagKey<Block> SOULWOOD_LOGS = createBlockTag("soulwood_logs");
    }

    private static TagKey<Item> createItemTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name));
    }

    private static TagKey<Block> createBlockTag(String name) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name));
    }
}