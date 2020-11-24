package events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.*;

public class PublicAutomod extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String message = e.getMessage().getContentRaw();
        message = message.toLowerCase();
        String server = "";
        try {
            server = e.getGuild().getId();
        } catch(Exception exception) {
            //nothing here lol
        }
        String[] data = new String[0];
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt"));
            data = br.readLine().split(" ");
            br.close();
        } catch(Exception exception) {
            //nothing here lol
        }
        if (server.equals("670725611207262219") && !e.getAuthor().getId().equals("619989388109152256") && !e.getAuthor().getId().equals("488487157372157962") && !e.getAuthor().getId().equals("706933826193981612") && !e.getAuthor().isBot()) {
            if (message.contains("discord.gg") && !e.getChannel().getId().equals("734697525868494849") && !e.getChannel().getId().equals("734697526833315930") && !e.getChannel().getId().equals("734697495225172009") && !e.getChannel().getId().equals("741709423860514867")) {
                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                e.getChannel().sendMessage("Please go to <#734697525868494849> to post invite links!").queue();
                EmbedBuilder builder = new EmbedBuilder()
                        .setColor(Color.BLUE)
                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                        .addField("Moderator: Automod", "User: " + e.getAuthor().getName() + "\nReason: Automatic action for placing server invite in improper channel", false)
                        .setTitle("__**Automatic Warning Info**__");
                TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                textChannel.sendMessage(builder.build()).queue();
                User send = e.getAuthor();
                send.openPrivateChannel().complete().sendMessage("You were warned in " + server + " for posting unauthorized invite links!").queue();
            }
        }
        if(data[3].equals("true")) {
            if (!e.getAuthor().getId().equals("619989388109152256") && !e.getAuthor().getId().equals("488487157372157962") && !e.getAuthor().getId().equals("706933826193981612")) {
                if (message.contains("retard") || message.contains("retarbed") || message.contains("re²tarded") || message.contains("r*tard") || message.contains("r*etard")) {
                    e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                    e.getChannel().sendMessage("That word is not allowed here!").queue();
                    EmbedBuilder builder = new EmbedBuilder()
                            .setColor(Color.BLUE)
                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                            .addField("Moderator: Automod", "User: <@" + e.getAuthor().getId() + ">\nReason: Automatic action for banned word (r-word)\nMessage: " + e.getMessage().getContentRaw(), false)
                            .setTitle("__**Automatic Warning Info**__");
                    TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                    textChannel.sendMessage(builder.build()).queue();
                    User send = e.getAuthor();
                    send.openPrivateChannel().complete().sendMessage("You were warned in " + e.getGuild().getName() + " for saying the r-word!").queue();
                } else if (message.contains("nigger") || message.contains("nigg") || message.contains("nibba")) {
                    e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                    e.getChannel().sendMessage("Woah there that looks like a derogatory term to me.  **__*BAN HAMMER NOW*__**\n\n> *Good musicians judge only by quality of music and do not consider anything else.*\n~ Ling Ling").queue();
                    EmbedBuilder builder = new EmbedBuilder()
                            .setColor(Color.BLUE)
                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                            .addField("Moderator: Automod", "User: <@" + e.getAuthor().getId() + ">\nReason: Automatic action for banned word (n-word)\nMessage: " + e.getMessage().getContentRaw(), false)
                            .setTitle("__**Automatic Warning Info**__");
                    TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                    textChannel.sendMessage(builder.build()).queue();
                    User send = e.getAuthor();
                    send.openPrivateChannel().complete().sendMessage("You were warned in " + e.getGuild().getName() + " for using derogatory terms!").queue();
                } else if (message.contains("cum ") || message.contains(" cum") || message.contains(" cum ") || message.contains("mcumc") || message.contains("cumcum") || message.equals("cum")) {
                    e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                    e.getChannel().sendMessage("That word is not allowed here!").queue();
                    EmbedBuilder builder = new EmbedBuilder()
                            .setColor(Color.BLUE)
                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                            .addField("Moderator: Automod", "User: <@" + e.getAuthor().getId() + ">\nReason: Automatic action for banned word (cum)\nMessage: " + e.getMessage().getContentRaw(), false)
                            .setTitle("__**Automatic Warning Info**__");
                    TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                    textChannel.sendMessage(builder.build()).queue();
                    User send = e.getAuthor();
                    send.openPrivateChannel().complete().sendMessage("You were warned in " + e.getGuild().getName() + " for using inappropriate words!").queue();
                } else if (message.contains("cunt")) {
                    e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                    e.getChannel().sendMessage("That word is not allowed here!").queue();
                    EmbedBuilder builder = new EmbedBuilder()
                            .setColor(Color.BLUE)
                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                            .addField("Moderator: Automod", "User: <@" + e.getAuthor().getId() + ">\nReason: Automatic action for banned word (cunt)\nMessage: " + e.getMessage().getContentRaw(), false)
                            .setTitle("__**Automatic Warning Info**__");
                    TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                    textChannel.sendMessage(builder.build()).queue();
                    User send = e.getAuthor();
                    send.openPrivateChannel().complete().sendMessage("You were warned in " + e.getGuild().getName() + " for using inappropriate words!").queue();
                }
            }
        }
    }
}