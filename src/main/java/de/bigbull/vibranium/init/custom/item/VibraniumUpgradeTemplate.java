package de.bigbull.vibranium.init.custom.item;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class VibraniumUpgradeTemplate extends SmithingTemplateItem {
    public static final Component VIBRANIUM_UPGRADE_APPLIES_TO = Component.translatable(
                    Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Vibranium.MODID, "smithing_template.vibranium_upgrade.applies_to"))
            )
            .withStyle(ChatFormatting.BLUE);
    public static final Component VIBRANIUM_UPGRADE_INGREDIENTS = Component.translatable(
                    Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Vibranium.MODID, "smithing_template.vibranium_upgrade.ingredients"))
            )
            .withStyle(ChatFormatting.BLUE);
    public static final Component VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Vibranium.MODID, "smithing_template.vibranium_upgrade.base_slot_description"))
    );
    public static final Component VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Vibranium.MODID, "smithing_template.vibranium_upgrade.additions_slot_description"))
    );

    private static final Identifier EMPTY_SLOT_HELMET = Identifier.withDefaultNamespace("container/slot/helmet");
    private static final Identifier EMPTY_SLOT_CHESTPLATE = Identifier.withDefaultNamespace("container/slot/chestplate");
    private static final Identifier EMPTY_SLOT_LEGGINGS = Identifier.withDefaultNamespace("container/slot/leggings");
    private static final Identifier EMPTY_SLOT_BOOTS = Identifier.withDefaultNamespace("container/slot/boots");
    private static final Identifier EMPTY_SLOT_HOE = Identifier.withDefaultNamespace("container/slot/hoe");
    private static final Identifier EMPTY_SLOT_AXE = Identifier.withDefaultNamespace("container/slot/axe");
    private static final Identifier EMPTY_SLOT_SWORD = Identifier.withDefaultNamespace("container/slot/sword");
    private static final Identifier EMPTY_SLOT_SHOVEL = Identifier.withDefaultNamespace("container/slot/shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE = Identifier.withDefaultNamespace("container/slot/pickaxe");
    private static final Identifier EMPTY_SLOT_INGOT = Identifier.withDefaultNamespace("container/slot/ingot");
    private static final Identifier EMPTY_SLOT_WOLF_ARMOR = Identifier.fromNamespaceAndPath(Vibranium.MODID, "container/slot/wolf_armor");
    private static final Identifier EMPTY_SLOT_HORSE_ARMOR = Identifier.fromNamespaceAndPath(Vibranium.MODID, "container/slot/horse_armor");

    public VibraniumUpgradeTemplate(
            Component appliesTo,
            Component ingredients,
            Component baseSlotDescription,
            Component additionsSlotDescription,
            List<Identifier> baseSlotEmptyIcons,
            List<Identifier> additionalSlotEmptyIcons,
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

    private static List<Identifier> createVibraniumUpgradeIconList() {
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

    private static List<Identifier> createVibraniumUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
