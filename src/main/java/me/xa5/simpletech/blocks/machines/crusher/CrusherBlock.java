package me.xa5.simpletech.blocks.machines.crusher;

import me.xa5.simpletech.blocks.machines.BaseMachineBlock;
import me.xa5.simpletech.container.STContainers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class CrusherBlock extends BaseMachineBlock {
    public CrusherBlock(Settings settings) {
        super(settings, STContainers.CRUSHER);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new CrusherBlockEntity();
    }
}