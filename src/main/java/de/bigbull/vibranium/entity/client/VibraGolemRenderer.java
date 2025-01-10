package de.bigbull.vibranium.entity.client;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.entity.client.Crackniess.VibraGolemCrackinessLayer;
import de.bigbull.vibranium.entity.VibraGolemEntity;
import de.bigbull.vibranium.init.ModelLayersInit;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class VibraGolemRenderer extends MobRenderer<VibraGolemEntity, VibraGolemRenderState, VibraGolemModel> {

    private static final ResourceLocation VIBRAGOLEM_LOCATION = ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "textures/entity/vibra/vibra_golem_2.png");

    public VibraGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new VibraGolemModel(context.bakeLayer(ModelLayersInit.VIBRAGOLEM_LAYER)), 0.5F);
        addLayer(new VibraGolemCrackinessLayer(this));
    }

    @Override
    public VibraGolemRenderState createRenderState() {
        return new VibraGolemRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(VibraGolemRenderState state) {
        return VIBRAGOLEM_LOCATION;
    }
}
