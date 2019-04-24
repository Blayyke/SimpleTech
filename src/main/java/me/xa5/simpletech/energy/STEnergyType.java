package me.xa5.simpletech.energy;

import io.github.cottonmc.energy.api.EnergyType;
import me.xa5.simpletech.Constants;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;

public class STEnergyType implements EnergyType {
    @Override
    public int getMaximumTransferSize() {
        return Integer.MAX_VALUE;
    }

    @Override
    public TextComponent getDisplayAmount(int amount) {
        float fAmount = (float) amount;

        if (fAmount < 1000) { // x < 1K
            return new TranslatableTextComponent("tooltip." + Constants.MOD_ID + ".energy", fAmount);
        } else if (fAmount < 1_000_000) { // 1K < x < 1M
            float tAmount = fAmount / 1000;
            return new TranslatableTextComponent("tooltip." + Constants.MOD_ID + ".energy.k", tAmount);
        } else if (fAmount < 1_000_000_000) { // 1M < x < 1G
            float tAmount = fAmount / 1_000_1000;
            return new TranslatableTextComponent("tooltip." + Constants.MOD_ID + ".energy.m", tAmount);
        } else { // 1B < x
            float tAmount = fAmount / 1_000_000_000;
            return new TranslatableTextComponent("tooltip." + Constants.MOD_ID + ".energy.b", tAmount);
        }
    }
}