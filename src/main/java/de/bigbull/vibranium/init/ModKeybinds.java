package de.bigbull.vibranium.init;

import de.bigbull.vibranium.Vibranium;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static final KeyMapping.Category VIBRANIUM_CATEGORY = new KeyMapping.Category(
            ResourceLocation.fromNamespaceAndPath(Vibranium.MODID, "main")
    );

    public static final KeyMapping TOGGLE_OUTLINE = new KeyMapping(
            "key.vibranium.toggle_outline",
            GLFW.GLFW_KEY_O,
            VIBRANIUM_CATEGORY
    );
}
