/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp;

import io.github.andrewpopovdp.events.PostMotionListener;
import io.github.andrewpopovdp.events.PreMotionListener;
import io.github.andrewpopovdp.util.RotationUtils;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

public final class RotationFaker
	implements PreMotionListener, PostMotionListener
{
	private boolean fakeRotation;
	private float serverYaw;
	private float serverPitch;
	private float realYaw;
	private float realPitch;
	
	@Override
	public void onPreMotion()
	{
		if(!fakeRotation)
			return;
		
		ClientPlayerEntity player = CmdClient.MC.player;
		realYaw = player.yaw;
		realPitch = player.pitch;
		player.yaw = serverYaw;
		player.pitch = serverPitch;
	}
	
	@Override
	public void onPostMotion()
	{
		if(!fakeRotation)
			return;
		
		ClientPlayerEntity player = CmdClient.MC.player;
		player.yaw = realYaw;
		player.pitch = realPitch;
		fakeRotation = false;
	}
	
	public void faceVectorPacket(Vec3d vec)
	{
		RotationUtils.Rotation rotations =
			RotationUtils.getNeededRotations(vec);
		
		fakeRotation = true;
		serverYaw = rotations.getYaw();
		serverPitch = rotations.getPitch();
	}
	
	public void faceVectorClient(Vec3d vec)
	{
		RotationUtils.Rotation rotations =
			RotationUtils.getNeededRotations(vec);
		
		CmdClient.MC.player.yaw = rotations.getYaw();
		CmdClient.MC.player.pitch = rotations.getPitch();
	}
	
	public void faceVectorClientIgnorePitch(Vec3d vec)
	{
		RotationUtils.Rotation rotations =
			RotationUtils.getNeededRotations(vec);
		
		CmdClient.MC.player.yaw = rotations.getYaw();
		CmdClient.MC.player.pitch = 0;
	}
	
	public float getServerYaw()
	{
		return fakeRotation ? serverYaw : CmdClient.MC.player.yaw;
	}
	
	public float getServerPitch()
	{
		return fakeRotation ? serverPitch : CmdClient.MC.player.pitch;
	}
}
