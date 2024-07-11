package de.bigbull.moregems.data.tag;

import de.bigbull.moregems.init.ItemInit;
import de.bigbull.moregems.init.TagsInit;
import de.bigbull.moregems.main.Main;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, BlockTagsProvider provider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, provider.contentsGetter(), Main.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        copy(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG, TagsInit.ItemTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG);
        copy(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG, TagsInit.ItemTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG);

        tag(TagsInit.ItemTagsInit.RAW_VIBRANIUM_TAG).add(ItemInit.RAW_VIBRANIUM.get());

        // tag(ItemTags.TRIMMABLE_ARMOR)
    }
}
