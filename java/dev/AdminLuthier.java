package dev;

import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class AdminLuthier {
	public static void adminLuthier(GenericDiscordEvent e, String mainAction, String editAction, String newValue) {
		String id = e.getGuild().getId();
		if(mainAction.equals("")) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception1) {
				e.reply("Luthier has not been set up!");
				return;
			}
			e.reply("**Luthier for " + e.getGuild().getName() + ":**\n" +
					"Channel: <#" + data.get("channel") + ">\n" +
					"Chance: " + data.get("chance") + "\n" +
					"Multiplier: " + data.get("multiplier") + "\n" +
					"Word: " + data.get("word") + "\n" +
					"Amount: " + data.get("amount") + "\n");
			return;
		}
		switch(mainAction) {
			case "setup" -> {
				File file = new File("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json");
				if(file.exists()) {
					e.reply("This server already has Luthier!");
				} else {
					int serverMembers = e.getGuild().getMemberCount();
					JSONObject data = new JSONObject();
					data.put("channel", e.getChannel().getId());
					data.put("multiplier", 0);
					data.put("chance", Numbers.luthierChance(serverMembers));
					data.put("hasWord", false);
					data.put("word", "blank");
					data.put("amount", 0);
					try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
						file.createNewFile();
						writer.write(data.toJSONString());
						writer.close();
						e.reply("Successfully set up Luthier for " + e.getGuild().getName() + " in " + e.getChannel().getAsMention() + ".\nLuthier Multipliers can be crafted using `/craft`");
					} catch(Exception exception2) {
						e.reply("Something went horribly wrong!");
					}
				}
			}
			case "edit" -> {
				if(editAction.equals("")) {
					e.reply("You need to provide an option, what are you, stupid?");
					return;
				}
				JSONParser parser = new JSONParser();
				JSONObject data;
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json")) {
					data = (JSONObject) parser.parse(reader);
					reader.close();
				} catch(Exception exception) {
					e.reply("This server does not have Luthier!");
					return;
				}
				switch(editAction) {
					case "channel" -> {
						data.replace("channel", newValue);
						try {
							FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json");
							writer.write(data.toJSONString());
							writer.close();
						} catch(Exception exception) {
							// nothing here lol
						}
						e.reply("Successfully changed the channel for " + e.getGuild().getName() + " to <#" + newValue + ">");
					}
					case "word" -> {
						String word = newValue.toLowerCase();
						if(word.equals("blank")) {
							data.replace("hasWord", false);
							data.replace("word", "blank");
						} else {
							data.replace("hasWord", true);
							data.replace("word", word);
						}
						try {
							FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json");
							writer.write(data.toJSONString());
							writer.close();
						} catch(Exception exception) {
							// nothing here lol
						}
						e.reply("Successfully changed the word for " + e.getGuild().getName() + " to `" + word + "`");
					}
					case "multiplier" -> {
						long newMulti;
						try {
							newMulti = (long) data.get("multiplier") + Long.parseLong(newValue);
						} catch(Exception exception) {
							e.reply("You didn't provide a valid integer.  Dumbass.");
							return;
						}
						data.replace("multiplier", newMulti);
						try {
							FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json");
							writer.write(data.toJSONString());
							writer.close();
						} catch(Exception exception) {
							// nothing here lol
						}
						e.reply("Successfully changed the multipler for " + e.getGuild().getName() + " by " + newValue + "x.  New multiplier: " + newMulti);
					}
					default -> e.reply("You can only edit the `channel`, `multiplier`, and `word`.");
				}
			}
			default -> e.reply("You can only `setup` and `edit` luthier.");
		}
	}
}