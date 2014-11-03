package me.zaboing.cydir.gui;

import me.zaboing.cydir.client.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

public class GuiSpell extends GuiScreen {
	public static final int GUI_ID = 34;

	public static final Cursor EMPTY_CURSOR;

	public static boolean enableGlyphs;
	
	public String spell = new String();
	
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void keyTyped(char keyChar, int keyCode) {
		if (keyCode == ClientProxy.startSpell.getKeyCode()) {
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		super.keyTyped(keyChar, keyCode);
	}

	private void setCursor(Cursor cursor) {
		try {
			Mouse.setNativeCursor(cursor);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initGui() {
		Minecraft.getMinecraft().mouseHelper.grabMouseCursor();
		setCursor(EMPTY_CURSOR);
		
		buttonList.add(new GuiGlyph(0, width / 2 - 107, height / 2 - 60,
				"Ek"));
		buttonList.add(new GuiGlyph(0, width / 2 - 107, height / 2 - 35,
				"Þú"));
		buttonList.add(new GuiGlyph(0, width / 2 - 107, height / 2 - 10,
				"Þik"));
		buttonList.add(new GuiGlyph(0, width / 2 - 107, height / 2 + 15,
				"Þeír"));
		buttonList.add(new GuiGlyph(0, width / 2 - 107, height / 2 + 40,
				"Oss"));
		
		super.initGui();
	}

	@Override
	public void onGuiClosed() {
		setCursor(null);
		super.onGuiClosed();
	}

	@Override
	protected void mouseClicked(int x, int y, int button) {
		setCursor(null);
		Minecraft.getMinecraft().mouseHelper.ungrabMouseCursor();
		super.mouseClicked(x, y, button);
	}

	@Override
	protected void mouseMovedOrUp(int x, int y, int button) {
		enableGlyphs = true;
		super.mouseClicked(x, y, button);
		super.mouseMovedOrUp(x, y, button);
		enableGlyphs = false;
		setCursor(EMPTY_CURSOR);
		Minecraft.getMinecraft().mouseHelper.grabMouseCursor();
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		spell += button.displayString + " ";
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
		
		drawString(Minecraft.getMinecraft().fontRenderer, spell, width / 2 + 130, 20, 0xffffffff);
	}
	
	static {
		Cursor emptyCursor = null;
		try {
			emptyCursor = new Cursor(1, 1, 0, 0, 1,
					BufferUtils.createIntBuffer(1), null);
		} catch (LWJGLException e) {
			e.printStackTrace();
		} finally {
			EMPTY_CURSOR = emptyCursor;
		}
	}
}
