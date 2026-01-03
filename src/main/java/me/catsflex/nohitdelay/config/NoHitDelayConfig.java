package me.catsflex.nohitdelay.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class NoHitDelayConfig {
	// Default values
	public static boolean ENABLED = true;
	public static boolean DEBUG_MODE = false;
	
	// Current values
	public boolean enabled = ENABLED;
	public boolean debugMode = DEBUG_MODE;
	
	// Config saving stuff
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final String FILE_PATH = "config/nohitdelay.json";
	private static final File FILE = new File(Minecraft.getInstance().gameDirectory, FILE_PATH);
	
	// Singleton
	private static NoHitDelayConfig instance;
	
	public static NoHitDelayConfig getInstance() {
		if (instance == null) {
			instance = load();
		}
		return instance;
	}
	
	public static NoHitDelayConfig load() {
		if (FILE.exists()) {
			try (FileReader reader = new FileReader(FILE)) {
				return GSON.fromJson(reader, NoHitDelayConfig.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		NoHitDelayConfig config = new NoHitDelayConfig();
		config.save();
		return config;
	}
	
	public void save() {
		try (FileWriter writer = new FileWriter(FILE)) {
			GSON.toJson(this, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
