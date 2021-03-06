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

public interface IsPlayerInWaterListener extends Listener
{
	public void onIsPlayerInWater(IsPlayerInWaterEvent event);
	
	public static class IsPlayerInWaterEvent
		extends Event<IsPlayerInWaterListener>
	{
		private boolean inWater;
		private final boolean normallyInWater;
		
		public IsPlayerInWaterEvent(boolean inWater)
		{
			this.inWater = inWater;
			normallyInWater = inWater;
		}
		
		public boolean isInWater()
		{
			return inWater;
		}
		
		public void setInWater(boolean inWater)
		{
			this.inWater = inWater;
		}
		
		public boolean isNormallyInWater()
		{
			return normallyInWater;
		}
		
		@Override
		public void fire(ArrayList<IsPlayerInWaterListener> listeners)
		{
			for(IsPlayerInWaterListener listener : listeners)
				listener.onIsPlayerInWater(this);
		}
		
		@Override
		public Class<IsPlayerInWaterListener> getListenerType()
		{
			return IsPlayerInWaterListener.class;
		}
	}
}
