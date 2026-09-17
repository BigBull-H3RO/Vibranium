package de.bigbull.vibranium.data.loottable;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLootSubProvider {

    protected ModBlockLootTables(LootTableSubProvider.Context context) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), context);
    }

    @Override
    protected void generate() {
        this.dropSelf(BlockInit.BLOCK_OF_RAW_VIBRANIUM.get());
        this.dropSelf(BlockInit.VIBRANIUM_BLOCK.get());
        this.dropSelf(BlockInit.SOULWOOD_LOG.get());
        this.dropSelf(BlockInit.SOULWOOD_SAPLING.get());
        this.dropSelf(BlockInit.SOULWOOD_WOOD.get());
        this.dropSelf(BlockInit.STRIPPED_SOULWOOD_LOG.get());
        this.dropSelf(BlockInit.STRIPPED_SOULWOOD_WOOD.get());
        this.dropSelf(BlockInit.SOULWOOD_PLANKS.get());
        this.dropSelf(BlockInit.SOULWOOD_STAIRS.get());
        this.dropSelf(BlockInit.SOULWOOD_FENCE.get());
        this.dropSelf(BlockInit.SOULWOOD_FENCE_GATE.get());
        this.dropSelf(BlockInit.SOULWOOD_TRAPDOOR.get());
        this.dropSelf(BlockInit.SOULWOOD_PRESSURE_PLATE.get());
        this.dropSelf(BlockInit.SOULWOOD_BUTTON.get());
        this.dropSelf(BlockInit.SOULWOOD_SIGN.get());
        this.dropSelf(BlockInit.SOULWOOD_HANGING_SIGN.get());
        this.dropSelf(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get());

        this.add(BlockInit.BUDDING_VIBRANIUM_CRYSTAL.get(), noDrop());
        this.dropWhenSilkTouch(BlockInit.SMALL_VIBRANIUM_BUD.get());
        this.dropWhenSilkTouch(BlockInit.MEDIUM_VIBRANIUM_BUD.get());
        this.dropWhenSilkTouch(BlockInit.LARGE_VIBRANIUM_BUD.get());

        this.add(BlockInit.SOULWOOD_SLAB.get(), block -> createSlabItemTable(
                BlockInit.SOULWOOD_SLAB.get()));
        this.add(BlockInit.SOULWOOD_DOOR.get(), block -> createDoorTable(
                BlockInit.SOULWOOD_DOOR.get()));
        this.add(BlockInit.SOULWOOD_LEAVES.get(), block -> createLeavesDrops(
                block, BlockInit.SOULWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(BlockInit.DEEPSLATE_VIBRANIUM_ORE.get(), block -> createSingleItemTableWithSilkTouch(
                block, ItemInit.RAW_VIBRANIUM.get()));
        this.add(BlockInit.SOULWOOD_SIGN.get(), createSingleItemTable(BlockInit.SOULWOOD_SIGN.get().asItem()));
        this.add(BlockInit.SOULWOOD_WALL_SIGN.get(), createSingleItemTable(BlockInit.SOULWOOD_SIGN.get().asItem()));
        this.add(BlockInit.SOULWOOD_HANGING_SIGN.get(), createSingleItemTable(BlockInit.SOULWOOD_HANGING_SIGN.get().asItem()));
        this.add(BlockInit.SOULWOOD_WALL_HANGING_SIGN.get(), createSingleItemTable(BlockInit.SOULWOOD_HANGING_SIGN.get().asItem()));
        this.dropPottedContents(BlockInit.POTTED_SOULWOOD_SAPLING.get());

        this.add(BlockInit.ENRICHED_VIBRANIUM_DIRT.get(), block -> LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(
                                LootItem.lootTableItem(ItemInit.RAW_VIBRANIUM.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.15f))
                                        .otherwise(
                                                LootItem.lootTableItem(ItemInit.VIBRANIUM_NUGGET.get())
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                        )
                        )
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.items, ItemTags.HOES)))
                )
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Blocks.DIRT))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.items, ItemTags.HOES)))
                )
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Blocks.DIRT))
                        .when(InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.items, ItemTags.HOES))))
                        .when(this.doesNotHaveSilkTouch())
                )
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(BlockInit.ENRICHED_VIBRANIUM_DIRT.get()))
                        .when(this.hasSilkTouch())
                )
        );

        this.add(BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get(), block -> LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(
                                LootItem.lootTableItem(ItemInit.RAW_VIBRANIUM.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.15f))
                                        .otherwise(
                                                LootItem.lootTableItem(ItemInit.VIBRANIUM_NUGGET.get())
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                        )
                        )
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.items, ItemTags.HOES)))
                )
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Blocks.DIRT))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.items, ItemTags.HOES)))
                )
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Blocks.DIRT))
                        .when(InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.items, ItemTags.HOES))))
                        .when(this.doesNotHaveSilkTouch())
                )
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(BlockInit.ENRICHED_VIBRANIUM_DIRT.get()))
                        .when(this.hasSilkTouch())
                )
        );

        this.add(
                BlockInit.HEART_SHAPED_HERB_BUSH.get(),
                p_249159_ -> this.applyExplosionDecay(
                        p_249159_,
                        LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .when(
                                                        MatchBlock.blockMatches(
                                                                this.blocks, BlockInit.HEART_SHAPED_HERB_BUSH.get(), StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3)
                                                        )
                                                )
                                                .add(LootItem.lootTableItem(ItemInit.HEART_SHAPED_HERB.get()))
                                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 3)))
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .when(
                                                        MatchBlock.blockMatches(
                                                                this.blocks, BlockInit.HEART_SHAPED_HERB_BUSH.get(), StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2)
                                                        )
                                                )
                                                .add(LootItem.lootTableItem(ItemInit.HEART_SHAPED_HERB.get()))
                                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                )
                )
        );

        this.add(
                BlockInit.VIBRANIUM_CLUSTER.get(),
                block -> this.createSilkTouchDispatchTable(
                        block,
                        LootItem.lootTableItem(ItemInit.VIBRANIUM_CRYSTAL_SHARD.get())
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(4)))
                                .apply(ApplyBonusCount.addOreBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.items, ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .otherwise(
                                        this.applyExplosionDecay(
                                                block, LootItem.lootTableItem(ItemInit.VIBRANIUM_CRYSTAL_SHARD.get())
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(2)))
                                        )
                                )
                )
        );
        
        this.dropWhenSilkTouch(BlockInit.SMALL_VIBRANIUM_BUD.get());
        this.dropWhenSilkTouch(BlockInit.MEDIUM_VIBRANIUM_BUD.get());
        this.dropWhenSilkTouch(BlockInit.LARGE_VIBRANIUM_BUD.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> Optional.of(BuiltInRegistries.BLOCK.getKey(block))
                        .filter(key -> key.getNamespace().equals(Vibranium.MODID))
                        .isPresent())
                .collect(Collectors.toSet());
    }
}
