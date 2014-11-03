package me.zaboing.cydir.client;

import me.zaboing.cydir.CommonProxy;
import me.zaboing.cydir.gui.GuiSpell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class ClientProxy extends CommonProxy {
	
	public static KeyBinding startSpell;
	
	@Override
	public void registerKeybindings() {
		startSpell = new KeyBinding("key.spell", Keyboard.KEY_F, "key.categories.cydir");
		
		ClientRegistry.registerKeyBinding(startSpell);
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (startSpell.isPressed()) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiSpell());
		}
	}
}
