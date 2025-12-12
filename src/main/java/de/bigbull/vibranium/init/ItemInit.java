package de.bigbull.vibranium.init;

import de.bigbull.vibranium.init.custom.item.HSHPotionItems;
import de.bigbull.vibranium.init.custom.item.VibraniumMaceItem;
import de.bigbull.vibranium.init.custom.item.VibraniumUpgradeTemplate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Optional;

public class ItemInit {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(de.bigbull.vibranium.Vibranium.MODID);

    //Items
    public static final DeferredItem<Item> RAW_VIBRANIUM = ITEMS.registerItem("raw_vibranium",
            properties -> new Item(properties
                    .fireResistant()));

    public static final DeferredItem<Item> VIBRANIUM_INGOT = ITEMS.registerItem("vibranium_ingot",
            properties -> new Item(properties
                    .fireResistant()));

    public static final DeferredItem<Item> VIBRANIUM_NUGGET = ITEMS.registerItem("vibranium_nugget",
            properties -> new Item(properties
                    .fireResistant()));

    public static final DeferredItem<Item> VIBRANIUM_PLATE = ITEMS.registerItem("vibranium_plate",
            properties -> new Item(properties
                    .fireResistant()));

    public static final DeferredItem<Item> VIBRANIUM_CORE = ITEMS.registerItem("vibranium_core",
            properties -> new Item(properties
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .stacksTo(1)));

    public static final DeferredItem<Item> VIBRANIUM_CRYSTAL_SHARD = ITEMS.registerItem("vibranium_crystal_shard",
            properties -> new Item(properties.trimMaterial(TrimMaterialsInit.VIBRANIUM)
                    .fireResistant()
                    .stacksTo(64)));

    public static final DeferredItem<Item> SOUL_HERB_MIXTURE = ITEMS.registerItem("soul_herb_mixture",
            properties -> new Item(properties
                    .stacksTo(16)));

//    public static final DeferredItem<SpawnEggItem> VIBRA_GOLEM_SPAWN_EGG = ITEMS.registerItem("vibra_golem_spawn_egg",
//            properties -> new SpawnEggItem(Entitiesinit.VIBRAGOLEM.get(), properties));

    public static final DeferredItem<Item> VIBRANIUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("vibranium_upgrade_smithing_template",
            VibraniumUpgradeTemplate::createVibraniumUpgradeTemplate, properties -> new Item.Properties()
                            .fireResistant()
                            .rarity(Rarity.UNCOMMON));

    //Armor
    public static final DeferredItem<Item> VIBRANIUM_BOOTS = ITEMS.registerItem("vibranium_boots",
            properties -> new Item(properties.humanoidArmor(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorType.BOOTS)
                    .fireResistant()
                    .stacksTo(1)));

    public static final DeferredItem<Item> VIBRANIUM_LEGGINGS = ITEMS.registerItem("vibranium_leggings",
            properties -> new Item(properties.humanoidArmor(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorType.LEGGINGS)
                    .fireResistant()
                    .stacksTo(1)));

    public static final DeferredItem<Item> VIBRANIUM_CHESTPLATE = ITEMS.registerItem("vibranium_chestplate",
            properties -> new Item(properties.humanoidArmor(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorType.CHESTPLATE)
                    .fireResistant()
                    .stacksTo(1)));

    public static final DeferredItem<Item> VIBRANIUM_HELMET = ITEMS.registerItem("vibranium_helmet",
            properties -> new Item(properties.humanoidArmor(ArmorMaterialsInit.VIBRANIUM_MATERIAL, ArmorType.HELMET)
                    .fireResistant()
                    .stacksTo(1)));

    public static final DeferredItem<Item> VIBRANIUM_TURTLE_HELMET = ITEMS.registerItem("vibranium_turtle_helmet",
            properties -> new Item(properties.humanoidArmor(ArmorMaterialsInit.VIBRANIUM_MATERIAL_TURTLE, ArmorType.HELMET)
                    .fireResistant()
                    .stacksTo(1)));

    public static final DeferredItem<Item> VIBRANIUM_WOLF_ARMOR = ITEMS.registerItem("vibranium_wolf_armor",
            properties -> new Item(properties.wolfArmor(ArmorMaterialsInit.VIBRANIUM_MATERIAL_WOLF)
                    .fireResistant()
                    .stacksTo(1)));

    public static final DeferredItem<Item> VIBRANIUM_HORSE_ARMOR = ITEMS.registerItem("vibranium_horse_armor",
            properties -> new Item(properties.horseArmor(ArmorMaterialsInit.VIBRANIUM_MATERIAL)
                    .fireResistant()
                    .stacksTo(1)));

    public static final DeferredItem<Item> VIBRANIUM_NAUTILUS_ARMOR = ITEMS.registerItem("vibranium_nautilus_armor",
            properties -> new Item(properties.nautilusArmor(ArmorMaterialsInit.VIBRANIUM_MATERIAL)
                    .fireResistant()
                    .stacksTo(1)));

    //Tools
    public static final DeferredItem<Item> VIBRANIUM_SWORD = ITEMS.registerItem("vibranium_sword",
            properties -> new Item(properties.sword(MaterialsInit.VIBRANIUM, 3.0f, -2.2f)
                    .fireResistant()));

