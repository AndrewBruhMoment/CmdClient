/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.commands;

import io.github.andrewpopovdp.command.CmdException;
import io.github.andrewpopovdp.command.CmdSyntaxError;
import io.github.andrewpopovdp.command.Command;
import io.github.andrewpopovdp.util.ChatUtils;
import net.minecraft.util.math.BlockPos;

public final class GetPosCmd extends Command
{
	public GetPosCmd()
	{
		super("getpos", "Shows your current position.", ".getpos",
			"Copy to clipboard: .getpos copy");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		BlockPos pos = new BlockPos(MC.player.getPos());
		String posString = pos.getX() + " " + pos.getY() + " " + pos.getZ();
		
		switch(String.join(" ", args).toLowerCase())
		{
			case "":
			ChatUtils.message("Position: " + posString);
			break;
			
			case "copy":
			MC.keyboard.setClipboard(posString);
			ChatUtils.message("Position copied to clipboard.");
			break;
			
			default:
			throw new CmdSyntaxError();
		}
	}
	
	@Override
	public String getPrimaryAction()
	{
		return "Get Position";
	}
	
	@Override
	public void doPrimaryAction()
	{
		CMD.getCmdProcessor().process("getpos");
	}
}
