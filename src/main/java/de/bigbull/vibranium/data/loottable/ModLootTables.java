package de.bigbull.vibranium.data.loottable;

import java.util.List;
import java.util.Set;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class ModLootTables extends LootTableProvider {
    public ModLootTables() {
        super(Set.of(), List.of(new SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)));
    }
}
