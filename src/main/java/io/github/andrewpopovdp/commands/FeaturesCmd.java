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

import io.github.andrewpopovdp.CmdClient;
import io.github.andrewpopovdp.command.CmdException;
import io.github.andrewpopovdp.command.CmdSyntaxError;
import io.github.andrewpopovdp.command.Command;
import io.github.andrewpopovdp.hack.Hack;
import io.github.andrewpopovdp.other_feature.OtherFeature;
import io.github.andrewpopovdp.util.ChatUtils;

public final class FeaturesCmd extends Command
{
	public FeaturesCmd()
	{
		super("features",
			"Shows the number of features and some other\n" + "statistics.",
			".features");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length != 0)
			throw new CmdSyntaxError();
		
		if(CmdClient.VERSION.startsWith("7.0pre"))
			ChatUtils.warning(
				"This is just a pre-release! It doesn't (yet) have all of the features of Wurst 7.0! See download page for details.");
		
		int hax = CMD.getHax().countHax();
		int cmds = CMD.getCmds().countCmds();
		int otfs = CMD.getOtfs().countOtfs();
		int all = hax + cmds + otfs;
		
		ChatUtils.message("All features: " + all);
		ChatUtils.message("Hacks: " + hax);
		ChatUtils.message("Commands: " + cmds);
		ChatUtils.message("Other features: " + otfs);
		
		int settings = 0;
		for(Hack hack : CMD.getHax().getAllHax())
			settings += hack.getSettings().size();
		for(Command cmd : CMD.getCmds().getAllCmds())
			settings += cmd.getSettings().size();
		for(OtherFeature otf : CMD.getOtfs().getAllOtfs())
			settings += otf.getSettings().size();
		
		ChatUtils.message("Settings: " + settings);
	}
	
	@Override
	public String getPrimaryAction()
	{
		return "Show Statistics";
	}
	
	@Override
	public void doPrimaryAction()
	{
		CMD.getCmdProcessor().process("features");
	}
}
