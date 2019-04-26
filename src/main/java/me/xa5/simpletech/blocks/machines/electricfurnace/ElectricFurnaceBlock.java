package me.xa5.simpletech.blocks.machines.electricfurnace;

import me.xa5.simpletech.blocks.machines.BaseMachineBlock;
import me.xa5.simpletech.container.STContainers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class ElectricFurnaceBlock extends BaseMachineBlock {
    public ElectricFurnaceBlock(Settings settings) {
        super(settings, STContainers.ELECTRIC_FURNACE);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new ElectricFurnaceBlockEntity();
    }
}