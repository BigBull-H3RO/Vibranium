package de.bigbull.vibranium.init;

import de.bigbull.vibranium.main.Vibranium;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockInit {
    public static DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Vibranium.MODID);

    public static final DeferredBlock<Block> BLOCK_OF_RAW_VIBRANIUM = registerBlock("block_of_raw_vibranium", () ->
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .mapColor(MapColor.COLOR_BLUE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)));

    public static final DeferredBlock<Block> DEPPSLATE_VIBRANIUM_ORE = registerBlock("deepslate_vibranium_ore", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .destroyTime(3.0F)
                    .strength(30.0F, 1200.0F)
                    .sound(SoundType.DEEPSLATE)
                    .requiresCorrectToolForDrops()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().fireResistant()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
