package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CheckMedals {
    public static String[] Medals(GuildMessageReceivedEvent e, String[] data) {
        long medals = Long.parseLong(data[55]);
        if (!Boolean.parseBoolean(data[56]) && medals >= 5) {
            e.getChannel().sendMessage("You achieved 5:military_medal:!  Congratulations!  You have received an extra 8 000 hourly income!").queue();
            data[56] = "true";
        } else if (!Boolean.parseBoolean(data[57]) && medals >= 10) {
            e.getChannel().sendMessage("You achieved 10:military_medal:!  Congratulations!  You now have 2x income from most commands!").queue();
            data[57] = "true";
        } else if (!Boolean.parseBoolean(data[58]) && medals >= 15) {
            e.getChannel().sendMessage("You have achieved 15:military_medal:!  Congratulations!  You now have an extra 5% gambling multiplier!").queue();
            data[58] = "true";
        } else if (!Boolean.parseBoolean(data[59]) && medals >= 25) {
            e.getChannel().sendMessage("You have achieved 25:military_medal:!  Congratulations!  You now have a 2.5% greater chance to succeed at robbing another user!  Note that this does **NOT** circumvent Ling Ling Insurance").queue();
            data[59] = "true";
        } else if (!Boolean.parseBoolean(data[60]) && medals >= 35) {
            e.getChannel().sendMessage("You have achieved 35:military_medal:!  Congratulations!  You now have a Steal Shield, which protects an additional 50% of :violin: from being robbed!").queue();
            data[60] = "true";
        } else if (!Boolean.parseBoolean(data[61]) && medals >= 50) {
            e.getChannel().sendMessage("You have achieved 50:military_medal:!  Congratulations!  You now have a Violin Duplicator, which doubles all violins robbed from other users!").queue();
            data[61] = "true";
        }
        return data;
    }
}