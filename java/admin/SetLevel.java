package admin;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.Objects;

public class SetLevel {
    public SetLevel(GuildMessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        String id = message[1];
        int level = Integer.parseInt(message[2]);
        String userData;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + id + ".txt"));
            userData = reader.readLine();
            reader.close();
        } catch (Exception exception) {
            e.getChannel().sendMessage("This user save doesn't exist!").queue();
            throw new IllegalArgumentException();
        }
        StringBuilder newData = new StringBuilder();
        newData.append(level).append(" ").append(userData.split(" ")[1]).append(" ").append(userData.split(" ")[2]);
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Leveling Data\\" + e.getGuild().getId() + "\\" + id + ".txt")));
            writer.print(newData);
            writer.close();
            e.getChannel().sendMessage("Successfully set " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName() + "'s level to " + level).queue();
        } catch (Exception exception) {
            //nothing here lol
        }
    }
}