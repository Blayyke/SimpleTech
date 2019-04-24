package me.xa5.simpletech.client;

import me.xa5.simpletech.Constants;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class STKeybindings {
    private static KeyBinding TEST_BINDING;

    public static void registerAll() {
        KeyBindingRegistry.INSTANCE.addCategory(Constants.MOD_ID);

        TEST_BINDING = register("test_key", GLFW.GLFW_KEY_K);
    }

    private static KeyBinding register(String id, int keycode) {
        FabricKeyBinding binding = FabricKeyBinding.Builder.create(new Identifier(Constants.MOD_ID, id), InputUtil.Type.KEYSYM, keycode, Constants.MOD_ID).build();
        KeyBindingRegistry.INSTANCE.register(binding);
        return binding;
    }
}