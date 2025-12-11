package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModelLayersInit {
    public static final ModelLayerLocation SOULWOOD_BOAT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Vibranium.MODID, "boat/soulwood"), "main");
    public static final ModelLayerLocation SOULWOOD_CHEST_BOAT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Vibranium.MODID, "chest_boat/soulwood"), "main");
    public static final ModelLayerLocation VIBRAGOLEM_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Vibranium.MODID, "vibra_golem"), "main");
}
