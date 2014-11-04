package me.zaboing.cydir.gui;

import java.io.File;

import me.zaboing.cydir.Spells;
import me.zaboing.cydir.client.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
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

	private String target;
	
	private boolean dirty = true;

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

		super.initGui();
	}

	private void generateGlyphs() {
		buttonList.clear();
		int id = 0;
		if (target == null) {
			int y = -60;
			for (String target : Spells.targetWords) {
				buttonList.add(new GuiGlyph(id, width / 2 - 107, height / 2 + y,
						target));
				++id;
				y += 25;
			}
		} else {
			int x = 5;
			int y = 5;
			for (String other : Spells.otherWords) {
				buttonList.add(new GuiGlyph(id, x, y, 78, 20, other));
				++id;
				x += 110;
				if (x > width / 2) {
					x = 5;
					y += 25;
				}
			}
		}
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
		if (target == null) {
			target = button.displayString;
		}
		dirty = true;
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		if (dirty) {
			generateGlyphs();
		}
		
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

		drawString(RuneFontRenderer.instance, spell,
				width / 2 + 15, 20, 0xffffffff);
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
