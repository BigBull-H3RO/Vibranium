package de.bigbull.vibranium.event.client;

import com.mojang.blaze3d.platform.InputConstants;
import de.bigbull.vibranium.Vibranium;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Vibranium.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientKeyBindings {
    public static KeyMapping toggleOutlineKey;

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        toggleOutlineKey = new KeyMapping(
                "key.vibranium.toggle_outline",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                "key.categories.vibranium"
        );

        event.register(toggleOutlineKey);
    }

    public static boolean isToggleOutlinePressed() {
        return toggleOutlineKey != null && toggleOutlineKey.isDown();
    }
}