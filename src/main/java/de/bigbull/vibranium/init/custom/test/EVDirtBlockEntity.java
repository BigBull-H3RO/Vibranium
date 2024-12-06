package de.bigbull.vibranium.init.custom.test;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EVDirtBlockEntity extends BlockEntity {
    public EVDirtBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.ENRICHED_VIBRANIUM_DIRT_ENTITY_TYPES.get(), pos, state);
    }
}
