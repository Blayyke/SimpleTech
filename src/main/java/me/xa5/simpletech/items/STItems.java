package me.xa5.simpletech.items;

import me.xa5.simpletech.Constants;
import me.xa5.simpletech.SimpleTech;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class STItems {
    public static final Item BATTERY = new BatteryItem(createDefaultSettings().stackSize(1).durability(BatteryItem.MAX_CHARGE));

    public static final Item STEEL_INGOT = new Item(createDefaultSettings());
    public static final Item BRONZE_INGOT = new Item(createDefaultSettings());
    public static final Item COPPER_INGOT = new Item(createDefaultSettings());
    public static final Item TIN_INGOT = new Item(createDefaultSettings());
    public static final Item ALUMINUM_INGOT = new Item(createDefaultSettings());

    public static final Item STEEL_NUGGET = new Item(createDefaultSettings());
    public static final Item BRONZE_NUGGET = new Item(createDefaultSettings());
    public static final Item COPPER_NUGGET = new Item(createDefaultSettings());
    public static final Item TIN_NUGGET = new Item(createDefaultSettings());
    public static final Item ALUMINUM_NUGGET = new Item(createDefaultSettings());

    public static final Item STEEL_DUST = new Item(createDefaultSettings());
    public static final Item BRONZE_DUST = new Item(createDefaultSettings());
    public static final Item COPPER_DUST = new Item(createDefaultSettings());
    public static final Item TIN_DUST = new Item(createDefaultSettings());
    public static final Item ALUMINUM_DUST = new Item(createDefaultSettings());

    public static final Item GOLD_DUST = new Item(createDefaultSettings());
    public static final Item IRON_DUST = new Item(createDefaultSettings());

    public static void init() {
        register(Constants.Items.BATTERY, BATTERY);

        register(Constants.Items.STEEL_INGOT, STEEL_INGOT);
        register(Constants.Items.BRONZE_INGOT, BRONZE_INGOT);
        register(Constants.Items.COPPER_INGOT, COPPER_INGOT);
        register(Constants.Items.TIN_INGOT, TIN_INGOT);
        register(Constants.Items.ALUMINUM_INGOT, ALUMINUM_INGOT);

        register(Constants.Items.STEEL_NUGGET, STEEL_NUGGET);
        register(Constants.Items.BRONZE_NUGGET, BRONZE_NUGGET);
        register(Constants.Items.COPPER_NUGGET, COPPER_NUGGET);
        register(Constants.Items.TIN_NUGGET, TIN_NUGGET);
        register(Constants.Items.ALUMINUM_NUGGET, ALUMINUM_NUGGET);

        register(Constants.Items.STEEL_DUST, STEEL_DUST);
        register(Constants.Items.BRONZE_DUST, BRONZE_DUST);
        register(Constants.Items.COPPER_DUST, COPPER_DUST);
        register(Constants.Items.TIN_DUST, TIN_DUST);
        register(Constants.Items.ALUMINUM_DUST, ALUMINUM_DUST);

        register(Constants.Items.GOLD_DUST, GOLD_DUST);
        register(Constants.Items.IRON_DUST, IRON_DUST);
    }

    public static void register(String id, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Constants.MOD_ID, id), item);
    }

    private static Item.Settings createDefaultSettings() {
        return new Item.Settings().itemGroup(SimpleTech.ITEMS_GROUP);
    }
}