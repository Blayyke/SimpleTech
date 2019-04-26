package me.xa5.simpletech.energy;

import io.github.cottonmc.energy.CottonEnergy;
import me.xa5.simpletech.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class STEnergy {
    public static final STEnergyType ENERGY_TYPE = new STEnergyType();

    public static void init() {
        Registry.register(CottonEnergy.ENERGY_REGISTRY, new Identifier(Constants.MOD_ID, Constants.Energy.ENERGY), ENERGY_TYPE);
    }

    public static boolean isEnergyItem(ItemStack itemStack) {
        return itemStack.getItem() instanceof EnergyHolderItem;
    }

    public static int getBatteryEnergy(ItemStack battery) {
        if (!isEnergyItem(battery)) {
            throw new IllegalArgumentException("Provided argument is not an energy item!");
        }

        return battery.hasTag() && battery.getTag().containsKey("Energy") ? battery.getTag().getInt("Energy") : Integer.MAX_VALUE;
    }

    public static int getMaxBatteryEnergy(ItemStack battery) {
        if (!isEnergyItem(battery)) {
            throw new IllegalArgumentException("Provided argument is not an energy item!");
        }

        return battery.getTag().getInt("MaxEnergy");
    }

    public static void incrementEnergy(ItemStack stack, int energyToAdd) {
        int newEnergy = getBatteryEnergy(stack);
        newEnergy = Math.min(newEnergy + energyToAdd, getMaxBatteryEnergy(stack));

        setEnergy(stack, newEnergy);
    }

    public static void decrementEnergy(ItemStack stack, int energyToRemove) {
        int newEnergy = getBatteryEnergy(stack);
        newEnergy = Math.max(newEnergy - energyToRemove, 0);

        setEnergy(stack, newEnergy);
    }

    public static void setEnergy(ItemStack stack, int newEnergy) {
        if (!isEnergyItem(stack)) {
            throw new IllegalArgumentException("Provided argument is not an energy item!");
        }

        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("Energy", newEnergy);
        stack.setTag(tag);
        stack.setDamage(stack.getDurability() - newEnergy);
    }
}