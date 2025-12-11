package de.bigbull.vibranium.init.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.equipment.ShieldModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import org.joml.Vector3fc;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

@EventBusSubscriber(value = Dist.CLIENT)
public class ShieldRenderer implements SpecialModelRenderer<DataComponentMap> {
    private final MaterialSet materials;
    private final ShieldModel model;

    public static final Material VIBRANIUM_SHIELD_BASE = new Material(Sheets.SHIELD_SHEET, Identifier.fromNamespaceAndPath(Vibranium.MODID, "entity/vibranium_shield_base"));
    public static final Material VIBRANIUM_SHIELD_BASE_NO_PATTERN = new Material(Sheets.SHIELD_SHEET, Identifier.fromNamespaceAndPath(Vibranium.MODID, "entity/vibranium_shield_base_nopattern"));

    public ShieldRenderer(MaterialSet materials, ShieldModel model) {
        this.materials = materials;
        this.model = model;
    }

    public void submit(@Nullable DataComponentMap dataComponents, ItemDisplayContext context, PoseStack poseStack, SubmitNodeCollector collector, int p_386748_, int p_388858_, boolean p_387642_, int p_451675_) {
        BannerPatternLayers bannerpatternlayers = dataComponents != null
                ? dataComponents.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                : BannerPatternLayers.EMPTY;
        DyeColor dyecolor = dataComponents != null ? dataComponents.get(DataComponents.BASE_COLOR) : null;
        boolean flag = !bannerpatternlayers.layers().isEmpty() || dyecolor != null;
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        Material material = flag ? VIBRANIUM_SHIELD_BASE : VIBRANIUM_SHIELD_BASE_NO_PATTERN;
        collector.submitModelPart(
                this.model.handle(),
                poseStack,
                this.model.renderType(material.atlasLocation()),
                p_386748_,
                p_388858_,
                this.materials.get(material),
                false,
                false,
                -1,
                null,
                p_451675_
        );
        if (flag) {
            BannerRenderer.submitPatterns(
                    this.materials,
                    poseStack,
                    collector,
                    p_386748_,
                    p_388858_,
                    this.model,
                    Unit.INSTANCE,
                    material,
                    false,
                    Objects.requireNonNullElse(dyecolor, DyeColor.WHITE),
                    bannerpatternlayers,
                    p_387642_,
                    null,
                    p_451675_
            );
        } else {
            collector.submitModelPart(
                    this.model.plate(),
                    poseStack,
                    this.model.renderType(material.atlasLocation()),
                    p_386748_,
                    p_388858_,
                    this.materials.get(material),
                    false,
                    p_387642_,
                    -1,
                    null,
                    p_451675_
            );
        }

        poseStack.popPose();
    }

    @Override
    public void getExtents(Consumer<Vector3fc> p_470829_) {
        PoseStack posestack = new PoseStack();
        posestack.scale(1.0F, -1.0F, -1.0F);
        this.model.root().getExtentsForGui(posestack, p_470829_);
    }

    @Nullable
    @Override
    public DataComponentMap extractArgument(ItemStack stack) {
        return stack.immutableComponents();
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final ShieldRenderer.Unbaked INSTANCE = new ShieldRenderer.Unbaked();
        public static final MapCodec<ShieldRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        @Override
        public MapCodec<ShieldRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext bakingContext) {
            return new ShieldRenderer(bakingContext.materials(), new ShieldModel(bakingContext.entityModelSet().bakeLayer(ModelLayers.SHIELD)));
        }
    }
}
