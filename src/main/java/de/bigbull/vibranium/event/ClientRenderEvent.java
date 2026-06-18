package de.bigbull.vibranium.event;

import com.mojang.blaze3d.vertex.PoseStack;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.config.ClientConfig;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.ModKeybinds;
import de.bigbull.vibranium.init.custom.item.VibraniumMaceItem;
import net.minecraft.client.Minecraft;

import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.BlockOutlineRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
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

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Vibranium.MODID, value = Dist.CLIENT)
public class ClientRenderEvent {
    private static boolean isOutlineEnabled = true;

    @SubscribeEvent
    public static void onExtractBlockOutline(ExtractBlockOutlineRenderStateEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null)
            return;

        ItemStack mainHandItem = mc.player.getMainHandItem();

        if (ModKeybinds.TOGGLE_OUTLINE.consumeClick()) {
            isOutlineEnabled = !isOutlineEnabled;
        }
        if (!isOutlineEnabled)
            return;

        if (isValidVibraniumMace(mainHandItem) && event.getHitResult() != null && !mc.player.isCreative()) {
            BlockHitResult hitResult = event.getHitResult();
            BlockPos hitPos = hitResult.getBlockPos();
            BlockState blockState = mc.level.getBlockState(hitPos);

            if (!mc.player.isShiftKeyDown() && isValidBlock(blockState)) {
                List<BlockPos> positions = VibraniumMaceItem.getBlocksToBeDestroyed(1, hitPos, mc.player);
                List<OutlineData> outlines = new ArrayList<>();
                Vec3 cameraPos = event.getCamera().position();

                for (BlockPos pos : positions) {
                    BlockState state = mc.level.getBlockState(pos);
                    if (isValidBlock(state)) {
                        VoxelShape shape = state.getShape(mc.level, pos);
                        if (!shape.isEmpty()) {
                            double x = pos.getX() - cameraPos.x;
                            double y = pos.getY() - cameraPos.y;
                            double z = pos.getZ() - cameraPos.z;
                            outlines.add(new OutlineData(shape, x, y, z));
                        }
                    }
                }

                if (!outlines.isEmpty()) {
                    event.addCustomRenderer(new VibraniumOutlineRenderer(outlines));
                }
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

    private record OutlineData(VoxelShape shape, double dx, double dy, double dz) {}

    private static class VibraniumOutlineRenderer implements CustomBlockOutlineRenderer {
        private final List<OutlineData> outlines;

        public VibraniumOutlineRenderer(List<OutlineData> outlines) {
            this.outlines = outlines;
        }

        @Override
        public boolean render(BlockOutlineRenderState renderState, net.minecraft.client.renderer.SubmitNodeCollector collector, PoseStack poseStack, LevelRenderState levelRenderState) {
            int red = (int) (ClientConfig.OUTLINE_RED.get() * 255.0);
            int green = (int) (ClientConfig.OUTLINE_GREEN.get() * 255.0);
            int blue = (int) (ClientConfig.OUTLINE_BLUE.get() * 255.0);
            int alpha = (int) (ClientConfig.OUTLINE_ALPHA.get() * 255.0);
            int color = ARGB.color(alpha, red, green, blue);

            for (OutlineData data : outlines) {
                poseStack.pushPose();
                poseStack.translate(data.dx, data.dy, data.dz);
                
                collector.submitShapeOutline(
                    poseStack, 
                    data.shape, 
                    RenderTypes.lines(),
                    color,
                    2.0F,
                    false
                );

                poseStack.popPose();
            }

            return true;
        }
    }
}