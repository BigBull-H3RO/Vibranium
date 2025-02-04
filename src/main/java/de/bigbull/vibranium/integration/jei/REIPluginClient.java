package de.bigbull.vibranium.integration.jei;

import de.bigbull.vibranium.init.ItemInit;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.brewing.BrewingRecipe;

@me.shedaniel.rei.forge.REIPluginClient
public class REIPluginClient implements REIClientPlugin {
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.add(new BrewingRecipe(
                Ingredient.of(Items.POTION),
                Ingredient.of(ItemInit.SOUL_HERB_MIXTURE.get()),
                ItemInit.SOUL_HERB_ELIXIR.get().getDefaultInstance()
        ));

        registry.add(new BrewingRecipe(
                Ingredient.of(ItemInit.SOUL_HERB_ELIXIR.get()),
                Ingredient.of(Items.REDSTONE),
                ItemInit.SOUL_HERB_ELIXIR_EXTENDED.get().getDefaultInstance()
        ));

        registry.add(new BrewingRecipe(
                Ingredient.of(ItemInit.SOUL_HERB_ELIXIR.get()),
                Ingredient.of(Items.GLOWSTONE_DUST),
                ItemInit.SOUL_HERB_ELIXIR_ENHANCED.get().getDefaultInstance()
        ));
    }
}
