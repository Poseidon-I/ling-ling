package dev;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Objects;

public class Ban {
	public Ban(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		StringBuilder reason = new StringBuilder();
		for(int i = 2; i < message.length; i++) {
			reason.append(" ").append(message[i]);
		}
		e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
		User user = null;
		try {
			user = e.getJDA().getUserById(message[1]);
		} catch(Exception exception1) {
			e.getChannel().sendMessage("You tried to blacklist a non-existant user.  You should know better smh.").queue();
		}
		assert user != null;
		if(user.getId().equals(e.getAuthor().getId())) {
			e.getChannel().sendMessage("Imagine trying to ban yourself, how dumb are you???").queue();
		} else if(user.getId().equals("619989388109152256") || user.getId().equals("488487157372157962")) {
			e.getChannel().sendMessage("Imagine trying to ban a developer smh").queue();
		} else {
			try {
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt")));
				writer.print("BANNED");
				writer.close();
			} catch(Exception exception) {
				File file = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt");
				try {
					file.createNewFile();
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
					writer.print("BANNED");
					writer.close();
				} catch(Exception exception1) {
					//nothing here lol
				}
			}
			e.getChannel().sendMessage(":hammer: " + user.getName() + " was successfully banned!").queue();
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
					.addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
					.setTitle("__**BAN Info**__");
			Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
			user.openPrivateChannel().complete().sendMessage("You were banned for" + reason + ".").queue();
		}
	}
}