package de.bigbull.moregems.data.texture;

import de.bigbull.moregems.init.ItemInit;
import de.bigbull.moregems.main.ModInfo;
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

        //Blocks

//        item(ItemInit.RAW_VIBRANIUM.get());
//        item(ItemInit.VIBRANIUM_INGOT.get());
//        item(ItemInit.VIBRANIUM_BOOTS.get());
//        item(ItemInit.VIBRANIUM_LEGGINGS.get());
//        item(ItemInit.VIBRANIUM_CHESTPLATE.get());
//        item(ItemInit.VIBRANIUM_HELMET.get());
//        item(ItemInit.VIBRANIUM_UPGRADE_SMITHING_TEMPLATE.get());
    }

    private void itemGenerated(DeferredItem item) {
        //String name = getItemName(item);
        //getBuilder(name).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/" + name);
        singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/generated"),"layer0", ResourceLocation.fromNamespaceAndPath(ModInfo.MODID,"item/" + item.getId().getPath().toString().substring(ModInfo.MODID.length() - 8)));
    }
    private void itemHandheld(DeferredItem item) {
        singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/handheld"),"layer0", ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "item/" + item.getId().getPath().toString().substring(ModInfo.MODID.length() - 8)));
    }

    private void itemBlock(DeferredItem item) {
        withExistingParent(item.getId().getPath(), ResourceLocation.fromNamespaceAndPath(ModInfo.MODID, "block/" + item.getId().getPath().toString().substring(ModInfo.MODID.length() - 8)));
    }



//    private void item(Item item) {
//        String name = getItemName(item);
//        getBuilder(name)
//                .parent(getExistingFile(mcLoc("item/generated")))
//                .texture("layer0", "item/" + name);
//    }
//
//    private @NotNull String getItemName(Item item) {
//        return BuiltInRegistries.ITEM.getKey(item).toString().replace(ModInfo.MODID + ":", "");
//    }

}
