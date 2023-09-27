package regular;

import com.google.gson.JsonObject;
import eventListeners.GenericDiscordEvent;
import net.hypixel.api.HypixelAPI;
import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.reply.GuildReply;
import net.hypixel.api.reply.PlayerReply;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

// BEETHOVEN-ONLY CLASS
public class Link {
	public static void link(GenericDiscordEvent e, String playerName) {
		e.reply("This command is disabled for 3-5 business days.");
        /*
        try {
            HypixelHttpClient client = new ApacheHttpClient(UUID.fromString("4b3188d6-b711-4c9e-b5be-d709aa63a2ce"));
            HypixelAPI api = new HypixelAPI(client);
            PlayerReply reply = api.getPlayerByName(playerName).get();
            PlayerReply.Player player = reply.getPlayer();
            GuildReply gReply = api.getGuildByPlayer(player.getUuid()).get();
            GuildReply.Guild guild = gReply.getGuild();
            JsonObject object = player.getRaw();
            String discord = object.get("socialMedia").getAsJsonObject().get("links").getAsJsonObject().get("DISCORD").getAsString();
            String[] parts = discord.split("#");
            String uuid = player.getUuid().toString();
            if (parts[0].equals(e.getAuthor().getName())) {
                if (guild.getName().equals("2bil Midas")) {
                    e.getMessage().reply("`" + player.getName() + "` is in the guild!").queue();
                } else {
                    e.getMessage().reply("`" + player.getName() + "` is not in the guild!  What a shame smfh.").queue();
                }
            } else {
                e.getMessage().reply("This is not your Minecraft account!").mentionRepliedUser(false).queue();
            }
        } catch (ExecutionException exception) {
            e.getMessage().reply("You are being rate-limited for this user!").mentionRepliedUser(false).queue();
        } catch (NullPointerException exception) {
            e.getMessage().reply("`" + e.getMessage().getContentRaw().split(" ")[2] + " has not linked a Discord account!").mentionRepliedUser(false).queue();
        } catch (Exception exception) {
            exception.printStackTrace();
        }*/
	}
}
