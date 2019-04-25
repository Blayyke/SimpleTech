package me.xa5.simpletech.container.slot;

import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhitelistSlot extends Slot {
    private List<Item> whitelist = new ArrayList<>();

    public WhitelistSlot(Inventory inv, int slotId, int x, int y, Item... whitelist) {
        super(inv, slotId, x, y);
        this.whitelist.addAll(Arrays.asList(whitelist));
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return whitelist.contains(stack.getItem());
    }
}