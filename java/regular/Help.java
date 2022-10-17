package regular;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class Help {
	public static void help(@NotNull SlashCommandInteractionEvent e) {
		EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl()).setTitle("__**Ling Ling Bot Help**__");
		try {
			switch(Objects.requireNonNull(e.getOption("page")).getAsString()) {
				case "1" -> builder.addField("Help List Page 1 - Fun\nRun `/help <commandName>` to view a command in depth", "`joke`\n`kill`\n`emojify`", false);
				case "2" -> builder.addField("Help List Page 2 - Utility\nRun `/help <commandName>` to view a command in depth",
						"**`rules`**\n`staff`\n`poll`\n`guide`\n`invite`\n`faq`\n`support`\n`website`\n`botstats`\n`settings`\n`link`", false);
				case "3" -> builder.addField("Help List Page 3 - Economy Commands\nRun `/help <commandName>` to view a command in depth.",
						"**__`Profile Commands`__**\n`start`\t`profile`\n`stats`\t`inventory`\n\n**__`Gameplay Commands`__**\n`cooldowns`\t`upgrades`\n`buy`\t`use`\n`claim`\t`gift`\n`craft`\t`market`\n`leaderboard`\n\n**__`Bank Commands`__**\n`deposit`\t`withdraw`\n`loan`\t`payloan`", false);
				case "4" -> builder.addField("Help List Page 4 - Income Commands\nRun `/help <commandName>` to view a command in depth.",
						"\n`scales`\n`practice`\n`rehearse`\n`perform`\n`teach`\n`daily`\n`gamble`\n`rob`", false);
				case "5" -> builder.addField("Help List Page 5 - Bot Moderation Commands\nOnly Bot Mods and Bot Admins have access to these commands.",
						"**__`Bot Mod Commands`__**\n`give`\n`warn`\n`resetsave`\n\n**__`Bot Admin Commands`__**\n`luthier`\n`resetincomes`\n`ban`\n`unban`\n`updateluthierchance`", false);
				case "6" -> builder.addField("Help List Page 6 - Dev-Only Commands\nOnly Developers have access to these commands.\nThis page exists for people who are curious.",
						"`resetdaily`\n`updateusers`\n`updateroles`\n`setpermlevel`\n`globalstats`\n**`forcestop`**", false);
				
				// PAGE 1
				case "joke" -> builder.addField("Joke Command", "Syntax: `/joke`\nUsage: Returns a music-related joke", false);
				case "kill" -> builder.addField("Kill Command", "Syntax: `/kill <thing>`\nUsage: Totally kills the target\nExample: `/kill violas`", false);
				case "emojify" -> builder.addField("Emojify Command", "Syntax: `/emojify [message]`\nUsage: Returns your message using regional indicator emojis.\nRestrictions: You can only use numbers, letters, spaces, question marks, and exclamation points.  Incompatible with mentions.\nExample: `/emojify go practise`", false);
				
				// PAGE 2
				case "rules" -> builder.addField("Rules Command", "Syntax: `/rules`\nUsage: View the bot's rules.  By using the bot, you agree to following these rules and can be subject to punishment for breaking them knowingly or unknowingly.", false);
				case "staff" -> builder.addField("Staff Command", "Syntax: `/staff`\nUsage: Shows the bot's moderation staff.  You may DM any of them for help at any time.", false);
				case "poll" -> builder.addField("Poll Command", "Syntax: `/poll \"<PollName>\" \"<Options; separated; by; semicolons>`\"\nUsage: Creates a simple poll where reactions are used to vote.\nExample: `/poll \"Which instrument is better?\" \"Violin; Viola; Cello; Bass\"`", false);
				case "guide" -> builder.addField("Guide Command", "Syntax: `/guide`\nUsage: Gives the link to the beginner's guide.", false);
				case "invite" -> builder.addField("Invite Command", "Syntax: `/invite`\nUsage: Gives you instructions on how to invite the bot to your server.", false);
				case "faq" -> builder.addField("FAQ Command", "Syntax: `/faq <item>`\nUsage: Shows answers  to frequently asked questions.\nExample: `/faq rob`", false);
				case "support" -> builder.addField("Support Command", "Syntax: `/support`\nUsage: Gives a link to the support server.", false);
				case "website" -> builder.addField("Website Command", "Syntax: `/website`\nUsage: Gives a link to view the bot's website.  Does not guarantee a non-shit website.", false);
				case "botstats" -> builder.addField("BotStats Command", "Syntax: `/botstats`\nUsage: Shows the number of servers the bot is in and the number of profiles that have been created.", false);
				case "settings" -> builder.addField("Settings Command", "Syntax: `/settings [setting] <(setting) newValue>`\nUsage: Change your settings.", false);
				case "link" -> builder.addField("Link Command", "Syntax: `/link <IGN>`\nUsage: Adds the specified Minecraft Java IGN to your Ling Ling account.  **CAN ONLY BE DONE ONCE**\nRecommended only for people who play on Strad's PRIVATE Creative World.", false);
				
				// PAGE 3
				case "start" -> builder.addField("Start Command", "Syntax: `/start`\nUsage: Creates a profile for the user.  This can only be used once.", false);
				case "profile" -> builder.addField("Profile Command", "Syntax: `/b [user]`\nUsage: Shows your profile or the profile of another user.", false);
				case "stats" -> builder.addField("Stats Command", "Syntax: `/stats [user]`\nUsage: Shows your detailed stats or the stats of another user.", false);
				case "inventory" -> builder.addField("Inventory Command", "Syntax: `/inv <page> [user]`\nUsage: Shows your inventory or the inventory of another user.", false);
				case "cooldowns" -> builder.addField("Cooldowns Command", "Syntax: `/c`\nUsage: Shows you the cooldowns of most commands.", false);
				case "upgrades" -> builder.addField("Upgrades Command", "Syntax: `/up <page>`\nUsage: Shows you a page of the shop.  Must include a page number.\nExample: `/upgrades 2`", false);
				case "buy" -> builder.addField("Buy Command", "Syntax: `/buy <itemID>`\nUsage: Buys an upgrade.\nExample: `/buy practising`", false);
				case "use" -> builder.addField("Use Command", "Syntax: `/use <id> [num]`\nUsage: Uses usable items like Rice.\nExample: `/use rice 5`", false);
				case "market" -> builder.addField("Market Command", "Syntax: `/market <buy | sell | view | offers> <(buy/sell) item> [(buy/sell) amount] [(sell) price]`\nUsage: Interacts with the Publik Market.\nExamples: `/market view grains` `/market buy grains 15` `/market sell grains 100 10`", false);
				case "craft" -> builder.addField("Craft Command", "Syntax: `/craft <item> [amount | max]`\nUsage: Crafts items using raw materials.\nExample: `/craft rice 5`", false);
				case "claim" -> builder.addField("Claim Command", "Syntax: `/claim`\nUsage: Claims a Free Box.\nCooldown: 12h", false);
				case "gift" -> builder.addField("Gift Command", "Syntax: `/gift <user>`\nUsage: Gifts the user a Gift Box.\nCooldown: 0-24h\nExample: `/gift 488487157372157962`", false);
				case "leaderboard" -> builder.addField("Leaderboard Command", "Syntax: `/lb <type>`\nUsage: Shows the leaderboard for a specific data point.\nExample: `/lb violins`", false);
				case "deposit" -> builder.addField("Deposit Command", "Syntax: `/dep <amount | max>`\nUsage: Deposits violins into your bank account.  Use `max` to deposit the most possible.", false);
				case "withdraw" -> builder.addField("Withdraw Command", "Syntax: `/with <amount | max>`\nUsage: Withdraws violins from your bank account.  Use `all` to withdraw everything.`", false);
				case "loan" -> builder.addField("Loan Command", "Syntax: `/loan <amount | max>`\nUsage: Take out a loan, maximum amount is based on your hourly income.  You can also use this to check how much you owe.", false);
				case "payloan" -> builder.addField("PayLoan Command", "Syntax: `/payloan <amount | max>`\nUsage: Pays back a specified amount, or everything in your wallet.", false);
				
				// PAGE 4
				case "scales" -> builder.addField("Scales Command", "Syntax: `/s`\nUsage: Practise scales earn some violins!\nCooldown: 90s/65s", false);
				case "practice" -> builder.addField("Practice Command", "Syntax: `/p`\nUsage: Practise to earn some violins!\nCooldown: 45m/30m", false);
				case "rehearse" -> builder.addField("Rehearse Command", "Syntax: `/r`\nUsage: Rehearse with an orchestra to earn loads of violins!\nRestrictions: Usable only by people with an Orchestra.\nCooldown: 1d/16h", false);
				case "perform" -> builder.addField("Perform Command", "Syntax: `/pf`\nUsage: Perform your solo to earn an INSANE amount of violins!  Gain even more by hiring an orchestra and upgrading your Concert Hall!\nCooldown: 84h/56h", false);
				case "teach" -> builder.addField("Teach Command", "Syntax: `/t`\nUsage: Teach a student to earn more violins!  Not affected by Efficient Practising, instead it has its own upgrades to improve the payout.\nRestrictions: Usable only by people with a Teaching Certificate.\nCooldown: 1h", false);
				case "daily" -> builder.addField("Daily Command", "Syntax: `/d`\nUsage: Get a daily dose of violins!  Run the command many days in a row to start gaining a streak and get even more violins!\nCooldown: 0-24h", false);
				case "gamble" -> builder.addField("Gamble Command", "Syntax: `/bet <type> <amount | max>`\nUsage: Bets the amount using the gamemode specified.  You can only bet up to 10x your hourly income.  Writing `max` in place of the amount will bet the maximum allowed.\nGambling Options: `rng` `slots` `scratch`\nCooldown: 10s\nExample: `/gamble slots 4000`", false);
				case "rob" -> builder.addField("Rob Command", "Syntax: `/rob <user>`\nUsage: Robs the user.  Beware that the more violins you have than the target, the harder it is to succeed!\nCooldown: 16h\nExample: `/rob 488487157372157962`", false);
				case "answer" -> builder.addField("Answer Command", "Syntax: `/answer <yourGuess>`\nUsage: Give your answer for a Luthier question.\nExample: `/answer sacrinterestinglegious`", false);
				
				// PAGE 5
				case "give" -> builder.addField("Give Command", "Syntax: `/give <id> <type> <amount>`\nUsage: Gives the user an amount of the specified currency.\nValid values: `violin`, `medal`, `rice`, `tea`, `blessing`, `gift`, `vote`, `kit`, `llbox`, `crazybox`\nRestrictions: Usable only by Bot Moderators and above.", false);
				case "warn" -> builder.addField("Warn Command", "Syntax: `/warn <user> [reason]`\nUsage: Gives a user a bot warning.\nRestrictions: Usable only by Bot Moderators and above.\nExample: `/warn 488487157372157962 not practising forty hours a day`", false);
				case "resetsave" -> builder.addField("ResetSave Command", "Syntax: `/resetsave <user> [reason]`\nUsage: Resets the save of a user.\nRestrictions: Usable only by Bot Moderators and above.\nExample: `/resetsave 488487157372157962 abusing bugs`", false);
				case "luthier" -> builder.addField("Luthier Command", "Syntax: `/luthier <setup | edit> <(edit) channel | multiplier | word> <(edit) newValue>`\nUsage: Sets up and edits a server's Luthier.\nRestrictions: Usable only by Bot Admins and above.", false);
				case "resetincomes" -> builder.addField("ResetIncomes Command", "Syntax: `/resetincomes`\nUsage: Manually resets all incomes.\nRestricitons: Usable only by Bot Admins and above.", false);
				case "ban" -> builder.addField("BAN Command", "Syntax: `/ban <user> [reason]`\nUsage: Bans the user from using the economy system.\nRestrictions: Usable only by Bot Admins and above.\nExample: `/ban 488487157372157962 abusing bugs AND not practising`", false);
				case "unban" -> builder.addField("Unban Command", "Syntax: `/unban <user> <wipe> [reason]`\nUsage: Unbans a user from using the economy system.  Choice to wipe save or not.\nRestrictions: Usable only by Bot Admins and above.\nExample: `/unban 488487157372157962 false appealed successfully`", false);
				case "updateluthierchance" -> builder.addField("UpdateLuthierChance Command", "Syntax: `/updateluthierchance`\nUsage: Updates the luthier spawn chance manually\nRestrictions: Usable only by Admins and above.", false);
				
				// PAGE 6
				case "resetdaily" -> builder.addField("ResetDaily Command", "Syntax: `/resetdaily`\nUsage: Gives everyone more time to run `/daily`\nRestrictions: Usable only by Developers.", false);
				case "updateusers" -> builder.addField("UpdateUsers Command", "Syntax: `/updateusers <dataType> <name> [value]`\nUsage: Adds variables to the save file.\nRestrictions: Usable only by Developers.", false);
				case "updateroles" -> builder.addField("UpdateRoles Command", "Syntax: `/updateroles`\nUsage: Updates role multipliers/booster status of users in the support server.\nRestrictions: Usable only by Developers in the Support Server.", false);
				case "setpermlevel" -> builder.addField("SetPermLevel Command", "Syntax: `/setpermlevel <id> <level>`\nUsage: Sets the permission level of the specified user.  `0` for none, `1` for mod, `2` for Admin\nRestrictions: Usable only by Developers.", false);
				case "globalstats" -> builder.addField("GlobalStats Command", "Syntax: `/globalstats`\nUsage: Shows the global statistics of the bot.  Aka combining the stats from all users.\nRestrictions: Usable only by Developers, due to crash concerns.", false);
				case "forcestop" -> builder.addField("ForceStop Command", "Syntax: `/forcestop`\nUsage: Forces the bot to stop.  **USE IN EMERGENCY CIRCUMSTANCES**\nRestrictions: Usable only by Developers.", false);
				default -> builder.addField("Help List", "The page/command you are looking for doesn't exist!  Run `/help` to see a list of pages.", false);
			}
		} catch(Exception exception) {
			builder.addField("Help List", """
					Use `/help [page]` to view further commands!
					
					`1` - Fun Commands
					`2` - Utility Commands
					`3` - Economy Commands
					`4` - Income Commands
					`5` - Bot Moderation Commands
					`6` - Dev-Only Commands""", false);
		}
		e.replyEmbeds(builder.build()).queue();
	}
}
