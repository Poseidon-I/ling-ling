package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.awt.*;
import java.io.FileReader;
import java.util.Objects;

public class Balance {
	public static void balance(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data;
		String user;
		try {
			user = Objects.requireNonNull(e.getOption("otheruser")).getAsString();
		} catch(NullPointerException exception) {
			user = e.getUser().getId();
		}
		
		JSONParser parser = new JSONParser();
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + user + ".json")) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.reply("This save file does not exist!").queue();
			return;
		}
		if(user.equals("768056391814086676")) {
			user = "**NARWHAL**";
		} else {
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(user)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.decode((String) data.get("color")))
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.setTitle(user + "'s Profile");
		builder.addField("General Stats", "Balance: " + Numbers.formatNumber(data.get("violins")) + Emoji.VIOLINS + "\nBank Balance: " + Numbers.formatNumber(data.get("bank")) + "/" + Numbers.formatNumber((long) data.get("storage") * 20000000) + Emoji.VIOLINS + "\nLing Ling Medals: " + Numbers.formatNumber(data.get("medals")) + Emoji.MEDALS + "\nHourly Income: " + Numbers.formatNumber(data.get("income")) + Emoji.VIOLINS + "/hour", false);
		builder.addField("Medals", Emoji.FIRST_PLACE + Numbers.formatNumber(data.get("firstPlace")) + "\n" + Emoji.SECOND_PLACE + Numbers.formatNumber(data.get("secondPlace")) + "\n" + Emoji.THIRD_PLACE + Numbers.formatNumber(data.get("thirdPlace")), false);
		e.replyEmbeds(builder.build()).queue();
	}
}