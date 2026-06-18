package de.bigbull.vibranium.data.tag;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.TagsInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, ModBlockTagsProvider modBlockTagsProvider) {
        super(output, completableFuture, Vibranium.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.FOOT_ARMOR).add(ItemInit.VIBRANIUM_BOOTS.getKey());
        tag(ItemTags.LEG_ARMOR).add(ItemInit.VIBRANIUM_LEGGINGS.getKey());
        tag(ItemTags.CHEST_ARMOR).add(ItemInit.VIBRANIUM_CHESTPLATE.getKey());
        tag(ItemTags.HEAD_ARMOR).add(ItemInit.VIBRANIUM_HELMET.getKey()).add(ItemInit.VIBRANIUM_TURTLE_HELMET.getKey());
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ItemInit.VIBRANIUM_BOOTS.getKey())
                .add(ItemInit.VIBRANIUM_LEGGINGS.getKey())
                .add(ItemInit.VIBRANIUM_CHESTPLATE.getKey())
                .add(ItemInit.VIBRANIUM_HELMET.getKey());

        tag(ItemTags.SWORDS).add(ItemInit.VIBRANIUM_SWORD.getKey());
        tag(ItemTags.PICKAXES)
                .add(ItemInit.VIBRANIUM_PICKAXE.getKey())
                .add(ItemInit.VIBRANIUM_MACE.getKey());
        tag(ItemTags.AXES)
                .add(ItemInit.VIBRANIUM_AXE.getKey())
                .add(ItemInit.VIBRANIUM_MACE.getKey());
        tag(ItemTags.SHOVELS)
                .add(ItemInit.VIBRANIUM_SHOVEL.getKey())
                .add(ItemInit.VIBRANIUM_MACE.getKey());
        tag(ItemTags.HOES).add(ItemInit.VIBRANIUM_HOE.getKey());
        tag(ItemTags.SPEARS).add(ItemInit.VIBRANIUM_SPEAR.getKey());
        tag(ItemTags.MACE_ENCHANTABLE).add(ItemInit.VIBRANIUM_MACE.getKey());
        tag(ItemTags.BREAKS_DECORATED_POTS).add(ItemInit.VIBRANIUM_MACE.getKey());
        tag(ItemTags.WEAPON_ENCHANTABLE).addTag(ItemTags.SHARP_WEAPON_ENCHANTABLE).add(ItemInit.VIBRANIUM_MACE.getKey());
        tag(ItemTags.DURABILITY_ENCHANTABLE).add(ItemInit.VIBRANIUM_SHIELD.getKey()).add(ItemInit.VIBRANIUM_MACE.getKey());

        tag(ItemTags.TRIM_MATERIALS).add(ItemInit.VIBRANIUM_CRYSTAL_SHARD.getKey());
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(ItemInit.VIBRANIUM_INGOT.getKey());
        tag(TagsInit.Items.VIBRANIUM_REPAIR).add(ItemInit.VIBRANIUM_INGOT.getKey());
        tag(TagsInit.Items.VIBRANIUM_TOOL_MATERIALS).add(ItemInit.VIBRANIUM_INGOT.getKey());
        tag(TagsInit.Items.SOUL_HERB_MIXTURE_TAG).add(ItemInit.SOUL_HERB_MIXTURE.getKey());
        tag(ItemTags.BOATS).add(ItemInit.SOULWOOD_BOAT.getKey());
        tag(ItemTags.CHEST_BOATS).add(ItemInit.SOULWOOD_CHEST_BOAT.getKey());

        //NeoForge Item Tags
        tag(Tags.Items.ARMORS_NAUTILUS).add(ItemInit.VIBRANIUM_NAUTILUS_ARMOR.getKey());
        tag(Tags.Items.ARMORS_HORSE).add(ItemInit.VIBRANIUM_HORSE_ARMOR.getKey());
        tag(Tags.Items.ARMORS_WOLF).add(ItemInit.VIBRANIUM_WOLF_ARMOR.getKey());

        //Block Tags
        tag(ItemTags.PLANKS).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_PLANKS.getId()));
        tag(ItemTags.SAPLINGS).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_SAPLING.getId()));
        tag(ItemTags.LOGS).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_LOG.getId()));
        tag(ItemTags.LEAVES).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_LEAVES.getId()));
        tag(ItemTags.DIRT).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.ENRICHED_VIBRANIUM_DIRT.getId()));
        tag(ItemTags.WOODEN_STAIRS).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_STAIRS.getId()));
        tag(ItemTags.WOODEN_SLABS).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_SLAB.getId()));
        tag(ItemTags.WOODEN_FENCES).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_FENCE.getId()));
        tag(ItemTags.FENCE_GATES).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_FENCE_GATE.getId()));
        tag(ItemTags.WOODEN_DOORS).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_DOOR.getId()));
        tag(ItemTags.WOODEN_TRAPDOORS).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_TRAPDOOR.getId()));
        tag(ItemTags.WOODEN_BUTTONS).addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_BUTTON.getId()));

        tag(TagsInit.Items.SOULWOOD_LOGS)
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_LOG.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.STRIPPED_SOULWOOD_LOG.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_WOOD.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.STRIPPED_SOULWOOD_WOOD.getId()));

        tag(ItemTags.NON_FLAMMABLE_WOOD)
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_LOG.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.STRIPPED_SOULWOOD_LOG.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.STRIPPED_SOULWOOD_WOOD.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_WOOD.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_PLANKS.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_STAIRS.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_SLAB.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_FENCE.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_FENCE_GATE.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_PRESSURE_PLATE.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_DOOR.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_TRAPDOOR.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_BUTTON.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_SAPLING.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_SIGN.getId()))
                .addOptional(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM, BlockInit.SOULWOOD_HANGING_SIGN.getId()));
    }
}
