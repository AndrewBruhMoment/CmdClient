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

@SearchTags({"ice", "slippery", "slide", "no friction"})
public final class SlideCmd extends Command
{
	public SlideCmd()
	{
		super("slide",
			"Makes you slide as if you are on ice. Can be set to infinity, where you should never stop sliding.",
			".slide <Ice/Infinite/Off>");
	}
	
	public static Float SlipperinessOverwrite = 0.6F;
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length != 1)
			throw new CmdSyntaxError();
		
		if(args[0].equalsIgnoreCase("ice"))
		{
			SlipperinessOverwrite = 0.999F;
			ChatUtils.message("Turned Slide to Ice");

		}
		if(args[0].equalsIgnoreCase("infinite"))
		{
			SlipperinessOverwrite = 1.1F;
			ChatUtils.message("Turned Slide to Intinity");

		}
		if(args[0].equalsIgnoreCase("off"))
		{
			SlipperinessOverwrite = 0.6F;
			ChatUtils.message("Turned Slide Off");
		}
		
	}
}
