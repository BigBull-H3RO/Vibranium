package de.bigbull.vibranium.data.texture;

import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.main.ModInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemStateProvider extends ItemModelProvider {
    public ModItemStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ModInfo.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Items
        itemGenerated(ItemInit.RAW_VIBRANIUM);
        itemGenerated(ItemInit.VIBRANIUM_INGOT);
        itemGenerated(ItemInit.VIBRANIUM_CORE);
        itemGenerated(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE);

        //Armors
        itemGenerated(ItemInit.VIBRANIUM_BOOTS);
        itemGenerated(ItemInit.VIBRANIUM_LEGGINGS);
        itemGenerated(ItemInit.VIBRANIUM_CHESTPLATE);
        itemGenerated(ItemInit.VIBRANIUM_HELMET);
        itemGenerated(ItemInit.VIBRANIUM_WOLF_ARMOR);
        itemGenerated(ItemInit.VIBRANIUM_HORSE_ARMOR);

        //Tools
        itemGenerated(ItemInit.VIBRANIUM_SWORD);
        itemGenerated(ItemInit.VIBRANIUM_PICKAXE);
        itemGenerated(ItemInit.VIBRANIUM_AXE);
        itemGenerated(ItemInit.VIBRANIUM_SHOVEL);
        itemGenerated(ItemInit.VIBRANIUM_HOE);

        //Blocks

    }

    private void itemGenerated(DeferredItem item) {
        singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/generated"),"layer0", ResourceLocation.fromNamespaceAndPath(ModInfo.MODID,"item/" + item.getId().getPath().toString().substring(ModInfo.MODID.length() - 9)));
    }

    private void itemBlock(DeferredItem item) {
        withExistingParent(item.getId().getPath(), ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "block/" + item.getId().getPath().toString().substring(ModInfo.MODID.length() - 9)));
    }
}
