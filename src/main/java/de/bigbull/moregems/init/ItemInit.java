package de.bigbull.moregems.init;

import de.bigbull.moregems.main.ModInfo;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModInfo.MODID);

    public static final DeferredItem<Item> RAW_VIBRANIUM = ITEMS.register("raw_vibranium",
            () -> new Item(new Item.Properties().fireResistant()));
    public static DeferredItem<Item> VIBRANIUM_INGOT = ITEMS.register("vibranium_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static DeferredItem<Item> VIBRANIUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("vibranium_upgrade_smithing_template",
            () -> new Item(new Item.Properties().fireResistant()));

    //Armor
    public static final DeferredItem<ArmorItem> VIBRANIUM_BOOTS = ITEMS.register("vibranium_boots",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
    public static final DeferredItem<ArmorItem> VIBRANIUM_LEGGINGS = ITEMS.register("vibranium_leggings",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final DeferredItem<ArmorItem> VIBRANIUM_CHESTPLATE = ITEMS.register("vibranium_chestplate",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final DeferredItem<ArmorItem> VIBRANIUM_HELMET = ITEMS.register("vibranium_helmet",
            () -> new ArmorItem(ArmorMaterialsInit.VIBRANIUM_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));

}
