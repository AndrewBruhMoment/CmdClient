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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.andrewpopovdp.CmdClient;
import net.minecraft.client.render.Camera;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;

@Mixin(Camera.class)
public abstract class CameraMixin
{
	@Inject(at = {@At("HEAD")},
		method = {"clipToSpace(D)D"},
		cancellable = true)
	private void onClipToSpace(double desiredCameraDistance,
		CallbackInfoReturnable<Double> cir)
	{
		cir.setReturnValue(desiredCameraDistance);
	}
	
	@Inject(at = {@At("HEAD")},
		method = {"getSubmergedFluidState()Lnet/minecraft/fluid/FluidState;"},
		cancellable = true)
	private void getSubmergedFluidState(CallbackInfoReturnable<FluidState> cir)
	{
		cir.setReturnValue(Fluids.EMPTY.getDefaultState());
	}
}
