package dev;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.List;

public class UpdateRoles {
	public UpdateRoles(GuildMessageReceivedEvent e) {
		if(e.getGuild().getId().equals("670725611207262219")) {
			List<Member> list = e.getGuild().getMembers();
			for(Member member : list) {
				List<Role> list2 = member.getRoles();
				JSONParser parser = new JSONParser();
				JSONObject data;
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + member.getId() + ".json")) {
					data = (JSONObject) parser.parse(reader);
					reader.close();
				} catch(Exception exception) {
					continue;
				}
				if(list2.contains(e.getGuild().getRoleById("852752096733429781"))) {
					data.replace("isBooster", true);
				}
				if(list2.contains(e.getGuild().getRoleById("734697410273607751"))) {
					data.replace("serverLevel", 1.25);
				} else if(list2.contains(e.getGuild().getRoleById("845121274958184499"))) {
					data.replace("serverLevel", 1.2);
				} else if(list2.contains(e.getGuild().getRoleById("845121187741958166"))) {
					data.replace("serverLevel", 1.15);
				} else if(list2.contains(e.getGuild().getRoleById("734697411074719765"))) {
					data.replace("serverLevel", 1.11);
				} else if(list2.contains(e.getGuild().getRoleById("734697411783688245"))) {
					data.replace("serverLevel", 1.075);
				} else if(list2.contains(e.getGuild().getRoleById("734697412865818645"))) {
					data.replace("serverLevel", 1.045);
				} else if(list2.contains(e.getGuild().getRoleById("734697413901680691"))) {
					data.replace("serverLevel", 1.02);
				} else {
					data.replace("serverLevel", 1);
				}
				try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + member.getId() + ".json")) {
					writer.write(data.toJSONString());
					writer.close();
				} catch(Exception exception) {
					e.getChannel().sendMessage("bruh").queue();
				}
			}
			e.getChannel().sendMessage("Successfully force-updated role multipliers!").queue();
		} else {
			e.getChannel().sendMessage("You can't use this command here!  Go to the support server stupid.").queue();
		}
	}
}