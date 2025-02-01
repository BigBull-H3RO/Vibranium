package de.bigbull.vibranium.data.tag;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.TagsInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, ModBlockTagsProvider modBlockTagsProvider) {
        super(output, completableFuture, modBlockTagsProvider.contentsGetter(), Vibranium.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.FOOT_ARMOR).add(ItemInit.VIBRANIUM_BOOTS.get());
        tag(ItemTags.LEG_ARMOR).add(ItemInit.VIBRANIUM_LEGGINGS.get());
        tag(ItemTags.CHEST_ARMOR).add(ItemInit.VIBRANIUM_CHESTPLATE.get());
        tag(ItemTags.HEAD_ARMOR).add(ItemInit.VIBRANIUM_HELMET.get());
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ItemInit.VIBRANIUM_BOOTS.get())
                .add(ItemInit.VIBRANIUM_LEGGINGS.get())
                .add(ItemInit.VIBRANIUM_CHESTPLATE.get())
                .add(ItemInit.VIBRANIUM_HELMET.get());

        tag(ItemTags.SWORDS).add(ItemInit.VIBRANIUM_SWORD.get());
        tag(ItemTags.PICKAXES)
                .add(ItemInit.VIBRANIUM_PICKAXE.get())
                .add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.AXES)
                .add(ItemInit.VIBRANIUM_AXE.get())
                .add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.SHOVELS)
                .add(ItemInit.VIBRANIUM_SHOVEL.get())
                .add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.HOES).add(ItemInit.VIBRANIUM_HOE.get());

        tag(ItemTags.DURABILITY_ENCHANTABLE).add(ItemInit.VIBRANIUM_SHIELD.get());
        tag(ItemTags.WEAPON_ENCHANTABLE).addTag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE).addTag(ItemTags.SWORD_ENCHANTABLE)
                .add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.BREAKS_DECORATED_POTS).add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.MINING_ENCHANTABLE).add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.MINING_LOOT_ENCHANTABLE).add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.VANISHING_ENCHANTABLE).add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE).add(ItemInit.VIBRANIUM_MACE.get());
        tag(ItemTags.MACE_ENCHANTABLE).add(ItemInit.VIBRANIUM_MACE.get());

        tag(ItemTags.TRIM_MATERIALS).add(ItemInit.VIBRANIUM_CRYSTAL_SHARD.get());
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(ItemInit.VIBRANIUM_INGOT.get());
        tag(TagsInit.Items.VIBRANIUM_REPAIR).add(ItemInit.VIBRANIUM_INGOT.get());
        tag(TagsInit.Items.VIBRANIUM_TOOL_MATERIALS).add(ItemInit.VIBRANIUM_INGOT.get());
        tag(ItemTags.DYEABLE).add(ItemInit.VIBRANIUM_WOLF_ARMOR.get());
        tag(TagsInit.Items.SOUL_HERB_MIXTURE_TAG).add(ItemInit.SOUL_HERB_MIXTURE.get());
        tag(ItemTags.BOATS).add(ItemInit.SOULWOOD_BOAT.get());
        tag(ItemTags.CHEST_BOATS).add(ItemInit.SOULWOOD_CHEST_BOAT.get());

        //Block Tags
        tag(ItemTags.PLANKS).add(BlockInit.SOULWOOD_PLANKS.asItem());
        tag(ItemTags.SAPLINGS).add(BlockInit.SOULWOOD_SAPLING.asItem());
        tag(ItemTags.LOGS).add(BlockInit.SOULWOOD_LOG.asItem());
        tag(ItemTags.LEAVES).add(BlockInit.SOULWOOD_LEAVES.asItem());
        tag(ItemTags.DIRT).add(BlockInit.ENRICHED_VIBRANIUM_DIRT.asItem());
        tag(ItemTags.STAIRS).add(BlockInit.SOULWOOD_STAIRS.asItem());
        tag(ItemTags.SLABS).add(BlockInit.SOULWOOD_SLAB.asItem());
        tag(ItemTags.FENCES).add(BlockInit.SOULWOOD_FENCE.asItem());
        tag(ItemTags.FENCE_GATES).add(BlockInit.SOULWOOD_FENCE_GATE.asItem());
        tag(ItemTags.DOORS).add(BlockInit.SOULWOOD_DOOR.asItem());
        tag(ItemTags.TRAPDOORS).add(BlockInit.SOULWOOD_TRAPDOOR.asItem());
        tag(ItemTags.BUTTONS).add(BlockInit.SOULWOOD_BUTTON.asItem());

        tag(TagsInit.Items.SOULWOOD_LOGS)
                .add(BlockInit.SOULWOOD_LOG.asItem())
                .add(BlockInit.STRIPPED_SOULWOOD_LOG.asItem())
                .add(BlockInit.SOULWOOD_WOOD.asItem())
                .add(BlockInit.STRIPPED_SOULWOOD_WOOD.asItem());

        tag(ItemTags.NON_FLAMMABLE_WOOD)
                .add(BlockInit.SOULWOOD_LOG.asItem())
                .add(BlockInit.STRIPPED_SOULWOOD_LOG.asItem())
                .add(BlockInit.STRIPPED_SOULWOOD_WOOD.asItem())
                .add(BlockInit.SOULWOOD_WOOD.asItem())
                .add(BlockInit.SOULWOOD_PLANKS.asItem())
                .add(BlockInit.SOULWOOD_STAIRS.asItem())
                .add(BlockInit.SOULWOOD_SLAB.asItem())
                .add(BlockInit.SOULWOOD_FENCE.asItem())
                .add(BlockInit.SOULWOOD_FENCE_GATE.asItem())
                .add(BlockInit.SOULWOOD_PRESSURE_PLATE.asItem())
                .add(BlockInit.SOULWOOD_DOOR.asItem())
                .add(BlockInit.SOULWOOD_TRAPDOOR.asItem())
                .add(BlockInit.SOULWOOD_BUTTON.asItem())
                .add(BlockInit.SOULWOOD_SAPLING.asItem())
                .add(BlockInit.SOULWOOD_SIGN.asItem())
                .add(BlockInit.SOULWOOD_HANGING_SIGN.asItem());
    }
}