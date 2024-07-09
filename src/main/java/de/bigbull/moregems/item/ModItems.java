package de.bigbull.moregems.item;

import de.bigbull.moregems.main.Main;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Main.MODID);

    public static final DeferredItem<Item> VIBRANIUM = ITEMS.register("vibranium", () -> new Item(new Item.Properties().fireResistant()));

}
