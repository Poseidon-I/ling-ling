package dev;

import eventListeners.GenericDiscordEvent;

import java.util.Objects;

public class Warn {
	public static void warn(GenericDiscordEvent e, String idToModerate, String reason) {
		try {
			if(idToModerate.equals("")) {
				throw new IllegalArgumentException();
			}
			Long.parseLong(idToModerate);
		} catch(Exception exception) {
			e.reply("You didn't provide an ID!");
		}

		if(idToModerate.equals(e.getAuthor().getId())) {
			e.reply("Imagine trying to warn yourself, how dumb are you???");
		} else if(idToModerate.equals("619989388109152256") || idToModerate.equals("488487157372157962")) {
			e.reply("Imagine trying to warn a developer smh");
		} else {
			String user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(idToModerate)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
			LogCase.logCase(e, "Warn", idToModerate, reason);
			try {
				Objects.requireNonNull(e.getJDA().getUserById(idToModerate)).openPrivateChannel().queue((channel) -> channel.sendMessage("You have received a warning.  Reason: " + reason + "\nContinuation of this action may result in a save file reset and/or a bot ban.").queue());
			} catch(Exception exception) {
				// nothing here lol
			}
			e.reply(":warning: " + user + " was successfully warned!");
		}
	}
}