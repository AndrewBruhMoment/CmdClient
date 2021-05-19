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
import net.minecraft.block.BlockState;

public interface TesselateBlockListener extends Listener
{
	public void onTesselateBlock(TesselateBlockEvent event);
	
	public static class TesselateBlockEvent
		extends CancellableEvent<TesselateBlockListener>
	{
		private final BlockState state;
		
		public TesselateBlockEvent(BlockState state)
		{
			this.state = state;
		}
		
		public BlockState getState()
		{
			return state;
		}
		
		@Override
		public void fire(ArrayList<TesselateBlockListener> listeners)
		{
			for(TesselateBlockListener listener : listeners)
			{
				listener.onTesselateBlock(this);
				
				if(isCancelled())
					break;
			}
		}
		
		@Override
		public Class<TesselateBlockListener> getListenerType()
		{
			return TesselateBlockListener.class;
		}
	}
}
