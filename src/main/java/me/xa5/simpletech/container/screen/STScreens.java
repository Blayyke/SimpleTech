package me.xa5.simpletech.container.screen;

import me.xa5.simpletech.blocks.machines.crusher.CrusherScreen;
import me.xa5.simpletech.blocks.machines.electricfurnace.ElectricFurnaceScreen;
import me.xa5.simpletech.container.STContainers;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;

public class STScreens {
    public static void registerAll() {
        ScreenProviderRegistry.INSTANCE.registerFactory(STContainers.ELECTRIC_FURNACE, (syncId, identifier, playerEntity, packetByteBuf) -> new ElectricFurnaceScreen(syncId, packetByteBuf.readBlockPos(), playerEntity));
        ScreenProviderRegistry.INSTANCE.registerFactory(STContainers.CRUSHER, (syncId, identifier, playerEntity, packetByteBuf) -> new CrusherScreen(syncId, packetByteBuf.readBlockPos(), playerEntity));
    }
}