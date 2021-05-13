package dev;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;

public class Luthier {
    public Luthier(GuildMessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        try {
            switch (message[1]) {
                case "setup" -> {
                    File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt");
                    try {
                        file.createNewFile();
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                        writer.print(e.getChannel().getId() + " 1 0.01 false blank 0");
                        writer.close();
                        e.getChannel().sendMessage("Successfully set up Luthier for " + e.getGuild().getName() + " in " + e.getChannel().getAsMention()).queue();
                    } catch (Exception exception2) {
                        //nothing here lol
                    }
                }
                case "edit" -> {
                    String id = e.getGuild().getId();
                    StringBuilder luthierData = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        luthierData.append(message[i]).append(" ");
                    }
                    luthierData.deleteCharAt(luthierData.length() - 1);
                    try {
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".txt")));
                        writer.print(luthierData);
                        writer.close();
                        e.getChannel().sendMessage("Successfully edited the data of Luthier for " + e.getGuild().getName()).queue();
                    } catch (Exception exception1) {
                        //nothing here lol
                    }
                }
                case "add" -> {
                    String[] data = new String[0];
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt"));
                        data = reader.readLine().split(" ");
                        reader.close();
                    } catch (Exception exception1) {
                        //nothing here lol
                    }
                    long add = Long.parseLong(message[2]);
                    StringBuilder luthierData = new StringBuilder().append(data[0]).append(" ").append((Long.parseLong(data[1]) + add));
                    for (int i = 2; i < data.length; i++) {
                        luthierData.append(" ").append(data[i]);
                    }
                    try {
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt")));
                        writer.print(luthierData);
                        writer.close();
                        e.getChannel().sendMessage("Successfully added " + message[2] + "x multiplier to " + e.getGuild().getName() + ".  New multiplier: " + (Long.parseLong(data[1]) + add)).queue();
                    } catch (Exception exception1) {
                        //nothing here lol
                    }
                }
            }
        } catch (Exception exception) {
            String luthierData;
            try {
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt"));
                luthierData = reader.readLine();
                e.getChannel().sendMessage("Luthier Settings for " + e.getGuild().getName() + ": " + luthierData).queue();
                reader.close();
            } catch (Exception exception1) {
                e.getChannel().sendMessage("Luthier has not been set up!").queue();
            }
        }
    }
}