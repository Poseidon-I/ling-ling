package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class Teach {
    public Teach(GuildMessageReceivedEvent e, String[] data) {
        if(Boolean.parseBoolean(data[78])) {
            long time = System.currentTimeMillis();
            Random random = new Random();
            if (time < Long.parseLong(data[79])) {
                long milliseconds = Long.parseLong(data[64]) - time;
                long seconds = milliseconds / 1000;
                milliseconds -= seconds * 1000;
                e.getChannel().sendMessage("Chill, you can't teach two students at once!  Wait " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
            } else {
                long base = random.nextInt(10001) + 45000;
                base *= Math.pow(1.2, Long.parseLong(data[81]));
                base *= Math.pow(1.125, Long.parseLong(data[82]));
                base *= Math.pow(1.075, Long.parseLong(data[80]));
                if(data[84].equals("true")) {
                    base *= 2;
                    e.getChannel().sendMessage("You taught a student for an hour and earned " + base + ":violin:").queue();
                    data[76] = String.valueOf(Long.parseLong(data[76]) + 1);
                } else {
                    e.getChannel().sendMessage("You taught a student for a half-hour and earned " + base + ":violin:").queue();
                    data[76] = String.valueOf(Long.parseLong(data[76]) + 0.5);
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
