package regular;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class Help {
    public Help(GuildMessageReceivedEvent e, char prefix) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl()).setTitle("__**Ling Ling Bot Help**__");
        try {
            switch (message[1]) {
                case "1" -> builder.addField("Help List Page 1 - Fun\nRun `" + prefix + "help <commandName>` to view a command in depth", "`joke`\n`kill`\n`emojify`", false);
                case "2" -> builder.addField("Help List Page 2 - Utility\nRun `" + prefix + "help <commandName>` to view a command in depth",
                        "**`rules`**\n`poll`\n`checkdm`\n`invite`\n`faq`\n`serversettings`\n`support`\n`code`\n`prefix`", false);
                case "3" -> builder.addField("Help List Page 3 - Economy Commands\nRun `" + prefix + "help <commandName>` to view a command in depth.",
                        "`start`\n`balance`\n`profile`\n`stats`\n`inventory`\n`cooldowns`\n`upgrades`\n`buy`\n`use`\n`leaderboard`", false);
                case "4" -> builder.addField("Help List Page 4 - Income Commands\nRun `" + prefix + "help <commandName>` to view a command in depth.",
                        "\n`scales`\n``practice`\n`rehearse`\n`perform`\n`teach`\n`daily`\n`gamble`\n`rob`", false);
                case "5" -> builder.addField("Help List Page 5 - Bot Moderation Commands\nOnly Bot Mods and Bot Admins have access to these commands.",
                        "`lookdata`\n`editdata`\n`luthier`\n`give`\n`resetincomes`\n`warn`\n`resetsave`\n`ban`\n`unban`", false);
                case "6" -> builder.addField("Help List Page 6 - Dev-Only Commands\nOnly Developers have access to these commands.\nThis page exists for people who are curious.",
                        "`status`\n`activity`\n`updateservers`\n`updateusers`\n`purgeusers`\n`restart`", false);
                case "cooldowns" -> builder.addField("Cooldowns Command", "Syntax: `" + prefix + "cooldowns`\nUsage: Shows you the cooldowns of most commands.\nAliases: `" + prefix + "c`", false);
                case "faq" -> builder.addField("FAQ Command", "Syntax: `" + prefix + "faq [item]`\nUsage: Shows answers  to frequently asked questions.\nExample: `" + prefix + "faq rob`", false);
                case "use" -> builder.addField("Use Command", "Syntax: `" + prefix + "use <id>`\nUsage: Uses usable items like Ling Ling Insurance.\nExample: `" + prefix + "use 1`", false);
                case "upgrades" -> builder.addField("Upgrades Command", "Syntax: `" + prefix + "upgrades [page]`\nUsage: Shows you a page of the shop.  Must include a page number.\nAliases: `" + prefix + "up`, `" + prefix + "u`, `" + prefix + "shop`\nExample: `" + prefix + "upgrades 2`", false);
                case "buy" -> builder.addField("Buy Command", "Syntax: `" + prefix + "buy <itemID>`\nUsage: Buys an upgrade.\nExample: `" + prefix + "buy practising`", false);
                case "orchestra" -> builder.addField("Orchestra Command", "Syntax: `" + prefix + "orchestra`\nUsage: Shows the statistics of your orchestra.\nAliases: `" + prefix + "o`", false);
                case "editdata" -> builder.addField("EditData Command", "Syntax: `!editdata <userID> [New Data]`\nUsage: Edits the profile of a user.\nRestrictions: Usable only by Bot Admins and above.", false);
                case "updateusers" -> builder.addField("UpdateUsers Command", "Syntax: `!updateusers <confirm> [Data to Append]`\nUsage: Updates the economy save format.\nRestrictions: Usable only by Developers.", false);
                case "updateservers" -> builder.addField("UpdateServers Command", "Syntax: `!updateservers <confirm> [Data to Append]`\nUsage: Updates the server settings save format.\nRestrictions: Usable only by Developers.", false);
                case "purgeusers" -> builder.addField("PurgeUsers Command", "Syntax: `!purgeusers`\nUsage: Deletes all economy save files for users with 0 violins.\nRestricitons: Usable only by Developers.", false);
                case "resetincomes" -> builder.addField("ResetIncomes Command", "Syntax: `!resetincomes`\nUsage: Manually resets all incomes.\nRestricitons: Usable only by Bot Admins and above.", false);
                case "give" -> builder.addField("Give Command", "Syntax: `!give [id] [type] [amount]\nUsage: Gives the user an amount of the specified currency.\nValid values: `violin`, `medal`, `rice`, `tea`, `blessing`\nRestrictions: Usable only by Bot Moderators and above.", false);
                case "lookdata" -> builder.addField("LookData Command", "Syntax: `!lookdata <userID>`\nUsage: Shows the profile of a user.\nRestrictions: Usable only by Bot Moderators and above.", false);
                case "luthier" -> builder.addField("Luthier Command", "Syntax: `!luthier <setup | edit | add> [New Data]`\nUsage: Sets up, edits, or adds multi for a server's Luthier.\nRestrictions: Usable only by Bot Admins and above.", false);
                case "leaderboard" -> builder.addField("Leaderboard Command", "Syntax: `" + prefix + "leaderboard [type]`\nUsage: Shows the leaderboard for a specific data point.\nAliases: `" + prefix + "lb`\nExample: `" + prefix + "lb violins`", false);
                case "scales" -> builder.addField("Scales Command", "Syntax: `" + prefix + "scales`\nUsage: Practise scales earn some violins!\nCooldown: 90s/65s\nAliases: `" + prefix + "s`", false);
                case "practice" -> builder.addField("Practice Command", "Syntax: `" + prefix + "practice`\nUsage: Practise to earn some violins!\nCooldown: 45m/30m\nAliases: `" + prefix + "p`", false);
                case "rehearse" -> builder.addField("Rehearse Command", "Syntax: `" + prefix + "rehearse`\nUsage: Rehearse with an orchestra to earn loads of violins!\nRestrictions: Usable only by people with an Orchestra.\nCooldown: 1d/18h\nAliases: `" + prefix + "r`", false);
                case "perform" -> builder.addField("Perform Command", "Syntax: `" + prefix + "perform`\nUsage: Perform your solo to earn an INSANE amount of violins!  Gain even more by hiring an orchestra and upgrading your Concert Hall!\nCooldown: 84h/60h\nAliases: `" + prefix + "pf`", false);
                case "daily" -> builder.addField("Daily Command", "Syntax: `" + prefix + "daily`\nUsage: Get a daily dose of violins!  Run the command many days in a row to start gaining a streak and get even more violins!\nCooldown: 24h\nAliases: `" + prefix + "d`", false);
                case "rob" -> builder.addField("Rob Command", "Syntax: `" + prefix + "rob <user>`\nUsage: Robs the user.  Beware that the more violins you have than the target, the harder it is to succeed!\nCooldown: 16h\nExample: `" + prefix + "rob 488487157372157962`", false);
                case "start" -> builder.addField("Start Command", "Syntax: `" + prefix + "start`\nUsage: Creates a profile for the user.  This can only be used once.", false);
                case "gamble" -> builder.addField("Gamble Command", "Syntax: `" + prefix + "gamble [type] [amount]`\nUsage: Bets the amount using the gamemode specified.  You can only bet up to 8x your hourly income.\nGambling Options: `rng` `slots` `scratch`\nCooldown: 10s\nAliases: `" + prefix + "bet`\nExample: `" + prefix + "gamble slots 4000`", false);
                case "inventory" -> builder.addField("Inventory Command", "Syntax: `" + prefix + "inventory [user]`\nUsage: Shows your inventory or the inventory of another user.\nAliases: `" + prefix + "inv`", false);
                case "profile" -> builder.addField("Profile Command", "Syntax: `" + prefix + "profile [user]`\nUsage: Shows your profile or the profile of another user.\nAliases: `" + prefix + "b` `" + prefix + "bal` `" + prefix + "balance`", false);
                case "stats" -> builder.addField("Stats Command", "Syntax: `" + prefix + "stats [user]`\nUsage: Shows your detailed stats or the stats of another user.", false);
                case "status" -> builder.addField("Status Command", "Syntax: `!status <online | idle | dnd>`\nUsage: Sets the status of the bot to Online, Idle, or DND.\nRestrictions: Usable only by Developers.", false);
                case "activity" -> builder.addField("Activity Command", "Syntax: `!activity <playing | listening | watching> [activity]`\nUsage: Changes the custom status of the bot.\nRestrictions: Usable only by Developers.", false);
                case "warn" -> builder.addField("Warn Command", "Syntax: `" + prefix + "warn <user> [reason]`\nUsage: Gives a user a bot warning.\nRestrictions: Usable only by Bot Moderators and above.\nExample: `" + prefix + "warn 488487157372157962 not practising forty hours a day`", false);
                case "resetsave" -> builder.addField("ResetSave Command", "Syntax: `" + prefix + "resetsave <user> [reason]`\nUsage: Resets the save of a user.\nRestrictions: Usable only by Bot Moderators and above.\nExample: `" + prefix + "resetsave 488487157372157962 abusing bugs`", false);
                case "ban" -> builder.addField("BAN Command", "Syntax: `" + prefix + "ban <user> [reason]`\nUsage: Bans the user from using the bot in addition to removing the save.\nRestrictions: Usable only by Bot Admins and above.\nExample: `" + prefix + "ban 488487157372157962 abusing bugs AND not practising`", false);
                case "unban" -> builder.addField("Unban Command", "Syntax: `" + prefix + "unban <user> [reason]`\nUsage: Unbans a user from using the bot.\nRestrictions: Usable only by Bot Admins and above.\nExample: `" + prefix + "unban 488487157372157962 appealed successfully`", false);
                case "restart" -> builder.addField("Restart Command", "Syntax: `" + prefix + "restart`\nUsage: Restarts the bot manually.\nRestrictions: Usable only by Developers.", false);
                case "joke" -> builder.addField("Joke Command", "Syntax: `" + prefix + "joke`\nUsage: Returns a music-related joke", false);
                case "kill" -> builder.addField("Kill Command", "Syntax: `" + prefix + "kill <user>`\nUsage: Totally kills the target\nExample: `" + prefix + "kill 488487157372157962`", false);
                case "checkdm" -> builder.addField("CheckDM Command", "Syntax: `" + prefix + "checkdm <user>`\nUsage: Sends a pre-generated message telling the mentioned user to check their DM.  Highly effective.\nExample: `" + prefix + "checkdm 488487157372157962`", false);
                case "poll" -> builder.addField("Poll Command", "Syntax: `" + prefix + "poll \"[Poll Name]\" \"[Options; separated; by; semicolons]`\"\nUsage: Creates a simple poll where reactions are used to vote.\nExample: `" + prefix + "poll \"Which instrument is better?\" \"Violin; Viola; Cello; Bass\"`", false);
                case "emojify" -> builder.addField("Emojify Command", "Syntax: `" + prefix + "emojify [message]`\nUsage: Returns your message using regional indicator emojis.\nRestrictions: You can only use numbers, letters, spaces, question marks, and exclamation points.  Incompatible with mentions.\nExample: `" + prefix + "emojify go practise`", false);
                case "invite" -> builder.addField("Invite Command", "Syntax: `" + prefix + "invite`\nUsage: Gives you instructions on how to invite the bot to your server.", false);
                case "serversettings" -> builder.addField("ServerSettings Command", "Syntax: `" + prefix + "serversettings [autoresponse | reactions | logging | automod | modcommands] [true/on | false/off]`\nUsage: Toggles a setting to be ON or OFF.\nRestrictions: Usable only by members with the `ADMINISTRATOR` permission.\nExample: `" + prefix + "serversettings autoresponse false`", false);
                case "support" -> builder.addField("Support Command", "Syntax: `" + prefix + "support`\nUsage: Gives a link to the support server.", false);
                case "code" -> builder.addField("Code Command", "Syntax: `" + prefix + "code`\nUsage: Gives a link to view the open-source bot code.", false);
                case "rules" -> builder.addField("Rules Command", "Syntax: `" + prefix + "rules`\nUsage: View the bot's rules.  By using the bot, you agree to following these rules and can be subject to punishment for breaking them knowingly or unknowingly.", false);
                case "vote" -> builder.addField("Vote Command", "Syntax: `" + prefix + "vote`\nUsage: Gives a link to vote for the bot and the support server.", false);
                case "prefix" -> builder.addField("Prefix Command", "Syntax: `!prefix [new]`\nUsage: Shows the current prefix.  Append a character to change the prefix.  This is the only command that will retain `!` across all servers.\nRestrictions: The prefix can only be changed by members with the `ADMINISTRATOR` permission.\nExample: `!prefix $`", false);
                default -> builder.addField("Help List", "Page or command `" + message[1] + "` does not exist!  Run `" + prefix + "help` to see a list of pages.", false);
            }
        } catch(Exception exception) {
            builder.addField("Help List", "Use `" + prefix + "help [page]` to view further commands!" +
                    "\n\n`1` - Fun Commands\n`2` - Utility Commands\n`3` - Economy Commands\n`4` - Leveling\n`5` - Bot Moderation Commands\n`6` - Dev-Only Commands", false);
        }
        e.getChannel().sendMessage(builder.build()).queue();
    }
}
