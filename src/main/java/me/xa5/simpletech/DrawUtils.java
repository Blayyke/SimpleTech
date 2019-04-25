package me.xa5.simpletech;

import net.minecraft.client.gui.DrawableHelper;

public class DrawUtils {
    public static void blit(int x, int y, int u, int v, int width, int height) {
        DrawableHelper.blit(x, y, 1, (float) u, (float) v, width, height, 256, 256);
    }
}