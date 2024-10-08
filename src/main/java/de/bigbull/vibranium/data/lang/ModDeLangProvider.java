package de.bigbull.vibranium.data.lang;

import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.CreativeTabInit;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModDeLangProvider extends LanguageProvider {
    public ModDeLangProvider(PackOutput output) {super(output, Vibranium.MODID, "de_de");
    }

    @Override
    protected void addTranslations() {
        //Items
        addItem(ItemInit.RAW_VIBRANIUM, "Rohes Vibranium");
        addItem(ItemInit.VIBRANIUM_INGOT, "Vibranium Barren");
        addItem(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, "Schmiedevorlage");
        addItem(ItemInit.VIBRA_GOLEM_SPAWN_EGG, "Vibra Golem Spawnei");
        addItem(ItemInit.VIBRANIUM_CORE, "Vibranium Kern");
        addItem(ItemInit.HEART_SHAPED_HERB, "Herzformiges Kraut");

        //Blocks
        addBlock(BlockInit.BLOCK_OF_RAW_VIBRANIUM, "Block aus rohem Vibranium");
        addBlock(BlockInit.DEPPSLATE_VIBRANIUM_ORE, "Tiefenschiefer Vibraniumerz");
        addBlock(BlockInit.Vibranium_Block, "Vibranium Block");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_DIRT, "Angereicherte Vibranium Erde");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_FARMLAND, "Angereichertes Vibranium Ackerland");

        //Tools
        addItem(ItemInit.VIBRANIUM_SWORD, "Vibranium Schwert");
        addItem(ItemInit.VIBRANIUM_PICKAXE, "Vibranium Spitzhacke");
        addItem(ItemInit.VIBRANIUM_AXE, "Vibranium Axt");
        addItem(ItemInit.VIBRANIUM_SHOVEL, "Vibranium Schaufel");
        addItem(ItemInit.VIBRANIUM_HOE, "Vibranium Hacke");
        addItem(ItemInit.VIBRANIUM_MACE, "Vibranium Mace");
        addItem(ItemInit.VIBRANIUM_SHIELD, "Vibranium Schild");

        //Armors
        addItem(ItemInit.VIBRANIUM_BOOTS, "Vibranium Stiefel");
        addItem(ItemInit.VIBRANIUM_LEGGINGS, "Vibranium Beinschützer");
        addItem(ItemInit.VIBRANIUM_CHESTPLATE, "Vibranium Brustplatte");
        addItem(ItemInit.VIBRANIUM_HELMET, "Vibranium Helm");
        addItem(ItemInit.VIBRANIUM_WOLF_ARMOR, "Vibranium Wolfsrüstung");
        addItem(ItemInit.VIBRANIUM_HORSE_ARMOR, "Vibranium Pferderüstung");

        //SmifhingTemplate
        add(ItemInit.VIBRANIUM_UPGRADE_APPLIES_TO.getString(), "Diamantausrüstung");
        add(ItemInit.VIBRANIUM_UPGRADE_INGREDIENTS.getString(), "Vibranium Barren");
        add(ItemInit.VIBRANIUM_UPGRADE.getString(), "Vibranium Aufrüstung");
        add(ItemInit.VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Diamantrüstung, -waffe oder -werkzeug hinzufügen");
        add(ItemInit.VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Vibranium Barren hinzufügen");

        //Potion
        addItem(ItemInit.VIBRANIUM_ENRICHED_HERB_ELIXIR, "Vibranium Angereichertes Kraut Elixier");
        addItem(ItemInit.VIBRANIUM_ENRICHED_HERB_ELIXIR_EXTENDED, "Vibranium Angereichertes Kraut Elixier");
        addItem(ItemInit.VIBRANIUM_ENRICHED_HERB_ELIXIR_ENHANCED, "Vibranium Angereichertes Kraut Elixier");
        add("effect.vibranium.kinetic_redistribution", "Kinetische Umverteilung");

        //KeyBinding
        add("key.categories.vibranium", "Vibranium");
        add("key.vibranium.toggle_outline", "Umrahmung umschalten");

        //Enchantments
        add("enchantment.vibranium.universal_breaker", "Universeller Brecher");
        add("enchantment.vibranium.universal_breaker.desc", "Ermöglicht das Abbauen aller Blöcke im Bereich, unabhängig von den Werkzeuganforderungen.");

        //JEI
        add("jei.vibranium.heart_shaped_herb.desc",
                "Das Herzförmige Kraut kann bei zerstörten Portalen gefunden werden. Sei vorsichtig der Verzehr bringt sowohl positive als auch negative Effekte mit sich.");
        add("jei.vibranium.vibranium_upgrade_smithing_template.desc",
                "Diese Schmiedevorlage kann in einer Prüfkammer gefunden werden, versteckt in einem Fass in der Nähe von Betten.");
        add("jei.vibranium.enriched_vibranium_dirt.desc",
                "Um Angereicherten Vibranium-Erdboden zu erhalten, klicke mit Rohvibranium auf normalen Erdboden. Du kannst das Rohvibranium mit einer Hacke vom Boden trennen.");
        add("jei.vibranium.vibranium_core.desc",
                "Dieser geheimnisvolle Kern strahlt dieselbe Energie aus wie die Vibra-Golems. Was könnte geschehen, wenn sie aufeinandertreffen?");
        add("jei.vibranium.enriched_vibranium_farmland.desc",
                "Die mysteriöse Energie im Angereicherten Vibranium-Ackerland scheint das Pflanzenwachstum zu beschleunigen. Du kannst das Rohvibranium mit einer Hacke aus dem Ackerland zurückgewinnen.");

        //Vibranium Mace
        add("item.vibranium_mace.tooltip", "Kann 3x3 Blöcke abbauen");

        //others
        add(CreativeTabInit.MAIN_TAB_ONE_TITLE, "Vibranium");
    }
}
