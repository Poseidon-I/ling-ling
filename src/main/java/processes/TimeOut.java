package processes;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.time.Duration;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.SECONDS;
// BEETHOVEN-ONLY CLASS
public class TimeOut {
	public static void timeOut(GenericDiscordEvent e) {
		MessageEmbed embed = e.getMessage().getEmbeds().get(0);
		if(Objects.requireNonNull(embed.getTitle()).contains("unmute | case")) {
			String[] string = Objects.requireNonNull(embed.getDescription()).split("\n");
			User user = e.getJDA().getUserById(string[0].substring(string[0].lastIndexOf('@') + 1, string[0].lastIndexOf('>')));
			assert user != null;
			Member member = e.getGuild().getMember(user);
			assert member != null;
			member.removeTimeout().queue();
		} else if(embed.getTitle().contains("mute | case")) {
			String[] string = Objects.requireNonNull(embed.getDescription()).split("\n");
			User user = e.getJDA().getUserById(string[0].substring(string[0].lastIndexOf('@') + 1, string[0].lastIndexOf('>')));
			assert user != null;
			Member member = e.getGuild().getMember(user);
			String[] time = string[1].split(" ");
			int duration = 0;
			if(embed.getDescription().contains("Automatic action carried out for hitting the message rate limit")) {
				duration += 300;
			} else if(embed.getDescription().contains("Automatic action carried out for posting links")) {
				duration += 300;
			} else if(embed.getDescription().contains("Automatic action carried out for spamming mentions")) {
				duration += 300;
			} else if(embed.getDescription().contains("Automatic action carried out after their")) {
				duration += 43200;
			} else if(embed.getDescription().contains("Automatic action carried out for using a blacklisted word")) {
				duration += 604800;
			} else {
				for(int i = 2; i < time.length; i += 2) {
					if(time[i].contains("year")) {
						duration += 31536000 * Integer.parseInt(time[i - 1]);
					} else if(time[i].contains("month")) {
						duration += 2592000 * Integer.parseInt(time[i - 1]);
					} else if(time[i].contains("day")) {
						duration += 86400 * Integer.parseInt(time[i - 1]);
					} else if(time[i].contains("hour")) {
						duration += 3600 * Integer.parseInt(time[i - 1]);
					} else if(time[i].contains("minute")) {
						duration += 60 * Integer.parseInt(time[i - 1]);
					} else if(time[i].contains("second")) {
						duration += Integer.parseInt(time[i - 1]);
					}
				}
			}
			assert member != null;
			if(member.isTimedOut()) {
				long remainingFromPrevious = Objects.requireNonNull(member.getTimeOutEnd()).toEpochSecond() - System.currentTimeMillis() / 1000;
				member.timeoutFor(Duration.of(duration + remainingFromPrevious, SECONDS)).reason(string[2].substring(string[2].indexOf(" ") + 1) +
						"\nIn addition to remaining " + remainingFromPrevious + " seconds from previous timeout.").queue();
			} else {
				member.timeoutFor(Duration.of(duration, SECONDS)).reason(string[2].substring(string[2].indexOf(" ") + 1)).queue();
			}
		}
	}
}