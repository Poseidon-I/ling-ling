package admin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.*;
import java.util.Objects;

public class LevelSettings {
    public LevelSettings(GuildMessageReceivedEvent e, char prefix) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        String[] serverSettings = new String[3];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt"));
            serverSettings = reader.readLine().split(" ");
            reader.close();
        } catch (Exception exception) {
            e.getChannel().sendMessage("Leveling has not ever been enabled on this server!  To enable it, run `" + prefix + "serversettings leveling true`").queue();
        }
        if (serverSettings[2].equals("true")) {
            String[] serverXpSettings;
            String serverBlacklist;
            try {
                switch (message[1]) {
                    case "min" -> {
                        try {
                            Integer.parseInt(message[2]);
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("You cannot enter a value that is not an integer, doesn't make sence to add word XP, does it?").queue();
                            throw new IllegalArgumentException();
                        }
                        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt"));
                        serverXpSettings = reader.readLine().split(" ");
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt")));
                        writer.print(message[2] + " " + serverXpSettings[1] + " " + serverXpSettings[2]);
                        writer.close();
                        e.getChannel().sendMessage("Set the minimum XP amount to " + message[2]).queue();
                    }
                    case "max" -> {
                        if(message.length >= 3) {
                            try {
                                Integer.parseInt(message[2]);
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You cannot enter a value that is not an integer, doesn't make sence to add word XP, does it?").queue();
                                throw new IllegalArgumentException();
                            }
                            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt"));
                            serverXpSettings = reader.readLine().split(" ");
                            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt")));
                            writer.print(serverXpSettings[0] + " " + message[2] + " " + serverXpSettings[2]);
                            writer.close();
                            e.getChannel().sendMessage("Set the maximum XP amount to " + message[2]).queue();
                        } else {
                            e.getChannel().sendMessage("You cannot set a value to nothing, that would be quite illogical.").queue();
                        }
                    }
                    case "cooldown" -> {
                        if(message.length >= 3) {
                            try {
                                Integer.parseInt(message[2]);
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You cannot enter a value that is not an integer, doesn't make sence to add word seconds, does it?").queue();
                                throw new IllegalArgumentException();
                            }
                            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt"));
                            serverXpSettings = reader.readLine().split(" ");
                            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt")));
                            writer.print(serverXpSettings[0] + " " + serverXpSettings[1] + " " + message[2]);
                            writer.close();
                            e.getChannel().sendMessage("Set the cooldown to " + message[2] + " seconds.").queue();
                        } else {
                            e.getChannel().sendMessage("You cannot set a value to nothing, that would be quite illogical.").queue();
                        }
                    }
                    case "blacklist" -> {
                        try {
                            switch (message[2]) {
                                case "add" -> {
                                    if (message.length >= 4) {
                                        String channel;
                                        try {
                                            channel = e.getMessage().getMentionedChannels().get(0).getId();
                                        } catch (Exception exception) {
                                            try {
                                                channel = Objects.requireNonNull(e.getGuild().getTextChannelById(message[3])).getId();
                                            } catch (Exception exception1) {
                                                e.getChannel().sendMessage("You can't blacklist something that isn't a text channel, how smol brane are you?").queue();
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt"));
                                        serverBlacklist = reader.readLine() + " ";
                                        if (serverBlacklist.contains(channel)) {
                                            e.getChannel().sendMessage("You already blacklisted this channel, you don't need to blacklist it again.").queue();
                                        } else {
                                            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt")));
                                            writer.print(serverBlacklist + channel);
                                            writer.close();
                                            e.getChannel().sendMessage("Successfully blacklisted <#" + channel + ">").queue();
                                        }
                                    } else {
                                        e.getChannel().sendMessage("You cannot blacklist no channels, that doesn't make sense.").queue();
                                    }
                                }
                                case "remove" -> {
                                    if (message.length >= 4) {
                                        String channel;
                                        try {
                                            channel = e.getMessage().getMentionedChannels().get(0).getId();
                                        } catch (Exception exception) {
                                            try {
                                                channel = Objects.requireNonNull(e.getGuild().getTextChannelById(message[3])).getId();
                                            } catch (Exception exception1) {
                                                e.getChannel().sendMessage("You can't remove something that isn't a text channel, how smol brane are you?").queue();
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt"));
                                        serverBlacklist = reader.readLine();
                                        if (serverBlacklist.contains(channel)) {
                                            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt")));
                                            String[] before = serverBlacklist.split(" ");
                                            if(!before[0].equals(channel) && !before[0].equals("null")) {
                                                writer.print(before[0]);
                                            }
                                            for (int i = 1; i < before.length; i++) {
                                                if (before[i].equals(channel)) {
                                                    i++;
                                                    continue;
                                                }
                                                writer.print(" " + before[i]);
                                            }
                                            writer.close();
                                            e.getChannel().sendMessage("Successfully removed <#" + channel + "> from the blacklist.").queue();
                                        } else {
                                            e.getChannel().sendMessage("This channel isn't on the blacklist, you don't need to remove it again.").queue();
                                        }
                                    } else {
                                        e.getChannel().sendMessage("You cannot remove no channels, that doesn't make sense.").queue();
                                        throw new IllegalArgumentException();
                                    }
                                }
                                case "clear" -> {
                                    File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt");
                                    file.delete();
                                    file.createNewFile();
                                    e.getChannel().sendMessage("Successfully cleared the channel blacklists on this server.").queue();
                                }
                            }
                        } catch(Exception exception) {
                            e.getChannel().sendMessage("You must either add or remove a channel.").queue();
                        }
                    }
                }
            } catch (Exception exception) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt"));
                    serverXpSettings = reader.readLine().split(" ");
                    reader.close();
                } catch(Exception exception1) {
                    e.getChannel().sendMessage("Something went wrong when creating the file, please turn leveling off and back on.  Your data will not be lost.").queue();
                    throw new IllegalArgumentException();
                }
                EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setFooter("Ling Ling Bot", e.getJDA().getSelfUser().getAvatarUrl())
                        .setTitle("Leveling Settings for " + e.getGuild().getName())
                        .addField("**XP Settings**", "Minimum: " + serverXpSettings[0] + "\nMaximum: " + serverXpSettings[1] + "\nCooldown: " + serverXpSettings[2] + " seconds", false);
                StringBuilder temp = new StringBuilder();
                String[] blacklist = new String[1];
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt"));
                    try {
                        blacklist = reader.readLine().split(" ");
                    } catch (Exception exception1) {
                        blacklist[0] = "None";
                    }
                    reader.close();
                } catch(Exception exception1) {
                    e.getChannel().sendMessage("Something went wrong when creating the file, please turn leveling off and back on.  Your data will not be lost.").queue();
                    throw new IllegalArgumentException();
                }
                if(blacklist[0].equals("None")) {
                    temp.append("None");
                } else {
                    for (String s : blacklist) {
                        if(s != null) {
                            temp.append("<#").append(s).append("> ");
                        }
                    }
                }
                try {
                    builder.addField("**Blacklisted Channels**", temp.toString(), false);
                } catch(Exception exception1) {
                    builder.addField("**Blacklisted Channels**", "The list cannot be viewed because there are too many blacklisted channels.", false);
                }
                e.getChannel().sendMessage(builder.build()).queue();
            }
        } else {
            e.getChannel().sendMessage("You cannot change Leveling settings without enabling Leveling.").queue();
        }
    }
}
