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

import io.github.andrewpopovdp.command.CmdError;
import io.github.andrewpopovdp.command.CmdException;
import io.github.andrewpopovdp.command.CmdSyntaxError;
import io.github.andrewpopovdp.command.Command;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.StringTag;

public final class AuthorCmd extends Command
{
	public AuthorCmd()
	{
		super("author", "Changes the author of a written book.\n"
			+ "Requires creative mode.", ".author <author>");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length == 0)
			throw new CmdSyntaxError();
		
		if(!MC.player.abilities.creativeMode)
			throw new CmdError("Creative mode only.");
		
		ItemStack heldItem = MC.player.inventory.getMainHandStack();
		int heldItemID = Item.getRawId(heldItem.getItem());
		int writtenBookID = Item.getRawId(Items.WRITTEN_BOOK);
		
		if(heldItemID != writtenBookID)
			throw new CmdError(
				"You must hold a written book in your main hand.");
		
		String author = String.join(" ", args);
		heldItem.putSubTag("author", StringTag.of(author));
	}
}