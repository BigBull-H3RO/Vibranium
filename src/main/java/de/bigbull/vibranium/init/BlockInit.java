package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.custom.entity.SWHangingSignBlockEntity;
import de.bigbull.vibranium.init.custom.entity.SWSignBlockEntity;
import de.bigbull.vibranium.init.custom.block.EVDirtBlock;
import de.bigbull.vibranium.init.custom.block.EVFarmlandBlock;
import de.bigbull.vibranium.init.custom.block.HSHBushBlock;
import de.bigbull.vibranium.init.custom.block.tree.EVTree;
import de.bigbull.vibranium.init.custom.block.tree.SWLeavesBlock;
import de.bigbull.vibranium.init.custom.block.tree.SoulTreeSaplingBlock;
import de.bigbull.vibranium.init.custom.block.tree.VibraniumRotatedPillarBlock;
import de.bigbull.vibranium.init.custom.block.vibraniumcrystal.BuddingVibraniumBlock;
import de.bigbull.vibranium.init.custom.block.vibraniumcrystal.VibraniumClusterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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

    public static final DeferredBlock<Block> VIBRANIUM_BLOCK = registerBlock("vibranium_block", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .strength(50.0F, 1200.0F)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> HEART_SHAPED_HERB_BUSH = BLOCKS.register("heart_shaped_herb_bush", () ->
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
                    .sound(SoundType.GRAVEL)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ENRICHED_VIBRANIUM_FARMLAND = registerBlock("enriched_vibranium_farmland", () ->
            new EVFarmlandBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT)
                    .randomTicks()
                    .strength(0.6F)
                    .sound(SoundType.GRAVEL)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SOULWOOD_LOG = registerBlock("soulwood_log", () ->
            new VibraniumRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3.0F)));

    public static final DeferredBlock<Block> SOULWOOD_WOOD = registerBlock("soulwood_wood", () ->
            new VibraniumRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3.0F)));

    public static final DeferredBlock<Block> STRIPPED_SOULWOOD_LOG = registerBlock("stripped_soulwood_log", () ->
            new VibraniumRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3.0F)));

    public static final DeferredBlock<Block> STRIPPED_SOULWOOD_WOOD = registerBlock("stripped_soulwood_wood", () ->
            new VibraniumRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3.0F)));

    public static final DeferredBlock<Block> SOULWOOD_LEAVES = registerBlock("soulwood_leaves", () ->
            new SWLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).noOcclusion()));

    public static final DeferredBlock<Block> SOULWOOD_SAPLING = registerBlock("soulwood_sapling", () ->
            new SoulTreeSaplingBlock(EVTree.SOUL_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> SOULWOOD_PLANKS = registerBlock("soulwood_planks", () ->
            new VibraniumRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<StairBlock> SOULWOOD_STAIRS = registerBlock("soulwood_stairs", () ->
            new StairBlock(SOULWOOD_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS).strength(2.5F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<SlabBlock> SOULWOOD_SLAB = registerBlock("soulwood_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<PressurePlateBlock> SOULWOOD_PRESSURE_PLATE = registerBlock("soulwood_pressure_plate", () ->
            new PressurePlateBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<ButtonBlock> SOULWOOD_BUTTON = registerBlock("soulwood_button", () ->
            new ButtonBlock(BlockSetType.CHERRY, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).strength(0.5F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<FenceBlock> SOULWOOD_FENCE = registerBlock("soulwood_fence", () ->
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<FenceGateBlock> SOULWOOD_FENCE_GATE = registerBlock("soulwood_fence_gate", () ->
            new FenceGateBlock(WoodType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<DoorBlock> SOULWOOD_DOOR = registerBlock("soulwood_door", () ->
            new DoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<TrapDoorBlock> SOULWOOD_TRAPDOOR = registerBlock("soulwood_trapdoor", () ->
            new TrapDoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).strength(3.0F).sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<StandingSignBlock> SOULWOOD_SIGN = BLOCKS.register("soulwood_sign",
            () -> new StandingSignBlock(TypesInit.SOULWOOD_WOODTYPE, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .strength(1.0F)
                    .noCollission()){
            @Override
            public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
                return new SWSignBlockEntity(pPos, pState);
            }
    });

    public static final DeferredBlock<WallSignBlock> SOULWOOD_WALL_SIGN = BLOCKS.register("soulwood_wall_sign",
            () -> new WallSignBlock(TypesInit.SOULWOOD_WOODTYPE, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .strength(1.0F)
                    .noCollission()){
            @Override
            public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
            return new SWSignBlockEntity(pPos, pState);
            }
    });

    public static final DeferredBlock<CeilingHangingSignBlock> SOULWOOD_HANGING_SIGN = BLOCKS.register("soulwood_hanging_sign",
            () -> new CeilingHangingSignBlock(TypesInit.SOULWOOD_WOODTYPE, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .strength(1.0F)
                    .noCollission()) {
            @Override
            public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
                return new SWHangingSignBlockEntity(pPos, pState);
            }
    });

    public static final DeferredBlock<WallHangingSignBlock> SOULWOOD_WALL_HANGING_SIGN = BLOCKS.register("soulwood_wall_hanging_sign",
            () -> new WallHangingSignBlock(TypesInit.SOULWOOD_WOODTYPE, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .strength(1.0F)
                    .noCollission()) {
            @Override
            public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
                return new SWHangingSignBlockEntity(pPos, pState);
            }
    });

    public static final DeferredBlock<FlowerPotBlock> POTTED_SOULWOOD_SAPLING = BLOCKS.register("potted_soulwood_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SOULWOOD_SAPLING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING)));

    public static final DeferredBlock<Block> VIBRANIUM_CRYSTAL_BLOCK = registerBlock("vibranium_crystal_block",
            () -> new AmethystBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_CYAN)
                            .strength(1.5F)
                            .sound(SoundType.AMETHYST)
                            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BUDDING_VIBRANIUM_CRYSTAL = registerBlock("budding_vibranium_crystal",
            () -> new BuddingVibraniumBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_CYAN)
                            .randomTicks()
                            .strength(5f)
                            .sound(SoundType.AMETHYST)
                            .requiresCorrectToolForDrops()
                            .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> VIBRANIUM_CLUSTER = registerBlock("vibranium_cluster",
            () -> new VibraniumClusterBlock(
                    7.0F, 3.0F,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_CYAN)
                            .forceSolidOn()
                            .noOcclusion()
                            .sound(SoundType.AMETHYST_CLUSTER)
                            .strength(5f)
                            .lightLevel((state) -> 6)
                            .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> LARGE_VIBRANIUM_BUD = registerBlock("large_vibranium_bud",
            () -> new VibraniumClusterBlock(
                    5.0F, 3.0F,
                    BlockBehaviour.Properties.ofFullCopy(VIBRANIUM_CLUSTER.get())
                            .sound(SoundType.MEDIUM_AMETHYST_BUD)
                            .lightLevel((state) -> 5)
            ));

    public static final DeferredBlock<Block> MEDIUM_VIBRANIUM_BUD = registerBlock("medium_vibranium_bud",
            () -> new VibraniumClusterBlock(
                    4.0F, 3.0F,
                    BlockBehaviour.Properties.ofFullCopy(VIBRANIUM_CLUSTER.get())
                            .sound(SoundType.LARGE_AMETHYST_BUD)
                            .lightLevel((state) -> 3)
            ));

    public static final DeferredBlock<Block> SMALL_VIBRANIUM_BUD = registerBlock("small_vibranium_bud",
            () -> new VibraniumClusterBlock(
                    3.0F, 4.0F,
                    BlockBehaviour.Properties.ofFullCopy(VIBRANIUM_CLUSTER.get())
                            .sound(SoundType.SMALL_AMETHYST_BUD)
                            .lightLevel((state) -> 2)
            ));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
