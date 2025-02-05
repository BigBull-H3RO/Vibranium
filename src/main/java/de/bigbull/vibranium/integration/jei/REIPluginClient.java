package de.bigbull.vibranium.integration.jei;

import de.bigbull.vibranium.init.ItemInit;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.common.displays.brewing.DefaultBrewingDisplay;
import net.minecraft.world.item.Items;

@me.shedaniel.rei.forge.REIPluginClient
public class REIPluginClient implements REIClientPlugin {
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.add(new DefaultBrewingDisplay(
                EntryIngredients.of(Items.POTION),
                EntryIngredients.of(ItemInit.SOUL_HERB_MIXTURE.get()),
                EntryIngredients.of(ItemInit.SOUL_HERB_ELIXIR.get().getDefaultInstance())
        ));

        registry.add(new DefaultBrewingDisplay(
                EntryIngredients.of(ItemInit.SOUL_HERB_ELIXIR.get()),
                EntryIngredients.of(Items.REDSTONE),
                EntryIngredients.of(ItemInit.SOUL_HERB_ELIXIR_EXTENDED.get().getDefaultInstance())
        ));

        registry.add(new DefaultBrewingDisplay(
                EntryIngredients.of(ItemInit.SOUL_HERB_ELIXIR.get()),
                EntryIngredients.of(Items.GLOWSTONE_DUST),
                EntryIngredients.of(ItemInit.SOUL_HERB_ELIXIR_ENHANCED.get().getDefaultInstance())
        ));
    }
}
