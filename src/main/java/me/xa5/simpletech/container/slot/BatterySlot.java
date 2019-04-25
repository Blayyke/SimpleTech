package me.xa5.simpletech.container.slot;

import alexiil.mc.lib.attributes.item.impl.PartialInventoryFixedWrapper;
import me.xa5.simpletech.energy.STEnergy;
import net.minecraft.container.Slot;
import net.minecraft.item.ItemStack;

public class BatterySlot extends Slot {
    public BatterySlot(PartialInventoryFixedWrapper inv, int slotId, int x, int y) {
        super(inv, slotId, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return STEnergy.isEnergyItem(stack);
    }
}