package me.xa5.simpletech.blocks.machines.electricfurnace;

import me.xa5.simpletech.Constants;
import me.xa5.simpletech.blocks.machines.BaseMachineBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;

public class ElectricFurnaceBlock extends BaseMachineBlock {
    public ElectricFurnaceBlock(Settings settings) {
        super(settings, new Identifier(Constants.MOD_ID, Constants.Blocks.ELECTRIC_FURNACE));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new ElectricFurnaceBlockEntity();
    }
}