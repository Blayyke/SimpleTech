package me.xa5.simpletech.entity;

import me.xa5.simpletech.Constants;
import me.xa5.simpletech.blocks.machines.crusher.CrusherBlockEntity;
import me.xa5.simpletech.blocks.machines.electricfurnace.ElectricFurnaceBlockEntity;
import me.xa5.simpletech.blocks.machines.wire.WireBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class STBlockEntities {
    public static final BlockEntityType<ElectricFurnaceBlockEntity> ELECTRIC_FURNACE = BlockEntityType.Builder.create(ElectricFurnaceBlockEntity::new).build(null);
    public static final BlockEntityType<CrusherBlockEntity> CRUSHER = BlockEntityType.Builder.create(CrusherBlockEntity::new).build(null);
    public static final BlockEntityType<WireBlockEntity> WIRE = BlockEntityType.Builder.create(WireBlockEntity::new).build(null);

    public static void init() {
        register(Constants.Blocks.ELECTRIC_FURNACE, ELECTRIC_FURNACE);
        register(Constants.Blocks.CRUSHER, CRUSHER);
        register(Constants.Blocks.WIRE, WIRE);
    }

    private static void register(String id, BlockEntityType<? extends BlockEntity> type) {
        Registry.register(Registry.BLOCK_ENTITY, new Identifier(Constants.MOD_ID, id), type);
    }
}