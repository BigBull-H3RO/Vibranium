package de.bigbull.vibranium.data.loot;

import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
        add("spawn_vibranium_upgrade_smithing_template", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(
                        ResourceLocation.parse("chests/trial_chambers/reward_common")).build(), LootItemRandomChanceCondition.randomChance(0.4f).build() },
                ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get()));

        add("heart_shaped_herb", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(
                        ResourceLocation.parse("chests/ruined_portal")).build(), LootItemRandomChanceCondition.randomChance(0.4f).build() },
                ItemInit.HEART_SHAPED_HERB.get()));
    }
}
