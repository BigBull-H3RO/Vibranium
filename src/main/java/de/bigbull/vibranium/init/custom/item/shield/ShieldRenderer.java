package de.bigbull.vibranium.init.custom.item.shield;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

import java.util.Objects;

@EventBusSubscriber(modid = Vibranium.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ShieldRenderer extends BlockEntityWithoutLevelRenderer {

    public static ShieldRenderer instance;

    public ShieldRenderer(BlockEntityRenderDispatcher p_172550_, EntityModelSet p_172551_) {
        super(p_172550_, p_172551_);
    }

    @SubscribeEvent
    public static void onRegisterReloadListener(RegisterClientReloadListenersEvent event) {
        instance = new ShieldRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                Minecraft.getInstance().getEntityModels());
        event.registerReloadListener(instance);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        BannerPatternLayers bannerpatternlayers = stack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
        DyeColor dyecolor = stack.get(DataComponents.BASE_COLOR);
        boolean flag = !bannerpatternlayers.layers().isEmpty() || dyecolor != null;
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        Material material = flag ? ModelBakery.SHIELD_BASE : ModelBakery.NO_PATTERN_SHIELD;

        Item shield = stack.getItem();
        if (shield == ItemInit.VIBRANIUM_SHIELD.get()) {
            material = flag ? ModelPredicateProvider.LOCATION_VIBRANIUM_SHIELD_BASE : ModelPredicateProvider.LOCATION_VIBRANIUM_SHIELD_BASE_NOPATTERN;
        }

        VertexConsumer $$28 = material.sprite().wrap(ItemRenderer.getFoilBuffer(multiBufferSource, this.shieldModel.renderType(material.atlasLocation()), true, stack.hasFoil()));
        this.shieldModel.handle().render(poseStack, $$28, light, overlay);
        if (flag) {
            BannerRenderer.renderPatterns(
                    poseStack,
                    multiBufferSource,
                    light,
                    overlay,
                    this.shieldModel.plate(),
                    material,
                    false,
                    (DyeColor) Objects.requireNonNullElse(dyecolor, DyeColor.WHITE),
                    bannerpatternlayers
            );
        } else {
            this.shieldModel.plate().render(poseStack, $$28, light, overlay);
        }
        poseStack.popPose();
    }
}
