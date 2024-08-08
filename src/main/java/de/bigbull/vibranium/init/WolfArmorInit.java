package de.bigbull.vibranium.init;

import com.google.common.base.Suppliers;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.UUID;
import java.util.function.Supplier;

public class WolfArmorInit extends AnimalArmorItem {
    private static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BODY, UUID.fromString("C1C72771-8B8E-BA4A-ACE0-81A93C8928B2"));
    });
    private final Supplier<ItemAttributeModifiers> defaultModifiers;
    public final Holder<ArmorMaterial> armorMaterialHolder;
    private final ResourceLocation textureLocation;
    @Nullable
    private final ResourceLocation overlayTextureLocation;

    public WolfArmorInit(Holder<ArmorMaterial> armorMaterial, BodyType bodyType, boolean pboolean, Properties properties) {
        super(armorMaterial, bodyType, pboolean, properties);
        this.armorMaterialHolder = armorMaterial;
        ResourceLocation resourcelocation = armorMaterial.unwrapKey().orElseThrow().location().withPath(p -> "textures/entity/wolf/armor/wolf_armor_" + p);
        this.textureLocation = resourcelocation.withSuffix(".png");
        if (pboolean) {
            this.overlayTextureLocation = resourcelocation.withSuffix("_overlay.png");
        } else {
            this.overlayTextureLocation = null;
        }

        ResourceLocation resourcelocation2 = ResourceLocation.withDefaultNamespace("armor." + Type.BODY.getName());

        this.defaultModifiers = Suppliers.memoize(
                () -> {
                    int i = armorMaterial.value().getDefense(Type.BODY);
                    float f = armorMaterial.value().toughness() * 1;
                    ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
                    EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(Type.BODY.getSlot());
                    UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(Type.BODY);

                    itemattributemodifiers$builder.add(
                            Attributes.ARMOR, new AttributeModifier(resourcelocation2, (double) i, AttributeModifier.Operation.ADD_VALUE), equipmentslotgroup
                    );
                    itemattributemodifiers$builder.add(
                            Attributes.ARMOR_TOUGHNESS,
                            new AttributeModifier(resourcelocation2, (double) f, AttributeModifier.Operation.ADD_VALUE),
                            equipmentslotgroup
                    );
                    float f1 = armorMaterial.value().knockbackResistance() * 1;
                    if (f1 > 0.0F) {
                        itemattributemodifiers$builder.add(
                                Attributes.KNOCKBACK_RESISTANCE,
                                new AttributeModifier(resourcelocation2, (double) f1, AttributeModifier.Operation.ADD_VALUE),
                                equipmentslotgroup
                        );
                    }

                    return itemattributemodifiers$builder.build();
                }
        );
    }

    @Override
    public ResourceLocation getTexture() {
        return this.textureLocation;
    }

    @Nullable
    @Override
    public ResourceLocation getOverlayTexture() {
        return this.overlayTextureLocation;
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return this.defaultModifiers.get();
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }
}