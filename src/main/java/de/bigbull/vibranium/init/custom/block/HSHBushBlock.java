package de.bigbull.vibranium.init.custom.block;

import com.mojang.serialization.MapCodec;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.ParticleInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HSHBushBlock extends VegetationBlock implements BonemealableBlock {
    public static final MapCodec<HSHBushBlock> CODEC = simpleCodec(HSHBushBlock::new);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape SHAPE_SAPLING = Block.column(10.0, 0.0, 8.0);
    private static final VoxelShape SHAPE_GROWING = Block.column(14.0, 0.0, 16.0);

    @Override
    public MapCodec<HSHBushBlock> codec() {
        return CODEC;
    }

    public HSHBushBlock(Block.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockState soil = worldIn.getBlockState(pos.below());
        return soil.is(BlockInit.ENRICHED_VIBRANIUM_DIRT.get()) || soil.is(BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get());
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
        return state.is(BlockInit.ENRICHED_VIBRANIUM_DIRT.get()) || state.is(BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        return new ItemStack(ItemInit.HEART_SHAPED_HERB.get());
    }

    @Override
    protected VoxelShape getShape(BlockState p_57291_, BlockGetter p_57292_, BlockPos p_57293_, CollisionContext p_57294_) {
        return switch (p_57291_.getValue(AGE)) {
            case 0 -> SHAPE_SAPLING;
            case 3 -> Shapes.block();
            default -> SHAPE_GROWING;
        };
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource) {
        int i = blockState.getValue(AGE);
        if (i < 3 && serverLevel.getRawBrightness(pos.above(), 0) >= 9 && net.neoforged.neoforge.common.CommonHooks.canCropGrow(serverLevel, pos, blockState, randomSource.nextInt(5) == 0)) {
            if (randomSource.nextInt(40) == 0) {
                BlockState blockstate = blockState.setValue(AGE, i + 1);
                serverLevel.setBlock(pos, blockstate, 2);
                net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(serverLevel, pos, blockState);
                serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (state.getValue(HSHBushBlock.AGE) == 3) {
            if (random.nextInt(20) == 0) {
                double offsetX = random.nextDouble() * 0.2 - 0.08;
                double offsetY = random.nextDouble() * 0.2 - 0.05;
                double offsetZ = random.nextDouble() * 0.2 - 0.08;

                double particleX = pos.getX() + 0.5 + offsetX;
                double particleY = pos.getY() + 0.45 + offsetY;
                double particleZ = pos.getZ() + 0.5 + offsetZ;

                world.addParticle(
                        ParticleInit.DRIPPING_VIBRANIUM.get(),
                        particleX, particleY, particleZ,
                        0.0, 0.0, 0.0
                );
            }
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int i = blockState.getValue(AGE);
        boolean flag = i == 3;
        if (i > 1) {
            int j = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack(ItemInit.HEART_SHAPED_HERB.get(), j + (flag ? 1 : 0)));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState blockstate = blockState.setValue(AGE, 1);
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockstate));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(blockState, level, pos, player, hitResult);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel p_220874_, RandomSource p_220875_, BlockPos p_220876_, BlockState p_220877_) {}
}
