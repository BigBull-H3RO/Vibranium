package de.bigbull.vibranium.init.custom.shield;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.TextureAtlasStitchedEvent;
import org.jetbrains.annotations.ApiStatus;

import java.util.Set;

@EventBusSubscriber(modid = Vibranium.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModelPredicateProvider {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            addShieldPropertyOverrides(ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "blocking"),
                    (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F,
                    ItemInit.VIBRANIUM_SHIELD.get()
            );
        });
    }

    private static void addShieldPropertyOverrides(ResourceLocation override, ClampedItemPropertyFunction propertyGetter,
                                                   ItemLike... shields) {
        for (ItemLike shield : shields) {
            ItemProperties.register(shield.asItem(), override, propertyGetter);
        }
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onStitch(Pre event) {
        if (event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            for (Material textures : new Material[] {
                    LOCATION_VIBRANIUM_SHIELD_BASE,
                    LOCATION_VIBRANIUM_SHIELD_BASE_NOPATTERN,
            }) {
                event.addSprite(textures.texture());
            }
        }
    }

    public static class Pre extends TextureAtlasStitchedEvent {
        private final Set<ResourceLocation> sprites;

        @ApiStatus.Internal
        public Pre(TextureAtlas map, Set<ResourceLocation> sprites) {
            super(map);
            this.sprites = sprites;
        }

        public boolean addSprite(ResourceLocation sprite) {
            return this.sprites.add(sprite);
        }
    }

    public static final Material LOCATION_VIBRANIUM_SHIELD_BASE = material("entity/vibranium_shield_base");
    public static final Material LOCATION_VIBRANIUM_SHIELD_BASE_NOPATTERN = material("entity/vibranium_shield_base_nopattern");

    @SuppressWarnings("deprecation")
    private static Material material(String path) {
        return new Material(
                TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, path));
    }
}