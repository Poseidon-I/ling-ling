package dev;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

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
			name = message[1];
		} catch(Exception exception) {
			e.getMessage().reply("You have to give a name, can't just call it nothing, can you?").mentionRepliedUser(false).queue();
			return;
		}
		File directory = new File("Ling Ling Bot Data\\Economy Data");
		File[] files = directory.listFiles();
		assert files != null;
		for(File file : files) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try (FileReader reader = new FileReader(file.getAbsolutePath())) {
				data = (JSONObject) parser.parse(reader);
			} catch(Exception exception) {
				continue;
			}
			switch(dataType) {
				case "boolean" -> data.put(name, false);
				case "double" -> data.put(name, 0.0);
				case "int" -> data.put(name, 0);
			}
		}
		e.getMessage().reply("Successfully added data value `" + name + "` with type `" + dataType + "` to all users.").mentionRepliedUser(false).queue();
	}
}