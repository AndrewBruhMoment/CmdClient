/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.andrewpopovdp.CmdClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin
{
	@Inject(at = {@At("HEAD")},
		method = {
			"renderFireOverlay(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/util/math/MatrixStack;)V"},
		cancellable = true)
	private static void onRenderFireOverlay(MinecraftClient minecraftClient,
		MatrixStack matrixStack, CallbackInfo ci)
	{
		ci.cancel();
	}
	
	@Inject(at = {@At("HEAD")},
		method = {
			"renderUnderwaterOverlay(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/util/math/MatrixStack;)V"},
		cancellable = true)
	private static void onRenderUnderwaterOverlay(
		MinecraftClient minecraftClient, MatrixStack matrixStack,
		CallbackInfo ci)
	{
		ci.cancel();
	}
}
