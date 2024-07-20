package regular;

import eventListeners.GenericDiscordEvent;

import java.util.Objects;
// BEETHOVEN-ONLY CLASS
public class RequestGiveaway {
	public static void requestGiveaway(GenericDiscordEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		if(message.length < 3) {
			e.reply("You have to tell me WHAT to give away, HOW LONG you want your giveaway to last, and HOW MANY WINNERS you want for your giveaway.  " +
					"Prize you put in this command will be split amongst the winners.");
			return;
		}
		StringBuilder send = new StringBuilder();
		for(int i = 2; i < message.length; i ++) {
			send.append(message[i]).append(' ');
		}
		send.deleteCharAt(send.length() - 1);
		Objects.requireNonNull(e.getGuild().getTextChannelById("734697497921978379"))
				.sendMessage("<@&734697390556184606> New Giveaway Request from <@" + e.getAuthor().getId() + ">\n" + send).queue();
	}
}