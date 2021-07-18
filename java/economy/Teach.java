package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;
import java.util.Random;

public class Teach {
    public Teach(GuildMessageReceivedEvent e, String[] data) {
        if(Boolean.parseBoolean(data[78])) {
            long time = System.currentTimeMillis();
            Random random = new Random();
            if (time < Long.parseLong(data[79])) {
                long milliseconds = Long.parseLong(data[79]) - time;
                long minutes = milliseconds / 60000;
                milliseconds -= minutes * 60000;
                long seconds = milliseconds / 1000;
                milliseconds -= seconds * 1000;
                e.getChannel().sendMessage("Chill, you can't teach two students at once!  Wait "+ minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
            } else {
                long base = random.nextInt(10001) + 35000;
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
                base *= Math.pow(1.2, Long.parseLong(data[81]));
                base *= Math.pow(1.125, Long.parseLong(data[82]));
                base *= Math.pow(1.075, Long.parseLong(data[80]));
                if(data[84].equals("true")) {
                    base *= 2;
                    e.getChannel().sendMessage("You taught a student for an hour and earned " + base + ":violin:").queue();
                    data[76] = String.valueOf(Double.parseDouble(data[76]) + 1);
                } else {
                    e.getChannel().sendMessage("You taught a student for a half-hour and earned " + base + ":violin:").queue();
                    data[76] = String.valueOf(Double.parseDouble(data[76]) + 0.5);
                }
                data[0] = String.valueOf(Long.parseLong(data[0]) + base);
                data[79] = String.valueOf(time + 3540000);
                data[75] = String.valueOf(Long.parseLong(data[75]) + base);
                new SaveData(e, data);
            }
        } else {
            e.getChannel().sendMessage("You must be certified to teach before you can use this command!").queue();
        }
    }
}
