package dev;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MoreDailyTime {
	public MoreDailyTime(MessageReceivedEvent e) {
		File directory = new File("Ling Ling Bot Data\\Economy Data");
		File[] files = directory.listFiles();
		assert files != null;
		for(File file : files) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader(file.getAbsolutePath())) {
				data = (JSONObject) parser.parse(reader);
			} catch(Exception exception) {
				continue;
			}
			data.replace("retainDaily", true);
			try(FileWriter writer = new FileWriter(file.getAbsolutePath())) {
				writer.write(data.toJSONString());
			} catch(Exception exception) {
				//nothing here lol
			}
		}
		e.getMessage().reply("Gave everyone +1 Day to run `!daily`").mentionRepliedUser(false).queue();
	}
}