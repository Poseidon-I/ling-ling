package processes;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.Random;

public class Leveling {
    public Leveling(GuildMessageReceivedEvent e) {
        BufferedReader reader;
        PrintWriter writer;
        Random random = new Random();
        String[] userData = new String[3];
        String[] serverXpSettings = new String[3];
        //String[][] roleRewards;
        String serverBlacklist = "";
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + e.getAuthor().getId() + ".txt"));
            userData = reader.readLine().split(" ");
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt"));
            serverXpSettings = reader.readLine().split(" ");
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt"));
            serverBlacklist = " " + reader.readLine();
            reader.close();
        } catch (Exception exception) {
            File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + e.getAuthor().getId() + ".txt");
            try {
                file.createNewFile();
                writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                writer.print("0 0 " + System.currentTimeMillis());
                writer.close();
            } catch (Exception exception1) {
                throw new IllegalArgumentException();
            }
        }
        if (!serverBlacklist.contains(e.getChannel().getId())) {
            int level = Integer.parseInt(userData[0]);
            int xp = Integer.parseInt(userData[1]);
            long time = Long.parseLong(userData[2]);
            int min = Integer.parseInt(serverXpSettings[0]);
            int max = Integer.parseInt(serverXpSettings[1]);
            int cooldown = Integer.parseInt(serverXpSettings[2]);
            if (System.currentTimeMillis() > time) {
                if(max - min + 1 <= 0) {
                    e.getChannel().sendMessage("You cannot have a minimum that is higher than the maximum.  Please contact an admin immediately to fix this.").queue();
                } else {
                    xp += random.nextInt(max - min + 1) + min;
                    time = System.currentTimeMillis() + cooldown * 1000L;
                    if (xp > (level + 1) * 100) {
                        while(xp > (level + 1) * 100) {
                            level++;
                            xp -= level * 100;
                        }
                        e.getChannel().sendMessage("Congratulations <@" + e.getAuthor().getId() + ">!  You advanced to Level " + level + "!").queue();
                    }
                    //insert role rewards here soon
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + e.getAuthor().getId() + ".txt")));
                        writer.print(level + " " + xp + " " + time);
                        writer.close();
                    } catch (Exception exception) {
                        //nothing here lol
                    }
                }
            }
        }
    }
}