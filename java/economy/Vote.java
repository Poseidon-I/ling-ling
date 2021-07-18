package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.discordbots.api.client.DiscordBotListAPI;

import java.io.BufferedReader;
import java.io.FileReader;

public class Vote {
    public static void GiveRewards(GuildMessageReceivedEvent e, String[] data, int numBoxes) {
        data[90] = String.valueOf(Long.parseLong(data[90]) + numBoxes);
        data[88] = String.valueOf(Long.parseLong(data[88]) + 1);
        data[89] = String.valueOf(System.currentTimeMillis() + 39600000);
        e.getChannel().sendMessage("Thank you for voting!  You have received **" + numBoxes + "** Vote Boxes!").queue();
        new SaveData(e, data);
    }
    public Vote(GuildMessageReceivedEvent e, String[] data) {
        long time = System.currentTimeMillis();
        if (time < Long.parseLong(data[89])) {
            e.getChannel().sendMessage("You claimed your reward too recently!  If you forgot to claim your reward after voting previously, this is a friendly reminder to claim your reward right after you vote.\n\n#dontbelikestrad").queue();
        } else {
            DiscordBotListAPI api = null;
            try (BufferedReader rdr = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\top.txt"))) {
                api = new DiscordBotListAPI.Builder()
                        .token(rdr.readLine())
                        .botId("733409243222507670")
                        .build();
            } catch (Exception exception) {
                //nothing here lol
            }
            String id = e.getAuthor().getId();
            assert api != null;
            DiscordBotListAPI finalApi = api;
            api.hasVoted(id).whenComplete((hasVoted, f) -> {
                if (hasVoted) {
                    finalApi.getVotingMultiplier().whenComplete((multiplier, g) -> {
                        if (multiplier.isWeekend()) {
                            GiveRewards(e, data, 2);
                        } else {
                            GiveRewards(e, data, 1);
                        }
                    });
                } else {
                    e.getChannel().sendMessage("You have not voted yet!  Vote here:\n<https://top.gg/bot/733409243222507670/vote>").queue();
                }
            });
            new SaveData(e, data);
        }
    }
}