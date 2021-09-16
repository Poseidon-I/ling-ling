package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

public class Inventory {
	public Inventory(GuildMessageReceivedEvent e) {
		String[] data = LoadData.loadData(e, "Economy Data");
		String[] message = e.getMessage().getContentRaw().split(" ");
		if(message.length == 1) {
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
					.setTitle(e.getAuthor().getName() + "'s Inventory")
					.addField("Rice :rice:", "Count: " + data[51] + "\nUsage: Gives you 2 hours of income.\nID: `rice`", true)
					.addField("Bubble Tea :bubble_tea:", "Count: " + data[62] + "\nUsage: Gives you 6 hours of income.\nID: `tea`", true)
					.addField("Ling Ling Blessing :angel:", "Count: " + data[63] + "\nUsage: Gives you 24 hours of income and 1-3 Ling Ling Medals.\nID: `blessing`", true)
					.addField("Vote Box :ballot_box:", "Count: " + data[90] + "\nUsage: Gives you random items, as decided by RNGesus.\nID: `vote`", true)
					.addField("Gift Box :gift:", "Count: " + data[87] + "\nUsage: Gives you semi-valuable random items, as decided by RNGesus.\nID: `gift`", true);
			if(data[91].equals("0") && data[92].equals("0") && data[93].equals("0")) {
				builder.addField("???", "Count: 0\nUsage: ???", true);
			} else {
				builder.addField("**Donator Boxes**", data[91] + " Standard Musician Kits\n" + data[92] + " Ling Ling Boxes\n" + data[93] + " Crazy Person Boxes\nUsage: Gives you valuable random items, as decided by RNGesus.  Ling Ling Medals drop once per day, with a limit of 5.\nID: `kit` `llbox` `crazybox`", true);
			}
			e.getChannel().sendMessageEmbeds(builder.build()).queue();
		} else {
			User target = Objects.requireNonNull(e.getJDA().getUserById(message[1]));
			try {
				BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + target.getId() + ".txt"));
				String[] line = reader.readLine().split(" ");
				EmbedBuilder builder = new EmbedBuilder()
						.setColor(Color.BLUE)
						.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
						.setTitle(target.getName() + "'s Inventory")
						.addField("Rice :rice:", "Count: " + line[51], false)
						.addField("Bubble Tea :bubble_tea:", "Count: " + line[62], false)
						.addField("Ling Ling Blessing :angel:", "Count: " + line[63], false)
						.addField("Gift Box :gift:", "Count: " + line[87], false)
						.addField("Vote Box :ballot_box:", "Count: " + line[90], false);
				if(data[91].equals("0") && data[92].equals("0") && data[93].equals("0")) {
					builder.addField("???", "Count: ?", false);
				} else {
					builder.addField("**Donator Boxes**", line[91] + " Standard Musician Kits\n" + line[92] + " Ling Ling Boxes\n" + line[93] + " Crazy Person Boxes", true);
				}
				e.getChannel().sendMessageEmbeds(builder.build()).queue();
				reader.close();
			} catch(Exception exception) {
				e.getChannel().sendMessage("This save file does not exist!").queue();
			}
		}
	}
}