package de.bigbull.vibranium.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.config.ClientConfig;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.ModKeybinds;
import de.bigbull.vibranium.init.custom.item.VibraniumMaceItem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.client.renderer.state.BlockOutlineRenderState;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.CustomBlockOutlineRenderer;
import net.neoforged.neoforge.client.event.ExtractBlockOutlineRenderStateEvent;

import java.util.List;

@EventBusSubscriber(modid = Vibranium.MODID, value = Dist.CLIENT)
public class ClientRenderEvent {
    private static boolean isOutlineEnabled = true;

    @SubscribeEvent
    public static void onExtractBlockOutline(ExtractBlockOutlineRenderStateEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        ItemStack mainHandItem = mc.player.getMainHandItem();

        // Toggle Key
        if (ModKeybinds.TOGGLE_OUTLINE.consumeClick()) {
            isOutlineEnabled = !isOutlineEnabled;
        }
        if (!isOutlineEnabled) return;

        // Nur bei Vibranium Mace + Block-Hit
        if (isValidVibraniumMace(mainHandItem) && event.getHitResult() != null && !mc.player.isCreative()) {
            BlockHitResult hitResult = event.getHitResult();
            BlockPos hitPos = hitResult.getBlockPos();
            BlockState blockState = mc.level.getBlockState(hitPos);

            if (!mc.player.isShiftKeyDown() && isValidBlock(blockState)) {
                event.addCustomRenderer(new VibraniumOutlineRenderer(hitPos, event.getCamera()));
            }

        }
    }

    private static boolean isValidVibraniumMace(ItemStack stack) {
        return stack.getItem() == ItemInit.VIBRANIUM_MACE.get();
    }

    private static boolean isValidBlock(BlockState state) {
        Block block = state.getBlock();
        return !state.isAir() && block.defaultBlockState().isSolidRender();
    }

    // --- eigener Renderer ---
    private static class VibraniumOutlineRenderer implements CustomBlockOutlineRenderer {
        private final BlockPos center;
        private final Camera camera;

        public VibraniumOutlineRenderer(BlockPos center, Camera camera) {
            this.center = center;
            this.camera = camera;
        }

        @Override
        public boolean render(BlockOutlineRenderState renderState,
                              MultiBufferSource.BufferSource buffer,
                              PoseStack poseStack,
                              boolean translucentPass,
                              LevelRenderState levelRenderState) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || mc.level == null) return false;

            Vec3 camPos = camera.getPosition();

            poseStack.pushPose();
            poseStack.translate(-camPos.x, -camPos.y, -camPos.z);

            List<BlockPos> positions = VibraniumMaceItem.getBlocksToBeDestroyed(1, center, mc.player);

            for (BlockPos pos : positions) {
                BlockState state = mc.level.getBlockState(pos);
                if (isValidBlock(state)) {
                    AABB box = state.getShape(mc.level, pos).bounds().move(pos);
                    renderBlockOutline(poseStack, buffer, box);
                }
            }

            poseStack.popPose();

            return true;
        }

        private void renderBlockOutline(PoseStack poseStack, MultiBufferSource bufferSource, AABB boundingBox) {
            VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());

            float red = (float) ClientConfig.OUTLINE_RED.getAsDouble();
            float green = (float) ClientConfig.OUTLINE_GREEN.getAsDouble();
            float blue = (float) ClientConfig.OUTLINE_BLUE.getAsDouble();
            float alpha = (float) ClientConfig.OUTLINE_ALPHA.getAsDouble();

            ShapeRenderer.renderLineBox(
                    poseStack.last(),
                    vertexConsumer,
                    boundingBox.minX, boundingBox.minY, boundingBox.minZ,
                    boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ,
                    red, green, blue, alpha
            );
        }
    }
}