package de.bigbull.vibranium.init.custom.block.tree;

import de.bigbull.vibranium.data.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class EVTree {
    public static final TreeGrower SOUL_TREE = new TreeGrower(
            "vibranium:soul_tree",
            0.3F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.SOUL_TREE),
            Optional.of(ModConfiguredFeatures.SOUL_TREE_SMALL),
            Optional.empty(),
            Optional.empty()
    );
}