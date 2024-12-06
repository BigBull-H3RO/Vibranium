package de.bigbull.vibranium.data.texture;

import de.bigbull.vibranium.Vibranium;
import de.bigbull.vibranium.init.BlockInit;
import de.bigbull.vibranium.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemStateProvider extends ItemModelProvider {
    public ModItemStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Vibranium.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Items
        itemGenerated(ItemInit.RAW_VIBRANIUM);
        itemGenerated(ItemInit.VIBRANIUM_INGOT);
        itemGenerated(ItemInit.VIBRANIUM_NUGGET);
        itemGenerated(ItemInit.VIBRANIUM_PLATE);
        itemGenerated(ItemInit.VIBRANIUM_CORE);
        itemGenerated(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE);
        itemGenerated(ItemInit.VIBRANIUM_CRYSTAL_SHARD);
        itemGenerated(ItemInit.SOUL_HERB_MIXTURE);

        //Armors
        itemGenerated(ItemInit.VIBRANIUM_BOOTS);
        itemGenerated(ItemInit.VIBRANIUM_LEGGINGS);
        itemGenerated(ItemInit.VIBRANIUM_CHESTPLATE);
        itemGenerated(ItemInit.VIBRANIUM_HELMET);

        //Animal Armors
        itemGenerated(ItemInit.VIBRANIUM_HORSE_ARMOR);
        itemGenerated(ItemInit.VIBRANIUM_WOLF_ARMOR);

        //Tools
        itemHandheld(ItemInit.VIBRANIUM_SWORD);
        itemHandheld(ItemInit.VIBRANIUM_PICKAXE);
        itemHandheld(ItemInit.VIBRANIUM_AXE);
        itemHandheld(ItemInit.VIBRANIUM_SHOVEL);
        itemHandheld(ItemInit.VIBRANIUM_HOE);

        //Addvance Items
        itemGenerated(ItemInit.HEART_SHAPED_HERB);
        itemMutliGenerated(ItemInit.SOUL_HERB_ELIXIR, ItemInit.SOUL_HERB_ELIXIR);
        itemMutliGenerated(ItemInit.SOUL_HERB_ELIXIR_EXTENDED, ItemInit.SOUL_HERB_ELIXIR);
        itemMutliGenerated(ItemInit.SOUL_HERB_ELIXIR_ENHANCED, ItemInit.SOUL_HERB_ELIXIR);

        //Tree
        saplingItem(BlockInit.SOULWOOD_SAPLING);

        //Blocks
        basicItem(BlockInit.SOULWOOD_DOOR.asItem());
    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "block/" + item.getId().getPath()));
    }

    private void itemGenerated(DeferredItem item) {
        singleTexture(item.getId().getPath(), mcLoc("item/generated"),"layer0",
                ResourceLocation.fromNamespaceAndPath(Vibranium.MODID,"item/" + item.getId().getPath()));
    }

    private void itemMutliGenerated(DeferredItem item, DeferredItem baseItem) {
        withExistingParent(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "item/" + baseItem.getId().getPath()));
    }

    private void itemHandheld(DeferredItem item) {
        singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/handheld"),"layer0", ResourceLocation.fromNamespaceAndPath(Vibranium.MODID,"item/" + item.getId().getPath()));
    }
}
