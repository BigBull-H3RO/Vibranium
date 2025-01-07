package de.bigbull.vibranium.data.recipe.provider;

import de.bigbull.vibranium.data.recipe.MainModRecipeProvider;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.TagsInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class NormalCraftingTableRecipeProvider extends MainModRecipeProvider {

    public NormalCraftingTableRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public void build() {
        shapeless(RecipeCategory.MISC, ItemInit.RAW_VIBRANIUM, 9)
                .requires(Ingredient.of(BlockInit.BLOCK_OF_RAW_VIBRANIUM), 1)
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                .save(this.output, "raw_vibranium_from_bock_of_raw_vibranium");

        shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_NUGGET, 9)
                .requires(Ingredient.of(ItemInit.VIBRANIUM_INGOT), 1)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.output, "vibranium_nugget");

        shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT, 9)
                .requires(Ingredient.of(BlockInit.VIBRANIUM_BLOCK), 1)
                .group("vibranium_ingot")
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                .save(this.output, "vibranium_ingot_from_vibranium_block");

        shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT, 1)
                .requires(Ingredient.of(ItemInit.VIBRANIUM_NUGGET), 9)
                .group("vibranium_ingot")
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                .save(this.output, "vibranium_ingot_from_vibranium_nugget");

        shapeless(RecipeCategory.BUILDING_BLOCKS, BlockInit.BLOCK_OF_RAW_VIBRANIUM, 1)
                .requires(Ingredient.of(ItemInit.RAW_VIBRANIUM), 9)
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                .save(this.output, "block_of_raw_vibranium");

        shapeless(RecipeCategory.BUILDING_BLOCKS, BlockInit.VIBRANIUM_BLOCK, 1)
                .requires(Ingredient.of(ItemInit.VIBRANIUM_INGOT), 9)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.output, "vibranium_block");

        shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT, 1)
                .requires(Ingredient.of(ItemInit.VIBRANIUM_PLATE), 4)
                .requires(Ingredient.of(Items.NETHERITE_SCRAP), 1)
                .requires(Ingredient.of(Items.DIAMOND), 4)
                .group("vibranium_ingot")
                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                .save(this.output, "vibranium_ingot");

        shaped(RecipeCategory.MISC, ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("#T#")
                .pattern("#V#")
                .pattern("###")
                .define('#', Items.DIAMOND)
                .define('V', ItemInit.VIBRANIUM_INGOT)
                .define('T', ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.output, "vibranium_upgrade_smithing_template");

        shaped(RecipeCategory.MISC, ItemInit.VIBRANIUM_CORE, 1)
                .pattern(" V ")
                .pattern("VEV")
                .pattern(" V ")
                .define('E', Items.EMERALD_BLOCK)
                .define('V', ItemInit.VIBRANIUM_INGOT)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.output, "vibranium_core");

        shaped(RecipeCategory.TOOLS, ItemInit.VIBRANIUM_MACE, 1)
                .pattern(" C ")
                .pattern("VMV")
                .pattern(" C ")
                .define('M', Items.MACE)
                .define('C', ItemInit.VIBRANIUM_CORE)
                .define('V', BlockInit.VIBRANIUM_BLOCK)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.output, "vibranium_mace");

        shaped(RecipeCategory.TOOLS, ItemInit.VIBRANIUM_SHIELD, 1)
                .pattern("WVW")
                .pattern("WHW")
                .pattern(" W ")
                .define('H', Items.HEAVY_CORE)
                .define('W', ItemTags.PLANKS)
                .define('V', ItemInit.VIBRANIUM_INGOT)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                .save(this.output, "vibranium_shield");

        shapeless(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_PLANKS, 4)
                .requires(TagsInit.Items.SOULWOOD_LOGS)
                .group("planks")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_LOG))
                .save(this.output, "enriched_vibranium_planks");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .define('#', BlockInit.SOULWOOD_LOG)
                .group("bark")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_LOG))
                .save(this.output, "enriched_vibranium_wood");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.STRIPPED_SOULWOOD_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .define('#', BlockInit.STRIPPED_SOULWOOD_LOG)
                .group("bark")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_LOG))
                .save(this.output, "stripped_enriched_vibranium_log");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_STAIRS, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("wooden_stairs")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.output, "enriched_vibranium_stairs");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.SOULWOOD_SLAB, 6)
                .pattern("###")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("wooden_slab")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.output, "enriched_vibranium_slab");

        shaped(RecipeCategory.MISC, BlockInit.SOULWOOD_FENCE, 3)
                .pattern("#W#")
                .pattern("#W#")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .define('W', Items.STICK)
                .group("wooden_fence")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.output, "enriched_vibranium_fence");

        shaped(RecipeCategory.REDSTONE, BlockInit.SOULWOOD_FENCE_GATE, 1)
                .pattern("#W#")
                .pattern("#W#")
                .define('W', BlockInit.SOULWOOD_PLANKS)
                .define('#', Items.STICK)
                .group("wooden_fence_gate")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.output, "enriched_vibranium_fence_gate");

        shaped(RecipeCategory.REDSTONE, BlockInit.SOULWOOD_DOOR, 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("wooden_door")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.output, "enriched_vibranium_door");

        shaped(RecipeCategory.REDSTONE, BlockInit.SOULWOOD_TRAPDOOR, 2)
                .pattern("###")
                .pattern("###")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("wooden_trapdoor")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.output, "enriched_vibranium_trapdoor");

        shapeless(RecipeCategory.REDSTONE, BlockInit.SOULWOOD_BUTTON, 1)
                .requires(BlockInit.SOULWOOD_PLANKS)
                .group("wooden_button")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.output, "enriched_vibranium_button");

        shaped(RecipeCategory.REDSTONE, BlockInit.SOULWOOD_PRESSURE_PLATE, 1)
                .pattern("##")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("wooden_pressure_plate")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.output, "enriched_vibranium_pressure_plate");

        shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.VIBRANIUM_CRYSTAL_BLOCK, 1)
                .pattern("##")
                .pattern("##")
                .define('#', ItemInit.VIBRANIUM_CRYSTAL_SHARD)
                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_CRYSTAL_SHARD))
                .save(this.output, "vibranium_crystal_block");

        shaped(RecipeCategory.MISC, ItemInit.SOUL_HERB_MIXTURE, 1)
                .pattern("#F ")
                .pattern("GC ")
                .define('#', ItemInit.HEART_SHAPED_HERB)
                .define('F', Items.FERMENTED_SPIDER_EYE)
                .define('C', Items.GOLDEN_CARROT)
                .define('G', Items.GHAST_TEAR)
                .unlockedBy("has_item", has(ItemInit.HEART_SHAPED_HERB))
                .save(this.output, "soul_herb_mixture");

        shaped(RecipeCategory.MISC, ItemInit.SOULWOOD_SIGN, 3)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .define('X', Items.STICK)
                .group("wooden_sign")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.output, "soulwood_sign");

        shaped(RecipeCategory.MISC, ItemInit.SOULWOOD_HANGING_SIGN, 6)
                .pattern("X X")
                .pattern("###")
                .pattern("###")
                .define('#', BlockInit.STRIPPED_SOULWOOD_LOG)
                .define('X', Items.CHAIN)
                .group("hanging_sign")
                .unlockedBy("has_item", has(BlockInit.SOULWOOD_PLANKS))
                .save(this.output, "soulwood_hanging_sign");

        shaped(RecipeCategory.TRANSPORTATION, ItemInit.SOULWOOD_BOAT, 1)
                .pattern("# #")
                .pattern("###")
                .define('#', BlockInit.SOULWOOD_PLANKS)
                .group("boat")
                .unlockedBy("in_water", insideOf(Blocks.WATER))
                .save(this.output, "soulwood_boat");

        shapeless(RecipeCategory.TRANSPORTATION, ItemInit.SOULWOOD_CHEST_BOAT, 1)
                .requires(Items.CHEST)
                .requires(ItemInit.SOULWOOD_BOAT)
                .group("chest_boat")
                .unlockedBy("has_boat", this.has(ItemTags.BOATS))
                .save(this.output, "soulwood_chest_boat");
    }
}
