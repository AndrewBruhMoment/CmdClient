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

import io.github.andrewpopovdp.command.CmdException;
import io.github.andrewpopovdp.command.CmdSyntaxError;
import io.github.andrewpopovdp.command.Command;

public final class LeaveCmd extends Command
{
	public LeaveCmd()
	{
		super("leave", "Instantly disconnects from the server.", ".leave");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length == 1 && args[0].equalsIgnoreCase("taco"))
			for(int i = 0; i < 128; i++)
				MC.player.sendChatMessage("Taco!");
		else if(args.length != 0)
			throw new CmdSyntaxError();
		
		MC.world.disconnect();
	}
	
	@Override
	public String getPrimaryAction()
	{
		return "Leave";
	}
	
	@Override
	public void doPrimaryAction()
	{
		CMD.getCmdProcessor().process("leave");
	}
}
