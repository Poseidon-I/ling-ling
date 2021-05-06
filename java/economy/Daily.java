package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Daily {
    public Daily(GuildMessageReceivedEvent e, String[] data) {
        long time = System.currentTimeMillis();
        if (!e.getMessage().getContentRaw().contains("!d bump")) {
            if (time < Long.parseLong(data[48])) {
                long milliseconds = Long.parseLong(data[48]) - time;
                long hours = milliseconds / 3600000;
                milliseconds -= hours * 3600000;
                long minutes = milliseconds / 60000;
                milliseconds -= minutes * 60000;
                long seconds = milliseconds / 1000;
                milliseconds -= seconds * 1000;
                e.getChannel().sendMessage("I can't give out violins that fast, wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
            } else {
                long streak = Long.parseLong(data[47]);
                /*if (time > Long.parseLong(data[48])) {
                    e.getChannel().sendMessage("Oh no!  Your streak was reset!").queue();
                    streak = 0;
                }*/
                long base = 50000 + (streak * 500);
                data[0] = String.valueOf(Long.parseLong(data[0]) + base);
                data[75] = String.valueOf(Long.parseLong(data[75]) + base);
                data[48] = String.valueOf(time + 85500000);
                data[49] = String.valueOf(time + 172800000);
                data[47] = String.valueOf(Long.parseLong(data[47]) + 1);
                e.getChannel().sendMessage("You received a total of " + base + ":violin:, with " + (streak - 1) * 500 + ":violin: coming from your " + streak + "-day streak!").queue();
                if (streak > Long.parseLong(data[74])) {
                    data[74] = String.valueOf(streak);
                }
            }
        }
        new SaveData(e, data);
    }
}
