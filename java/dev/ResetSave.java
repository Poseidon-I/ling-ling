package dev;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.File;
import java.util.Objects;

public class ResetSave {
	public ResetSave(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		StringBuilder reason = new StringBuilder();
		for(int i = 2; i < message.length; i++) {
			reason.append(" ").append(message[i]);
		}
		if(reason.isEmpty()) {
			reason.append("None");
		}
		e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
		String id = message[1];
		try {
			Long.parseLong(id);
		} catch(Exception exception) {
			e.getChannel().sendMessage("You didn't provide a valid ID!").queue();
			throw new IllegalArgumentException();
		}
		File file = new File("Ling Ling Bot Data\\Economy Data\\" + id + ".json");
		if(file.exists()) {
			if(id.equals(e.getAuthor().getId())) {
				e.getChannel().sendMessage("Imagine trying to reset your own save, how dumb are you???").queue();
			} else if(id.equals("619989388109152256") || id.equals("488487157372157962")) {
				e.getChannel().sendMessage("Imagine trying to reset the save of a developer smh").queue();
			} else {
				file.delete();
				e.getChannel().sendMessage(":wastebasket: <@" + id + ">'s save was successfully reset!").queue();
				Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().complete().sendMessage("Your save was reset.  Reason: " + reason + "\nContinuation of this action may result in a bot ban.").queue();
				new LogCase(e, "Save Reset", id, reason.toString());
			}
		} else {
			e.getChannel().sendMessage("This save doesn't even exist, idiot.").queue();
		}
	}
}