package de.bigbull.vibranium.data.lang;

import de.bigbull.vibranium.event.ModEvents;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.init.CreativeTabInit;
import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.custom.item.VibraniumMaceItem;
import de.bigbull.vibranium.init.custom.item.VibraniumUpgradeTemplate;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModDeLangProvider extends LanguageProvider {
    public ModDeLangProvider(PackOutput output) {super(output, Vibranium.MODID, "de_de");
    }

    @Override
    protected void addTranslations() {
        //Items
        addItem(ItemInit.RAW_VIBRANIUM, "Rohvibranium");
        addItem(ItemInit.VIBRANIUM_INGOT, "Vibraniumbarren");
        addItem(ItemInit.VIBRANIUM_NUGGET, "Vibraniumklumpen");
        addItem(ItemInit.VIBRANIUM_PLATE, "Vibraniumplatte");
        addItem(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE, "Vibraniumaufwertung");
        addItem(ItemInit.VIBRA_GOLEM_SPAWN_EGG, "Vibra Golem Spawnei");
        addItem(ItemInit.VIBRANIUM_CORE, "Vibraniumkern");
        addItem(ItemInit.HEART_SHAPED_HERB, "Herzformiges Kraut");
        addItem(ItemInit.SOUL_HERB_MIXTURE, "Seelenkraut Mischung");
        addItem(ItemInit.VIBRANIUM_CRYSTAL_SHARD, "Vibranium Kristall Splitter");
        addItem(ItemInit.SOULWOOD_SIGN, "Seelenholz Schild");
        addItem(ItemInit.SOULWOOD_HANGING_SIGN, "Seelenholz Hängeschild");
        addItem(ItemInit.SOULWOOD_BOAT, "Seelenholz Boot");
        addItem(ItemInit.SOULWOOD_CHEST_BOAT, "Seelenholz Boot mit Truhe");
        add("entity.vibranium.soulwood_chest_boat", "Seelenholz Boot mit Truhe");

        //Blocks
        addBlock(BlockInit.BLOCK_OF_RAW_VIBRANIUM, "Block aus rohem Vibranium");
        addBlock(BlockInit.DEPPSLATE_VIBRANIUM_ORE, "Tiefenschiefer Vibraniumerz");
        addBlock(BlockInit.VIBRANIUM_BLOCK, "Vibranium Block");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_DIRT, "Angereicherte Vibranium Erde");
        addBlock(BlockInit.ENRICHED_VIBRANIUM_FARMLAND, "Angereichertes Vibranium Ackerland");
        addBlock(BlockInit.SOULWOOD_LOG, "Seelenholz Stamm");
        addBlock(BlockInit.SOULWOOD_SAPLING, "Seelenholz Setzling");
        addBlock(BlockInit.SOULWOOD_WOOD, "Seelenholz");
        addBlock(BlockInit.STRIPPED_SOULWOOD_LOG, "Abgezogener Seelenholz Stamm");
        addBlock(BlockInit.STRIPPED_SOULWOOD_WOOD, "Abgezogenes Seelenholz");
        addBlock(BlockInit.SOULWOOD_PLANKS, "Seelenholz Planken");
        addBlock(BlockInit.SOULWOOD_LEAVES, "Seelenholz Blätter");
        addBlock(BlockInit.SOULWOOD_SLAB, "Seelenholz Platte");
        addBlock(BlockInit.SOULWOOD_STAIRS, "Seelenholz Treppe");
        addBlock(BlockInit.SOULWOOD_FENCE, "Seelenholz Zaun");
        addBlock(BlockInit.SOULWOOD_FENCE_GATE, "Seelenholz Zauntor");
        addBlock(BlockInit.SOULWOOD_DOOR, "Seelenholz Tür");
        addBlock(BlockInit.SOULWOOD_TRAPDOOR, "Seelenholz Falltür");
        addBlock(BlockInit.SOULWOOD_PRESSURE_PLATE, "Seelenholz Druckplatte");
        addBlock(BlockInit.SOULWOOD_BUTTON, "Seelenholz Knopf");
        addBlock(BlockInit.VIBRANIUM_CRYSTAL_BLOCK, "Kristallblock aus Vibranium");
        addBlock(BlockInit.BUDDING_VIBRANIUM_CRYSTAL, "Vibranium Knospenblock");
        addBlock(BlockInit.VIBRANIUM_CLUSTER, "Vibraniumhaufen");
        addBlock(BlockInit.SMALL_VIBRANIUM_BUD, "Kleine Kristall-Vibraniumknospe");
        addBlock(BlockInit.MEDIUM_VIBRANIUM_BUD, "Mittlere Kristall-Vibraniumknospe");
        addBlock(BlockInit.LARGE_VIBRANIUM_BUD, "Große Kristall-Vibraniumknospe");

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
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_APPLIES_TO.getString(), "Netheriteausrüstung");
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_INGREDIENTS.getString(), "Vibraniumbarren");
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_BASE_SLOT_DESCRIPTION.getString(), "Rüstungsteil, Werkzeuge oder Schwert aus Netherit hinzufügen");
        add(VibraniumUpgradeTemplate.VIBRANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION.getString(), "Vibraniumbarren hinzufügen");

        //Potion
        addItem(ItemInit.SOUL_HERB_ELIXIR, "Vibranium Angereichertes Kraut Elixier");
        addItem(ItemInit.SOUL_HERB_ELIXIR_EXTENDED, "Vibranium Angereichertes Kraut Elixier");
        addItem(ItemInit.SOUL_HERB_ELIXIR_ENHANCED, "Vibranium Angereichertes Kraut Elixier");
        add("effect.vibranium.kinetic_redistribution", "Kinetische Umverteilung");

        //KeyBinding
        add(ModEvents.KEY_CATEGORIES.getString(), "Vibranium");
        add(ModEvents.TOGGLE_OUTLINE.getString(), "Umrahmung umschalten");

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

        //Trim Materials
        add("trim_material.vibranium.vibranium", "Vibranium Material");

        //Vibranium Mace
        add(VibraniumMaceItem.TOOLTIP.getString(), "Kann 3x3 Blöcke abbauen");

        //others
        add(CreativeTabInit.MAIN_TAB_ONE_TITLE, "Vibranium");
    }
}
