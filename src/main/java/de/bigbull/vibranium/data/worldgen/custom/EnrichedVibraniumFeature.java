package de.bigbull.vibranium.data.worldgen.custom;

import com.mojang.serialization.Codec;
import de.bigbull.vibranium.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class EnrichedVibraniumFeature extends Feature<NoneFeatureConfiguration> {

    public EnrichedVibraniumFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();

        if (canReplace(level.getBlockState(pos))) {
            level.setBlock(pos, BlockInit.ENRICHED_VIBRANIUM_DIRT.get().defaultBlockState(), 2);

            BlockPos abovePos = pos.above();
            if (level.isEmptyBlock(abovePos)) {
                level.setBlock(abovePos, BlockInit.HEART_SHAPED_HERB_BUSH.get().defaultBlockState(), 2);
                return true;
            }
        }
        return false;
    }

    private boolean canReplace(BlockState state) {
        return state.is(Blocks.STONE) || state.is(Blocks.DEEPSLATE);
    }
}