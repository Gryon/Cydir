package me.zaboing.cydir;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = CydirMod.MOD_ID, version = CydirMod.MOD_VERSION)
public class CydirMod {
	public static final String MOD_ID = "Cydir";
	public static final String MOD_VERSION = "0.0.1";

	@SidedProxy(clientSide = "me.zaboing.cydir.client.ClientProxy", serverSide = "me.zaboing.cydir.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerKeybindings();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
//		try {
//			Field f = FontRenderer.class.getDeclaredField("glyphWidth");
//			f.setAccessible(true);
//			byte[] glyphWidth = (byte[])f.get(Minecraft.getMinecraft().fontRenderer);
//			byte sample = glyphWidth['Þ'];
//			System.out.println("Glyph width: " + sample);
//			System.out.println("Shifted: " + (sample >>> 4));
//			System.out.println("Anded: " + (sample & 0xf));
//			System.out.println("Result: " + ((sample & 0xf) - (sample >>> 4)));
//			glyphWidth['Þ'] = (byte) 31;
//			f.set(Minecraft.getMinecraft().fontRenderer, glyphWidth);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
