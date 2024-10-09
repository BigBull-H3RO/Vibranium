package de.bigbull.vibranium.event.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.bigbull.vibranium.config.VibraniumConfigValues;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.custom.item.VibraniumMaceItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
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
import net.neoforged.neoforge.client.event.RenderHighlightEvent;

import java.util.List;

@EventBusSubscriber(modid = Vibranium.MODID, value = Dist.CLIENT)
public class ClientRenderEvent {
    private static boolean isOutlineEnabled = true;

    @SubscribeEvent
    public static void onRenderWorld(RenderHighlightEvent.Block event) {
        Minecraft mc = Minecraft.getInstance();
        ItemStack mainHandItem = mc.player.getMainHandItem();

        if (ClientKeyBindings.toggleOutlineKey.consumeClick()) {
            isOutlineEnabled = !isOutlineEnabled;
        }

        if (!isOutlineEnabled) {
            return;
        }

        if (isValidVibraniumMace(mainHandItem) && mc.hitResult instanceof BlockHitResult hitResult && !mc.player.isCreative()) {
            BlockPos hitPos = hitResult.getBlockPos();
            BlockState blockState = mc.level.getBlockState(hitPos);

            if (!mc.player.isShiftKeyDown() && isValidBlock(blockState)) {
                render3x3Outline(event.getPoseStack(), hitPos);
                event.setCanceled(true);
            }
        }
    }

    private static boolean isValidVibraniumMace(ItemStack itemStack) {
        return itemStack.getItem() == ItemInit.VIBRANIUM_MACE.get();
    }

    private static boolean isValidBlock(BlockState state) {
        Block block = state.getBlock();
        return !state.isAir() && block.defaultBlockState().isSolidRender(Minecraft.getInstance().level, BlockPos.ZERO);
    }

    private static void render3x3Outline(PoseStack poseStack, BlockPos center) {
        Minecraft mc = Minecraft.getInstance();
        Vec3 camPos = mc.gameRenderer.getMainCamera().getPosition();

        poseStack.pushPose();
        poseStack.translate(-camPos.x, -camPos.y, -camPos.z);

        List<BlockPos> positions = VibraniumMaceItem.getBlocksToBeDestroyed(1, center, mc.player);

        for (BlockPos pos : positions) {
            BlockState state = mc.level.getBlockState(pos);
            if (isValidBlock(state)) {
                AABB box = state.getShape(mc.level, pos).bounds().move(pos);
                renderBlockOutline(poseStack, box);
            }
        }
        poseStack.popPose();
    }

    private static void renderBlockOutline(PoseStack poseStack, AABB boundingBox) {
        RenderType renderType = RenderType.lines();
        VertexConsumer builder = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(renderType);

        float red = (float) VibraniumConfigValues.OUTLINE_RED;
        float green = (float) VibraniumConfigValues.OUTLINE_GREEN;
        float blue = (float) VibraniumConfigValues.OUTLINE_BLUE;
        float alpha = (float) VibraniumConfigValues.OUTLINE_ALPHA;

        LevelRenderer.renderLineBox(poseStack, builder, boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ, red, green, blue, alpha);
        Minecraft.getInstance().renderBuffers().bufferSource().endBatch(renderType);
    }
}