package de.bigbull.vibranium.data.recipe;

import com.google.common.collect.ImmutableList;
import de.bigbull.vibranium.init.BlockFamilyInit;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.TagsInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public static final ImmutableList<ItemLike> VIBRANIUM_SMELTABLES = ImmutableList.of(ItemInit.RAW_VIBRANIUM, BlockInit.DEPPSLATE_VIBRANIUM_ORE);

    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    @Override
    protected void buildRecipes() {
        generateBlockFamilies(FeatureFlagSet.of(FeatureFlags.VANILLA));

        planksFromLogs(BlockInit.SOULWOOD_PLANKS, TagsInit.Items.SOULWOOD_LOGS, 4);
        woodFromLogs(BlockInit.SOULWOOD_WOOD, BlockInit.SOULWOOD_LOG);
        woodenBoat(ItemInit.SOULWOOD_BOAT, BlockInit.SOULWOOD_PLANKS);
        chestBoat(ItemInit.SOULWOOD_CHEST_BOAT, ItemInit.SOULWOOD_BOAT);
        hangingSign(ItemInit.SOULWOOD_HANGING_SIGN, BlockInit.STRIPPED_SOULWOOD_LOG);
        copySmithingTemplate(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, ItemInit.VIBRANIUM_INGOT);

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

        vibraniumSmithing(Items.NETHERITE_SWORD, ItemInit.VIBRANIUM_SWORD.get());
        vibraniumSmithing(Items.NETHERITE_PICKAXE, ItemInit.VIBRANIUM_PICKAXE.get());
        vibraniumSmithing(Items.NETHERITE_AXE, ItemInit.VIBRANIUM_AXE.get());
        vibraniumSmithing(Items.NETHERITE_SHOVEL, ItemInit.VIBRANIUM_SHOVEL.get());
        vibraniumSmithing(Items.NETHERITE_HOE, ItemInit.VIBRANIUM_HOE.get());
        vibraniumSmithing(Items.NETHERITE_HELMET, ItemInit.VIBRANIUM_HELMET.get());
        vibraniumSmithing(Items.NETHERITE_CHESTPLATE, ItemInit.VIBRANIUM_CHESTPLATE.get());
        vibraniumSmithing(Items.NETHERITE_LEGGINGS, ItemInit.VIBRANIUM_LEGGINGS.get());
        vibraniumSmithing(Items.NETHERITE_BOOTS, ItemInit.VIBRANIUM_BOOTS.get());
        vibraniumSmithing(Items.WOLF_ARMOR, ItemInit.VIBRANIUM_WOLF_ARMOR.get());
        vibraniumSmithing(Items.DIAMOND_HORSE_ARMOR, ItemInit.VIBRANIUM_HORSE_ARMOR.get());

        oreSmelting(VIBRANIUM_SMELTABLES, RecipeCategory.MISC, ItemInit.VIBRANIUM_PLATE, 2.5F, 400, "vibranium_ingot");
        oreBlasting(VIBRANIUM_SMELTABLES, RecipeCategory.MISC, ItemInit.VIBRANIUM_PLATE, 2.5F, 150, "vibranium_ingot");
    }

    protected void generateBlockFamilies(FeatureFlagSet flags) {
        BlockFamilyInit.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach((family) -> generateRecipes(family, flags));
    }

    protected void vibraniumSmithing(Item item, Item outputItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(item),
                        this.tag(TagsInit.Items.VIBRANIUM_TOOL_MATERIALS),
                        RecipeCategory.TOOLS,
                        outputItem
                )
                .unlocks("has_vibranium_ingot", this.has(TagsInit.Items.VIBRANIUM_TOOL_MATERIALS))
                .save(this.output, getItemName(outputItem) + "_smithing");
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(packOutput, completableFuture);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);

        }

        @Override
        public String getName() {
            return "Vibranium Recipes";
        }
    }
}
