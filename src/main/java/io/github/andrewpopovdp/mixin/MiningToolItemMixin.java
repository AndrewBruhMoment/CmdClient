/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.google.common.collect.Multimap;

import io.github.andrewpopovdp.CmdClient;
import io.github.andrewpopovdp.mixinterface.IMiningToolItem;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin extends ToolItem implements IMiningToolItem
{
	@Shadow
	@Final
	protected float attackDamage;
	
	@Shadow
	@Final
	private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
	
	private MiningToolItemMixin(CmdClient wurst, ToolMaterial material,
		Settings settings)
	{
		super(material, settings);
	}
	
	@Override
	public float fuckMcAfee1()
	{
		return attackDamage;
	}
	
	@Override
	public float fuckMcAfee2()
	{
		return (float)attributeModifiers
			.get(EntityAttributes.GENERIC_ATTACK_SPEED).iterator().next()
			.getValue();
	}
}
