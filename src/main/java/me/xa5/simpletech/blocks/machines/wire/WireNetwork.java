package me.xa5.simpletech.blocks.machines.wire;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class WireNetwork {
    public final static List<WireNetwork> NETWORKS = new ArrayList<>();

    public List<BlockPos> wires = new ArrayList<>();

    public static WireNetwork findOrCreateNetwork(World world, BlockPos pos) {
        WireNetwork network = new WireNetwork();
        for (Direction dir : Direction.values()) {
            BlockPos offset = pos.offset(dir);
            BlockState blockState = world.getBlockState(offset);
            if (blockState.getBlock() instanceof WireConnectable) {
                network.mergeWith(((WireConnectable) blockState.getBlock()).getNetwork());
                WireNetwork.NETWORKS.remove(((WireConnectable) blockState.getBlock()).getNetwork());
            }
        }

        network.wires.add(pos);
        WireNetwork.NETWORKS.add(network);
        return network;
    }

    /**
     * Merge the parameter networks cables into this network.
     *
     * @param oldNetwork The old network that should be merged into this new one.
     */
    public void mergeWith(WireNetwork oldNetwork) {
        this.wires.addAll(oldNetwork.wires);
        System.out.println("Merged networks!");
    }
}