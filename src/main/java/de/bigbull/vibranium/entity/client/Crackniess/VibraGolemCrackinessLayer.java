//package de.bigbull.vibranium.entity.client.Crackniess;
//
//import com.google.common.collect.ImmutableMap;
//import com.mojang.blaze3d.vertex.PoseStack;
//import de.bigbull.vibranium.entity.client.VibraGolemModel;
//import de.bigbull.vibranium.entity.custom.VibraGolemEntity;
//import de.bigbull.vibranium.Vibranium;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.entity.RenderLayerParent;
//import net.minecraft.client.renderer.entity.layers.RenderLayer;
//import net.minecraft.resources.ResourceLocation;
//
//import java.util.Map;
//
//public class VibraGolemCrackinessLayer extends RenderLayer<VibraGolemEntity, VibraGolemModel<VibraGolemEntity>> {
//    private static final Map<VibraCrackiness.Level, ResourceLocation> resourceLocations = ImmutableMap.of(
//            VibraCrackiness.Level.LOW,
//            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "textures/entity/vibra/vibra_golem_crackiness_low.png"),
//            VibraCrackiness.Level.MEDIUM,
//            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "textures/entity/vibra/vibra_golem_crackiness_medium.png"),
//            VibraCrackiness.Level.HIGH,
//            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "textures/entity/vibra/vibra_golem_crackiness_high.png")
//    );
//
//    public VibraGolemCrackinessLayer(RenderLayerParent<VibraGolemEntity, VibraGolemModel<VibraGolemEntity>> p_117346_) {
//        super(p_117346_);
//    }
//
//    @Override
//    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int p_117351_, VibraGolemEntity entity, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
//            if (!entity.isInvisible()) {
//                VibraCrackiness.Level crackiness$level = entity.getCrackiness();
//                if (crackiness$level != VibraCrackiness.Level.NONE) {
//                    ResourceLocation resourcelocation = resourceLocations.get(crackiness$level);
//                    renderColoredCutoutModel(this.getParentModel(), resourcelocation, poseStack, bufferSource, p_117351_, entity, -1);
//                }
//            }
//    }
//}