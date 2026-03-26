package de.bigbull.vibranium.data.loottable;

import com.google.common.collect.Sets;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModLootTables extends LootTableProvider {
    public ModLootTables(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, Set.of(), List.of(new SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)), pRegistries);
    }


    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContextSource validationcontext, ProblemReporter.Collector problemreporter$collector) {
        var modLootTablesId = BuiltInLootTables.all()
                .stream()
                .filter(id -> id.registry().getNamespace().equals(Vibranium.MODID))
                .collect(Collectors.toSet());

        for (var id : Sets.difference(modLootTablesId, writableregistry.keySet())) {
            problemreporter$collector.report(new MissingTableProblem(id));
        }

        writableregistry.listElements().forEach(holder -> holder.value().validate(
                validationcontext.context(holder.value().getParamSet())
                        .enterElement(new ProblemReporter.RootElementPathElement(holder.key()), holder.key())
        ));
    }
}
