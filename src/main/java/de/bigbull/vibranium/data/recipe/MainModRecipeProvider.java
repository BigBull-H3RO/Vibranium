package de.bigbull.vibranium.data.recipe;

import de.bigbull.vibranium.data.recipe.provider.FurnaceRecipeProvider;
import de.bigbull.vibranium.data.recipe.provider.NormalCraftingTableRecipeProvider;
import de.bigbull.vibranium.data.recipe.provider.SmithingTableRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class MainModRecipeProvider extends RecipeProvider {

    public MainModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }


    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(packOutput, completableFuture);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new MainModRecipeProvider(provider, recipeOutput);

        }

        @Override
        public String getName() {
            return "My Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        new NormalCraftingTableRecipeProvider(registries, output).build();
        new FurnaceRecipeProvider(registries, output).build();
        new SmithingTableRecipeProvider(registries, output).build();
    }
}
