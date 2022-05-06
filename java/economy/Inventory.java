package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.awt.*;
import java.io.FileReader;
import java.util.Objects;

public class Inventory {
	public Inventory(MessageReceivedEvent e) {
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
				.setTitle(user + "'s Inventory")
				.addField("Rice :rice:", "Count: " + Numbers.FormatNumber(data.get("rice")) + "\nUsage: Gives you 2 hours of income.\nID: `rice`", true)
				.addField("Bubble Tea :bubble_tea:", "Count: " + Numbers.FormatNumber(data.get("tea")) + "\nUsage: Gives you 6 hours of income.\nID: `tea`", true)
				.addField("Ling Ling Blessing :angel:", "Count: " + Numbers.FormatNumber(data.get("blessings")) + "\nUsage: Gives you 24 hours of income and 1-3 Ling Ling Medals.\nID: `blessing`", true)
				.addField("Vote Box :ballot_box:", "Count: " + Numbers.FormatNumber(data.get("voteBox")) + "\nUsage: Gives you random items, as decided by RNGesus.\nID: `vote`", true)
				.addField("Gift Box :gift:", "Count: " + Numbers.FormatNumber(data.get("giftBox")) + "\nUsage: Gives you miniscully valuable random items, as decided by RNGesus.\nID: `gift`", true)
				.addField("Musician Kit <:musiciankit:947328030529961984>", "Count: " + Numbers.FormatNumber(data.get("kits")) + "\nUsage: Gives you somewhat valuable random items, as decided by RNGesus.\nID: `kit`", true)
				.addField("Ling Ling Box <:linglingbox:947328144027820052>", "Count: " + Numbers.FormatNumber(data.get("linglingBox")) + "\nUsage: Gives you valuable random items, as decided by RNGesus\nID: `llbox`", true)
				.addField("Crazy Person Box <:crazybox:946231980419342417>", "Count: " + Numbers.FormatNumber(data.get("crazyBox")) + "\nUsage: Gives you very valuable random items, as decided by RNGesus.\nID: `crazybox`", true)
				.addField("RNGesus Box <:rngesusbox:946888300608778321>", "Count: " + Numbers.FormatNumber(data.get("RNGesusBox")) + "\nUsage: Gives you EXTREMELY valuable random items, as decided by RNGesus.\nID: `rngesus`", true);
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
}