package regular;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Emojify {
    public Emojify(GuildMessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
        StringBuilder convert = new StringBuilder("> ");
        if (message.length == 1) {
            e.getChannel().sendMessage("how are we going to emojify nothing dum dum").queue();
        }
        for (int i = 1; i < message.length; i++) {
            if (i + 1 == message.length) {
                convert.append(message[i]);
            } else {
                convert.append(message[i]).append(" ");
            }
        }
        convert = new StringBuilder(convert.toString().toLowerCase());
        StringBuilder send = new StringBuilder();
        for (int i = 2; i < convert.length(); i++) {
            char cur = convert.charAt(i);
            if (cur == ' ') {
                send.append("<:linglingclock:747499551451250730> ");
            } else if (cur == '1') {
                send.append(":one: ");
            } else if (cur == '2') {
                send.append(":two: ");
            } else if (cur == '3') {
                send.append(":three: ");
            } else if (cur == '4') {
                send.append(":four: ");
            } else if (cur == '5') {
                send.append(":five: ");
            } else if (cur == '6') {
                send.append(":six: ");
            } else if (cur == '7') {
                send.append(":seven: ");
            } else if (cur == '8') {
                send.append(":eight: ");
            } else if (cur == '9') {
                send.append(":nine: ");
            } else if (cur == '0') {
                send.append(":zero: ");
            } else if (cur == '?') {
                send.append(":grey_question: ");
            } else if (cur == '!') {
                send.append(":grey_exclamation: ");
            } else {
                send.append(":regional_indicator_").append(cur).append(": ");
            }
        }
        send.append("\n<@").append(e.getAuthor().getId()).append(">");
        try {
            e.getChannel().sendMessage(send.toString()).queue();
        } catch (Exception exception) {
            e.getChannel().sendMessage("Your message ended up being over 2000 characters, try shortening it.").queue();
        }

    }
}
