package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class Cooldowns {
	public static String Reformat(long string) {
		String newString = string + "";
		if(String.valueOf(string).length() == 1) {
			newString = "0" + string;
		}
		return newString;
	}
	
	public static String ReformatMilliseconds(long string) {
		String newString = string + "";
		if(String.valueOf(string).length() == 1) {
			newString = "00" + string;
		} else if(String.valueOf(string).length() == 2) {
			newString = "0" + string;
		}
		return newString;
	}
	
	public Cooldowns(GuildMessageReceivedEvent e) {
		String[] data = LoadData.loadData(e, "Economy Data");
		long time = System.currentTimeMillis();
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
		//rob
		long milliseconds = Long.parseLong(data[5]) - time;
		long seconds;
		long minutes;
		long hours;
		if(milliseconds < 0) {
			builder.addField("**Rob**", ":white_check_mark:", true);
		} else {
			hours = milliseconds / 3600000;
			milliseconds -= hours * 3600000;
			minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			builder.addField("**Rob**", "`" + Reformat(hours) + ":" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", true);
		}
		
		//scales
		milliseconds = Long.parseLong(data[64]) - time;
		if(milliseconds < 0) {
			builder.addField("**Scales**", ":white_check_mark:", true);
		} else {
			seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			builder.addField("**Scales**", "`" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", true);
		}
		
		//practice
		milliseconds = Long.parseLong(data[1]) - time;
		if(milliseconds < 0) {
			builder.addField("**Practice**", ":white_check_mark:", true);
		} else {
			minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			builder.addField("**Practice**", "`" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", true);
		}
		
		//teach
		if(Boolean.parseBoolean(data[78])) {
			milliseconds = Long.parseLong(data[79]) - time;
			if(milliseconds < 0) {
				builder.addField("**Teach**", ":white_check_mark:", true);
			} else {
				hours = milliseconds / 3600000;
				milliseconds -= hours * 3600000;
				minutes = milliseconds / 60000;
				milliseconds -= minutes * 60000;
				seconds = milliseconds / 1000;
				milliseconds -= seconds * 1000;
				builder.addField("**Teach**", "`" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", true);
			}
		}
		
		//rehearse
		if(Boolean.parseBoolean(data[19])) {
			milliseconds = Long.parseLong(data[7]) - time;
			if(milliseconds < 0) {
				builder.addField("**Rehearse**", ":white_check_mark:", true);
			} else {
				hours = milliseconds / 3600000;
				milliseconds -= hours * 3600000;
				minutes = milliseconds / 60000;
				milliseconds -= minutes * 60000;
				seconds = milliseconds / 1000;
				milliseconds -= seconds * 1000;
				builder.addField("**Rehearse**", "`" + Reformat(hours) + ":" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", true);
			}
		}
		
		//perform
		milliseconds = Long.parseLong(data[8]) - time;
		if(milliseconds < 0) {
			builder.addField("**Perform**", ":white_check_mark:", true);
		} else {
			hours = milliseconds / 3600000;
			milliseconds -= hours * 3600000;
			minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			builder.addField("**Perform**", "`" + Reformat(hours) + ":" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", true);
		}
		
		//daily
		if(Boolean.parseBoolean(data[48])) {
			long temp = System.currentTimeMillis() % 86400000;
			milliseconds = 86400000 - temp;
			hours = milliseconds / 3600000;
			milliseconds -= hours * 3600000;
			minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			builder.addField("**Daily**", "`" + Reformat(hours) + ":" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", true);
		} else {
			builder.addField("**Daily**", ":white_check_mark:", true);
		}
		
		//gift
		if(Boolean.parseBoolean(data[49])) {
			long temp = System.currentTimeMillis() % 86400000;
			milliseconds = 86400000 - temp;
			hours = milliseconds / 3600000;
			milliseconds -= hours * 3600000;
			minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			builder.addField("**Gift**", "`" + Reformat(hours) + ":" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", true);
		} else {
			builder.addField("**Gift**", ":white_check_mark:", true);
		}
		
		//vote
		milliseconds = Long.parseLong(data[89]) - time + 3600000;
		if(milliseconds < 0) {
			builder.addField("**Vote**", ":white_check_mark:\n`WARNING: May be inaccurate`", true);
		} else {
			hours = milliseconds / 3600000;
			milliseconds -= hours * 3600000;
			minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			builder.addField("**Vote**", "`" + Reformat(hours) + ":" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`\n`WARNING: May be inaccurate`", true);
		}
		builder.setTitle("__**Cooldowns**__");
		e.getChannel().sendMessageEmbeds(builder.build()).queue();
	}
}