package de.bigbull.vibranium.data.loot;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Vibranium.MODID);
    }

    @Override
    protected void start() {
        add("spawn_heart_shaped_herb", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(
                        Identifier.parse("chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.4f).build() },
                ItemInit.HEART_SHAPED_HERB.get()));

        add("spawn_vibranium_upgrade_smithing_template", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(
                        Identifier.parse("chests/trial_chambers/intersection_barrel")).build()},
                ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()));
    }
}
