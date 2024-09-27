package de.bigbull.vibranium.data.texture;

import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
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
        normalBlock(BlockInit.Vibranium_Block.get());
        normalBlock(BlockInit.ENRICHED_VIBRANIUM_DIRT.get());

        farmlandBlock(BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get());
    }

    private void normalBlock(Block block) {
        ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockKey.getPath();
        simpleBlock(block, models().cubeAll(path, modLoc("block/" + path)));
        simpleBlockItem(block, models().getExistingFile(modLoc("block/" + path)));
    }

    private void farmlandBlock(Block block) {
        String blockName = BuiltInRegistries.BLOCK.getKey(block).getPath();

        ModelFile dryModel = models().withExistingParent(blockName, mcLoc("block/farmland"))
                .texture("dirt", modLoc("block/enriched_vibranium_dirt"))
                .texture("top", modLoc("block/enriched_vibranium_farmland"));

        ModelFile moistModel = models().withExistingParent(blockName + "_moist", mcLoc("block/farmland_moist"))
                .texture("dirt", modLoc("block/enriched_vibranium_dirt"))
                .texture("top", modLoc("block/enriched_vibranium_farmland_moist"));

        IntegerProperty moisture = FarmBlock.MOISTURE;
        getVariantBuilder(block).forAllStates(state -> {
            int moistureValue = state.getValue(moisture);
            return ConfiguredModel.builder()
                    .modelFile(moistureValue > 0 ? moistModel : dryModel)
                    .build();
        });
        simpleBlockItem(block, dryModel);
    }
}
