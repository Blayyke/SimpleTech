package me.xa5.simpletech.blocks.machines.crusher;

import me.xa5.simpletech.Constants;
import me.xa5.simpletech.blocks.machines.BaseMachineBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.BlockView;

public class CrusherBlock extends BaseMachineBlock {
    public CrusherBlock(Settings settings) {
        super(settings, new Identifier(Constants.MOD_ID, Constants.Blocks.CRUSHER));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new CrusherBlockEntity();
    }
}