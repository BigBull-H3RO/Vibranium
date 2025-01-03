package de.bigbull.vibranium.integration.jei;

//@JeiPlugin
//public class JEIPluginVibranium implements IModPlugin {
//
//    public static final ResourceLocation PLUGIN_ID = ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "jei_plugin");
//
//    @Override
//    public ResourceLocation getPluginUid() {
//        return PLUGIN_ID;
//    }
//
//    @Override
//    public void registerRecipes(IRecipeRegistration registration) {
//        IVanillaRecipeFactory factory = registration.getVanillaRecipeFactory();
//        registration.addRecipes(RecipeTypes.BREWING, List.of(
//                createBrewingRecipe(factory,
//                        TagsInit.ItemTagsInit.HEART_SHAPED_HERB_TAG,
//                        PotionContents.createItemStack(Items.POTION, Potions.AWKWARD),
//                        new ItemStack(ItemInit.SOUL_HERB_ELIXIR.get()),
//                        ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "vibranium_herb_elixir_brewing")),
//                createBrewingRecipe(factory,
//                        Tags.Items.DUSTS_REDSTONE,
//                        new ItemStack(ItemInit.SOUL_HERB_ELIXIR.get()),
//                        new ItemStack(ItemInit.SOUL_HERB_ELIXIR_EXTENDED.get()),
//                        ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "vibranium_extended_brewing")),
//                createBrewingRecipe(factory,
//                        Tags.Items.DUSTS_GLOWSTONE,
//                        new ItemStack(ItemInit.SOUL_HERB_ELIXIR.get()),
//                        new ItemStack(ItemInit.SOUL_HERB_ELIXIR_ENHANCED.get()),
//                        ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "vibranium_enhanced_brewing"))
//        ));
//
//        addItemDescriptions(registration);
//    }
//
//    private static IJeiBrewingRecipe createBrewingRecipe(IVanillaRecipeFactory factory, TagKey<Item> catalystTag, ItemStack inputPotion, ItemStack outputPotion, ResourceLocation uid) {
//        List<ItemStack> catalysts = new ArrayList<>();
//        for (var item : BuiltInRegistries.ITEM.getTagOrEmpty(catalystTag))
//        {
//            catalysts.add(new ItemStack(item));
//        }
//        return factory.createBrewingRecipe(
//                catalysts,
//                inputPotion,
//                outputPotion,
//                uid);
//    }
//
//    private void addItemDescriptions(IRecipeRegistration registration) {
//        registration.addItemStackInfo(
//                new ItemStack(ItemInit.HEART_SHAPED_HERB.get()),
//                Component.translatable("jei.vibranium.heart_shaped_herb.desc")
//        );
//
//        registration.addItemStackInfo(
//                new ItemStack(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()),
//                Component.translatable("jei.vibranium.vibranium_upgrade_smithing_template.desc")
//        );
//
//        registration.addItemStackInfo(
//                new ItemStack(BlockInit.ENRICHED_VIBRANIUM_DIRT.get()),
//                Component.translatable("jei.vibranium.enriched_vibranium_dirt.desc")
//        );
//
//        registration.addItemStackInfo(
//                new ItemStack(ItemInit.VIBRANIUM_CORE.get()),
//                Component.translatable("jei.vibranium.vibranium_core.desc")
//        );
//
//        registration.addItemStackInfo(
//                new ItemStack(BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get()),
//                Component.translatable("jei.vibranium.enriched_vibranium_farmland.desc")
//        );
//    }
//}