package me.xa5.simpletech;

import me.xa5.simpletech.blocks.STBlocks;
import me.xa5.simpletech.container.STContainers;
import me.xa5.simpletech.energy.STEnergy;
import me.xa5.simpletech.entity.STBlockEntities;
import me.xa5.simpletech.items.STItems;
import me.xa5.simpletech.recipe.STRecipes;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class SimpleTech {
    public static final ItemGroup MACHINES_GROUP = FabricItemGroupBuilder.create(new Identifier(Constants.MOD_ID, Constants.ItemGroup.MACHINES_GROUP))
            .icon(() -> new ItemStack(STBlocks.ELECTRIC_FURNACE_ITEM)).build();

    public static final ItemGroup ITEMS_GROUP = FabricItemGroupBuilder.create(new Identifier(Constants.MOD_ID, Constants.ItemGroup.ITEMS_GROUP))
            .icon(() -> new ItemStack(STItems.COPPER_INGOT)).build();

    public static final ItemGroup BLOCKS_GROUP = FabricItemGroupBuilder.create(new Identifier(Constants.MOD_ID, Constants.ItemGroup.BLOCKS_GROUP))
            .icon(() -> new ItemStack(STBlocks.COPPER_BLOCK)).build();

    @SuppressWarnings("unused")
    public static void init() {
        STEnergy.init();
        STBlocks.init();
        STBlockEntities.init();
        STItems.init();
        STContainers.init();
        STRecipes.init();
    }
}