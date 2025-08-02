package de.bigbull.vibranium.integration;

import de.bigbull.vibranium.init.ItemInit;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.brewing.DefaultBrewingDisplay;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;

@me.shedaniel.rei.forge.REIPluginClient
public class REIPlugin implements REIClientPlugin {
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.add(new DefaultBrewingDisplay(
                EntryIngredients.of(PotionContents.createItemStack(Items.POTION, Potions.AWKWARD)),
                EntryIngredients.of(ItemInit.SOUL_HERB_MIXTURE.get()),
                EntryStacks.of(ItemInit.SOUL_HERB_ELIXIR.get().getDefaultInstance())
        ));

        registry.add(new DefaultBrewingDisplay(
                EntryIngredients.of(ItemInit.SOUL_HERB_ELIXIR.get()),
                EntryIngredients.of(Items.REDSTONE),
                EntryStacks.of(ItemInit.SOUL_HERB_ELIXIR_EXTENDED.get().getDefaultInstance())
        ));

        registry.add(new DefaultBrewingDisplay(
                EntryIngredients.of(ItemInit.SOUL_HERB_ELIXIR.get()),
                EntryIngredients.of(Items.GLOWSTONE_DUST),
                EntryStacks.of(ItemInit.SOUL_HERB_ELIXIR_ENHANCED.get().getDefaultInstance())
        ));
    }
}
