package eventListeners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.*;

public class RoleAdded extends ListenerAdapter {
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent e) {
		if(e.getGuild().getId().equals("670725611207262219")) {
			String[] data = new String[0];
			try {
				BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + e.getMember().getId() + ".txt"));
				data = reader.readLine().split(" ");
				reader.close();
			} catch(Exception exception) {
				//nothing here lol
			}
			if(e.getRoles().contains(e.getGuild().getRoleById("852752096733429781"))) {
				data[96] = "true";
			} else if(e.getRoles().contains(e.getGuild().getRoleById("734697413901680691"))) {
				data[97] = "1.02";
			} else if(e.getRoles().contains(e.getGuild().getRoleById("734697412865818645"))) {
				data[97] = "1.045";
			} else if(e.getRoles().contains(e.getGuild().getRoleById("734697411783688245"))) {
				data[97] = "1.075";
			} else if(e.getRoles().contains(e.getGuild().getRoleById("734697411074719765"))) {
				data[97] = "1.11";
			} else if(e.getRoles().contains(e.getGuild().getRoleById("845121187741958166"))) {
				data[97] = "1.15";
			} else if(e.getRoles().contains(e.getGuild().getRoleById("845121274958184499"))) {
				data[97] = "1.2";
			} else if(e.getRoles().contains(e.getGuild().getRoleById("734697410273607751"))) {
				data[97] = "1.25";
			}
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + e.getMember().getId() + ".txt")));
				pw.print(data[0]);
				for(int i = 1; i < data.length; i++) {
					pw.print(" " + data[i]);
				}
				pw.close();
			} catch(Exception exception) {
				//nothing here lol
			}
		}
	}
}