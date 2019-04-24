package me.xa5.simpletech.blocks.machines.electricfurnace;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class ElectricFurnaceContainer extends Container {
    private final BlockPos blockPos;
    private final PlayerEntity player;
    private final ElectricFurnaceBlockEntity furnace;

    public ElectricFurnaceContainer(int syncId, BlockPos blockPos, PlayerEntity player) {
        super(null, syncId);
        this.blockPos = blockPos;
        this.player = player;

        BlockEntity blockEntity = player.world.getBlockEntity(blockPos);
        if (!(blockEntity instanceof ElectricFurnaceBlockEntity)) {
            // TODO: Move this logic somewhere else to just not open this at all.
            throw new IllegalStateException("Found " + blockEntity + " instead of an ElectricFurnaceBlockEntity!");
        }
        this.furnace = (ElectricFurnaceBlockEntity) blockEntity;
    }

    @Override
    public boolean canUse(PlayerEntity var1) {
        return true;
    }
}