package de.bigbull.gemsofpower.data.texture;

import de.bigbull.gemsofpower.init.ItemInit;
import de.bigbull.gemsofpower.main.ModInfo;
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
        itemGenerated(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE);

        //Armors
        itemHandheld(ItemInit.VIBRANIUM_BOOTS);
        itemHandheld(ItemInit.VIBRANIUM_LEGGINGS);
        itemHandheld(ItemInit.VIBRANIUM_CHESTPLATE);
        itemHandheld(ItemInit.VIBRANIUM_HELMET);

        //Tools
        itemHandheld(ItemInit.VIBRANIUM_SWORD);
        itemHandheld(ItemInit.VIBRANIUM_PICKAXE);
        itemHandheld(ItemInit.VIBRANIUM_AXE);
        itemHandheld(ItemInit.VIBRANIUM_SHOVEL);
        itemHandheld(ItemInit.VIBRANIUM_HOE);

        //Blocks
    }

    private void itemGenerated(DeferredItem item) {
        singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/generated"),"layer0", ResourceLocation.fromNamespaceAndPath(ModInfo.MODID,"item/" + item.getId().getPath().toString().substring(ModInfo.MODID.length() - 11)));
    }
    private void itemHandheld(DeferredItem item) {
        singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/handheld"),"layer0", ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "item/" + item.getId().getPath().toString().substring(ModInfo.MODID.length() - 11)));
    }

    private void itemBlock(DeferredItem item) {
        withExistingParent(item.getId().getPath(), ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "block/" + item.getId().getPath().toString().substring(ModInfo.MODID.length() - 11)));
    }
}
