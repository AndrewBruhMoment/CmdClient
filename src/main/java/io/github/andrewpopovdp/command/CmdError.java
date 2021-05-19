/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.command;

import io.github.andrewpopovdp.util.ChatUtils;

public final class CmdError extends CmdException
{
	public CmdError(String message)
	{
		super(message);
	}
	
	@Override
	public void printToChat(Command cmd)
	{
		ChatUtils.error(getMessage());
	}
}
