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
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

@Mixin(World.class)
public abstract class WorldMixin implements WorldAccess, AutoCloseable
{
	@Inject(at = {@At("HEAD")},
		method = {"getRainGradient(F)F"},
		cancellable = true)
	private void onGetRainGradient(float f, CallbackInfoReturnable<Float> cir)
	{
		return;
	}
	
	// getSkyAngle
	@Override
	public float getSkyAngle(float tickDelta)
	{
		
		long timeOfDay =
				getLevelProperties().getTimeOfDay();
		
		return getDimension().getSkyAngle(timeOfDay);
	}
	
	@Override
	public int getMoonPhase()
	{	
		return getDimension().getMoonPhase(getLunarTime());
	}
}
