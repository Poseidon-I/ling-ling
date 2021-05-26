package dev;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.Objects;

public class Warn {
    public Warn(GuildMessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        StringBuilder reason = new StringBuilder();
        for (int i = 2; i < message.length; i++) {
            reason.append(" ").append(message[i]);
        }
        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
        User user = null;
        try {
            user = e.getMessage().getMentionedUsers().get(0);
        } catch (Exception exception) {
            try {
                user = e.getJDA().getUserById(message[1]);
            } catch (Exception exception1) {
                e.getChannel().sendMessage("You tried to warn a non-existant user.  You should know better smh.").queue();
            }
        }
        assert user != null;
        if(user.getId().equals(e.getAuthor().getId())) {
            e.getChannel().sendMessage("Imagine trying to warn yourself, how dum are you???").queue();
        } else if(user.getId().equals("619989388109152256") && user.getId().equals("488487157372157962")) {
            e.getChannel().sendMessage("Imagine trying to warn a developer smh").queue();
        } else {
            e.getChannel().sendMessage(":warning: " + user.getName() + " was successfully warned!").queue();
            EmbedBuilder builder = new EmbedBuilder()
                    .setColor(Color.BLUE)
                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                    .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                    .setTitle("__**Bot Warning Info**__");
            Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("709632179340312597")).getTextChannelById("800613646380040233")).sendMessage(builder.build()).queue();
            user.openPrivateChannel().complete().sendMessage("You have received an official bot warning for" + reason + ".  Continuation of this action will result in a save file reset and/or a bot ban.").queue();
        }
    }
}