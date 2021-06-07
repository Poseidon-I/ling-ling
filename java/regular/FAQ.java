package regular;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class FAQ {
    public FAQ(GuildMessageReceivedEvent e, char prefix) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl()).setTitle("__**Ling Ling Bot FAQ**__");
        try {
            switch (message[1]) {
                case "give" -> builder.addField(":money_with_wings: Why can't you give money to other users?", "Since robbing is dynamic, one can easily abuse an alt to rob a person and transfer those funds to the main account, or run commands on mutiple accounts and channel it into the main account.  Giving users money **will never be implemented**, however, user-sponsored giveaways may become a thing in the future.", false);
                case "luthier" -> builder.addField(":violin: How do I get Luthier in my server?", "Luthier is currently unobtainable by normal or stable means.  Currently, the only way to obtain this exclusive feature is by winning giveaways.", false);
                case "hourly" -> builder.addField(":timer: How do I increase hourly income?", "Run `" + prefix + "upgrades 1` to see a list of hourly income upgrades.\n\nThe current maximum income is `50000`:violin:/hour.", false);
                case "orchestra" -> builder.addField(":musical_score: How do I hire an orchestra?", "You need an hourly income of at least 7500:violin:/hour and 40 000 000:violin: to hire one.  Run `" + prefix + "orchestra` for more info.", false);
                case "rehearse" -> builder.addField(":notes: Why don't I have access to `" + prefix + "rehearse`?", "You need to have an orchestra to use `" + prefix + "rehearse`. ", false);
                case "rob" -> builder.addField(":cop: Why is there no way to disable robbing?", "Robbing, or the ability to hamper other users, is an integral part of the bot.  While there are ways to lower the effect of robbers, robbing **will never have an option to be disabled**.  User feedback is always accepted, and balancing happens regularly.", false);
                case "slots" -> builder.addField(":slot_machine: What are the payouts for slots?", "Rolling 2 items that are the same will pay 1x your bet.\nRolling 3 :trumpet: will pay 2.5x of your bet.\nRolling 3 :violin: will pay 5x of your bet.\nRolling 3 <:lingling40hrs:688449820410773532> will pay 10x of your bet.\nRolling 3 <:twoset:688452024009883669> will pay 15x of your bet.\nRolling 3 <:linglingclock:747499551451250730> will pay 25x of your bet.\nRolling 3 <a:StradSpam:772894512154279945> will pay 40x of your bet.", false);
                case "delete" -> builder.addField(":wastebasket: Why was my save randomly deleted?", "It may have been purged due to having 0 violins and having 0 hourly income.  Purges happen often to help save storage space and processing demands.", false);
                case "scratch" -> builder.addField(":tickets: What are the payouts for scratch tickets?", "__**Chances to Draw Certain Tickets**__\nLose 5:violin:: 50%\nNo Prize: 25%\nGain 2:violin:: 15%\nGain 5:violin:: 5%\nGain 10:violin:: 3%\nGain 25:violin:: 1%\nGain 50:violin:: 0.5%\nGain 100:violin:: 0.3%\nGain 200:violin:: 0.1%\nGain 500:violin:: 0.075%\n1 000 000:violin: Jackpot: 0.025%", false);
                case "robchance" -> builder.addField(":cop: What are the chances to succeed in a rob?", "Your chance is determined by your balance and the target's balance.  The exact chance of **failing** is your balance divided by the sum of your balance and the target's balance.  There is a cap of 5 000 000:violin: during a robbery that can be stolen, and there is no cap on how much you can be fined for failing.\n\ntl;dr - the more you have, the harder it is to succeed in robbing someone", false);
                case "leveling" -> builder.addField(":chart_with_upwards_trend: What happened to Leveling and server settings?", "Leveling was moved to a new bot that can be invited using `!invite` (use your server's prefix).  All other server settings were completely removed in an effort to save resources.", false);
                default -> builder.addField("You entered an invalid entry!  FAQ Entries", "Use `" + prefix + "faq [item]` to view a page in depth.\n\n`rob` `give` `luthier` `hourly` `orchestra` `rehearse` `delete` `slots` `scratch`", false);
            }
        } catch(Exception exception) {
            builder.addField("FAQ Entries", "Use `" + prefix + "faq [item]` to view a page in depth.\n\n`rob` `give` `luthier` `hourly` `orchestra` `rehearse` `delete` `slots` `scratch` `robchance`", false);
        }
        e.getChannel().sendMessage(builder.build()).queue();
    }
}
