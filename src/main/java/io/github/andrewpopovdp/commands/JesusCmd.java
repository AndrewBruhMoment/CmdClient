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
import io.github.andrewpopovdp.command.CmdException;
import io.github.andrewpopovdp.command.CmdSyntaxError;
import io.github.andrewpopovdp.command.Command;
import io.github.andrewpopovdp.util.ChatUtils;

@SearchTags({"walk on water", "jesus", "no swim", "g8ev"})
public final class JesusCmd extends Command
{
	public JesusCmd()
	{
		super("jesus",
			"Transform into g8ev and walk across water",
			".jesus <On/Part/Off>");
	}
	
	public static String JesusOn = "false";
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length != 1)
			throw new CmdSyntaxError();
		
		if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("g8ev"))
		{
			JesusOn = "true";
			ChatUtils.message("Turned Jesus On");
			if(args[0].equalsIgnoreCase("g8ev"))
				ChatUtils.message("You hold the true power of g8ev");
		}
		if(args[0].equalsIgnoreCase("part"))
		{
			JesusOn = "part";
			ChatUtils.message("Turned Jesus On");
			ChatUtils.message("Parting The Seas");
		}
		if(args[0].equalsIgnoreCase("off"))
		{
			JesusOn = "false";
			ChatUtils.message("Turned Jesus Off");
		}
		
	}
}
