package me.zaboing.cydir.gui;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.zaboing.cydir.CydirUtils;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.client.event.GuiScreenEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GuiServer {

	private static Set<ServerData> relentlessServers = new HashSet<ServerData>();

	@SubscribeEvent
	public void onInitGui(final GuiScreenEvent.InitGuiEvent.Post event) {
		if (event.gui instanceof GuiScreenServerList) {
			List buttonList = CydirUtils.getPrivateField(event.gui,
					"buttonList", GuiScreen.class, List.class);
			buttonList.add(new GuiButton(2, event.gui.width / 2 - 100,
					event.gui.height / 4 + 72 + 12, "Relentless Connect") {
				@Override
				public void func_146113_a(SoundHandler p_146113_1_) {
					super.func_146113_a(p_146113_1_);
					ServerData data = CydirUtils.getPrivateField(event.gui,
							"field_146301_f", ServerData.class);
					data.serverIP = CydirUtils.getPrivateField(event.gui,
							"field_146302_g", GuiTextField.class).getText();

					relentlessServers.add(data);

					CydirUtils.getPrivateField(event.gui, "field_146303_a",
							GuiScreen.class).confirmClicked(true, 0);
				}
			});
		} else if (event.gui instanceof GuiConnecting) {
			List buttonList = CydirUtils.getPrivateField(event.gui,
					"buttonList", GuiScreen.class, List.class);
			GuiMultiplayer parent = CydirUtils.getPrivateField(event.gui,
					"field_146374_i", GuiMultiplayer.class);
			ServerData data = CydirUtils.getPrivateField(parent,
					"field_146811_z", ServerData.class);
			if (relentlessServers.contains(data)) {
				((GuiButton) buttonList.get(0)).enabled = false;
			}
		}
	}

	@SubscribeEvent
	public void onGuiDraw(GuiScreenEvent.DrawScreenEvent.Pre event) {

		if (event.gui instanceof GuiDisconnected) {
			GuiMultiplayer parent = CydirUtils.getPrivateField(event.gui,
					"field_146307_h", GuiMultiplayer.class);
			ServerData data = CydirUtils.getPrivateField(parent,
					"field_146811_z", ServerData.class);
			if (relentlessServers.contains(data)) {
				event.setCanceled(true);
				event.gui.mc.displayGuiScreen(new GuiConnecting(parent,
						event.gui.mc, data));
			}
		}
	}

	// @SubscribeEvent
	// public void onRenderGui(GuiScreenEvent.DrawScreenEvent event) {
	// System.out.println(event.gui);
	// GuiCon
	// }
}
