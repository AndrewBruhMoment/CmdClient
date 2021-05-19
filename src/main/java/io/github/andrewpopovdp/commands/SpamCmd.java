/*
 * Copyright (c) 2021 Andrew Popov and contributors.
 *
 *Original Creator - Wurst-Imperium
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package io.github.andrewpopovdp.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.github.andrewpopovdp.SearchTags;
import io.github.andrewpopovdp.command.CmdException;
import io.github.andrewpopovdp.command.CmdSyntaxError;
import io.github.andrewpopovdp.command.Command;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

@SearchTags({ "spammer", "spam", "text variation", "repeat", "chat" })
public final class SpamCmd extends Command {
	public SpamCmd() {
		super("spam",
				"Spams the given chat message while adding randomized charachters."
						+ "<length> represents the times <message> is to be spammed."
						+ "<delay> represents the time between <message> sending in milliseconds. (1000ms = 1s)"
						+ "<replace> represents the amount of characters replaced in <message>. Format as a percentage with 2 numbers. (00% - 99 With or without percent)"
						+ "<message> represents the message you want to send. <message> can have spaces in it.",
				".spam <length> <delay> <replace> <message>");
	}

	static Map<String, String> map = new HashMap<String, String>();
	{
		// Mapping for Character Replacement
		// Upper Case Mapping

		map.put("A", "Ã");
		map.put("B", "Β");
		map.put("C", "Ç");
		map.put("D", "�?");
		map.put("E", "Ë");
		map.put("F", "f");
		map.put("G", "Ĝ");
		map.put("H", "Ĥ");
		map.put("I", "Ì");
		map.put("J", "Ĵ");
		map.put("K", "Ķ");
		map.put("L", "Ĺ");
		map.put("M", "m");
		map.put("N", "Ń");
		map.put("O", "Ô");
		map.put("P", "p");
		map.put("Q", "q");
		map.put("R", "Ř");
		map.put("S", "Ś");
		map.put("T", "Ţ");
		map.put("U", "Ù");
		map.put("V", "Λ");
		map.put("W", "Ŵ");
		map.put("X", "×");
		map.put("Y", "�?");
		map.put("Z", "Ž");

		// Lower Case Mapping

		map.put("a", "ä");
		map.put("b", "Β");
		map.put("c", "ç");
		map.put("d", "đ");
		map.put("e", "ê");
		map.put("f", "F");
		map.put("g", "ğ");
		map.put("h", "ĥ");
		map.put("i", "ï");
		map.put("j", "ĵ");
		map.put("k", "ķ");
		map.put("l", "ĺ");
		map.put("m", "M");
		map.put("n", "ń");
		map.put("o", "ö");
		map.put("p", "P");
		map.put("q", "Q");
		map.put("r", "ŕ");
		map.put("s", "Ś");
		map.put("t", "†");
		map.put("u", "ü");
		map.put("v", "ν");
		map.put("w", "ŵ");
		map.put("x", "X");
		map.put("y", "ỳ");
		map.put("z", "ž");

		// Number Mapping

		map.put("1", "¹");
		map.put("2", "²");
		map.put("3", "³");
		map.put("4", "�?�");
		map.put("5", "�?�");
		map.put("6", "�?�");
		map.put("7", "�?�");
		map.put("8", "�?�");
		map.put("9", "�?�");
		map.put("0", "�?�");

		// Symbol Mapping

		map.put("?", "¿");
		map.put("!", "¡");
		map.put("&", "⅋");
		map.put(".", "˙");
		map.put(" ", "_");
		map.put("_", " ");
		// Going To Add More Later
	}

	@Override
	public void call(String[] args) throws CmdException {

		// Warning for less than 3 arguments

		if (args.length < 4)
			throw new CmdSyntaxError("There should be 4 Arguments - Do .help spam for more info");

		// Warning for more than 3 arguments

		String toChange1 = "";

		// Convert all arguments after the first 3 arguments into 1 string for conversion.

		if (args.length > 3) {
			StringBuilder message = new StringBuilder();

			// Combine all arguments after i (3)

			for (int i = 3; i < args.length; i++) {
				message.append(" ").append(args[i]);
			}

			toChange1 = message.toString();
		}

		// Warning for incorrect input of Length

		if (!isInteger(args[0]))
			throw new CmdSyntaxError("First Argument is Length - Should be an Intiger");

		// Warning for incorrect input of Delay

		if (!isInteger(args[1]))
			throw new CmdSyntaxError("Second Argument is Delay - Should be an Intiger");

		// Remove percent sign from chanceInputString1.

		String chanceInputString1 = args[2];

		if (chanceInputString1.contains("%")) {
			chanceInputString1 = chanceInputString1.replace("%", "");
		}

		if (!(chanceInputString1.length() == 2))
			throw new CmdSyntaxError("Use two numbers to represent your Chance - 00 to 99 Supported.");
		// Warning for incorrect input of Chance

		if (!isInteger(chanceInputString1))
			throw new CmdSyntaxError("Third Argument is Chance - Should be an Intiger");

		// Convert chanceInputString1 to chanceInputString if requirements met.

		String chanceInputString = chanceInputString1;

		// Make ChanceInputString into an int

		int chanceInput = Integer.parseInt(chanceInputString);

		// Make compatible with Math.Random()

		double chance = ((double) chanceInput) / 100;

		// Apply repeatLength and repeatDelay from args input

		int repeatLenght = Integer.parseInt(args[0]);
		int repeatDelay = Integer.parseInt(args[1]);

		final String toChange = toChange1;

		// Starts the program in a new tread

		Thread thread = new Thread() {
			public void run() {
				System.out.println("Spam Thread Running");

				// Repeat for how many times as defined in int repeatLength

				for (int j = 0; j < repeatLenght; j++) {

					// Temporarily changes string temp to string toChange for each repetition

					String temp = toChange;

					// Repeats for each character

					for (int i = 0; i < temp.length(); i++) {

						// Gets a random value and checks if it is below a probability to get a %
						// chance.

						if ((Math.random() < chance)) {

							// Set string temp to string temp after a character has gone through the
							// function findMapping()

							try {
								temp = temp.replace(temp.charAt(i), findMapping(temp.charAt(i)));
							} catch (Exception e) {
								// Do Nothing because above has a 100% probability of happening
								e.printStackTrace();
							}
						}
					}

					// Sets string message to string temp

					String message = String.join(" ", temp);

					// Prepares a network packet for Minecraft containing string message

					ChatMessageC2SPacket packet = new ChatMessageC2SPacket(message);

					// Sends the packet with the message to the game server

					MC.getNetworkHandler().sendPacket(packet);

					// Delays the loop that repeats the message by an amount specified in int
					// releatDelay

					try {
						TimeUnit.MILLISECONDS.sleep(repeatDelay);
					} catch (InterruptedException e) {
						// Do Nothing because above has a 100% probability of happening.
						e.printStackTrace();
					}
				}
			}
		};

		thread.start();

	}

	// Function findMapping()

	private static char findMapping(char c) throws Exception {

		// Repeats for every set of mappings

		for (Map.Entry<String, String> entry : map.entrySet()) {

			// Checks if a mapping key matches with the character

			if (entry.getKey().equals(Character.toString(c))) {

				// Returns the value from the mapping for replacement

				return entry.getValue().charAt(0);
			}
		}

		// Returns the original character if there is no mapping for the character

		return c;
	}

	// Function isInteger(), used to see if <length> and <delay> inputs are correct
	// to see if a string is an integer

	private static boolean isInteger(String s) {

		try {
			Integer.parseInt(s);

			// Detects if it is not an int

		} catch (NumberFormatException e) {
			return false;

			// Detects if it is an int

		} catch (NullPointerException e) {
			return false;
		}
		// Only got here if we didn't return false
		return true;
	}

}