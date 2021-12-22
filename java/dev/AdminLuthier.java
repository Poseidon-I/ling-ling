package dev;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import static processes.Chance.LuthierChance;

public class AdminLuthier {
	public AdminLuthier(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		String id = e.getGuild().getId();
		try {
			switch(message[1]) {
				case "setup" -> {
					File file = new File("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json");
					if(file.exists()) {
						e.getChannel().sendMessage("This server already has Luthier!").queue();
					} else {
							int serverMembers = e.getGuild().getMemberCount();
							JSONObject data = new JSONObject();
							data.put("channel", e.getChannel().getId());
							data.put("multiplier", 1);
							data.put("chance", LuthierChance(serverMembers));
							data.put("hasWord", false);
							data.put("word", "blank");
							data.put("amount", 0);
							file.createNewFile();
						try (FileWriter writer = new FileWriter(file.getAbsolutePath())) {
							writer.write(data.toJSONString());
							writer.close();
							e.getChannel().sendMessage("Successfully set up Luthier for " + e.getGuild().getName() + " in " + e.getChannel().getAsMention()).queue();
						} catch(Exception exception2) {
							e.getChannel().sendMessage("Something went horribly wrong!").queue();
						}
					}
				}
				case "edit" -> {
					try {
						JSONParser parser = new JSONParser();
						JSONObject data;
						try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json")) {
							data = (JSONObject) parser.parse(reader);
							reader.close();
						} catch(Exception exception) {
							e.getChannel().sendMessage("This server does not have Luthier!").queue();
							throw new IllegalArgumentException();
						}
						switch(message[2]) {
							case "channel" -> {
								data.replace("channel", message[3]);
								FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json");
								writer.write(data.toJSONString());
								writer.close();
							}
							case "word" -> {
								String word = message[3].toLowerCase();
								if(word.equals("blank")) {
									data.replace("hasWord", false);
									data.replace("word", "blank");
								} else {
									data.replace("hasWord", true);
									data.replace("word", word);
								}
								FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json");
								writer.write(data.toJSONString());
								writer.close();
							}
							case "multiplier" -> {
								long newValue;
								try {
									newValue = (long) data.get("multiplier") + Long.parseLong(message[3]);
								} catch(Exception exception) {
									e.getChannel().sendMessage("You didn't provide a valid integer.  Dumbass.").queue();
									throw new IllegalArgumentException();
								}
								data.replace("multiplier", newValue);
								FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json");
								writer.write(data.toJSONString());
								writer.close();
								e.getChannel().sendMessage("Successfully changed the multipler for " + e.getGuild().getName() + " by " + message[3] + "x.  New multiplier: " + newValue).queue();
							}
							default -> e.getChannel().sendMessage("You can only edit the `channel`, `multiplier`, and `word`.").queue();
						}
					} catch(Exception exception) {
						e.getChannel().sendMessage("You need to provide an option, what are you, stupid?").queue();
					}
				}
				default -> e.getChannel().sendMessage("You can only `setup` and `edit` luthier.").queue();
			}
		} catch(Exception exception) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception1) {
				e.getChannel().sendMessage("Luthier has not been set up!").queue();
				throw new IllegalArgumentException();
			}
			e.getChannel().sendMessage("**Luthier for " + e.getGuild().getName() + ":**\n" +
					"Channel: <#" + data.get("channel") + ">\n" +
					"Chance: " + data.get("chance") + "\n" +
					"Multiplier: " + data.get("multiplier") + "\n" +
					"Word: " + data.get("word") + "\n" +
					"Amount: " + data.get("amount") + "\n").queue();
		}
	}
}