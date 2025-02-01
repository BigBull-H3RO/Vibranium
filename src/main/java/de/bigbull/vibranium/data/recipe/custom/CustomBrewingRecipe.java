package de.bigbull.vibranium.data.recipe.custom;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.TagsInit;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

import java.util.function.Predicate;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Vibranium.MODID)
public class CustomBrewingRecipe implements IBrewingRecipe {

    private final Predicate<ItemStack> inputPredicate;
    private final TagKey<Item> catalystTag;
    private final Supplier<? extends Item> outputItem;

    public CustomBrewingRecipe(Predicate<ItemStack> inputPredicate, TagKey<Item> catalystTag, Supplier<? extends Item> outputItem) {
        this.inputPredicate = inputPredicate;
        this.catalystTag = catalystTag;
        this.outputItem = outputItem;
    }

    @SubscribeEvent
    public static void onRegisterBrewingRecipe(RegisterBrewingRecipesEvent event) {
        var builder = event.getBuilder();
        builder.addRecipe(new CustomBrewingRecipe(
                input -> {
                    if (input.getItem() != Items.POTION) {
                        return false;
                    }
                    PotionContents contents = input.get(DataComponents.POTION_CONTENTS);
                    if (contents == null) {
                        return false;
                    }
                    return contents.is(Potions.AWKWARD);
                },
                TagsInit.Items.SOUL_HERB_MIXTURE_TAG,
                ItemInit.SOUL_HERB_ELIXIR
        ));

        builder.addRecipe(new CustomBrewingRecipe(
                input -> input.getItem() == ItemInit.SOUL_HERB_ELIXIR.get(),
                Tags.Items.DUSTS_REDSTONE,
                ItemInit.SOUL_HERB_ELIXIR_EXTENDED
        ));

        builder.addRecipe(new CustomBrewingRecipe(
                input -> input.getItem() == ItemInit.SOUL_HERB_ELIXIR.get(),
                Tags.Items.DUSTS_GLOWSTONE,
                ItemInit.SOUL_HERB_ELIXIR_ENHANCED
        ));
    }

    @Override
    public boolean isInput(ItemStack input) {
        return this.inputPredicate.test(input);
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return ingredient.is(this.catalystTag);
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        return this.isInput(input) && this.isIngredient(ingredient)
                ? new ItemStack(this.outputItem.get())
                : ItemStack.EMPTY;
    }
}