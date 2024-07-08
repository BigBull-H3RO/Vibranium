package de.bigbull.moregems.data.texture;

import de.bigbull.moregems.item.ModItems;
import de.bigbull.moregems.main;
import de.bigbull.moregems.util.NameUtility;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemStateProvider extends ItemModelProvider {
    public ModItemStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, main.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        item(ModItems.VIBRANIUM.get());
    }

    private void item(Item item) {
        String name = NameUtility.getItemName(item);
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "item/" + name);
    }
}
