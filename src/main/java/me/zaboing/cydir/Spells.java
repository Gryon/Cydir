package me.zaboing.cydir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Spells {
	public static final Set<String> targetWords = new HashSet<String>();	
	public static final Set<String> otherWords = new HashSet<String>();
//	
	private static final ResourceLocation wordLocation = new ResourceLocation("cydir", "wordsofpower.json");
	
	public static void loadWords() {
		try {
			InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(wordLocation).getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			JsonParser parser = new JsonParser();
			JsonObject words = parser.parse(reader).getAsJsonObject();
			JsonArray targets = words.get("targets").getAsJsonArray();
			for (int i = 0; i < targets.size(); i++) {
				targetWords.add(targets.get(i).getAsString());
			}
			JsonArray others = words.get("others").getAsJsonArray();
			for (int i = 0; i < others.size(); i++) {
				otherWords.add(others.get(i).getAsString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
