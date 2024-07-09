package de.bigbull.moregems.init;

import de.bigbull.moregems.main.Main;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Main.MODID);

    public static final DeferredItem<Item> RAW_VIBRANIUM = ITEMS.register("raw_vibranium", () -> new Item(new Item.Properties().fireResistant()));

}
