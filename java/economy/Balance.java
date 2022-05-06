package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.awt.*;
import java.io.FileReader;
import java.util.Objects;

public class Balance {
	public Balance(MessageReceivedEvent e) {
		JSONObject data;
		String[] message = e.getMessage().getContentRaw().split(" ");
		String user;
		if(message.length == 1) {
			user = e.getAuthor().getId();
			data = LoadData.loadData(e);
		} else {
			user = message[1];
			JSONParser parser = new JSONParser();
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + user + ".json")) {
				data = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				e.getMessage().reply("This save file does not exist!").mentionRepliedUser(false).queue();
				return;
			}
		}
		try {
			user = Objects.requireNonNull(e.getJDA().getUserById(user)).getName();
		} catch(Exception exception) {
			user = "Someone";
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.decode((String) data.get("color")))
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.setTitle(user + "'s Profile");
		builder.addField("General Stats", "Balance: " + Numbers.FormatNumber(data.get("violins")) + ":violin:\nBank Balance: " + Numbers.FormatNumber(data.get("bank")) + "/" + Numbers.FormatNumber((long) data.get("storage") * 20000000) + ":violin:\nLing Ling Medals: " + Numbers.FormatNumber(data.get("medals")) + ":military_medal:\nHourly Income: " + Numbers.FormatNumber(data.get("income")) + ":violin:/hour", false);
		builder.addField("Medals", ":first_place:" + Numbers.FormatNumber(data.get("firstPlace")) + "\n:second_place:" + Numbers.FormatNumber(data.get("secondPlace")) + "\n:third_place:" + Numbers.FormatNumber(data.get("thirdPlace")), false);
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
}