package me.xa5.simpletech;

import me.xa5.simpletech.client.STKeybindings;
import me.xa5.simpletech.container.screen.STScreens;
import net.fabricmc.api.ClientModInitializer;

public class SimpleTechClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        STScreens.registerAll();
        STKeybindings.registerAll();
    }
}