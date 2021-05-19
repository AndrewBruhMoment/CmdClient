/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.other_features;

import io.github.andrewpopovdp.CmdClient;
import io.github.andrewpopovdp.DontBlock;
import io.github.andrewpopovdp.SearchTags;
import io.github.andrewpopovdp.other_feature.OtherFeature;
import io.github.andrewpopovdp.update.Version;
import net.minecraft.util.Util;

@SearchTags({"change log", "wurst update", "release notes", "what's new",
	"what is new", "new features", "recently added features"})
@DontBlock
public final class ChangelogOtf extends OtherFeature
{
	public ChangelogOtf()
	{
		super("Changelog", "Opens the changelog in your browser.");
	}
	
	@Override
	public String getPrimaryAction()
	{
		return "View Changelog";
	}
	
	@Override
	public void doPrimaryAction()
	{
		String link = new Version(CmdClient.VERSION).getChangelogLink();
		Util.getOperatingSystem().open(link);
	}
}
