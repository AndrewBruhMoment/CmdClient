/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import io.github.andrewpopovdp.CmdClient;
import io.github.andrewpopovdp.mixinterface.IArmorItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

@Mixin(ArmorItem.class)
public class ArmorItemMixin extends Item implements IArmorItem
{
	@Shadow
	protected float toughness;
	
	private ArmorItemMixin(CmdClient wurst, Settings item$Settings_1)
	{
		super(item$Settings_1);
	}
	
	@Override
	public float getToughness()
	{
		return toughness;
	}
}
