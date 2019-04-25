package me.xa5.simpletech.blocks.machines;

import alexiil.mc.lib.attributes.item.FixedItemInvView;
import alexiil.mc.lib.attributes.item.impl.SimpleFixedItemInv;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

public abstract class Machine extends BlockEntity implements Tickable, BlockEntityClientSerializable {
    private SimpleFixedItemInv inventory = new SimpleFixedItemInv(getInventorySize());

    public Machine(BlockEntityType<?> type) {
        super(type);
        inventory.addListener(this::onInventoryChanged, () -> {
            // Do nothing when listener is invalidated i guess?
        });
    }

    protected abstract int getInventorySize();

    protected void onInventoryChanged(FixedItemInvView inv, int slot, ItemStack previous, ItemStack current) {
        this.markDirty();
    }

    @Override
    public final CompoundTag toClientTag(CompoundTag tag) {
        return this.toTag(tag);
    }

    public SimpleFixedItemInv getInventory() {
        return inventory;
    }

    @Override
    public final void fromClientTag(CompoundTag tag) {
        this.fromTag(tag);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.put("Inventory", this.inventory.toTag());

        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        this.inventory.fromTag(tag.getCompound("Inventory"));
    }
}