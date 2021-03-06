/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.ai;

import java.util.ArrayList;

import io.github.andrewpopovdp.CmdClient;
import io.github.andrewpopovdp.mixinterface.IKeyBinding;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public abstract class PathProcessor
{
	protected static final CmdClient WURST = CmdClient.INSTANCE;
	protected static final MinecraftClient MC = CmdClient.MC;
	
	private static final KeyBinding[] CONTROLS = new KeyBinding[]{
		MC.options.keyForward, MC.options.keyBack, MC.options.keyRight,
		MC.options.keyLeft, MC.options.keyJump, MC.options.keySneak};
	
	protected final ArrayList<PathPos> path;
	protected int index;
	protected boolean done;
	protected int ticksOffPath;
	
	public PathProcessor(ArrayList<PathPos> path)
	{
		if(path.isEmpty())
			throw new IllegalStateException("There is no path!");
		
		this.path = path;
	}
	
	public abstract void process();
	
	public final int getIndex()
	{
		return index;
	}
	
	public final boolean isDone()
	{
		return done;
	}
	
	public final int getTicksOffPath()
	{
		return ticksOffPath;
	}
	
	protected final void facePosition(BlockPos pos)
	{
		WURST.getRotationFaker()
			.faceVectorClientIgnorePitch(Vec3d.ofCenter(pos));
	}
	
	public static final void lockControls()
	{
		// disable keys
		for(KeyBinding key : CONTROLS)
			key.setPressed(false);
		
		// disable sprinting
		CmdClient.MC.player.setSprinting(false);
	}
	
	public static final void releaseControls()
	{
		// reset keys
		for(KeyBinding key : CONTROLS)
			key.setPressed(((IKeyBinding)key).isActallyPressed());
	}
}
