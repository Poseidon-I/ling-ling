package regular;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Emojify {
	public Emojify(MessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		StringBuilder convert = new StringBuilder("> ");
		if(message.length == 1) {
			e.getMessage().reply("how are we going to emojify nothing dum dum").mentionRepliedUser(false).queue();
		}
		for(int i = 1; i < message.length; i++) {
			if(i + 1 == message.length) {
				convert.append(message[i]);
			} else {
				convert.append(message[i]).append(" ");
			}
		}
		convert = new StringBuilder(convert.toString().toLowerCase());
		StringBuilder send = new StringBuilder();
		for(int i = 2; i < convert.length(); i++) {
			char current= convert.charAt(i);
			if(current== ' ') {
				send.append("<:linglingclock:747499551451250730> ");
			} else if(current== '1') {
				send.append(":one: ");
			} else if(current== '2') {
				send.append(":two: ");
			} else if(current== '3') {
				send.append(":three: ");
			} else if(current== '4') {
				send.append(":four: ");
			} else if(current== '5') {
				send.append(":five: ");
			} else if(current== '6') {
				send.append(":six: ");
			} else if(current== '7') {
				send.append(":seven: ");
			} else if(current== '8') {
				send.append(":eight: ");
			} else if(current== '9') {
				send.append(":nine: ");
			} else if(current== '0') {
				send.append(":zero: ");
			} else if(current== '?') {
				send.append(":grey_question: ");
			} else if(current== '!') {
				send.append(":grey_exclamation: ");
			} else {
				send.append(":regional_indicator_").append(current).append(": ");
			}
		}
		send.append("\n<@").append(e.getAuthor().getId()).append(">");
		try {
			e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
			e.getChannel().sendMessage(send.toString()).queue();
		} catch(Exception exception) {
			e.getMessage().reply("Your message ended up being too long, try shortening it.").mentionRepliedUser(false).queue();
		}
		
	}
}
