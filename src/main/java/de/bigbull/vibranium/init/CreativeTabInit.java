package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabInit {
    public static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Vibranium.MODID);

    public static String MAIN_TAB_ONE_TITLE = "main.tab.one";

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB_ONE = CREATIVE_MODE_TABS.register("main_tab_one", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplay, output) -> {
            output.accept(ItemInit.RAW_VIBRANIUM);
            output.accept(ItemInit.VIBRANIUM_INGOT);
            output.accept(ItemInit.VIBRANIUM_PLATE);
            output.accept(ItemInit.VIBRANIUM_NUGGET);
            output.accept(ItemInit.VIBRANIUM_CORE);
            output.accept(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE);
            output.accept(ItemInit.VIBRANIUM_BOOTS);
            output.accept(ItemInit.VIBRANIUM_LEGGINGS);
            output.accept(ItemInit.VIBRANIUM_CHESTPLATE);
            output.accept(ItemInit.VIBRANIUM_HELMET);
            output.accept(ItemInit.VIBRANIUM_TURTLE_HELMET);
            output.accept(ItemInit.VIBRANIUM_WOLF_ARMOR);
            output.accept(ItemInit.VIBRANIUM_HORSE_ARMOR);
            output.accept(ItemInit.VIBRANIUM_SWORD);
            output.accept(ItemInit.VIBRANIUM_PICKAXE);
            output.accept(ItemInit.VIBRANIUM_AXE);
            output.accept(ItemInit.VIBRANIUM_SHOVEL);
            output.accept(ItemInit.VIBRANIUM_HOE);
            output.accept(ItemInit.VIBRANIUM_MACE);
            output.accept(ItemInit.VIBRANIUM_SHIELD);
            output.accept(ItemInit.HEART_SHAPED_HERB);
            output.accept(ItemInit.SOUL_HERB_MIXTURE);
            output.accept(ItemInit.SOUL_HERB_ELIXIR);
            output.accept(ItemInit.SOUL_HERB_ELIXIR_EXTENDED);
            output.accept(ItemInit.SOUL_HERB_ELIXIR_ENHANCED);
            output.accept(ItemInit.VIBRANIUM_CRYSTAL_SHARD);
            output.accept(ItemInit.SOULWOOD_SIGN);
            output.accept(ItemInit.SOULWOOD_HANGING_SIGN);
            output.accept(ItemInit.SOULWOOD_BOAT);
            output.accept(ItemInit.SOULWOOD_CHEST_BOAT);
//            output.accept(ItemInit.VIBRA_GOLEM_SPAWN_EGG);

            output.accept(BlockInit.BLOCK_OF_RAW_VIBRANIUM);
            output.accept(BlockInit.DEPPSLATE_VIBRANIUM_ORE);
            output.accept(BlockInit.VIBRANIUM_BLOCK);
            output.accept(BlockInit.ENRICHED_VIBRANIUM_DIRT);
            output.accept(BlockInit.ENRICHED_VIBRANIUM_FARMLAND);
            output.accept(BlockInit.SOULWOOD_LOG);
            output.accept(BlockInit.SOULWOOD_WOOD);
            output.accept(BlockInit.STRIPPED_SOULWOOD_LOG);
            output.accept(BlockInit.STRIPPED_SOULWOOD_WOOD);
            output.accept(BlockInit.SOULWOOD_LEAVES);
            output.accept(BlockInit.SOULWOOD_SAPLING);
            output.accept(BlockInit.SOULWOOD_PLANKS);
            output.accept(BlockInit.SOULWOOD_STAIRS);
            output.accept(BlockInit.SOULWOOD_SLAB);
            output.accept(BlockInit.SOULWOOD_PRESSURE_PLATE);
            output.accept(BlockInit.SOULWOOD_BUTTON);
            output.accept(BlockInit.SOULWOOD_FENCE);
            output.accept(BlockInit.SOULWOOD_FENCE_GATE);
            output.accept(BlockInit.SOULWOOD_DOOR);
            output.accept(BlockInit.SOULWOOD_TRAPDOOR);
            output.accept(BlockInit.VIBRANIUM_CRYSTAL_BLOCK);
            output.accept(BlockInit.BUDDING_VIBRANIUM_CRYSTAL);
            output.accept(BlockInit.VIBRANIUM_CLUSTER);
            output.accept(BlockInit.LARGE_VIBRANIUM_BUD);
            output.accept(BlockInit.MEDIUM_VIBRANIUM_BUD);
            output.accept(BlockInit.SMALL_VIBRANIUM_BUD);
        });
        builder.icon(() -> new ItemStack(ItemInit.VIBRANIUM_MACE.get()));
        builder.title(Component.translatable(MAIN_TAB_ONE_TITLE));

        return builder.build();
    });
}