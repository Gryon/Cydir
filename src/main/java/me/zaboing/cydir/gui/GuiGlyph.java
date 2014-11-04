package me.zaboing.cydir.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLCommonHandler;

public class GuiGlyph extends GuiButton {

	public GuiGlyph(int id, int x, int y, String text) {
		this(id, x, y, 100, 20, text);
	}

	public GuiGlyph(int id, int x, int y, int width, int height, String text) {
		super(id, x, y, width, height, text);
	}

	@Override
	public void drawButton(Minecraft minecraft, int mouseX, int mouseY) {
		if (Mouse.isGrabbed()) {
			drawGlyph(minecraft, -1, -1);
		} else {
			drawGlyph(minecraft, mouseX, mouseY);
		}
	}

	public void drawGlyph(Minecraft minecraft, int mouseX, int mouseY) {
		if (this.visible) {
			FontRenderer fontRenderer = RuneFontRenderer.instance;

			minecraft.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = ((mouseX >= this.xPosition)
					&& (mouseY >= this.yPosition)
					&& (mouseX < this.xPosition + this.width) && (mouseY < this.yPosition
					+ this.height));
			int k = getHoverState(this.field_146123_n);
			GL11.glEnable(3042);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(770, 771);
			drawTexturedModalRect(this.xPosition, this.yPosition, 0,
					46 + k * 20, this.width / 2, this.height);
			drawTexturedModalRect(this.xPosition + this.width / 2,
					this.yPosition, 200 - this.width / 2, 46 + k * 20,
					this.width / 2, this.height);
			mouseDragged(minecraft, mouseX, mouseY);
			int l = 0xfff06c00;

			if (this.packedFGColour != 0) {
				l = this.packedFGColour;
			} else if (!this.enabled) {
				l = 10526880;
			} else if (this.field_146123_n) {
				l = 0xffffe6d1;
			}

			int x = this.xPosition + this.width / 2
					- fontRenderer.getStringWidth(displayString) / 2;
			int y = this.yPosition + (this.height - 8) / 2;
			drawString(fontRenderer, displayString, x, y, l);
		}
	}

	@Override
	public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
		return GuiSpell.enableGlyphs
				&& super.mousePressed(minecraft, mouseX, mouseY);
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		if (super.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY)) {

		}
	}
}
