package me.xa5.simpletech.container;

import alexiil.mc.lib.attributes.item.impl.PartialInventoryFixedWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public abstract class GenericContainer<BE extends BlockEntity> extends Container {
    protected final BE blockEntity;
    protected PartialInventoryFixedWrapper inventory;
    private BlockContext context;
    private final BlockPos blockPos;
    private final PlayerEntity player;
    private Block block;

    protected GenericContainer(int syncId, BlockPos blockPos, PlayerEntity player, Block block, BlockContext context) {
        super(null, syncId);
        this.context = context;
        this.blockPos = blockPos;
        this.player = player;
        this.block = block;

        BlockEntity blockEntity = player.world.getBlockEntity(blockPos);
        this.blockEntity = (BE) blockEntity;

        addPlayerInvSlots();
    }

    @Override
    public boolean canUse(PlayerEntity var1) {
        return canUse(this.context, this.player, this.block);
    }

    private void addPlayerInvSlots() {
        int yOffset = getPlayerInvYOffset();

        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 9; ++y) {
                this.addSlot(new Slot(player.inventory, y + x * 9 + 9, 8 + y * 18, yOffset + x * 18));
            }
        }

        // Draw hotbar
        for (int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(player.inventory, x, 8 + x * 18, yOffset + 58));
        }
    }

    protected abstract int getPlayerInvYOffset();
}