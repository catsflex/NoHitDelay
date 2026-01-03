package me.catsflex.nohitdelay.mixin;

import me.catsflex.nohitdelay.config.NoHitDelayConfig;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class NoHitDelayMixin {
	// Miss penalty time, first appeared in 1.8 version
	@Shadow
	public int missTime;
	
	private final String PREFIX = "[NoHitDelay]";
	
	@Inject(method = "startAttack", at = @At("HEAD"))
	private void onStartAttack(CallbackInfoReturnable<Boolean> cir) {
		var cfg = NoHitDelayConfig.getInstance();
		int mt = missTime;
		
		// Reset the timer if enabled in config
		if (cfg.enabled) {
			missTime = 0;
			
			// Log results if there were changes in missTime
			if (cfg.debugMode) {
				if (mt == 0) return;
				System.out.println(PREFIX + " missTime: " + mt + " -> " + missTime);
			}
		}
	}
}
