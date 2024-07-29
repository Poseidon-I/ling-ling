package regular;

import com.google.gson.JsonObject;
import economy.LoadData;
import eventListeners.GenericDiscordEvent;
import net.hypixel.api.HypixelAPI;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.reply.GuildReply;
import net.hypixel.api.reply.PlayerReply;
import org.json.simple.JSONObject;
import processes.HypixelManager;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

// BEETHOVEN-ONLY CLASS
public class Link {
	public static void link(GenericDiscordEvent e, String playerName) {
		JSONObject data = LoadData.loadData(e);
		try {
			HypixelHttpClient client = HypixelManager.getClient();
			HypixelAPI api = new HypixelAPI(client);
			PlayerReply reply = api.getPlayerByName(playerName).get();
			PlayerReply.Player player = reply.getPlayer();
			GuildReply gReply = api.getGuildByPlayer(player.getUuid()).get();
			GuildReply.Guild guild = gReply.getGuild();
			JsonObject object = player.getRaw();
			String discord = object.get("socialMedia").getAsJsonObject().get("links").getAsJsonObject().get("DISCORD").getAsString();
			String[] parts = discord.split("#");
			String uuid = player.getUuid().toString();
			if(parts[0].equals(e.getAuthor().getName())) {
				data.replace("mcIGN", playerName + ":" + uuid);
				if(guild.getName().equals("2bil Midas")) {
					e.getGuild().addRoleToMember(e.getAuthor(), Objects.requireNonNull(e.getGuild().getRoleById("1090691229789982800")));
					e.getMessage().reply("Here you go!").queue();
				} else {
					e.getMessage().reply("You are not in the guild.  Shame on you.").queue();
				}
			} else {
				e.getMessage().reply("This is not your Minecraft account!").mentionRepliedUser(false).queue();
			}
		} catch(ExecutionException exception) {
			e.getMessage().reply("You are being rate-limited for this user!").mentionRepliedUser(false).queue();
		} catch(NullPointerException exception) {
			e.getMessage().reply("`" + e.getMessage().getContentRaw().split(" ")[2] + " has not linked a Discord account!").mentionRepliedUser(false).queue();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}
