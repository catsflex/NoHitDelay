package me.catsflex.nohitdelay;

import me.catsflex.nohitdelay.config.NoHitDelayConfig;
import net.fabricmc.api.ModInitializer;

public class NoHitDelay implements ModInitializer {
	@Override
	public void onInitialize() {
		NoHitDelayConfig.getInstance();
	}
}
