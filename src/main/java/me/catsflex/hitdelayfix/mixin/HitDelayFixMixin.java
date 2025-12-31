package me.catsflex.hitdelayfix.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class HitDelayFixMixin {
	
	// Debug switcher
	@Unique
	private static final boolean DEBUG = false;
	
	// The delay that messes up hit registration
	@Shadow
	protected int missTime;
	
	// Vanilla delay that would have been without this mod
	@Unique
	private int vanillaMissTime;
	
	@Inject(method = "startAttack", at = @At("HEAD"))
	private void beforeAttack(CallbackInfoReturnable<Boolean> cir) {
		
		// Save vanilla delay and reset it for better hit registration
		vanillaMissTime = missTime;
		missTime = 0;
	}
	
	@Inject(method = "startAttack", at = @At("TAIL"))
	private void afterAttack(CallbackInfoReturnable<Boolean> cir) {
		if (!DEBUG) return;
		
		if (vanillaMissTime > 0 && hitResult != null && hitResult.getType() == HitResult.Type.ENTITY) {
			if (player == null) return;
			
			// Action bar output
			player.displayClientMessage(
				Component.literal("§a✔ Hit registered! Vanilla delay: " + vanillaMissTime + " tick(s)"),
				true
			);
			
			// Play sound (sound, volume, pitch)
			player.playSound(
				SoundEvents.NOTE_BLOCK_BELL.value(),
				1.0F,
				1.0F
			);
		}
	}
	
	@Shadow
	@Nullable
	public LocalPlayer player;
	@Shadow
	@Nullable
	public HitResult hitResult;
}
