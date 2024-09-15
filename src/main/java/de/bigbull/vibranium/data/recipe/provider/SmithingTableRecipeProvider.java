package de.bigbull.vibranium.data.recipe.provider;

import de.bigbull.vibranium.data.recipe.MainModRecipeProvider;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class SmithingTableRecipeProvider extends MainModRecipeProvider {
    private final RecipeOutput recipeOutput;

    public SmithingTableRecipeProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> registries, RecipeOutput recipeOutput) {
        super(generator, registries);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        //Vibranium Tools
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_SWORD),
                        Ingredient.of(ItemInit.VIBRANIUM_INGOT.get()),
                        RecipeCategory.TOOLS,
                        ItemInit.VIBRANIUM_SWORD.get())
                .unlocks("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_sword"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_PICKAXE),
                        Ingredient.of(ItemInit.VIBRANIUM_INGOT.get()),
                        RecipeCategory.TOOLS,
                        ItemInit.VIBRANIUM_PICKAXE.get())
                .unlocks("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_pickaxe"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_AXE),
                        Ingredient.of(ItemInit.VIBRANIUM_INGOT.get()),
                        RecipeCategory.TOOLS,
                        ItemInit.VIBRANIUM_AXE.get())
                .unlocks("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_axe"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_SHOVEL),
                        Ingredient.of(ItemInit.VIBRANIUM_INGOT.get()),
                        RecipeCategory.TOOLS,
                        ItemInit.VIBRANIUM_SHOVEL.get())
                .unlocks("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_shovel"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_HOE),
                        Ingredient.of(ItemInit.VIBRANIUM_INGOT.get()),
                        RecipeCategory.TOOLS,
                        ItemInit.VIBRANIUM_HOE.get())
                .unlocks("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_hoe"));

        //Vibranium Armor
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(ItemInit.VIBRANIUM_INGOT.get()),
                        RecipeCategory.TOOLS,
                        ItemInit.VIBRANIUM_HELMET.get())
                .unlocks("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_helmet"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(ItemInit.VIBRANIUM_INGOT.get()),
                        RecipeCategory.TOOLS,
                        ItemInit.VIBRANIUM_CHESTPLATE.get())
                .unlocks("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_chestplate"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(ItemInit.VIBRANIUM_INGOT.get()),
                        RecipeCategory.TOOLS,
                        ItemInit.VIBRANIUM_LEGGINGS.get())
                .unlocks("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_leggings"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(ItemInit.VIBRANIUM_INGOT.get()),
                        RecipeCategory.TOOLS,
                        ItemInit.VIBRANIUM_BOOTS.get())
                .unlocks("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_boots"));

        //Vibranium Wolf/Horse Armor
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Items.WOLF_ARMOR),
                        Ingredient.of(ItemInit.VIBRANIUM_INGOT.get()),
                        RecipeCategory.TOOLS,
                        ItemInit.VIBRANIUM_WOLF_ARMOR.get())
                .unlocks("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_wolf_armor"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
                        Ingredient.of(Items.DIAMOND_HORSE_ARMOR),
                        Ingredient.of(ItemInit.VIBRANIUM_INGOT.get()),
                        RecipeCategory.TOOLS,
                        ItemInit.VIBRANIUM_HORSE_ARMOR.get())
                .unlocks("has_item", has(ItemInit.VIBRANIUM_INGOT.get()))
                .save(this.recipeOutput, getModId("vibranium_horse_armor"));
    }
}
