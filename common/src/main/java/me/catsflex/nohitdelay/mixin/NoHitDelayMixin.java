package me.catsflex.nohitdelay.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class NoHitDelayMixin {
	
	@Shadow
	protected int missTime;
	
	@Inject(method = "startAttack", at = @At("HEAD"))
	private void onStartAttack(CallbackInfoReturnable<Boolean> cir) {
		this.missTime = 0;
	}
}
