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

import io.github.andrewpopovdp.altmanager.Alt;
import io.github.andrewpopovdp.altmanager.AltManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;

public final class EditAltScreen extends AltEditorScreen
{
	private final AltManager altManager;
	private Alt editedAlt;
	
	public EditAltScreen(Screen prevScreen, AltManager altManager,
		Alt editedAlt)
	{
		super(prevScreen, new LiteralText("Edit Alt"));
		this.altManager = altManager;
		this.editedAlt = editedAlt;
	}
	
	@Override
	protected String getDefaultEmail()
	{
		return editedAlt.getEmail();
	}
	
	@Override
	protected String getDefaultPassword()
	{
		return editedAlt.getPassword();
	}
	
	@Override
	protected String getDoneButtonText()
	{
		return "Save";
	}
	
	@Override
	protected void pressDoneButton()
	{
		altManager.edit(editedAlt, getEmail(), getPassword());
		client.openScreen(prevScreen);
	}
}
