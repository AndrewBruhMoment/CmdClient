/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.mixin;

import org.spongepowered.asm.mixin.Mixin;

import io.github.andrewpopovdp.CmdClient;
import io.github.andrewpopovdp.hack.HackList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import io.github.andrewpopovdp.CmdClient;
import io.github.andrewpopovdp.commands.JesusCmd;

@Mixin(FluidBlock.class)
public abstract class FluidBlockMixin extends Block implements FluidDrainable
{
	private FluidBlockMixin(CmdClient wurst, Settings block$Settings_1)
	{
		super(block$Settings_1);
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState blockState_1,
		BlockView blockView_1, BlockPos blockPos_1,
		ShapeContext entityContext_1)
	{
		if(JesusCmd.JesusOn == null)
			JesusCmd.JesusOn = "false";
		
		if(JesusCmd.JesusOn == "true")
			return VoxelShapes.fullCube();
		
		if(JesusCmd.JesusOn == "part")
			return VoxelShapes.empty();
		
		return super.getCollisionShape(blockState_1, blockView_1, blockPos_1,
			entityContext_1);
	}
}
