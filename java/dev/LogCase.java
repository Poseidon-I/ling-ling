package dev;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Objects;

public class LogCase {
	public static void logCase(@NotNull SlashCommandInteractionEvent e, String type, String id, String reason) {
		File file = new File("Ling Ling Bot Data\\Moderation\\" + id);
		if(!file.exists()) {
			file.mkdirs();
		}
		int caseNum = Objects.requireNonNull(file.listFiles()).length;
		String user;
		try {
			user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName() + "#" + Objects.requireNonNull(e.getJDA().getUserById(id)).getDiscriminator();
		} catch(Exception exception) {
			user = "<@" + id + ">";
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.addField("Moderator: " + e.getUser().getName(), "User: " + user + "\nReason: " + reason, false)
				.setTitle("__**Case " + caseNum + ": " + type + "**__");
		switch(type) {
			case "Warn" -> builder.setColor(Color.YELLOW);
			case "Save Reset" -> builder.setColor(Color.RED);
			case "BAN" -> builder.setColor(Color.BLACK);
			case "Unban" -> builder.setColor(Color.GREEN);
		}
		Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("1029498872441077860")).sendMessageEmbeds(builder.build()).queue();
		JSONObject data = new JSONObject();
		data.put("user", id);
		data.put("moderator", e.getUser().getName());
		data.put("type", type);
		data.put("reason", reason);
		try(FileWriter writer = new FileWriter(file.getAbsolutePath() + "\\" + caseNum + ".json")) {
			writer.write(data.toJSONString());
			writer.close();
		} catch(Exception exception) {
			e.getChannel().sendMessage("Something went terribly wrong!").queue();
		}
	}
}