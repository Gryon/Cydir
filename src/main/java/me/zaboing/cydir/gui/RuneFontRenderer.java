package me.zaboing.cydir.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;

public class RuneFontRenderer extends FontRenderer {

	
	public RuneFontRenderer(GameSettings p_i1035_1_,
			ResourceLocation p_i1035_2_, TextureManager p_i1035_3_,
			boolean p_i1035_4_) {
		super(p_i1035_1_, p_i1035_2_, p_i1035_3_, p_i1035_4_);
	}

	public static final RuneFontRenderer instance;

	public static void init() {
	}
	
	static {
		
		Minecraft mc = Minecraft.getMinecraft();
		instance = new RuneFontRenderer(mc.gameSettings, new ResourceLocation(
				"cydir", "ascii_fixed.png"), mc.renderEngine, true);
		((IReloadableResourceManager) mc.getResourceManager())
				.registerReloadListener(instance);
	}
}
