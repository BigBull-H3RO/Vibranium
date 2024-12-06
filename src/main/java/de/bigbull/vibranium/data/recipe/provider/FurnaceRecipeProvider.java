package de.bigbull.vibranium.data.recipe.provider;

import com.google.common.collect.ImmutableList;
import de.bigbull.vibranium.data.recipe.MainModRecipeProvider;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class FurnaceRecipeProvider extends MainModRecipeProvider {
    public static final ImmutableList<ItemLike> VIBRANIUM_SMELTABLES = ImmutableList.of(ItemInit.RAW_VIBRANIUM, BlockInit.DEPPSLATE_VIBRANIUM_ORE);
    private final RecipeOutput recipeOutput;

    public FurnaceRecipeProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> registries, RecipeOutput recipeOutput) {
        super(generator, registries);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        oreSmelting(recipeOutput, VIBRANIUM_SMELTABLES, RecipeCategory.MISC, ItemInit.VIBRANIUM_PLATE, 2.5F, 400, "vibranium_ingot");
        oreBlasting(recipeOutput, VIBRANIUM_SMELTABLES, RecipeCategory.MISC, ItemInit.VIBRANIUM_PLATE, 2.5F, 150, "vibranium_ingot");
    }
}
