package regular;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.Member;
import net.hypixel.api.HypixelAPI;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.reply.GuildReply;
import processes.HypixelManager;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class UpdateHypixel {
	public static void updateHypixel(GenericDiscordEvent e) {
		try {
			HypixelHttpClient client = HypixelManager.getClient();
			HypixelAPI api = new HypixelAPI(client);

			List<GuildReply.Guild.Member> guildMembers = api.getGuildByName("2bil Midas").get().getGuild().getMembers();
			List<Member> nonGuildServerMembers = e.getGuild().getMembers();
			for(GuildReply.Guild.Member member : guildMembers) {
				String discord = api.getPlayerByUuid(member.getUuid()).get().getPlayer().getRaw().get("socialMedia").getAsJsonObject().get("links").getAsJsonObject().get("DISCORD").getAsString();
				String[] parts = discord.split("#");
				List<Member> guildServerMember = e.getGuild().getMembersByName(parts[0], true);
				if(!guildServerMember.isEmpty()) {
					e.getGuild().addRoleToMember(guildServerMember.getFirst(), Objects.requireNonNull(e.getGuild().getRoleById("1090691229789982800")));
					nonGuildServerMembers.remove(guildServerMember.getFirst());
				}
			}
			for(Member member : nonGuildServerMembers) {
				e.getGuild().removeRoleFromMember(member, Objects.requireNonNull(e.getGuild().getRoleById("1090691229789982800")));
			}
		} catch(ExecutionException exception) {
			e.reply("You are being rate-limited for this user!");
		} catch(NullPointerException exception) {
			e.reply("`" + e.getMessage().getContentRaw().split(" ")[2] + " has not linked a Discord account!");
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}