package de.bigbull.vibranium.init;

import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

public class BlockFamilyInit {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();

    public static final BlockFamily SOULWOOD_FAMILY = familyBuilder(BlockInit.SOULWOOD_PLANKS.get())
            .button(BlockInit.SOULWOOD_BUTTON.get())
            .fence(BlockInit.SOULWOOD_FENCE.get())
            .fenceGate(BlockInit.SOULWOOD_FENCE_GATE.get())
            .pressurePlate(BlockInit.SOULWOOD_PRESSURE_PLATE.get())
            .sign(BlockInit.SOULWOOD_SIGN.get(), BlockInit.SOULWOOD_WALL_SIGN.get())
            .slab(BlockInit.SOULWOOD_SLAB.get())
            .stairs(BlockInit.SOULWOOD_STAIRS.get())
            .door(BlockInit.SOULWOOD_DOOR.get())
            .trapdoor(BlockInit.SOULWOOD_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    private static BlockFamily.Builder familyBuilder(Block block)
    {
        BlockFamily.Builder blockfamily$builder = new BlockFamily.Builder(block);
        BlockFamily blockfamily = MAP.put(block, blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(block));
        } else {
            return blockfamily$builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
