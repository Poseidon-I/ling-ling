package regular;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Poll {
    public Poll(GuildMessageReceivedEvent e) {
        String fullMessage = e.getMessage().getContentRaw();
        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
        int i = 7;
        StringBuilder send = new StringBuilder("**POLL:  ");
        try {
            while (fullMessage.charAt(i) != '"' && fullMessage.charAt(i) != '“' && fullMessage.charAt(i) != '”') {
                send.append(fullMessage.charAt(i));
                i++;
            }
        } catch (Exception exception) {
            e.getChannel().sendMessage("You need to end your title with a `\"`, or you did not properly start your title with `\"`").queue();
            throw new IllegalArgumentException();
        }
        send.append("**\n`A:` ");
        i += 3;
        int options = 1;
        char character = 'A';
        try {
            while (fullMessage.charAt(i) != '"' && fullMessage.charAt(i) != '“' && fullMessage.charAt(i) != '”') {
                if (fullMessage.charAt(i) == ';') {
                    i ++;
                    options++;
                    character++;
                    send.append("\n`").append(character).append(":` ");
                } else {
                    send.append(fullMessage.charAt(i));
                    i++;
                }
            }
        } catch (Exception exception) {
            e.getChannel().sendMessage("You need to end your options portion with a `\"`, or you did not properly start your options portion with `\"`").queue();
            throw new IllegalArgumentException();
        }
        send.append("\nPoll created by ").append(e.getAuthor().getName()).append("#").append(e.getAuthor().getDiscriminator());
        String react = "";
        if (options > 20) {
            e.getChannel().sendMessage("Please limit your polls to 20 options or less.").queue();
        } else {
            react = e.getChannel().sendMessage(send.toString()).complete().getId();
        }
        int hex = 127462;
        for (int j = 0; j < options; j++) {
            String unicode = "U+" + Integer.toHexString(hex);
            e.getChannel().addReactionById(react, unicode).queue();
            hex++;
        }

    }
}
