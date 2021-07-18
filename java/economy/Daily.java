package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Daily {
    public Daily(GuildMessageReceivedEvent e, String[] data) {
        if (data[48].equals("true")) {
            e.getChannel().sendMessage("I can't give out violins that fast, wait until 00:00 UTC!").queue();
        } else {
            long streak = Long.parseLong(data[47]);
            /*if (time > Long.parseLong(data[49])) {
                e.getChannel().sendMessage("Oh no!  Your streak was reset!").queue();
                streak = 0;
            }*/
            long base = 100000 + (streak * 750);
            data[0] = String.valueOf(Long.parseLong(data[0]) + base);
            data[75] = String.valueOf(Long.parseLong(data[75]) + base);
            data[48] = "true";
            data[47] = String.valueOf(streak + 1);
            e.getChannel().sendMessage("You received a total of " + base + ":violin:, with " + streak * 750 + ":violin: coming from your " + streak + "-day streak!").queue();
            if (streak > Long.parseLong(data[74])) {
                data[74] = String.valueOf(streak);
            }
        }
        new SaveData(e, data);
    }
}
