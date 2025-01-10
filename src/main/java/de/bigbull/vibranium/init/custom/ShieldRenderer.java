package de.bigbull.vibranium.init.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.MapCodec;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class ShieldRenderer implements SpecialModelRenderer<DataComponentMap> {
    private final ShieldModel model;

    public static final Material VIBRANIUM_SHIELD_BASE = new Material(Sheets.SHIELD_SHEET, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "entity/vibranium_shield_base"));
    public static final Material VIBRANIUM_SHIELD_BASE_NO_PATTERN = new Material(Sheets.SHIELD_SHEET, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "entity/vibranium_shield_base_nopattern"));

    public ShieldRenderer(ShieldModel model) {
        this.model = model;
    }

    public void render(@Nullable DataComponentMap p_386991_, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource bufferSource, int p_387382_, int p_387013_, boolean p_387902_) {
        BannerPatternLayers bannerpatternlayers = p_386991_ != null
                ? p_386991_.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                : BannerPatternLayers.EMPTY;
        DyeColor dyecolor = p_386991_ != null ? p_386991_.get(DataComponents.BASE_COLOR) : null;
        boolean flag = !bannerpatternlayers.layers().isEmpty() || dyecolor != null;
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        Material material = flag ? VIBRANIUM_SHIELD_BASE : VIBRANIUM_SHIELD_BASE_NO_PATTERN;
        VertexConsumer vertexconsumer = material.sprite()
                .wrap(ItemRenderer.getFoilBuffer(bufferSource, this.model.renderType(material.atlasLocation()), context == ItemDisplayContext.GUI, p_387902_));
        this.model.handle().render(poseStack, vertexconsumer, p_387382_, p_387013_);
        if (flag) {
            BannerRenderer.renderPatterns(
                    poseStack,
                    bufferSource,
                    p_387382_,
                    p_387013_,
                    this.model.plate(),
                    material,
                    false,
                    Objects.requireNonNullElse(dyecolor, DyeColor.WHITE),
                    bannerpatternlayers,
                    p_387902_,
                    false
            );
        } else {
            this.model.plate().render(poseStack, vertexconsumer, p_387382_, p_387013_);
        }
        poseStack.popPose();
    }

    @Nullable
    @Override
    public DataComponentMap extractArgument(ItemStack stack) {
        return stack.immutableComponents();
    }

    @OnlyIn(Dist.CLIENT)
    public static record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final ShieldRenderer.Unbaked INSTANCE = new ShieldRenderer.Unbaked();
        public static final MapCodec<ShieldRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        @Override
        public MapCodec<ShieldRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public SpecialModelRenderer<?> bake(EntityModelSet entityModelSet) {
            return new ShieldRenderer(new ShieldModel(entityModelSet.bakeLayer(ModelLayers.SHIELD)));
        }
    }
}
