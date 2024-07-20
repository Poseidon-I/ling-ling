package processes;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
// BEETHOVEN-ONLY CLASS

public class CheckCounting {
	public static void checkCounting(GenericDiscordEvent e) {
		try {
			System.out.println(e.getChannel().getHistory().getRetrievedHistory().get(0).getContentRaw());
			if(Integer.parseInt(e.getMessage().getContentRaw()) != Integer.parseInt(e.getChannel().getHistory().getRetrievedHistory().get(0).getContentRaw()) + 1) {
				e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
				TextChannel channel = e.getGuild().getTextChannelById("979776347729719296");
				assert channel != null;
				if(!e.getAuthor().isBot()) {
					channel.sendMessage(e.getAuthor().getAsMention() + " has made a mistake in <#734697521758339163>!").queue();
				}
			}
		} catch(Exception exception) {
			try {
				if(Integer.parseInt(e.getMessage().getContentRaw()) != Integer.parseInt(e.getChannel().getHistory().retrievePast(2).complete().get(1).getContentRaw()) + 1) {
					e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
					TextChannel channel = e.getGuild().getTextChannelById("979776347729719296");
					assert channel != null;
					if(!e.getAuthor().isBot()) {
						channel.sendMessage(e.getAuthor().getAsMention() + " has made a mistake in <#734697521758339163>!").queue();
					}
				}
			} catch(Exception exception1) {
				e.getChannel().deleteMessageById(e.getMessage().getId()).queue();
				TextChannel channel = e.getGuild().getTextChannelById("979776347729719296");
				assert channel != null;
				if(!e.getAuthor().isBot()) {
					channel.sendMessage(e.getAuthor().getAsMention() + " has made a mistake in <#734697521758339163>!").queue();
				}
			}
		}
	}
}