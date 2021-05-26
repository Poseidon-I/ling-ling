package dev;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.File;
import java.util.Objects;

public class ResetSave {
    public ResetSave(GuildMessageReceivedEvent e) {
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
                e.getChannel().sendMessage("You tried to reset the save of a non-existant user.  You should know better smh.").queue();
            }
        }
        assert user != null;
        if(user.getId().equals(e.getAuthor().getId())) {
            e.getChannel().sendMessage("Imagine trying to reset your own save, how dumb are you???").queue();
        } else if(user.getId().equals("619989388109152256") && user.getId().equals("488487157372157962")) {
            e.getChannel().sendMessage("Imagine trying to reset the save of a developer smh").queue();
        } else {
            File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt");
            file.delete();
            e.getChannel().sendMessage(":warning: " + user.getName() + "'s save was successfully reset!").queue();
            EmbedBuilder builder = new EmbedBuilder()
                    .setColor(Color.BLUE)
                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                    .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                    .setTitle("__**Save Reset Info**__");
            Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("709632179340312597")).getTextChannelById("800613646380040233")).sendMessage(builder.build()).queue();
            user.openPrivateChannel().complete().sendMessage("Your save was reset for" + reason + ".  Continuation of this action will result in a bot ban.").queue();
        }
    }
}