package regular;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Poll {
	public Poll(MessageReceivedEvent e) {
		String fullMessage = e.getMessage().getContentRaw();
		int i = 7;
		StringBuilder send = new StringBuilder("**POLL:  ");
		try {
			while(fullMessage.charAt(i) != '"' && fullMessage.charAt(i) != '“' && fullMessage.charAt(i) != '”') {
				send.append(fullMessage.charAt(i));
				i++;
			}
		} catch(Exception exception) {
			e.getMessage().reply("You need to end your title with a `\"`, or you did not properly start your title with `\"`").mentionRepliedUser(false).queue();
			return;
		}
		send.append("**\n`A:` ");
		i += 3;
		int options = 1;
		char character = 'A';
		try {
			while(fullMessage.charAt(i) != '"' && fullMessage.charAt(i) != '“' && fullMessage.charAt(i) != '”') {
				if(fullMessage.charAt(i) == ';') {
					i++;
					options++;
					character++;
					send.append("\n`").append(character).append(":` ");
				} else {
					send.append(fullMessage.charAt(i));
					i++;
				}
			}
		} catch(Exception exception) {
			e.getMessage().reply("You need to end your options portion with a `\"`, or you did not properly start your options portion with `\"`").mentionRepliedUser(false).queue();
			return;
		}
		send.append("\nPoll created by ").append(e.getAuthor().getName()).append("#").append(e.getAuthor().getDiscriminator());
		Message message = null;
		if(options > 20) {
			e.getMessage().reply("Please limit your polls to 20 options or less.").mentionRepliedUser(false).queue();
			return;
		} else {
			e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
			message = e.getChannel().sendMessage(send.toString()).complete();
		}
		int hex = 127462;
		for(int j = 0; j < options; j++) {
			String unicode = "U+" + Integer.toHexString(hex);
			message.addReaction(unicode).queue();
			hex++;
		}
		
	}
}
