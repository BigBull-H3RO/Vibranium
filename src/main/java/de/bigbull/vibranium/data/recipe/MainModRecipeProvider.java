package de.bigbull.vibranium.data.recipe;

import de.bigbull.vibranium.data.recipe.provider.FurnaceRecipeProvider;
import de.bigbull.vibranium.data.recipe.provider.NormalCraftingTableRecipeProvider;
import de.bigbull.vibranium.data.recipe.provider.SmithingTableRecipeProvider;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class MainModRecipeProvider extends RecipeProvider {
    protected final DataGenerator generator;
    private final CompletableFuture<HolderLookup.Provider> registries;

    public MainModRecipeProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> registries) {
        super(generator.getPackOutput(), registries);
        this.generator = generator;
        this.registries = registries;
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        new NormalCraftingTableRecipeProvider(generator, registries, recipeOutput).build();
        new FurnaceRecipeProvider(generator, registries, recipeOutput).build();
        new SmithingTableRecipeProvider(generator, registries, recipeOutput).build();
    }

    public ResourceLocation getModId(String path) {
        return ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, path);
    }
}
