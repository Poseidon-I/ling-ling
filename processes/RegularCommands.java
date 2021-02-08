package processes;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class RegularCommands {
    public RegularCommands(GuildMessageReceivedEvent e, String[] message, char prefix, boolean isDev, String target, String targetPing, String[] serverSettings) {
        if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.ADMINISTRATOR) || isDev) {
            new AdminCommands(e, message, prefix);
        }
        String fullMessage = e.getMessage().getContentRaw();
        boolean ranCommand = false;
        Random random = new Random();
        switch (message[0]) {
            case "help" -> {
                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl()).setTitle("__**Ling Ling Bot Help**__");
                try {
                    switch (message[1]) {
                        case "1" -> builder.addField("Help List Page 1 - Fun\nRun `" + prefix + "help <commandName>` to view a command in depth", "`joke`\n`insult`\n`kill`\n`emojify`", false);
                        case "2" -> builder.addField("Help List Page 2 - Utility\nRun `" + prefix + "help <commandName>` to view a command in depth",
                                "`suggest`\n`poll`\n`checkdm`\n`invite`\n`faq`\n`serversettings`\n`support`\n`prefix`", false);
                        case "3" -> builder.addField("Help List Page 3 - Economy eventListeners.Commands\nRun `" + prefix + "help <commandName>` to view a command in depth.",
                                "`start`\n`practice`\n`rehearse`\n`perform`\n`daily`\n`gamble`\n`balance`\n`profile`\n`inventory`\n`cooldowns`\n`rob`\n`upgrades`\n`buy`\n`use`", false);
                        case "4" -> builder.addField("Help List Page 4 - Leaderboards\nRun `" + prefix + "help <commandName>` to view a command in depth", "`leaderboard`\n`streakleaderboard`\n`medalleaderboard`\n`incomeleaderboard`", false);
                        case "5" -> builder.addField("Help List Page 5 - Leveling Commands\nRun `" + prefix + "help <commandName>` to view a command in depth\nThis module requres the server setting `leveling` to be set to `true`.", "`rank`\n`levelsettings`\n`levels`\n`setlevel`", false);
                        case "6" -> builder.addField("Help List Page 6 - Dev Only Commands\nOnly Developers have access to these commands.\nThis page exists for people who are curious.",
                                "`status`\n`activity`\n`lookdata`\n`editdata`\n`luthier`\n`updateservers`\n`updateusers`\n`give`\n`purgeusers`\n`resetincomes`\n`warn`", false);
                        case "rank" -> builder.addField("Rank Command", "Syntax: `" + prefix + "rank`\nUsage: Shows you your level and XP amount in the server.", false);
                        case "levelsettings" -> builder.addField("LevelSettings Command", "Syntax: `" + prefix + "levelsettings [min | max | cooldown | blacklist] [amount | add | remove] <channel>`\nUsage: A variety of commands to configure the leveling experience in a server.\nDefault values are Minimum 15, Maximum 25, Cooldown 60.\nRestrictions: Usable only by members with the `ADMINISTRATOR` permission.\nExample: `" + prefix + "levelsettings min 10`\nExample: `" + prefix + "levelsettings blacklist add #spam", false);
                        case "setlevel" -> builder.addField("SetLevel Command", "Syntax: `" + prefix + "setlevel <user> <level>`\nUsage: Sets the level of a user.\nRestrictions: Usable only by members with the `ADMINISTRATOR` permission.\nExample: `" + prefix + "setlevel 488487157372157962 10`", false);
                        case "levels" -> builder.addField("Levels Command", "Syntax: `" + prefix + "levels`\nUsage: Shows the 10 people with the most XP in a server.\nAliases: `" + prefix + "levellb`", false);
                        case "cooldowns" -> builder.addField("Cooldowns Command", "Syntax: `" + prefix + "cooldowns`\nUsage: Shows you the cooldowns of most commands.\nAliases: `" + prefix + "c`", false);
                        case "faq" -> builder.addField("FAQ Command", "Syntax: `" + prefix + "faq [item]`\nUsage: Shows answers  to frequently asked questions.\nExample: `" + prefix + "faq rob`", false);
                        case "use" -> builder.addField("Use Command", "Syntax: `" + prefix + "use <id>`\nUsage: Uses usable items like Ling Ling Insurance.\nExample: `" + prefix + "use 1`", false);
                        case "upgrades" -> builder.addField("Upgrades Command", "Syntax: `" + prefix + "upgrades [page]`\nUsage: Shows you a page of the shop.  Must include a page number.\nAliases: `" + prefix + "up`, `" + prefix + "u`, `" + prefix + "shop`\nExample: `" + prefix + "upgrades 2`", false);
                        case "buy" -> builder.addField("Buy Command", "Syntax: `" + prefix + "buy <itemID>`\nUsage: Buys an upgrade.\nExample: `" + prefix + "buy practising`", false);
                        case "orchestra" -> builder.addField("Orchestra Command", "Syntax: `" + prefix + "orchestra`\nUsage: Shows the statistics of your orchestra.\nAliases: `" + prefix + "o`", false);
                        case "editdata" -> builder.addField("EditData Command", "Syntax: `!editdata <userID> [New Data]`\nUsage: Edits the profile of a user.\nRestrictions: Usable only by Developers.", false);
                        case "updateusers" -> builder.addField("UpdateUsers Command", "Syntax: `!updateusers <confirm> [Data to Append]`\nUsage: Updates the economy save format.\nRestrictions: Usable only by Developers.", false);
                        case "updateservers" -> builder.addField("UpdateServers Command", "Syntax: `!updateservers <confirm> [Data to Append]`\nUsage: Updates the server settings save format.\nRestrictions: Usable only by Developers.", false);
                        case "purgeusers" -> builder.addField("PurgeUsers Command", "Syntax: `!purgeusers`\nUsage: Deletes all economy save files for users with 0 violins.\nRestricitons: Usable only by Developers.", false);
                        case "resetincomes" -> builder.addField("ResetIncomes Command", "Syntax: `!resetincomes`\nUsage: Manually resets all incomes.\nRestricitons: Usable only by Developers.", false);
                        case "give" -> builder.addField("Give Command", "Syntax: `!give <id> <amount>`\nUsage: Gives the user an amount of violins.\nRestrictions: Usable only by Developers.", false);
                        case "lookdata" -> builder.addField("LookData Command", "Syntax: `!lookdata <userID>`\nUsage: Shows the profile of a user.\nRestrictions: Usable only by Developers.", false);
                        case "luthier" -> builder.addField("Luthier Command", "Syntax: `!luthier <setup | edit> [New Data]`\nUsage: Sets up, or edits the settings of a server's Luthier.\nRestrictions: Usable only by Developers.", false);
                        case "practice" -> builder.addField("Practice Command", "Syntax: `" + prefix + "practice`\nUsage: Practise to earn some violins!\nCooldown: 40M/30M\nAliases: `" + prefix + "p`", false);
                        case "rehearse" -> builder.addField("Rehearse Command", "Syntax: `" + prefix + "rehearse`\nUsage: Rehearse with an orchestra to earn loads of violins!\nRestrictions: Usable only by people with an Orchestra.\nCooldown: 1D/18H\nAliases: `" + prefix + "r`", false);
                        case "perform" -> builder.addField("Perform Command", "Syntax: `" + prefix + "perform`\nUsage: Perform your solo to earn an INSANE amount of violins!  Gain even more by hiring an orchestra and upgrading your Concert Hall!\nCooldown: 3D 12H/2D 12H", false);
                        case "daily" -> builder.addField("Daily Command", "Syntax: `" + prefix + "daily`\nUsage: Get a daily dose of violins!  Run the command many days in a row to start gaining a streak and get even more violins!\nCooldown: 1D\nAliases: `" + prefix + "d`", false);
                        case "rob" -> builder.addField("Rob Command", "Syntax: `" + prefix + "rob <user>`\nUsage: Robs the user.  Beware that the more violins you have than the target, the harder it is to succeed!\nCooldown: 16H\nExample: `" + prefix + "rob 488487157372157962`", false);
                        case "start" -> builder.addField("Start Command", "Syntax: `" + prefix + "start`\nUsage: Creates a profile for the user.  This can only be used once.", false);
                        case "gamble" -> builder.addField("Gamble Command", "Syntax: `" + prefix + "gamble [type] [amount]`\nUsage: Bets the amount using the gamemode specified.  You can only bet up to 8x your hourly income.\nGambling Options: `rng` `slots` `scratch`\nCooldown: 30 seconds\nAliases: `" + prefix + "bet`\nExample: `" + prefix + "gamble slots 4000`", false);
                        case "balance" -> builder.addField("Balance Command", "Syntax: `" + prefix + "balance [user]`\nUsage: Shows your balance or the balance of another user.\nAliases: `" + prefix + "bal`, `" + prefix + "b`", false);
                        case "inventory" -> builder.addField("Inventory Command", "Syntax: `" + prefix + "inventory [user]`\nUsage: Shows your inventory or the inventory of another user.\nAliases: `" + prefix + "inv`", false);
                        case "profile" -> builder.addField("Profile Command", "Syntax: `" + prefix + "profile [user]`\nUsage: Shows your profile or the profile of another user.", false);
                        case "leaderboard" -> builder.addField("Leaderboard Command", "Syntax: `" + prefix + "leaderboard`\nUsage: Shows the ten richest people in the world.\nAliases: `" + prefix + "lb`", false);
                        case "medalleaderboard" -> builder.addField("Medal Leaderboard Command", "Syntax: `" + prefix + "medalleaderboard`\nUsage: Shows the ten most recognized people in the world.\nAliases: `" + prefix + "medallb`", false);
                        case "streakleaderboard" -> builder.addField("Streak Leaderboard Command", "Syntax: `" + prefix + "streakleaderboard`\nUsage: Shows the ten most resilient people in the world.\nAliases: `" + prefix + "streaklb`", false);
                        case "incomeleaderboard" -> builder.addField("Income Leaderboard Command", "Syntax: `" + prefix + "incomeleaderboard`\nUsage: Shows the ten fastest idle moneymakers in the world.\nAliases: `" + prefix + "incomelb`", false);
                        case "status" -> builder.addField("Status Command", "Syntax: `!status <online | idle | dnd>`\nUsage: Sets the status of the bot to Online, Idle, or DND.\nRestrictions: Usable only by Developers.", false);
                        case "activity" -> builder.addField("Activity Command", "Syntax: `!activity <playing | listening | watching> [activity]`\nUsage: Changes the custom status of the bot.\nRestrictions: Usable only by Developers.", false);
                        case "warn" -> builder.addField("Warn Command", "Syntax: `" + prefix + "warn <user> [reason]`\nUsage: Warns a user for breaking bot rules OR abusing an aspect or known bug of the bot.\nRestrictions: Usable only by Developers.\nExample: `" + prefix + "warn 488487157372157962 not practising forty hours a day`", false);
                        case "joke" -> builder.addField("Joke Command", "Syntax: `" + prefix + "joke`\nUsage: Returns a music-related joke", false);
                        case "insult" -> builder.addField("Insult Command", "Syntax: `" + prefix + "insult <user>`\nUsage: Insults the target.\nExample: `" + prefix + "insult 488487157372157962`", false);
                        case "kill" -> builder.addField("Kill Command", "Syntax: `" + prefix + "kill <user>`\nUsage: Totally kills the target\nExample: `" + prefix + "kill 488487157372157962`", false);
                        case "checkdm" -> builder.addField("CheckDM Command", "Syntax: `" + prefix + "checkdm <user>`\nUsage: Sends a pre-generated message telling the mentioned user to check their DM.  Highly effective.\nExample: `" + prefix + "checkdm 488487157372157962`", false);
                        case "poll" -> builder.addField("Poll Command", "Syntax: `" + prefix + "poll \"[Poll Name]\" \"[Options; separated; by; semicolons]`\"\nUsage: Creates a simple poll where reactions are used to vote.\nExample: `" + prefix + "poll \"Which instrument is better?\" \"Violin; Viola; Cello; Bass\"`", false);
                        case "suggest" -> builder.addField("Suggest Command", "Syntax: `" + prefix + "suggest`\nUsage: Gives you the links to the suggestion pages..", false);
                        case "emojify" -> builder.addField("Emojify Command", "Syntax: `" + prefix + "emojify [message]`\nUsage: Returns your message using regional indicator emojis.\nRestrictions: You can only use numbers, letters, spaces, question marks, and exclamation points.  Incompatible with mentions.\nExample: `" + prefix + "emojify go practise`", false);
                        case "invite" -> builder.addField("Invite Command", "Syntax: `" + prefix + "invite`\nUsage: Gives you instructions on how to invite the bot to your server.", false);
                        case "serversettings" -> builder.addField("ServerSettings Command", "Syntax: `" + prefix + "serversettings [autoresponse | reactions | logging | automod | modcommands] [true/on | false/off]`\nUsage: Toggles a setting to be ON or OFF.\nRestrictions: Usable only by members with the `ADMINISTRATOR` permission.\nExample: `" + prefix + "serversettings autoresponse false`", false);
                        case "support" -> builder.addField("Support Command", "Syntax: `" + prefix + "support`\nUsage: Gives a link to the support server.", false);
                        case "vote" -> builder.addField("Vote Command", "Syntax: `" + prefix + "vote`\nUsage: Gives a link to vote for the bot and the support server.", false);
                        case "prefix" -> builder.addField("Prefix Command", "Syntax: `!prefix [new]`\nUsage: Shows the current prefix.  Append a character to change the prefix.  This is the only command that will retain `!` across all servers.\nRestrictions: The prefix can only be changed by members with the `ADMINISTRATOR` permission.\nExample: `!prefix $`", false);
                        default -> builder.addField("Help List", "Page or command `" + message[1] + "` does not exist!  Run `" + prefix + "help` to see a list of pages.", false);
                    }
                } catch(Exception exception) {
                    builder.addField("Help List", "Use `" + prefix + "help [page]` to view further commands!" +
                            "\n\n`1` - Fun Commands\n`2` - Utility Commands\n`3` - Economy Commands\n`4` - Leaderboards\n`5` - Leveling\n`6` - Dev-Only Commands", false);
                }
                e.getChannel().sendMessage(builder.build()).queue();
                ranCommand = true;
            }
            case "faq" -> {
                EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl()).setTitle("__**Ling Ling Bot FAQ**__");
                try {
                    switch (message[1]) {
                        case "give" -> builder.addField(":money_with_wings: Why can't you give money to other users?", "Since robbing is dynamic, one can easily abuse an alt to rob a person and transfer those funds to the main account, or run commands on mutiple accounts and channel it into the main account.  Giving users money **will never be implemented**, however, user-sponsored giveaways may become a thing in the future.", false);
                        case "luthier" -> builder.addField(":violin: How do I get Luthier in my server?", "Luthier is currently unobtainable by normal or stable means.  Currently, the only way to obtain this exclusive feature is by winning giveaways.", false);
                        case "hourly" -> builder.addField(":timer: How do I increase hourly income?", "Run `" + prefix + "upgrades 1` to see a list of hourly income upgrades.  There are 4 pages of upgrades, or 8 if you own an orchestra.  If you have rice, you can use that to temporarily boost your income.\n\nThe current maximum income is `50000`:violin:/hour without boosts.", false);
                        case "orchestra" -> builder.addField(":musical_score: How do I hire an orchestra?", "You need an hourly income of at least 7500:violin:/hour and 40 000 000:violin: to hire one.  Run `" + prefix + "orchestra` for more info.", false);
                        case "rehearse" -> builder.addField(":notes: Why don't I have access to `" + prefix + "rehearse`?", "You need to have an orchestra to use `" + prefix + "rehearse`. ", false);
                        case "rob" -> builder.addField(":cop: Why is there no way to disable robbing?", "Robbing, or the ability to hamper other users, is an integral part of the bot.  While there are ways to lower the effect of robbers, robbing **will never have an option to be disabled**.  User feedback is always accepted, and balancing happens regularly.", false);
                        case "slots" -> builder.addField(":slot_machine: What are the payouts for slots?", "Rolling 2 items that are the same will pay 2x your bet.\nRolling 3 :trumpet: will pay 2.5x of your bet.\nRolling 3 :violin: will pay 5x of your bet.\nRolling 3 <:lingling40hrs:688449820410773532> will pay 10x of your bet.\nRolling 3 <:twoset:688452024009883669> will pay 15x of your bet.\nRolling 3 <:linglingclock:747499551451250730> will pay 25x of your bet.\nRolling 3 <a:StradSpam:772894512154279945> will pay 40x of your bet.", false);
                        case "delete" -> builder.addField(":wastebasket: Why was my save randomly deleted?", "It may have been purged due to having 0 violins and having 0 hourly income.  Purges happen often to help save storage space and processing demands.", false);
                        case "scratch" -> builder.addField(":tickets: What are the payouts for scratch tickets?", "__**Chances to Draw Certain Tickets**__\nLose 4:violin:: 60%\nNo Prize: 20%\nGain 2:violin:: 10%\nGain 5:violin:: 5%\nGain 10:violin:: 3%\nGain 25:violin:: 1%\nGain 50:violin:: 0.5%\nGain 100:violin:: 0.3%\nGain 200:violin:: 0.1%\nGain 500:violin:: 0.099%\n1 000 000:violin: Jackpot: 0.001%", false);
                    }
                } catch(Exception exception) {
                    builder.addField("FAQ Entries", "Use `" + prefix + "faq [item]` to view a page in depth.\n\n`rob` `give` `luthier` `hourly` `orchestra` `rehearse` `delete` `slots` `scratch`", false);
                }
                e.getChannel().sendMessage(builder.build()).queue();
                ranCommand = true;
            }
            case "suggest" -> {
                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                e.getChannel().sendMessage("Reporting a bug or requesting a new command?  Go to <https://github.com/Poseidon-I/ling-ling/wiki/This-Repository-is-for-Submitting-Bugs,-Feature-Requests,-or-Command-Requests> \n\nSuggesting a new autoresponse, command response, or word to blacklist?  Go to <https://forms.gle/LTqhVNdu7CgsrQzf7>").queue();
                ranCommand = true;
            }
            case "support" -> {
                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                e.getChannel().sendMessage("Join the support server at discord.gg/gNfPwa8").queue();
                ranCommand = true;
            }

            case "checkdm" -> {
                e.getChannel().sendMessage("**OI <@" + target + ">, " + e.getAuthor().getName() + " WANTS YOU TO CHECK YOUR DMS.  DO IT NOW OR ELSE.**").queue();
                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                try {
                    User send = e.getJDA().getUserById(target);
                    assert send != null;
                    send.openPrivateChannel().complete().sendMessage("**OI <@" + target + ">, " + e.getAuthor().getName() + " WANTS YOU TO CHECK YOUR DMS.  DO IT NOW OR ELSE.**").queue();
                } catch (Exception exception) {
                    e.getChannel().sendMessage("Either the recipient was being a n00b and didn't have their DMs open or you are smol brane and forgot to mention a user or you mentioned a bot.").queue();
                }
                ranCommand = true;
            }
            case "kill" -> {
                if (e.getAuthor().getName().contains("@everyone") || e.getAuthor().getName().contains("@here") || e.getAuthor().getName().contains("<@&")) {
                    e.getChannel().sendMessage("Nice try but no").queue();
                    throw new IllegalArgumentException();
                }
                int i = random.nextInt(27);
                if (i == 0) {
                    e.getChannel().sendMessage(targetPing + " thought it was a good idea to play the sousaphone after eating chili pepper").queue();
                } else if (i == 1) {
                    e.getChannel().sendMessage(targetPing + "'s ears were blasted apart with a piccolo playing 10000 Hz at fortissimo").queue();
                } else if (i == 2) {
                    e.getChannel().sendMessage("Ling Ling deemed " + targetPing + " unworthy of violin so he (she?) forced " + targetPing + " to play the viola.  Their ego was permanently damaged.").queue();
                } else if (i == 3) {
                    e.getChannel().sendMessage("Ling Ling is a benevolent violinist god so nice try but no").queue();
                } else if (i == 4) {
                    e.getChannel().sendMessage(targetPing + " was blinded because they used light mode").queue();
                } else if (i == 5) {
                    e.getChannel().sendMessage(e.getAuthor().getName() + " started punching " + targetPing + ".").queue();
                } else if (i == 6) {
                    e.getChannel().sendMessage(e.getAuthor().getName() + " somehow got " + targetPing + " into their Minecraft world and then " + targetPing + " was dumb enough to light TNT.").queue();
                } else if (i == 7) {
                    e.getChannel().sendMessage(targetPing + " stuffed too much chili pepper down their throat and the results were rather explosive").queue();
                } else if (i == 8) {
                    e.getChannel().sendMessage("A bomb detonated over " + targetPing + "'s head").queue();
                } else if (i == 9) {
                    e.getChannel().sendMessage(targetPing + " destroyed Jacqueline's Stradivarius so Jacqueline destroyed " + targetPing).queue();
                } else if (i == 10) {
                    e.getChannel().sendMessage(targetPing + " tripped over a tripwire and fell into the Void").queue();
                } else if (i == 11) {
                    e.getChannel().sendMessage(targetPing + " went to the zoo and got trampled by 20 elephants").queue();
                } else if (i == 12) {
                    e.getChannel().sendMessage(e.getAuthor().getName() + " played a very high harmonic on their violin and exploded " + targetPing + "'s eardrums.").queue();
                } else if (i == 13) {
                    e.getChannel().sendMessage(targetPing + " was run over by " + e.getAuthor().getName() + " because " + targetPing + " was crossing the street while playing a sousaphone.").queue();
                } else if (i == 14) {
                    e.getChannel().sendMessage(targetPing + " fell off a cliff because they were not paying attention to where they were going.  Reason?  Using Simply Piano app (sacrilegious)").queue();
                } else if (i == 15) {
                    e.getChannel().sendMessage(targetPing + " was whacked on the head with a viola for displeasing the conductor.").queue();
                } else if (i == 16) {
                    e.getChannel().sendMessage(targetPing + " tried to steal 2000 pounds worth of gold from " + e.getAuthor().getName() + " but they were caught and ended up crushing themselves under the gold.").queue();
                } else if (i == 17) {
                    e.getChannel().sendMessage(targetPing + " was attacked by " + e.getAuthor().getName() + ".  It was that simple").queue();
                } else if (i == 18) {
                    e.getChannel().sendMessage("Why would you want to wound " + targetPing + " they haven't done anything wrong").queue();
                } else if (i == 19) {
                    e.getChannel().sendMessage(targetPing + " tripped over " + e.getAuthor().getName() + "'s foot while reading music and walking").queue();
                } else if (i == 20) {
                    e.getChannel().sendMessage(e.getAuthor().getName() + " clobbers " + targetPing + " with a clarinet because " + targetPing + " was being dumb").queue();
                } else if (i == 21) {
                    e.getChannel().sendMessage(e.getAuthor().getName() + " slapped " + targetPing + " for playing the VIOLA").queue();
                } else if (i == 22) {
                    e.getChannel().sendMessage(targetPing + " was playing on CHIMPS mode and lost due to a Camo Regrow Red on Round 100.  The rest is obvious.").queue();
                } else if (i == 23) {
                    e.getChannel().sendMessage(targetPing + " displeased the Avatar of the Vengeful Monkey, who decided to crush " + targetPing + " with 999 BADs.").queue();
                } else if (i == 24) {
                    e.getChannel().sendMessage(e.getAuthor().getName() + " sacrificed all of " + targetPing + "'s towers to a Sun Temple, which was then sold, causing " + targetPing + " to lose.").queue();
                } else if (i == 25) {
                    e.getChannel().sendMessage(targetPing + " abused bugs on Tacoshack for two years.  Cole just found out and their $500 000/hour Shack was deleted.").queue();
                } else {
                    e.getChannel().sendMessage(targetPing + " stepped on a landmine and suffered major injuries.").queue();
                }
                ranCommand = true;
            }
            case "insult" -> {
                int i = random.nextInt(13);
                if (i == 0) {
                    e.getChannel().sendMessage(targetPing + "'s violining skills are so bad even the violists were allowed to laugh at them").queue();
                } else if (i == 1) {
                    e.getChannel().sendMessage("Why would you insult " + targetPing + ", they seem very nice").queue();
                } else if (i == 2) {
                    e.getChannel().sendMessage(targetPing + " tried conducting an orchestra but their skills were so shitty not even Ling Ling could figure out what was going on").queue();
                } else if (i == 3) {
                    e.getChannel().sendMessage(targetPing + " auditioned for Seattle Symphony but the audition panel just laughed at their horrible intonation").queue();
                } else if (i == 4) {
                    e.getChannel().sendMessage(targetPing + " is so bad that they were forced to play the viola").queue();
                } else if (i == 5) {
                    e.getChannel().sendMessage(targetPing + " played out of tune in front of the Berlin Philharmonic and they were ridiculed").queue();
                } else if (i == 6) {
                    e.getChannel().sendMessage("During a music test " + targetPing + " was called out for cheating").queue();
                } else if (i == 7) {
                    e.getChannel().sendMessage(targetPing + " abused a violin and Ling Ling came and punished them for their smol brane").queue();
                } else if (i == 8) {
                    e.getChannel().sendMessage(targetPing + " ruined the annual orchestra concert and was ridiculed for playing a wrong note.").queue();
                } else if (i == 9) {
                    e.getChannel().sendMessage(targetPing + " played the violin out of tune.  Ling Ling harassed them for the sin.").queue();
                } else if (i == 10) {
                    e.getChannel().sendMessage(targetPing + " lost Easy mode on Round 1.  How embarassing.").queue();
                } else if (i == 11) {
                    e.getChannel().sendMessage(targetPing + " was so bad at Bloons that even super slow Red Bloons could get by their poorly planned defense.").queue();
                } else {
                    e.getChannel().sendMessage(targetPing + " tried playing their piccolo solo but it broke so many eardrums that the conductor threw them off the stage into the audience headfirst.  Everyone laughed.").queue();
                }
                ranCommand = true;
            }
            case "joke" -> {
                int i = random.nextInt(23);
                if (i == 0) {
                    e.getChannel().sendMessage("Q: What do a viola and a lawsuit have in common?\nA: ||Everyone is happy when the case is closed.||").queue();
                } else if (i == 1) {
                    e.getChannel().sendMessage("Q: What do you get when a viola gets run over by a car?\nA:|| Peace||").queue();
                } else if (i == 2) {
                    e.getChannel().sendMessage("Q: What's the difference between a violin and a viola?\nA1: ||A viola burns longer.||\nA2: ||You can tune a violin.||").queue();
                } else if (i == 3) {
                    e.getChannel().sendMessage("Q: What's the definition of a minor second?\nA: ||Two violists playing in unison.||").queue();
                } else if (i == 4) {
                    e.getChannel().sendMessage("Q: How was the canon invented?\nA: ||Two violists were trying to play the same passage together.||").queue();
                } else if (i == 5) {
                    e.getChannel().sendMessage("Q: How can you tell when a violist is playing out of tune?\nA: ||The bow is moving.||").queue();
                } else if (i == 6) {
                    e.getChannel().sendMessage("Q: What's the difference between a washing machine and a violist?\nA: ||Vibrato.||").queue();
                } else if (i == 7) {
                    e.getChannel().sendMessage("Q: How do you get a violin to sound like a viola?\nA: ||Play in the low register with a lot of wrong notes.||").queue();
                } else if (i == 8) {
                    e.getChannel().sendMessage("Q: What is the range of a viola?\nA: ||As far as you can kick it.||").queue();
                } else if (i == 9) {
                    e.getChannel().sendMessage("A violist and a cellist were standing on a sinking ship. \"Help!\" cried the cellist, \"I can't swim!\"\n\"Don't worry,\" said the violist, \"||Just fake it.||\"").queue();
                } else if (i == 10) {
                    e.getChannel().sendMessage("Q: What's the difference between the first and last desk of the viola section?\nA: ||About half a bar.||").queue();
                } else if (i == 11) {
                    e.getChannel().sendMessage("Q: How do you get two violists to play in tune with each other?\nA: ||Ask one of them to leave.||").queue();
                } else if (i == 12) {
                    e.getChannel().sendMessage("A group of terrorists hijacked a plane full of violists. They called down to ground control with their list of demands and added that if their demands weren't met, they would ||release one violist every hour.||").queue();
                } else if (i == 13) {
                    e.getChannel().sendMessage("Q: What's the difference between a viola and a trampoline?\nA: ||You take your shoes off to jump on a trampoline.||").queue();
                } else if (i == 14) {
                    e.getChannel().sendMessage("Q: What's the difference between a viola and an onion?\nA: ||No one cries when you cut up a viola.||").queue();
                } else if (i == 15) {
                    e.getChannel().sendMessage("Q: Anything\nA: Viola.\n||*intense wheezing*||").queue();
                } else if (i == 16) {
                    e.getChannel().sendMessage("Q: What's the difference between a pizza and a violist? \nA: ||A pizza can feed a family of four.||").queue();
                } else if (i == 17) {
                    e.getChannel().sendMessage("Q: Why don't violists play hide and seek? \nA: ||Because no one would look for them.||").queue();
                } else if (i == 18) {
                    e.getChannel().sendMessage("Q : Why bAss and not viola?\nA: ||Because viola isn't badAss.||").queue();
                } else if (i == 19) {
                    e.getChannel().sendMessage("Q: Did you hear about the violist who played in tune?\nA: ||Neither did I.||").queue();
                } else if (i == 20) {
                    e.getChannel().sendMessage("Q: How do you get a violin to sound like a viola?\nA1: ||Sit in the back and don't play.||\nA2: ||Play in the front with a lot of wrong notes.||").queue();
                } else if (i == 21) {
                    e.getChannel().sendMessage("Q: Why is viola illegal?\nA: ||It viola-ted the Terms of Service of the musician world.||").queue();
                } else {
                    e.getChannel().sendMessage("Q: How make someone practice the viola?\nA: ||Tell them a violinist is getting better than them.||").queue();
                }
                ranCommand = true;
            }
            case "poll" -> {
                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                int i = 7;
                StringBuilder send = new StringBuilder("**POLL:  ");
                try {
                    while (fullMessage.charAt(i) != '"' && fullMessage.charAt(i) != '“' && fullMessage.charAt(i) != '”') {
                        send.append(fullMessage.charAt(i));
                        i++;
                    }
                } catch (Exception exception) {
                    e.getChannel().sendMessage("You need to end your title with a `\"`, or you did not properly start your title with `\"`").queue();
                    break;
                }
                send.append("**\n`A:` ");
                i += 3;
                int options = 1;
                char character = 'A';
                try {
                    while (fullMessage.charAt(i) != '"' && fullMessage.charAt(i) != '“' && fullMessage.charAt(i) != '”') {
                        if (fullMessage.charAt(i) == ';') {
                            i += 2;
                            options++;
                            character++;
                            send.append("\n`").append(character).append(":` ");
                        } else {
                            send.append(fullMessage.charAt(i));
                            i++;
                        }
                    }
                } catch (Exception exception) {
                    e.getChannel().sendMessage("You need to end your options portion with a `\"`, or you did not properly start your options portion with `\"`").queue();
                    break;
                }
                send.append("\nPoll created by ").append(e.getAuthor().getName()).append("#").append(e.getAuthor().getDiscriminator());
                String react = "";
                if (options > 20) {
                    e.getChannel().sendMessage("Please limit your polls to 20 options or less.").queue();
                } else {
                    react = e.getChannel().sendMessage(send.toString()).complete().getId();
                }
                int hex = 127462;
                for (int j = 0; j < options; j++) {
                    String unicode = "U+" + Integer.toHexString(hex);
                    e.getChannel().addReactionById(react, unicode).queue();
                    hex++;
                }
                ranCommand = true;
            }
            case "emojify" -> {
                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                StringBuilder convert = new StringBuilder("> ");
                if (message.length == 1) {
                    e.getChannel().sendMessage("how are we going to emojify nothing dum dum").queue();
                }
                for (int i = 1; i < message.length; i++) {
                    if (i + 1 == message.length) {
                        convert.append(message[i]);
                    } else {
                        convert.append(message[i]).append(" ");
                    }
                }
                convert = new StringBuilder(convert.toString().toLowerCase());
                StringBuilder send = new StringBuilder();
                for (int i = 2; i < convert.length(); i++) {
                    char cur = convert.charAt(i);
                    if (cur == ' ') {
                        send.append("<:linglingclock:747499551451250730> ");
                    } else if (cur == '1') {
                        send.append(":one: ");
                    } else if (cur == '2') {
                        send.append(":two: ");
                    } else if (cur == '3') {
                        send.append(":three: ");
                    } else if (cur == '4') {
                        send.append(":four: ");
                    } else if (cur == '5') {
                        send.append(":five: ");
                    } else if (cur == '6') {
                        send.append(":six: ");
                    } else if (cur == '7') {
                        send.append(":seven: ");
                    } else if (cur == '8') {
                        send.append(":eight: ");
                    } else if (cur == '9') {
                        send.append(":nine: ");
                    } else if (cur == '0') {
                        send.append(":zero: ");
                    } else if (cur == '?') {
                        send.append(":grey_question: ");
                    } else if (cur == '!') {
                        send.append(":grey_exclamation: ");
                    } else {
                        send.append(":regional_indicator_").append(cur).append(": ");
                    }
                }
                send.append("\n<@").append(e.getAuthor().getId()).append(">");
                try {
                    e.getChannel().sendMessage(send.toString()).queue();
                } catch (Exception exception) {
                    e.getChannel().sendMessage("Your message ended up being over 2000 characters, try shortening it.").queue();
                }
                ranCommand = true;
            }
            case "invite" -> {
                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                EmbedBuilder builder = new EmbedBuilder()
                        .setColor(Color.BLUE)
                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                        .addField("__How to invite the bot to your server.__", "You can add the bot to your server using the below link:\n<https://discord.com/api/oauth2/authorize?client_id=733409243222507670&permissions=268725312&scope=bot>", false);
                e.getChannel().sendMessage(builder.build()).queue();
                ranCommand = true;
            }
            case "vote" -> {
                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                e.getChannel().sendMessage("You can vote for the bot here: <https://top.gg/bot/733409243222507670/vote>.\nYou can vote for the support server here: <https://top.gg/servers/670725611207262219/vote>.  Voting gives a 10% boost to `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` in the support server!").queue();
                ranCommand = true;
            }
                    /*case "website" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("Visit my webpage!  <https://lingling40hrs.weebly.com/>").queue();
                    }
                    case "costs" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("Need a table with all upgrade costs?  Here's one!  <https://lingling40hrs-guide.neocities.org/upgrades.html>").queue();
                    }
                    case "guide" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("The guide is being worked on currently, this command will be updated as soon as it releases!").queue();
                    }*/
        }
        if(serverSettings[2].equals("true")) {
            new LevelCommands(e, message);
        }
        if(!ranCommand) {
            new Economy(e, message, prefix, target);
        }
    }
}