package me.xa5.simpletech.blocks.machines.electricfurnace;

import me.xa5.simpletech.BasicInventoryFixedWrapper;
import me.xa5.simpletech.blocks.STBlocks;
import me.xa5.simpletech.container.GenericContainer;
import me.xa5.simpletech.container.slot.BatterySlot;
import me.xa5.simpletech.container.slot.OutputSlot;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class ElectricFurnaceContainer extends GenericContainer<ElectricFurnaceBlockEntity> {
    public ElectricFurnaceContainer(int syncId, BlockPos blockPos, PlayerEntity player) {
        super(syncId, blockPos, player, STBlocks.ELECTRIC_FURNACE, BlockContext.create(player.world, blockPos));

        this.inventory = new BasicInventoryFixedWrapper(blockEntity, this);
        addSlot(new Slot(this.inventory, 0, 56, 17));
        addSlot(new OutputSlot(player, this.inventory, 1, 56, 17));
        addSlot(new BatterySlot(this.inventory, 2, 56, 17));
    }

    @Override
    protected int getPlayerInvYOffset() {
        return 84;
    }
}