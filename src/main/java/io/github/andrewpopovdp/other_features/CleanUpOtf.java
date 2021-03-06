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

@SearchTags({"Clean Up"})
@DontBlock
public final class CleanUpOtf extends OtherFeature
{
	public CleanUpOtf()
	{
		super("CleanUp",
			"Cleans up your server list.\n"
				+ "To use it, press the 'Clean Up' button\n"
				+ "on the server selection screen.");
	}
}
