package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.discordbots.api.client.DiscordBotListAPI;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

public class Vote {
	public static void GiveRewards(GuildMessageReceivedEvent e, JSONObject data, int numBoxes) {
		data.replace("voteBox", (long) data.get("voteBox") + numBoxes);
		data.replace("votes", (long) data.get("votes") + 1);
		data.replace("voteCD", System.currentTimeMillis() + 41400000);
		e.getChannel().sendMessage("Thank you for voting!  You have received **" + numBoxes + "** Vote Boxes!").queue();
		if((long) data.get("votes") % 40 == 0) {
			data.replace("medals", (long) data.get("medals") + 1);
			e.getChannel().sendMessage("You have voted " + data.get("votes") + " times!  Enjoy your Ling Ling Medal!").queue();
		}
		new SaveData(e, data);
	}
	
	public Vote(GuildMessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		long time = System.currentTimeMillis();
		if(time < (long) data.get("voteCD")) {
			e.getChannel().sendMessage("You claimed your reward too recently!  If you forgot to claim your reward after voting previously, this is a friendly reminder to claim your reward right after you vote.\n\n#dontbelikestrad").queue();
		} else {
			DiscordBotListAPI api = null;
			try(BufferedReader rdr = new BufferedReader(new FileReader("Ling Ling Bot Data\\top.txt"))) {
				api = new DiscordBotListAPI.Builder()
						.token(rdr.readLine())
						.botId("733409243222507670")
						.build();
			} catch(Exception exception) {
				e.getChannel().sendMessage("top.gg shit no look at me\nPing/DM a bot mod and they'll give you your reward").queue();
			}
			String id = e.getAuthor().getId();
			assert api != null;
			DiscordBotListAPI finalApi = api;
			api.hasVoted(id).whenComplete((hasVoted, f) -> {
				if(hasVoted) {
					finalApi.getVotingMultiplier().whenComplete((multiplier, g) -> {
						if(multiplier.isWeekend()) {
							GiveRewards(e, data, 2);
						} else {
							GiveRewards(e, data, 1);
						}
					});
				} else {
					e.getChannel().sendMessage("You have not voted yet!  Vote here:\n<https://top.gg/bot/733409243222507670/vote>").queue();
				}
			});
		}
	}
}