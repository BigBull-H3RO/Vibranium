package de.bigbull.vibranium.data.tag;

import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.TagsInit;
import de.bigbull.vibranium.main.ModInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ModInfo.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG)
                .add(BlockInit.BLOCK_OF_RAW_VIBRANIUM.get());

        tag(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG)
                .add(BlockInit.DEPPSLATE_VIBRANIUM_ORE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG)
                .addTag(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG);

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG)
                .addTag(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG);
    }
}