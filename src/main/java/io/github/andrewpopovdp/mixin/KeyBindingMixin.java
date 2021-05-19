/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import io.github.andrewpopovdp.CmdClient;
import io.github.andrewpopovdp.mixinterface.IKeyBinding;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;

@Mixin(KeyBinding.class)
public class KeyBindingMixin implements IKeyBinding
{
	@Shadow
	private InputUtil.Key boundKey;
	
	@Override
	public boolean isActallyPressed()
	{
		long handle = CmdClient.MC.getWindow().getHandle();
		int code = boundKey.getCode();
		return InputUtil.isKeyPressed(handle, code);
	}
}
