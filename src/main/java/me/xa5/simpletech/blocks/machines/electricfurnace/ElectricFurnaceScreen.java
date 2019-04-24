package me.xa5.simpletech.blocks.machines.electricfurnace;

import net.minecraft.client.gui.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.math.BlockPos;

public class ElectricFurnaceScreen extends ContainerScreen {
    public ElectricFurnaceScreen(int syncId, BlockPos pos, PlayerEntity player) {
        super(new ElectricFurnaceContainer(syncId, pos,player), player.inventory, new TranslatableTextComponent(""));
    }

    @Override
    protected void drawBackground(float var1, int var2, int var3) {
    }
}