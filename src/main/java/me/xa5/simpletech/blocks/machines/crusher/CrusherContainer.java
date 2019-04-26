package me.xa5.simpletech.blocks.machines.crusher;

import me.xa5.simpletech.BasicInventoryFixedWrapper;
import me.xa5.simpletech.blocks.STBlocks;
import me.xa5.simpletech.container.GenericContainer;
import me.xa5.simpletech.container.slot.BatterySlot;
import me.xa5.simpletech.container.slot.OutputSlot;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class CrusherContainer extends GenericContainer<CrusherBlockEntity> {
    public CrusherContainer(int syncId, BlockPos blockPos, PlayerEntity player) {
        super(syncId, blockPos, player, STBlocks.ELECTRIC_FURNACE, BlockContext.create(player.world, blockPos));

        this.inventory = new BasicInventoryFixedWrapper(blockEntity, this);
        int slotX = 26;

        addSlot(new Slot(this.inventory, 0, slotX+(18*2), 26));
        addSlot(new OutputSlot(player, this.inventory, CrusherBlockEntity.OUTPUT_SLOT, slotX + (18 * 6), 26));
        addSlot(new BatterySlot(this.inventory, CrusherBlockEntity.CHARGE_SLOT, slotX +(18*0), 26));
    }

    @Override
    protected int getPlayerInvYOffset() {
        return 59;
    }
}