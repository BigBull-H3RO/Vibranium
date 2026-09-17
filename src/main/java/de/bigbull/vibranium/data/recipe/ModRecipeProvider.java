package de.bigbull.vibranium.data.recipe;

import com.google.common.collect.ImmutableList;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockFamilyInit;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.TagsInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.component.predicates.PotionsPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.BrewingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PotionIngredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.Optional;

public class ModRecipeProvider extends RecipeProvider {
        private static final List<ItemLike> VIBRANIUM_SMELTABLES = ImmutableList.of(
                        ItemInit.RAW_VIBRANIUM,
                        BlockInit.DEEPSLATE_VIBRANIUM_ORE);

        public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
                super(recipeOutput, advancementOutput);
        }

        private static ResourceKey<Recipe<?>> modLoc(String name) {
                return ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(Vibranium.MODID, name));
        }

        @Override
        protected void buildRecipes() {
                generateBlockFamilies(FeatureFlagSet.of(FeatureFlags.VANILLA));

                planksFromLogs(BlockInit.SOULWOOD_PLANKS, TagsInit.Items.SOULWOOD_LOGS, 4);
                woodFromLogs(BlockInit.SOULWOOD_WOOD, BlockInit.SOULWOOD_LOG);
                woodenBoat(ItemInit.SOULWOOD_BOAT, BlockInit.SOULWOOD_PLANKS);
                chestBoat(ItemInit.SOULWOOD_CHEST_BOAT, ItemInit.SOULWOOD_BOAT);
                copySmithingTemplate(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, ItemInit.VIBRANIUM_INGOT);

                shapeless(RecipeCategory.MISC, ItemInit.RAW_VIBRANIUM, 9)
                                .requires(Ingredient.of(BlockInit.BLOCK_OF_RAW_VIBRANIUM), 1)
                                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                                .save(this.output, modLoc("raw_vibranium_from_bock_of_raw_vibranium"));

                shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_NUGGET, 9)
                                .requires(Ingredient.of(ItemInit.VIBRANIUM_INGOT), 1)
                                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                                .save(this.output, modLoc("vibranium_nugget"));

                shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT, 9)
                                .requires(Ingredient.of(BlockInit.VIBRANIUM_BLOCK), 1)
                                .group("vibranium_ingot")
                                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                                .save(this.output, modLoc("vibranium_ingot_from_vibranium_block"));

                shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT, 1)
                                .requires(Ingredient.of(ItemInit.VIBRANIUM_NUGGET), 9)
                                .group("vibranium_ingot")
                                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                                .save(this.output, modLoc("vibranium_ingot_from_vibranium_nugget"));

                shapeless(RecipeCategory.BUILDING_BLOCKS, BlockInit.BLOCK_OF_RAW_VIBRANIUM, 1)
                                .requires(Ingredient.of(ItemInit.RAW_VIBRANIUM), 9)
                                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                                .save(this.output, modLoc("block_of_raw_vibranium"));

                shapeless(RecipeCategory.BUILDING_BLOCKS, BlockInit.VIBRANIUM_BLOCK, 1)
                                .requires(Ingredient.of(ItemInit.VIBRANIUM_INGOT), 9)
                                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                                .save(this.output, modLoc("vibranium_block"));

                shapeless(RecipeCategory.MISC, ItemInit.VIBRANIUM_INGOT, 1)
                                .requires(Ingredient.of(ItemInit.VIBRANIUM_PLATE), 4)
                                .requires(Ingredient.of(Items.NETHERITE_SCRAP), 1)
                                .requires(Ingredient.of(Items.DIAMOND), 4)
                                .group("vibranium_ingot")
                                .unlockedBy("has_item", has(ItemInit.RAW_VIBRANIUM))
                                .save(this.output, modLoc("vibranium_ingot"));

                shaped(RecipeCategory.MISC, ItemInit.VIBRANIUM_CORE, 1)
                                .pattern(" V ")
                                .pattern("VEV")
                                .pattern(" V ")
                                .define('E', Items.EMERALD_BLOCK)
                                .define('V', ItemInit.VIBRANIUM_INGOT)
                                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                                .save(this.output, modLoc("vibranium_core"));

                shaped(RecipeCategory.TOOLS, ItemInit.VIBRANIUM_MACE, 1)
                                .pattern(" C ")
                                .pattern("VMV")
                                .pattern(" C ")
                                .define('M', Items.MACE)
                                .define('C', ItemInit.VIBRANIUM_CORE)
                                .define('V', ItemInit.VIBRANIUM_INGOT)
                                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                                .save(this.output, modLoc("vibranium_mace"));

                shaped(RecipeCategory.TOOLS, ItemInit.VIBRANIUM_SHIELD, 1)
                                .pattern("WVW")
                                .pattern("WHW")
                                .pattern(" W ")
                                .define('H', ItemInit.VIBRANIUM_CORE)
                                .define('W', ItemTags.PLANKS)
                                .define('V', ItemInit.VIBRANIUM_INGOT)
                                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_INGOT))
                                .save(this.output, modLoc("vibranium_shield"));

                shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.VIBRANIUM_CRYSTAL_BLOCK, 1)
                                .pattern("##")
                                .pattern("##")
                                .define('#', ItemInit.VIBRANIUM_CRYSTAL_SHARD)
                                .unlockedBy("has_item", has(ItemInit.VIBRANIUM_CRYSTAL_SHARD))
                                .save(this.output, modLoc("vibranium_crystal_block"));

                shaped(RecipeCategory.MISC, ItemInit.SOUL_HERB_MIXTURE, 1)
                                .pattern("#F ")
                                .pattern("GC ")
                                .define('#', ItemInit.HEART_SHAPED_HERB)
                                .define('F', Items.FERMENTED_SPIDER_EYE)
                                .define('C', Items.GOLDEN_CARROT)
                                .define('G', Items.GHAST_TEAR)
                                .unlockedBy("has_item", has(ItemInit.HEART_SHAPED_HERB))
                                .save(this.output, modLoc("soul_herb_mixture"));

                vibraniumSmithing(Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ItemInit.VIBRANIUM_SWORD.get());
                vibraniumSmithing(Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, ItemInit.VIBRANIUM_PICKAXE.get());
                vibraniumSmithing(Items.NETHERITE_AXE, RecipeCategory.TOOLS, ItemInit.VIBRANIUM_AXE.get());
                vibraniumSmithing(Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, ItemInit.VIBRANIUM_SHOVEL.get());
                vibraniumSmithing(Items.NETHERITE_HOE, RecipeCategory.TOOLS, ItemInit.VIBRANIUM_HOE.get());
                vibraniumSmithing(Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ItemInit.VIBRANIUM_HELMET.get());
                vibraniumSmithing(Items.TURTLE_HELMET, RecipeCategory.COMBAT, ItemInit.VIBRANIUM_TURTLE_HELMET.get());
                vibraniumSmithing(Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT,
                                ItemInit.VIBRANIUM_CHESTPLATE.get());
                vibraniumSmithing(Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ItemInit.VIBRANIUM_LEGGINGS.get());
                vibraniumSmithing(Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ItemInit.VIBRANIUM_BOOTS.get());
                vibraniumSmithing(Items.WOLF_ARMOR, RecipeCategory.COMBAT, ItemInit.VIBRANIUM_WOLF_ARMOR.get());
                vibraniumSmithing(Items.NETHERITE_HORSE_ARMOR, RecipeCategory.COMBAT,
                                ItemInit.VIBRANIUM_HORSE_ARMOR.get());
                vibraniumSmithing(Items.NETHERITE_NAUTILUS_ARMOR, RecipeCategory.COMBAT,
                                ItemInit.VIBRANIUM_NAUTILUS_ARMOR.get());
                vibraniumSmithing(Items.NETHERITE_SPEAR, RecipeCategory.COMBAT, ItemInit.VIBRANIUM_SPEAR.get());

                modOreSmelting(VIBRANIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC,
                                ItemInit.VIBRANIUM_PLATE, 2.5F, 400, "vibranium_ingot");
                modOreBlasting(VIBRANIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC,
                                ItemInit.VIBRANIUM_PLATE, 2.5F, 150, "vibranium_ingot");

                addBrewingRecipe("soul_herb_elixir",
                                PotionIngredient.of(Items.POTION, PotionsPredicate.ofPotion(Potions.AWKWARD)),
                                new PotionIngredient(this.tag(TagsInit.Items.SOUL_HERB_MIXTURE_TAG), Optional.empty()),
                                ItemInit.SOUL_HERB_ELIXIR.get());
                addBrewingRecipe("soul_herb_elixir_extended",
                                PotionIngredient.of(ItemInit.SOUL_HERB_ELIXIR.get()),
                                new PotionIngredient(this.tag(Tags.Items.DUSTS_REDSTONE), Optional.empty()),
                                ItemInit.SOUL_HERB_ELIXIR_EXTENDED.get());
                addBrewingRecipe("soul_herb_elixir_enhanced",
                                PotionIngredient.of(ItemInit.SOUL_HERB_ELIXIR.get()),
                                new PotionIngredient(this.tag(Tags.Items.DUSTS_GLOWSTONE), Optional.empty()),
                                ItemInit.SOUL_HERB_ELIXIR_ENHANCED.get());
        }

        protected void addBrewingRecipe(String name, PotionIngredient input, PotionIngredient reagent, Item outputItem) {
                this.output.accept(
                                modLoc("brewing/" + name),
                                new BrewingRecipe(input, reagent, new ItemStackTemplate(outputItem)),
                                null);
        }

        protected void generateBlockFamilies(FeatureFlagSet flags) {
                BlockFamilyInit.getAllFamilies().filter(BlockFamily::shouldGenerateCraftingRecipe)
                                .forEach((family) -> generateRecipes(family, flags));
        }

        protected void vibraniumSmithing(Item item, RecipeCategory category, Item outputItem) {
                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.of(item),
                                this.tag(TagsInit.Items.VIBRANIUM_TOOL_MATERIALS),
                                category,
                                outputItem)
                                .unlocks("has_vibranium_ingot", this.has(TagsInit.Items.VIBRANIUM_TOOL_MATERIALS))
                                .save(this.output, modLoc(getItemName(outputItem) + "_smithing"));
        }

        protected void modOreSmelting(List<ItemLike> items, RecipeCategory category, CookingBookCategory bookCategory, ItemLike result, float experience, int cookingTime, String group) {
                modOreCooking(SmeltingRecipe::new, items, category, bookCategory, result, experience, cookingTime, group, "_from_smelting");
        }

        protected void modOreBlasting(List<ItemLike> items, RecipeCategory category, CookingBookCategory bookCategory, ItemLike result, float experience, int cookingTime, String group) {
                modOreCooking(BlastingRecipe::new, items, category, bookCategory, result, experience, cookingTime, group, "_from_blasting");
        }

        protected <T extends AbstractCookingRecipe> void modOreCooking(
                        AbstractCookingRecipe.Factory<T> factory,
                        List<ItemLike> items,
                        RecipeCategory category,
                        CookingBookCategory bookCategory,
                        ItemLike result,
                        float experience,
                        int cookingTime,
                        String group,
                        String suffix
        ) {
                for (ItemLike itemlike : items) {
                        SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, bookCategory, result, experience, cookingTime, factory)
                                        .group(group)
                                        .unlockedBy(getHasName(itemlike), this.has(itemlike))
                                        .save(this.output, modLoc(getItemName(result) + suffix + "_" + getItemName(itemlike)));
                }
        }
}
