package de.bigbull.vibranium.init;

import de.bigbull.vibranium.entity.MobEnities;
import de.bigbull.vibranium.init.custom.item.HSHPotionItem;
import de.bigbull.vibranium.init.custom.item.VibraniumMaceItem;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.custom.item.WolfArmorItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ItemInit {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Vibranium.MODID);

    public static final Component VIBRANIUM_UPGRADE_APPLIES_TO = Component.translatable("item.vibraniumupgrademod.vibranium_upgrade_smithing_template.applies_to").withStyle(ChatFormatting.BLUE);
    public static final Component VIBRANIUM_UPGRADE_INGREDIENTS = Component.translatable("item.vibraniumupgrademod.vibranium_upgrade_smithing_template.ingredients").withStyle(ChatFormatting.BLUE);
    public static final Component VIBRANIUM_UPGRADE = Component.translatable("upgrade.vibranium_upgrade").withStyle(ChatFormatting.GRAY);
    public static final Component VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable("item.vibraniumupgrademod.vibranium_upgrade_smithing_template.base_slot_description").withStyle(ChatFormatting.WHITE);
    public static final Component VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable("item.vibraniumupgrademod.vibranium_upgrade_smithing_template.additions_slot_description").withStyle(ChatFormatting.WHITE);

    //Items
    public static final DeferredItem<Item> RAW_VIBRANIUM = ITEMS.register("raw_vibranium",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> VIBRANIUM_INGOT = ITEMS.register("vibranium_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> VIBRANIUM_CORE = ITEMS.register("vibranium_core",
            () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<SmithingTemplateItem> VIBRANIUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("vibranium_upgrade_smithing_template",
            () -> new SmithingTemplateItem(
                    VIBRANIUM_UPGRADE_APPLIES_TO,
                    VIBRANIUM_UPGRADE_INGREDIENTS,
                    VIBRANIUM_UPGRADE,
                    VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION,
                    VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                    List.of(ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_armor_slot_helmet"),
                            ResourceLocation.fromNamespaceAndPath("minecraft","item/empty_slot_sword"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_armor_slot_chestplate"),
                            ResourceLocation.fromNamespaceAndPath("minecraft","item/empty_slot_pickaxe"),
                            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID,"item/empty_slot_wolf_armor"),
                            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID,"item/empty_slot_horse_armor"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_armor_slot_leggings"),
                            ResourceLocation.fromNamespaceAndPath("minecraft","item/empty_slot_axe"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_armor_slot_boots"),
                            ResourceLocation.fromNamespaceAndPath("minecraft","item/empty_slot_hoe"),
                            ResourceLocation.fromNamespaceAndPath("minecraft","item/empty_slot_shovel")),
                    List.of(ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_slot_ingot"))
            ));
    public static final DeferredItem<DeferredSpawnEggItem> VIBRA_GOLEM_SPAWN_EGG = ITEMS.register("vibra_golem_entity_spawn_egg",
            () -> new DeferredSpawnEggItem(MobEnities.VIBRAGOLEM, 0x28272B, 0x020250, new Item.Properties().stacksTo(16)));

    //Armor
    public static final DeferredItem<ArmorItem> VIBRANIUM_BOOTS = ITEMS.register("vibranium_boots",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().stacksTo(1).durability(650)));
    public static final DeferredItem<ArmorItem> VIBRANIUM_LEGGINGS = ITEMS.register("vibranium_leggings",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().stacksTo(1).durability(700)));
    public static final DeferredItem<ArmorItem> VIBRANIUM_CHESTPLATE = ITEMS.register("vibranium_chestplate",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().stacksTo(1).durability(750)));
    public static final DeferredItem<ArmorItem> VIBRANIUM_HELMET = ITEMS.register("vibranium_helmet",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().stacksTo(1).durability(550)));
    public static final DeferredItem<Item> VIBRANIUM_WOLF_ARMOR = ITEMS.register("vibranium_wolf_armor",
            () -> new WolfArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL, AnimalArmorItem.BodyType.CANINE, false,
                    new Item.Properties().fireResistant().stacksTo(1).durability(120)));
    public static final DeferredItem<Item> VIBRANIUM_HORSE_ARMOR = ITEMS.register("vibranium_horse_armor",
            () -> new AnimalArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN, false,
                    new Item.Properties().fireResistant().stacksTo(1).durability(100)));

    //Tools
    public static final DeferredItem<SwordItem> VIBRANIUM_SWORD = ITEMS.register("vibranium_sword", () ->
            new SwordItem(MaterialsInit.VIBRANIUM, new Item.Properties().fireResistant().attributes(SwordItem.createAttributes(
                    MaterialsInit.VIBRANIUM, 3.0f, -2.2f))));
    public static final DeferredItem<PickaxeItem> VIBRANIUM_PICKAXE = ITEMS.register("vibranium_pickaxe", () ->
            new PickaxeItem(MaterialsInit.VIBRANIUM, new Item.Properties().fireResistant().attributes(PickaxeItem.createAttributes(
                    MaterialsInit.VIBRANIUM, 0.0f, -2.8f))));
    public static final DeferredItem<AxeItem> VIBRANIUM_AXE = ITEMS.register("vibranium_axe", () ->
            new AxeItem(MaterialsInit.VIBRANIUM, new Item.Properties().fireResistant().attributes(AxeItem.createAttributes(
                    MaterialsInit.VIBRANIUM, 5.0f, -3.0f))));
    public static final DeferredItem<ShovelItem> VIBRANIUM_SHOVEL = ITEMS.register("vibranium_shovel", () ->
            new ShovelItem(MaterialsInit.VIBRANIUM, new Item.Properties().fireResistant().attributes(ShovelItem.createAttributes(
                    MaterialsInit.VIBRANIUM, 0.5f, -3.0f))));
    public static final DeferredItem<HoeItem> VIBRANIUM_HOE = ITEMS.register("vibranium_hoe", () ->
            new HoeItem(MaterialsInit.VIBRANIUM, new Item.Properties().fireResistant().attributes(HoeItem.createAttributes(
                    MaterialsInit.VIBRANIUM, -5.0f, 0.0f))));

    //Advanced Items
    public static final DeferredItem<VibraniumMaceItem> VIBRANIUM_MACE = ITEMS.register("vibranium_mace", () ->
            new VibraniumMaceItem(MaterialsInit.VIBRANIUM, BlockTags.MINEABLE_WITH_PICKAXE,
                    new Item.Properties().fireResistant().rarity(Rarity.EPIC).attributes(VibraniumMaceItem.createAttributes(
                            MaterialsInit.VIBRANIUM, 0.5f, -3.0f))));
    public static final DeferredItem<Item> VIBRANIUM_SHIELD = ITEMS.register("vibranium_shield",
            () -> new ShieldItem(new Item.Properties().fireResistant().stacksTo(1).durability(1024)));
    public static final DeferredItem<Item> HEART_SHAPED_HERB = ITEMS.register("heart_shaped_herb",
            () -> new ItemNameBlockItem(BlockInit.HEART_SHAPED_HERB_BUSH.get(), new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(0.5F)
                            .alwaysEdible()
                            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 400, 0, true, false), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 400, 0, true, false), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 200, 0, true, false), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 400, 0, true, false), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0, true, false), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.POISON, 200, 0, true, false), 1.0F)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 250, 0, true, false), 1.0F)
                            .build())
                    .stacksTo(16)
                    .rarity(Rarity.RARE)));
    public static final DeferredItem<Item> SOUL_HERB_ELIXIR = ITEMS.register("soul_herb_elixir",
            () -> new HSHPotionItem(new Item.Properties().stacksTo(1),3600, 0));
    public static final DeferredItem<Item> SOUL_HERB_ELIXIR_EXTENDED = ITEMS.register("soul_herb_elixir_extended",
            () -> new HSHPotionItem(new Item.Properties().stacksTo(1),9600, 0));
    public static final DeferredItem<Item> SOUL_HERB_ELIXIR_ENHANCED = ITEMS.register("soul_herb_elixir_enhanced",
            () -> new HSHPotionItem(new Item.Properties().stacksTo(1),1800, 1));
}