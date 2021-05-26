package dev;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.File;
import java.util.Objects;

public class Unban {
    public Unban(GuildMessageReceivedEvent e) {
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
                e.getChannel().sendMessage("You tried to unblacklist a non-existant user.  You should know better smh.").queue();
            }
        }
        assert user != null;
        File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt");
        file.delete();
        e.getChannel().sendMessage(":white_check_mark: " + user.getName() + " was successfully unbanned!").queue();
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                .setTitle("__**Unban Info**__");
        Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("709632179340312597")).getTextChannelById("800613646380040233")).sendMessage(builder.build()).queue();
    }
}