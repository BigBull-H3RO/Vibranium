package de.bigbull.vibranium.init.custom.item;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class VibraniumUpgradeTemplate extends SmithingTemplateItem {
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    public static final Component VIBRANIUM_UPGRADE_APPLIES_TO = Component.translatable(
                    Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "smithing_template.vibranium_upgrade.applies_to"))
            )
            .withStyle(DESCRIPTION_FORMAT);
    public static final Component VIBRANIUM_UPGRADE_INGREDIENTS = Component.translatable(
                    Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "smithing_template.vibranium_upgrade.ingredients"))
            )
            .withStyle(DESCRIPTION_FORMAT);
    public static final Component VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "smithing_template.vibranium_upgrade.base_slot_description"))
    );
    public static final Component VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "smithing_template.vibranium_upgrade.additions_slot_description"))
    );

    private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.withDefaultNamespace("container/slot/helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("container/slot/chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("container/slot/leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("container/slot/boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.withDefaultNamespace("container/slot/hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.withDefaultNamespace("container/slot/axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("container/slot/sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.withDefaultNamespace("container/slot/shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("container/slot/pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("container/slot/ingot");
    private static final ResourceLocation EMPTY_SLOT_WOLF_ARMOR = ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "container/slot/wolf_armor");
    private static final ResourceLocation EMPTY_SLOT_HORSE_ARMOR = ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "container/slot/horse_armor");

    public VibraniumUpgradeTemplate(
            Component appliesTo,
            Component ingredients,
            Component baseSlotDescription,
            Component additionsSlotDescription,
            List<ResourceLocation> baseSlotEmptyIcons,
            List<ResourceLocation> additionalSlotEmptyIcons,
            Properties properties) {
        super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription, baseSlotEmptyIcons, additionalSlotEmptyIcons, properties);
    }

    public static SmithingTemplateItem createVibraniumUpgradeTemplate(Item.Properties properties) {
        return new SmithingTemplateItem(
                VIBRANIUM_UPGRADE_APPLIES_TO,
                VIBRANIUM_UPGRADE_INGREDIENTS,
                VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION,
                VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                createVibraniumUpgradeIconList(),
                createVibraniumUpgradeMaterialList(),
                properties
        );
    }

    private static List<ResourceLocation> createVibraniumUpgradeIconList() {
        return List.of(
                EMPTY_SLOT_HELMET,
                EMPTY_SLOT_SWORD,
                EMPTY_SLOT_CHESTPLATE,
                EMPTY_SLOT_PICKAXE,
                EMPTY_SLOT_WOLF_ARMOR,
                EMPTY_SLOT_LEGGINGS,
                EMPTY_SLOT_AXE,
                EMPTY_SLOT_BOOTS,
                EMPTY_SLOT_HORSE_ARMOR,
                EMPTY_SLOT_HOE,
                EMPTY_SLOT_SHOVEL
        );
    }

    private static List<ResourceLocation> createVibraniumUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
