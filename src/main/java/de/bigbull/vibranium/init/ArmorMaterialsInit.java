package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;

public class ArmorMaterialsInit {
    public static final ArmorMaterial VIBRANIUM_MATERIAL = new ArmorMaterial(42,
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.BOOTS, 3);
                map.put(ArmorType.LEGGINGS, 6);
                map.put(ArmorType.CHESTPLATE, 8);
                map.put(ArmorType.HELMET, 3);
                map.put(ArmorType.BODY, 16);
            }),
            20, SoundEvents.ARMOR_EQUIP_NETHERITE, 4, 0.2f, TagsInit.Items.VIBRANIUM_REPAIR,
            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "vibranium"));

    public static final ArmorMaterial VIBRANIUM_MATERIAL_WOLF = new ArmorMaterial(16,
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.BODY, 16);
            }),
            15, SoundEvents.ARMOR_EQUIP_WOLF, 3, 0.1f, TagsInit.Items.VIBRANIUM_REPAIR,
            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "vibranium"));
}