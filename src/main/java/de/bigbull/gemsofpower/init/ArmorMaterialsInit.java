package de.bigbull.gemsofpower.init;

import de.bigbull.gemsofpower.main.ModInfo;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ArmorMaterialsInit {

    public static DeferredRegister<ArmorMaterial> MATERIAL = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, ModInfo.MODID);

    public static final Holder<ArmorMaterial> VIBRANIUM_ARMOR_MATERIAL = MATERIAL.register("vibranium", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }),
            18, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(TagsInit.VIRANMIUM_TAG),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "vibranium"))), 2, 0));
}