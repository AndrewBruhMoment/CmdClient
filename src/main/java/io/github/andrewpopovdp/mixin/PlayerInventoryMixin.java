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
import net.minecraft.entity.player.PlayerInventory;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin
{
	@Inject(at = {@At("HEAD")},
		method = {"scrollInHotbar(D)V"},
		cancellable = true)
	private void onScrollInHotbar(double scrollAmount, CallbackInfo ci)
	{
		if(CmdClient.INSTANCE.getZoomKey().isPressed())
			ci.cancel();
	}
}
