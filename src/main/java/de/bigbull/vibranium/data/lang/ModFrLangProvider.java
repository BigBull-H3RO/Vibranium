package de.bigbull.vibranium.data.lang;

import de.bigbull.vibranium.init.*;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.custom.item.VibraniumUpgradeTemplate;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModFrLangProvider extends LanguageProvider {
    public ModFrLangProvider(PackOutput output) {
        super(output, Vibranium.MODID, "fr_fr");
    }

    @Override
    protected void addTranslations() {
        // Items
        addItem(ItemInit.RAW_VIBRANIUM, "Vibranium brut");
        addItem(ItemInit.VIBRANIUM_INGOT, "Lingot de vibranium");
        addItem(ItemInit.VIBRANIUM_NUGGET, "Pépite de vibranium");
        addItem(ItemInit.VIBRANIUM_PLATE, "Plaque de vibranium");
        addItem(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, "Gabarit de forgeage");
        addItem(ItemInit.VIBRA_GOLEM_SPAWN_EGG, "Oeuf d'apparition de Golem Vibra");
        addItem(ItemInit.VIBRANIUM_CORE, "Noyau de vibranium");
        addItem(ItemInit.HEART_SHAPED_HERB, "Herbe en forme de coeur");
        addItem(ItemInit.SOUL_HERB_MIXTURE, "Mélange d'herbes d'âme");
        addItem(ItemInit.VIBRANIUM_CRYSTAL_SHARD, "Éclat de cristal de vibranium");
        addItem(ItemInit.SOULWOOD_SIGN, "Pancarte en bois d'âme");
        addItem(ItemInit.SOULWOOD_HANGING_SIGN, "Pancarte suspendue en bois d'âme");
        addItem(ItemInit.SOULWOOD_BOAT, "Bateau en bois d'âme");
        addItem(ItemInit.SOULWOOD_CHEST_BOAT, "Bateau en bois d'âme avec coffre");

        // Blocks
        addBlock(BlockInit.BLOCK_OF_RAW_VIBRANIUM, "Bloc de vibranium brut");
        addBlock(BlockInit.DEPPSLATE_VIBRANIUM_ORE, "Minerai de vibranium d'ardoise profonde");
        addBlock(BlockInit.VIBRANIUM_BLOCK, "Bloc de vibranium");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_DIRT, "Terre enrichie en vibranium");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_FARMLAND, "Terre agricole enrichie en vibranium");
        addBlock(BlockInit.SOULWOOD_LOG, "Bûche de bois d'âme");
        addBlock(BlockInit.SOULWOOD_SAPLING, "Pousse de bois d'âme");
        addBlock(BlockInit.SOULWOOD_WOOD, "Bois d'âme");
        addBlock(BlockInit.STRIPPED_SOULWOOD_LOG, "Bûche de bois d'âme écorcée");
        addBlock(BlockInit.STRIPPED_SOULWOOD_WOOD, "Bois d'âme écorcé");
        addBlock(BlockInit.SOULWOOD_PLANKS, "Planches de bois d'âme");
        addBlock(BlockInit.SOULWOOD_LEAVES, "Feuilles d'âme");
        addBlock(BlockInit.SOULWOOD_SLAB, "Dalle de bois d'âme");
        addBlock(BlockInit.SOULWOOD_STAIRS, "Escalier en bois d'âme");
        addBlock(BlockInit.SOULWOOD_FENCE, "Barrière en bois d'âme");
        addBlock(BlockInit.SOULWOOD_FENCE_GATE, "Portillon en bois d'âme");
        addBlock(BlockInit.SOULWOOD_DOOR, "Porte en bois d'âme");
        addBlock(BlockInit.SOULWOOD_TRAPDOOR, "Trappe en bois d'âme");
        addBlock(BlockInit.SOULWOOD_PRESSURE_PLATE, "Plaque de pression en bois d'âme");
        addBlock(BlockInit.SOULWOOD_BUTTON, "Bouton en bois d'âme");
        addBlock(BlockInit.VIBRANIUM_CRYSTAL_BLOCK, "Bloc de cristal de vibranium");
        addBlock(BlockInit.BUDDING_VIBRANIUM_CRYSTAL, "Cristal de vibranium en bourgeon");
        addBlock(BlockInit.VIBRANIUM_CLUSTER, "Amas de cristal de vibranium");
        addBlock(BlockInit.SMALL_VIBRANIUM_BUD, "Petit bourgeon de vibranium");
        addBlock(BlockInit.MEDIUM_VIBRANIUM_BUD, "Bourgeon moyen de vibranium");
        addBlock(BlockInit.LARGE_VIBRANIUM_BUD, "Grand bourgeon de vibranium");
        addBlock(BlockInit.POTTED_SOULWOOD_SAPLING, "Pousse de bois d'âme en pot");

        // Tools
        addItem(ItemInit.VIBRANIUM_SWORD, "Épée en vibranium");
        addItem(ItemInit.VIBRANIUM_PICKAXE, "Pioche en vibranium");
        addItem(ItemInit.VIBRANIUM_AXE, "Hache en vibranium");
        addItem(ItemInit.VIBRANIUM_SHOVEL, "Pelle en vibranium");
        addItem(ItemInit.VIBRANIUM_HOE, "Houe en vibranium");
        addItem(ItemInit.VIBRANIUM_MACE, "Masse en vibranium");
        addItem(ItemInit.VIBRANIUM_SHIELD, "Bouclier en vibranium");

        // Armors
        addItem(ItemInit.VIBRANIUM_BOOTS, "Bottes en vibranium");
        addItem(ItemInit.VIBRANIUM_LEGGINGS, "Jambières en vibranium");
        addItem(ItemInit.VIBRANIUM_CHESTPLATE, "Plastron en vibranium");
        addItem(ItemInit.VIBRANIUM_HELMET, "Casque en vibranium");
        addItem(ItemInit.VIBRANIUM_TURTLE_HELMET, "Casque de tortue en vibranium");
        addItem(ItemInit.VIBRANIUM_WOLF_ARMOR, "Armure de loup en vibranium");
        addItem(ItemInit.VIBRANIUM_HORSE_ARMOR, "Armure de cheval en vibranium");

        // Smithing Template
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_APPLIES_TO.getString(), "Équipement en netherite");
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_INGREDIENTS.getString(), "Lingot de vibranium");
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE.getString(), "Amélioration en vibranium");
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Ajouter une armure, un outil ou une arme en netherite");
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Ajouter un lingot de vibranium");

        // Potion
        addItem(ItemInit.SOUL_HERB_ELIXIR, "Élixir d'herbe d'âme");
        addItem(ItemInit.SOUL_HERB_ELIXIR_EXTENDED, "Élixir d'herbe d'âme");
        addItem(ItemInit.SOUL_HERB_ELIXIR_ENHANCED, "Élixir d'herbe d'âme");
        add("effect.vibranium.kinetic_redistribution", "Redistribution cinétique");

        // KeyBinding
        add("key.categories.vibranium", "Vibranium");
        add("key.vibranium.toggle_outline", "Basculer le contour");

        // Enchantments
        add("enchantment.vibranium.universal_breaker", "Briseur universel");
        add("enchantment.vibranium.universal_breaker.desc", "Permet de casser tous les blocs de la zone, peu importe l'outil requis.");

        // JEI
        add("jei.vibranium.heart_shaped_herb.desc",
                "L'herbe en forme de coeur se trouve dans les portails en ruine. Attention, la consommer a des effets positifs et négatifs.");
        add("jei.vibranium.vibranium_upgrade_smithing_template.desc",
                "Ce gabarit de forgeage se trouve dans une chambre d'épreuve, caché dans un tonneau près de lits.");
        add("jei.vibranium.enriched_vibranium_dirt.desc",
                "Pour obtenir de la terre enrichie en vibranium, faites un clic droit sur de la terre normale avec du vibranium brut. Vous pouvez séparer le vibranium avec une houe.");
        add("jei.vibranium.vibranium_core.desc",
                "Ce noyau mystérieux émet la même énergie que les Golems Vibra. Que se passera-t-il s'ils se rencontrent ?");
        add("jei.vibranium.enriched_vibranium_farmland.desc",
                "L'énergie mystérieuse de la terre agricole enrichie en vibranium semble accélérer la croissance des plantes. Vous pouvez récupérer le vibranium avec une houe.");

        // Trim Materials
        add("trim_material.vibranium.vibranium", "Matériau de vibranium");

        // Vibranium Mace
        add("item.vibranium_mace.tooltip", "Peut miner des blocs en 3x3");

        // Others
        add(CreativeTabInit.MAIN_TAB_ONE_TITLE, "Vibranium");

        // --- Config translations: Titles ---
        add("vibranium.config.title.oreGen", "Paramètres de génération de minerai de vibranium");
        add("vibranium.config.title.geodeGen", "Paramètres des géodes de vibranium");
        add("vibranium.config.title.mace", "Paramètres de la masse de vibranium");

        // --- Config translations: Ore Generation ---
        add("vibranium.config.veinsPerChunk", "Veines de vibranium par fragment");
        add("vibranium.config.maxHeight", "Hauteur maximale pour le minerai de vibranium");
        add("vibranium.config.minHeight", "Hauteur minimale pour le minerai de vibranium");

        // --- Config translations: Geode Generation ---
        add("vibranium.config.geodesRarity", "Rareté des géodes de vibranium");
        add("vibranium.config.geodesMaxHeight", "Hauteur maximale pour les géodes de vibranium");
        add("vibranium.config.geodesMinHeight", "Hauteur minimale pour les géodes de vibranium");

        // --- Config translations: Mace ---
        add("vibranium.config.useFastMode", "Mode rapide pour la masse");

        // --- Config translations: Outline (Client) ---
        add("vibranium.config.outlineRed", "Composant rouge du contour");
        add("vibranium.config.outlineGreen", "Composant vert du contour");
        add("vibranium.config.outlineBlue", "Composant bleu du contour");
        add("vibranium.config.outlineAlpha", "Transparence du contour");
    }
}