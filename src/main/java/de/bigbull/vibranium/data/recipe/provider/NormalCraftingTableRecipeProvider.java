package de.bigbull.vibranium.data.recipe.provider;

import de.bigbull.vibranium.data.recipe.MainModRecipeProvider;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.TagsInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class NormalCraftingTableRecipeProvider extends MainModRecipeProvider {
    private final RecipeOutput recipeOutput;

    public NormalCraftingTableRecipeProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> registries, RecipeOutput recipeOutput) {
        super(generator, registries);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.RAW_VIBRANIUM, 9)
                .requires(Ingredient.of(BlockInit.BLOCK_OF_RAW_VIBRANIUM), 1)
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                .save(this.recipeOutput, getModId("raw_vibranium_from_bock_of_raw_vibranium"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_NUGGET, 9)
                .requires(Ingredient.of(ItemInit.VIBRANIUM_INGOT), 1)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.recipeOutput, getModId("vibranium_nugget"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT, 9)
                .requires(Ingredient.of(BlockInit.VIBRANIUM_BLOCK), 1)
                .group("vibranium_ingot")
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                .save(this.recipeOutput, getModId("vibranium_ingot_from_vibranium_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT, 1)
                .requires(Ingredient.of(ItemInit.VIBRANIUM_NUGGET), 9)
                .group("vibranium_ingot")
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                .save(this.recipeOutput, getModId("vibranium_ingot_from_vibranium_nugget"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockInit.BLOCK_OF_RAW_VIBRANIUM, 1)
                .requires(Ingredient.of(ItemInit.RAW_VIBRANIUM), 9)
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                .save(this.recipeOutput, getModId("block_of_raw_vibranium"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockInit.VIBRANIUM_BLOCK, 1)
                .requires(Ingredient.of(ItemInit.VIBRANIUM_INGOT), 9)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.recipeOutput, getModId("vibranium_block"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT, 1)
                .requires(Ingredient.of(ItemInit.VIBRANIUM_PLATE), 4)
                .requires(Ingredient.of(Items.NETHERITE_SCRAP), 1)
                .requires(Ingredient.of(Items.DIAMOND), 4)
                .group("vibranium_ingot")
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                .save(this.recipeOutput, getModId("vibranium_ingot"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("#T#")
                .pattern("#V#")
                .pattern("###")
                .define('#', Items.DIAMOND)
                .define('V', ItemInit.VIBRANIUM_INGOT)
                .define('T', ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.recipeOutput, getModId("vibranium_upgrade_smithing_template"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.VIBRANIUM_CORE, 1)
                .pattern(" V ")
                .pattern("VEV")
                .pattern(" V ")
                .define('E', Items.EMERALD_BLOCK)
                .define('V', ItemInit.VIBRANIUM_INGOT)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.recipeOutput, getModId("vibranium_core"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemInit.VIBRANIUM_MACE, 1)
                .pattern(" C ")
                .pattern("VMV")
                .pattern(" C ")
                .define('M', Items.MACE)
                .define('C', ItemInit.VIBRANIUM_CORE)
                .define('V', BlockInit.VIBRANIUM_BLOCK)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.recipeOutput, getModId("vibranium_mace"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemInit.VIBRANIUM_SHIELD, 1)
                .pattern("WVW")
                .pattern("WHW")
                .pattern(" W ")
                .define('H', Items.HEAVY_CORE)
                .define('W', ItemTags.PLANKS)
                .define('V', ItemInit.VIBRANIUM_INGOT)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.recipeOutput, getModId("vibranium_shield"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_PLANKS, 4)
                .requires(Ingredient.of(TagsInit.ItemTagsInit.SOULWOOD_LOGS), 1)
                .group("planks")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_LOG))
                .save(this.recipeOutput, getModId("enriched_vibranium_log"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .define('#', BlockInit.SOULWOOD_LOG)
                .group("bark")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_LOG))
                .save(this.recipeOutput, getModId("enriched_vibranium_wood"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.STRIPPED_SOULWOOD_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .define('#', BlockInit.STRIPPED_SOULWOOD_LOG)
                .group("bark")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_LOG))
                .save(this.recipeOutput, getModId("stripped_enriched_vibranium_log"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("wooden_stairs")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.recipeOutput, getModId("enriched_vibranium_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_SLAB, 6)
                .pattern("###")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("wooden_slab")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.recipeOutput, getModId("enriched_vibranium_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.SOULWOOD_FENCE, 3)
                .pattern("#W#")
                .pattern("#W#")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .define('W', Items.STICK)
                .group("wooden_fence")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.recipeOutput, getModId("enriched_vibranium_fence"));

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, BlockInit.SOULWOOD_FENCE_GATE, 1)
                .pattern("#W#")
                .pattern("#W#")
                .define('W', BlockInit.SOULWOOD_PLANKS)
                .define('#', Items.STICK)
                .group("wooden_fence_gate")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.recipeOutput, getModId("enriched_vibranium_fence_gate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, BlockInit.SOULWOOD_DOOR, 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("wooden_door")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.recipeOutput, getModId("enriched_vibranium_door"));

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, BlockInit.SOULWOOD_TRAPDOOR, 2)
                .pattern("###")
                .pattern("###")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("wooden_trapdoor")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.recipeOutput, getModId("enriched_vibranium_trapdoor"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, BlockInit.SOULWOOD_BUTTON, 1)
                .requires(BlockInit.SOULWOOD_PLANKS, 1)
                .group("wooden_button")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.recipeOutput, getModId("enriched_vibranium_button"));

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, BlockInit.SOULWOOD_PRESSURE_PLATE, 1)
                .pattern("##")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("wooden_pressure_plate")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.recipeOutput, getModId("enriched_vibranium_pressure_plate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.VIBRANIUM_CRYSTAL_BLOCK, 1)
                .pattern("##")
                .pattern("##")
                .define('#', ItemInit.VIBRANIUM_CRYSTAL_SHARD)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_CRYSTAL_SHARD))
                .save(this.recipeOutput, getModId("vibranium_crystal_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.SOUL_HERB_MIXTURE, 1)
                .pattern("#F ")
                .pattern("GC ")
                .define('#', ItemInit.HEART_SHAPED_HERB)
                .define('F', Items.FERMENTED_SPIDER_EYE)
                .define('C', Items.GOLDEN_CARROT)
                .define('G', Items.GHAST_TEAR)
                .unlockedBy("has_item", has(ItemInit.HEART_SHAPED_HERB))
                .save(this.recipeOutput, getModId("soul_herb_mixture"));
    }
}
