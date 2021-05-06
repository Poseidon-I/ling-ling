package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

public class Inventory {
    public Inventory(GuildMessageReceivedEvent e, String[] data) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        if (message.length == 1) {
            EmbedBuilder builder = new EmbedBuilder()
                    .setColor(Color.BLUE)
                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                    .setTitle(e.getAuthor().getName() + "'s Inventory")
                    .addField("Rice :rice:", "Count: " + data[51] + "\nUsage: Gives you 1 hour of income.\nID: `rice`", false)
                    .addField("Bubble Tea :bubble_tea:", "Count: " + data[62] + "\nUsage: Gives you 4 hours of income.\nID: `tea`", false)
                    .addField("Ling Ling Blessing :angel:", "Count: " + data[63] + "\nUsage: Gives you 24 hours of income and 1-3 Ling Ling Medals.\nID: `blessing`", false);
            e.getChannel().sendMessage(builder.build()).queue();
        } else {
            User target = Objects.requireNonNull(e.getJDA().getUserById(message[1]));
            try {
                BufferedReader reader = new BufferedReader(new FileReader("Ling Ling Bot Data\\Economy Data\\" + target.getId() + ".txt"));
                String[] line = reader.readLine().split(" ");
                EmbedBuilder builder = new EmbedBuilder()
                        .setColor(Color.BLUE)
                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                        .setTitle(target.getName() + "'s Inventory")
                        .addField("Rice :rice:", "Count: " + line[51], false)
                        .addField("Bubble Tea :bubble_tea:", "Count: " + line[62], false)
                        .addField("Ling Ling Blessing :angel:", "Count: " + line[63], false);
                e.getChannel().sendMessage(builder.build()).queue();
                reader.close();
            } catch (Exception exception) {
                e.getChannel().sendMessage("This save file does not exist!").queue();
            }
        }
    }
}
