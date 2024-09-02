package de.bigbull.vibranium.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class MaterialsInit {

    public static final SimpleTier VIBRANIUM = new SimpleTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2500, 10.0f, 2.5f, 18, () -> Ingredient.of(ItemInit.VIBRANIUM_INGOT));
}
