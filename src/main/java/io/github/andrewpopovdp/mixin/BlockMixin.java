/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.andrewpopovdp.commands.SlideCmd;
import io.github.andrewpopovdp.event.EventManager;
import io.github.andrewpopovdp.events.ShouldDrawSideListener.ShouldDrawSideEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

@Mixin(Block.class)
public abstract class BlockMixin implements ItemConvertible
{
	@Inject(at = {@At("HEAD")},
		method = {
			"shouldDrawSide(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)Z"},
		cancellable = true)
	private static void onShouldDrawSide(BlockState state, BlockView blockView,
		BlockPos blockPos, Direction side, CallbackInfoReturnable<Boolean> cir)
	{
		ShouldDrawSideEvent event = new ShouldDrawSideEvent(state);
		EventManager.fire(event);
		
		if(event.isRendered() != null)
			cir.setReturnValue(event.isRendered());
	}
	
	@Inject(at = {@At("HEAD")},
		method = {"getVelocityMultiplier()F"},
		cancellable = true)
	private void onGetVelocityMultiplier(CallbackInfoReturnable<Float> cir)
	{
		if(cir.getReturnValueF() < 1)
			cir.setReturnValue(1F);
		
		return;
	}
	
	@Inject(at = {@At("HEAD")},
		method = {"getSlipperiness()F"},
		cancellable = true)
	public float getSlipperiness(CallbackInfoReturnable<Float> cir)
	{
		if(SlideCmd.SlipperinessOverwrite == null)
			SlideCmd.SlipperinessOverwrite = 0.6F;
		
		if(cir.getReturnValueF() <= 0.6)
			cir.setReturnValue(SlideCmd.SlipperinessOverwrite);
		
		return SlideCmd.SlipperinessOverwrite;
	}
}
