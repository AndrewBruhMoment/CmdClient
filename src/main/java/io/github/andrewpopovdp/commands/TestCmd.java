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

@SearchTags({".test", "cmd test", "command test", "color test"})
public final class TestCmd extends Command
{
	public TestCmd()
	{
		super("test",
			"Sends a seiries of client-side messages that are meant to test all working features. These are currently colors and other text features.",
			".test");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length > 0)
			throw new CmdSyntaxError("Bruh");
		
		if(args.length == 0)
		{
			ChatUtils.message("Color Test #1: " + "\u00a70Test " + "\u00a71Test " + "\u00a72Test " + "\u00a73Test " + "\u00a74Test " + "\u00a75Test " + "\u00a76Test " + "\u00a77Test " + "\u00a78Test " + "\u00a79Test " + "\u00a7r");
			ChatUtils.message("Color Test #2: " + "\u00a7aTest " + "\u00a7bTest " + "\u00a7cTest " + "\u00a7dTest " + "\u00a7eTest " + "\u00a7fTest " + "\u00a7r");
			ChatUtils.message("Formating Test: " + "\u00a7kTest " + "\u00a7r" + "\u00a7lTest " + "\u00a7r" + "\u00a7mTest " + "\u00a7r" + "\u00a7nTest " + "\u00a7r" + "\u00a7oTest " + "\u00a7r");
			ChatUtils.message("Overlap Test: " + "\u00a7k\u00a7l\u00a7m\u00a7n\u00a7o\u00a7pTest");
		}
	}
}
	
