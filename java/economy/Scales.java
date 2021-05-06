package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class Scales {
    public Scales(GuildMessageReceivedEvent e, String[] data) {
        long time = System.currentTimeMillis();
        Random random = new Random();
        if (time < Long.parseLong(data[64])) {
            long milliseconds = Long.parseLong(data[64]) - time;
            long seconds = milliseconds / 1000;
            milliseconds -= seconds * 1000;
            e.getChannel().sendMessage("Chill, you can't play two scales at once!  Wait " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
        } else {
            long base = Calculate.CalculateAmount(e, data, random.nextInt(11) + 10);
            data[0] = String.valueOf(Long.parseLong(data[0]) + base);
            if (Boolean.parseBoolean(data[50])) {
                data[64] = String.valueOf(time + 64500);
            } else {
                data[64] = String.valueOf(time + 89500);
            }
            e.getChannel().sendMessage("You played your scales and earned " + base + ":violin:").queue();
            data[70] = String.valueOf(Long.parseLong(data[70]) + 1);
            data[75] = String.valueOf(Long.parseLong(data[75]) + base);
            new SaveData(e, data);
        }
    }
}