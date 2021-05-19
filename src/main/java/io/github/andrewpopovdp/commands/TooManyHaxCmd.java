/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.commands;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import io.github.andrewpopovdp.DontBlock;
import io.github.andrewpopovdp.Feature;
import io.github.andrewpopovdp.command.CmdError;
import io.github.andrewpopovdp.command.CmdException;
import io.github.andrewpopovdp.command.CmdSyntaxError;
import io.github.andrewpopovdp.command.Command;
import io.github.andrewpopovdp.hack.Hack;
import io.github.andrewpopovdp.hacks.TooManyHaxHack;
import io.github.andrewpopovdp.other_feature.OtherFeature;
import io.github.andrewpopovdp.util.ChatUtils;
import io.github.andrewpopovdp.util.MathUtils;
import io.github.andrewpopovdp.util.json.JsonException;

@DontBlock
public final class TooManyHaxCmd extends Command
{
	public TooManyHaxCmd()
	{
		super("toomanyhax",
			"Allows to manage which hacks should be blocked\n"
				+ "when TooManyHax is enabled.",
			".toomanyhax block <feature>", ".toomanyhax unblock <feature>",
			".toomanyhax block-all", ".toomanyhax unblock-all",
			".toomanyhax list [<page>]", ".toomanyhax load-profile <file>",
			".toomanyhax save-profile <file>",
			".toomanyhax list-profiles [<page>]",
			"Profiles are saved in '.minecraft/wurst/toomanyhax'.");
	}
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length < 1)
			throw new CmdSyntaxError();
		
		switch(args[0].toLowerCase())
		{
			case "block":
			block(args);
			break;
			
			case "unblock":
			unblock(args);
			break;
			
			case "block-all":
			blockAll();
			break;
			
			case "unblock-all":
			unblockAll();
			break;
			
			case "list":
			list(args);
			break;
			
			case "load-profile":
			loadProfile(args);
			break;
			
			case "save-profile":
			saveProfile(args);
			break;
			
			default:
			throw new CmdSyntaxError();
		}
	}
	
	private void block(String[] args) throws CmdException
	{
		if(args.length != 2)
			throw new CmdSyntaxError();
		
		String name = args[1];
		Feature feature = parseFeature(name);
		String typeAndName = getType(feature) + " '" + name + "'";
		
		if(!feature.isSafeToBlock())
			throw new CmdError("The " + typeAndName + " is not safe to block.");
		
		TooManyHaxHack tooManyHax = CMD.getHax().tooManyHaxHack;
		if(tooManyHax.isBlocked(feature))
		{
			ChatUtils.error("The " + typeAndName + " is already blocked.");
			
			if(!tooManyHax.isEnabled())
				ChatUtils.message("Enable TooManyHax to see the effect.");
			
			return;
		}
		
		tooManyHax.setBlocked(feature, true);
		ChatUtils.message("Added " + typeAndName + " to TooManyHax list.");
	}
	
	private void unblock(String[] args) throws CmdException
	{
		if(args.length != 2)
			throw new CmdSyntaxError();
		
		String name = args[1];
		Feature feature = parseFeature(name);
		String typeAndName = getType(feature) + " '" + name + "'";
		
		TooManyHaxHack tooManyHax = CMD.getHax().tooManyHaxHack;
		if(!tooManyHax.isBlocked(feature))
			throw new CmdError("The " + typeAndName + " is not blocked.");
		
		tooManyHax.setBlocked(feature, false);
		ChatUtils.message("Removed " + typeAndName + " from TooManyHax list.");
	}
	
	private void blockAll()
	{
		CMD.getHax().tooManyHaxHack.blockAll();
		ChatUtils.message("All* features blocked.");
		ChatUtils
			.message("*Note: A few features cannot be blocked because they");
		ChatUtils.message("are required for Wurst to work properly.");
	}
	
	private void unblockAll()
	{
		CMD.getHax().tooManyHaxHack.unblockAll();
		ChatUtils.message("All features unblocked.");
	}
	
	private Feature parseFeature(String name) throws CmdSyntaxError
	{
		Feature feature = CMD.getFeatureByName(name);
		if(feature == null)
			throw new CmdSyntaxError(
				"A feature named '" + name + "' could not be found");
		
		return feature;
	}
	
	private String getType(Feature feature)
	{
		if(feature instanceof Hack)
			return "hack";
		
		if(feature instanceof Command)
			return "command";
		
		if(feature instanceof OtherFeature)
			return "feature";
		
		throw new IllegalStateException();
	}
	
	private void list(String[] args) throws CmdException
	{
		if(args.length > 2)
			throw new CmdSyntaxError();
		
		TooManyHaxHack tooManyHax = CMD.getHax().tooManyHaxHack;
		List<Feature> blocked = tooManyHax.getBlockedFeatures();
		int page = parsePage(args);
		int pages = (int)Math.ceil(blocked.size() / 8.0);
		pages = Math.max(pages, 1);
		
		if(page > pages || page < 1)
			throw new CmdSyntaxError("Invalid page: " + page);
		
		String total = "Total: " + blocked.size() + " blocked feature";
		total += blocked.size() != 1 ? "s" : "";
		ChatUtils.message(total);
		
		int start = (page - 1) * 8;
		int end = Math.min(page * 8, blocked.size());
		
		ChatUtils.message("TooManyHax list (page " + page + "/" + pages + ")");
		for(int i = start; i < end; i++)
			ChatUtils.message(blocked.get(i).getName());
	}
	
	private int parsePage(String[] args) throws CmdSyntaxError
	{
		if(args.length < 2)
			return 1;
		
		if(!MathUtils.isInteger(args[1]))
			throw new CmdSyntaxError("Not a number: " + args[1]);
		
		return Integer.parseInt(args[1]);
	}
	
	private void loadProfile(String[] args) throws CmdException
	{
		if(args.length != 2)
			throw new CmdSyntaxError();
		
		String name = parseFileName(args[1]);
		
		try
		{
			CMD.getHax().tooManyHaxHack.loadProfile(name);
			ChatUtils.message("TooManyHax profile loaded: " + name);
			
		}catch(NoSuchFileException e)
		{
			throw new CmdError("Profile '" + name + "' doesn't exist.");
			
		}catch(JsonException e)
		{
			e.printStackTrace();
			throw new CmdError(
				"Profile '" + name + "' is corrupted: " + e.getMessage());
			
		}catch(IOException e)
		{
			e.printStackTrace();
			throw new CmdError("Couldn't load profile: " + e.getMessage());
		}
	}
	
	private void saveProfile(String[] args) throws CmdException
	{
		if(args.length != 2)
			throw new CmdSyntaxError();
		
		String name = parseFileName(args[1]);
		
		try
		{
			CMD.getHax().tooManyHaxHack.saveProfile(name);
			ChatUtils.message("TooManyHax profile saved: " + name);
			
		}catch(IOException | JsonException e)
		{
			e.printStackTrace();
			throw new CmdError("Couldn't save profile: " + e.getMessage());
		}
	}
	
	private String parseFileName(String input)
	{
		String fileName = input;
		if(!fileName.endsWith(".json"))
			fileName += ".json";
		
		return fileName;
	}
}
