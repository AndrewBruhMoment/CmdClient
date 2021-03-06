/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.other_features;

import io.github.andrewpopovdp.DontBlock;
import io.github.andrewpopovdp.SearchTags;
import io.github.andrewpopovdp.other_feature.OtherFeature;

@SearchTags({"turn off", "hide wurst logo", "ghost mode", "stealth mode",
	"vanilla Minecraft"})
@DontBlock
public final class DisableOtf extends OtherFeature
{
	public DisableOtf()
	{
		super("Disable Wurst",
			"To disable Wurst, go to the Statistics screen and press the \"Disable Wurst\" button.\n"
				+ "It will turn into an \"Enable Wurst\" button once pressed.");
	}
}
