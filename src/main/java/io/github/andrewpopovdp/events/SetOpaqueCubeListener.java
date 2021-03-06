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

public interface SetOpaqueCubeListener extends Listener
{
	public void onSetOpaqueCube(SetOpaqueCubeEvent event);
	
	public static class SetOpaqueCubeEvent
		extends CancellableEvent<SetOpaqueCubeListener>
	{
		@Override
		public void fire(ArrayList<SetOpaqueCubeListener> listeners)
		{
			for(SetOpaqueCubeListener listener : listeners)
			{
				listener.onSetOpaqueCube(this);
				
				if(isCancelled())
					break;
			}
		}
		
		@Override
		public Class<SetOpaqueCubeListener> getListenerType()
		{
			return SetOpaqueCubeListener.class;
		}
	}
}
