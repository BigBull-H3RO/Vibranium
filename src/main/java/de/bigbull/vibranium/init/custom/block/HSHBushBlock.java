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
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HSHBushBlock extends BushBlock implements BonemealableBlock {
    public static final MapCodec<HSHBushBlock> CODEC = simpleCodec(HSHBushBlock::new);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape SAPLING_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);
    private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public HSHBushBlock(Properties p_51021_) {
        super(p_51021_);
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
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return new ItemStack(ItemInit.HEART_SHAPED_HERB.get());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (state.getValue(AGE) == 0) {
            return SAPLING_SHAPE;
        } else {
            return state.getValue(AGE) < 3 ? MID_GROWTH_SHAPE : super.getShape(state, world, pos, context);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        if (age < 3 && world.getRawBrightness(pos.above(), 0) >= 9) {
            if (random.nextInt(40) == 0) {
                world.setBlock(pos, state.setValue(AGE, age + 1), 2);
                world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
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
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int age = state.getValue(AGE);
        boolean isMature = age == 3;
        if (isMature) {
            level.setBlock(pos, state.setValue(AGE, 1), 2);
            ItemStack drop = new ItemStack(ItemInit.HEART_SHAPED_HERB.get(), 1);
            popResource(level, pos, drop);
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            return level.isClientSide ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
        } else {
            return InteractionResult.PASS;
        }
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        int age = Math.min(3, state.getValue(AGE) + 1);
        world.setBlock(pos, state.setValue(AGE, age), 2);
    }
}
