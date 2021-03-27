package net.wurstclient.commands;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.wurstclient.SearchTags;
import net.wurstclient.command.CmdException;
import net.wurstclient.command.CmdSyntaxError;
import net.wurstclient.command.Command;
import net.wurstclient.mixinterface.IGameRenderer;

@SearchTags({"shader", "lsd", "drugs", "secret"})
public final class ShaderCmd extends Command
{
	public ShaderCmd()
	{
		super("shader", "Toggles a Build-In Shader",
			".shader <art/bits/bumpy/creeper/desaturate/notch/invert/outline/pencil/phosphor/spider/transparency/wobble>");
	}
	
	String[] ShaderList = new String[]{"art", "bits", "bumpy", "creeper",
		"desaturate", "notch", "invert", "outline", "pencil", "phosphor",
		"spider", "transparency", "wobble"};
	Boolean FoundArg1 = false;
	Boolean Generated = false;
	String shaderName = "";
	
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length > 1)
			throw new CmdSyntaxError("Check your argument");
		
		if(args[0].equalsIgnoreCase("off") && args.length == 1)
			MC.gameRenderer.disableShader();
		
		for(int i = 0; i < ShaderList.length; i++)
			if(args[0].toString().equals(ShaderList[i].toString()))
			{
				shaderName = ShaderList[i];
				FoundArg1 = true;
				break;
			}else
			{
				if(i == ShaderList.length - 1)
				{
					System.out.println("(Debug)Action Check Failed");
					if(!args[0].equalsIgnoreCase("off"))
						throw new CmdSyntaxError(
							"The shader you want to apply does not exist. Check your spelling.");
				}else
					System.out.println("(Debug)still checking");
				
			}
		
		if(args.length == 1 && FoundArg1 == true)
		{
			if(!(MC.getCameraEntity() instanceof PlayerEntity))
			{
				return;
			}
			
			if(MC.gameRenderer.getShader() != null)
				MC.gameRenderer.disableShader();
			
			((IGameRenderer)MC.gameRenderer).loadWurstShader(
				new Identifier("shaders/post/" + shaderName + ".json"));
		}
	}
}
