/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.events;

import java.util.ArrayList;

import io.github.andrewpopovdp.event.Event;
import io.github.andrewpopovdp.event.Listener;

public interface DeathListener extends Listener
{
	public void onDeath();
	
	public static class DeathEvent extends Event<DeathListener>
	{
		public static final DeathEvent INSTANCE = new DeathEvent();
		
		@Override
		public void fire(ArrayList<DeathListener> listeners)
		{
			for(DeathListener listener : listeners)
				listener.onDeath();
		}
		
		@Override
		public Class<DeathListener> getListenerType()
		{
			return DeathListener.class;
		}
	}
}
