package de.bigbull.vibranium.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.entity.client.Crackniess.VibraGolemCrackinessLayer;
import de.bigbull.vibranium.entity.VibraGolemEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class VibraGolemRenderer extends MobRenderer<VibraGolemEntity, VibraGolemModel<VibraGolemEntity>> {

    public VibraGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new VibraGolemModel<>(context.bakeLayer(ModModelLayers.VIBRAGOLEM_LAYER)), 0.5F);
        addLayer(new VibraGolemCrackinessLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(VibraGolemEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "textures/entity/vibra/vibra_golem_2.png");
    }

    @Override
    public void render(VibraGolemEntity entity, float entityYaw, float particalTricks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, particalTricks, poseStack, bufferSource, packedLight);
    }
}
