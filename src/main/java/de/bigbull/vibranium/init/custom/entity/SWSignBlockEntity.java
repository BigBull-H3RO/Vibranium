package de.bigbull.vibranium.init.custom.entity;

import de.bigbull.vibranium.init.EntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SWSignBlockEntity extends SignBlockEntity {
    public SWSignBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return EntitiesInit.SOULWOOD_SIGN.get();
    }
}
