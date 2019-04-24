package me.xa5.simpletech.container;

import me.xa5.simpletech.Constants;
import me.xa5.simpletech.blocks.machines.electricfurnace.ElectricFurnaceContainer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.util.Identifier;

public class STContainers {
    public static final Identifier ELECTRIC_FURNACE = new Identifier(Constants.MOD_ID, Constants.Blocks.ELECTRIC_FURNACE);

    public static void registerAll(){
        ContainerProviderRegistry.INSTANCE.registerFactory(ELECTRIC_FURNACE, ((syncId, identifier, player, buf) -> new ElectricFurnaceContainer(syncId, buf.readBlockPos(), player)));
    }
}