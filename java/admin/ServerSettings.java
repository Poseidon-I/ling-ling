package admin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.*;

public class ServerSettings {
    public ServerSettings(GuildMessageReceivedEvent e, char prefix) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        String[] data = new String[0];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt"));
            data = reader.readLine().split(" ");
            reader.close();
        } catch (Exception exception) {
            File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt");
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
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt")));
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
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt")));
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
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt")));
                        if (message[2].equals("false") || message[2].equals("off")) {
                            writer.print(data[0] + " " + data[1] + " false");
                            writer.close();
                            e.getChannel().sendMessage("Turned off Leveling!").queue();
                        } else if (message[2].equals("true") || message[2].equals("on")) {
                            writer.print(data[0] + " " + data[1] + " true");
                            writer.close();
                            e.getChannel().sendMessage("Turned on Leveling!").queue();
                            File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt");
                            if (!file.exists()) {
                                file.createNewFile();
                                writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                                writer.print("15 25 60");
                                writer.close();
                            }
                            file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt");
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "rewards.txt");
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId());
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
                    .addField("~~Reactions~~", "~~All messages sent in channels with \"announcement\" in their name will have a 100% chance to have the V I O L A reaction.~~\n~~All other messages have a 2.5% chance of a V I O L A reactions.~~\n**TEMPORARILY DISABLED**\nCurrent value: `" + data[1] + "`\nID: `reactions`", false)
                    .addField("Leveling", "Turns on the leveling feature.  Can be configured using `" + prefix + "rolerewards` to give role rewards, change the XP amount given, and change the cooldown.\nCurrent value: `" + data[2] + "`\nID: `leveling`", false)
                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
            e.getChannel().sendMessage(builder.build()).queue();
        }
    }
}
