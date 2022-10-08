package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Random;

public class Scales {
	public static void scales(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		long time = System.currentTimeMillis();
		Random random = new Random();
		if(time < (long) data.get("scaleCD")) {
			long milliseconds = (long) data.get("scaleCD") - time;
			long seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			e.reply("Chill, you can't play two scales at once!  Wait " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
		} else {
			long base = Numbers.calculateAmount(data, random.nextInt(11) + 10);
			Numbers.calculateLoan(data, base);
			if((boolean) data.get("timeCrunch")) {
				data.replace("scaleCD", time + 64500);
			} else {
				data.replace("scaleCD", time + 89500);
			}
			e.reply("You played your scales and earned " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
			data.replace("scalesPlayed", (long) data.get("scalesPlayed") + 1);
			data.replace("earnings", (long) data.get("earnings") + base);
			RNGesus.lootbox(e, data);
			SaveData.saveData(e, data);
		}
	}
}