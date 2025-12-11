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
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.BlockOutlineRenderState;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
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

        if (ModKeybinds.TOGGLE_OUTLINE.consumeClick()) {
            isOutlineEnabled = !isOutlineEnabled;
        }
        if (!isOutlineEnabled) return;

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

    // Custom outline renderer for Vibranium Mace
    private record VibraniumOutlineRenderer(BlockPos center, Camera camera) implements CustomBlockOutlineRenderer {

        @Override
        public boolean render(BlockOutlineRenderState renderState,
                              MultiBufferSource.BufferSource buffer,
                              PoseStack poseStack,
                              boolean translucentPass,
                              LevelRenderState levelRenderState) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || mc.level == null) return false;

            Vec3 camPos = camera.position();

            poseStack.pushPose();
            poseStack.translate(-camPos.x, -camPos.y, -camPos.z);

            List<BlockPos> positions = VibraniumMaceItem.getBlocksToBeDestroyed(1, center, mc.player);

            for (BlockPos pos : positions) {
                BlockState state = mc.level.getBlockState(pos);
                if (isValidBlock(state)) {
                    VoxelShape shape = state.getShape(mc.level, pos);
                    renderBlockOutline(poseStack, buffer, shape, pos);
                }
            }

            poseStack.popPose();

            return true;
        }

        private void renderBlockOutline(PoseStack poseStack, MultiBufferSource bufferSource, VoxelShape shape, BlockPos pos) {
            VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderTypes.LINES);

            int red = (int) (ClientConfig.OUTLINE_RED.getAsDouble() * 255);
            int green = (int) (ClientConfig.OUTLINE_GREEN.getAsDouble() * 255);
            int blue = (int) (ClientConfig.OUTLINE_BLUE.getAsDouble() * 255);
            int alpha = (int) (ClientConfig.OUTLINE_ALPHA.getAsDouble() * 255);

            int color = ARGB.color(alpha, red, green, blue);

            ShapeRenderer.renderShape(
                    poseStack,
                    vertexConsumer,
                    shape,
                    pos.getX(), pos.getY(), pos.getZ(),
                    color,
                    2.0F
            );
        }
    }
}