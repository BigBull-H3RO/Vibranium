package de.bigbull.vibranium.init.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.equipment.ShieldModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public class ShieldRenderer implements SpecialModelRenderer<DataComponentMap> {
    private final SpriteGetter sprites;
    private final ShieldModel model;

    private static final class Sprites {
        static final SpriteId VIBRANIUM_SHIELD_BASE = new SpriteId(Sheets.SHIELD_SHEET,
                Identifier.fromNamespaceAndPath(Vibranium.MODID, "entity/vibranium_shield_base"));
        static final SpriteId VIBRANIUM_SHIELD_BASE_NO_PATTERN = new SpriteId(Sheets.SHIELD_SHEET,
                Identifier.fromNamespaceAndPath(Vibranium.MODID, "entity/vibranium_shield_base_nopattern"));
    }

    public ShieldRenderer(SpriteGetter sprites, ShieldModel model) {
        this.sprites = sprites;
        this.model = model;
    }

    @Override
    public void submit(@Nullable DataComponentMap components, PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil,
            int outlineColor) {
        BannerPatternLayers patterns = components != null
                ? components.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                : BannerPatternLayers.EMPTY;
        DyeColor baseColor = components != null ? components.get(DataComponents.BASE_COLOR) : null;
        boolean hasPatterns = !patterns.layers().isEmpty() || baseColor != null;
        SpriteId base = hasPatterns ? Sprites.VIBRANIUM_SHIELD_BASE : Sprites.VIBRANIUM_SHIELD_BASE_NO_PATTERN;

        submitNodeCollector.submitModel(this.model, Unit.INSTANCE, poseStack, lightCoords, overlayCoords, -1, base,
                this.sprites, outlineColor, null);
        if (hasPatterns) {
            BannerRenderer.submitPatterns(
                    this.sprites,
                    poseStack,
                    submitNodeCollector,
                    lightCoords,
                    overlayCoords,
                    this.model,
                    Unit.INSTANCE,
                    false,
                    Objects.requireNonNullElse(baseColor, DyeColor.WHITE),
                    patterns,
                    null);
        }

        if (hasFoil) {
            submitNodeCollector.submitModel(
                    this.model,
                    Unit.INSTANCE,
                    poseStack,
                    RenderTypes.entityGlint(),
                    lightCoords,
                    overlayCoords,
                    -1,
                    this.sprites.get(base),
                    0,
                    null);
        }
    }

    @Override
    public void getExtents(Consumer<Vector3fc> p_470829_) {
        PoseStack posestack = new PoseStack();
        this.model.root().getExtentsForGui(posestack, p_470829_);
    }

    @Nullable
    @Override
    public DataComponentMap extractArgument(ItemStack stack) {
        return stack.immutableComponents();
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked<DataComponentMap> {
        public static final ShieldRenderer.Unbaked INSTANCE = new ShieldRenderer.Unbaked();
        public static final MapCodec<ShieldRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        @Override
        public MapCodec<ShieldRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public ShieldRenderer bake(SpecialModelRenderer.BakingContext bakingContext) {
            return new ShieldRenderer(bakingContext.sprites(),
                    new ShieldModel(bakingContext.entityModelSet().bakeLayer(ModelLayers.SHIELD)));
        }
    }
}
