package me.xa5.simpletech.blocks.machines;

import io.github.cottonmc.energy.impl.SimpleEnergyAttribute;
import me.xa5.simpletech.energy.STEnergy;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

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
        int amountToExtract = 5;

        ItemStack battery = getInventory().getInvStack(getChargeSlot());
        if (STEnergy.isEnergyItem(battery)) {
            int machineEnergy = this.energy.getCurrentEnergy();
            if (machineEnergy >= this.energy.getMaxEnergy()) {
                // Cannot accept more energy; already full.
                return;
            }

            int batteryEnergy = STEnergy.getBatteryEnergy(battery);
            // Don't extract more energy than what is stored in the battery.
            amountToExtract = Math.min(amountToExtract, batteryEnergy);

            // 15000 - 14950 = 50 room to charge
            int machineChargeRoom = energy.getMaxEnergy() - machineEnergy;
            amountToExtract = Math.min(amountToExtract, machineChargeRoom);

            STEnergy.decrementEnergy(battery, amountToExtract);
            this.energy.setCurrentEnergy(machineEnergy + amountToExtract);
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

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);

        energy.fromTag(tag.getCompound("Energy"));
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);

        tag.put("Energy", energy.toTag());
        return tag;
    }

    protected abstract void onTick();

    public SimpleEnergyAttribute getEnergy() {
        return energy;
    }
}