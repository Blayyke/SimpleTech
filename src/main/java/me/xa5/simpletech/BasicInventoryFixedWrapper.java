package me.xa5.simpletech;

import alexiil.mc.lib.attributes.item.FixedItemInv;
import alexiil.mc.lib.attributes.item.impl.PartialInventoryFixedWrapper;
import me.xa5.simpletech.blocks.machines.Machine;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;

public class BasicInventoryFixedWrapper extends PartialInventoryFixedWrapper {
    private final Machine blockEntity;
    private Container container;

    public BasicInventoryFixedWrapper(Machine blockEntity, Container container) {
        super(blockEntity.getInventory());
        this.blockEntity = blockEntity;
        this.container = container;
    }

    public BasicInventoryFixedWrapper(Machine blockEntity, FixedItemInv inv) {
        super(inv);
        this.blockEntity = blockEntity;
    }

    @Override
    public void markDirty() {
        this.blockEntity.markDirty();
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity player) {
        // If container is null, just return true.
        return container == null || container.canUse(player);
    }
}
