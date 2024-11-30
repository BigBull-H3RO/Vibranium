package de.bigbull.vibranium.data.recipe.provider;

import de.bigbull.vibranium.data.recipe.MainModRecipeProvider;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.TagsInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class NormalCraftingTableRecipeProvider extends MainModRecipeProvider {

    public NormalCraftingTableRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public void build() {
        shapeless(RecipeCategory.MISC, ItemInit.RAW_VIBRANIUM.get(), 9)
                .requires(Ingredient.of(BlockInit.BLOCK_OF_RAW_VIBRANIUM), 1)
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM.get()))
                .save(this.output, "raw_vibranium_from_bock_of_raw_vibranium");

        shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_NUGGET.get(), 9)
                .requires(Ingredient.of(ItemInit.VIBRANIUM_INGOT), 1)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.output, "vibranium_nugget");

        shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT.get(), 9)
                .requires(Ingredient.of(BlockInit.VIBRANIUM_BLOCK), 1)
                .group("vibranium_ingot")
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM.get()))
                .save(this.output, "vibranium_ingot_from_vibranium_block");

        shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT.get(), 1)
                .requires(Ingredient.of(BlockInit.VIBRANIUM_BLOCK), 9)
                .group("vibranium_ingot")
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM.get()))
                .save(this.output, "vibranium_ingot_from_vibranium_nugget");

        shapeless(RecipeCategory.BUILDING_BLOCKS, BlockInit.BLOCK_OF_RAW_VIBRANIUM.get(), 1)
                .requires(Ingredient.of(ItemInit.RAW_VIBRANIUM), 9)
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM.get()))
                .save(this.output, "block_of_raw_vibranium");

        shapeless(RecipeCategory.BUILDING_BLOCKS, BlockInit.VIBRANIUM_BLOCK.get(), 1)
                .requires(Ingredient.of(ItemInit.VIBRANIUM_INGOT), 9)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.output, "vibranium_block");

        shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT.get(), 1)
                .requires(Ingredient.of(ItemInit.RAW_VIBRANIUM), 4)
                .requires(Ingredient.of(Items.NETHERITE_SCRAP), 1)
                .requires(Ingredient.of(Items.DIAMOND), 4)
                .group("vibranium_ingot")
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM.get()))
                .save(this.output, "vibranium_ingot");

        shaped(RecipeCategory.MISC, ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("#T#")
                .pattern("#V#")
                .pattern("###")
                .define('#', Items.DIAMOND)
                .define('V', ItemInit.VIBRANIUM_INGOT)
                .define('T', ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.output, "vibranium_upgrade_smithing_template");

        shaped(RecipeCategory.MISC, ItemInit.VIBRANIUM_CORE, 1)
                .pattern(" V ")
                .pattern("VEV")
                .pattern(" V ")
                .define('E', Items.EMERALD_BLOCK)
                .define('V', ItemInit.VIBRANIUM_INGOT)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.output, "vibranium_core");

        shaped(RecipeCategory.TOOLS, ItemInit.VIBRANIUM_MACE, 1)
                .pattern("VHV")
                .pattern(" R ")
                .pattern("   ")
                .define('H', Items.HEAVY_CORE)
                .define('R', Items.BREEZE_ROD)
                .define('V', BlockInit.VIBRANIUM_BLOCK)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.output, "vibranium_mace");

        shaped(RecipeCategory.TOOLS, ItemInit.VIBRANIUM_SHIELD, 1)
                .pattern("WVW")
                .pattern("WHW")
                .pattern(" W ")
                .define('H', Items.HEAVY_CORE)
                .define('W', ItemTags.PLANKS)
                .define('V', ItemInit.VIBRANIUM_INGOT)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.output, "vibranium_shield");

        this.planksFromLogs(BlockInit.SOULWOOD_PLANKS, TagsInit.Items.SOULWOOD_LOGS, 4);

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .define('#', BlockInit.SOULWOOD_LOG)
                .group("wood")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_LOG.get()))
                .save(this.output, "enriched_vibranium_wood");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.STRIPPED_SOULWOOD_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .define('#', BlockInit.STRIPPED_SOULWOOD_LOG)
                .group("wood")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_LOG.get()))
                .save(this.output, "stripped_enriched_vibranium_log");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("stairs")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS.get()))
                .save(this.output, "enriched_vibranium_stairs");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_SLAB, 6)
                .pattern("###")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("slab")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS.get()))
                .save(this.output, "enriched_vibranium_slab");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_FENCE, 3)
                .pattern("#W#")
                .pattern("#W#")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .define('W', Items.STICK)
                .group("fence")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS.get()))
                .save(this.output, "enriched_vibranium_fence");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_FENCE_GATE, 1)
                .pattern("#W#")
                .pattern("#W#")
                .define('W', BlockInit.SOULWOOD_PLANKS)
                .define('#', Items.STICK)
                .group("fence_gate")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS.get()))
                .save(this.output, "enriched_vibranium_fence_gate");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_DOOR, 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("door")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS.get()))
                .save(this.output, "enriched_vibranium_door");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_TRAPDOOR, 2)
                .pattern("###")
                .pattern("###")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("trapdoor")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS.get()))
                .save(this.output, "enriched_vibranium_trapdoor");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_BUTTON, 1)
                .pattern("#")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("button")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS.get()))
                .save(this.output, "enriched_vibranium_button");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_PRESSURE_PLATE, 1)
                .pattern("##")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("pressure_plate")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS.get()))
                .save(this.output, "enriched_vibranium_pressure_plate");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.VIBRANIUM_CRYSTAL_BLOCK, 1)
                .pattern("##")
                .pattern("##")
                .define('#', ItemInit.VIBRANIUM_CRYSTAL_SHARD)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_CRYSTAL_SHARD))
                .save(this.output, "vibranium_crystal_block");
    }
}
