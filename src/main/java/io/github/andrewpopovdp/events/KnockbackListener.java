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

public interface KnockbackListener extends Listener
{
	public void onKnockback(KnockbackEvent event);
	
	public static class KnockbackEvent extends Event<KnockbackListener>
	{
		private double x;
		private double y;
		private double z;
		private final double defaultX;
		private final double defaultY;
		private final double defaultZ;
		
		public KnockbackEvent(double x, double y, double z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			defaultX = x;
			defaultY = y;
			defaultZ = z;
		}
		
		@Override
		public void fire(ArrayList<KnockbackListener> listeners)
		{
			for(KnockbackListener listener : listeners)
				listener.onKnockback(this);
		}
		
		@Override
		public Class<KnockbackListener> getListenerType()
		{
			return KnockbackListener.class;
		}
		
		public double getX()
		{
			return x;
		}
		
		public void setX(double x)
		{
			this.x = x;
		}
		
		public double getY()
		{
			return y;
		}
		
		public void setY(double y)
		{
			this.y = y;
		}
		
		public double getZ()
		{
			return z;
		}
		
		public void setZ(double z)
		{
			this.z = z;
		}
		
		public double getDefaultX()
		{
			return defaultX;
		}
		
		public double getDefaultY()
		{
			return defaultY;
		}
		
		public double getDefaultZ()
		{
			return defaultZ;
		}
	}
}
