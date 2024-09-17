package de.bigbull.vibranium.event.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.item.VibraniumMaceItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

import java.util.List;

@EventBusSubscriber(modid = Vibranium.MODID, value = Dist.CLIENT)
public class ClientRenderEvent {
    @SubscribeEvent
    public static void onRenderWorldLast(RenderLevelStageEvent event) {
        Minecraft mc = Minecraft.getInstance();
        ItemStack item = mc.player.getMainHandItem();

        if (item.getItem() != ItemInit.VIBRANIUM_MACE.get() || mc.player.isCreative()) {
            return;
        }

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            HitResult hitResult = mc.hitResult;

            if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
                BlockState centerBlockState = mc.level.getBlockState(hitPos);

                if (!isValidBlock(centerBlockState) || mc.player.isShiftKeyDown()) {
                    return;
                }

                Direction direction = ((BlockHitResult) hitResult).getDirection();
                render3x3Outline(event.getPoseStack(), hitPos, direction);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockHighlight(RenderHighlightEvent.Block event) {
        Minecraft mc = Minecraft.getInstance();
        ItemStack item = mc.player.getMainHandItem();

        if (item.getItem() == ItemInit.VIBRANIUM_MACE.get() && !mc.player.isCreative()) {
            BlockPos hitPos = event.getTarget().getBlockPos();
            BlockState centerBlockState = mc.level.getBlockState(hitPos);

            if (isValidBlock(centerBlockState) && !mc.player.isShiftKeyDown()) {
                event.setCanceled(true);
            }
        }
    }

    private static void render3x3Outline(PoseStack poseStack, BlockPos center, Direction direction) {
        Minecraft mc = Minecraft.getInstance();
        Vec3 camPos = mc.gameRenderer.getMainCamera().getPosition();
        poseStack.pushPose();
        poseStack.translate(-camPos.x, -camPos.y, -camPos.z);

        List<BlockPos> positions = VibraniumMaceItem.getAffectedPositions(mc.player, center);
        for (BlockPos pos : positions) {
            BlockState state = mc.level.getBlockState(pos);
            if (isValidBlock(state)) {
                AABB box = state.getShape(mc.level, pos).bounds().move(pos);
                renderBlockOutline(poseStack, box);
            }
        }
        poseStack.popPose();
    }

    private static boolean isValidBlock(BlockState state) {
        Block block = state.getBlock();
        FluidState fluidState = state.getFluidState();
        return (!state.isAir() || fluidState.isEmpty()) && block.defaultBlockState().isSolidRender(Minecraft.getInstance().level, BlockPos.ZERO);
    }

    private static void renderBlockOutline(PoseStack poseStack, AABB boundingBox) {
        RenderType renderType = RenderType.lines();
        VertexConsumer builder = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(renderType);
        LevelRenderer.renderLineBox(poseStack, builder, boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ, 0.0F, 0.0F, 0.0F, 0.4F);
        Minecraft.getInstance().renderBuffers().bufferSource().endBatch(renderType);
    }
}