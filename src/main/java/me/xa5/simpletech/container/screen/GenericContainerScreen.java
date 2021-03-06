package me.xa5.simpletech.container.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import me.xa5.simpletech.Constants;
import net.minecraft.client.gui.ContainerScreen;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.TextComponent;
import net.minecraft.util.Identifier;

public class GenericContainerScreen<T extends Container> extends ContainerScreen<T> {
    private final Identifier backgroundTexture;

    public GenericContainerScreen(T container, PlayerInventory playerInv, TextComponent name, String bgLocation) {
        super(container, playerInv, name);
        backgroundTexture = new Identifier(Constants.MOD_ID, "textures/screen/" + bgLocation + ".png");
    }

    @Override
    protected final void drawBackground(float var1, int var2, int var3) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.renderBackground();
        this.minecraft.getTextureManager().bindTexture(backgroundTexture);

        this.blit(this.left, this.top, 0, 0, this.containerWidth, this.containerHeight);

        drawOverlay(var1, var2, var3);
    }

    @Override
    public void render(int mouseX, int mouseY, float v) {
        super.render(mouseX, mouseY, v);
//        DrawableUtils.drawCenteredString(this.minecraft.textRenderer, new TranslatableTextComponent("block.simple-tech.circuit_fabricator").getText(), (this.width / 2), this.top + 5, TextFormat.DARK_GRAY.getColor());
        this.drawMouseoverTooltip(mouseX, mouseY);
    }

    protected void drawOverlay(float var1, int var2, int var3) {
    }

    protected boolean isMouseWithinBounds(int mouseX, int mouseY, int x1, int y1, int x2, int y2) {
        return isCoordinateBetween(mouseX, x1, x2) && isCoordinateBetween(mouseY, y1, y2);
    }

    private boolean isCoordinateBetween(int coordinate, int min, int max) {
        int newMin = Math.min(min, max);
        int newMax = Math.max(min, max);
        return coordinate >= newMin && coordinate <= newMax;
    }
}