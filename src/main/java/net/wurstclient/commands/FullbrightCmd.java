/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.commands;

import net.minecraft.client.options.GameOptions;
import net.wurstclient.SearchTags;
import net.wurstclient.command.CmdException;
import net.wurstclient.command.CmdSyntaxError;
import net.wurstclient.command.Command;

@SearchTags({"fullbright", "see everything", "nightvision", "visibility"})
public final class FullbrightCmd extends Command
{
	public FullbrightCmd()
	{
		super("fullbright",
			"Makes everything bright by changing the gamma value. Does not usualy work with shaders.",
			".fullbright <on/off/up/down");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		GameOptions options = MC.options;
		String[] PossibleArgs = new String[] {"on","off","up","down"};
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
					options.gamma = 5;
				
				if(GammaChange == "off")
					options.gamma = 1;
				
				if(GammaChange == "up" && options.gamma <= 5)
					options.gamma += 1;
				
				if(GammaChange == "down"  && options.gamma >= 1)
					options.gamma -= 1;
				
				GammaSet = true;
			}
		}
	}
}
