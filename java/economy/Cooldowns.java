package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class Cooldowns {
    @SuppressWarnings("ConstantConditions")
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
            if (hours < 0) {
                builder.addField("**Rob**", minutes + "m " + seconds + "s " + milliseconds + "ms", false);
            } else {
                builder.addField("**Rob**", hours + "h " + minutes + "m " + seconds + "s ", false);
            }
        }

        //scales
        milliseconds = Long.parseLong(data[64]) - time;
        if (milliseconds < 0) {
            builder.addField("**Scales**", ":white_check_mark:", false);
        } else {
            seconds = milliseconds / 1000;
            milliseconds -= seconds * 1000;
            builder.addField("**Scales**", seconds + "s " + milliseconds + "ms", false);
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
            if (minutes < 0) {
                builder.addField("**Practice**", seconds + "s " + milliseconds + "ms", false);
            } else {
                builder.addField("**Practice**", minutes + "m " + seconds + "s ", false);
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
                if (hours < 0) {
                    builder.addField("**Rehearse**", minutes + "m " + seconds + "s " + milliseconds + "ms", false);
                } else {
                    builder.addField("**Rehearse**", hours + "h " + minutes + "m " + seconds + "s ", false);
                }
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
            if (hours < 0) {
                builder.addField("**Perform**", minutes + "m " + seconds + "s " + milliseconds + "ms", false);
            } else {
                builder.addField("**Perform**", hours + "h " + minutes + "m " + seconds + "s ", false);
            }
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
            if (hours < 0) {
                builder.addField("**Daily**", minutes + "m " + seconds + "s " + milliseconds + "ms", false);
            } else {
                builder.addField("**Daily**", hours + "h " + minutes + "m " + seconds + "s ", false);
            }
        }
        builder.setTitle("__**Cooldowns**__");
        e.getChannel().sendMessage(builder.build()).queue();
    }
}