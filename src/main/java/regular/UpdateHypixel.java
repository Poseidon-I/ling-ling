package regular;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.Member;
import net.hypixel.api.HypixelAPI;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.reply.GuildReply;
import processes.HypixelManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UpdateHypixel {
	public static void updateHypixel(GenericDiscordEvent e) {
		HypixelHttpClient client = HypixelManager.getClient();
		HypixelAPI api = new HypixelAPI(client);

		List<GuildReply.Guild.Member> guildMembers;
		try {
			guildMembers = api.getGuildByName("2bil%20Midas").get().getGuild().getMembers();
		} catch(Exception exception) {
			exception.printStackTrace();
			return;
		}
		List<Member> nonGuildServerMembers = e.getGuild().getMembers();
		ArrayList<String> nonGuildServerMemberIDs = new ArrayList<>();
		for(Member member : nonGuildServerMembers) {
			nonGuildServerMemberIDs.add(member.getId());
		}
		for(GuildReply.Guild.Member member : guildMembers) {
			String discord;
			try {
				discord = api.getPlayerByUuid(member.getUuid()).get().getPlayer().getRaw().get("socialMedia").getAsJsonObject().get("links").getAsJsonObject().get("DISCORD").getAsString();
			} catch(Exception exception) {
				continue;
			}
			String[] parts = discord.split("#");
			List<Member> guildServerMember = e.getGuild().getMembersByName(parts[0], true);
			if(!guildServerMember.isEmpty()) {
				try {
					e.getGuild().addRoleToMember(guildServerMember.getFirst(), Objects.requireNonNull(e.getGuild().getRoleById("1090691229789982800"))).queue();
					nonGuildServerMemberIDs.remove(guildServerMember.getFirst().getId());
				} catch(Exception exception) {
					exception.printStackTrace();
				}
			}
		}
		for(String id : nonGuildServerMemberIDs) {
			e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getGuild().getMemberById(id)), Objects.requireNonNull(e.getGuild().getRoleById("1090691229789982800"))).queue();
		}
		e.reply("Done!");
	}
}