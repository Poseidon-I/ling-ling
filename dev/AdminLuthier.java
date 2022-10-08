package dev;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class AdminLuthier {
	public static void adminLuthier(@NotNull SlashCommandInteractionEvent e) {
		String id = Objects.requireNonNull(e.getGuild()).getId();
		try {
			switch(Objects.requireNonNull(e.getOption("actiontype")).getAsString()) {
				case "setup" -> {
					File file = new File("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json");
					if(file.exists()) {
						e.reply("This server already has Luthier!").queue();
					} else {
						int serverMembers = e.getGuild().getMemberCount();
						JSONObject data = new JSONObject();
						data.put("channel", e.getChannel().getId());
						data.put("multiplier", 0);
						data.put("chance", Numbers.luthierChance(serverMembers));
						data.put("hasWord", false);
						data.put("word", "blank");
						data.put("amount", 0);
						file.createNewFile();
						try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
							writer.write(data.toJSONString());
							writer.close();
							e.reply("Successfully set up Luthier for " + e.getGuild().getName() + " in " + e.getChannel().getAsMention() + ".\nLuthier Multipliers can be crafted using `/craft`").queue();
						} catch(Exception exception2) {
							e.reply("Something went horribly wrong!").queue();
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
							e.reply("This server does not have Luthier!").queue();
							return;
						}
						switch(Objects.requireNonNull(e.getOption("editoption")).getAsString()) {
							case "channel" -> {
								data.replace("channel", Objects.requireNonNull(e.getOption("newvalue")).getAsString());
								FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json");
								writer.write(data.toJSONString());
								writer.close();
								e.reply("Successfully changed the channel for " + e.getGuild().getName() + " to <#" + Objects.requireNonNull(Objects.requireNonNull(e.getOption("newvalue")).getAsString()) + ">").queue();
							}
							case "word" -> {
								String word = Objects.requireNonNull(e.getOption("newvalue")).getAsString().toLowerCase();
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
								e.reply("Successfully changed the word for " + e.getGuild().getName() + " to `" + word + "`").queue();
							}
							case "multiplier" -> {
								long newValue;
								try {
									newValue = (long) data.get("multiplier") + Long.parseLong(Objects.requireNonNull(e.getOption("newvalue")).getAsString());
								} catch(Exception exception) {
									e.reply("You didn't provide a valid integer.  Dumbass.").setEphemeral(true).queue();
									throw new IllegalArgumentException();
								}
								data.replace("multiplier", newValue);
								FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json");
								writer.write(data.toJSONString());
								writer.close();
								e.reply("Successfully changed the multipler for " + e.getGuild().getName() + " by " + Objects.requireNonNull(e.getOption("newvalue")) + "x.  New multiplier: " + newValue).queue();
							}
							default -> e.reply("You can only edit the `channel`, `multiplier`, and `word`.").setEphemeral(true).queue();
						}
					} catch(Exception exception) {
						e.reply("You need to provide an option, what are you, stupid?").setEphemeral(true).queue();
					}
				}
				default -> e.reply("You can only `setup` and `edit` luthier.").setEphemeral(true).queue();
			}
		} catch(Exception exception) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception1) {
				e.reply("Luthier has not been set up!").queue();
				return;
			}
			e.reply("**Luthier for " + e.getGuild().getName() + ":**\n" +
					"Channel: <#" + data.get("channel") + ">\n" +
					"Chance: " + data.get("chance") + "\n" +
					"Multiplier: " + data.get("multiplier") + "\n" +
					"Word: " + data.get("word") + "\n" +
					"Amount: " + data.get("amount") + "\n").queue();
		}
	}
}