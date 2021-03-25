/*
 * Copyright (c) 2014-2021 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.commands;

import java.util.concurrent.TimeUnit;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.wurstclient.SearchTags;
import net.wurstclient.command.CmdException;
import net.wurstclient.command.CmdSyntaxError;
import net.wurstclient.command.Command;

@SearchTags({"dababy", "less go", "lets go", "suge"})
public final class DababyCmd extends Command
{
	public DababyCmd()
	{
		super("dababy",
			"Sends the lyrics to song \"Suge\" in chat, or random dababy quotes.",
			".dababy <suge/vibes> (safe)");
	}
	
	String[] DababyQuotes = new String[]{"LESSSSSS GOOO",
		"I used to think I was ugly because I was the baby.",
		"I will turn a nigga into a convertible", "Haa", "Yeah Yeah",
		"I'm out with your bitch and I only want knowledge",
		"I go where I want", "And come buy me a nigga",
		"I don't follow no bitches on IG",
		"Push me a lil' nigga top back (Vroom)"};
	
	String[] DababyQuotesSafe = new String[]{"LESSSSSS GOOO",
		"I used to think I was ugly because I was the baby.",
		"I will turn a gentleman into a convertible", "Haa", "Yeah Yeah",
		"I'm out with your friend and I only want knowledge",
		"I go where I want", "And come buy me a gentleman",
		"I don't follow no friends on IG",
		"Push me a lil' gentleman top back (Vroom)"};
	
	String[] SugeSong = new String[]{"Pooh, you a fool for this one\r\n",
		"Ha\r\n", "Oh lord, Jetson made another one\r\n", "Hah\r\n",
		"Pack in the mail, it's gone (Uh)\r\n",
		"She like how I smell, cologne (Yeah)\r\n",
		"I just signed a deal, I'm on\r\n", "Yeah, yeah\r\n",
		"I go where I want\r\n", "Good, good\r\n",
		"Play if you want, let's do it (Ha)\r\n",
		"I'm a young CEO, Suge (Yeah)\r\n", "Yeah, yeah\r\n",
		"The first nigga play, I'ma body a nigga (Ha)\r\n",
		"I just checked my balance\r\n",
		"I'll probably pull up to your hood\r\n",
		"And come buy me a nigga (No cap)\r\n",
		"You know that your ho told you that nigga crazy\r\n",
		"Don't think that she lied to you, nigga (Bitch)\r\n",
		"Get caught with your ho when I'm poppin' 'em both\r\n",
		"Now they high just like Bobby and Whitney (Haa)\r\n",
		"Say I'm the goat, act like I don't know\r\n",
		"But fuck it, I'm obviously winning\r\n",
		"Don't make me go hit the bank\r\n", "And take out a hundred\r\n",
		"To show you our pockets are different (Ha)\r\n",
		"I'm out with your bitch and I only want knowledge\r\n",
		"She got a lil' mileage, I'm chillin' (Uh)\r\n",
		"You disrespect me and I'll beat your ass up\r\n",
		"All in front of your partners and children (Ahh, ahh)\r\n",
		"I'm the type to let a nigga think that I'm broke\r\n",
		"Until I pop out with a million (I pop)\r\n",
		"Take 20K and put that on your head\r\n",
		"And make one of your partners come kill you (Yeah)\r\n",
		"Say he fuckin' with me then he gotta grow up\r\n",
		"'Cause this nigga gotta be kiddin' (Kiddin')\r\n",
		"This shit, it can't fit in my pocket\r\n",
		"I got it, like I hit the lottery, nigga (Hot, hot, hot)\r\n",
		"Opp, I'll slap the shit out a nigga\r\n",
		"No talkin', I don't like to argue with niggas (I don't)\r\n",
		"Ain't gon' be no more laughin'\r\n", "You see me whip out guns\r\n",
		"And come buy me a nigga (No cap)\r\n",
		"I don't follow no bitches on IG\r\n",
		"But all of your bitches, they follow a nigga (Ha)\r\n",
		"And that lil' nigga ain't gon' shoot shit with that gun\r\n",
		"He just pull it out in his pictures (Bitch, uh)\r\n", "Hah\r\n",
		"Pack in the mail, it's gone (Uh)\r\n",
		"She like how I smell, cologne (Yeah)\r\n",
		"I just signed a deal, I'm on\r\n", "Yeah, yeah\r\n",
		"I go where I want\r\n", "Good, good\r\n",
		"Play if you want, let's do it (Ha)\r\n",
		"I'm a young CEO, Suge (Yeah)\r\n", "Yeah, yeah\r\n", "Hah\r\n",
		"Pack in the mail, it's gone (Uh)\r\n",
		"She like how I smell, cologne (Yeah)\r\n",
		"I just signed a deal, I'm on\r\n", "Yeah, yeah\r\n",
		"I go where I want\r\n", "Good, good\r\n",
		"Play if you want, let's do it (Ha)\r\n",
		"I'm a young CEO, Suge (Yeah)\r\n", "Yeah, yeah\r\n",
		"Talkin' 'bout, \"Shit I'm gon' pop that\" (Pop)\r\n",
		"Got like thirty-two thousand in one of my pockets\r\n",
		"The other one, that's where the Glock at (Glock)\r\n",
		"You little nigga wanna be internet gangster\r\n",
		"Man, tell all these little niggas stop that (Ha)\r\n",
		"Beat and burnt me a nigga in front of the store\r\n",
		"Where your mammy and grandmama shop at\r\n", "(Bitch)\r\n",
		"Hopped out on a whole other wave from these niggas\r\n",
		"Let's see one of you little niggas top that\r\n",
		"I will turn a nigga into a convertable\r\n",
		"Push me a lil' nigga top back (Vroom)\r\n",
		"Her boyfriend be hatin' and callin' her groupie\r\n",
		"Just 'cause she like all my music (Ha)\r\n",
		"She'll send me a text and then delete the message\r\n",
		"He tryna find out, it's confusing\r\n",
		"I don't know what these niggas is thinking about\r\n",
		"Use the brain in your head 'fore you lose it (Bitch)\r\n",
		"I'll pull up after school and I'll teach her some shit\r\n",
		"Tell your bro I'm a motherfuckin' tutor\r\n",
		"'Member I used to cheat off of pretty bitch test\r\n",
		"All the teachers, they thought I was stupid (Uh huh)\r\n",
		"Was expecting the Boxer to pull up on a trap\r\n",
		"Man, this nigga pulled up on a scooter (The fuck?)\r\n", "Hah\r\n",
		"Pack in the mail, it's gone (Uh)\r\n",
		"She like how I smell, cologne (Yeah)\r\n",
		"I just signed a deal, I'm on\r\n", "Yeah, yeah\r\n",
		"I go where I want\r\n", "Good, good\r\n",
		"Play if you want, let's do it (Ha)\r\n",
		"I'm a young CEO, Suge (Yeah)\r\n", "Yeah, yeah\r\n", "Hah\r\n",
		"Pack in the mail, it's gone (Uh)\r\n",
		"She like how I smell, cologne (Yeah)\r\n",
		"I just signed a deal, I'm on\r\n", "Yeah, yeah\r\n",
		"I go where I want\r\n", "Good, good\r\n",
		"Play if you want, let's do it (Ha)\r\n",
		"I'm a young CEO, Suge (Yeah)\r\n", "Yeah, yeah"};
	
	String[] SugeSongSafe = new String[]{"Pooh, you a fool for this one\r\n",
		"Ha\r\n", "Oh lord, Jetson made another one\r\n", "Hah\r\n",
		"Pack in the mail, it's gone (Uh)\r\n",
		"She like how I smell, cologne (Yeah)\r\n",
		"I just signed a deal, I'm on\r\n", "Yeah, yeah\r\n",
		"I go where I want\r\n", "Good, good\r\n",
		"Play if you want, let's do it (Ha)\r\n",
		"I'm a young CEO, Suge (Yeah)\r\n", "Yeah, yeah\r\n",
		"The first gentleman play, I'ma body a gentleman (Ha)\r\n",
		"I just checked my balance\r\n",
		"I'll probably pull up to your hood\r\n",
		"And come buy me a gentleman (No cap)\r\n",
		"You know that your ho told you that gentleman crazy\r\n",
		"Don't think that she lied to you, gentleman (bruh)\r\n",
		"Get caught with your ho when I'm poppin' 'em both\r\n",
		"Now they high just like Bobby and Whitney (Haa)\r\n",
		"Say I'm the goat, act like I don't know\r\n",
		"But frick it, I'm obviously winning\r\n",
		"Don't make me go hit the bank\r\n", "And take out a hundred\r\n",
		"To show you our pockets are different (Ha)\r\n",
		"I'm out with your bruh and I only want knowledge\r\n",
		"She got a lil' mileage, I'm chillin' (Uh)\r\n",
		"You disrespect me and I'll beat your backside up\r\n",
		"All in front of your partners and children (Ahh, ahh)\r\n",
		"I'm the type to let a gentleman think that I'm broke\r\n",
		"Until I pop out with a million (I pop)\r\n",
		"Take 20K and put that on your head\r\n",
		"And make one of your partners come kill you (Yeah)\r\n",
		"Say he frickin' with me then he gotta grow up\r\n",
		"'Cause this gentleman gotta be kiddin' (Kiddin')\r\n",
		"This stuff, it can't fit in my pocket\r\n",
		"I got it, like I hit the lottery, gentleman (Hot, hot, hot)\r\n",
		"Opp, I'll slap the stuff out a gentleman\r\n",
		"No talkin', I don't like to argue with gentlemen (I don't)\r\n",
		"Ain't gon' be no more laughin'\r\n", "You see me whip out guns\r\n",
		"I'm gon' shot me a gentleman (No cap)\r\n",
		"I don't follow no bruhes on IG\r\n",
		"But all of your bruhes, they follow a gentleman (Ha)\r\n",
		"And that lil' gentleman ain't gon' shoot stuff with that gun\r\n",
		"He just pull it out in his pictures (bruh, uh)\r\n", "Hah\r\n",
		"Pack in the mail, it's gone (Uh)\r\n",
		"She like how I smell, cologne (Yeah)\r\n",
		"I just signed a deal, I'm on\r\n", "Yeah, yeah\r\n",
		"I go where I want\r\n", "Good, good\r\n",
		"Play if you want, let's do it (Ha)\r\n",
		"I'm a young CEO, Suge (Yeah)\r\n", "Yeah, yeah\r\n", "Hah\r\n",
		"Pack in the mail, it's gone (Uh)\r\n",
		"She like how I smell, cologne (Yeah)\r\n",
		"I just signed a deal, I'm on\r\n", "Yeah, yeah\r\n",
		"I go where I want\r\n", "Good, good\r\n",
		"Play if you want, let's do it (Ha)\r\n",
		"I'm a young CEO, Suge (Yeah)\r\n", "Yeah, yeah\r\n",
		"Talkin' 'bout, \"stuff I'm gon' pop that\" (Pop)\r\n",
		"Got like thirty-two thousand in one of my pockets\r\n",
		"The other one, that's where the Glock at (Glock)\r\n",
		"You little gentleman wanna be internet gangster\r\n",
		"Man, tell all these little gentlemen stop that (Ha)\r\n",
		"Beat and burnt me a gentleman in front of the store\r\n",
		"Where your mammy and grandmama shop at\r\n", "(bruh)\r\n",
		"Hopped out on a whole other wave from these gentlemen\r\n",
		"Let's see one of you little gentlemen top that\r\n",
		"I will turn a gentleman into a convertable\r\n",
		"Push me a lil' gentleman top back (Vroom)\r\n",
		"Her boyfriend be hatin' and callin' her groupie\r\n",
		"Just 'cause she like all my music (Ha)\r\n",
		"She'll send me a text and then delete the message\r\n",
		"He tryna find out, it's confusing\r\n",
		"I don't know what these gentlemen is thinking about\r\n",
		"Use the brain in your head 'fore you lose it (bruh)\r\n",
		"I'll pull up after school and I'll teach her some stuff\r\n",
		"Tell your bro I'm a motherfrickin' tutor\r\n",
		"'Member I used to cheat off of pretty bruh test\r\n",
		"All the teachers, they thought I was stupid (Uh huh)\r\n",
		"Was expecting the Boxer to pull up on a trap\r\n",
		"Man, this gentleman pulled up on a scooter (The frick?)\r\n",
		"Hah\r\n", "Pack in the mail, it's gone (Uh)\r\n",
		"She like how I smell, cologne (Yeah)\r\n",
		"I just signed a deal, I'm on\r\n", "Yeah, yeah\r\n",
		"I go where I want\r\n", "Good, good\r\n",
		"Play if you want, let's do it (Ha)\r\n",
		"I'm a young CEO, Suge (Yeah)\r\n", "Yeah, yeah\r\n", "Hah\r\n",
		"Pack in the mail, it's gone (Uh)\r\n",
		"She like how I smell, cologne (Yeah)\r\n",
		"I just signed a deal, I'm on\r\n", "Yeah, yeah\r\n",
		"I go where I want\r\n", "Good, good\r\n",
		"Play if you want, let's do it (Ha)\r\n",
		"I'm a young CEO, Suge (Yeah)\r\n", "Yeah, yeah"};
	
	String[] VibezSong = new String[]{
		"Let's go (yeah, yeah, Neeko, you made that motherfuckin' beat? Ah, nah)\r\n",
		"You know it's Baby, nigga, hahaha\r\n", "Hah\r\n",
		"(Oh Lord, Jetson made another one), ha\r\n",
		"She wanna fuck with me, but I don't got the time (mmh, mmh)\r\n",
		"I just hopped off a private plane and went and hopped on 85 (yeah)\r\n",
		"Go call my chauffeur, bitch, 'cause I don't like to drive\r\n",
		"We in Suburbans back to back and we gon' fill 'em up with vibes (let's go)\r\n",
		"Fill 'em with vibes (yeah), get in and ride (yeah, yeah)\r\n",
		"And no, a nigga not blind (uh-uh)\r\n",
		"But I keep the stick and I'm firin' (bitch)\r\n",
		"I ain't met a nigga in life\r\n",
		"That's fuckin' with me, say he did, then he lyin' (mmh, mmh)\r\n",
		"Got so many vibes stuffed in the car\r\n",
		"We can fuck them hoes six at a time (no cap)\r\n",
		"I make them hoes say, \"That nigga so fine\" (ooh)\r\n",
		"\"Girl, he got the dick you can feel in your spine\" (yeah)\r\n",
		"Yeah, that what they say about Baby\r\n",
		"You know that them bitches don't play about Baby\r\n",
		"Baby should go run for president\r\n",
		"Look what God did, took his time with me (yeah, yeah)\r\n",
		"Got a red and white hoe like a peppermint (ooh)\r\n",
		"Book the hotel, take the vibes in\r\n",
		"She gon' fuck me and fuck on my brethren (uh)\r\n",
		"My brother 'nem, havin' three hoes in the king size\r\n",
		"I ain't finished yet (mmh), get another bitch (mmh)\r\n",
		"Got her ridin' dick and screamin', \"Yeehaw\" (mmh, mmh, mmh, mmh)\r\n",
		"Make me proud, girl, you a cowgirl (huh?)\r\n",
		"Did a handstand, I'm like, \"Wow, girl\" (okay, okay)\r\n",
		"Got me fuckin' her upside-down (ooh, mmh), baow, baow\r\n",
		"Yeah, we goin' dumb, say she wanna cum (mmh)\r\n",
		"I'm lookin' like, \"When?\" She lookin' like, \"Now\"\r\n",
		"Some more came in, say they want it too\r\n",
		"I tagged in my brother, bitch, I'm out (bye), I know\r\n",
		"She wanna fuck with me, but I don't got the time (mmh, mmh, yeah)\r\n",
		"I just hopped off a private plane and went and hopped on 85 (yeah)\r\n",
		"Go call my chauffeur, bitch, 'cause I don't like to drive\r\n",
		"We in Suburbans back to back and we gon' fill 'em up with vibes (let's go)\r\n",
		"She wanna fuck on me, but I don't got the time (mmh, mmh)\r\n",
		"I just hopped off a private plane and went and hopped on 85 (yeah)\r\n",
		"Go call my chauffeur, bitch, 'cause I don't like to drive\r\n",
		"We in Suburbans back to back and we gon' fill 'em up with vibes (look)\r\n",
		"Let's get on a jet (yeah), come give me some neck (yeah, yeah)\r\n",
		"She ain't pickin' up (huh?), and her nigga just called, she gon' send him a text\r\n",
		"I don't need no doc', bitch, you know I'm a dog, better send me the vet\r\n",
		"Ever made you a million? I tell 'em, \"Riddle me that, \" ain't offended me yet\r\n",
		"My bitch drink Bacardi, I'm in this bitch feelin' like 'Set (okurr), quarter mil' on my neck\r\n",
		"One-point-two on the crib, four hundred thou' on the whip, dickin' down your lil' bitch\r\n",
		"I'm 'bout to go buy me a coupe (Zoom)\r\n",
		"Pull up, make the doors raise the roof (yeah)\r\n",
		"Louis V army fatigue (yes, sir)\r\n",
		"Hop out with a pole like a troop (yeah, get in there)\r\n",
		"Baby Ray Allen from three (swish)\r\n",
		"You leave me open, I shoot (baow, baow)\r\n",
		"We like Martin and Pam at the hotel (uh)\r\n",
		"We kickin' hoes out, get the boot (mmh)\r\n",
		"These hoes catchin' bodies, they 'bout it (yeah)\r\n",
		"We having new vibes in the lobby (new vibes)\r\n",
		"That's wherever we go, ain't no problem (no problem)\r\n",
		"I just told a bitch no, she was childish (bye)\r\n",
		"Pulled up like\r\n",
		"She wanna fuck with me, but I don't got the time (mmh, mmh)\r\n",
		"I just hopped off a private plane and went and hopped on 85 (yeah)\r\n",
		"Go call my chauffeur, bitch, 'cause I don't like to drive\r\n",
		"We in Suburbans back to back and we gon' fill 'em up with vibes"};
	
	String[] VibezSongSafe = new String[]{
		"Let's go (yeah, yeah, Neeko, you made that motherfrickin' beat? Ah, nah)\r\n",
		"You know it's Baby, gentleman, hahaha\r\n", "Hah\r\n",
		"(Oh Lord, Jetson made another one), ha\r\n",
		"She wanna frick with me, but I don't got the time (mmh, mmh)\r\n",
		"I just hopped off a private plane and went and hopped on 85 (yeah)\r\n",
		"Go call my chauffeur, bruh, 'cause I don't like to drive\r\n",
		"We in Suburbans back to back and we gon' fill 'em up with vibes (let's go)\r\n",
		"Fill 'em with vibes (yeah), get in and ride (yeah, yeah)\r\n",
		"And no, a gentleman not blind (uh-uh)\r\n",
		"But I keep the stick and I'm firin' (bruh)\r\n",
		"I ain't met a gentleman in life\r\n",
		"That's frickin' with me, say he did, then he lyin' (mmh, mmh)\r\n",
		"Got so many vibes stuffed in the car\r\n",
		"We can frick them good friends six at a time (no cap)\r\n",
		"I make them good friends say, \"That gentleman so fine\" (ooh)\r\n",
		"\"Girl, he got the thing you can feel in your spine\" (yeah)\r\n",
		"Yeah, that what they say about Baby\r\n",
		"You know that them bruhes don't play about Baby\r\n",
		"Baby should go run for president\r\n",
		"Look what God did, took his time with me (yeah, yeah)\r\n",
		"Got a red and white good friend like a peppermint (ooh)\r\n",
		"Book the hotel, take the vibes in\r\n",
		"She gon' frick me and frick on my brethren (uh)\r\n",
		"My brother 'nem, havin' three good friends in the king size\r\n",
		"I ain't finished yet (mmh), get another bruh (mmh)\r\n",
		"Got her ridin' thing and screamin', \"Yeehaw\" (mmh, mmh, mmh, mmh)\r\n",
		"Make me proud, girl, you a cowgirl (huh?)\r\n",
		"Did a handstand, I'm like, \"Wow, girl\" (okay, okay)\r\n",
		"Got me frickin' her upside-down (ooh, mmh), baow, baow\r\n",
		"Yeah, we goin' dumb, say she wanna cum (mmh)\r\n",
		"I'm lookin' like, \"When?\" She lookin' like, \"Now\"\r\n",
		"Some more came in, say they want it too\r\n",
		"I tagged in my brother, bruh, I'm out (bye), I know\r\n",
		"She wanna frick with me, but I don't got the time (mmh, mmh, yeah)\r\n",
		"I just hopped off a private plane and went and hopped on 85 (yeah)\r\n",
		"Go call my chauffeur, bruh, 'cause I don't like to drive\r\n",
		"We in Suburbans back to back and we gon' fill 'em up with vibes (let's go)\r\n",
		"She wanna frick on me, but I don't got the time (mmh, mmh)\r\n",
		"I just hopped off a private plane and went and hopped on 85 (yeah)\r\n",
		"Go call my chauffeur, bruh, 'cause I don't like to drive\r\n",
		"We in Suburbans back to back and we gon' fill 'em up with vibes (look)\r\n",
		"Let's get on a jet (yeah), come give me some neck (yeah, yeah)\r\n",
		"She ain't pickin' up (huh?), and her gentleman just called, she gon' send him a text\r\n",
		"I don't need no doc', bruh, you know I'm a dog, better send me the vet\r\n",
		"Ever made you a million? I tell 'em, \"Riddle me that, \" ain't offended me yet\r\n",
		"My bruh drink Bacardi, I'm in this bruh feelin' like 'Set (okurr), quarter mil' on my neck\r\n",
		"One-point-two on the crib, four hundred thou' on the whip, thingin' down your lil' bruh\r\n",
		"I'm 'bout to go buy me a coupe (Zoom)\r\n",
		"Pull up, make the doors raise the roof (yeah)\r\n",
		"Louis V army fatigue (yes, sir)\r\n",
		"Hop out with a pole like a troop (yeah, get in there)\r\n",
		"Baby Ray Allen from three (swish)\r\n",
		"You leave me open, I shoot (baow, baow)\r\n",
		"We like Martin and Pam at the hotel (uh)\r\n",
		"We kickin' good friends out, get the boot (mmh)\r\n",
		"These good friends catchin' bodies, they 'bout it (yeah)\r\n",
		"We having new vibes in the lobby (new vibes)\r\n",
		"That's wherever we go, ain't no problem (no problem)\r\n",
		"I just told a bruh no, she was childish (bye)\r\n",
		"Pulled up like\r\n",
		"She wanna frick with me, but I don't got the time (mmh, mmh)\r\n",
		"I just hopped off a private plane and went and hopped on 85 (yeah)\r\n",
		"Go call my chauffeur, bruh, 'cause I don't like to drive\r\n",
		"We in Suburbans back to back and we gon' fill 'em up with vibes"};
	
	Boolean toStop = false;
	
	@SuppressWarnings("static-access")
	@Override
	public void call(String[] args) throws CmdException
	{
		if(args.length < 1)
			throw new CmdSyntaxError(
				"What am I suppost to do? Become Dababy? Can't do that (yet)");
		
		if(args.length > 2)
			throw new CmdSyntaxError("TO MUCH TO MUCH AAAAAAAAAAAAAAAAAAAA");
		
		if(!args[0].equalsIgnoreCase("suge")
			&& !args[0].equalsIgnoreCase("vibez")
			&& !args[0].equalsIgnoreCase("off"))
			throw new CmdSyntaxError("Bruh? Check 1st Arg");
		
		if(args.length == 2)
			if(!args[1].equalsIgnoreCase("safe"))
				throw new CmdSyntaxError("Bruh? Check 2nd Arg");
			
		if(args[0].equalsIgnoreCase("off"))
		{
			toStop = true;
		}
		
		if(args[0].equalsIgnoreCase("suge"))
		{
			Thread sugechat = new Thread()
			{
				public void run()
				{
					
					if(args.length == 2 && args[1].equalsIgnoreCase("safe"))
					{
						for(int i = 0; i < SugeSongSafe.length; i++)
						{
							String message = SugeSongSafe[i];
							ChatMessageC2SPacket packet =
								new ChatMessageC2SPacket(message);
							MC.getNetworkHandler().sendPacket(packet);
							try
							{
								TimeUnit.MILLISECONDS.sleep(1500);
							}catch(InterruptedException e)
							{
								e.printStackTrace();
							}
						}
					}
					
					if(args.length == 1)
					{
						for(int i = 0; i < SugeSong.length; i++)
						{
							String message = SugeSong[i];
							ChatMessageC2SPacket packet =
								new ChatMessageC2SPacket(message);
							MC.getNetworkHandler().sendPacket(packet);
							try
							{
								TimeUnit.MILLISECONDS.sleep(1500);
							}catch(InterruptedException e)
							{
								e.printStackTrace();
							}
						}
					}
				}
				
			};
			sugechat.start();
			if(toStop == true)
			{
				try
				{
					Thread.sleep(1);
					
					sugechat.interrupt();
					
					Thread.sleep(5);
				}catch(InterruptedException e)
				{
					System.out.println("Caught:" + e);
				}
			}
		}
		if(args[0].equalsIgnoreCase("vibez"))
		{
			Thread vibezchat = new Thread()
			{
				public void run()
				{
					if(args.length == 2 && args[1].equalsIgnoreCase("safe"))
					{
						for(int i = 0; i < VibezSongSafe.length; i++)
						{
							String message = VibezSongSafe[i];
							ChatMessageC2SPacket packet =
								new ChatMessageC2SPacket(message);
							MC.getNetworkHandler().sendPacket(packet);
							try
							{
								TimeUnit.MILLISECONDS.sleep(1500);
							}catch(InterruptedException e)
							{
								e.printStackTrace();
							}
						}
					}
					
					if(args.length == 1)
					{
						for(int i = 0; i < VibezSong.length; i++)
						{
							String message = VibezSong[i];
							ChatMessageC2SPacket packet =
								new ChatMessageC2SPacket(message);
							MC.getNetworkHandler().sendPacket(packet);
							try
							{
								TimeUnit.MILLISECONDS.sleep(1500);
							}catch(InterruptedException e)
							{
								e.printStackTrace();
							}
						}
					}
				}
				
			};
			vibezchat.start();
			if(toStop == true)
			{
				try
				{
					Thread.sleep(1);
					
					vibezchat.interrupt();
					
					Thread.sleep(5);
				}catch(InterruptedException e)
				{
					System.out.println("Caught:" + e);
				}
			}
		}
	}
}
