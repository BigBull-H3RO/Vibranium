package de.bigbull.vibranium.entity.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bigbull.vibranium.entity.custom.VibraGolemEntity;
import de.bigbull.vibranium.main.ModInfo;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.Map;

public class VibraGolemGeoRenderLayer extends GeoRenderLayer<VibraGolemEntity> {
    private static final Map<VibraCrackiness.Level, ResourceLocation> resourceLocations = ImmutableMap.of(
            VibraCrackiness.Level.LOW,
            ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "textures/entity/vibra/vibra_golem_crackiness_low.png"),
            VibraCrackiness.Level.MEDIUM,
            ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "textures/entity/vibra/vibra_golem_crackiness_medium.png"),
            VibraCrackiness.Level.HIGH,
            ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "textures/entity/vibra/vibra_golem_crackiness_high.png")
    );

    public VibraGolemGeoRenderLayer(GeoRenderer<VibraGolemEntity> entityRenderer) {
        super(entityRenderer);
    }

    @Override
    public void render(PoseStack poseStack, VibraGolemEntity vibraGolem, BakedGeoModel bakedModel, @Nullable RenderType renderType, MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        if (!vibraGolem.isInvisible()) {
            VibraCrackiness.Level vibraCrackiness$level = vibraGolem.getCrackiness();
            if (vibraCrackiness$level != VibraCrackiness.Level.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(vibraCrackiness$level);
                VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(resourcelocation));
                poseStack.pushPose();
                this.getRenderer().reRender(bakedModel, poseStack, bufferSource, vibraGolem, renderType, vertexConsumer, partialTick, packedLight, packedOverlay, -1);
                poseStack.popPose();
            }
        }
    }

//    @Override
//    public void renderForBone(PoseStack poseStack, VibraGolemEntity vibraGolem, GeoBone bone, RenderType renderType,
//                              MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
//        if (bone.getName().equals("R_LowerArm") && !vibraGolem.getHeldItem().isEmpty()) {
//            poseStack.pushPose();
//
//            poseStack.translate(bone.getPivotX() / 16.0, bone.getPivotY() / 16.0, bone.getPivotZ() / 16.0);
//            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
//            poseStack.mulPose(Axis.YP.rotationDegrees(0.0F));
//
//            poseStack.scale(0.75F, 0.75F, 0.75F);
//            Minecraft.getInstance().getItemRenderer().renderStatic(vibraGolem.getHeldItem(), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, packedLight, packedOverlay, poseStack, bufferSource,vibraGolem.level(), vibraGolem.getId());
//            poseStack.popPose();
//        }
//    }
}