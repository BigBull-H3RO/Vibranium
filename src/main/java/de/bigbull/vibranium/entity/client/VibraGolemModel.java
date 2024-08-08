package de.bigbull.vibranium.entity.client;

import de.bigbull.vibranium.entity.custom.VibraGolemEntity;
import de.bigbull.vibranium.main.ModInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class VibraGolemModel extends GeoModel<VibraGolemEntity> {
    private final ResourceLocation model = ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "geo/vibra_golem.geo.json");
    private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "textures/entity/vibra/vibra_golem_2.png");
    private final ResourceLocation animations = ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "animations/vibra_golem.animation.json");

    @Override
    public ResourceLocation getModelResource(VibraGolemEntity animatable) {
        return this.model;
    }

    @Override
    public ResourceLocation getTextureResource(VibraGolemEntity animatable) {
        return this.texture;
    }

    @Override
    public ResourceLocation getAnimationResource(VibraGolemEntity animatable) {
        return this.animations;
    }

    @Override
    public void setCustomAnimations(VibraGolemEntity animatable, long instanceId, AnimationState<VibraGolemEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
