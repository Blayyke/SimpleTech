package me.xa5.simpletech.blocks;

import me.xa5.simpletech.Constants;
import me.xa5.simpletech.SimpleTech;
import me.xa5.simpletech.blocks.machines.electricfurnace.ElectricFurnaceBlock;
import me.xa5.simpletech.items.STItems;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tag.FabricItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class STBlocks {
    public static final ElectricFurnaceBlock ELECTRIC_FURNACE = new ElectricFurnaceBlock(createDefaultMachineSettings());
    public static final Block MACHINE_BASE = new Block(createDefaultMachineSettings());

    public static final BlockItem ELECTRIC_FURNACE_ITEM = new BlockItem(ELECTRIC_FURNACE, createDefaultMachineItemSettings());
    public static final Item MACHINE_BASE_ITEM = new BlockItem(MACHINE_BASE, createDefaultMachineItemSettings());

    public static final Block STEEL_BLOCK = new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build());
    public static final Block COPPER_BLOCK = new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build());
    public static final Block ALUMINUM_BLOCK = new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build());
    public static final Block TIN_BLOCK = new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build());
    public static final Block BRONZE_BLOCK = new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build());

    private static final Item STEEL_BLOCK_ITEM = new BlockItem(STEEL_BLOCK, createDefaultItemSettings());
    private static final Item BRONZE_BLOCK_ITEM = new BlockItem(BRONZE_BLOCK, createDefaultItemSettings());
    private static final Item COPPER_BLOCK_ITEM = new BlockItem(COPPER_BLOCK, createDefaultItemSettings());
    private static final Item ALUMINUM_BLOCK_ITEM = new BlockItem(ALUMINUM_BLOCK, createDefaultItemSettings());
    private static final Item TIN_BLOCK_ITEM = new BlockItem(TIN_BLOCK, createDefaultItemSettings());

    public static void registerAll() {
        register(Constants.Blocks.ELECTRIC_FURNACE, ELECTRIC_FURNACE);
        register(Constants.Blocks.MACHINE_BASE, MACHINE_BASE);

        register(Constants.Blocks.STEEL_BLOCK, STEEL_BLOCK);
        register(Constants.Blocks.BRONZE_BLOCK, BRONZE_BLOCK);
        register(Constants.Blocks.COPPER_BLOCK, COPPER_BLOCK);
        register(Constants.Blocks.ALUMINUM_BLOCK, ALUMINUM_BLOCK);
        register(Constants.Blocks.TIN_BLOCK, TIN_BLOCK);

        STItems.register(Constants.Blocks.STEEL_BLOCK, STEEL_BLOCK_ITEM);
        STItems.register(Constants.Blocks.BRONZE_BLOCK, BRONZE_BLOCK_ITEM);
        STItems.register(Constants.Blocks.COPPER_BLOCK, COPPER_BLOCK_ITEM);
        STItems.register(Constants.Blocks.ALUMINUM_BLOCK, ALUMINUM_BLOCK_ITEM);
        STItems.register(Constants.Blocks.TIN_BLOCK, TIN_BLOCK_ITEM);

        STItems.register(Constants.Blocks.ELECTRIC_FURNACE, ELECTRIC_FURNACE_ITEM);
        STItems.register(Constants.Blocks.MACHINE_BASE, MACHINE_BASE_ITEM);
    }

    private static void register(String id, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(Constants.MOD_ID, id), block);
    }

    private static Block.Settings createDefaultMachineSettings() {
        return FabricBlockSettings.of(Material.METAL)
                .breakByTool(FabricItemTags.PICKAXES)
                .strength(1.5F, 6.0F)
                .sounds(BlockSoundGroup.METAL)
                .build();
    }

    private static Item.Settings createDefaultMachineItemSettings() {
        return new Item.Settings()
                .itemGroup(SimpleTech.MACHINES_GROUP);
    }

    private static Item.Settings createDefaultItemSettings() {
        return new Item.Settings()
                .itemGroup(SimpleTech.BLOCKS_GROUP);
    }
}