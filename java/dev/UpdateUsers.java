package dev;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class UpdateUsers {
	public UpdateUsers(MessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		String dataType;
		try {
			dataType = message[1];
		} catch(Exception exception) {
			e.getMessage().reply("You have to specify a data type, dumbass").mentionRepliedUser(false).queue();
			return;
		}
		String name;
		try {
			name = message[2];
		} catch(Exception exception) {
			e.getMessage().reply("You have to give a name, can't just call it nothing, can you?").mentionRepliedUser(false).queue();
			return;
		}
		String value;
		try {
			value = message[3];
		} catch(Exception exception) {
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
						e.getMessage().reply("You didn't specify a valid type!  Valid types: `int` `double` `boolean` `string`").mentionRepliedUser(false).queue();
						return;
					}
				}
			} catch(Exception exception) {
				e.getMessage().reply("You didn't provide a valid value to put in the key.").mentionRepliedUser(false).queue();
				return;
			}
			try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				// nothing here lol
			}
		}
		e.getMessage().reply("Successfully added data value `" + name + "` with type `" + dataType + "` to all users.").mentionRepliedUser(false).queue();
	}
}