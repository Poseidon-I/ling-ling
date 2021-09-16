package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class Calculate {
	public static long CalculateAmount(GuildMessageReceivedEvent e, String[] data, long base) {
		base *= Math.pow(1.05, Long.parseLong(data[2]));
		base *= Math.pow(1.1, Long.parseLong(data[43]));
		base *= Math.pow(1.3, Long.parseLong(data[57]));
		if(e.getGuild().getId().equals("670725611207262219")) {
			if(Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("755910677075460206"))) {
				base *= 1.1;
			}
		}
		if(data[96].equals("true")) {
			base *= 1.3;
		}
		base *= Double.parseDouble(data[97]);
		return base;
	}
}