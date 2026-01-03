package me.catsflex.nohitdelay.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuImpl implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		final String YACL_ID = "yet_another_config_lib_v3";
		
		if (FabricLoader.getInstance().isModLoaded(YACL_ID)) {
			return YACLIntegration::createScreen;
		}
		return parent -> null;
	}
}
