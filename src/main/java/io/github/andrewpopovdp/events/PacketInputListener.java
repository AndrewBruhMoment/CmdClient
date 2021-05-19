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
import net.minecraft.network.Packet;

public interface PacketInputListener extends Listener
{
	public void onReceivedPacket(PacketInputEvent event);
	
	public static class PacketInputEvent
		extends CancellableEvent<PacketInputListener>
	{
		private final Packet<?> packet;
		
		public PacketInputEvent(Packet<?> packet)
		{
			this.packet = packet;
		}
		
		public Packet<?> getPacket()
		{
			return packet;
		}
		
		@Override
		public void fire(ArrayList<PacketInputListener> listeners)
		{
			for(PacketInputListener listener : listeners)
			{
				listener.onReceivedPacket(this);
				
				if(isCancelled())
					break;
			}
		}
		
		@Override
		public Class<PacketInputListener> getListenerType()
		{
			return PacketInputListener.class;
		}
	}
}
