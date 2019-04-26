package me.xa5.simpletech.blocks.machines;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;

public interface WireConnectable {
    boolean canWireConnect(IWorld world, Direction opposite, BlockPos thisWire, BlockPos otherConnectable) ;
}