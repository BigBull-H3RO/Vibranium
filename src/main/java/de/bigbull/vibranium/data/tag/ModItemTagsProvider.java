package de.bigbull.vibranium.data.tag;

import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.TagsInit;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
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
        copy(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_FARMLAND_TAG, TagsInit.ItemTagsInit.ENRICHED_VIBRANIUM_FARMLAND_TAG);

        //Items
        tag(TagsInit.ItemTagsInit.RAW_VIBRANIUM_TAG).add(ItemInit.RAW_VIBRANIUM.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_INGOT_TAG).add(ItemInit.VIBRANIUM_INGOT.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE_TAG).add(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get());
        tag(TagsInit.ItemTagsInit.VIBRA_GOLEM_SPAWN_EGG_TAG).add(ItemInit.VIBRA_GOLEM_SPAWN_EGG.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_CORE_TAG).add(ItemInit.VIBRANIUM_CORE.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_ENRICHED_HERB_ELIXIR_TAG).add(ItemInit.VIBRANIUM_ENRICHED_HERB_ELIXIR.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_ENRICHED_HERB_ELIXIR_EXTENDED_TAG).add(ItemInit.VIBRANIUM_ENRICHED_HERB_ELIXIR_EXTENDED.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_ENRICHED_HERB_ELIXIR_ENHANCED_TAG).add(ItemInit.VIBRANIUM_ENRICHED_HERB_ELIXIR_ENHANCED.get());

        //Armor
        tag(TagsInit.ItemTagsInit.VIBRANIUM_HELMET_TAG).add(ItemInit.VIBRANIUM_HELMET.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_CHESTPLATE_TAG).add(ItemInit.VIBRANIUM_CHESTPLATE.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_LEGGINGS_TAG).add(ItemInit.VIBRANIUM_LEGGINGS.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_BOOTS_TAG).add(ItemInit.VIBRANIUM_BOOTS.get());

        //ArmorAnimals
        tag(TagsInit.ItemTagsInit.VIBRANIUM_WOLF_ARMOR_TAG).add(ItemInit.VIBRANIUM_WOLF_ARMOR.get());
        tag(TagsInit.ItemTagsInit.VIBRANIUM_HORSE_ARMOR_TAG).add(ItemInit.VIBRANIUM_HORSE_ARMOR.get());

        //Tools
        tag(TagsInit.ToolTagsInit.VIBRANIUM_MACE_TAG).add(ItemInit.VIBRANIUM_MACE.get());
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
                .addTag(TagsInit.ItemTagsInit.VIBRANIUM_BOOTS_TAG);

        tag(ItemTags.LEG_ARMOR)
                .addTag(TagsInit.ItemTagsInit.VIBRANIUM_LEGGINGS_TAG);

        tag(ItemTags.CHEST_ARMOR)
                .addTag(TagsInit.ItemTagsInit.VIBRANIUM_CHESTPLATE_TAG);

        tag(ItemTags.HEAD_ARMOR)
                .addTag(TagsInit.ItemTagsInit.VIBRANIUM_HELMET_TAG);

        tag(ItemTags.TRIMMABLE_ARMOR)
                .addTag(TagsInit.ItemTagsInit.VIBRANIUM_BOOTS_TAG)
                .addTag(TagsInit.ItemTagsInit.VIBRANIUM_LEGGINGS_TAG)
                .addTag(TagsInit.ItemTagsInit.VIBRANIUM_CHESTPLATE_TAG)
                .addTag(TagsInit.ItemTagsInit.VIBRANIUM_HELMET_TAG);

        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .addTag(TagsInit.ItemTagsInit.VIBRANIUM_INGOT_TAG);

        //Tools
        tag(ItemTags.SWORDS)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_SWORD_TAG);

        tag(ItemTags.PICKAXES)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_PICKAXE_TAG)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_MACE_TAG);

        tag(ItemTags.AXES)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_AXE_TAG)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_MACE_TAG);

        tag(ItemTags.SHOVELS)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_SHOVEL_TAG)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_MACE_TAG);

        tag(ItemTags.HOES)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_HOE_TAG);

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_SHIELD_TAG);

        //Vibranium Mace
        tag(ItemTags.MINING_ENCHANTABLE)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_MACE_TAG);
        tag(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_MACE_TAG);
        tag(ItemTags.VANISHING_ENCHANTABLE)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_MACE_TAG);
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(TagsInit.ToolTagsInit.VIBRANIUM_MACE_TAG);
    }
}