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

import io.github.andrewpopovdp.altmanager.Alt;
import io.github.andrewpopovdp.altmanager.AltManager;
import io.github.andrewpopovdp.command.CmdException;
import io.github.andrewpopovdp.command.CmdSyntaxError;
import io.github.andrewpopovdp.command.Command;
import io.github.andrewpopovdp.util.ChatUtils;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.ChatUtil;

public final class AddAltCmd extends Command
{
	public AddAltCmd()
	{
		super("addalt", "Adds a player to your alt list.", ".addalt <player>",
			"Add all players on the server: .addalt all");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length != 1)
			throw new CmdSyntaxError();
		
		String name = args[0];
		
		switch(name)
		{
			case "all":
			addAll();
			break;
			
			default:
			add(name);
			break;
		}
	}
	
	private void add(String name)
	{
		if(name.equalsIgnoreCase("Alexander01998"))
			return;
		
		CMD.getAltManager().add(new Alt(name, null, null));
		ChatUtils.message("Added 1 alt.");
	}
	
	private void addAll()
	{
		int alts = 0;
		AltManager altManager = CMD.getAltManager();
		String playerName = MC.getSession().getProfile().getName();
		
		for(PlayerListEntry entry : MC.player.networkHandler.getPlayerList())
		{
			String name = entry.getProfile().getName();
			name = ChatUtil.stripTextFormat(name);
			
			if(altManager.contains(name))
				continue;
			
			if(name.equalsIgnoreCase(playerName)
				|| name.equalsIgnoreCase("Alexander01998"))
				continue;
			
			altManager.add(new Alt(name, null, null));
			alts++;
		}
		
		ChatUtils.message("Added " + alts + (alts == 1 ? " alt." : " alts."));
	}
}
