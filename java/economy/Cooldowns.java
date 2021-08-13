package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class Cooldowns {
    public static String Reformat(long string) {
        String newString = string + "";
        if(String.valueOf(string).length() == 1) {
            newString = "0" + string;
        }
        return newString;
    }
    public static String ReformatMilliseconds(long string) {
        String newString = string + "";
        if(String.valueOf(string).length() == 1) {
            newString = "00" + string;
        } else if(String.valueOf(string).length() == 2) {
            newString = "0" + string;
        }
        return newString;
    }
    public Cooldowns(GuildMessageReceivedEvent e, String[] data) {
        long time = System.currentTimeMillis();
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("Daily and Gift reset at 00:00 UTC\nLing Ling", e.getJDA().getSelfUser().getAvatarUrl());
        //rob
        long milliseconds = Long.parseLong(data[5]) - time;
        long seconds;
        long minutes;
        long hours;
        if (milliseconds < 0) {
            builder.addField("**Rob**", ":white_check_mark:", false);
        } else {
            hours = milliseconds / 3600000;
            milliseconds -= hours * 3600000;
            minutes = milliseconds / 60000;
            milliseconds -= minutes * 60000;
            seconds = milliseconds / 1000;
            milliseconds -= seconds * 1000;
            builder.addField("**Rob**", "`" + Reformat(hours) + ":" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", false);
        }

        //scales
        milliseconds = Long.parseLong(data[64]) - time;
        if (milliseconds < 0) {
            builder.addField("**Scales**", ":white_check_mark:", false);
        } else {
            seconds = milliseconds / 1000;
            milliseconds -= seconds * 1000;
            builder.addField("**Scales**", "`" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", false);
        }

        //practice
        milliseconds = Long.parseLong(data[1]) - time;
        if (milliseconds < 0) {
            builder.addField("**Practice**", ":white_check_mark:", false);
        } else {
            minutes = milliseconds / 60000;
            milliseconds -= minutes * 60000;
            seconds = milliseconds / 1000;
            milliseconds -= seconds * 1000;
                builder.addField("**Practice**", "`" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", false);
        }

        //teach
        if (Boolean.parseBoolean(data[78])) {
            milliseconds = Long.parseLong(data[79]) - time;
            if (milliseconds < 0) {
                builder.addField("**Teach**", ":white_check_mark:", false);
            } else {
                hours = milliseconds / 3600000;
                milliseconds -= hours * 3600000;
                minutes = milliseconds / 60000;
                milliseconds -= minutes * 60000;
                seconds = milliseconds / 1000;
                milliseconds -= seconds * 1000;
                builder.addField("**Teach**", "`" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", false);
            }
        }

        //rehearse
        if (Boolean.parseBoolean(data[19])) {
            milliseconds = Long.parseLong(data[7]) - time;
            if (milliseconds < 0) {
                builder.addField("**Rehearse**", ":white_check_mark:", false);
            } else {
                hours = milliseconds / 3600000;
                milliseconds -= hours * 3600000;
                minutes = milliseconds / 60000;
                milliseconds -= minutes * 60000;
                seconds = milliseconds / 1000;
                milliseconds -= seconds * 1000;
                builder.addField("**Rehearse**", "`" + Reformat(hours) + ":" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", false);
            }
        }

        //perform
        milliseconds = Long.parseLong(data[8]) - time;
        if (milliseconds < 0) {
            builder.addField("**Perform**", ":white_check_mark:", false);
        } else {
            hours = milliseconds / 3600000;
            milliseconds -= hours * 3600000;
            minutes = milliseconds / 60000;
            milliseconds -= minutes * 60000;
            seconds = milliseconds / 1000;
            milliseconds -= seconds * 1000;
            builder.addField("**Perform**", "`" + Reformat(hours) + ":" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`", false);
        }

        //vote
        milliseconds = Long.parseLong(data[89]) - time + 1800000;
        if (milliseconds < 0) {
            builder.addField("**Vote**", ":white_check_mark:\n`WARNING: May be inaccurate`", false);
        } else {
            hours = milliseconds / 3600000;
            milliseconds -= hours * 3600000;
            minutes = milliseconds / 60000;
            milliseconds -= minutes * 60000;
            seconds = milliseconds / 1000;
            milliseconds -= seconds * 1000;
            builder.addField("**Vote**", "`" + Reformat(hours) + ":" + Reformat(minutes) + ":" + Reformat(seconds) + "." + ReformatMilliseconds(milliseconds) + "`\n`WARNING: May be inaccurate`", false);
        }
        builder.setTitle("__**Cooldowns**__");
        e.getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}