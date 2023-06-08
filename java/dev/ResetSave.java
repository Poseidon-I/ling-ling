package dev;

import eventListeners.GenericDiscordEvent;

import java.io.File;
import java.util.Objects;

public class ResetSave {
	public static void resetSave(GenericDiscordEvent e, String idToModerate, String reason) {
		try {
			if(idToModerate.equals("")) {
				throw new IllegalArgumentException();
			}
			Long.parseLong(idToModerate);
		} catch(Exception exception) {
			e.reply("You didn't provide an ID!");
			return;
		}
		
		File file = new File("Ling Ling Bot Data\\Economy Data\\" + idToModerate + ".json");
		if(file.exists()) {
			if(idToModerate.equals(e.getAuthor().getId())) {
				e.reply("Imagine trying to reset your own save, how dumb are you???");
			} else if(idToModerate.equals("619989388109152256") || idToModerate.equals("488487157372157962")) {
				e.reply("Imagine trying to reset the save of a developer smh");
			} else {
				String name;
				try {
					name = Objects.requireNonNull(e.getJDA().getUserById(idToModerate)).getName();
				} catch(Exception exception) {
					name = "Someone";
				}
				file.delete();
				LogCase.logCase(e, "Save Reset", idToModerate, reason);
				try {
					Objects.requireNonNull(e.getJDA().getUserById(idToModerate)).openPrivateChannel().queue((channel) -> channel.sendMessage("Your save was reset.  Reason: " + reason + "\nContinuation of this action may result in a bot ban.").queue());
				} catch(Exception exception) {
					// nothing here lol
				}
				e.reply(":wastebasket: " + name + "'s save was successfully reset!");
			}
		} else {
			e.reply("This save doesn't even exist, idiot.");
		}
	}
}