    public static final DeferredItem<Item> VIBRANIUM_PICKAXE = ITEMS.registerItem("vibranium_pickaxe",
            properties -> new Item(properties.pickaxe(MaterialsInit.VIBRANIUM, 0.0f, -2.8f)
                    .fireResistant()));

    public static final DeferredItem<Item> VIBRANIUM_AXE = ITEMS.registerItem("vibranium_axe",
            properties -> new Item(properties.axe(MaterialsInit.VIBRANIUM, 5.0f, -3.0f)
                    .fireResistant()));

    public static final DeferredItem<Item> VIBRANIUM_SHOVEL = ITEMS.registerItem("vibranium_shovel",
            properties -> new Item(properties.shovel(MaterialsInit.VIBRANIUM, 0.5f, -3.0f)
                    .fireResistant()));

    public static final DeferredItem<Item> VIBRANIUM_HOE = ITEMS.registerItem("vibranium_hoe",
            properties -> new Item(properties.hoe(MaterialsInit.VIBRANIUM, -5.0f, 0.0f)
                    .fireResistant()));

    public static final DeferredItem<Item> VIBRANIUM_SPEAR = ITEMS.registerItem("vibranium_spear",
            properties -> new Item(properties.spear(MaterialsInit.VIBRANIUM,
                    1.25F, 1.3F, 0.3F, 2.0F, 6.0F, 4.5F, 5.1F, 7.5F, 4.6F)
                    .fireResistant()));

    public static final DeferredItem<VibraniumMaceItem> VIBRANIUM_MACE = ITEMS.registerItem("vibranium_mace",
            properties -> new VibraniumMaceItem(MaterialsInit.VIBRANIUM, properties
                    .fireResistant()
                    .rarity(Rarity.EPIC)
                    .durability(1260)
                    .repairable(VIBRANIUM_INGOT.asItem())
                    .component(DataComponents.TOOL, VibraniumMaceItem.createToolProperties())
                    .attributes(VibraniumMaceItem.createAttributes())
                    .enchantable(18)
                    .component(DataComponents.WEAPON, new Weapon(1))));

    public static final DeferredItem<Item> VIBRANIUM_SHIELD = ITEMS.registerItem("vibranium_shield",
            properties -> new ShieldItem(properties
                    .fireResistant()
                    .stacksTo(1)
                    .durability(1024)
                    .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                    .repairable(TagsInit.Items.VIBRANIUM_REPAIR)
                    .equippableUnswappable(EquipmentSlot.OFFHAND)
                    .component(
                            DataComponents.BLOCKS_ATTACKS,
                            new BlocksAttacks(
                                    0.25F,
                                    1.0F,
                                    List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                    new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                    Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                                    Optional.of(SoundEvents.SHIELD_BLOCK),
                                    Optional.of(SoundEvents.SHIELD_BREAK)
                            )
                    )
                    .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)));

    //Advanced Items
    public static final DeferredItem<BlockItem> HEART_SHAPED_HERB = ITEMS.registerSimpleBlockItem("heart_shaped_herb",
            BlockInit.HEART_SHAPED_HERB_BUSH, properties -> new Item.Properties()
                    .food(FoodPropertiesInit.HEART_SHAPED_HERB, FoodPropertiesInit.HEART_SHAPED_HERB_EFFECT)
                    .rarity(Rarity.RARE)
                    .stacksTo(16));

    public static final DeferredItem<Item> SOUL_HERB_ELIXIR = ITEMS.registerItem("soul_herb_elixir",
            properties -> new HSHPotionItems(properties
                    .rarity(Rarity.RARE)
                    .stacksTo(1), 3600, 0));

    public static final DeferredItem<Item> SOUL_HERB_ELIXIR_EXTENDED = ITEMS.registerItem("soul_herb_elixir_extended",
            properties -> new HSHPotionItems(properties
                    .rarity(Rarity.RARE)
                    .stacksTo(1), 9600, 0));

    public static final DeferredItem<Item> SOUL_HERB_ELIXIR_ENHANCED = ITEMS.registerItem("soul_herb_elixir_enhanced",
            properties -> new HSHPotionItems(properties
                    .rarity(Rarity.RARE)
                    .stacksTo(1), 1800, 1));

    public static final DeferredItem<Item> SOULWOOD_SIGN = ITEMS.registerItem("soulwood_sign",
            properties -> new SignItem(BlockInit.SOULWOOD_SIGN.get(), BlockInit.SOULWOOD_WALL_SIGN.get(), properties
                    .stacksTo(16)));

    public static final DeferredItem<Item> SOULWOOD_HANGING_SIGN = ITEMS.registerItem("soulwood_hanging_sign",
            properties -> new HangingSignItem(BlockInit.SOULWOOD_HANGING_SIGN.get(), BlockInit.SOULWOOD_WALL_HANGING_SIGN.get(), properties
                    .stacksTo(16)));

    public static final DeferredItem<Item> SOULWOOD_BOAT = ITEMS.registerItem("soulwood_boat",
            properties -> new BoatItem(Entitiesinit.SOULWOOD_BOAT.get(), properties
                    .stacksTo(1)));

    public static final DeferredItem<Item> SOULWOOD_CHEST_BOAT = ITEMS.registerItem("soulwood_chest_boat",
            properties -> new BoatItem(Entitiesinit.SOULWOOD_CHEST_BOAT.get(), properties
                    .stacksTo(1)));
}