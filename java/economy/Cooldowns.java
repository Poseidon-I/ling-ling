package economy;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONObject;

import java.awt.*;

public class Cooldowns {
	public static String reformat(long string) {
		String newString = String.valueOf(string);
		if(String.valueOf(string).length() == 1) {
			newString = "0" + string;
		}
		return newString;
	}
	
	public static String reformatMilliseconds(long string) {
		String newString = String.valueOf(string);
		if(String.valueOf(string).length() == 1) {
			newString = "00" + string;
		} else if(String.valueOf(string).length() == 2) {
			newString = "0" + string;
		}
		return newString;
	}
	
	public static void cooldowns(GenericDiscordEvent e) {
		try {
			JSONObject data = LoadData.loadData(e);
			long time = System.currentTimeMillis();
			EmbedBuilder builder = new EmbedBuilder()
					.setColor(Color.decode((String) data.get("color")))
					.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
			EmbedBuilder builder2 = new EmbedBuilder()
					.setColor(Color.decode((String) data.get("color")))
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
				builder.addField("**Rob**", "`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
			}
			
			//scales
			milliseconds = (long) data.get("scaleCD") - time;
			if(milliseconds < 0) {
				builder.addField("**Scales**", ":white_check_mark:", true);
			} else {
				seconds = milliseconds / 1000;
				milliseconds -= seconds * 1000;
				builder.addField("**Scales**", "`" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
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
				builder.addField("**Practice**", "`" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
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
					builder.addField("**Teach**", "`" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
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
					builder.addField("**Rehearse**", "`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
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
				builder.addField("**Perform**", "`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
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
				builder.addField("**Daily**", "`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
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
				builder.addField("**Gift**", "`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
			} else {
				builder.addField("**Gift**", ":white_check_mark:", true);
			}
			
			//claim
			milliseconds = (long) data.get("voteCD") - time;
			if(milliseconds < 0) {
				builder.addField("**Claim**", ":white_check_mark:", true);
			} else {
				hours = milliseconds / 3600000;
				milliseconds -= hours * 3600000;
				minutes = milliseconds / 60000;
				milliseconds -= minutes * 60000;
				seconds = milliseconds / 1000;
				milliseconds -= seconds * 1000;
				builder.addField("**Claim**", "`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
			}
			
			//income - rosin
			milliseconds = (long) data.get("rosinExpire") - time;
			if(milliseconds < 0) {
				builder2.addField("**Rosin Income**", ":x:", true);
			} else {
				hours = milliseconds / 3600000;
				milliseconds -= hours * 3600000;
				minutes = milliseconds / 60000;
				milliseconds -= minutes * 60000;
				seconds = milliseconds / 1000;
				milliseconds -= seconds * 1000;
				if(hours < 24) {
					builder2.addField("**Rosin Income**", ":warning:\n`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
				} else {
					builder2.addField("**Rosin Income**", ":white_check_mark:\n`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
				}
			}
			builder2.addBlankField(true);
			
			//income - strings
			milliseconds = (long) data.get("stringsExpire") - time;
			if(milliseconds < 0) {
				builder2.addField("**Strings Income**", ":x:", true);
			} else {
				hours = milliseconds / 3600000;
				milliseconds -= hours * 3600000;
				minutes = milliseconds / 60000;
				milliseconds -= minutes * 60000;
				seconds = milliseconds / 1000;
				milliseconds -= seconds * 1000;
				if(hours < 24) {
					builder2.addField("**Strings Income**", ":warning:\n`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
				} else {
					builder2.addField("**Strings Income**", ":white_check_mark:\n`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
				}
			}
			
			//income - bow hairs
			milliseconds = (long) data.get("bowHairExpire") - time;
			if(milliseconds < 0) {
				builder2.addField("**Bow Hair Income**", ":x:", true);
			} else {
				hours = milliseconds / 3600000;
				milliseconds -= hours * 3600000;
				minutes = milliseconds / 60000;
				milliseconds -= minutes * 60000;
				seconds = milliseconds / 1000;
				milliseconds -= seconds * 1000;
				if(hours < 24) {
					builder2.addField("**Bow Hair Income**", ":warning:\n`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
				} else {
					builder2.addField("**Bow Hair Income**", ":white_check_mark:\n`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
				}
			}
			builder2.addBlankField(true);
			
			//income - service
			milliseconds = (long) data.get("serviceExpire") - time;
			if(milliseconds < 0) {
				builder2.addField("**Violin Service Income**", ":x:", true);
			} else {
				hours = milliseconds / 3600000;
				milliseconds -= hours * 3600000;
				minutes = milliseconds / 60000;
				milliseconds -= minutes * 60000;
				seconds = milliseconds / 1000;
				milliseconds -= seconds * 1000;
				if(hours < 24) {
					builder2.addField("**Violin Service Income**", ":warning:\n`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
				} else {
					builder2.addField("**Violin Service Income**", ":white_check_mark:\n`" + reformat(hours) + ":" + reformat(minutes) + ":" + reformat(seconds) + "." + reformatMilliseconds(milliseconds) + "`", true);
				}
			}
			
			builder.setTitle("__**Command Cooldowns**__");
			builder2.setTitle("__**Time Before Hourly Income Penalty**__");
			e.replyTwoEmbeds(builder.build(), builder2.build());
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}