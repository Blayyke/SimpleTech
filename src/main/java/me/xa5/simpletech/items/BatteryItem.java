package me.xa5.simpletech.items;

import me.xa5.simpletech.energy.EnergyHolderItem;
import me.xa5.simpletech.energy.STEnergy;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public class BatteryItem extends Item implements EnergyHolderItem {
    private static final int MAX_CHARGE = 2000;

    public BatteryItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendItemsForGroup(ItemGroup group, DefaultedList<ItemStack> list) {
        if (this.isInItemGroup(group)) {
            ItemStack charged = new ItemStack(this);
            STEnergy.setEnergy(charged, 0);
            list.add(charged);

            ItemStack depleted = new ItemStack(this);
            STEnergy.setEnergy(depleted, MAX_CHARGE);
            list.add(depleted);
        }
    }

    @Override
    public void buildTooltip(ItemStack battery, World world_1, List<TextComponent> list, TooltipContext tooltipContext_1) {
        TextComponent text;
        int energy = STEnergy.getBatteryEnergy(battery);

        if (energy <= 0) {
            text = new TranslatableTextComponent("ui.simple-tech.battery.depleted");
            text.getStyle().setItalic(true).setColor(TextFormat.GRAY);
        } else {
            text = STEnergy.ENERGY_TYPE.getDisplayAmount(energy);
            text.getStyle().setItalic(true).setColor(TextFormat.GRAY);
        }

        list.add(text);
    }
}