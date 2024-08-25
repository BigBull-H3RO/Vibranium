package de.bigbull.vibranium.event.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.main.Vibranium;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(modid = Vibranium.MODID, value = Dist.CLIENT)
public class ClientRenderEvent {
    @SubscribeEvent
    public static void onRenderWorldLast(RenderLevelStageEvent event) {
        Minecraft mc = Minecraft.getInstance();
        ItemStack heldItem = mc.player.getMainHandItem();

        if (heldItem.getItem() != ItemInit.VIBRANIUM_MACE.get()) {
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
        ItemStack heldItem = mc.player.getMainHandItem();

        if (heldItem.getItem() == ItemInit.VIBRANIUM_MACE.get()) {
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

        Iterable<BlockPos> positions = get3x3Positions(center, direction);
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
        return !state.isAir() && block.defaultBlockState().isSolidRender(Minecraft.getInstance().level, BlockPos.ZERO);
    }

    private static Iterable<BlockPos> get3x3Positions(BlockPos center, Direction direction) {
        switch (direction) {
            case UP:
            case DOWN:
                return BlockPos.betweenClosed(center.offset(-1, 0, -1), center.offset(1, 0, 1));
            case NORTH:
            case SOUTH:
                return BlockPos.betweenClosed(center.offset(-1, -1, 0), center.offset(1, 1, 0));
            case EAST:
            case WEST:
                return BlockPos.betweenClosed(center.offset(0, -1, -1), center.offset(0, 1, 1));
            default:
                return BlockPos.betweenClosed(center, center);
        }
    }

    private static void renderBlockOutline(PoseStack poseStack, AABB boundingBox) {
        RenderType renderType = RenderType.lines();
        VertexConsumer builder = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(renderType);
        LevelRenderer.renderLineBox(poseStack, builder, boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ, 0.0F, 0.0F, 0.0F, 0.35F);
        Minecraft.getInstance().renderBuffers().bufferSource().endBatch(renderType);
    }
}