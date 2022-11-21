package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

// CLAIM COMMAND - TOO LAZY TO RENAME
public class Vote {
	public static void vote(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		long time = System.currentTimeMillis();
		if(time < (long) data.get("voteCD")) {
			long milliseconds = (long) data.get("voteCD") - time;
			long hours = milliseconds / 3600000;
			milliseconds -= hours * 3600000;
			long minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			long seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			e.reply("You claimed your Free Box too recently!  Wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
		} else {
			data.replace("voteBox", (long) data.get("voteBox") + 1);
			data.replace("votes", (long) data.get("votes") + 1);
			data.replace("voteCD", System.currentTimeMillis() + 21540000);
			e.reply("You have received `1`" + Emoji.FREE_BOX).queue();
			if((long) data.get("votes") % 40 == 0) {
				data.replace("medals", (long) data.get("medals") + 1);
				e.reply("You have claimed Free Boxes `" + data.get("votes") + "` times!  Enjoy your Ling Ling Medal!").queue();
			}
			RNGesus.lootbox(e, data);
			SaveData.saveData(e, data);
		}
	}
}