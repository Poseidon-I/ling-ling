package dev;

import economy.Start;
import eventListeners.GenericDiscordEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Ban {
	public static void ban(GenericDiscordEvent e, String idToModerate, String reason) {
		try {
			if(idToModerate.equals("")) {
				throw new IllegalArgumentException();
			}
			Long.parseLong(idToModerate);
		} catch(Exception exception) {
			e.reply("You didn't provide an ID!");
			return;
		}

		if(idToModerate.equals(e.getAuthor().getId())) {
			e.reply("Imagine trying to ban yourself, how dumb are you???");
		} else if(idToModerate.equals("619989388109152256") || idToModerate.equals("488487157372157962")) {
			e.reply("Imagine trying to ban a developer smh");
		} else {
			JSONParser parser = new JSONParser();
			JSONObject data;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + idToModerate + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
				data.replace("banned", true);
				FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + idToModerate + ".json");
				writer.write(data.toJSONString());
				writer.close();
			} catch(Exception exception) {
				Start.start(e, idToModerate, true);
			}
			String name;
			try {
				name = Objects.requireNonNull(e.getJDA().getUserById(idToModerate)).getName();
			} catch(Exception exception) {
				name = "Someone";
			}
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("734697505543159879")).sendMessage("""
					:hammer: **THE BAN HAMMER HAS SPOKEN** :hammer:
					            Someone has been banned.
										
					https://i.imgur.com/KTyk7EC.mp4""").queue();
			LogCase.logCase(e, "BAN", idToModerate, reason);
			try {
				Objects.requireNonNull(e.getJDA().getUserById(idToModerate)).openPrivateChannel().queue((channel) -> channel.sendMessage("You were banned.  Reason: " + reason).queue());
			} catch(Exception exception) {
				// nothing here lol
			}
			e.reply(":hammer: " + name + " was successfully banned!");
		}
	}
}