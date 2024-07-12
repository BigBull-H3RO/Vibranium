package de.bigbull.gemsofpower.data.tag;

import de.bigbull.gemsofpower.init.ItemInit;
import de.bigbull.gemsofpower.init.TagsInit;
import de.bigbull.gemsofpower.main.ModInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, BlockTagsProvider provider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, provider.contentsGetter(), ModInfo.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        copy(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG, TagsInit.ItemTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG);
        copy(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG, TagsInit.ItemTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG);

        tag(TagsInit.ItemTagsInit.RAW_VIBRANIUM_TAG).add(ItemInit.RAW_VIBRANIUM.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_INGOT_TAG).add(ItemInit.VIBRANIUM_INGOT.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE_TAG).add(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get());

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(   ItemInit.VIBRANIUM_BOOTS.get(),
                        ItemInit.VIBRANIUM_LEGGINGS.get(),
                        ItemInit.VIBRANIUM_CHESTPLATE.get(),
                        ItemInit.VIBRANIUM_HELMET.get());
    }
}
