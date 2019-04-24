package me.xa5.simpletech.energy;

import net.minecraft.item.ItemStack;

public interface EnergyHolderItem {
    /**
     * If this is overridden to return true, energy will always be extracted and inserting of energy will be denied.
     *
     * @return If the battery is infinite.
     */
    default boolean isInfinite() {
        return false;
    }

    /**
     * Attempt to extract energy from this battery.
     *
     * @param battery        The battery that energy should be removed from.
     * @param energyToRemove The amount of energy to remove from the battery.
     * @return The amount of energy that could not be removed from this battery
     */
    default int extract(ItemStack battery, int energyToRemove) {
        if (isInfinite()) {
            // Allow extracting any amount.
            return 0;
        }

        int batteryEnergy = STEnergy.getBatteryEnergy(battery);
        if (energyToRemove > batteryEnergy) {
            STEnergy.decrementEnergy(battery, batteryEnergy);
            return energyToRemove - batteryEnergy;
        }

        STEnergy.decrementEnergy(battery, energyToRemove);
        return 0;
    }

    /**
     * Attempt to insert energy into this battery.
     *
     * @param battery     The battery that energy should be added to.
     * @param energyToAdd The amount of energy to add to the battery.
     * @return The amount of energy that could not be added from this battery
     */
    default int insert(ItemStack battery, int energyToAdd) {
        if (isInfinite()) {
            // Don't insert any energy.
            return energyToAdd;
        }

        int batteryEnergy = STEnergy.getBatteryEnergy(battery);
        int maxEnergy = STEnergy.getMaxBatteryEnergy(battery);

        if (batteryEnergy + energyToAdd > maxEnergy) {
            // Could not add all the energy to the battery. Add what we can and return the leftover.

            STEnergy.setEnergy(battery, maxEnergy);
            return (batteryEnergy + energyToAdd) - maxEnergy;
        }

        STEnergy.incrementEnergy(battery, energyToAdd);
        return 0;
    }
}