/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.andrewpopovdp.CmdClient;
import io.github.andrewpopovdp.event.EventManager;
import io.github.andrewpopovdp.events.LeftClickListener.LeftClickEvent;
import io.github.andrewpopovdp.events.RightClickListener.RightClickEvent;
import io.github.andrewpopovdp.mixinterface.IClientPlayerEntity;
import io.github.andrewpopovdp.mixinterface.IClientPlayerInteractionManager;
import io.github.andrewpopovdp.mixinterface.IMinecraftClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.util.Session;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.snooper.SnooperListener;
import net.minecraft.util.thread.ReentrantThreadExecutor;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin
	extends ReentrantThreadExecutor<Runnable> implements SnooperListener,
	WindowEventHandler, AutoCloseable, IMinecraftClient
{
	@Shadow
	private int itemUseCooldown;
	@Shadow
	private ClientPlayerInteractionManager interactionManager;
	@Shadow
	private ClientPlayerEntity player;
	@Shadow
	private Session session;
	
	private Session wurstSession;
	
	private MinecraftClientMixin(CmdClient wurst, String string_1)
	{
		super(string_1);
	}
	
	@Inject(at = {@At(value = "FIELD",
		target = "Lnet/minecraft/client/MinecraftClient;crosshairTarget:Lnet/minecraft/util/hit/HitResult;",
		ordinal = 0)}, method = {"doAttack()V"}, cancellable = true)
	private void onDoAttack(CallbackInfo ci)
	{
		LeftClickEvent event = new LeftClickEvent();
		EventManager.fire(event);
		
		if(event.isCancelled())
			ci.cancel();
	}
	
	@Inject(at = {@At(value = "FIELD",
		target = "Lnet/minecraft/client/MinecraftClient;itemUseCooldown:I",
		ordinal = 0)}, method = {"doItemUse()V"}, cancellable = true)
	private void onDoItemUse(CallbackInfo ci)
	{
		RightClickEvent event = new RightClickEvent();
		EventManager.fire(event);
		
		if(event.isCancelled())
			ci.cancel();
	}
	
	@Inject(at = {@At("HEAD")}, method = {"doItemPick()V"})
	private void onDoItemPick(CallbackInfo ci)
	{
		if(!CmdClient.INSTANCE.isEnabled())
			return;
		
		HitResult hitResult = CmdClient.MC.crosshairTarget;
		if(hitResult == null || hitResult.getType() != HitResult.Type.ENTITY)
			return;
		
		Entity entity = ((EntityHitResult)hitResult).getEntity();
	}
	
	@Inject(at = {@At("HEAD")},
		method = {"getSession()Lnet/minecraft/client/util/Session;"},
		cancellable = true)
	private void onGetSession(CallbackInfoReturnable<Session> cir)
	{
		if(wurstSession == null)
			return;
		
		cir.setReturnValue(wurstSession);
	}
	
	@Redirect(at = @At(value = "FIELD",
		target = "Lnet/minecraft/client/MinecraftClient;session:Lnet/minecraft/client/util/Session;",
		opcode = Opcodes.GETFIELD,
		ordinal = 0),
		method = {
			"getSessionProperties()Lcom/mojang/authlib/properties/PropertyMap;"})
	private Session getSessionForSessionProperties(MinecraftClient mc)
	{
		if(wurstSession != null)
			return wurstSession;
		else
			return session;
	}
	
	@Override
	public void rightClick()
	{
		doItemUse();
	}
	
	@Override
	public int getItemUseCooldown()
	{
		return itemUseCooldown;
	}
	
	@Override
	public void setItemUseCooldown(int itemUseCooldown)
	{
		this.itemUseCooldown = itemUseCooldown;
	}
	
	@Override
	public IClientPlayerEntity getPlayer()
	{
		return (IClientPlayerEntity)player;
	}
	
	@Override
	public IClientPlayerInteractionManager getInteractionManager()
	{
		return (IClientPlayerInteractionManager)interactionManager;
	}
	
	@Override
	public void setSession(Session session)
	{
		wurstSession = session;
	}
	
	@Shadow
	private void doItemUse()
	{
		
	}
}
