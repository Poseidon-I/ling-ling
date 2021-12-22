package eventListeners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class RoleRemoved extends ListenerAdapter {
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent e) {
		if(e.getGuild().getId().equals("670725611207262219") && e.getRoles().contains(e.getGuild().getRoleById("852752096733429781"))) {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + e.getMember().getId() + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				throw new IllegalArgumentException("FILE ERROR FOR " + e.getMember().getId());
			}
			data.replace("isBooster", false);
			try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + e.getMember().getId() + ".json")) {
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
		}
	}
}