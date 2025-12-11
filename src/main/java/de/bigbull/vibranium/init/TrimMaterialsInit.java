package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class TrimMaterialsInit {
    public static final ResourceKey<TrimMaterial> VIBRANIUM =
            ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(Vibranium.MODID, "vibranium"));
}
