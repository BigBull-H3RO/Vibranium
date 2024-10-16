package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.custom.block.EVDirtBlock;
import de.bigbull.vibranium.init.custom.block.EVFarmlandBlock;
import de.bigbull.vibranium.init.custom.block.EVTree;
import de.bigbull.vibranium.init.custom.block.HSHBushBlock;
import de.bigbull.vibranium.init.custom.block.tree.VibraniumLeavesBlock;
import de.bigbull.vibranium.init.custom.block.tree.VibraniumRotatedPillarBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockInit {
    public static DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Vibranium.MODID);

    public static final DeferredBlock<Block> BLOCK_OF_RAW_VIBRANIUM = registerBlock("block_of_raw_vibranium", () ->
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM)
                    .mapColor(MapColor.COLOR_BLUE)
                    .requiresCorrectToolForDrops()
                    .strength(6.0F, 1200.0F)));

    public static final DeferredBlock<Block> DEPPSLATE_VIBRANIUM_ORE = registerBlock("deepslate_vibranium_ore", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .strength(12.0F, 1200.0F)
                    .sound(SoundType.DEEPSLATE)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> Vibranium_Block = registerBlock("vibranium_block", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .strength(50.0F, 1200.0F)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> HEART_SHAPED_HERB_BUSH = registerBlock("heart_shaped_herb_bush", () ->
            new HSHBushBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .randomTicks()
                    .noCollission()
                    .sound(SoundType.SWEET_BERRY_BUSH)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> ENRICHED_VIBRANIUM_DIRT = registerBlock("enriched_vibranium_dirt", () ->
            new EVDirtBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .strength(0.8F)
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> ENRICHED_VIBRANIUM_FARMLAND = registerBlock("enriched_vibranium_farmland", () ->
            new EVFarmlandBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .randomTicks()
                    .strength(0.6F)
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> SOULWOOD_LOG = registerBlock("soulwood_log", () ->
            new VibraniumRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3.0F)));

    public static final DeferredBlock<Block> SOULWOOD_WOOD = registerBlock("soulwood_wood", () ->
            new VibraniumRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3.0F)));

    public static final DeferredBlock<Block> STRIPPED_SOULWOOD_LOG = registerBlock("stripped_soulwood_log", () ->
            new VibraniumRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3.0F)));

    public static final DeferredBlock<Block> STRIPPED_SOULWOOD_WOOD = registerBlock("stripped_soulwood_wood", () ->
            new VibraniumRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3.0F)));

    public static final DeferredBlock<Block> SOULWOOD_LEAVES = registerBlock("soulwood_leaves", () ->
            new VibraniumLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final DeferredBlock<Block> SOULWOOD_SAPLING = registerBlock("soulwood_sapling", () ->
            new SaplingBlock(EVTree.SOUL_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> SOULWOOD_PLANKS = registerBlock("soulwood_planks", () ->
            new VibraniumRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<StairBlock> SOULWOOD_STAIRS = registerBlock("soulwood_stairs", () ->
            new StairBlock(SOULWOOD_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS).strength(2.5F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<SlabBlock> SOULWOOD_SLAB = registerBlock("soulwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<PressurePlateBlock> SOULWOOD_PRESSURE_PLATE = registerBlock("soulwood_pressure_plate", () ->
            new PressurePlateBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<ButtonBlock> SOULWOOD_BUTTON = registerBlock("soulwood_button", () ->
            new ButtonBlock(BlockSetType.CHERRY, 20, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).strength(0.5F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<FenceBlock> SOULWOOD_FENCE = registerBlock("soulwood_fence", () ->
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<FenceGateBlock> SOULWOOD_FENCE_GATE = registerBlock("soulwood_fence_gate", () ->
            new FenceGateBlock(WoodType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<DoorBlock> SOULWOOD_DOOR = registerBlock("soulwood_door", () ->
            new DoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<TrapDoorBlock> SOULWOOD_TRAPDOOR = registerBlock("soulwood_trapdoor", () ->
            new TrapDoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        if (!name.equals("heart_shaped_herb_bush")) {
            ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        }
    }
}
