/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.command;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.TreeMap;

import io.github.andrewpopovdp.commands.*;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

public final class CmdList
{
	public final AddAltCmd addAltCmd = new AddAltCmd();
	public final AuthorCmd authorCmd = new AuthorCmd();
	public final AutoChatCmd autoChatCmd = new AutoChatCmd();
	public final ClearCmd clearCmd = new ClearCmd();
	public final CopyItemCmd copyitemCmd = new CopyItemCmd();
	public final DababyCmd dababyCmd = new DababyCmd();
	public final DamageCmd damageCmd = new DamageCmd();
	public final DropCmd dropCmd = new DropCmd();
	public final EnchantCmd enchantCmd = new EnchantCmd();
	public final FeaturesCmd featuresCmd = new FeaturesCmd();
	public final FullbrightCmd fullbrightCmd = new FullbrightCmd();
	public final FulldarkCmd fulldarkCmd = new FulldarkCmd();
	public final GetPosCmd getPosCmd = new GetPosCmd();
	public final GiveCmd giveCmd = new GiveCmd();
	public final GmCmd gmCmd = new GmCmd();
	public final HelpCmd helpCmd = new HelpCmd();
	public final InvseeCmd invseeCmd = new InvseeCmd();
	public final JesusCmd jesusCmd = new JesusCmd();
	public final IpCmd ipCmd = new IpCmd();
	public final JumpCmd jumpCmd = new JumpCmd();
	public final LeaveCmd leaveCmd = new LeaveCmd();
	public final ModifyCmd modifyCmd = new ModifyCmd();
	public final PotionCmd potionCmd = new PotionCmd();
	public final RenameCmd renameCmd = new RenameCmd();
	public final RepairCmd repairCmd = new RepairCmd();
	public final SvCmd svCmd = new SvCmd();
	public final SayCmd sayCmd = new SayCmd();
	public final SettingsCmd settingsCmd = new SettingsCmd();
	public final ShaderCmd shaderCmd = new ShaderCmd();
	public final SlideCmd slideCmd = new SlideCmd();
	public final SpamCmd spamCmd = new SpamCmd();
	public final TacoCmd tacoCmd = new TacoCmd();
	public final TestCmd testCmd = new TestCmd();
	public final TooManyHaxCmd tooManyHaxCmd = new TooManyHaxCmd();
	public final TpCmd tpCmd = new TpCmd();
	public final VClipCmd vClipCmd = new VClipCmd();
	public final ViewNbtCmd viewNbtCmd = new ViewNbtCmd();
	
	private final TreeMap<String, Command> cmds =
		new TreeMap<>((o1, o2) -> o1.compareToIgnoreCase(o2));
	
	public CmdList()
	{
		try
		{
			for(Field field : CmdList.class.getDeclaredFields())
			{
				if(!field.getName().endsWith("Cmd"))
					continue;
				
				Command cmd = (Command)field.get(this);
				cmds.put(cmd.getName(), cmd);
			}
			
		}catch(Exception e)
		{
			String message = "Initializing Wurst commands";
			CrashReport report = CrashReport.create(e, message);
			throw new CrashException(report);
		}
	}
	
	public Command getCmdByName(String name)
	{
		return cmds.get("." + name);
	}
	
	public Collection<Command> getAllCmds()
	{
		return cmds.values();
	}
	
	public int countCmds()
	{
		return cmds.size();
	}
}
