package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

public class Stats {
	public Stats(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		if(message.length == 1) {
			String[] data = LoadData.loadData(e, "Economy Data");
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
					.setTitle(e.getAuthor().getName() + "'s Stats");
			builder.addField("**__Gambling__**", "**Net Winnings**: " + data[66] + "\n**Million-Dollar Tickets Drawn**: " + data[67], false);
			builder.addField("**__Robbing__**", "**Amount Earned from Robbing**: " + data[68] + "\n**Amount Lost to Robbers**: " + data[69], false);
			builder.addField("**__Commands__**", "**Hours Practised**: " + data[71] + "\n**Scales Played**: " + data[70] + "\n**Rehearsals Attended**: " + data[72] + "\n**Performances Given**: " + data[73] + "\n**Hours Taught**: " + data[76], false);
			builder.addField("**__Lootboxes__**", "**Gifts Given**: " + data[85] + "\n**Gifts Received**: " + data[86] + "\n**Number of Votes**: " + data[88], false);
			builder.addField("**__Miscellaneous__**", "**Highest Daily Streak**: " + data[74] + "\n**Luthiers Unscrambled**: " + data[77] + "\n**Violins Earned**: " + data[75] + "\n**Interest Earned**: " + data[10] + "\n**Penalties Paid**: " + data[11], false);
			e.getChannel().sendMessageEmbeds(builder.build()).queue();
		} else {
			User user = Objects.requireNonNull(e.getJDA().getUserById(message[1]));
			try {
				BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt"));
				String[] line = reader.readLine().split(" ");
				EmbedBuilder builder = new EmbedBuilder()
						.setColor(Color.BLUE)
						.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
						.setTitle(user.getName() + "'s Stats");
				builder.addField("**__Gambling__**", "**Net Winnings**: " + line[66] + "\n**Million-Dollar Tickets Drawn**: " + line[67], false);
				builder.addField("**__Robbing__**", "**Amount Earned from Robbing**: " + line[68] + "\n**Amount Lost to Robbers**: " + line[69], false);
				builder.addField("**__Commands__**", "**Hours Practised**: " + line[71] + "\n**Scales Played**: " + line[70] + "\n**Rehearsals Attended**: " + line[72] + "\n**Performances Given**: " + line[73] + "\n**Hours Taught: **" + line[76], false);
				builder.addField("**__Lootboxes__**", "**Gifts Given**: " + line[85] + "\n**Gifts Received**: " + line[86] + "\n**Number of Votes**: " + line[88], false);
				builder.addField("**__Miscellaneous__**", "**Highest Daily Streak**: " + line[74] + "\n**Luthiers Unscrambled**: " + line[77] + "\n**Violins Earned**: " + line[75] + "\n**Interest Earned**: " + line[10] + "\n**Penalties Paid**: " + line[11], false);
				e.getChannel().sendMessageEmbeds(builder.build()).queue();
				reader.close();
			} catch(Exception exception) {
				e.getChannel().sendMessage("This save file does not exist!").queue();
			}
		}
	}
}
