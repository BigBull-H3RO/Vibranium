package de.bigbull.vibranium.data.worldgen.ore;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {
    private static List<PlacementModifier> orePlacements(PlacementModifier placementModifier, PlacementModifier placementModifier1) {
        return List.of(placementModifier, InSquarePlacement.spread(), placementModifier1, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacements(int count, PlacementModifier placementModifier) {
        return orePlacements(CountPlacement.of(count), placementModifier);
    }
}
