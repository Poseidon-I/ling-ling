package dev;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class UpdateUsers {
	public static void updateUsers(@NotNull SlashCommandInteractionEvent e) {
		String dataType;
		try {
			dataType = Objects.requireNonNull(e.getOption("datatype")).getAsString();
		} catch(Exception exception) {
			e.reply("You have to specify a data type, dumbass").queue();
			return;
		}
		
		String name;
		try {
			name = Objects.requireNonNull(e.getOption("newkey")).getAsString();
		} catch(Exception exception) {
			e.reply("You have to give a name, can't just call it nothing, can you?").queue();
			return;
		}
		
		String value;
		try {
			value = Objects.requireNonNull(e.getOption("setvalue")).getAsString();
		} catch(Exception exception) {
			e.reply("You have to give a default value, stupid.").queue();
			return;
		}
		
		File directory = new File("Ling Ling Bot Data\\Economy Data");
		File[] files = directory.listFiles();
		assert files != null;
		for(File file : files) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader(file.getAbsolutePath())) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				continue;
			}
			try {
				switch(dataType) {
					case "boolean" -> data.put(name, Boolean.parseBoolean(value));
					case "double" -> data.put(name, Double.parseDouble(value));
					case "int" -> data.put(name, Long.parseLong(value));
					case "string" -> data.put(name, value);
					default -> {
						e.reply("You didn't specify a valid type!  Valid types: `int` `double` `boolean` `string`").queue();
						return;
					}
				}
			} catch(Exception exception) {
				e.reply("You didn't provide a valid value to put in the key.").queue();
				return;
			}
			try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				// nothing here lol
			}
		}
		e.reply("Successfully added data value `" + name + "` with type `" + dataType + "` to all users.").queue();
	}
}