package dev;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;

public class UpdateUsers {
    public UpdateUsers(GuildMessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        if (message.length > 1 && message[1].equals("confirm")) {
            e.getChannel().sendMessage("Updating saves for all users...").queue();
            File directory = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
            File[] files = directory.listFiles();
            StringBuilder append = new StringBuilder();
            for (int i = 2; i < message.length; i++) {
                append.append(" ").append(message[i]);
            }
            if (files != null) {
                for (File file : files) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                        String currentData = reader.readLine();
                        reader.close();
                        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                        writer.print(currentData + append);
                        writer.close();
                    } catch (Exception exception) {
                        //nothing here lol
                    }
                }
                e.getChannel().sendMessage("Successfully updated saves for all users!").queue();
            }
        } else {
            e.getChannel().sendMessage("Please type `!updateusers confirm` to confirm that you would like to update all data to the latest version.").queue();
        }
    }
}
