package de.bigbull.vibranium.data.texture;

import de.bigbull.vibranium.init.ItemInit;
import de.bigbull.vibranium.main.Vibranium;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
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
        itemHandheld(ItemInit.VIBRANIUM_SWORD);
        itemHandheld(ItemInit.VIBRANIUM_PICKAXE);
        itemHandheld(ItemInit.VIBRANIUM_AXE);
        itemHandheld(ItemInit.VIBRANIUM_SHOVEL);
        itemHandheld(ItemInit.VIBRANIUM_HOE);
        itemHandheldWithTransform(ItemInit.VIBRANIUM_MACE);
    }

    private void itemGenerated(DeferredItem item) {
        singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/generated"),"layer0", ResourceLocation.fromNamespaceAndPath(Vibranium.MODID,"item/" + item.getId().getPath().toString().substring(Vibranium.MODID.length() - 9)));
    }

    private void itemHandheld(DeferredItem item) {
        singleTexture(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/handheld"),"layer0", ResourceLocation.fromNamespaceAndPath(Vibranium.MODID,"item/" + item.getId().getPath().toString().substring(Vibranium.MODID.length() - 9)));
    }

    private void itemHandheldWithTransform(DeferredItem item) {
        getBuilder(item.getId().getPath())
                .parent(getExistingFile(ResourceLocation.withDefaultNamespace("item/handheld")))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "item/" + item.getId().getPath().toString().substring(Vibranium.MODID.length() - 9)))
                .transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(0, -90, 55)
                .translation(0, 4.0f, 1)
                .scale(1, 1, 1)
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                .rotation(0, 90, -55)
                .translation(0, 4.0f, 1)
                .scale(1, 1, 1)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0, -90, 25)
                .translation(0, 3, 0.8f)
                .scale(0.9f, 0.9f, 0.9f)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(0, 90, -25)
                .translation(0, 3, 0.8f)
                .scale(0.9f, 0.9f, 0.9f)
                .end();
    }
}
