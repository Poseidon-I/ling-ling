package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.*;

public class Help extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        boolean isDev = false;
        try {
            if (e.getAuthor().getId().equals("619989388109152256") || e.getAuthor().getId().equals("488487157372157962") || e.getAuthor().getId().equals("706933826193981612")) {
                isDev = true;
            }
        } catch (Exception exception) {
            //nothing here lol
        }
        String server = "";
        try {
            server = e.getGuild().getId();
        } catch (Exception exception) {
            //nothing here lol
        }
        char prefix = '!';
        BufferedReader prefixes;
        PrintWriter pw = null;
        try {
            prefixes = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt"));
            prefix = (char) prefixes.read();
        } catch (Exception exception) {
            File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt");
            try {
                file.createNewFile();
                pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt")));
            } catch(Exception exception1) {
                //nothing here lol
            }
            assert pw != null;
            pw.print('!');
            pw.close();
        }
        if(isDev) {
            prefix = '!';
        }
        if(message[0].equals(prefix + "help") && !e.getAuthor().isBot()) {
            e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
            EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl()).setTitle("__**Ling Ling Bot Help**__");
            try {
                switch (message[1]) {
                    case "1" -> builder.addField("Help List Page 1 - Moderation\nRun `" + prefix + "help <commandName>` to view a command in depth", "`warn`\n`mute`\n`unmute`\n`kick`\n`ban`\n`unban`", false);
                    case "2" -> builder.addField("Help List Page 2 - Fun\nRun `" + prefix + "help <commandName>` to view a command in depth", "`joke`\n`insult`\n`wound`\n`fakeban`\n`fakekick`\n`fakewarn`\n`fakemute`", false);
                    case "3" -> builder.addField("Help List Page 3 - Utility\nRun `" + prefix + "help <commandName>` to view a command in depth",
                                "`help`\n`suggest`\n`poll`\n`checkdm`\n`invite`\n`emojify`\n`rules`\n`serversettings`\n`support`\n`prefix`\n`website`\n`costs`\n`guide`", false);
                    case "4" -> builder.addField("Help List Page 4 - Economy Commands\nRun `" + prefix + "help <commandName>` to view a command in depth.",
                                "`start`\n`practice`\n`rehearse`\n`perform`\n`daily`\n`gamble`\n`balance`\n`cooldowns`\n`rob`\n`upgrades`\n`buy`\n`use`\n`leaderboard`", false);
                    case "5" -> builder.addField("Help List Page 5 - Dev Only Commands\nOnly Developers have access to these commands.\nThis page exists for people who are curious.",
                                "`status`\n`activity`\n`lookdata`\n`editdata`\n`luthier`\n`updateservers`\n`updateusers`\n`give`\n`purgeusers`", false);
                    case "cooldowns" -> builder.addField("Cooldowns Command", "Syntax: `" + prefix + "cooldowns`\nUsage: Shows you the cooldowns of all commands that have cooldowns.\nAliases: `" + prefix + "c`", false);
                    case "use" -> builder.addField("Use Command", "Syntax: `" + prefix + "use <id>`\nUsage: Uses usable items like Ling Ling Insurance.\nExample: `" + prefix + "use 1`", false);
                    case "upgrades" -> builder.addField("Upgrades Command", "Syntax: `" + prefix + "upgrades [page]`\nUsage: Shows you a page of the shop.\nAliases: `" + prefix + "up`, `" + prefix + "u`, `" + prefix + "shop`\nExample: `" + prefix + "upgrades 2`", false);
                    case "buy" -> builder.addField("Buy Command", "Syntax: `" + prefix + "buy <itemID>`\nUsage: Buys an upgrade.\nExample: `" + prefix + "buy practising`", false);
                    case "orchestra" -> builder.addField("Orchestra Command", "Syntax: `" + prefix + "orchestra`\nUsage: Shows the statistics of your orchestra.\nAliases: `" + prefix + "o`", false);
                    case "editdata" -> builder.addField("EditData Command", "Syntax: `!editdata <userID> [New Data]`\nUsage: Edits the profile of a user.\nRestrictions: Usable only by Developers.", false);
                    case "updateusers" -> builder.addField("UpdateUsers Command", "Syntax: `!updateusers <confirm> [Data to Append]`\nUsage: Updates the economy save format.\nRestrictions: Usable only by Developers.", false);
                    case "updateservers" -> builder.addField("UpdateServers Command", "Syntax: `!updateservers <confirm> [Data to Append]`\nUsage: Updates the server settings save format.\nRestrictions: Usable only by Developers.", false);
                    case "purgeusers" -> builder.addField("PurgeUsers Command", "Syntax: `!purgeusers`\nUsage: Deletes all economy save files for users with 0 violins.\nRestricitons: Usable only by Developers.", false);
                    case "give" -> builder.addField("Give Command", "Syntax: `!give <id> <amount>`\nUsage: Gives the user an amount of violins.\nRestrictions: Usable only by Developers.", false);
                    case "lookdata" -> builder.addField("LookData Command", "Syntax: `!lookdata <userID>`\nUsage: Shows the profile of a user.\nRestrictions: Usable only by Developers.", false);
                    case "luthier" -> builder.addField("Luthier Command", "Syntax: `!luthier <setup | edit> [New Data]`\nUsage: Sets up, or edits the settings of a server's Luthier.\nRestrictions: Usable only by Developers.", false);
                    case "practice" -> builder.addField("Practice Command", "Syntax: `" + prefix + "practice`\nUsage: Practise to earn some violins!\nCooldown: 45 Minutes\nAliases: `" + prefix + "p`", false);
                    case "rehearse" -> builder.addField("Rehearse Command", "Syntax: `" + prefix + "rehearse`\nUsage: Rehearse with an orchestra to earn loads of violins!\nRestrictions: Usable only by people with Orchestras.\nCooldown: 1 day\nAliases: `" + prefix + "r`", false);
                    case "perform" -> builder.addField("Perform Command", "Syntax: `" + prefix + "perform`\nUsage: Perform your solo to earn an INSANE amount of violins!  Gain even more by hiring an orchestra and upgrading your Concert Hall!\nCooldown: 1 week", false);
                    case "daily" -> builder.addField("Daily Command", "Syntax: `" + prefix + "daily`\nUsage: Get a daily dose of violins!  Run the command many days in a row to start gaining a streak and get even more violins!\nCooldown: 1 day\n~liases: `" + prefix + "d`", false);
                    case "rob" -> builder.addField("Rob Command", "Syntax: `" + prefix + "rob <user>`\nUsage: Robs the user.  Beware that if you have more violins than the target, the harder it is to succeed!\nCooldown: 1 day\nExample: `" + prefix + "rob 488487157372157962`", false);
                    case "start" -> builder.addField("Start Command", "Syntax: `" + prefix + "start`\nUsage: Creates a profile for the user.  Can only be used once.", false);
                    case "gamble" -> builder.addField("Gamble Command", "Syntax: `" + prefix + "gamble <amount>`\nUsage: Bets the amount with a chance of winning determined by the Lucky Musician upgrade.\nCooldown: 10 seconds\nAliases: `" + prefix + "bet`\nExample: `" + prefix + "gamble 100`", false);
                    case "balance" -> builder.addField("Balance Command", "Syntax: `" + prefix + "balance [user]`\nUsage: Shows your balance, or the balance of another user.\nAliases: `" + prefix + "bal`, `" + prefix + "b`", false);
                    case "leaderboard" -> builder.addField("Leaderboard Command", "Syntax: `" + prefix + "leaderboard`\nUsage: Shows the ten richest people in the world.\nAliases: `" + prefix + "lb`", false);
                    case "status" -> builder.addField("Status Command", "Syntax: `!status <online | idle | dnd>`\nUsage: Sets the status of the bot to Online, Idle, or DND.\nRestrictions: Usable only by Developers.", false);
                    case "activity" -> builder.addField("Activity Command", "Syntax: `!activity <playing | listening | watching> [activity]`\nUsage: Changes the custom status of the bot.\nRestrictions: Usable only by Developers.", false);
                    case "warn" -> builder.addField("Warn Command", "Syntax: `" + prefix + "warn <user> [reason]`\nUsage: Logs a warning in the moderation log for the mentioned user.\nRestrictions: Usable only by members with `Manage Messages` Permission.\nExample: `" + prefix + "warn 488487157372157962 not practisnig forty hours a day`", false);
                    case "kick" -> builder.addField("Kick Command", "Syntax: `" + prefix + "kick <user> [reason]`\nUsage: Kicks the user from the server and logs a kick in the moderation log for the mentioned user.\nRestrictions: Usable only by members with `Kick Members` Permission.\nExample: `" + prefix + "kick 488487157372157962 shitposting bad viola jokes even after being told to stop`", false);
                    case "ban" -> builder.addField("BAN Command", "Syntax: `" + prefix + "ban <user> [reason]`\nUsage: Bans the user from the server, and logs a ban in the moderation log for the mentioned user.\nRestrictions: Usable only by members with `Ban Members` Permission.\nExample: `" + prefix + "ban 488487157372157962 kept shitposting bad viola jokes even after being kicked three times`", false);
                    case "joke" -> builder.addField("Joke Command", "Syntax: `" + prefix + "joke`\nUsage: Returns a music-related joke", false);
                    case "insult" -> builder.addField("Insult Command", "Syntax: `" + prefix + "insult <user>`\nUsage: Insults the target.\nExample: `" + prefix + "insult 488487157372157962`", false);
                    case "kill" -> builder.addField("Kill Command", "Syntax: `" + prefix + "kill <user>`\nUsage: Totally kills the target\nExample: `" + prefix + "kill 488487157372157962`", false);
                    case "checkdm" -> builder.addField("CheckDM Command", "Syntax: `" + prefix + "checkdm <user>`\nUsage: Sends a pre-generated message telling the mentioned user to check their DM.  Highly effective.\nExample: `" + prefix + "checkdm 488487157372157962`", false);
                    case "poll" -> builder.addField("Poll Command", "Syntax: `" + prefix + "poll \"[Poll Name]\" \"[Options; separated; by; semicolons]`\"\nUsage: Creates a simple poll where reactions are used to vote.\nExample: `" + prefix + "poll \"Which instrument is better?\" \"Violin; Viola; Cello; Bass\"`", false);
                    case "fakeban" -> builder.addField("FakeBan Command", "Syntax: `" + prefix + "fakeban <user>`\nUsage: Totally bans the target.\nExample: `" + prefix + "fakeban 488487157372157962`", false);
                    case "fakekick" -> builder.addField("FakeKick Command", "Syntax: `" + prefix + "fakekick <user>`\nUsage: Totally kicks the target.\nExample: `" + prefix + "fakekick 488487157372157962`" , false);
                    case "fakemute" -> builder.addField("FakeMute Command", "Syntax: `" + prefix + "fakemute <user>`\nUsage: Totally mutes the target.\nExample: `" + prefix + "fakemute 488487157372157962`", false);
                    case "fakewarn" -> builder.addField("FakeWarn Command", "Syntax: `" + prefix + "fakewarn <user>`\nUsage: Totally warns the target.\nExample: `" + prefix + "fakewarn 488487157372157962`", false);
                    case "mute" -> builder.addField("Mute Command", "Syntax: `" + prefix + "mute <user> [reason]`\nUsage: Mutes the user.\nRestrictions: Only usable by members with the `Manage Messages` Permission.\nExample: `" + prefix + "mute 488487157372157962 shitposting bad viola jokes`", false);
                    case "unmute" -> builder.addField("Unmute Command", "Syntax: `" + prefix + "unmute <user>`\nUsage: Unmutes the user.\nRestrictions: Only usable by members with the `Manage Messages` Permission.\nExample: `" + prefix + "unmute 488487157372157962 redeemed themselves by practising fifty hours a day`", false);
                    case "suggest" -> builder.addField("Suggest Command", "Syntax: `" + prefix + "suggest`\nUsage: Gives you the links to the suggestion pages..", false);
                    case "emojify" -> builder.addField("Emojify Command", "Syntax: `" + prefix + "emojify [message]`\nUsage: Returns your message except using regional indicator emojis.\nRestrictions: You can only use numbers, letters, spaces, question marks, and exclamation points.  Incompatible with mentions.\nExample: `" + prefix + "emojify go practise`", false);
                    case "invite" -> builder.addField("Invite Command", "Syntax: `" + prefix + "invite`\nUsage: Gives you instructions on how to invite the bot to your server.", false);
                    case "rules" -> builder.addField("Rules Command", "Syntax: `" + prefix + "rules`\nUsage: Shows the bot rules.  APPLIES IN ALL SERVERS.", false);
                    case "serversettings" -> builder.addField("ServerSettings Command", "Syntax: `" + prefix + "serversettings [autoresponse | reactions | logging | automod | modcommands] [true/on | false/off]`\nUsage: Toggles a setting to be ON or OFF.\nRestrictions: Usable only by members with the `ADMINISTRATOR` permission.\nExample: `" + prefix + "serversettings autoresponse false`", false);
                    case "support" -> builder.addField("Support Command", "Syntax: `" + prefix + "support`\nUsage: Gives a link to the support server.", false);
                    case "vote" -> builder.addField("Vote Command", "Syntax: `" + prefix + "vote`\nUsage: Gives a link to vote for the bot and the support server.", false);
                    case "website" -> builder.addField("Website Command", "Syntax: `" + prefix + "website`\nUsage: Gives a link to Ling Ling's webpage!", false);
                    case "costs" -> builder.addField("Costs Command", "Syntax: `" + prefix + "costs`\nUsage: Gives a link to ALL economy upgrade costs.", false);
                    case "guide" -> builder.addField("Guide Command", "Syntax: `" + prefix + "guide`\nUsage: Gives a link to a guide for Ling Ling's economy.", false);
                    case "prefix" -> builder.addField("Prefix Command", "Syntax: `!prefix [new]`\nUsage: Shows the current prefix.  Append a character to change the prefix.  This is the only command that will retain `!` across all servers.\nRestrictions: The prefix can only be changed by members with the `ADMINISTRATOR` permission.\nExample: `!prefix $`", false);
                    default -> builder.addField("Help List", "Page or command `" + message[1] + "` does not exist!  Run `" + prefix + "help` to see a list of pages.", false);
                }
                e.getChannel().sendMessage(builder.build()).queue();
            } catch(Exception exception) {
                builder.addField("Help List", "Use `" + prefix + "help {page}` to view further commands!" +
                        "\n\n`1` - Moderation Commands\n`2` - Fun Commands\n`3` - Utility Commands\n`4` - Economy Commands\n`5` - Dev-Only Commands", false);
                e.getChannel().sendMessage(builder.build()).queue();
            }
        }
    }
}