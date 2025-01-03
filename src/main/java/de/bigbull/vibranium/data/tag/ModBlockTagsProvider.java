package de.bigbull.vibranium.data.tag;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.TagsInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.Tags.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Vibranium.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //Blocks
        tag(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG).add(BlockInit.BLOCK_OF_RAW_VIBRANIUM.get());
        tag(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG).add(BlockInit.DEPPSLATE_VIBRANIUM_ORE.get());
        tag(TagsInit.BlockTagsInit.VIBRANIUM_BLOCK_TAG).add(BlockInit.VIBRANIUM_BLOCK.get());
        tag(TagsInit.BlockTagsInit.HEART_SHAPED_HERB_BUSH_TAG).add(BlockInit.HEART_SHAPED_HERB_BUSH.get());
        tag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_DIRT_TAG).add(BlockInit.ENRICHED_VIBRANIUM_DIRT.get());
        tag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_FARMLAND_TAG).add(BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_LOG_TAG).add(BlockInit.SOULWOOD_LOG.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_SAPLING_TAG).add(BlockInit.SOULWOOD_SAPLING.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_WOOD_TAG).add(BlockInit.SOULWOOD_WOOD.get());
        tag(TagsInit.BlockTagsInit.STRIPPED_SOULWOOD_LOG_TAG).add(BlockInit.STRIPPED_SOULWOOD_LOG.get());
        tag(TagsInit.BlockTagsInit.STRIPPED_SOULWOOD_WOOD_TAG).add(BlockInit.STRIPPED_SOULWOOD_WOOD.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_PLANKS_TAG).add(BlockInit.SOULWOOD_PLANKS.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_LEAVES_TAG).add(BlockInit.SOULWOOD_LEAVES.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_STAIRS_TAG).add(BlockInit.SOULWOOD_STAIRS.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_SLAB_TAG).add(BlockInit.SOULWOOD_SLAB.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_FENCE_TAG).add(BlockInit.SOULWOOD_FENCE.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_FENCE_GATE_TAG).add(BlockInit.SOULWOOD_FENCE_GATE.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_DOOR_TAG).add(BlockInit.SOULWOOD_DOOR.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_TRAPDOOR_TAG).add(BlockInit.SOULWOOD_TRAPDOOR.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_PRESSURE_PLATE_TAG).add(BlockInit.SOULWOOD_PRESSURE_PLATE.get());
        tag(TagsInit.BlockTagsInit.SOULWOOD_BUTTON_TAG).add(BlockInit.SOULWOOD_BUTTON.get());
        tag(TagsInit.BlockTagsInit.VIBRANIUM_CRYSTAL_BLOCK_TAG).add(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get());
        tag(TagsInit.BlockTagsInit.BUDDING_VIRBANIUM_CRYSTAL_TAG).add(BlockInit.BUDDING_VIBRANIUM_CRYSTAL.get());
        tag(TagsInit.BlockTagsInit.LARGE_VIBRANIUM_BUD_TAG).add(BlockInit.LARGE_VIBRANIUM_BUD.get());
        tag(TagsInit.BlockTagsInit.MEDIUM_VIBRANIUM_BUD_TAG).add(BlockInit.MEDIUM_VIBRANIUM_BUD.get());
        tag(TagsInit.BlockTagsInit.SMALL_VIBRANIUM_BUD_TAG).add(BlockInit.SMALL_VIBRANIUM_BUD.get());

        //Tags
        tag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(TagsInit.BlockTagsInit.INCORRECT_FOR_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_IRON_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_STONE_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.BlockTagsInit.NEEDS_VIBRANIUM_TOOL);

        tag(TagsInit.BlockTagsInit.SOULWOOD_LOGS)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_LOG_TAG)
                .addTag(TagsInit.BlockTagsInit.STRIPPED_SOULWOOD_LOG_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_WOOD_TAG)
                .addTag(TagsInit.BlockTagsInit.STRIPPED_SOULWOOD_WOOD_TAG);

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG)
                .addTag(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG)
                .addTag(TagsInit.BlockTagsInit.VIBRANIUM_BLOCK_TAG)
                .addTag(TagsInit.BlockTagsInit.VIBRANIUM_CRYSTAL_BLOCK_TAG)
                .addTag(TagsInit.BlockTagsInit.BUDDING_VIRBANIUM_CRYSTAL_TAG)
                .addTag(TagsInit.BlockTagsInit.LARGE_VIBRANIUM_BUD_TAG)
                .addTag(TagsInit.BlockTagsInit.MEDIUM_VIBRANIUM_BUD_TAG)
                .addTag(TagsInit.BlockTagsInit.SMALL_VIBRANIUM_BUD_TAG);

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_DIRT_TAG)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_FARMLAND_TAG);

        tag(Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(TagsInit.BlockTagsInit.BLOCK_OF_RAW_VIBRANIUM_TAG)
                .addTag(TagsInit.BlockTagsInit.DEEPSLATE_VIBRANIUM_ORE_TAG)
                .addTag(TagsInit.BlockTagsInit.VIBRANIUM_BLOCK_TAG);

        tag(BlockTags.MINEABLE_WITH_HOE)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_FARMLAND_TAG)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_DIRT_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_LEAVES_TAG);

        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_LOG_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_WOOD_TAG)
                .addTag(TagsInit.BlockTagsInit.STRIPPED_SOULWOOD_LOG_TAG)
                .addTag(TagsInit.BlockTagsInit.STRIPPED_SOULWOOD_WOOD_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_PLANKS_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_STAIRS_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_SLAB_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_FENCE_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_FENCE_GATE_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_DOOR_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_TRAPDOOR_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_BUTTON_TAG)
                .addTag(TagsInit.BlockTagsInit.SOULWOOD_PRESSURE_PLATE_TAG);

        tag(BlockTags.BIG_DRIPLEAF_PLACEABLE)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_DIRT_TAG)
                .addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_FARMLAND_TAG);

        tag(BlockTags.CRYSTAL_SOUND_BLOCKS)
                .addTag(TagsInit.BlockTagsInit.VIBRANIUM_CRYSTAL_BLOCK_TAG)
                .addTag(TagsInit.BlockTagsInit.BUDDING_VIRBANIUM_CRYSTAL_TAG);

        tag(BlockTags.BEACON_BASE_BLOCKS).addTag(TagsInit.BlockTagsInit.VIBRANIUM_BLOCK_TAG);
        tag(BlockTags.DIRT).addTag(TagsInit.BlockTagsInit.ENRICHED_VIBRANIUM_DIRT_TAG);
        tag(BlockTags.PLANKS).addTag(TagsInit.BlockTagsInit.SOULWOOD_PLANKS_TAG);
        tag(BlockTags.SAPLINGS).addTag(TagsInit.BlockTagsInit.SOULWOOD_SAPLING_TAG);
        tag(BlockTags.LOGS).addTag(TagsInit.BlockTagsInit.SOULWOOD_LOG_TAG);
        tag(BlockTags.LEAVES).addTag(TagsInit.BlockTagsInit.SOULWOOD_LEAVES_TAG);
        tag(BlockTags.FENCES).addTag(TagsInit.BlockTagsInit.SOULWOOD_FENCE_TAG);
        tag(BlockTags.FENCE_GATES).addTag(TagsInit.BlockTagsInit.SOULWOOD_FENCE_GATE_TAG);
        tag(BlockTags.DOORS).addTag(TagsInit.BlockTagsInit.SOULWOOD_DOOR_TAG);
        tag(BlockTags.TRAPDOORS).addTag(TagsInit.BlockTagsInit.SOULWOOD_TRAPDOOR_TAG);
        tag(BlockTags.BUTTONS).addTag(TagsInit.BlockTagsInit.SOULWOOD_BUTTON_TAG);
        tag(BlockTags.PRESSURE_PLATES).addTag(TagsInit.BlockTagsInit.SOULWOOD_PRESSURE_PLATE_TAG);
        tag(BlockTags.STAIRS).addTag(TagsInit.BlockTagsInit.SOULWOOD_STAIRS_TAG);
        tag(BlockTags.SLABS).addTag(TagsInit.BlockTagsInit.SOULWOOD_SLAB_TAG);

        tag(BlockTags.STANDING_SIGNS).add(BlockInit.SOULWOOD_SIGN.get());
        tag(BlockTags.WALL_SIGNS).add(BlockInit.SOULWOOD_WALL_SIGN.get());
        tag(BlockTags.CEILING_HANGING_SIGNS).add(BlockInit.SOULWOOD_HANGING_SIGN.get());
        tag(BlockTags.WALL_HANGING_SIGNS).add(BlockInit.SOULWOOD_WALL_HANGING_SIGN.get());
    }
}