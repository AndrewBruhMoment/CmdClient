/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.commands;

import io.github.andrewpopovdp.SearchTags;
import io.github.andrewpopovdp.command.CmdError;
import io.github.andrewpopovdp.command.CmdException;
import io.github.andrewpopovdp.command.CmdSyntaxError;
import io.github.andrewpopovdp.command.Command;
import io.github.andrewpopovdp.util.ChatUtils;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

@SearchTags({"view nbt", "NBTViewer", "nbt viewer"})
public final class ViewNbtCmd extends Command
{
	public ViewNbtCmd()
	{
		super("viewnbt", "Shows you the NBT data of an item.", ".viewnbt",
			"Copy to clipboard: .viewnbt copy");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		ClientPlayerEntity player = MC.player;
		ItemStack stack = player.inventory.getMainHandStack();
		if(stack.isEmpty())
			throw new CmdError("You must hold an item in your main hand.");
		
		CompoundTag tag = stack.getTag();
		String nbt = tag == null ? "" : tag.asString();
		
		switch(String.join(" ", args).toLowerCase())
		{
			case "":
			ChatUtils.message("NBT: " + nbt);
			break;
			
			case "copy":
			MC.keyboard.setClipboard(nbt);
			ChatUtils.message("NBT data copied to clipboard.");
			break;
			
			default:
			throw new CmdSyntaxError();
		}
	}
}
