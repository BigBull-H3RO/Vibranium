package de.bigbull.vibranium.init.custom.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.custom.entity.SWBoatEntity;
import de.bigbull.vibranium.init.custom.entity.SWChestBoatEntity;
import de.bigbull.vibranium.init.ModelLayersInit;
import de.bigbull.vibranium.init.TypesInit;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;

public class SoulwoodBoatRenderer extends BoatRenderer {
    private final boolean HAS_CHEST;
    private final Map<String, Pair<ResourceLocation, ListModel<Boat>>> BOAT_RESOURCES;
    private final Map<String, ModelLayerLocation> chestBoatModels;
    private final Map<String, ModelLayerLocation> boatModels;

    public SoulwoodBoatRenderer(EntityRendererProvider.Context context, boolean chest) {
        super(context, chest);
        this.HAS_CHEST = chest;
        this.chestBoatModels = Map.of(TypesInit.SOULWOOD_WOODTYPE.name(), ModelLayersInit.SOULWOOD_CHEST_BOAT);
        this.boatModels = Map.of(TypesInit.SOULWOOD_WOODTYPE.name(), ModelLayersInit.SOULWOOD_BOAT);
        this.BOAT_RESOURCES = ImmutableMap.of(
                TypesInit.SOULWOOD_WOODTYPE.name(), Pair.of(ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, ("textures/entity/" + (chest ? "chest_boat" : "boat") + "/" + TypesInit.SOULWOOD_WOODTYPE.name() + ".png")
                ), this.createBoatModel(context, TypesInit.SOULWOOD_WOODTYPE.name())));
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context context, String boatType) {
        ModelPart modelPart = context.bakeLayer(HAS_CHEST ? this.chestBoatModels.get(boatType) : this.boatModels.get(boatType));
        return HAS_CHEST ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }

    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        Pair<ResourceLocation, ListModel<Boat>> model = null;
        if(boat instanceof SWBoatEntity swBoat) model = BOAT_RESOURCES.get(swBoat.getWoodType());
        if(boat instanceof SWChestBoatEntity swChestBoat) model = BOAT_RESOURCES.get(swChestBoat.getWoodType());
        return model == null ? BOAT_RESOURCES.get(TypesInit.SOULWOOD_WOODTYPE.name()) : model;
    }
}
