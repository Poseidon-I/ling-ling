package processes;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.Objects;
// BEETHOVEN-ONLY CLASS

public class CheckReply {
	public static void checkReply(GenericDiscordEvent e) {
		try {
			Message repliedTo = Objects.requireNonNull(e.getMessage().getMessageReference()).getMessage();
			assert repliedTo != null;
			try {
				if(repliedTo.getAuthor().equals(e.getAuthor()) || !repliedTo.equals(e.getChannel().getHistory().getRetrievedHistory().get(1))) {
					e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
					TextChannel channel = e.getGuild().getTextChannelById("979776347729719296");
					assert channel != null;
					if(!e.getAuthor().isBot()) {
						channel.sendMessage(e.getAuthor().getAsMention() + " has made a mistake in <#836710100127318057>!").queue();
					}
				}
			} catch(Exception exception) {
				if(!repliedTo.equals(e.getChannel().getHistory().retrievePast(2).complete().get(1))) {
					e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
					TextChannel channel = e.getGuild().getTextChannelById("979776347729719296");
					assert channel != null;if(!e.getAuthor().isBot()) {
						channel.sendMessage(e.getAuthor().getAsMention() + " has made a mistake in <#836710100127318057>!").queue();
					}
				}
			}
		} catch(Exception exception) {
			e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
			TextChannel channel = e.getGuild().getTextChannelById("979776347729719296");
			assert channel != null;if(!e.getAuthor().isBot()) {
				channel.sendMessage(e.getAuthor().getAsMention() + " has made a mistake in <#836710100127318057>!").queue();
			}
		}
	}
}