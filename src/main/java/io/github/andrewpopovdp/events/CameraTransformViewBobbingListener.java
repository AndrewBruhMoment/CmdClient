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

public interface CameraTransformViewBobbingListener extends Listener
{
	public void onCameraTransformViewBobbing(
		CameraTransformViewBobbingEvent event);
	
	public static class CameraTransformViewBobbingEvent
		extends CancellableEvent<CameraTransformViewBobbingListener>
	{
		@Override
		public void fire(
			ArrayList<CameraTransformViewBobbingListener> listeners)
		{
			for(CameraTransformViewBobbingListener listener : listeners)
			{
				listener.onCameraTransformViewBobbing(this);
				
				if(isCancelled())
					break;
			}
		}
		
		@Override
		public Class<CameraTransformViewBobbingListener> getListenerType()
		{
			return CameraTransformViewBobbingListener.class;
		}
	}
}
