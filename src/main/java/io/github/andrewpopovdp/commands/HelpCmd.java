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

import java.util.ArrayList;

import io.github.andrewpopovdp.DontBlock;
import io.github.andrewpopovdp.command.CmdException;
import io.github.andrewpopovdp.command.CmdSyntaxError;
import io.github.andrewpopovdp.command.Command;
import io.github.andrewpopovdp.util.ChatUtils;
import io.github.andrewpopovdp.util.MathUtils;

@DontBlock
public final class HelpCmd extends Command
{
	private static final int CMDS_PER_PAGE = 8;
	
	public HelpCmd()
	{
		super("help", "Shows help for a command or a list of commands.",
			".help <command>", "List commands: .help [<page>]");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length > 1)
			throw new CmdSyntaxError();
		
		String arg = args.length > 0 ? args[0] : "1";
		
		if(MathUtils.isInteger(arg))
			listCommands(Integer.parseInt(arg));
		else
			help(arg);
	}
	
	private void listCommands(int page) throws CmdException
	{
		ArrayList<Command> cmds = new ArrayList<>(CMD.getCmds().getAllCmds());
		int pages = (int)Math.ceil(cmds.size() / (double)CMDS_PER_PAGE);
		pages = Math.max(pages, 1);
		
		if(page > pages || page < 1)
			throw new CmdSyntaxError("Invalid page: " + page);
		
		String total = "Total: " + cmds.size() + " command";
		total += cmds.size() != 1 ? "s" : "";
		ChatUtils.message(total);
		
		int start = (page - 1) * CMDS_PER_PAGE;
		int end = Math.min(page * CMDS_PER_PAGE, cmds.size());
		
		ChatUtils.message("Command list (page " + page + "/" + pages + ")");
		for(int i = start; i < end; i++)
			ChatUtils.message("- " + cmds.get(i).getName());
	}
	
	private void help(String cmdName) throws CmdException
	{
		if(cmdName.startsWith("."))
			cmdName = cmdName.substring(1);
		
		Command cmd = CMD.getCmds().getCmdByName(cmdName);
		if(cmd == null)
			throw new CmdSyntaxError("Unknown command: ." + cmdName);
		
		ChatUtils.message("Available help for ." + cmdName + ":");
		cmd.printHelp();
	}
}