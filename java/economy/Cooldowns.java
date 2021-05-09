package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class Cooldowns {
    public Cooldowns(GuildMessageReceivedEvent e, String[] data) {
        long time = System.currentTimeMillis();
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
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
            builder.addField("**Rob**", "`" + hours + ":" + minutes + ":" + seconds + "." + milliseconds + "`", false);
        }

        //scales
        milliseconds = Long.parseLong(data[64]) - time;
        if (milliseconds < 0) {
            builder.addField("**Scales**", ":white_check_mark:", false);
        } else {
            seconds = milliseconds / 1000;
            milliseconds -= seconds * 1000;
            builder.addField("**Scales**", "`" + seconds + "." + milliseconds + "`", false);
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
                builder.addField("**Practice**", "`" + minutes + ":" + seconds + "." + milliseconds + "`", false);
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
                builder.addField("**Teach**", "`" + minutes + ":" + seconds + "." + milliseconds + "`", false);
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
                builder.addField("**Rehearse**", "`" + hours + ":" + minutes + ":" + seconds + "." + milliseconds + "`", false);
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
            builder.addField("**Perform**", "`" + hours + ":" + minutes + ":" + seconds + "." + milliseconds + "`", false);
        }

        //daily
        milliseconds = Long.parseLong(data[48]) - time;
        if (milliseconds < 0) {
            builder.addField("**Daily**", ":white_check_mark:", false);
        } else {
            hours = milliseconds / 3600000;
            milliseconds -= hours * 3600000;
            minutes = milliseconds / 60000;
            milliseconds -= minutes * 60000;
            seconds = milliseconds / 1000;
            milliseconds -= seconds * 1000;
            builder.addField("**Daily**", "`" + hours + ":" + minutes + ":" + seconds + "." + milliseconds + "`", false);
        }
        builder.setTitle("__**Cooldowns**__");
        e.getChannel().sendMessage(builder.build()).queue();
    }
}