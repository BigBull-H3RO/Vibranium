package de.bigbull.vibranium.data.tag;

import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.TagsInit;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import net.neoforged.neoforge.common.Tags.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Vibranium.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(TagsInit.BlockTagsInit.INCORRECT_FOR_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_IRON_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_STONE_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG)
                .addTag(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG)
                .addTag(TagsInit.BlockTagsInit.VIBRANIUM_BLOCK_TAG);

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_DIRT_TAG)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_FARMLAND_TAG);

        tag(Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG)
                .addTag(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG)
                .addTag(TagsInit.BlockTagsInit.VIBRANIUM_BLOCK_TAG);

        tag(BlockTags.MINEABLE_WITH_HOE)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_FARMLAND_TAG)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_DIRT_TAG);

        tag(BlockTags.BIG_DRIPLEAF_PLACEABLE)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_DIRT_TAG)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_FARMLAND_TAG);

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .addTag(TagsInit.BlockTagsInit.VIBRANIUM_BLOCK_TAG);

        //Blocks
        tag(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG).add(BlockInit.BLOCK_OF_RAW_VIBRANIUM.get());
        tag(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG).add(BlockInit.DEPPSLATE_VIBRANIUM_ORE.get());
        tag(TagsInit.BlockTagsInit.VIBRANIUM_BLOCK_TAG).add(BlockInit.Vibranium_Block.get());
        tag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_DIRT_TAG).add(BlockInit.ENRICHED_VIBRANIUM_DIRT.get());
        tag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_FARMLAND_TAG).add(BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get());
    }
}