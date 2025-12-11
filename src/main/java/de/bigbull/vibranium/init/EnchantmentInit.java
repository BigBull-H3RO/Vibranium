package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;

public class EnchantmentInit {
    public static final DeferredHolder<Enchantment, Enchantment> UNIVERSAL_BREAKER = DeferredHolder.create(Registries.ENCHANTMENT, Identifier.fromNamespaceAndPath(Vibranium.MODID, "universal_breaker"));
}
