/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.andrewpopovdp.CmdClient;
import io.github.andrewpopovdp.util.LastServerRememberer;
import net.minecraft.client.gui.screen.DirectConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Text;

@Mixin(DirectConnectScreen.class)
public class DirectConnectScreenMixin extends Screen
{
	@Shadow
	@Final
	private ServerInfo serverEntry;
	
	private DirectConnectScreenMixin(CmdClient wurst, Text text_1)
	{
		super(text_1);
	}
	
	@Inject(at = {@At("TAIL")}, method = {"saveAndClose()V"})
	private void onSaveAndClose(CallbackInfo ci)
	{
		LastServerRememberer.setLastServer(serverEntry);
	}
}
