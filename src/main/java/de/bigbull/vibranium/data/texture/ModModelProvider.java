package de.bigbull.vibranium.data.texture;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;

import java.util.Set;
import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, Vibranium.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(BlockInit.BLOCK_OF_RAW_VIBRANIUM.get());
        blockModels.createTrivialCube(BlockInit.DEPPSLATE_VIBRANIUM_ORE.get());
        blockModels.createTrivialCube(BlockInit.VIBRANIUM_BLOCK.get());
        blockModels.createTrivialCube(BlockInit.SOULWOOD_PLANKS.get());
        blockModels.createTrivialCube(BlockInit.VIBRANIUM_CRYSTAL_BLOCK.get());
        blockModels.createTrivialCube(BlockInit.BUDDING_VIBRANIUM_CRYSTAL.get());
        blockModels.woodProvider(BlockInit.STRIPPED_SOULWOOD_LOG.get()).logWithHorizontal(BlockInit.STRIPPED_SOULWOOD_LOG.get()).wood(BlockInit.STRIPPED_SOULWOOD_WOOD.get());
        blockModels.createHangingSign(BlockInit.STRIPPED_SOULWOOD_LOG.get(), BlockInit.SOULWOOD_HANGING_SIGN.get(), BlockInit.SOULWOOD_WALL_HANGING_SIGN.get());
        blockModels.createPlantWithDefaultItem(BlockInit.SOULWOOD_SAPLING.get(), BlockInit.POTTED_SOULWOOD_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createAmethystCluster(BlockInit.VIBRANIUM_CLUSTER.get());
        blockModels.createAmethystCluster(BlockInit.SMALL_VIBRANIUM_BUD.get());
        blockModels.createAmethystCluster(BlockInit.MEDIUM_VIBRANIUM_BUD.get());
        blockModels.createAmethystCluster(BlockInit.LARGE_VIBRANIUM_BUD.get());

        BlockFamily SOULWOOD_FAMILY = new BlockFamily.Builder(BlockInit.SOULWOOD_PLANKS.get())
                .button(BlockInit.SOULWOOD_BUTTON.get())
                .fence(BlockInit.SOULWOOD_FENCE.get())
                .fenceGate(BlockInit.SOULWOOD_FENCE_GATE.get())
                .pressurePlate(BlockInit.SOULWOOD_PRESSURE_PLATE.get())
                .sign(BlockInit.SOULWOOD_SIGN.get(), BlockInit.SOULWOOD_WALL_SIGN.get())
                .slab(BlockInit.SOULWOOD_SLAB.get())
                .stairs(BlockInit.SOULWOOD_STAIRS.get())
                .door(BlockInit.SOULWOOD_DOOR.get())
                .trapdoor(BlockInit.SOULWOOD_TRAPDOOR.get())
                .getFamily();

        blockModels.familyWithExistingFullBlock(SOULWOOD_FAMILY.getBaseBlock()).generateFor(SOULWOOD_FAMILY);

        itemModels.generateFlatItem(ItemInit.RAW_VIBRANIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_CORE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_CRYSTAL_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.SOUL_HERB_MIXTURE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateTrimmableItem(ItemInit.VIBRANIUM_BOOTS.get(), ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "vibranium")), "boots", false);
        itemModels.generateTrimmableItem(ItemInit.VIBRANIUM_LEGGINGS.get(), ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "vibranium")), "leggings", false);
        itemModels.generateTrimmableItem(ItemInit.VIBRANIUM_CHESTPLATE.get(), ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "vibranium")), "chestplate", false);
        itemModels.generateTrimmableItem(ItemInit.VIBRANIUM_HELMET.get(), ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "vibranium")), "helmet", false);
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateWolfArmor(ItemInit.VIBRANIUM_WOLF_ARMOR.get());
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateSpawnEgg(ItemInit.VIBRA_GOLEM_SPAWN_EGG.get(), 2631467, 131664);
        itemModels.generateFlatItem(ItemInit.HEART_SHAPED_HERB.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.SOUL_HERB_ELIXIR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.SOUL_HERB_ELIXIR_EXTENDED.get(), ItemInit.SOUL_HERB_ELIXIR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.SOUL_HERB_ELIXIR_ENHANCED.get(), ItemInit.SOUL_HERB_ELIXIR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.SOULWOOD_BOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ItemInit.SOULWOOD_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.declareCustomModelItem(BlockInit.VIBRANIUM_CLUSTER.get().asItem());
        itemModels.declareCustomModelItem(BlockInit.SMALL_VIBRANIUM_BUD.get().asItem());
        itemModels.declareCustomModelItem(BlockInit.MEDIUM_VIBRANIUM_BUD.get().asItem());
        itemModels.declareCustomModelItem(BlockInit.LARGE_VIBRANIUM_BUD.get().asItem());
        itemModels.generateFlatItem(ItemInit.VIBRANIUM_MACE.get(), ModelTemplates.FLAT_HANDHELD_MACE_ITEM);
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        Set<Block> excludedBlocks = Set.of(
                BlockInit.HEART_SHAPED_HERB_BUSH.get(),
                BlockInit.ENRICHED_VIBRANIUM_DIRT.get(),
                BlockInit.ENRICHED_VIBRANIUM_FARMLAND.get(),
                BlockInit.SOULWOOD_LOG.get(),
                BlockInit.SOULWOOD_WOOD.get(),
                BlockInit.SOULWOOD_LEAVES.get()
        );

        return BuiltInRegistries.BLOCK.listElements()
                .filter(holder -> holder.getKey().location().getNamespace().equals(modId))
                .filter(holder -> !excludedBlocks.contains(holder.value()));
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        Set<Item> excludedItems = Set.of(
                ItemInit.VIBRANIUM_SHIELD.get()
        );

        return BuiltInRegistries.ITEM.listElements()
                .filter(holder -> holder.getKey().location().getNamespace().equals(modId))
                .filter(holder -> !excludedItems.contains(holder.value()));
    }
}
