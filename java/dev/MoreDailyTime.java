package dev;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class MoreDailyTime {
	public MoreDailyTime(GuildMessageReceivedEvent e) {
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
		e.getChannel().sendMessage("Gave everyone +1 Day to run `!daily`").queue();
	}
}