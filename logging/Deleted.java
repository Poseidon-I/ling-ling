package logging;

import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class Deleted extends ListenerAdapter {
    public void onGuildMessageDelete(@NotNull GuildMessageDeleteEvent e) {
        String[] data = new String[0];
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt"));
            data = br.readLine().split(" ");
            br.close();
        } catch(Exception exception) {
            File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt");
            try {
                file.createNewFile();
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt")));
                pw.print("true true true true");
                pw.close();
            } catch(Exception exception1) {
                //nothing here lol
            }
        }
        if(data[2].equals("true")) {
            String message = e.getChannel().retrieveMessageById(e.getMessageId()).complete().getContentRaw();
            e.getChannel().sendMessage(message).queue();
        }
    }
}