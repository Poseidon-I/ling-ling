package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

public class Balance {
	public Balance(GuildMessageReceivedEvent e) {
		String[] data = LoadData.loadData(e, "Economy Data");
		String[] bankData = LoadData.loadData(e, "Bank Data");
		String[] message = e.getMessage().getContentRaw().split(" ");
		if(message.length == 1) {
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
					.setTitle(e.getAuthor().getName() + "'s Profile");
			builder.addField("General Stats", "Balance: " + data[0] + ":violin:\nBank Balance: " + bankData[0] + "/" + Long.parseLong(bankData[1]) * 15000000 + ":violin:\nLing Ling Medals: " + data[55] + ":military_medal:\nHourly Income: " + data[12] + ":violin:/hour", false);
			builder.addField("Medals", ":first_place:" + data[54] + "\n:second_place:" + data[53] + "\n:third_place:" + data[52], false);
			e.getChannel().sendMessageEmbeds(builder.build()).queue();
		} else {
			User user = Objects.requireNonNull(e.getJDA().getUserById(message[1]));
			try {
				BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt"));
				String[] line = reader.readLine().split(" ");
				reader.close();
				reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Bank Data\\" + user.getId() + ".txt"));
				String[] bankline = reader.readLine().split(" ");
				reader.close();
				EmbedBuilder builder = new EmbedBuilder()
						.setColor(Color.BLUE)
						.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
						.setTitle(user.getName() + "'s Profile")
						.addField("General Stats", "Balance: " + line[0] + ":violin:\nBank Balance: " + bankline[0] + "\nLing Ling Medals: " + line[55] + ":military_medal:\nHourly Income: " + line[12] + ":violin:/hour", false)
						.addField("Medals", ":first_place:" + line[54] + "\n:second_place:" + line[53] + "\n:third_place:" + line[52], false);
				e.getChannel().sendMessageEmbeds(builder.build()).queue();
			} catch(Exception exception) {
				e.getChannel().sendMessage("This save file does not exist!").queue();
			}
		}
	}
}