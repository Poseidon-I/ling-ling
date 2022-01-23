package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;

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
	
	public Cooldowns(MessageReceivedEvent e) {
		try {
			JSONObject data = LoadData.loadData(e);
			long time = System.currentTimeMillis();
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
			//rob
			long milliseconds = (long) data.get("robCD") - time;
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
			milliseconds = (long) data.get("scaleCD") - time;
			if(milliseconds < 0) {
				builder.addField("**Scales**", ":white_check_mark:", true);
			} else {
				seconds = milliseconds / 1000;
				milliseconds -= seconds * 1000;
				builder.addField("**Scales**", "`" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", true);
			}
			
			//practice
			milliseconds = (long) data.get("practiceCD") - time;
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
			if((boolean) data.get("certificate")) {
				milliseconds = (long) data.get("teachCD") - time;
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
			if((boolean) data.get("orchestra")) {
				milliseconds = (long) data.get("rehearseCD") - time;
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
			milliseconds = (long) data.get("performCD") - time;
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
			if((boolean) data.get("hadDailyToday")) {
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
			if((boolean) data.get("hadGiftToday")) {
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
			milliseconds = (long) data.get("voteCD") - time + 3600000;
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
			e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}