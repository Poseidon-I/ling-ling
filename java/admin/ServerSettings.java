package admin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.*;

public class ServerSettings {
    public ServerSettings(GuildMessageReceivedEvent e) {
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
                pw.print("true");
                pw.close();
            } catch (Exception exception1) {
                //nothing here lol
            }
        }
        try {
            if (message[1].equals("autoresponse")) {
                try {
                    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt")));
                    if (message[2].equals("false") || message[2].equals("off")) {
                        writer.print("false");
                        writer.close();
                        e.getChannel().sendMessage("Turned off Autoresponse!").queue();
                    } else if (message[2].equals("true") || message[2].equals("on")) {
                        writer.print("true");
                        writer.close();
                        e.getChannel().sendMessage("Turned on Autoresponse!").queue();
                    } else {
                        e.getChannel().sendMessage("That format isn't supported!  Supported values are `true/false`, `on/off`").queue();
                    }
                } catch (Exception exception1) {
                    e.getChannel().sendMessage("That format isn't supported!  Supported values are `true/false`, `on/off`").queue();
                }
            }
        } catch(Exception exception) {
            EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE)
                    .setTitle("Server Settings for " + e.getGuild().getName())
                    .addField("Autoresponse", "A variety of more than forty triggers to add some pizzazz to your conversation!\nCurrent value: `" + data[0] + "`\nID: `autoresponse`", false)
                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
            e.getChannel().sendMessage(builder.build()).queue();
        }
    }
}
