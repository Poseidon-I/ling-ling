package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.FileReader;
import java.util.Objects;

public class Balance {
	public Balance(GuildMessageReceivedEvent e) {
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
				e.getChannel().sendMessage("This save file does not exist!").queue();
				throw new IllegalArgumentException();
			}
		}
		try {
			user = Objects.requireNonNull(e.getJDA().getUserById(user)).getName();
		} catch(Exception exception) {
			user = "Someone";
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.setTitle(user + "'s Profile");
		builder.addField("General Stats", "Balance: " + data.get("violins") + ":violin:\nBank Balance: " + data.get("bank") + "/" + (long) data.get("storage") * 15000000 + ":violin:\nLing Ling Medals: " + data.get("medals") + ":military_medal:\nHourly Income: " + data.get("income") + ":violin:/hour", false);
		builder.addField("Medals", ":first_place:" + data.get("firstPlace") + "\n:second_place:" + data.get("secondPlace") + "\n:third_place:" + data.get("thirdPlace"), false);
		e.getChannel().sendMessageEmbeds(builder.build()).queue();
	}
}