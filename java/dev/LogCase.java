package dev;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Objects;

public class LogCase {
	public static void logCase(GenericDiscordEvent e, String type, String id, String reason) {
		/* File file = new File("Ling Ling Bot Data\\Moderation\\" + id);
		if(!file.exists()) {
			file.mkdirs();
		} */
		// int caseNum = Objects.requireNonNull(file.listFiles()).length;

		//TODO - overhaul moderation system
		String user;
		try {
			user = Objects.requireNonNull(e.getJDA().getUserById(id)).getName();
		} catch(Exception exception) {
			user = "<@" + id + ">";
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
				.addField("Moderator: " + e.getAuthor().getName(), "User: " + user + "\nReason: " + reason, false)
				.setTitle("__**Case UNDEFINED: " + type + "**__");
		switch(type) {
			case "Warn" -> builder.setColor(Color.YELLOW);
			case "Save Reset" -> builder.setColor(Color.RED);
			case "BAN" -> builder.setColor(Color.BLACK);
			case "Unban" -> builder.setColor(Color.GREEN);
		}
		/*
		Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("1029498872441077860")).sendMessageEmbeds(builder.build()).queue();
		JSONObject data = new JSONObject();
		data.put("user", id);
		data.put("moderator", e.getAuthor().getName());
		data.put("type", type);
		data.put("reason", reason);
		try(FileWriter writer = new FileWriter(file.getAbsolutePath() + "\\" + caseNum + ".json")) {
			writer.write(data.toJSONString());
			writer.close();
		} catch(Exception exception) {
			// nothing here lol
		} */
	}
}