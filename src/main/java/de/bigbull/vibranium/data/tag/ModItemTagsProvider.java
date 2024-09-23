package de.bigbull.vibranium.data.tag;

import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.TagsInit;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, BlockTagsProvider provider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, provider.contentsGetter(), Vibranium.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //Blocks
        copy(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG, TagsInit.ItemTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG);
        copy(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG, TagsInit.ItemTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG);
        copy(TagsInit.BlockTagsInit.VIBRANIUM_BLOCK_TAG, TagsInit.ItemTagsInit.VIBRANIUM_BLOCK_TAG);
        copy(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_DIRT_TAG, TagsInit.ItemTagsInit.ENRICHED_VIBRANIUM_DIRT_TAG);

        //Items
        tag(TagsInit.ItemTagsInit.RAW_VIBRANIUM_TAG).add(ItemInit.RAW_VIBRANIUM.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_INGOT_TAG).add(ItemInit.VIBRANIUM_INGOT.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE_TAG).add(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get());
        tag(TagsInit.ItemTagsInit.VIBRA_GOLEM_SPAWN_EGG_TAG).add(ItemInit.VIBRA_GOLEM_SPAWN_EGG.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_CORE_TAG).add(ItemInit.VIBRANIUM_CORE.get());

        //Armor
        tag(TagsInit.ItemTagsInit.VIBRANIUM_HELMET_TAG).add(ItemInit.VIBRANIUM_HELMET.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_CHESTPLATE_TAG).add(ItemInit.VIBRANIUM_CHESTPLATE.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_LEGGINGS_TAG).add(ItemInit.VIBRANIUM_LEGGINGS.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_BOOTS_TAG).add(ItemInit.VIBRANIUM_BOOTS.get());

        //ArmorAnimals
        tag(TagsInit.ItemTagsInit.VIBRANIUM_WOLF_ARMOR_TAG).add(ItemInit.VIBRANIUM_WOLF_ARMOR.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_HORSE_ARMOR_TAG).add(ItemInit.VIBRANIUM_HORSE_ARMOR.get());

        //Tools
        tag(TagsInit.ToolTagsInit.VIRABNIUM_MACE_TAG).add(ItemInit.VIBRANIUM_MACE.get());
        tag(TagsInit.ToolTagsInit.VIBRANIUM_SWORD_TAG).add(ItemInit.VIBRANIUM_SWORD.get());
        tag(TagsInit.ToolTagsInit.VIBRANIUM_PICKAXE_TAG).add(ItemInit.VIBRANIUM_PICKAXE.get());
        tag(TagsInit.ToolTagsInit.VIBRANIUM_AXE_TAG).add(ItemInit.VIBRANIUM_AXE.get());
        tag(TagsInit.ToolTagsInit.VIBRANIUM_SHOVEL_TAG).add(ItemInit.VIBRANIUM_SHOVEL.get());
        tag(TagsInit.ToolTagsInit.VIBRANIUM_HOE_TAG).add(ItemInit.VIBRANIUM_HOE.get());
        tag(TagsInit.ToolTagsInit.VIBRANIUM_SHIELD_TAG).add(ItemInit.VIBRANIUM_SHIELD.get());

        //Food
        tag(TagsInit.ItemTagsInit.HEART_SHAPED_HERB_TAG).add(ItemInit.HEART_SHAPED_HERB.get());

        //Armor
        tag(ItemTags.FOOT_ARMOR)
                .add(ItemInit.VIBRANIUM_BOOTS.get());

        tag(ItemTags.LEG_ARMOR)
                .add(ItemInit.VIBRANIUM_LEGGINGS.get());

        tag(ItemTags.CHEST_ARMOR)
                .add(ItemInit.VIBRANIUM_CHESTPLATE.get());

        tag(ItemTags.HEAD_ARMOR)
                .add(ItemInit.VIBRANIUM_HELMET.get());

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(   ItemInit.VIBRANIUM_BOOTS.get(),
                        ItemInit.VIBRANIUM_LEGGINGS.get(),
                        ItemInit.VIBRANIUM_CHESTPLATE.get(),
                        ItemInit.VIBRANIUM_HELMET.get());

        //Tools
        tag(ItemTags.SWORDS)
                .add(ItemInit.VIBRANIUM_SWORD.get());

        tag(ItemTags.PICKAXES)
                .add(ItemInit.VIBRANIUM_PICKAXE.get())
                .add(ItemInit.VIBRANIUM_MACE.get());

        tag(ItemTags.AXES)
                .add(ItemInit.VIBRANIUM_AXE.get())
                .add(ItemInit.VIBRANIUM_MACE.get());

        tag(ItemTags.SHOVELS)
                .add(ItemInit.VIBRANIUM_SHOVEL.get())
                .add(ItemInit.VIBRANIUM_MACE.get());

        tag(ItemTags.HOES)
                .add(ItemInit.VIBRANIUM_HOE.get());

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ItemInit.VIBRANIUM_SHIELD.get());

        //Vibranium Mace
        tag(ItemTags.MINING_ENCHANTABLE)
                .add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.MINING_LOOT_ENCHANTABLE)
                .add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.VANISHING_ENCHANTABLE)
                .add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ItemInit.VIBRANIUM_MACE.get());
    }
}