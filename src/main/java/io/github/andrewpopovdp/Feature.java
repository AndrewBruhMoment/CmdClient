/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import io.github.andrewpopovdp.event.EventManager;
import io.github.andrewpopovdp.mixinterface.IMinecraftClient;
import io.github.andrewpopovdp.settings.Setting;
import net.minecraft.client.MinecraftClient;

public abstract class Feature
{
	protected static final CmdClient CMD = CmdClient.INSTANCE;
	protected static final EventManager EVENTS = CMD.getEventManager();
	protected static final MinecraftClient MC = CmdClient.MC;
	protected static final IMinecraftClient IMC = CmdClient.IMC;
	
	private final LinkedHashMap<String, Setting> settings =
		new LinkedHashMap<>();
	
	private final String searchTags =
		getClass().isAnnotationPresent(SearchTags.class) ? String.join("\u00a7",
			getClass().getAnnotation(SearchTags.class).value()) : "";
	
	private final boolean safeToBlock =
		!getClass().isAnnotationPresent(DontBlock.class);
	
	public abstract String getName();
	
	public abstract String getDescription();
	
	public Category getCategory()
	{
		return null;
	}
	
	public abstract String getPrimaryAction();
	
	public void doPrimaryAction()
	{
		
	}
	
	public boolean isEnabled()
	{
		return false;
	}
	
	public final Map<String, Setting> getSettings()
	{
		return Collections.unmodifiableMap(settings);
	}
	
	protected final void addSetting(Setting setting)
	{
		String key = setting.getName().toLowerCase();
		
		if(settings.containsKey(key))
			throw new IllegalArgumentException(
				"Duplicate setting: " + getName() + " " + key);
		
	}
	
	public final String getSearchTags()
	{
		return searchTags;
	}
	
	public final boolean isSafeToBlock()
	{
		return safeToBlock;
	}
}
