package regular;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
// BEETHOVEN-ONLY CLASS
public class BeethovenHelp {
    public static void help(GenericDiscordEvent e, String[] message) {
        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("**__Beethoven Bot Help__**")
                .setFooter("Beethoven (1770-1827)", e.getJDA().getSelfUser().getAvatarUrl())
                .setColor(Color.BLUE);
        try {
            switch(message[2]) {
                case "gstart" -> builder.addField("**GStart Command**", "Syntax: `beethoven gstart [time] [winners] [hasRequirement] [item]`\nUsage: Start a giveaway that will automatically choose a winner at the end of the allotted time.\nRestrictions: Only usable in <#734697492490354768>\nExample: `beethoven gstart 1440 2 true Ling Ling Medal`", false)
                        .addField("**Command Parameters**", "`time` - Integer; the amount of time the giveaway will last in minutes\n`winners` - Integer; the number of winners that will be selected when the timer ends\n`hasRequirement` - Boolean; controls whether the requirement warning is shown\n`item` - String (multiple); the item that is being given away", false);
                case "forceupdategiveaways" -> builder.addField("**ForceUpdateGiveaways Command**", "Syntax: `beethoven forceupdategiveaways`\nUsage: Forces currently running giveaways to be updated.", false);
                case "rerollgiveaway" -> builder.addField("**RerollGiveaway Command**", "Syntax: `beethoven rerollgiveaway [id] [winners]`\nUsage: Rerolls a giveaway.\nRestrictions: Only usable in <#734697492490354768>\nExample: `beethoven rerollgiveaway 863623564618629150 1`", false)
                        .addField("**Command Parameters**", "`id` - Message ID; the ID of the message that the giveaway is contained in\n`winners` - Integer; the number of winners that will be selected from the current people that have reacted", false);
                case "slowmode" -> builder.addField("**Slowmode Command**", "Syntax: `beethoven slowmode [time]`\nUsage: Set the slowmode of the current channel in seconds.\nRestrictions: Only usable by <@&735308459452661802>\nExample: `beethoven slowmode 1`", false)
                        .addField("**Command Parameters**", "`time` - Integer; number of seconds that the slowmode should be set for", false);
                case "rank" -> builder.addField("Rank Command", "Syntax: `beethoven rank`\nUsage: Shows you your level and XP server.", false);
                case "setlevel" -> builder.addField("SetLevel Command", "Syntax: `beethoven setlevel [user] [level]`\nUsage: Sets the level of a user.\nRestrictions: Usable only by members with the `ADMINISTRATOR` permission.\nExample: `beethoven setlevel 488487157372157962 10`", false);
                case "leaderboard" -> builder.addField("Leaderboard Command", "Syntax: `beethoven leaderboard`\nUsage: Shows the 10 people w    ith the most levels and XP server.\nAliases: `lb` `levels`", false);
                case "checkmessages" -> builder.addField("CheckMessages Command", "Syntax: `beethoven checkmessages [user]`\nUsage: Shows you the amount of messages someone has sent this month.\nAliases: `checkmsgs`\nExample: `beethoven checkmessages 488487157372157962`", false);
                case "resetmessages" -> builder.addField("ResetMessages Command", "Syntax: `beethoven resetmesssages`\nUsage: Resets all messages.\nRestrictions: Usable only by Developers", false);
                case "messageleaderboard" -> builder.addField("MessageLeaderboard Command", "Syntax: `beethoven messageleaderboard`\nUsage: Shows the 10 people with the most messages this month.\nAliases: `msgleaderboard` `messagelb` `msglb` `messages` `msgs`", false);
                case "forcerestartlingling" -> builder.addField("ForceRestartLingLing Command", "Syntax: `beethoven forcerestartlingling`\nForces Ling Ling to restart.\nRestrictions: Usable only by Developers", false);
                case "staffreport" -> builder.addField("StaffReport Command", "Syntax: `beethoven staffreport`\nShows the amount of messages each staff member has sent, and whether they meet the required message count for the current month.", false);
                case "gamble" -> builder.addField("Gamble Command", "Syntax: `beethoven gamble <type> <amount | max>`\nUsage: Bets the amount using the gamemode specified.  You can only bet up to 10x your hourly income.  Writing `max` in place of the amount will bet the maximum allowed.\nGambling Options: `rng` `slots` `scratch`\nCooldown: 10s\nExample: `beethoven gamble slots 40001\nAlias: `bet`", false);
                case "pingdeadchat" -> builder.addField("PingDeadChat Command", "Syntax: `beethoven pingdeadchat`\nPings <@&934618203152347187>\nGlobal Cooldown: 24H", false);
                case "checkdm" -> builder.addField("CheckDM Command", "Syntax: `beethoven checkdm <user>`\nUsage: Sends a pre-generated message telling the mentioned user to check their DM.  Highly effective.\nExample: `beethoven checkdm 488487157372157962`", false);
                case "annoy" -> builder.addField("Annoy Command", "Syntax: `beethoven annoy @user`\nUsage: Annoys the targeted user.  **REQUIRES A PING**\nExample: `beethoven annoy <@488487157372157962>`\nGlobal Cooldown: 1H", false);
                case "requestgiveaway" -> builder.addField("RequestGiveaway Command", "Syntax: `beethoven requestgiveaway <message>`\nUsage: Request to host a giveaway!  Make sure you include how much you want to give away, how long your giveaway should be, and how many winners there are.\nExample: `beethoven requestgiveaway 1 million violins, 24 hours, 1 winner`", false);
                default -> builder.addField("Command Doesn't Exist!", "`beethoven " + message[2] + "` doesn't exist you idiot.", false);
            }
        } catch(Exception exception) {
            builder.addField("**Commands**", "**Run `beethoven help [command]` for more info on a command!**\n\n**__Utility Commands__**\n`gstart` `forceupdategiveaways`\n`rerollgiveaway` `slowmode`\n`pingdeadchat` `checkdm`\n`annoy` `forcerestartlingling`\n`requestgiveaway`\n\n**__Leveling Commands__**\n`rank` `setlevel`\n`leaderboard` `checkmessages`\n`resetmessages` `messageleaderboard`\n`staffreport`", false);
        }
        e.replyEmbeds(builder.build());
    }
}