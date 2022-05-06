package dev;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public class Warn {
	public Warn(MessageReceivedEvent e) {
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
		if(id.equals(e.getAuthor().getId())) {
			e.getMessage().reply("Imagine trying to warn yourself, how dumb are you???").mentionRepliedUser(false).queue();
		} else if(id.equals("619989388109152256") || id.equals("488487157372157962")) {
			e.getMessage().reply("Imagine trying to warn a developer smh").mentionRepliedUser(false).queue();
		} else {
			String user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
			new LogCase(e, "Warn", id, reason.toString());
			e.getChannel().sendMessage(":warning: " + user + " was successfully warned!").queue();
			Objects.requireNonNull(e.getJDA().getUserById(id)).
					openPrivateChannel().queue((channel) -> channel.sendMessage("You have received a warning.  Reason: " + reason + "\nContinuation of this action may result in a save file reset and/or a bot ban.").queue());
			e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
		}
	}
}