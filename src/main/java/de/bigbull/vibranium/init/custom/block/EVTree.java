package de.bigbull.vibranium.init.custom.block;

import de.bigbull.vibranium.data.worldgen.ore.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class EVTree {
    public static final TreeGrower SOUL_TREE = new TreeGrower(
            "vibranium:enriched_vibranium",
            0.2F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.SOUL_TREE),
            Optional.of(ModConfiguredFeatures.FANCY_SOUL_TREE),
            Optional.empty(),
            Optional.empty()
    );
}