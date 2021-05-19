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
import net.minecraft.client.options.GameOptions;

@SearchTags({"fulldark", "see nothing", "black", "blindness"})
public final class FulldarkCmd extends Command
{
	public FulldarkCmd()
	{
		super("fulldark",
			"Makes everything dark by changing the gamma value. Does not usualy work with shaders.",
			".fulldark <on/off>");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		GameOptions options = MC.options;
		String[] PossibleArgs = new String[] {"on","off"};
		Boolean FoundArg1 = false;
		Boolean GammaSet = false;
		String GammaChange = "";
		
		if(args.length != 1)
			throw new CmdSyntaxError("Only Use One Argument.");
		
		if(args.length == 1)
		{
			for(int i = 0; i < PossibleArgs.length; i++)
			{
				if(args[0].toString().equals(PossibleArgs[i].toString()))
				{
					GammaChange = PossibleArgs[i];
					FoundArg1 = true;
					break;
				}else
				{
					if(i == PossibleArgs.length - 1)
					{
						System.out.println("(Debug) Arg1 Check Failed");
						FoundArg1 = false;
						throw new CmdSyntaxError("Your arguments aren't right.");
					}else
						System.out.println("(Debug)still checking arg1");
					
				}	
			}
			
			while(FoundArg1 == true && GammaSet != true)
			{
				if(GammaChange == "on")
					options.gamma = -1;
				
				if(GammaChange == "off")
					options.gamma = 1;
				
				GammaSet = true;
			}
		}
	}
}
