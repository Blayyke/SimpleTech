package me.xa5.simpletech.mixins;

import net.minecraft.client.gui.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract CompoundTag getOrCreateTag();

    @Shadow
    public abstract boolean hasTag();

    @Inject(method = "getTooltipText", at = @At("RETURN"))
    public void getTooltipText(CallbackInfoReturnable<List<TextComponent>> info) {
        if (hasTag()) {
            if (Screen.hasAltDown() && Screen.hasShiftDown()) {
                info.getReturnValue().add(new StringTextComponent(getOrCreateTag().toString()));
            } else {
                StringTextComponent text = new StringTextComponent("Hold Shift+Alt to display item NBT.");
                text.setStyle(text.getStyle().setItalic(true));

                info.getReturnValue().add(text);
            }
        }
    }
}