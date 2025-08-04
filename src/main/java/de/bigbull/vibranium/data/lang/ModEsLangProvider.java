package de.bigbull.vibranium.data.lang;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.CreativeTabInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.custom.item.VibraniumUpgradeTemplate;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEsLangProvider extends LanguageProvider {
    public ModEsLangProvider(PackOutput output) {
        super(output, Vibranium.MODID, "es_es");
    }

    @Override
    protected void addTranslations() {
        // Items
        addItem(ItemInit.RAW_VIBRANIUM, "Vibranium en bruto");
        addItem(ItemInit.VIBRANIUM_INGOT, "Lingote de Vibranium");
        addItem(ItemInit.VIBRANIUM_NUGGET, "Pepita de Vibranium");
        addItem(ItemInit.VIBRANIUM_PLATE, "Placa de Vibranium");
        addItem(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, "Plantilla de herrería");
//        addItem(ItemInit.VIBRA_GOLEM_SPAWN_EGG, "Huevo de invocación de Golem Vibra");
        addItem(ItemInit.VIBRANIUM_CORE, "Núcleo de Vibranium");
        addItem(ItemInit.HEART_SHAPED_HERB, "Hierba en forma de corazón");
        addItem(ItemInit.SOUL_HERB_MIXTURE, "Mezcla de hierbas de alma");
        addItem(ItemInit.VIBRANIUM_CRYSTAL_SHARD, "Fragmento de cristal de Vibranium");
        addItem(ItemInit.SOULWOOD_SIGN, "Letrero de madera de alma");
        addItem(ItemInit.SOULWOOD_HANGING_SIGN, "Letrero colgante de madera de alma");
        addItem(ItemInit.SOULWOOD_BOAT, "Barco de madera de alma");
        addItem(ItemInit.SOULWOOD_CHEST_BOAT, "Barco de madera de alma con cofre");

        // Blocks
        addBlock(BlockInit.BLOCK_OF_RAW_VIBRANIUM, "Bloque de Vibranium en bruto");
        addBlock(BlockInit.DEPPSLATE_VIBRANIUM_ORE, "Mena de Vibranium de pizarra profunda");
        addBlock(BlockInit.VIBRANIUM_BLOCK, "Bloque de Vibranium");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_DIRT, "Tierra enriquecida con Vibranium");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_FARMLAND, "Tierra de cultivo enriquecida con Vibranium");
        addBlock(BlockInit.SOULWOOD_LOG, "Tronco de madera de alma");
        addBlock(BlockInit.SOULWOOD_SAPLING, "Brote de madera de alma");
        addBlock(BlockInit.SOULWOOD_WOOD, "Madera de alma");
        addBlock(BlockInit.STRIPPED_SOULWOOD_LOG, "Tronco de madera de alma pelado");
        addBlock(BlockInit.STRIPPED_SOULWOOD_WOOD, "Madera de alma pelada");
        addBlock(BlockInit.SOULWOOD_PLANKS, "Tablones de madera de alma");
        addBlock(BlockInit.SOULWOOD_LEAVES, "Hojas de alma");
        addBlock(BlockInit.SOULWOOD_SLAB, "Losa de madera de alma");
        addBlock(BlockInit.SOULWOOD_STAIRS, "Escaleras de madera de alma");
        addBlock(BlockInit.SOULWOOD_FENCE, "Valla de madera de alma");
        addBlock(BlockInit.SOULWOOD_FENCE_GATE, "Puerta de valla de madera de alma");
        addBlock(BlockInit.SOULWOOD_DOOR, "Puerta de madera de alma");
        addBlock(BlockInit.SOULWOOD_TRAPDOOR, "Trampilla de madera de alma");
        addBlock(BlockInit.SOULWOOD_PRESSURE_PLATE, "Placa de presión de madera de alma");
        addBlock(BlockInit.SOULWOOD_BUTTON, "Botón de madera de alma");
        addBlock(BlockInit.VIBRANIUM_CRYSTAL_BLOCK, "Bloque de cristal de Vibranium");
        addBlock(BlockInit.BUDDING_VIBRANIUM_CRYSTAL, "Brote de cristal de Vibranium");
        addBlock(BlockInit.VIBRANIUM_CLUSTER, "Racimo de cristal de Vibranium");
        addBlock(BlockInit.SMALL_VIBRANIUM_BUD, "Brote pequeño de Vibranium");
        addBlock(BlockInit.MEDIUM_VIBRANIUM_BUD, "Brote mediano de Vibranium");
        addBlock(BlockInit.LARGE_VIBRANIUM_BUD, "Brote grande de Vibranium");
        addBlock(BlockInit.POTTED_SOULWOOD_SAPLING, "Brote de madera de alma en maceta");

        // Tools
        addItem(ItemInit.VIBRANIUM_SWORD, "Espada de Vibranium");
        addItem(ItemInit.VIBRANIUM_PICKAXE, "Pico de Vibranium");
        addItem(ItemInit.VIBRANIUM_AXE, "Hacha de Vibranium");
        addItem(ItemInit.VIBRANIUM_SHOVEL, "Pala de Vibranium");
        addItem(ItemInit.VIBRANIUM_HOE, "Azada de Vibranium");
        addItem(ItemInit.VIBRANIUM_MACE, "Maza de Vibranium");
        addItem(ItemInit.VIBRANIUM_SHIELD, "Escudo de Vibranium");

        // Armors
        addItem(ItemInit.VIBRANIUM_BOOTS, "Botas de Vibranium");
        addItem(ItemInit.VIBRANIUM_LEGGINGS, "Pantalones de Vibranium");
        addItem(ItemInit.VIBRANIUM_CHESTPLATE, "Peto de Vibranium");
        addItem(ItemInit.VIBRANIUM_HELMET, "Casco de Vibranium");
        addItem(ItemInit.VIBRANIUM_TURTLE_HELMET, "Casco de tortuga de Vibranium");
        addItem(ItemInit.VIBRANIUM_WOLF_ARMOR, "Armadura de lobo de Vibranium");
        addItem(ItemInit.VIBRANIUM_HORSE_ARMOR, "Armadura de caballo de Vibranium");

        // Smithing Template
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_APPLIES_TO.getString(), "Equipo de netherita");
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_INGREDIENTS.getString(), "Lingote de Vibranium");
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Añade armadura, herramienta o arma de netherita");
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Añade lingote de Vibranium");

        // Potion
        addItem(ItemInit.SOUL_HERB_ELIXIR, "Elixir de hierba de alma");
        addItem(ItemInit.SOUL_HERB_ELIXIR_EXTENDED, "Elixir de hierba de alma");
        addItem(ItemInit.SOUL_HERB_ELIXIR_ENHANCED, "Elixir de hierba de alma");
        add("effect.vibranium.kinetic_redistribution", "Redistribución cinética");

        // KeyBinding
        add("key.categories.vibranium", "Vibranium");
        add("key.vibranium.toggle_outline", "Alternar contorno");

        // Enchantments
        add("enchantment.vibranium.universal_breaker", "Destructor universal");
        add("enchantment.vibranium.universal_breaker.desc", "Permite romper todos los bloques del área, sin importar la herramienta necesaria.");

        // JEI
        add("jei.vibranium.heart_shaped_herb.desc",
                "La hierba en forma de corazón se puede encontrar en portales en ruinas. Ten cuidado, consumirla tiene efectos tanto positivos como negativos.");
        add("jei.vibranium.vibranium_upgrade_smithing_template.desc",
                "Esta plantilla de herrería se puede encontrar en una cámara de pruebas, escondida en un barril cerca de unas camas.");
        add("jei.vibranium.enriched_vibranium_dirt.desc",
                "Para obtener tierra enriquecida con Vibranium, haz clic derecho sobre tierra normal con Vibranium en bruto. Puedes separar el Vibranium usando una azada.");
        add("jei.vibranium.vibranium_core.desc",
                "Este misterioso núcleo emite la misma energía que los Golems Vibra. ¿Qué pasará si se encuentran?");
        add("jei.vibranium.enriched_vibranium_farmland.desc",
                "La energía misteriosa de la tierra de cultivo enriquecida con Vibranium parece acelerar el crecimiento de las plantas. Puedes recuperar el Vibranium usando una azada.");

        // Trim Materials
        add("trim_material.vibranium.vibranium", "Material de Vibranium");

        // Vibranium Mace
        add("item.vibranium_mace.tooltip", "Puede minar bloques en 3x3");

        // Others
        add(CreativeTabInit.MAIN_TAB_ONE_TITLE, "Vibranium");

        // --- Config translations: Titles ---
        add("vibranium.config.title.mace", "Configuración de la maza de Vibranium");

        // --- Config translations: Mace ---
        add("vibranium.config.useFastMode", "Modo rápido para la maza");

        // --- Config translations: Outline (Client) ---
        add("vibranium.config.outlineRed", "Componente rojo del contorno");
        add("vibranium.config.outlineGreen", "Componente verde del contorno");
        add("vibranium.config.outlineBlue", "Componente azul del contorno");
        add("vibranium.config.outlineAlpha", "Transparencia del contorno");
    }
}