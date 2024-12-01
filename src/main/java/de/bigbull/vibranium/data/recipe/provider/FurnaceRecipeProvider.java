package de.bigbull.vibranium.data.recipe.provider;

import com.google.common.collect.ImmutableList;
import de.bigbull.vibranium.data.recipe.MainModRecipeProvider;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.ItemLike;

public class FurnaceRecipeProvider extends MainModRecipeProvider {
    public static final ImmutableList<ItemLike> VIBRANIUM_SMELTABLES = ImmutableList.of(ItemInit.RAW_VIBRANIUM, BlockInit.DEPPSLATE_VIBRANIUM_ORE);

    public FurnaceRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public void build() {
        this.oreSmelting(VIBRANIUM_SMELTABLES, RecipeCategory.MISC, ItemInit.VIBRANIUM_PLATE, 2.5F, 400, "vibranium_ingot");
        this.oreBlasting(VIBRANIUM_SMELTABLES, RecipeCategory.MISC, ItemInit.VIBRANIUM_PLATE, 2.5F, 150, "vibranium_ingot");
    }
}
