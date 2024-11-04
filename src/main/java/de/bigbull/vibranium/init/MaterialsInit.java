package de.bigbull.vibranium.init;

import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;


public class MaterialsInit {
    public static final SimpleTier VIBRANIUM = new SimpleTier(
            TagsInit.BlockTagsInit.INCORRECT_FOR_VIBRANIUM_TOOL,
            2512,
            10.0f,
            5.0f,
            18,
            () -> Ingredient.of(ItemInit.VIBRANIUM_INGOT));
}