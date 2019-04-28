package me.xa5.simpletech.blocks.machines.wire;

import me.xa5.simpletech.entity.STBlockEntities;
import net.minecraft.block.entity.BlockEntity;

public class WireBlockEntity extends BlockEntity implements WireNetworkPart {
    private WireNetwork network;

    public WireBlockEntity() {
        super(STBlockEntities.WIRE);
    }

    @Override
    public WireNetwork getNetwork() {
        if (network == null) {
            System.err.println("Returning null network!");
        }
        return this.network;
    }

    @Override
    public void setNetwork(WireNetwork network) {
        this.network = network;
    }
}