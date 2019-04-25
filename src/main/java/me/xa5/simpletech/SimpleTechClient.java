package me.xa5.simpletech;

import me.xa5.simpletech.client.STKeybindings;
import me.xa5.simpletech.container.screen.STScreens;

public class SimpleTechClient {
    public void init() {
        STScreens.registerAll();
        STKeybindings.registerAll();
    }
}