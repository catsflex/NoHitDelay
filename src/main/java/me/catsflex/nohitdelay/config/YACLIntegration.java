package me.catsflex.nohitdelay.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class YACLIntegration {
	public static Screen createScreen(Screen parent) {
		NoHitDelayConfig config = NoHitDelayConfig.getInstance();
		
		return YetAnotherConfigLib.createBuilder()
			.title(Component.literal("NoHitDelay Configuration"))
			.category(ConfigCategory.createBuilder()
				.name(Component.literal("Settings"))
				
				// 'Enabled' option
				.option(Option.<Boolean>createBuilder()
					.name(Component.literal("Enabled"))
					.description(OptionDescription.of(Component.literal("Removes the 10-tick (0.5s) timer.")))
					.binding(NoHitDelayConfig.ENABLED, () -> config.enabled, v -> config.enabled = v)
					.controller(TickBoxControllerBuilder::create)
					.build())
				
				// 'Debug Mode' option
				.option(Option.<Boolean>createBuilder()
					.name(Component.literal("Debug Mode"))
					.description(OptionDescription.of(Component.literal("Prints mod actions to console.")))
					.binding(NoHitDelayConfig.DEBUG_MODE, () -> config.debugMode, v -> config.debugMode = v)
					.controller(TickBoxControllerBuilder::create)
					.build())
				
				.build())
			.save(config::save)
			.build()
			.generateScreen(parent);
	}
}
