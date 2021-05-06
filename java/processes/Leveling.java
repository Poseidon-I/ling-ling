package processes;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Leveling {
    public Leveling(GuildMessageReceivedEvent e) {
        BufferedReader reader;
        PrintWriter writer;
        Random random = new Random();
        String[] userData = new String[3];
        String[] serverXpSettings = new String[3];
        String serverBlacklist = "";
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + e.getAuthor().getId() + ".txt"));
            userData = reader.readLine().split(" ");
            reader.close();
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "xp.txt"));
            serverXpSettings = reader.readLine().split(" ");
            reader.close();
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "blacklist.txt"));
            serverBlacklist = " " + reader.readLine();
            reader.close();
        } catch (Exception exception) {
            File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + e.getAuthor().getId() + ".txt");
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
            int level = 0;
            int xp = 0;
            long time = 0;
            try {
                level = Integer.parseInt(userData[0]);
                xp = Integer.parseInt(userData[1]);
                time = Long.parseLong(userData[2]);
            } catch(Exception exception) {
                File file = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + e.getAuthor().getId() + ".txt");
                try {
                    file.createNewFile();
                    writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                    writer.print("0 0 " + System.currentTimeMillis());
                    writer.close();
                } catch (Exception exception1) {
                    throw new IllegalArgumentException();
                }
            }
            int min = Integer.parseInt(serverXpSettings[0]);
            int max = Integer.parseInt(serverXpSettings[1]);
            int cooldown = Integer.parseInt(serverXpSettings[2]);
            String[] data;
            HashMap<Integer, Role> map = new HashMap<>();
            try {
                reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "rewards.txt"));
                while (true) {
                    try {
                        data = reader.readLine().split(" ");
                        map.put(Integer.parseInt(data[0]), e.getGuild().getRoleById(data[1]));
                    } catch (Exception exception) {
                        break;
                    }
                }
                reader.close();
            } catch (Exception exception) {
                e.getChannel().sendMessage("Something went wrong when creating the file, please have an Admin turn leveling off and back on.  Your data will not be lost.").queue();
            }
            if (System.currentTimeMillis() > time) {
                if(max - min + 1 <= 0) {
                    e.getChannel().sendMessage("The minimum XP must be lower than the maximum.  Ping a server admin immediately to have them fix this.  If an admin does not respond, contact a bot admin in the support server.").queue();
                } else {
                    xp += random.nextInt(max - min + 1) + min;
                    time = System.currentTimeMillis() + cooldown * 1000L;
                    if (xp > (level + 1) * 100) {
                        while(xp > (level + 1) * 100) {
                            level++;
                            xp -= level * 100;
                            if (map.containsKey(level)) {
                                try {
                                    e.getGuild().addRoleToMember(Objects.requireNonNull(e.getGuild().getMember(e.getAuthor())), map.get(level)).queue();
                                } catch(Exception exception) {
                                    e.getChannel().sendMessage("I could not assign that role!  Make sure that my highest role is above all level reward roles.").queue();
                                }
                            }
                        }
                        e.getChannel().sendMessage("Congratulations <@" + e.getAuthor().getId() + ">!  You advanced to Level " + level + "!").queue();
                    }
                    //insert role rewards here soon
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + e.getAuthor().getId() + ".txt")));
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