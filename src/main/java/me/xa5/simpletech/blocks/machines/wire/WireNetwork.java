package me.xa5.simpletech.blocks.machines.wire;

import net.minecraft.block.entity.BlockEntity;
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
            BlockEntity be = world.getBlockEntity(offset);

            if (be instanceof WireNetworkPart) {
                WireNetwork oldNetwork = WireNetwork.getNetwork(world, offset);

                for (BlockPos wire : oldNetwork.wires) {
                    BlockEntity wireInOldNetwork = world.getBlockEntity(wire);

                    ((WireNetworkPart) wireInOldNetwork).setNetwork(network);
                    network.wires.add(wire);
                }
                WireNetwork.NETWORKS.remove(oldNetwork);
            }
        }

        network.wires.add(pos);
        WireNetwork.NETWORKS.add(network);
        return network;
    }

    public static WireNetwork getNetwork(World world, BlockPos offset) {
        // Useless bad code.

        // Loop all WireNetworks in WireNetwork.NETWORKS; then loop all the wires in the network. Return network in loop if the pos provided in the parameter is in it's network.
//        return NETWORKS.stream().filter(network -> {
//            for (BlockPos pos : network.wires) {
//                if (pos.equals(offset)) {
//                    return true;
//                }
//            }
//            return false;
//        }).findFirst().orElse(null);

        return ((WireNetworkPart) world.getBlockEntity(offset)).getNetwork();
    }
}