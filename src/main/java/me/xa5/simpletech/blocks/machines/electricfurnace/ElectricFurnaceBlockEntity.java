package me.xa5.simpletech.blocks.machines.electricfurnace;

import me.xa5.simpletech.blocks.machines.MachineWithEnergy;
import me.xa5.simpletech.entity.STBlockEntities;

public class ElectricFurnaceBlockEntity extends MachineWithEnergy {
    public ElectricFurnaceBlockEntity() {
        super(STBlockEntities.ELECTRIC_FURNACE);
    }

    @Override
    protected int getInventorySize() {
        return 3;
    }

    @Override
    protected void onTick() {
    }
}