package dev;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.List;

public class UpdateRoles {
	public UpdateRoles(GuildMessageReceivedEvent e) {
		if(e.getGuild().getId().equals("670725611207262219")) {
			List<Member> list = e.getGuild().getMembers();
			for(Member member : list) {
				List<Role> list2 = member.getRoles();
				try {
					BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + member.getId() + ".txt"));
					String[] data = reader.readLine().split(" ");
					reader.close();
					if(list2.contains(e.getGuild().getRoleById("852752096733429781"))) {
						data[96] = "true";
					}
					if(list2.contains(e.getGuild().getRoleById("734697410273607751"))) {
						data[97] = "1.25";
					} else if(list2.contains(e.getGuild().getRoleById("845121274958184499"))) {
						data[97] = "1.2";
					} else if(list2.contains(e.getGuild().getRoleById("845121187741958166"))) {
						data[97] = "1.15";
					} else if(list2.contains(e.getGuild().getRoleById("734697411074719765"))) {
						data[97] = "1.11";
					} else if(list2.contains(e.getGuild().getRoleById("734697411783688245"))) {
						data[97] = "1.075";
					} else if(list2.contains(e.getGuild().getRoleById("734697412865818645"))) {
						data[97] = "1.045";
					} else if(list2.contains(e.getGuild().getRoleById("734697413901680691"))) {
						data[97] = "1.02";
					} else {
						data[97] = "1";
					}
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + member.getId() + ".txt")));
					writer.print(data[0]);
					for(int i = 1; i < data.length; i++) {
						writer.print(" " + data[i]);
					}
					writer.close();
				} catch(Exception exception) {
					//nothing here lol
				}
			}
			e.getChannel().sendMessage("Successfully force-updated role multipliers!").queue();
		} else {
			e.getChannel().sendMessage("You can't use this command here!  Go to the support server smh.").queue();
		}
	}
}