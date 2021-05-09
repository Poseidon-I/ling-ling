package admin;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RoleRewards {
    public RoleRewards(GuildMessageReceivedEvent e) {
        String[] data;
        String[] message = e.getMessage().getContentRaw().split(" ");
        HashMap<Integer, Role> map = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "rewards.txt"));
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
            e.getChannel().sendMessage("Something went wrong when creating the file, please turn leveling off and back on.  Your data will not be lost.").queue();
            throw new IllegalArgumentException();
        }
        if (message.length > 1) {
            switch (message[1]) {
                case "add" -> {
                    int level = Integer.parseInt(message[2]);
                    Role role;
                    try {
                        role = e.getGuild().getRoleById(message[3]);
                        map.put(level, role);
                        assert role != null;
                        e.getChannel().sendMessage("Successfully added role reward of Role " + role.getName() + " for Level " + level).queue();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("The role ID provided is not valid!").queue();
                    }
                }
                case "remove" -> {
                    int level = Integer.parseInt(message[2]);
                    map.remove(level);
                    e.getChannel().sendMessage("Removed role reward for level " + level).queue();
                }
                default -> e.getChannel().sendMessage("You can only add or remove role rewards.").queue();
            }
            StringBuilder string = new StringBuilder();
            for (Map.Entry<Integer, Role> entry : map.entrySet()) {
                string.append(entry.getKey()).append(" ").append(entry.getValue().getId()).append("\n");
            }
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Leveling\\" + e.getGuild().getId() + "rewards.txt")));
                writer.print(string.toString());
                writer.close();
            } catch (Exception exception) {
                //nothing here lol
            }
        }
    }
}
