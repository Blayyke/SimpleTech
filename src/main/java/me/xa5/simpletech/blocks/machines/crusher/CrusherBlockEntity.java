package me.xa5.simpletech.blocks.machines.crusher;

import alexiil.mc.lib.attributes.Simulation;
import io.github.prospector.silk.util.ActionType;
import me.xa5.simpletech.BasicInventoryFixedWrapper;
import me.xa5.simpletech.MachineStatus;
import me.xa5.simpletech.blocks.machines.MachineWithEnergy;
import me.xa5.simpletech.energy.STEnergy;
import me.xa5.simpletech.entity.STBlockEntities;
import me.xa5.simpletech.recipe.CrushingRecipe;
import me.xa5.simpletech.recipe.STRecipes;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import java.util.Optional;

public class CrusherBlockEntity extends MachineWithEnergy {
    public static final int OUTPUT_SLOT = 1;
    public static final int CHARGE_SLOT = 2;
    private MachineStatus status = MachineStatus.OUT_OF_POWER;
    public int progress = 0;
    public int maxProgress;

    public CrusherBlockEntity() {
        super(STBlockEntities.CRUSHER);
    }

    @Override
    protected int getInventorySize() {
        return 3;
    }

    private ItemStack getResultFromRecipeStack(Inventory inv) {
        Optional<CrushingRecipe> crushingRecipe = getCrushingRecipe(inv);
        if (!crushingRecipe.isPresent()) {
            throw new IllegalStateException("Crushing recipe was not found!");
        }

        return crushingRecipe.get().craft(inv);
    }

    private Optional<CrushingRecipe> getCrushingRecipe(Inventory input) {
        return this.world.getRecipeManager().getFirstMatch(STRecipes.CRUSHING_TYPE, input, this.world);
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

    private boolean isValidRecipe(Optional<CrushingRecipe> recipe) {
        return recipe.isPresent();
    }

    @Override
    protected void onTick() {
        BasicInventoryFixedWrapper inv = new BasicInventoryFixedWrapper(this, this.getInventory().getSubInv(0, 1));
        Optional<CrushingRecipe> recipe = getCrushingRecipe(inv);
        recipe.ifPresent(crushingRecipe -> this.maxProgress = crushingRecipe.getCookTime());

        // Drain energy
        if (this.getEnergy().getCurrentEnergy() - 2 < 0) {
            status = MachineStatus.OUT_OF_POWER;
        } else {
            status = MachineStatus.PROCESSING;
        }

        if (status == MachineStatus.PROCESSING && !isValidRecipe(recipe)) {
            // Recipe is invalid. Machine is idle.
            status = MachineStatus.IDLE;
        }
        if (status == MachineStatus.PROCESSING) {
            if (isValidRecipe(recipe) && canPutStackInResultSlot(getResultFromRecipeStack(inv))) {
                // Subtract 2 from energy buffer.
                this.getEnergy().extractEnergy(STEnergy.ENERGY_TYPE, 2, ActionType.PERFORM);

                ItemStack output = getResultFromRecipeStack(inv);
                this.progress++;

                if (this.progress >= maxProgress) {
                    this.progress = 0;
                    this.maxProgress = 0;

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