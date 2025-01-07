package de.bigbull.vibranium.data.texture;

import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Vibranium.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        normalBlock(BlockInit.BLOCK_OF_RAW_VIBRANIUM.get());
        normalBlock((BlockInit.DEPPSLATE_VIBRANIUM_ORE.get()));
        normalBlock(BlockInit.VIBRANIUM_BLOCK.get());
        normalBlock(BlockInit.SOULWOOD_PLANKS.get());
        normalBlock(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get());
        normalBlock(BlockInit.BUDDING_VIBRANIUM_CRYSTAL.get());

        //Special Blocks
        saplingBlock(BlockInit.SOULWOOD_SAPLING.get());
        axisBlock(((RotatedPillarBlock) BlockInit.STRIPPED_SOULWOOD_LOG.get()), blockTexture(BlockInit.STRIPPED_SOULWOOD_LOG.get()),
                ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "block/stripped_soulwood_log_top"));
        axisBlock(((RotatedPillarBlock) BlockInit.STRIPPED_SOULWOOD_WOOD.get()), blockTexture(BlockInit.STRIPPED_SOULWOOD_LOG.get()),
                blockTexture(BlockInit.STRIPPED_SOULWOOD_LOG.get()));
        stairsBlock(BlockInit.SOULWOOD_STAIRS.get(), blockTexture(BlockInit.SOULWOOD_PLANKS.get()));
        slabBlock(BlockInit.SOULWOOD_SLAB.get(), blockTexture(BlockInit.SOULWOOD_PLANKS.get()), blockTexture(BlockInit.SOULWOOD_PLANKS.get()));
        buttonBlock(BlockInit.SOULWOOD_BUTTON.get(), blockTexture(BlockInit.SOULWOOD_PLANKS.get()));
        pressurePlateBlock(BlockInit.SOULWOOD_PRESSURE_PLATE.get(), blockTexture(BlockInit.SOULWOOD_PLANKS.get()));
        fenceBlock(BlockInit.SOULWOOD_FENCE.get(), blockTexture(BlockInit.SOULWOOD_PLANKS.get()));
        fenceGateBlock(BlockInit.SOULWOOD_FENCE_GATE.get(), blockTexture(BlockInit.SOULWOOD_PLANKS.get()));
        doorBlockWithRenderType(BlockInit.SOULWOOD_DOOR.get(), modLoc("block/soulwood_door_bottom"), modLoc("block/soulwood_door_top"), "cutout");
        trapdoorBlockWithRenderType(BlockInit.SOULWOOD_TRAPDOOR.get(), modLoc("block/soulwood_trapdoor"), true, "cutout");
        signBlock(BlockInit.SOULWOOD_SIGN.get(), BlockInit.SOULWOOD_WALL_SIGN.get(), blockTexture(BlockInit.SOULWOOD_PLANKS.get()));
        hangingSignBlock(BlockInit.SOULWOOD_HANGING_SIGN.get(), BlockInit.SOULWOOD_WALL_HANGING_SIGN.get(), blockTexture(BlockInit.SOULWOOD_PLANKS.get()));
        simpleBlock(BlockInit.POTTED_SOULWOOD_SAPLING.get(), models().withExistingParent(BlockInit.POTTED_SOULWOOD_SAPLING.getId().getPath(), mcLoc("flower_pot_cross"))
                .texture("plant", blockTexture(BlockInit.SOULWOOD_SAPLING.get())).renderType("cutout"));

        //Item Blocks
        blockItem(BlockInit.ENRICHED_VIBRANIUM_DIRT.get());
        blockItem(BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get());
        blockItem(BlockInit.SOULWOOD_LEAVES.get());
        blockItem(BlockInit.SOULWOOD_LOG.get());
        blockItem(BlockInit.SOULWOOD_WOOD.get());
        blockItem(BlockInit.STRIPPED_SOULWOOD_LOG.get());
        blockItem(BlockInit.STRIPPED_SOULWOOD_WOOD.get());
        blockItem(BlockInit.SOULWOOD_STAIRS.get());
        blockItem(BlockInit.SOULWOOD_FENCE_GATE.get());
        blockItem(BlockInit.SOULWOOD_PRESSURE_PLATE.get());
        blockItem(BlockInit.SOULWOOD_SLAB.get());
        blockItem(BlockInit.SOULWOOD_TRAPDOOR.get(), "_bottom");
    }

    private void normalBlock(Block block) {
        ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockKey.getPath();
        simpleBlock(block, models().cubeAll(path, modLoc("block/" + path)));
        simpleBlockItem(block, models().getExistingFile(modLoc("block/" + path)));
    }

    private void blockItem(Block block) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(Vibranium.MODID + ":block/" + BuiltInRegistries.BLOCK.getKey(block).getPath()));
    }

    private void blockItem(Block block, String appendix) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(Vibranium.MODID + ":block/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + appendix));
    }

    private void saplingBlock(Block block) {
        simpleBlock(block,
                models().cross(BuiltInRegistries.BLOCK.getKey(block).getPath(), blockTexture(block)).renderType("cutout"));
    }
}
