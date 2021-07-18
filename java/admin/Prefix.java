package admin;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Prefix {
    public Prefix(GuildMessageReceivedEvent e) {
        char newPrefix = e.getMessage().getContentRaw().split(" ")[1].charAt(0);
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Prefixes\\" + e.getGuild().getId() + ".txt")));
            writer.print(newPrefix);
            writer.close();
            e.getChannel().sendMessage("The prefix is now `" + newPrefix + "`").queue();
        } catch (Exception exception1) {
            //nothing here lol
        }
    }
}