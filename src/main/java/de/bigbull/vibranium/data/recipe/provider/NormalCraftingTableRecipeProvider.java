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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BlockInit.BLOCK_OF_RAW_VIBRANIUM.get(), 1)
                .requires(Ingredient.of(ItemInit.RAW_VIBRANIUM), 9)
                .unlockedBy("has_item", has(TagsInit.ItemTagsInit.RAW_VIBRANIUM_TAG))
                .save(this.recipeOutput, getModId("block_of_raw_vibranium"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT.get(), 1)
                .requires(Ingredient.of(ItemInit.RAW_VIBRANIUM), 4)
                .requires(Ingredient.of(Items.DIAMOND), 4)
                .unlockedBy("has_item", has(TagsInit.ItemTagsInit.RAW_VIBRANIUM_TAG))
                .save(this.recipeOutput, getModId("vibranium_ingot"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("#T#")
                .pattern("#V#")
                .pattern("###")
                .define('#', Items.DIAMOND)
                .define('V', ItemInit.VIBRANIUM_INGOT)
                .define('T', ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_upgrade_smithing_template"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.VIBRANIUM_CORE, 1)
                .pattern(" V ")
                .pattern("VEV")
                .pattern(" V ")
                .define('E', Items.EMERALD_BLOCK)
                .define('V', ItemInit.VIBRANIUM_INGOT)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_core"));
    }
}
