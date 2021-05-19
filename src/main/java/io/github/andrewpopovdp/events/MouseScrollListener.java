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

public interface MouseScrollListener extends Listener
{
	public void onMouseScroll(double amount);
	
	public static class MouseScrollEvent extends Event<MouseScrollListener>
	{
		private final double amount;
		
		public MouseScrollEvent(double amount)
		{
			this.amount = amount;
		}
		
		@Override
		public void fire(ArrayList<MouseScrollListener> listeners)
		{
			for(MouseScrollListener listener : listeners)
				listener.onMouseScroll(amount);
		}
		
		@Override
		public Class<MouseScrollListener> getListenerType()
		{
			return MouseScrollListener.class;
		}
	}
}
