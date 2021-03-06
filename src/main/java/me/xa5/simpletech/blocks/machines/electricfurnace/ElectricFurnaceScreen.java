package me.xa5.simpletech.blocks.machines.electricfurnace;

import me.xa5.simpletech.Constants;
import me.xa5.simpletech.DrawUtils;
import me.xa5.simpletech.container.screen.GenericContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.math.BlockPos;

public class ElectricFurnaceScreen extends GenericContainerScreen {
    public ElectricFurnaceScreen(int syncId, BlockPos pos, PlayerEntity player) {
        super(new ElectricFurnaceContainer(syncId, pos, player), player.inventory, new TranslatableTextComponent(""), Constants.Blocks.ELECTRIC_FURNACE);
        this.height = 140;
    }

    @Override
    protected void drawOverlay(float var1, int var2, int var3) {
        drawEnergyBar();
        drawCraftProgress();
    }

    private void drawCraftProgress() {
        ElectricFurnaceBlockEntity blockEntity = ((ElectricFurnaceContainer) container).blockEntity;
        float progressScale = ((float) blockEntity.progress / (float) blockEntity.maxProgress) * 30;

        DrawUtils.blit(left + 89, top + 32, 176, 0, (int) Math.ceil(progressScale), 4);
    }

    private void drawEnergyBar() {
        ElectricFurnaceBlockEntity blockEntity = ((ElectricFurnaceContainer) container).blockEntity;
        float progressScale = ((float) blockEntity.getEnergy().getCurrentEnergy() / (float) blockEntity.getEnergy().getMaxEnergy()) * 36;

        DrawUtils.blit(left + 12, top + 16, 184, 4, 8, 36);
        DrawUtils.blit(left + 12, top + 16 + 36 - (int) Math.ceil(progressScale), 176, 4, 8, (int) Math.ceil(progressScale));
    }

    @Override
    protected void drawMouseoverTooltip(int mouseX, int mouseY) {
        ElectricFurnaceBlockEntity blockEntity = ((ElectricFurnaceContainer) container).blockEntity;

        if (isMouseWithinBounds(mouseX, mouseY, left + 11, top + 15, left + 21, top + 52)) {
            renderTooltip("Stored energy : " + blockEntity.getEnergy().getCurrentEnergy(), mouseX, mouseY);
        }

        super.drawMouseoverTooltip(mouseX, mouseY);
    }
}