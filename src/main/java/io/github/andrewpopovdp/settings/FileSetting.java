/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import io.github.andrewpopovdp.CmdClient;
import io.github.andrewpopovdp.clickgui.Component;
import io.github.andrewpopovdp.util.json.JsonException;
import io.github.andrewpopovdp.util.json.JsonUtils;

public abstract class FileSetting extends Setting
{
	private final Path folder;
	private String selectedFile = "";
	private final Consumer<Path> createDefaultFiles;
	
	public FileSetting(String name, String description, String folderName,
		Consumer<Path> createDefaultFiles)
	{
		super(name, description);
		folder = CmdClient.INSTANCE.getCmdFolder().resolve(folderName);
		this.createDefaultFiles = createDefaultFiles;
		setSelectedFileToDefault();
	}
	
	public Path getFolder()
	{
		return folder;
	}
	
	public String getSelectedFileName()
	{
		return selectedFile;
	}
	
	public Path getSelectedFile()
	{
		return folder.resolve(selectedFile);
	}
	
	public void setSelectedFile(String selectedFile)
	{
		Objects.requireNonNull(selectedFile);
		
		Path newSelectedFile = folder.resolve(selectedFile);
		if(!Files.exists(newSelectedFile))
			return;
		
		this.selectedFile = selectedFile;
		CmdClient.INSTANCE.saveSettings();
	}
	
	private void setSelectedFileToDefault()
	{
		ArrayList<Path> files = listFiles();
		
		if(files.isEmpty())
			files = createDefaultFiles();
		
		selectedFile = "" + files.get(0).getFileName();
	}
	
	private ArrayList<Path> createDefaultFiles()
	{
		try
		{
			Files.createDirectories(folder);
			
		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
		createDefaultFiles.accept(folder);
		
		ArrayList<Path> files = listFiles();
		if(files.isEmpty())
			throw new IllegalStateException(
				"Created default files but folder is still empty!");
		
		return files;
	}
	
	public void resetFolder()
	{
		for(Path path : listFiles())
			try
			{
				Files.delete(path);
				
			}catch(IOException e)
			{
				throw new RuntimeException(e);
			}
		
		setSelectedFileToDefault();
		CmdClient.INSTANCE.saveSettings();
	}
	
	public ArrayList<Path> listFiles()
	{
		if(!Files.isDirectory(folder))
			return new ArrayList<>();
		
		try(Stream<Path> files = Files.list(folder))
		{
			return files.filter(Files::isRegularFile)
				.collect(Collectors.toCollection(() -> new ArrayList<>()));
			
		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public void fromJson(JsonElement json)
	{
		try
		{
			String newFile = JsonUtils.getAsString(json);
			
			if(newFile.isEmpty() || !Files.exists(folder.resolve(newFile)))
				throw new JsonException();
			
			selectedFile = newFile;
			
		}catch(JsonException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public JsonElement toJson()
	{
		return new JsonPrimitive(selectedFile);
	}
}
