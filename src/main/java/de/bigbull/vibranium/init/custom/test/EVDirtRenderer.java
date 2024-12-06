package de.bigbull.vibranium.init.custom.test;

import com.mojang.blaze3d.vertex.PoseStack;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class EVDirtRenderer implements BlockEntityRenderer<EVDirtBlockEntity> {
    private static final ResourceLocation EMISSIVE_TEXTURE = ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "textures/block/emissive/enriched_vibranium_dirt_emissive.png");

    public EVDirtRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(EVDirtBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
    }
}
