package de.bigbull.vibranium.init;

import de.bigbull.vibranium.main.ModInfo;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashSet;
import java.util.Set;

public class CreativeTabInit {
    public static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModInfo.MODID);

    public static String MAIN_TAB_ONE_TITLE = "main.tab.one";

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB_ONE = CREATIVE_MODE_TABS.register("main_tab_one", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplay, output) -> {
            Set<Item> addedItems = new HashSet<>();

            ItemInit.ITEMS.getEntries()
                    .stream()
                    .map((item) -> item.get().asItem())
                    .filter(addedItems::add)
                    .forEach(output::accept);

            BlockInit.BLOCKS.getEntries()
                    .stream()
                    .map((block) -> block.get().asItem())
                    .filter(addedItems::add)
                    .forEach(output::accept);

        });

        builder.icon(() -> new ItemStack(ItemInit.RAW_VIBRANIUM.get()));
        builder.title(Component.translatable(MAIN_TAB_ONE_TITLE));

        return builder.build();
    });
}