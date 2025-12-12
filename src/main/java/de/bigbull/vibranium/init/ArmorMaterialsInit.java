package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.EnumMap;

public class ArmorMaterialsInit {
    public static final ArmorMaterial VIBRANIUM_MATERIAL = new ArmorMaterial(42,
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.BOOTS, 3);
                map.put(ArmorType.LEGGINGS, 6);
                map.put(ArmorType.CHESTPLATE, 8);
                map.put(ArmorType.HELMET, 3);
                map.put(ArmorType.BODY, 28);
            }),
            20, SoundEvents.ARMOR_EQUIP_NETHERITE, 4, 0.2f, TagsInit.Items.VIBRANIUM_REPAIR,
            ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Vibranium.MODID, "vibranium")));

    public static final ArmorMaterial VIBRANIUM_MATERIAL_WOLF = new ArmorMaterial(16,
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.BODY, 16);
            }),
            15, SoundEvents.ARMOR_EQUIP_WOLF, 2, 0.0f, TagsInit.Items.VIBRANIUM_REPAIR,
            ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Vibranium.MODID, "vibranium")));

    public static final ArmorMaterial VIBRANIUM_MATERIAL_TURTLE = new ArmorMaterial(50,
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.HELMET, 3);
            }),
            12, SoundEvents.ARMOR_EQUIP_TURTLE, 2, 0.0f, TagsInit.Items.VIBRANIUM_REPAIR,
            ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Vibranium.MODID, "vibranium_turtle")));
}