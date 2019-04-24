package me.xa5.simpletech.blocks.machines;

import io.github.cottonmc.energy.impl.SimpleEnergyAttribute;
import me.xa5.simpletech.energy.STEnergy;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;

public abstract class MachineWithEnergy extends Machine {
    public static final int DEFAULT_MAX_ENERGY = 1000;
    private SimpleEnergyAttribute energy = new SimpleEnergyAttribute(getMaxEnergy(), STEnergy.ENERGY_TYPE);

    public MachineWithEnergy(BlockEntityType<?> type) {
        super(type);
        energy.listen(this::markDirty);
    }

    protected int getMaxEnergy() {
        return DEFAULT_MAX_ENERGY;
    }

    @Override
    public final void tick() {
        this.chargeFromBattery();
        this.onTick();
    }

    private void chargeFromBattery() {
        ItemStack battery = getInventory().getInvStack(getChargeSlot());
        if (battery.isEmpty()) {
            return;
        }


    }

    /**
     * Get the ID of the inventory slot that the battery item is in. Return -
     *
     * @return The slot ID that should be used when getting the battery item from the inventory.
     */
    public int getChargeSlot() {
        return 0;
    }

    protected abstract void onTick();

    public SimpleEnergyAttribute getEnergy() {
        return energy;
    }
}