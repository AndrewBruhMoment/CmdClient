/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.altmanager.screens;

import io.github.andrewpopovdp.altmanager.AltManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;

public final class AddAltScreen extends AltEditorScreen
{
	private final AltManager altManager;
	
	public AddAltScreen(Screen prevScreen, AltManager altManager)
	{
		super(prevScreen, new LiteralText("New Alt"));
		this.altManager = altManager;
	}
	
	@Override
	protected String getDoneButtonText()
	{
		return "Add";
	}
	
	@Override
	protected void pressDoneButton()
	{
		altManager.add(getEmail(), getPassword(), false);
		client.openScreen(prevScreen);
	}
}
