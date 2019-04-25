package me.xa5.simpletech;

import alexiil.mc.lib.attributes.item.impl.PartialInventoryFixedWrapper;
import me.xa5.simpletech.blocks.machines.electricfurnace.ElectricFurnaceBlockEntity;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;

public class BasicInventoryFixedWrapper extends PartialInventoryFixedWrapper {
    private final ElectricFurnaceBlockEntity blockEntity;
    private Container container;

    public BasicInventoryFixedWrapper(ElectricFurnaceBlockEntity blockEntity, Container container) {
        super(blockEntity.getInventory());
        this.blockEntity = blockEntity;
        this.container = container;
    }

    @Override
    public void markDirty() {
        this.blockEntity.markDirty();
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity player) {
        return container.canUse(player);
    }
}
