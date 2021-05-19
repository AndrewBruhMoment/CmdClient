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

import io.github.andrewpopovdp.event.CancellableEvent;
import io.github.andrewpopovdp.event.Listener;

public interface VelocityFromFluidListener extends Listener
{
	public void onVelocityFromFluid(VelocityFromFluidEvent event);
	
	public static class VelocityFromFluidEvent
		extends CancellableEvent<VelocityFromFluidListener>
	{
		@Override
		public void fire(ArrayList<VelocityFromFluidListener> listeners)
		{
			for(VelocityFromFluidListener listener : listeners)
			{
				listener.onVelocityFromFluid(this);
				
				if(isCancelled())
					break;
			}
		}
		
		@Override
		public Class<VelocityFromFluidListener> getListenerType()
		{
			return VelocityFromFluidListener.class;
		}
	}
}
