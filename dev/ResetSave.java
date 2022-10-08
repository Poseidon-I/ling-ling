package dev;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class ResetSave {
	public static void resetSave(@NotNull SlashCommandInteractionEvent e) {
		String id;
		try {
			id = Objects.requireNonNull(e.getOption("user")).getAsString();
			Long.parseLong(id);
		} catch(NullPointerException exception) {
			e.reply("You didn't provide an ID!").setEphemeral(true).queue();
			return;
		} catch(Exception exception) {
			e.reply("You didn't provide a valid ID!").setEphemeral(true).queue();
			return;
		}
		
		String reason;
		try {
			reason = Objects.requireNonNull(e.getOption("reason")).getAsString();
		} catch(Exception exception) {
			reason = "None";
		}
		
		File file = new File("Ling Ling Bot Data\\Economy Data\\" + id + ".json");
		if(file.exists()) {
			if(id.equals(e.getUser().getId())) {
				e.reply("Imagine trying to reset your own save, how dumb are you???").queue();
			} else if(id.equals("619989388109152256") || id.equals("488487157372157962")) {
				e.reply("Imagine trying to reset the save of a developer smh").queue();
			} else {
				String user;
				try {
					user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName();
				} catch(Exception exception) {
					user = "Someone";
				}
				file.delete();
				LogCase.logCase(e, "Save Reset", id, reason);
				e.reply(":wastebasket: " + user + "'s save was successfully reset!").queue();
				String finalReason = reason;
				Objects.requireNonNull(e.getJDA().getUserById(id)).openPrivateChannel().queue((channel) -> channel.sendMessage("Your save was reset.  Reason: " + finalReason + "\nContinuation of this action may result in a bot ban.").queue());
			}
		} else {
			e.reply("This save doesn't even exist, idiot.").queue();
		}
	}
}