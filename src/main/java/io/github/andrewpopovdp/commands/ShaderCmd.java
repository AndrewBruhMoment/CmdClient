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
import io.github.andrewpopovdp.mixinterface.IGameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@SearchTags({"shader", "lsd", "drugs", "secret"})
public final class ShaderCmd extends Command
{
	public ShaderCmd()
	{
		super("shader", "Toggles a Build-In Shader",
			".shader <art/bits/bumpy/creeper/desaturate/notch/invert/outline/pencil/phosphor/spider/transparency/wobble>");
	}
	
	String[] ShaderList = new String[]{"art", "bits", "bumpy", "creeper",
		"desaturate", "notch", "invert", "outline", "pencil", "phosphor",
		"spider", "transparency", "wobble"};
	Boolean FoundArg1 = false;
	Boolean Generated = false;
	String shaderName = "";
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length > 1)
			throw new CmdSyntaxError("Check your argument");
		
		if(args[0].equalsIgnoreCase("off") && args.length == 1)
			MC.gameRenderer.disableShader();
		
		for(int i = 0; i < ShaderList.length; i++)
			if(args[0].toString().equals(ShaderList[i].toString()))
			{
				shaderName = ShaderList[i];
				FoundArg1 = true;
				break;
			}else
			{
				if(i == ShaderList.length - 1)
				{
					System.out.println("(Debug)Action Check Failed");
					if(!args[0].equalsIgnoreCase("off"))
						throw new CmdSyntaxError(
							"The shader you want to apply does not exist. Check your spelling.");
				}else
					System.out.println("(Debug)still checking");
				
			}
		
		if(args.length == 1 && FoundArg1 == true)
		{
			if(!(MC.getCameraEntity() instanceof PlayerEntity))
			{
				return;
			}
			
			if(MC.gameRenderer.getShader() != null)
				MC.gameRenderer.disableShader();
			
			((IGameRenderer)MC.gameRenderer).loadWurstShader(
				new Identifier("shaders/post/" + shaderName + ".json"));
		}
	}
}
