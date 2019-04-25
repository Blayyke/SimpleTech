package me.xa5.simpletech.container.slot;

import alexiil.mc.lib.attributes.item.impl.PartialInventoryFixedWrapper;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class OutputSlot extends Slot {
    private final PlayerEntity player;
    private int amount;

    public OutputSlot(PlayerEntity player, PartialInventoryFixedWrapper inventory, int slotId, int x, int y) {
        super(inventory, slotId, x, y);
        this.player = player;
    }

    public boolean canInsert(ItemStack stack) {
        return false;
    }

    public ItemStack takeStack(int int_1) {
        if (this.hasStack()) {
            this.amount += Math.min(int_1, this.getStack().getAmount());
        }

        return super.takeStack(int_1);
    }

    public ItemStack onTakeItem(PlayerEntity playerEntity_1, ItemStack itemStack_1) {
        this.onCrafted(itemStack_1);
        super.onTakeItem(playerEntity_1, itemStack_1);
        return itemStack_1;
    }

    protected void onCrafted(ItemStack itemStack_1, int int_1) {
        this.amount += int_1;
        this.onCrafted(itemStack_1);
    }

    protected void onCrafted(ItemStack stack) {
        stack.onCrafted(this.player.world, this.player, this.amount);
        this.amount = 0;
    }
}