package dev;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class Warn {
	public Warn(GuildMessageReceivedEvent e) {
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
		if(id.equals(e.getAuthor().getId())) {
			e.getChannel().sendMessage("Imagine trying to warn yourself, how dumb are you???").queue();
		} else if(id.equals("619989388109152256") || id.equals("488487157372157962")) {
			e.getChannel().sendMessage("Imagine trying to warn a developer smh").queue();
		} else {
			e.getChannel().sendMessage(":warning: <@" + id + "> was successfully warned!").queue();
			Objects.requireNonNull(e.getJDA().getUserById(id)).
					openPrivateChannel().complete().sendMessage("You have received a warning.  Reason: " + reason + "\nContinuation of this action may result in a save file reset and/or a bot ban.").queue();
			new LogCase(e, "Warn", id, reason.toString());
		}
	}
}