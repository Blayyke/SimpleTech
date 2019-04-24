package me.xa5.simpletech.blocks.machines.electricfurnace;

import me.xa5.simpletech.Constants;
import me.xa5.simpletech.container.screen.GenericContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.math.BlockPos;

public class ElectricFurnaceScreen extends GenericContainerScreen {
    public ElectricFurnaceScreen(int syncId, BlockPos pos, PlayerEntity player) {
        super(new ElectricFurnaceContainer(syncId, pos, player), player.inventory, new TranslatableTextComponent(""), Constants.Blocks.ELECTRIC_FURNACE);
    }
}