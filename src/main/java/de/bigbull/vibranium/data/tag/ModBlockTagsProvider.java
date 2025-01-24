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

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Vibranium.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(TagsInit.Blocks.NEEDS_VIBRANIUM_TOOL);
        tag(TagsInit.Blocks.INCORRECT_FOR_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL).addTag(TagsInit.Blocks.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.Blocks.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.Blocks.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_IRON_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.Blocks.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_STONE_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.Blocks.NEEDS_VIBRANIUM_TOOL);
        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagsInit.Blocks.NEEDS_VIBRANIUM_TOOL);

        tag(TagsInit.Blocks.SOULWOOD_LOGS)
                .add(BlockInit.SOULWOOD_LOG.get())
                .add(BlockInit.STRIPPED_SOULWOOD_LOG.get())
                .add(BlockInit.SOULWOOD_WOOD.get())
                .add(BlockInit.STRIPPED_SOULWOOD_WOOD.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockInit.BLOCK_OF_RAW_VIBRANIUM.get())
                .add(BlockInit.DEPPSLATE_VIBRANIUM_ORE.get())
                .add(BlockInit.VIBRANIUM_BLOCK.get())
                .add(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get())
                .add(BlockInit.BUDDING_VIBRANIUM_CRYSTAL.get())
                .add(BlockInit.LARGE_VIBRANIUM_BUD.get())
                .add(BlockInit.MEDIUM_VIBRANIUM_BUD.get())
                .add(BlockInit.SMALL_VIBRANIUM_BUD.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(BlockInit.ENRICHED_VIBRANIUM_DIRT.get())
                .add(BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get());

        tag(Blocks.NEEDS_NETHERITE_TOOL)
                .add(BlockInit.BLOCK_OF_RAW_VIBRANIUM.get())
                .add(BlockInit.DEPPSLATE_VIBRANIUM_ORE.get())
                .add(BlockInit.VIBRANIUM_BLOCK.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get())
                .add(BlockInit.ENRICHED_VIBRANIUM_DIRT.get())
                .add(BlockInit.SOULWOOD_LEAVES.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(BlockInit.SOULWOOD_LOG.get())
                .add(BlockInit.SOULWOOD_WOOD.get())
                .add(BlockInit.STRIPPED_SOULWOOD_LOG.get())
                .add(BlockInit.STRIPPED_SOULWOOD_WOOD.get())
                .add(BlockInit.SOULWOOD_PLANKS.get())
                .add(BlockInit.SOULWOOD_STAIRS.get())
                .add(BlockInit.SOULWOOD_SLAB.get())
                .add(BlockInit.SOULWOOD_FENCE.get())
                .add(BlockInit.SOULWOOD_FENCE_GATE.get())
                .add(BlockInit.SOULWOOD_DOOR.get())
                .add(BlockInit.SOULWOOD_TRAPDOOR.get())
                .add(BlockInit.SOULWOOD_BUTTON.get())
                .add(BlockInit.SOULWOOD_PRESSURE_PLATE.get());

        tag(BlockTags.BIG_DRIPLEAF_PLACEABLE)
                .add(BlockInit.ENRICHED_VIBRANIUM_DIRT.get())
                .add(BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get());

        tag(BlockTags.CRYSTAL_SOUND_BLOCKS)
                .add(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get())
                .add(BlockInit.BUDDING_VIBRANIUM_CRYSTAL.get());

        tag(BlockTags.BEACON_BASE_BLOCKS).add(BlockInit.VIBRANIUM_BLOCK.get());
        tag(BlockTags.DIRT).add(BlockInit.ENRICHED_VIBRANIUM_DIRT.get());
        tag(BlockTags.PLANKS).add(BlockInit.SOULWOOD_PLANKS.get());
        tag(BlockTags.SAPLINGS).add(BlockInit.SOULWOOD_SAPLING.get());
        tag(BlockTags.LOGS).add(BlockInit.SOULWOOD_LOG.get());
        tag(BlockTags.LEAVES).add(BlockInit.SOULWOOD_LEAVES.get());
        tag(BlockTags.FENCES).add(BlockInit.SOULWOOD_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(BlockInit.SOULWOOD_FENCE_GATE.get());
        tag(BlockTags.DOORS).add(BlockInit.SOULWOOD_DOOR.get());
        tag(BlockTags.TRAPDOORS).add(BlockInit.SOULWOOD_TRAPDOOR.get());
        tag(BlockTags.BUTTONS).add(BlockInit.SOULWOOD_BUTTON.get());
        tag(BlockTags.PRESSURE_PLATES).add(BlockInit.SOULWOOD_PRESSURE_PLATE.get());
        tag(BlockTags.STAIRS).add(BlockInit.SOULWOOD_STAIRS.get());
        tag(BlockTags.SLABS).add(BlockInit.SOULWOOD_SLAB.get());
        tag(BlockTags.STANDING_SIGNS).add(BlockInit.SOULWOOD_SIGN.get());
        tag(BlockTags.WALL_SIGNS).add(BlockInit.SOULWOOD_WALL_SIGN.get());
        tag(BlockTags.CEILING_HANGING_SIGNS).add(BlockInit.SOULWOOD_HANGING_SIGN.get());
        tag(BlockTags.WALL_HANGING_SIGNS).add(BlockInit.SOULWOOD_WALL_HANGING_SIGN.get());
        tag(BlockTags.FLOWER_POTS).add(BlockInit.POTTED_SOULWOOD_SAPLING.get());
    }
}