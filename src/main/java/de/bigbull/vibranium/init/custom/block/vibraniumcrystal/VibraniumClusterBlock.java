package de.bigbull.vibranium.init.custom.block.vibraniumcrystal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class VibraniumClusterBlock extends AmethystBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<VibraniumClusterBlock> CODEC = RecordCodecBuilder.mapCodec(
            p_367977_ -> p_367977_.group(
                            Codec.FLOAT.fieldOf("height").forGetter(p_304411_ -> p_304411_.height),
                            Codec.FLOAT.fieldOf("aabb_offset").forGetter(p_304908_ -> p_304908_.aabbOffset),
                            propertiesCodec()
                    )
                    .apply(p_367977_, VibraniumClusterBlock::new)
    );
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;
    private final float height;
    private final float aabbOffset;
    protected final VoxelShape northAabb;
    protected final VoxelShape southAabb;
    protected final VoxelShape eastAabb;
    protected final VoxelShape westAabb;
    protected final VoxelShape upAabb;
    protected final VoxelShape downAabb;

    @Override
    public MapCodec<VibraniumClusterBlock> codec() {
        return CODEC;
    }

    public VibraniumClusterBlock(float height, float aabbOffset, Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(FACING, Direction.UP));
        this.upAabb = Block.box((double)aabbOffset, 0.0, (double)aabbOffset, (double)(16.0F - aabbOffset), (double)height, (double)(16.0F - aabbOffset));
        this.downAabb = Block.box((double)aabbOffset, (double)(16.0F - height), (double)aabbOffset, (double)(16.0F - aabbOffset), 16.0, (double)(16.0F - aabbOffset));
        this.northAabb = Block.box((double)aabbOffset, (double)aabbOffset, (double)(16.0F - height), (double)(16.0F - aabbOffset), (double)(16.0F - aabbOffset), 16.0);
        this.southAabb = Block.box((double)aabbOffset, (double)aabbOffset, 0.0, (double)(16.0F - aabbOffset), (double)(16.0F - aabbOffset), (double)height);
        this.eastAabb = Block.box(0.0, (double)aabbOffset, (double)aabbOffset, (double)height, (double)(16.0F - aabbOffset), (double)(16.0F - aabbOffset));
        this.westAabb = Block.box((double)(16.0F - height), (double)aabbOffset, (double)aabbOffset, 16.0, (double)(16.0F - aabbOffset), (double)(16.0F - aabbOffset));
        this.height = height;
        this.aabbOffset = aabbOffset;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return switch (direction) {
            case NORTH -> this.northAabb;
            case SOUTH -> this.southAabb;
            case EAST -> this.eastAabb;
            case WEST -> this.westAabb;
            case DOWN -> this.downAabb;
            case UP -> this.upAabb;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        LevelAccessor levelaccessor = placeContext.getLevel();
        BlockPos blockpos = placeContext.getClickedPos();
        return this.defaultBlockState()
                .setValue(WATERLOGGED, Boolean.valueOf(levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER))
                .setValue(FACING, placeContext.getClickedFace());
    }

    @Override
    protected BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess tickAccess, BlockPos pos, Direction direction, BlockPos blockPos, BlockState state, RandomSource randomSource
    ) {
        if (blockState.getValue(WATERLOGGED)) {
            tickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(levelReader));
        }

        return direction == blockState.getValue(FACING).getOpposite() && !blockState.canSurvive(levelReader, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(blockState, levelReader, tickAccess, pos, direction, blockPos, state, randomSource);
    }


    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction direction = state.getValue(FACING).getOpposite();
        BlockPos blockPos = pos.relative(direction);
        return world.getBlockState(blockPos).isFaceSturdy(world, blockPos, direction);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }
}
