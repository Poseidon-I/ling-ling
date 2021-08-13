package eventListeners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.*;

public class RoleRemoved extends ListenerAdapter {
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent e) {
        if(e.getGuild().getId().equals("670725611207262219")) {
            if(e.getRoles().contains(e.getGuild().getRoleById("852752096733429781"))) {
                String[] data = new String[0];
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + e.getMember().getId() + ".txt"));
                    data = reader.readLine().split(" ");
                    reader.close();
                } catch (Exception exception) {
                    //nothing here lol
                }
                data[96] = "false";
                try {
                    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + e.getMember().getId() + ".txt")));
                    pw.print(data[0]);
                    for(int i = 1; i < data.length; i ++) {
                        pw.print(" " + data[i]);
                    }
                    pw.close();
                } catch(Exception exception) {
                    //nothing here lol
                }
            }
        }
    }
}