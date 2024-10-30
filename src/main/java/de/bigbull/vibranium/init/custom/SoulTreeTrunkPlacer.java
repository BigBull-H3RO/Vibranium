package de.bigbull.vibranium.init.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.bigbull.vibranium.init.FeatureInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class SoulTreeTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<SoulTreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> trunkPlacerParts(instance)
                    .and(
                            instance.group(
                                    IntProvider.codec(1, 4).fieldOf("branch_count").forGetter(placer -> placer.branchCount),
                                    IntProvider.codec(2, 16).fieldOf("branch_horizontal_length").forGetter(placer -> placer.branchHorizontalLength),
                                    IntProvider.codec(-16, 0).fieldOf("branch_start_offset_from_top").forGetter(placer -> placer.branchStartOffsetFromTop),
                                    IntProvider.codec(-16, 16).fieldOf("branch_end_offset_from_top").forGetter(placer -> placer.branchEndOffsetFromTop)
                            )
                    )
                    .apply(instance, SoulTreeTrunkPlacer::new)
    );

    private final IntProvider branchCount;
    private final IntProvider branchHorizontalLength;
    private final IntProvider branchStartOffsetFromTop;
    private final IntProvider branchEndOffsetFromTop;

    public SoulTreeTrunkPlacer(
            int baseHeight, int heightRandA, int heightRandB,
            IntProvider branchCount,
            IntProvider branchHorizontalLength,
            IntProvider branchStartOffsetFromTop, IntProvider branchEndOffsetFromTop
    ) {
        super(baseHeight, heightRandA, heightRandB);
        this.branchCount = branchCount;
        this.branchHorizontalLength = branchHorizontalLength;
        this.branchStartOffsetFromTop = branchStartOffsetFromTop;
        this.branchEndOffsetFromTop = branchEndOffsetFromTop;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return FeatureInit.SOUL_TREE_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            LevelSimulatedReader level,
            BiConsumer<BlockPos, BlockState> blockSetter,
            RandomSource random,
            int trunkHeight,
            BlockPos pos,
            TreeConfiguration config
    ) {
        setDirtAt(level, blockSetter, random, pos.below(), config);

        for (int i = 0; i < trunkHeight; i++) {
            this.placeLog(level, blockSetter, random, pos.above(i), config);
        }

        List<FoliagePlacer.FoliageAttachment> attachments = new ArrayList<>();
        attachments.add(new FoliagePlacer.FoliageAttachment(pos.above(trunkHeight), 0, false));

        List<Direction> possibleDirections = new ArrayList<>(List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST));
        int branches = this.branchCount.sample(random);
        Collections.shuffle(possibleDirections, new Random(random.nextLong()));

        for (int i = 0; i < branches; i++) {
            Direction direction = possibleDirections.get(i);
            int branchStartOffset = this.branchStartOffsetFromTop.sample(random);
            int branchStartHeight = trunkHeight - 1 + branchStartOffset;

            attachments.add(
                    this.generateBranch(
                            level, blockSetter, random, trunkHeight, pos, config,
                            direction, branchStartHeight, branchStartHeight < trunkHeight - 1
                    )
            );
        }
        return attachments;
    }

    private FoliagePlacer.FoliageAttachment generateBranch(
            LevelSimulatedReader level,
            BiConsumer<BlockPos, BlockState> blockSetter,
            RandomSource random,
            int trunkHeight,
            BlockPos pos,
            TreeConfiguration config,
            Direction direction,
            int branchStartHeight,
            boolean extraLength
    ) {
        BlockPos.MutableBlockPos mutablePos = pos.mutable().move(Direction.UP, branchStartHeight);
        int branchEndOffset = this.branchEndOffsetFromTop.sample(random);
        int branchHeight = trunkHeight - 1 + branchEndOffset;
        boolean flag = extraLength || branchHeight < branchStartHeight;
        int branchLength = this.branchHorizontalLength.sample(random) + (flag ? 1 : 0);
        BlockPos branchEndPos = pos.relative(direction, branchLength).above(branchHeight);
        int steps = flag ? 2 : 1;

        Function<BlockState, BlockState> axisSetter = (state) -> state.setValue(RotatedPillarBlock.AXIS, direction.getAxis());
        for (int i = 0; i < steps; i++) {
            this.placeLog(level, blockSetter, random, mutablePos.move(direction), config, axisSetter);
        }

        Direction verticalDirection = branchEndPos.getY() > mutablePos.getY() ? Direction.UP : Direction.DOWN;
        while (!mutablePos.equals(branchEndPos)) {
            int distance = mutablePos.distManhattan(branchEndPos);
            float chance = (float)Math.abs(branchEndPos.getY() - mutablePos.getY()) / (float)distance;
            boolean moveVertically = random.nextFloat() < chance;
            mutablePos.move(moveVertically ? verticalDirection : direction);
            this.placeLog(level, blockSetter, random, mutablePos, config, moveVertically ? Function.identity() : axisSetter);
        }

        return new FoliagePlacer.FoliageAttachment(branchEndPos.above(), 0, false);
    }
}