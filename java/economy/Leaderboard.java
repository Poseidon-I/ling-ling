package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Leaderboard {
	public Leaderboard(String emoji, String what, GuildMessageReceivedEvent e, int dataPosition, long userNum) {
		BufferedReader reader;
		File directory = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
		File[] files = directory.listFiles();
		String[] entry = new String[0];
		long place = 1;
		if(files != null) {
			entry = new String[]{e.getAuthor().getAsMention() + ": " + userNum + " " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n"};
			for(File file : files) {
				String currentData;
				String user;
				int pos;
				try {
					reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
					currentData = reader.readLine();
					reader.close();
					if(currentData.equals("BANNED")) {
						continue;
					}
				} catch(Exception exception) {
					continue;
				}
				user = file.getName();
				pos = user.lastIndexOf(".");
				user = user.substring(0, pos);
				String[] temp = currentData.split(" ");
				long num;
				try {
					num = Long.parseLong(temp[dataPosition]);
				} catch(Exception exception) {
					num = (long) Double.parseDouble(temp[dataPosition]);
				}
				if(num == 0) {
					continue;
				}
				for(int i = 0; i < 10; i++) {
					if(num > Long.parseLong(entry[i].split(" ")[1]) && !user.equals(e.getAuthor().getId())) {
						System.arraycopy(entry, i, entry, i + 1, 9 - i);
						entry[i] = "<@" + user + ">: " + num + " " + emoji + "\n";
						if(num > userNum) {
							place++;
						}
						break;
					}
				}
			}
		}
		StringBuilder board = new StringBuilder();
		for(int i = 0; i < 10; i++) {
			if(entry[i].contains("<@0>")) {
				break;
			}
			try {
				String id = entry[i].split(" ")[0];
				User user = e.getJDA().getUserById(id.substring(2, id.length() - 2));
				assert user != null;
				board.append("**").append(i + 1).append(". ").append(user.getName()).append("**#").append(user.getDiscriminator()).append(" `").append(user.getId()).append("`: ").append(entry[i].split(" ")[1]).append(" ").append(emoji).append("\n");
			} catch(Exception exception) {
				board.append("**").append(entry[i]);
			}
		}
		if(place >= 11) {
			board.append("\n**").append(place).append(". You**: ").append(userNum).append(" ").append(emoji);
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.setTitle("__**Global Leaderboard**__")
				.addField("**" + what + " in the World**", board.toString(), false);
		e.getChannel().sendMessageEmbeds(builder.build()).queue();
	}
}