/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.commands;

import io.github.andrewpopovdp.command.CmdException;
import io.github.andrewpopovdp.command.Command;

public final class UnbindCmd extends Command
{
	public UnbindCmd()
	{
		super("unbind", "Shortcut for '.binds remove'.", ".unbind <key>",
			"Use .binds for more options.");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		CMD.getCmdProcessor()
			.process("binds remove " + String.join(" ", args));
	}
}
