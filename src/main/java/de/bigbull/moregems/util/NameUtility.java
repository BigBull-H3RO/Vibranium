package de.bigbull.moregems.util;

import de.bigbull.moregems.main;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class NameUtility {

    public static String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(main.MODID + ":", "");
    }
}
