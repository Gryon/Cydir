package me.zaboing.cydir;

import me.zaboing.cydir.gui.GuiSpell;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	public void registerRenderers() {
	}

	public void registerKeybindings() {
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		if (id == GuiSpell.GUI_ID) {
			return new GuiSpell();
		}
		return null;
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}
}
