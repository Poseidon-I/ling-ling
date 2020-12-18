package joinLeave;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class BotJoin extends ListenerAdapter {
    public void onGuildJoin(@NotNull GuildJoinEvent e) {
        File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt");
        try {
            file.createNewFile();
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt")));
            pw.print("true true false true true");
            pw.close();
        } catch(Exception exception) {
            //nothing here lol
        }
    }
}