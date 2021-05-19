/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.lwjgl.glfw.GLFW;

import io.github.andrewpopovdp.altmanager.AltManager;
import io.github.andrewpopovdp.command.CmdList;
import io.github.andrewpopovdp.command.CmdProcessor;
import io.github.andrewpopovdp.command.Command;
import io.github.andrewpopovdp.event.EventManager;
import io.github.andrewpopovdp.events.ChatOutputListener;
import io.github.andrewpopovdp.events.PostMotionListener;
import io.github.andrewpopovdp.events.PreMotionListener;
import io.github.andrewpopovdp.hack.Hack;
import io.github.andrewpopovdp.hack.HackList;
import io.github.andrewpopovdp.mixinterface.IMinecraftClient;
import io.github.andrewpopovdp.other_feature.OtfList;
import io.github.andrewpopovdp.other_feature.OtherFeature;
import io.github.andrewpopovdp.settings.SettingsFile;
import io.github.andrewpopovdp.util.json.JsonException;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Util;

public enum CmdClient
{
	INSTANCE;
	
	public static final MinecraftClient MC = MinecraftClient.getInstance();
	public static final IMinecraftClient IMC = (IMinecraftClient)MC;
	
	public static final String VERSION = "7.13";
	public static final String MC_VERSION = "1.16.5";
	
	private EventManager eventManager;
	private AltManager altManager;
	private HackList hax;
	private CmdList cmds;
	private OtfList otfs;
	private SettingsFile settingsFile;
	private Path settingsProfileFolder;
	private CmdProcessor cmdProcessor;
	private RotationFaker rotationFaker;
	
	private boolean enabled = true;
	private static boolean guiInitialized;
	private Path cmdFolder;
	
	private KeyBinding zoomKey;
	
	public void initialize()
	{
		System.out.println("Starting Cmd Client...");
		
		cmdFolder = createCmdFolder();
		
		eventManager = new EventManager(this);
		
		Path enabledHacksFile = cmdFolder.resolve("enabled-hacks.json");
		hax = new HackList(enabledHacksFile);
		
		cmds = new CmdList();
		
		otfs = new OtfList();
		
		Path settingsFile = cmdFolder.resolve("settings.json");
		settingsProfileFolder = cmdFolder.resolve("settings");
		this.settingsFile = new SettingsFile(settingsFile, hax, cmds, otfs);
		this.settingsFile.load();
		hax.tooManyHaxHack.loadBlockedHacksFile();
		
		cmdProcessor = new CmdProcessor(cmds);
		eventManager.add(ChatOutputListener.class, cmdProcessor);
						
		rotationFaker = new RotationFaker();
		eventManager.add(PreMotionListener.class, rotationFaker);
		eventManager.add(PostMotionListener.class, rotationFaker);
		
		Path altsFile = cmdFolder.resolve("alts.encrypted_json");
		Path encFolder = createEncryptionFolder();
		altManager = new AltManager(altsFile, encFolder);
		
		zoomKey = new KeyBinding("key.wurst.zoom", InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_V, "Zoom");
		KeyBindingHelper.registerKeyBinding(zoomKey);
	}
	
	private Path createCmdFolder()
	{
		Path dotMinecraftFolder = MC.runDirectory.toPath().normalize();
		Path cmdFolder = dotMinecraftFolder.resolve("cmd");
		
		try
		{
			Files.createDirectories(cmdFolder);
			
		}catch(IOException e)
		{
			throw new RuntimeException(
				"Couldn't create .minecraft/cmd folder.", e);
		}
		
		return cmdFolder;
	}
	
	private Path createEncryptionFolder()
	{
		Path encFolder =
			Paths.get(System.getProperty("user.home"), ".Cmd encryption")
				.normalize();
		
		try
		{
			Files.createDirectories(encFolder);
			if(Util.getOperatingSystem() == Util.OperatingSystem.WINDOWS)
				Files.setAttribute(encFolder, "dos:hidden", true);
			
			Path readme = encFolder.resolve("READ ME I AM VERY IMPORTANT.txt");
			String readmeText = "DO NOT SHARE THESE FILES WITH ANYONE!\r\n"
				+ "They are encryption keys that protect your alt list file from being read by someone else.\r\n"
				+ "If someone is asking you to send these files, they are 100% trying to scam you.\r\n"
				+ "\r\n"
				+ "DO NOT EDIT, RENAME OR DELETE THESE FILES! (unless you know what you're doing)\r\n"
				+ "If you do, Wurst's Alt Manager can no longer read your alt list and will replace it with a blank one.\r\n"
				+ "In other words, YOUR ALT LIST WILL BE DELETED.";
			Files.write(readme, readmeText.getBytes("UTF-8"),
				StandardOpenOption.CREATE);
			
		}catch(IOException e)
		{
			throw new RuntimeException(
				"Couldn't create '.Cmd encryption' folder.", e);
		}
		
		return encFolder;
	}
	
	public EventManager getEventManager()
	{
		return eventManager;
	}
	
	public void saveSettings()
	{
		settingsFile.save();
	}
	
	public ArrayList<Path> listSettingsProfiles()
	{
		if(!Files.isDirectory(settingsProfileFolder))
			return new ArrayList<>();
		
		try(Stream<Path> files = Files.list(settingsProfileFolder))
		{
			return files.filter(Files::isRegularFile)
				.collect(Collectors.toCollection(() -> new ArrayList<>()));
			
		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void loadSettingsProfile(String fileName)
		throws IOException, JsonException
	{
		settingsFile.loadProfile(settingsProfileFolder.resolve(fileName));
	}
	
	public void saveSettingsProfile(String fileName)
		throws IOException, JsonException
	{
		settingsFile.saveProfile(settingsProfileFolder.resolve(fileName));
	}
	
	public HackList getHax()
	{
		return hax;
	}
	
	public CmdList getCmds()
	{
		return cmds;
	}
	
	public OtfList getOtfs()
	{
		return otfs;
	}
	
	public Feature getFeatureByName(String name)
	{
		Hack hack = getHax().getHackByName(name);
		if(hack != null)
			return hack;
		
		Command cmd = getCmds().getCmdByName(name.substring(1));
		if(cmd != null)
			return cmd;
		
		OtherFeature otf = getOtfs().getOtfByName(name);
		if(otf != null)
			return otf;
		
		return null;
	}
	

	public CmdProcessor getCmdProcessor()
	{
		return cmdProcessor;
	}
	
	public RotationFaker getRotationFaker()
	{
		return rotationFaker;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public Path getCmdFolder()
	{
		return cmdFolder;
	}
	
	public KeyBinding getZoomKey()
	{
		return zoomKey;
	}
	
	public AltManager getAltManager()
	{
		return altManager;
	}
}
