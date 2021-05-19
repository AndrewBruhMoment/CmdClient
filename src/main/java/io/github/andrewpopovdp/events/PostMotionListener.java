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

public interface PostMotionListener extends Listener
{
	public void onPostMotion();
	
	public static class PostMotionEvent extends Event<PostMotionListener>
	{
		public static final PostMotionEvent INSTANCE = new PostMotionEvent();
		
		@Override
		public void fire(ArrayList<PostMotionListener> listeners)
		{
			for(PostMotionListener listener : listeners)
				listener.onPostMotion();
		}
		
		@Override
		public Class<PostMotionListener> getListenerType()
		{
			return PostMotionListener.class;
		}
	}
}
