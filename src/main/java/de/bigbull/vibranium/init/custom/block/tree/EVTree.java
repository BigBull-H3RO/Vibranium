package de.bigbull.vibranium.init.custom.block.tree;

import de.bigbull.vibranium.data.worldgen.ModConfiguredFeatures;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;

public class EVTree {
    public static final TreeGrower SOUL_TREE = new TreeGrower(
            "soul_tree",
            WeightedList.of(ModConfiguredFeatures.SOUL_TREE, ModConfiguredFeatures.SOUL_TREE_SMALL),
            WeightedList.of(),
            WeightedList.of(),
            ModConfiguredFeatures.SOUL_TREE_SMALL
    );
}
