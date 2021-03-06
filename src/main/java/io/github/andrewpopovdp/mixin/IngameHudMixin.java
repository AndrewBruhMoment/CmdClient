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
import io.github.andrewpopovdp.event.EventManager;
import io.github.andrewpopovdp.events.GUIRenderListener.GUIRenderEvent;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(InGameHud.class)
public class IngameHudMixin extends DrawableHelper
{
	@Inject(
		at = {@At(value = "INVOKE",
			target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V",
			ordinal = 4)},
		method = {"render(Lnet/minecraft/client/util/math/MatrixStack;F)V"})
	private void onRender(MatrixStack matrixStack, float partialTicks,
		CallbackInfo ci)
	{
		if(CmdClient.MC.options.debugEnabled)
			return;
		
		GUIRenderEvent event = new GUIRenderEvent(matrixStack, partialTicks);
		EventManager.fire(event);
	}
	
	@Inject(at = {@At("HEAD")},
		method = {"renderPumpkinOverlay()V"},
		cancellable = true)
	private void onRenderPumpkinOverlay(CallbackInfo ci)
	{
		ci.cancel();
	}
}
