package me.xa5.simpletech.blocks.machines.electricfurnace;

import alexiil.mc.lib.attributes.Simulation;
import io.github.prospector.silk.util.ActionType;
import me.xa5.simpletech.BasicInventoryFixedWrapper;
import me.xa5.simpletech.MachineStatus;
import me.xa5.simpletech.blocks.machines.MachineWithEnergy;
import me.xa5.simpletech.energy.STEnergy;
import me.xa5.simpletech.entity.STBlockEntities;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.cooking.SmeltingRecipe;

import java.util.Optional;

public class ElectricFurnaceBlockEntity extends MachineWithEnergy {
    public static final int OUTPUT_SLOT = 1;
    public static final int CHARGE_SLOT = 2;
    private MachineStatus status = MachineStatus.OUT_OF_POWER;
    public int progress = 0;
    public int maxProgress = 100;

    public ElectricFurnaceBlockEntity() {
        super(STBlockEntities.ELECTRIC_FURNACE);
    }

    @Override
    protected int getInventorySize() {
        return 3;
    }

    private ItemStack getResultFromRecipeStack(Inventory inv) {
        // Once this method has been called, we have verified that either a shapeless or shaped recipe is present with isValidRecipe. Ignore the warning on getShapedRecipe(inv).get().

        Optional<SmeltingRecipe> shapelessRecipe = getSmeltingRecipe(inv);
        if (shapelessRecipe.isPresent()) {
            return shapelessRecipe.get().craft(inv);
        }
        return getSmeltingRecipe(inv).orElseThrow(() -> new IllegalStateException("No smelting recipe present! This should never happen, as isValidRecipe should have been called first.")).craft(inv);
    }

    private Optional<SmeltingRecipe> getSmeltingRecipe(Inventory input) {
        return this.world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, input, this.world);
    }

    private boolean canPutStackInResultSlot(ItemStack itemStack) {
        if (getInventory().getInvStack(OUTPUT_SLOT).isEmpty()) {
            return true;
        } else if (getInventory().getInvStack(OUTPUT_SLOT).getItem() == itemStack.getItem()) {
            return (getInventory().getInvStack(OUTPUT_SLOT).getAmount() + itemStack.getAmount()) <= itemStack.getMaxAmount();
        } else {
            return false;
        }
    }

    private boolean isValidRecipe(Inventory input) {
        return getSmeltingRecipe(input).isPresent();
    }

    @Override
    protected void onTick() {
        BasicInventoryFixedWrapper inv = new BasicInventoryFixedWrapper(this, this.getInventory().getSubInv(0, 1));
        // Drain energy
        if (this.getEnergy().getCurrentEnergy() - 2 < 0) {
            status = MachineStatus.OUT_OF_POWER;
        } else {
            status = MachineStatus.PROCESSING;
        }

        if (status == MachineStatus.PROCESSING && !isValidRecipe(inv)) {
            // Recipe is invalid. Machine is idle.
            status = MachineStatus.IDLE;
        }
        if (status == MachineStatus.PROCESSING) {
            if (isValidRecipe(inv) && canPutStackInResultSlot(getResultFromRecipeStack(inv))) {
                // Subtract 2 from energy buffer.
                this.getEnergy().extractEnergy(STEnergy.ENERGY_TYPE, 2, ActionType.PERFORM);

                ItemStack output = getResultFromRecipeStack(inv);
                this.progress++;

                if (this.progress >= maxProgress) {
                    this.progress = 0;

                    craftItem(output);
                }
            }
        } else {
            this.progress = 0;
        }
    }

    private void craftItem(ItemStack output) {
        this.getInventory().getInvStack(0).subtractAmount(1);

        ItemStack outputSlotStack = getInventory().getInvStack(OUTPUT_SLOT);
        if (outputSlotStack.isEmpty()) {
            getInventory().setInvStack(OUTPUT_SLOT, output, Simulation.ACTION);
        } else {
            getInventory().getInvStack(OUTPUT_SLOT).addAmount(output.getAmount());
        }
    }

    @Override
    public int getChargeSlot() {
        return CHARGE_SLOT;
    }
}