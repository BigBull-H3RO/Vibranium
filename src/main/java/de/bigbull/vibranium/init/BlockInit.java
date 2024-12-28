package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.custom.block.EVDirtBlock;
import de.bigbull.vibranium.init.custom.block.EVFarmlandBlock;
import de.bigbull.vibranium.init.custom.block.HSHBushBlock;
import de.bigbull.vibranium.init.custom.block.tree.EVTree;
import de.bigbull.vibranium.init.custom.block.tree.SWLeavesBlock;
import de.bigbull.vibranium.init.custom.block.tree.SoulTreeSaplingBlock;
import de.bigbull.vibranium.init.custom.block.tree.VibraniumRotatedPillarBlock;
import de.bigbull.vibranium.init.custom.block.vibraniumcrystal.BuddingVibraniumBlock;
import de.bigbull.vibranium.init.custom.block.vibraniumcrystal.VibraniumClusterBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class BlockInit {
    public static DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Vibranium.MODID);

    public static final DeferredBlock<Block> BLOCK_OF_RAW_VIBRANIUM = registerBlock("block_of_raw_vibranium",
            properties -> new Block(properties
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .mapColor(MapColor.COLOR_BLUE)
                    .requiresCorrectToolForDrops()
                    .strength(6.0F, 1200.0F)));

    public static final DeferredBlock<Block> DEPPSLATE_VIBRANIUM_ORE = registerBlock("deepslate_vibranium_ore",
            properties -> new Block(properties
                    .mapColor(MapColor.DEEPSLATE)
                    .strength(12.0F, 1200.0F)
                    .sound(SoundType.DEEPSLATE)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> VIBRANIUM_BLOCK = registerBlock("vibranium_block",
            properties -> new Block(properties
                    .mapColor(MapColor.COLOR_BLUE)
                    .strength(50.0F, 1200.0F)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> HEART_SHAPED_HERB_BUSH = BLOCKS.register("heart_shaped_herb_bush",
            () -> new HSHBushBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "heart_shaped_herb_bush")))
                    .mapColor(MapColor.PLANT)
                    .randomTicks()
                    .noCollission()
                    .sound(SoundType.SWEET_BERRY_BUSH)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<EVDirtBlock> ENRICHED_VIBRANIUM_DIRT = registerBlock("enriched_vibranium_dirt",
            properties -> new EVDirtBlock(properties
                    .mapColor(MapColor.DIRT)
                    .strength(0.6F)
                    .sound(SoundType.GRAVEL)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ENRICHED_VIBRANIUM_FARMLAND = registerBlock("enriched_vibranium_farmland",
            properties -> new EVFarmlandBlock(properties
                    .mapColor(MapColor.DIRT)
                    .randomTicks()
                    .strength(0.6F)
                    .sound(SoundType.GRAVEL)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SOULWOOD_LOG = registerBlock("soulwood_log",
            properties -> new VibraniumRotatedPillarBlock(properties
                    .mapColor(p_152624_ -> p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.WOOD : MapColor.PODZOL)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<Block> SOULWOOD_WOOD = registerBlock("soulwood_wood",
            properties -> new VibraniumRotatedPillarBlock(properties
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<Block> STRIPPED_SOULWOOD_LOG = registerBlock("stripped_soulwood_log",
            properties -> new VibraniumRotatedPillarBlock(properties
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<Block> STRIPPED_SOULWOOD_WOOD = registerBlock("stripped_soulwood_wood",
            properties -> new VibraniumRotatedPillarBlock(properties
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<Block> SOULWOOD_LEAVES = registerBlock("soulwood_leaves",
            properties -> new SWLeavesBlock(properties
                    .mapColor(MapColor.PLANT)
                    .strength(0.4F)
                    .randomTicks()
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating((state, reader, pos) -> false)
                    .isViewBlocking((state, reader, pos) -> false)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor((state, reader, pos) -> false)));

    public static final DeferredBlock<Block> SOULWOOD_SAPLING = registerBlock("soulwood_sapling",
            properties -> new SoulTreeSaplingBlock(EVTree.SOUL_TREE, properties
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> SOULWOOD_PLANKS = registerBlock("soulwood_planks",
            properties -> new VibraniumRotatedPillarBlock(properties
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F, 4.0F)
                    .sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<StairBlock> SOULWOOD_STAIRS = registerBlock("soulwood_stairs",
            properties -> new StairBlock(SOULWOOD_PLANKS.get().defaultBlockState(), properties));

    public static final DeferredBlock<SlabBlock> SOULWOOD_SLAB = registerBlock("soulwood_slab",
            properties -> new SlabBlock(properties
                    .mapColor(SOULWOOD_PLANKS.get().defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F, 3.5F)
                    .sound(SoundType.CHERRY_WOOD)));

    public static final DeferredBlock<PressurePlateBlock> SOULWOOD_PRESSURE_PLATE = registerBlock("soulwood_pressure_plate",
            properties -> new PressurePlateBlock(TypesInit.SOULWOOD_BLOCKSETTYPE, properties
                    .mapColor(SOULWOOD_PLANKS.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(0.8F)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<ButtonBlock> SOULWOOD_BUTTON = registerBlock("soulwood_button",
            properties -> new ButtonBlock(TypesInit.SOULWOOD_BLOCKSETTYPE, 30, properties
                    .noCollission().strength(0.8F).pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<FenceBlock> SOULWOOD_FENCE = registerBlock("soulwood_fence",
            properties -> new FenceBlock(properties
                    .mapColor(SOULWOOD_PLANKS.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F, 3.5F)));

    public static final DeferredBlock<FenceGateBlock> SOULWOOD_FENCE_GATE = registerBlock("soulwood_fence_gate",
            properties -> new FenceGateBlock(TypesInit.SOULWOOD_WOODTYPE, properties
                    .mapColor(SOULWOOD_PLANKS.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F, 3.5F)));

    public static final DeferredBlock<DoorBlock> SOULWOOD_DOOR = registerBlock("soulwood_door",
            properties -> new DoorBlock(TypesInit.SOULWOOD_BLOCKSETTYPE, properties
                    .mapColor(SOULWOOD_PLANKS.get().defaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.5F)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<TrapDoorBlock> SOULWOOD_TRAPDOOR = registerBlock("soulwood_trapdoor",
            properties -> new TrapDoorBlock(TypesInit.SOULWOOD_BLOCKSETTYPE, properties
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)));

    public static final DeferredBlock<StandingSignBlock> SOULWOOD_SIGN = BLOCKS.register("soulwood_sign",
            () -> new StandingSignBlock(TypesInit.SOULWOOD_WOODTYPE, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "soulwood_sign")))
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .strength(1.0F)
                    .noCollission()
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallSignBlock> SOULWOOD_WALL_SIGN = BLOCKS.register("soulwood_wall_sign",
            () -> new WallSignBlock(TypesInit.SOULWOOD_WOODTYPE, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "soulwood_wall_sign")))
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .strength(1.0F)
                    .noCollission()
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<CeilingHangingSignBlock> SOULWOOD_HANGING_SIGN = BLOCKS.register("soulwood_hanging_sign",
            () -> new CeilingHangingSignBlock(TypesInit.SOULWOOD_WOODTYPE, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "soulwood_hanging_sign")))
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .strength(1.0F)
                    .noCollission()
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallHangingSignBlock> SOULWOOD_WALL_HANGING_SIGN = BLOCKS.register("soulwood_wall_hanging_sign",
            () -> new WallHangingSignBlock(TypesInit.SOULWOOD_WOODTYPE, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "soulwood_wall_hanging_sign")))
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .strength(1.0F)
                    .noCollission()
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> VIBRANIUM_CRYSTAL_BLOCK = registerBlock("vibranium_crystal_block",
            properties ->  new AmethystBlock(properties
                            .mapColor(MapColor.COLOR_CYAN)
                            .strength(1.5F)
                            .sound(SoundType.AMETHYST)
                            .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BUDDING_VIBRANIUM_CRYSTAL = registerBlock("budding_vibranium_crystal",
            properties ->  new BuddingVibraniumBlock(properties
                            .mapColor(MapColor.COLOR_CYAN)
                            .randomTicks()
                            .strength(5f)
                            .sound(SoundType.AMETHYST)
                            .requiresCorrectToolForDrops()
                            .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> VIBRANIUM_CLUSTER = registerBlock("vibranium_cluster",
            properties ->  new VibraniumClusterBlock(
                    7.0F, 3.0F, properties
                            .mapColor(MapColor.COLOR_CYAN)
                            .forceSolidOn()
                            .noOcclusion()
                            .sound(SoundType.AMETHYST_CLUSTER)
                            .strength(5f)
                            .lightLevel(state -> 8)
                            .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> LARGE_VIBRANIUM_BUD = registerBlock("large_vibranium_bud",
            properties ->  new VibraniumClusterBlock(
                    5.0F, 3.0F, properties
                            .mapColor(MapColor.COLOR_PURPLE)
                            .forceSolidOn()
                            .noOcclusion()
                            .sound(SoundType.LARGE_AMETHYST_BUD)
                            .strength(1.5F)
                            .lightLevel(state -> 6)
                            .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> MEDIUM_VIBRANIUM_BUD = registerBlock("medium_vibranium_bud",
            properties ->  new VibraniumClusterBlock(
                    4.0F, 3.0F, properties
                            .mapColor(MapColor.COLOR_PURPLE)
                            .forceSolidOn()
                            .noOcclusion()
                            .sound(SoundType.MEDIUM_AMETHYST_BUD)
                            .strength(1.5F)
                            .lightLevel(state -> 4)
                            .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> SMALL_VIBRANIUM_BUD = registerBlock("small_vibranium_bud",
            properties ->  new VibraniumClusterBlock(
                    3.0F, 4.0F, properties
                            .mapColor(MapColor.COLOR_PURPLE)
                            .forceSolidOn()
                            .noOcclusion()
                            .sound(SoundType.SMALL_AMETHYST_BUD)
                            .strength(1.5F)
                            .lightLevel(state -> 2)
                            .pushReaction(PushReaction.DESTROY)));

    private static <B extends Block> DeferredBlock<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> func) {
        DeferredBlock<B> toReturn = BLOCKS.registerBlock(name, func);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().useBlockDescriptionPrefix()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, name)))));
    }
}