package processes;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.*;
import java.util.Objects;

public class AdminCommands {
    public AdminCommands(GuildMessageReceivedEvent e, String[] message, char prefix) {
        BufferedReader reader;
        PrintWriter writer;
        switch(message[0]) {
            case "prefix" -> {
                char newPrefix = message[1].charAt(0);
                try {
                    writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + e.getGuild().getId() + ".txt")));
                    writer.print(newPrefix);
                    writer.close();
                    e.getChannel().sendMessage("The prefix is now `" + newPrefix + "`").queue();
                } catch (Exception exception1) {
                    //nothing here lol
                }
            }
            case "serversettings" -> {
                String[] data = new String[0];
                try {
                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt"));
                    data = reader.readLine().split(" ");
                    reader.close();
                } catch (Exception exception) {
                    File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt");
                    try {
                        file.createNewFile();
                        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                        pw.print("true true false true true");
                        pw.close();
                    } catch (Exception exception1) {
                        //nothing here lol
                    }
                }
                try {
                    switch (message[1]) {
                        case "autoresponse" -> {
                            try {
                                writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt")));
                                if (message[2].equals("false") || message[2].equals("off")) {
                                    writer.print("false " + data[1] + " " + data[2]);
                                    writer.close();
                                    e.getChannel().sendMessage("Turned off Autoresponse!").queue();
                                } else if (message[2].equals("true") || message[2].equals("on")) {
                                    writer.print("true " + data[1] + " " + data[2]);
                                    writer.close();
                                    e.getChannel().sendMessage("Turned on Autoresponse!").queue();
                                } else {
                                    e.getChannel().sendMessage("That format isn't supported!  Supported values are `true/false`, `on/off`").queue();
                                }
                            } catch (Exception exception1) {
                                e.getChannel().sendMessage("That format isn't supported!  Supported values are `true/false`, `on/off`").queue();
                            }
                        }
                        case "reactions" -> {
                            try {
                                writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt")));
                                if (message[2].equals("false") || message[2].equals("off")) {
                                    writer.print(data[0] + " false " + data[2]);
                                    writer.close();
                                    e.getChannel().sendMessage("Turned off Reactions!").queue();
                                } else if (message[2].equals("true") || message[2].equals("on")) {
                                    writer.print(data[0] + " true " + data[2]);
                                    writer.close();
                                    e.getChannel().sendMessage("Turned on Reactions!").queue();
                                } else {
                                    e.getChannel().sendMessage("That format isn't supported!  Supported values are `true/false`, `on/off`").queue();
                                }
                            } catch (Exception exception1) {
                                e.getChannel().sendMessage("You need to enter a value.  Currently supportad values: `true/false`, `on/off`").queue();
                            }
                        }
                        case "leveling" -> {
                            try {
                                writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt")));
                                if (message[2].equals("false") || message[2].equals("off")) {
                                    writer.print(data[0] + " " + data[1] + " false");
                                    writer.close();
                                    e.getChannel().sendMessage("Turned off Leveling!").queue();
                                } else if (message[2].equals("true") || message[2].equals("on")) {
                                    writer.print(data[0] + " " + data[1] + " true");
                                    writer.close();
                                    e.getChannel().sendMessage("Turned on Leveling!").queue();
                                    File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt");
                                    if (!file.exists()) {
                                        file.createNewFile();
                                        writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                                        writer.print("15 25 60");
                                        writer.close();
                                    }
                                    file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt");
                                    if (!file.exists()) {
                                        file.createNewFile();
                                    }
                                    file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId());
                                    if(!file.exists()) {
                                        file.mkdirs();
                                    }
                                } else {
                                    e.getChannel().sendMessage("That format isn't supported!  Supported values are `true/false`, `on/off`").queue();
                                }
                            } catch (Exception exception1) {
                                e.getChannel().sendMessage("You need to enter a value.  Currently supportad values: `true/false`, `on/off`").queue();
                            }
                        }
                    }
                } catch (Exception exception) {
                    EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE)
                            .setTitle("Server Settings for " + e.getGuild().getName())
                            .addField("Autoresponse", "A variety of more than forty triggers to add some pizzazz to your conversation!\nCurrent value: `" + data[0] + "`\nID: `autoresponse`", false)
                            .addField("Reactions", "All messages sent in channels with \"announcement\" in their name will have a 100% chance to have the V I O L A reaction.\nAll other messages have a 2.5% chance of a V I O L A reactions.\nSome random messages will have :violin: reacted on them.\nCurrent value: `" + data[1] + "`\nID: `reactions`", false)
                            .addField("**BETA FEATURE** Leveling", "Turns on the beta leveling feature.  Currently only logs XP but will extend to role rewards in the future.\nCurrent value: `" + data[2] + "`\nID: `leveling`", false)
                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
                    e.getChannel().sendMessage(builder.build()).queue();
                }
            }
            case "levelsettings" -> {
                String[] serverSettings = new String[3];
                try {
                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt"));
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
                                reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt"));
                                serverXpSettings = reader.readLine().split(" ");
                                writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt")));
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
                                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt"));
                                    serverXpSettings = reader.readLine().split(" ");
                                    writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt")));
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
                                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt"));
                                    serverXpSettings = reader.readLine().split(" ");
                                    writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt")));
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
                                                reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt"));
                                                serverBlacklist = reader.readLine() + " ";
                                                if (serverBlacklist.contains(channel)) {
                                                    e.getChannel().sendMessage("You already blacklisted this channel, you don't need to blacklist it again.").queue();
                                                } else {
                                                    writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt")));
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
                                                reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt"));
                                                serverBlacklist = reader.readLine();
                                                if (serverBlacklist.contains(channel)) {
                                                    writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt")));
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
                                    }
                                } catch(Exception exception) {
                                    e.getChannel().sendMessage("You must either add or remove a channel.").queue();
                                }
                            }
                        }
                    } catch (Exception exception) {
                        try {
                            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt"));
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
                            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt"));
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
                                temp.append("<#").append(s).append("> ");
                            }
                        }
                        builder.addField("**Blacklisted Channels**", temp.toString(), false);
                        e.getChannel().sendMessage(builder.build()).queue();
                    }
                } else {
                    e.getChannel().sendMessage("You cannot change Leveling settings without enabling Leveling.").queue();
                }
            }
            case "setlevel" -> {
                String id = message[1];
                int level = Integer.parseInt(message[2]);
                String userData;
                try {
                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + id + ".txt"));
                    userData = reader.readLine();
                    reader.close();
                } catch (Exception exception) {
                    e.getChannel().sendMessage("This user save doesn't exist!").queue();
                    throw new IllegalArgumentException();
                }
                StringBuilder newData = new StringBuilder();
                newData.append(level).append(" ").append(userData.split(" ")[1]).append(" ").append(userData.split(" ")[2]);
                try {
                    writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + id + ".txt")));
                    writer.print(newData);
                    writer.close();
                    e.getChannel().sendMessage("Successfully set " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName() + "'s level to " + level).queue();
                } catch (Exception exception) {
                    //nothing here lol
                }
            }
        }
    }
}