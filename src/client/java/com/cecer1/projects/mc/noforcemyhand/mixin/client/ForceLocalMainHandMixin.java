package com.cecer1.projects.mc.noforcemyhand.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
abstract class ForceLocalMainHandMixin extends LivingEntity {

	protected ForceLocalMainHandMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "getMainArm", at = @At("HEAD"), cancellable = true)
	public void getMainAram(CallbackInfoReturnable<Arm> cir) {
		MinecraftClient client = MinecraftClient.getInstance();
		//noinspection ConstantValue
		if ((Object)this == client.player) {
			cir.setReturnValue(MinecraftClient.getInstance().options.getMainArm().getValue());
		}
	}
}