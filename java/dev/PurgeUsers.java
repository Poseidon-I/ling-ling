package dev;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class PurgeUsers {
    public PurgeUsers(GuildMessageReceivedEvent e) {
        e.getChannel().sendMessage("Purging saves for users with no violins or income...").queue();
        File directory = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
        File[] files = directory.listFiles();
        if (files != null) {
            int deleted = 0;
            for (File file : files) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                    String line = reader.readLine();
                    reader.close();
                    if (line.equals("0 0 0 0 0 0 0 0 0 false false 0 0 0 0 0 0 0 false false false 0 0 0 0 false 0 0 0 0 0 0 1 1 0 0 0 false 0 0 0 0 0 0 0 0 0 0 false false false 0 0 0 0 0 0 0 0 0 false false 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 false 0 0 0 0 false false 0 0 0 0 0 0 0 0 0 false false false 1")) {
                        file.delete();
                        deleted++;
                    }
                } catch (Exception exception) {
                    //nothing here lol
                }
            }
            e.getChannel().sendMessage("Successfully purged " + deleted + " files!").queue();
        }
    }
}