package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.custom.item.HSHPotionItems;
import de.bigbull.vibranium.init.custom.item.VibraniumMaceItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
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
    public static final DeferredItem<Item> RAW_VIBRANIUM = ITEMS.registerItem("raw_vibranium", properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> VIBRANIUM_INGOT = ITEMS.registerItem("vibranium_ingot", properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> VIBRANIUM_NUGGET = ITEMS.registerItem("vibranium_nugget",
            properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> VIBRANIUM_PLATE = ITEMS.registerItem("vibranium_plate",
            properties -> new Item(properties.fireResistant()));

    public static final DeferredItem<Item> VIBRANIUM_CORE = ITEMS.registerItem("vibranium_core",
            properties -> new Item(properties.fireResistant().rarity(Rarity.RARE).stacksTo(1)));
    public static final DeferredItem<SmithingTemplateItem> VIBRANIUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("vibranium_upgrade_smithing_template", properties ->
            new SmithingTemplateItem(
                    VIBRANIUM_UPGRADE_APPLIES_TO,
                    VIBRANIUM_UPGRADE_INGREDIENTS,
                    VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION,
                    VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                    List.of(
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_armor_slot_helmet"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_slot_sword"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_armor_slot_chestplate"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_slot_pickaxe"),
                            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "item/empty_slot_wolf_armor"),
                            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "item/empty_slot_horse_armor"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_armor_slot_leggings"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_slot_axe"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_armor_slot_boots"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_slot_hoe"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_slot_shovel")
                    ),
                    List.of(
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_slot_ingot")
                    ),
                    properties.fireResistant().rarity(Rarity.UNCOMMON)
            ));

    public static final DeferredItem<SpawnEggItem> VIBRA_GOLEM_SPAWN_EGG = ITEMS.registerItem("vibra_golem_spawn_egg",
            properties -> new SpawnEggItem(Entitiesinit.VIBRAGOLEM.get(), properties));

    //Armor
    public static final DeferredItem<ArmorItem> VIBRANIUM_BOOTS = ITEMS.registerItem("vibranium_boots",
            properties -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorType.BOOTS,
                    properties.fireResistant().stacksTo(1)));
    public static final DeferredItem<ArmorItem> VIBRANIUM_LEGGINGS = ITEMS.registerItem("vibranium_leggings",
            properties -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorType.LEGGINGS,
                    properties.fireResistant().stacksTo(1)));
    public static final DeferredItem<ArmorItem> VIBRANIUM_CHESTPLATE = ITEMS.registerItem("vibranium_chestplate",
            properties -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorType.CHESTPLATE,
                    properties.fireResistant().stacksTo(1)));
    public static final DeferredItem<ArmorItem> VIBRANIUM_HELMET = ITEMS.registerItem("vibranium_helmet",
            properties -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorType.HELMET,
                    properties.fireResistant().stacksTo(1)));

    public static final DeferredItem<AnimalArmorItem> VIBRANIUM_WOLF_ARMOR = ITEMS.registerItem("vibranium_wolf_armor",
            properties -> new AnimalArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL_WOLF, AnimalArmorItem.BodyType.CANINE,
                    properties.fireResistant().stacksTo(1)));
    public static final DeferredItem<AnimalArmorItem> VIBRANIUM_HORSE_ARMOR = ITEMS.registerItem("vibranium_horse_armor",
            properties -> new AnimalArmorItem(ArmorMaterialsInit.VIBRANIUM_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                    properties.fireResistant().stacksTo(1)));

    //Tools
    public static final DeferredItem<SwordItem> VIBRANIUM_SWORD = ITEMS.registerItem("vibranium_sword", properties ->
            new SwordItem(MaterialsInit.VIBRANIUM, 3.0f, -2.2f, properties.fireResistant()));
    public static final DeferredItem<PickaxeItem> VIBRANIUM_PICKAXE = ITEMS.registerItem("vibranium_pickaxe", properties ->
            new PickaxeItem(MaterialsInit.VIBRANIUM, 0.0f, -2.8f, properties.fireResistant()));
    public static final DeferredItem<AxeItem> VIBRANIUM_AXE = ITEMS.registerItem("vibranium_axe", properties ->
            new AxeItem(MaterialsInit.VIBRANIUM, 5.0f, -3.0f, properties.fireResistant()));
    public static final DeferredItem<ShovelItem> VIBRANIUM_SHOVEL = ITEMS.registerItem("vibranium_shovel", properties ->
            new ShovelItem(MaterialsInit.VIBRANIUM, 0.5f, -3.0f, properties.fireResistant()));
    public static final DeferredItem<HoeItem> VIBRANIUM_HOE = ITEMS.registerItem("vibranium_hoe", properties ->
            new HoeItem(MaterialsInit.VIBRANIUM, -5.0f, 0.0f, properties.fireResistant()));
    public static final DeferredItem<VibraniumMaceItem> VIBRANIUM_MACE = ITEMS.registerItem("vibranium_mace", properties ->
            new VibraniumMaceItem(MaterialsInit.VIBRANIUM, BlockTags.MINEABLE_WITH_PICKAXE, 2.0f, -3.2f,
                    properties.fireResistant().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> VIBRANIUM_SHIELD = ITEMS.registerItem("vibranium_shield",
            properties ->  new ShieldItem(properties.fireResistant()
                    .stacksTo(1)
                    .durability(1024)
                    .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                    .repairable(TagsInit.Items.VIBRANIUM_REPAIR)
                    .equippableUnswappable(EquipmentSlot.OFFHAND)));

    //Advanced Items
    public static final DeferredItem<BlockItem> HEART_SHAPED_HERB = ITEMS.registerSimpleBlockItem("heart_shaped_herb", BlockInit.HEART_SHAPED_HERB_BUSH,
            new Item.Properties()
                    .food(FoodPropertiesInit.HEART_SHAPED_HERB, FoodPropertiesInit.HEART_SHAPED_HERB_EFFECT)
                    .rarity(Rarity.RARE)
                    .stacksTo(16));
    public static final DeferredItem<Item> SOUL_HERB_MIXTURE = ITEMS.registerItem("soul_herb_mixture",
            properties ->  new Item(properties.stacksTo(16)));

    public static final DeferredItem<Item> SOUL_HERB_ELIXIR = ITEMS.registerItem("soul_herb_elixir",
            properties ->  new HSHPotionItems(properties
                    .rarity(Rarity.RARE)
                    .component(DataComponents.CONSUMABLE, HSHPotionItems.SOUL_HERB_ELIXIR), 3600, 0));
    public static final DeferredItem<Item> SOUL_HERB_ELIXIR_EXTENDED = ITEMS.registerItem("soul_herb_elixir_extended",
            properties ->  new HSHPotionItems(properties
                    .rarity(Rarity.RARE)
                    .component(DataComponents.CONSUMABLE, HSHPotionItems.SOUL_HERB_ELIXIR_EXTENDED), 9600, 0));
    public static final DeferredItem<Item> SOUL_HERB_ELIXIR_ENHANCED = ITEMS.registerItem("soul_herb_elixir_enhanced",
            properties ->  new HSHPotionItems(properties
                    .rarity(Rarity.RARE)
                    .component(DataComponents.CONSUMABLE, HSHPotionItems.SOUL_HERB_ELIXIR_ENHANCED), 1800, 1));

    public static final DeferredItem<Item> VIBRANIUM_CRYSTAL_SHARD = ITEMS.registerItem("vibranium_crystal_shard",
            properties ->  new Item(properties.fireResistant().stacksTo(64)));

    public static final DeferredItem<Item> SOULWOOD_SIGN = ITEMS.registerItem("soulwood_sign",
            properties ->  new SignItem(BlockInit.SOULWOOD_SIGN.get(), BlockInit.SOULWOOD_WALL_SIGN.get(),
                    properties.stacksTo(16)));

    public static final DeferredItem<Item> SOULWOOD_HANGING_SIGN = ITEMS.registerItem("soulwood_hanging_sign",
            properties ->  new HangingSignItem(BlockInit.SOULWOOD_HANGING_SIGN.get(), BlockInit.SOULWOOD_WALL_HANGING_SIGN.get(),
                    properties.stacksTo(16)));

    public static final DeferredItem<Item> SOULWOOD_BOAT = ITEMS.registerItem("soulwood_boat",
            properties ->  new BoatItem(Entitiesinit.SOULWOOD_BOAT.get(), properties.stacksTo(1)));

    public static final DeferredItem<Item> SOULWOOD_CHEST_BOAT = ITEMS.registerItem("soulwood_chest_boat",
            properties ->  new BoatItem(Entitiesinit.SOULWOOD_CHEST_BOAT.get(), properties.stacksTo(1)));
}