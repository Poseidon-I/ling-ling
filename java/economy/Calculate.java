package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class Calculate {
    public static long CalculateAmount(GuildMessageReceivedEvent e, String[] data, long base) {
        base *= Math.pow(1.05, Integer.parseInt(data[2]));
        base *= Math.pow(1.1, Integer.parseInt(data[43]));
        if (e.getGuild().getId().equals("670725611207262219")) {
            if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("755910677075460206"))) {
                base *= 1.1;
            }
            if(e.getMember().getRoles().contains(e.getGuild().getRoleById("852752096733429781"))) {
                base *= 1.3;
            }
            if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("734697410273607751"))) {
                base *= 1.25;
            } else if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("845121274958184499"))) {
                base *= 1.2;
            } else if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("845121187741958166"))) {
                base *= 1.15;
            } else if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("734697411074719765"))) {
                base *= 1.11;
            } else if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("734697411783688245"))) {
                base *= 1.075;
            } else if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("734697412865818645"))) {
                base *= 1.045;
            } else if (Objects.requireNonNull(e.getMember()).getRoles().contains(e.getGuild().getRoleById("734697413901680691"))) {
                base *= 1.02;
            }
        }
        if (Boolean.parseBoolean(data[57])) {
            base *= 2;
        }
        return base;
    }
}