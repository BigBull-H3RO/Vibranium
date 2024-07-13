package de.bigbull.gemsofpower.init;

import de.bigbull.gemsofpower.main.ModInfo;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;


public class ItemInit {

    public static final Component VIBRANIUM_UPGRADE_APPLIES_TO = Component.translatable("item.vibraniumupgrademod.vibranium_upgrade_smithing_template.applies_to").withStyle(ChatFormatting.BLUE);
    public static final Component VIBRANIUM_UPGRADE_INGREDIENTS = Component.translatable("item.vibraniumupgrademod.vibranium_upgrade_smithing_template.ingredients").withStyle(ChatFormatting.BLUE);
    public static final Component VIBRANIUM_UPGRADE = Component.translatable("upgrade.vibranium_upgrade").withStyle(ChatFormatting.GRAY);
    public static final Component VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable("item.vibraniumupgrademod.vibranium_upgrade_smithing_template.base_slot_description").withStyle(ChatFormatting.WHITE);
    public static final Component VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable("item.vibraniumupgrademod.vibranium_upgrade_smithing_template.additions_slot_description").withStyle(ChatFormatting.WHITE);

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModInfo.MODID);

    //Items
    public static final DeferredItem<Item> RAW_VIBRANIUM = ITEMS.register("raw_vibranium",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> VIBRANIUM_INGOT = ITEMS.register("vibranium_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
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
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_armor_slot_leggings"),
                            ResourceLocation.fromNamespaceAndPath("minecraft","item/empty_slot_axe"),
                            ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_armor_slot_boots"),
                            ResourceLocation.fromNamespaceAndPath("minecraft","item/empty_slot_hoe"),
                            ResourceLocation.fromNamespaceAndPath("minecraft","item/empty_slot_shovel")),
                    List.of(ResourceLocation.fromNamespaceAndPath("minecraft", "item/empty_slot_ingot"))
            ));

    //Armor
    public static final DeferredItem<ArmorItem> VIBRANIUM_BOOTS = ITEMS.register("vibranium_boots",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant().stacksTo(1)));
    public static final DeferredItem<ArmorItem> VIBRANIUM_LEGGINGS = ITEMS.register("vibranium_leggings",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant().stacksTo(1)));
    public static final DeferredItem<ArmorItem> VIBRANIUM_CHESTPLATE = ITEMS.register("vibranium_chestplate",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant().stacksTo(1)));
    public static final DeferredItem<ArmorItem> VIBRANIUM_HELMET = ITEMS.register("vibranium_helmet",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().fireResistant().stacksTo(1)));

}
