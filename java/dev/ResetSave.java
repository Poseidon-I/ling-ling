package dev;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.util.Objects;

public class ResetSave {
	public ResetSave(MessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		StringBuilder reason = new StringBuilder();
		for(int i = 2; i < message.length; i++) {
			reason.append(" ").append(message[i]);
		}
		if(reason.isEmpty()) {
			reason.append("None");
		}
		String id = message[1];
		try {
			Long.parseLong(id);
		} catch(Exception exception) {
			e.getMessage().reply("You didn't provide a valid ID!").mentionRepliedUser(false).queue();
			return;
		}
		File file = new File("Ling Ling Bot Data\\Economy Data\\" + id + ".json");
		if(file.exists()) {
			if(id.equals(e.getAuthor().getId())) {
				e.getMessage().reply("Imagine trying to reset your own save, how dumb are you???").mentionRepliedUser(false).queue();
			} else if(id.equals("619989388109152256") || id.equals("488487157372157962")) {
				e.getMessage().reply("Imagine trying to reset the save of a developer smh").mentionRepliedUser(false).queue();
			} else {
				String user;
				try {
					user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName();
				} catch(Exception exception) {
					user = "Someone";
				}
				file.delete();
				e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
				e.getChannel().sendMessage(":wastebasket: " + user + "'s save was successfully reset!").queue();
				new LogCase(e, "Save Reset", id, reason.toString());
				Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().queue((channel) -> channel.sendMessage("Your save was reset.  Reason: " + reason + "\nContinuation of this action may result in a bot ban.").queue());
			}
		} else {
			e.getMessage().reply("This save doesn't even exist, idiot.").mentionRepliedUser(false).queue();
		}
	}
}