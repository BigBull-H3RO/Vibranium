package de.bigbull.vibranium.entity.client;

import de.bigbull.vibranium.entity.custom.VibraGolemEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class VibraGolemRenderer extends GeoEntityRenderer<VibraGolemEntity> {
    public VibraGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new VibraGolemModel());
        this.shadowRadius = 0.5f;
        addRenderLayer(new VibraGolemGeoRenderLayer(this));
    }

    @Override
    public RenderType getRenderType(VibraGolemEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        withScale(0.8f);
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}
