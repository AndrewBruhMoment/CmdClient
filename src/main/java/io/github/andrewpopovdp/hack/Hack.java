/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.hack;

import java.util.Objects;

import io.github.andrewpopovdp.Category;
import io.github.andrewpopovdp.Feature;
import io.github.andrewpopovdp.hacks.TooManyHaxHack;

public abstract class Hack extends Feature
{
	private final String name;
	private final String description;
	private Category category;
	
	private boolean enabled;
	private final boolean stateSaved =
		!getClass().isAnnotationPresent(DontSaveState.class);
	
	public Hack(String name, String description)
	{
		this.name = Objects.requireNonNull(name);
		this.description = Objects.requireNonNull(description);
	}
	
	@Override
	public final String getName()
	{
		return name;
	}
	
	public String getRenderName()
	{
		return name;
	}
	
	@Override
	public final String getDescription()
	{
		return description;
	}
	
	@Override
	public final Category getCategory()
	{
		return category;
	}
	
	protected final void setCategory(Category category)
	{
		this.category = category;
	}
	
	@Override
	public final boolean isEnabled()
	{
		return enabled;
	}
	
	public final void setEnabled(boolean enabled)
	{
		if(this.enabled == enabled)
			return;
		
		TooManyHaxHack tooManyHax = CMD.getHax().tooManyHaxHack;
		if(enabled && tooManyHax.isEnabled() && tooManyHax.isBlocked(this))
			return;
		
		this.enabled = enabled;
		
		if(enabled)
			onEnable();
		else
			onDisable();
		
		if(stateSaved)
			CMD.getHax().saveEnabledHax();
	}
	
	@Override
	public final String getPrimaryAction()
	{
		return enabled ? "Disable" : "Enable";
	}
	
	@Override
	public final void doPrimaryAction()
	{
		setEnabled(!enabled);
	}
	
	public final boolean isStateSaved()
	{
		return stateSaved;
	}
	
	protected void onEnable()
	{
		
	}
	
	protected void onDisable()
	{
		
	}
}
