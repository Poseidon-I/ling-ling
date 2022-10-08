package dev;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Warn {
	public static void warn(@NotNull SlashCommandInteractionEvent e) {
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
		
		try {
			Long.parseLong(id);
		} catch(Exception exception) {
			e.reply("You didn't provide a valid ID!").queue();
			return;
		}
		if(id.equals(e.getUser().getId())) {
			e.reply("Imagine trying to warn yourself, how dumb are you???").queue();
		} else if(id.equals("619989388109152256") || id.equals("488487157372157962")) {
			e.reply("Imagine trying to warn a developer smh").queue();
		} else {
			String user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
			LogCase.logCase(e, "Warn", id, reason);
			e.reply(":warning: " + user + " was successfully warned!").queue();
			String finalReason = reason;
			Objects.requireNonNull(e.getJDA().getUserById(id)).
					openPrivateChannel().queue((channel) -> channel.sendMessage("You have received a warning.  Reason: " + finalReason + "\nContinuation of this action may result in a save file reset and/or a bot ban.").queue());
		}
	}
}