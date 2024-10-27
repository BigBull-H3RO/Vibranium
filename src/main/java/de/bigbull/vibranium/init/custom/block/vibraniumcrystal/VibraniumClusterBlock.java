package de.bigbull.vibranium.init.custom.block.vibraniumcrystal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VibraniumClusterBlock extends AmethystBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    private final float height;
    private final float aabbOffset;
    protected final VoxelShape northAabb;
    protected final VoxelShape southAabb;
    protected final VoxelShape eastAabb;
    protected final VoxelShape westAabb;
    protected final VoxelShape upAabb;
    protected final VoxelShape downAabb;

    public VibraniumClusterBlock(float height,  float aabbOffset, BlockBehaviour.Properties properties) {
        super(properties);
        this.height = height;
        this.aabbOffset = aabbOffset;
        this.upAabb = Block.box((double) aabbOffset, 0.0, (double) aabbOffset, (double) (16.0F - aabbOffset), (double) height, (double) (16.0F - aabbOffset));
        this.downAabb = Block.box((double) aabbOffset, (double) (16.0F - height), (double) aabbOffset, (double) (16.0F - aabbOffset), 16.0, (double) (16.0F - aabbOffset));
        this.northAabb = Block.box((double) aabbOffset, (double) aabbOffset, (double) (16.0F - height), (double) (16.0F - aabbOffset), (double) (16.0F - aabbOffset), 16.0);
        this.southAabb = Block.box((double) aabbOffset, (double) aabbOffset, 0.0, (double) (16.0F - aabbOffset), (double) (16.0F - aabbOffset), (double) height);
        this.eastAabb = Block.box(0.0, (double) aabbOffset, (double) aabbOffset, (double) height, (double) (16.0F - aabbOffset), (double) (16.0F - aabbOffset));
        this.westAabb = Block.box((double) (16.0F - height), (double) aabbOffset, (double) aabbOffset, 16.0, (double) (16.0F - aabbOffset), (double) (16.0F - aabbOffset));
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false).setValue(FACING, Direction.UP));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(BlockStateProperties.FACING);  // Assuming you have FACING as a property
        switch (direction) {
            case NORTH:
                return this.northAabb;
            case SOUTH:
                return this.southAabb;
            case EAST:
                return this.eastAabb;
            case WEST:
                return this.westAabb;
            case DOWN:
                return this.downAabb;
            case UP:
            default:
                return this.upAabb;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, context.getClickedFace())
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return facing == state.getValue(FACING).getOpposite() && !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction direction = state.getValue(FACING).getOpposite();
        BlockPos blockPos = pos.relative(direction);
        return world.getBlockState(blockPos).isFaceSturdy(world, blockPos, direction);
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
