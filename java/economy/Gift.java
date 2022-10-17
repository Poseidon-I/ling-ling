package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Gift {
	public static void gift(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		if((boolean) data.get("hadGiftToday")) {
			e.reply("I appreciate your generosity, but I can't let you give away too much.  Wait until 00:00 UTC!").queue();
		} else {
			String target;
			try {
				target = Objects.requireNonNull(e.getOption("otheruser")).getAsString();
			} catch(Exception exception1) {
				e.reply("You have to gift someone for this to work.").setEphemeral(true).queue();
				return;
			}
			if(target.equals(e.getUser().getId())) {
				e.reply("Hey greedy!  Don't gift yourself!").queue();
			} else {
				JSONParser parser = new JSONParser();
				JSONObject targetdata;
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
					targetdata = (JSONObject) parser.parse(reader);
					reader.close();
				} catch(Exception exception) {
					e.reply("You did not provide a valid User ID.  Doesn't make sense to gift someone nonexistant, does it?").queue();
					return;
				}
				data.replace("giftsGiven", (long) data.get("giftsGiven") + 1);
				data.replace("hadGiftToday", true);
				targetdata.replace("giftsReceived", (long) targetdata.get("giftsReceived") + 1);
				targetdata.replace("giftBox", (long) targetdata.get("giftBox") + 1);
				RNGesus.lootbox(e, data);
				SaveData.saveData(e, data);
				try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + target + ".json")) {
					writer.write(targetdata.toJSONString());
					writer.close();
				} catch(Exception exception) {
					//nothing here lol
				}
				try {
					e.reply("Successfully gifted 1" + Emoji.GIFT_BOX + " to " + Objects.requireNonNull(e.getJDA().getUserById(target)).getName()).queue();
				} catch(Exception exception) {
					e.reply("Successfully gifted 1" + Emoji.GIFT_BOX + " to Someone").queue();
				}
				if((boolean) targetdata.get("DMs")) {
					try {
						Objects.requireNonNull(e.getJDA().getUserById(target)).openPrivateChannel().queue((channel) -> channel.sendMessage("<@" + e.getUser().getId() + "> (" + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + ") just gifted you!").queue());
					} catch(Exception exception) {
						// nothing here lol
					}
				}
			}
		}
	}
}