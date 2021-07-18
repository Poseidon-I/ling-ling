package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class SaveData {
    public SaveData(GuildMessageReceivedEvent e, String[] newData) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt")));
            pw.print(newData[0]);
            for(int i = 1; i < newData.length; i ++) {
                pw.print(" " + newData[i]);
            }
            pw.close();
        } catch(Exception exception) {
            //nothing here lol
        }
    }
}