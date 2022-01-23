package dev;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Unban {
	public Unban(MessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		String id = message[1];
		try {
			Long.parseLong(id);
		} catch(Exception exception) {
			e.getMessage().reply("You didn't provide a valid ID!").mentionRepliedUser(false).queue();
			return;
		}
		StringBuilder reason = new StringBuilder();
		for(int i = 3; i < message.length; i++) {
			reason.append(" ").append(message[i]);
		}
		if(reason.isEmpty()) {
			reason.append("None");
		}
		JSONParser parser = new JSONParser();
		JSONObject data;
		File file = new File("Ling Ling Bot Data\\Economy Data\\" + id + ".json");
		try(FileReader reader = new FileReader(file.getAbsolutePath())) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.getMessage().reply("File doesn't exist!  Looks like they never used the bot to begin with...").mentionRepliedUser(false).queue();
			return;
		}
		if((Boolean) data.get("banned")) {
			boolean resetSave;
			try {
				resetSave = Boolean.parseBoolean(message[2]);
			} catch(Exception exception) {
				e.getMessage().reply("You have to tell me whether you want to reset the save or not, dumb.").mentionRepliedUser(false).queue();
				return;
			}
			String user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
			e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
			e.getChannel().sendMessage(":white_check_mark: " + user + " was successfully unbanned!").queue();
			new LogCase(e, "Unban", id, reason.toString());
			if(resetSave) {
				file.delete();
				reason.append("\nSave Reset: Yes");
				Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().queue((channel) -> channel.sendMessage("You have been unbanned :fireworks:\nYour save was reset.").queue());
			} else {
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + id + ".json")) {
					data = (JSONObject) parser.parse(reader);
					reader.close();
					data.replace("banned", false);
					
					FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + id + ".json");
					writer.write(data.toJSONString());
					writer.close();
				} catch(Exception exception) {
					//nothing here lol
				}
				reason.append("\nSave Reset: No");
				Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().queue((channel) -> channel.sendMessage("You have been unbanned :fireworks:\nYour save was not reset.").queue());
			}
		} else {
			e.getMessage().reply("That user isn't even banned, stupid.").mentionRepliedUser(false).queue();
		}
	}
}