package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class Orchestra {
    public Orchestra(GuildMessageReceivedEvent e, String[] data) {
        if (Boolean.parseBoolean(data[19])) {
            long temp1 = 0;
            long temp2 = 0;
            long temp3 = 0;
            if (Boolean.parseBoolean(data[20])) {
                temp1 = 1;
            }
            if (Boolean.parseBoolean(data[25])) {
                temp2 = 1;
            }
            if (Boolean.parseBoolean(data[37])) {
                temp3 = 1;
            }
            EmbedBuilder builder = new EmbedBuilder()
                    .setTitle("**__Orchestra Stats__**")
                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                    .addField("**Woodwinds**", temp1 + " Piccolo\n" + data[21] + " Flutes\n" + data[22] + " Oboes\n" + data[23] + " Clarinets\n" + data[24] + " Bassoons\n" + temp2 + " Contrabassoon", true)
                    .addField("**Brass**", data[26] + " French Horns\n" + data[27] + " Trumpets\n" + data[28] + " Trombones\n" + data[29] + " Tubas", true)
                    .addField("**Strings**", data[32] + " Violin I\n" + data[33] + " Violin II\n" + data[34] + " Celli\n" + data[35] + " Double Basses\n" + data[36] + " Pianists", true)
                    .addField("**Choir**", data[38] + " Sopranos\n" + data[39] + " Altos\n" + data[40] + " Tenors\n" + data[41] + " Basses\n" + data[42] + " Vocal Soloists", true)
                    .addField("**Other**", temp3 + " Harp\n" + data[31] + " Percussionists\n" + data[30] + " Timpanists", true)
                    .setColor(Color.BLUE);
            e.getChannel().sendMessage(builder.build()).queue();
        } else {
            e.getChannel().sendMessage("You don't have an orchestra yet!").queue();
        }
    }
}
