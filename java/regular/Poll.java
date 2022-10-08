package regular;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Poll {
	public static void poll(@NotNull SlashCommandInteractionEvent e) {
		String title;
		try {
			title = Objects.requireNonNull(e.getOption("title")).getAsString();
		} catch(Exception exception) {
			title = "No Title";
		}
		StringBuilder send = new StringBuilder("**POLL: ").append(title).append("**\n");
		
		String choices;
		try {
			choices = Objects.requireNonNull(e.getOption("choices")).getAsString();
		} catch(Exception exception) {
			e.reply("You must provide choices.  Can't have people vote on nothing, you know.").queue();
			return;
		}
		String[] splitChoices = choices.split(";");
		if(splitChoices.length > 20) {
			e.reply("Please limit your polls to 20 options or less.").queue();
			return;
		}
		char currentChar = 'a';
		for(String splitChoice : splitChoices) {
			send.append(":regional_indicator_").append(currentChar).append(": ").append(splitChoice).append("\n");
			currentChar ++;
		}
		send.append("\nPoll created by ").append(e.getUser().getName()).append("#").append(e.getUser().getDiscriminator());
		Message message = e.getChannel().sendMessage(send.toString()).complete();
		e.reply("Poll created!").setEphemeral(true).queue();
		int hex = 127462;
		for(int j = 0; j < splitChoices.length; j++) {
			Emoji emoji = Emoji.fromUnicode("U+" + Integer.toHexString(hex));
			message.addReaction(emoji).queue();
			hex++;
		}
	}
}
