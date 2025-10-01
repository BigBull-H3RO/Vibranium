package de.bigbull.vibranium.entity.client.Crackniess;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.entity.client.VibraGolemModel;
import de.bigbull.vibranium.entity.client.VibraGolemRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class VibraGolemCrackinessLayer extends RenderLayer<VibraGolemRenderState, VibraGolemModel> {
    private static final Map<VibraCrackiness.Level, ResourceLocation> resourceLocations = ImmutableMap.of(
            VibraCrackiness.Level.LOW,
            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "textures/entity/vibra/vibra_golem_crackiness_low.png"),
            VibraCrackiness.Level.MEDIUM,
            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "textures/entity/vibra/vibra_golem_crackiness_medium.png"),
            VibraCrackiness.Level.HIGH,
            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "textures/entity/vibra/vibra_golem_crackiness_high.png")
    );

    public VibraGolemCrackinessLayer(RenderLayerParent<VibraGolemRenderState, VibraGolemModel> p_117346_) {
        super(p_117346_);
    }

    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int p_117351_, VibraGolemRenderState renderState, float p_117353_, float p_117354_) {
        if (!renderState.isInvisible) {
            VibraCrackiness.Level crackiness$level = renderState.crackiness;
            if (crackiness$level != VibraCrackiness.Level.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(crackiness$level);
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, poseStack, collector, p_117351_, renderState, -1, 1);
            }
        }
    }
}