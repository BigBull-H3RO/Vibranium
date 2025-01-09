package de.bigbull.vibranium.entity.client.Crackniess;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.entity.client.VibraGolemModel;
import de.bigbull.vibranium.entity.client.VibraGolemRenderState;
import net.minecraft.client.renderer.MultiBufferSource;
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

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int i, VibraGolemRenderState state, float v, float f) {
        if (!state.isInvisible) {
            VibraCrackiness.Level crackiness$level = state.crackiness;
            if (crackiness$level != VibraCrackiness.Level.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(crackiness$level);
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, poseStack, bufferSource, i, state, -1);
            }
        }

    }
}