package net.wurstclient.commands;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.wurstclient.SearchTags;
import net.wurstclient.command.CmdException;
import net.wurstclient.command.CmdSyntaxError;
import net.wurstclient.command.Command;

@SearchTags({".legit", "dots in chat", "command bypass", "prefix"})
public final class AutoChatCmd extends Command
{
	public AutoChatCmd()
	{
		super("autoChat",
			"Sends a message with a given prompt. Not responsible to inaccurate message!",
			".autochat <toxic/excuse/compliment>");
	}
	
	String[] firstArgOptions = new String[] {"toxic","excuse","compliment","bruh"};
	String[] toxic = new String[] {"You're so bad","SO LUCKY","your literally garbage","your trash get out of my game","your SO BAD","Dogwater","Get out of my game kid","WOW! HOW CAN SOMEONE BE THIS BAD?","Your so bad im actualy amazed you need to be put in a reasearch lab.","Not even good"};
	String[] excuse = new String[] {"Im litterally lagging","so lucky","Why is my internet SO BAD???","B R U H ???","why am I playing SO BAD?","That wasn't even fair","STOP IT B R U H","How did you get that?","Im done with this game","That wasn't fair"};
	String[] compliment = new String[] {"How are you so good?","You're so good", "I'm amazed at your skill","SO IMPRESSIVE WOW","HOW ARE YOU THIS GOOD???","YOUR SO GOOD HOW ???","You're Amazing!","Good Job!","Your playing really well!","That was great!"};
	String[] bruh = new String[] {"Bruh","BRUH","B R U H","B r u h","b r u h","BrUh","B R U H ???","Bruh?","b r u h ?"," Bruh -_-"};

	
	@Override
	public void call(String[] args) throws CmdException
	{
		String toGenerate = "";
		String message = "";
		Boolean FoundArg1 = false;
		Boolean Generated = false;

		if(args.length < 1)
			throw new CmdSyntaxError("What is the purpose of existance without anything to do?");
		
		if(args.length > 1)
			throw new CmdSyntaxError("To many arguments");
		
		for(int i = 0; i < firstArgOptions.length; i++)
		{
			if(args[0].toString().equals(firstArgOptions[i].toString()))
			{
				toGenerate = firstArgOptions[i];
				FoundArg1 = true;
				break;
			}else
			{
				if(i == firstArgOptions.length - 1)
				{
					System.out.println("(Debug)Action Check Failed");
					FoundArg1 = false;
					throw new CmdSyntaxError("Something Went Severely Wrong, Try Again");
				}else
					System.out.println("(Debug)still checking");
				
			}
		}
		
		while(FoundArg1 == true && Generated != true)
		{
			if(toGenerate == "toxic")
				message = toxic[(int)(Math.random()*10)];
			
			if(toGenerate == "excuse")
				message = excuse[(int)(Math.random()*10)];
			
			if(toGenerate == "compliment")
				message = compliment[(int)(Math.random()*10)];
			
			if(toGenerate == "bruh")
				message = bruh[(int)(Math.random()*10)];
			
			Generated = true;
		}
		
		if (message != "")
		{
			ChatMessageC2SPacket packet = new ChatMessageC2SPacket(message);
			MC.getNetworkHandler().sendPacket(packet);
		} else
			throw new CmdSyntaxError("Wrong argument, make sure that the first argument valid");
		
	}
}
