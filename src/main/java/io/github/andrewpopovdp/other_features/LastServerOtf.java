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

@SearchTags({"last server"})
@DontBlock
public final class LastServerOtf extends OtherFeature
{
	public LastServerOtf()
	{
		super("LastServer",
			"Wurst adds a \"Last Server\" button to the server selection\n"
				+ "screen that automatically brings you back to the last\n"
				+ "server you played on.\n\n"
				+ "Useful when you get kicked and/or have a lot of servers.");
	}
}
