package de.bigbull.vibranium.data.loottable;

import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.main.ModInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLootSubProvider {

    protected ModBlockLootTables(HolderLookup.Provider p_344943_) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), p_344943_);
    }

    @Override
    protected void generate() {
        add(BlockInit.DEPPSLATE_VIBRANIUM_ORE.get(), createOreDrop(BlockInit.DEPPSLATE_VIBRANIUM_ORE.get(), ItemInit.RAW_VIBRANIUM.get()));

        dropSelf(BlockInit.BLOCK_OF_RAW_VIBRANIUM.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> Optional.of(BuiltInRegistries.BLOCK.getKey(block))
                        .filter(key -> key.getNamespace().equals(ModInfo.MODID))
                        .isPresent())
                .collect(Collectors.toSet());
    }
}