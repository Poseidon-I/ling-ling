package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Numbers;
import processes.Prefix;

import java.awt.*;

public class Upgrades {
	public static void IncomeUpgrades(MessageReceivedEvent e, JSONObject data, char prefix) {
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("User balance: " + Numbers.FormatNumber(data.get("violins")) + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
				.addField("Violin Quality (" + Numbers.FormatNumber(data.get("violinQuality")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("violinQuality"), 3, 1000)) + ":violin:\nEffect: +600:violin:/hour\nID: `violin`, `v`", false)
				.addField("Skill Level (" + Numbers.FormatNumber(data.get("skills")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("skills"), 2, 500)) + ":violin:\nEffect: +240:violin:/hour\nID: `skill`, `s`", false)
				.addField("Lesson Quality (" + Numbers.FormatNumber(data.get("lessonQuality")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("lessonQuality"), 2.5, 750)) + ":violin:\nEffect: +150:violin:/hour\nID: `lesson`, `l`", false)
				.addField("String Quality (" + Numbers.FormatNumber(data.get("stringQuality")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("stringQuality"), 1.75, 500)) + ":violin:\nEffect: +100:violin:/hour\nID: `string`, `str`", false)
				.addField("Bow Quality (" + Numbers.FormatNumber(data.get("bowQuality")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("bowQuality"), 3, 750)) + ":violin:\nEffect: +200:violin:/hour\nID: `bow`, `b`", false)
				.setTitle("__**Income Upgrades**__");
		if((boolean) data.get("math")) {
			builder.addField("Math Tutoring :white_check_mark:", "Effect: +6500:violin:/hour\nID: `math`", false);
		} else {
			builder.addField("Math Tutoring :x:", "Price: 10 000 000:violin:\nEffect: +6500:violin:/hour\nID: `math`", false);
		}
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
	
	public static void OrchMiscUpgrades(MessageReceivedEvent e, JSONObject data, char prefix) {
		EmbedBuilder builder;
		if(!(boolean) data.get("orchestra")) {
			builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setFooter("User balance: " + Numbers.FormatNumber(data.get("violins")) + "\nUser `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
					.addField("Concert Hall Quality (" + Numbers.FormatNumber(data.get("hall")) + "/3)", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("hall"), 4, 100000) )+ ":violin:\nEffect: +300:violin:/hour, +10% violins from `" + prefix + "scales`, `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID: `concert`, `hall`", false)
					.addField("Orchestra", "Price: 25 000 000:violin:\nIncome Requirement: 7 500\nEffect: +3 100:violin:/hour, access to `" + prefix + "rehearse` command\nID:`orchestra`, `o`", false)
					.setTitle("__**Miscellaneous Orchestra Items**__");
		} else if(!(boolean) data.get("certificate")) {
			builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setFooter("User balance: " + Numbers.FormatNumber(data.get("violins")) + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
					.addField("Concert Hall Quality (" + Numbers.FormatNumber(data.get("hall")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("hall"), 4, 100000)) + ":violin:\nEffect: +300:violin:/hour, +10% violins from `" + prefix + "scales`, `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID: `concert`, `hall`", false)
					.addField("Conductor Musicality (" + Numbers.FormatNumber(data.get("conductor")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("conductor"), 4, 100000)) + ":violin:\nEffect: +200:violin:/hour\nID: `conductor`, `musicality`", false)
					.addField("Ticket Price (" + Numbers.FormatNumber(data.get("tickets")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("tickets"), 2, 1000000)) + ":violin:\nEffect: +1000:violin:/hour\nID: `tickets`", false)
					.addField("Advertising (" + Numbers.FormatNumber(data.get("advertising")) + "/20)", "Price: " + Numbers.FormatNumber(100000 * ((long) data.get("advertising")) + 1) + ":violin:\nEffect: +100:violin:/hour\nID: `advertisement`, `ad`", false)
					.addField("Teaching Certificate", "Price: 200 000 000:violin:\nIncome Requirement: 40 000:violin:/hr\nEffect: +5 000:violin:/hr, access to `" + prefix + "teach` command\nID: `certificate`", false)
					.setTitle("__**Miscellaneous Orchestra Items**__");
		} else {
			builder = new EmbedBuilder()
					.setColor(Color.BLUE)
					.setFooter("User balance: " + Numbers.FormatNumber(data.get("violins")) + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
					.addField("Concert Hall Quality (" + Numbers.FormatNumber(data.get("hall")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("hall"), 4, 100000)) + ":violin:\nEffect: +300:violin:/hour, +10% violins from `" + prefix + "scales`, `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID: `concert`, `hall`", false)
					.addField("Conductor Musicality (" + Numbers.FormatNumber(data.get("conductor")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("conductor"), 4, 100000)) + ":violin:\nEffect: +200:violin:/hour\nID: `conductor`, `musicality`", false)
					.addField("Ticket Price (" + Numbers.FormatNumber(data.get("tickets")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("tickets"), 2, 1000000)) + ":violin:\nEffect: +1000:violin:/hour\nID: `tickets`", false)
					.addField("Advertising (" + Numbers.FormatNumber(data.get("advertising")) + "/20)", "Price: " + Numbers.FormatNumber(100000 * ((long) data.get("advertising")) + 1) + ":violin:\nEffect: +100:violin:/hour\nID: `advertisement`, `ad`", false)
					.setTitle("__**Miscellaneous Orchestra Items**__");
		}
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
	
	public static void OtherMiscUpgrades(MessageReceivedEvent e, JSONObject data, char prefix) {
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("User balance: " + Numbers.FormatNumber(data.get("violins")) + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
				.addField("Efficient Practising (" + Numbers.FormatNumber(data.get("efficiency")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("efficiency"), 1.1, 400)) + ":violin:\nEffect: Increases your income from `" + prefix + "scales`, `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` by 4% (10% for Levels 1-20).\nID: `efficiency`, `ep`", false)
				.addField("Lucky Musician (" + Numbers.FormatNumber(data.get("luck")) + "/50)", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("luck"), 1.25, 1000)) + ":violin:\nEffect: Increases your gambling multiplier by 0.5%.\nID: `lucky`, `lm`", false)
				.addField("Sophisticated Robbing (" + Numbers.FormatNumber(data.get("sophistication")) + "/30)", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("sophistication"), 1.4, 5000)) + ":violin:\nEffect: Increases your chance of a successful `" + prefix + "rob` by 0.25%.\nID: `sophistication`, `sr`", false)
				.setTitle("__**Other Miscellaneous Upgrades**__");
		if((boolean) data.get("insurance")) {
			builder.addField("Ling Ling Insurance :white_check_mark:", "Effect: When robbed, this will protect 50% of violins from being stolen.\nID: `insurance`", false);
		} else {
			builder.addField("Ling Ling Insurance :x:", "Price: 2 500 000:violin:\nEffect: When robbed, this will protect 50% of violins from being stolen.\nID: `insurance`", false);
		}
		if((boolean) data.get("timeCrunch")) {
			builder.addField("Time Crunch :white_check_mark:", "Effect: Decreases `" + prefix + "scales`, `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `tc`, `timecrunch`", false);
		} else {
			builder.addField("Time Crunch :x:", "Price: 120 000 000:violin:\nEffect: Decreases `" + prefix + "scales`, `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `tc`, `timecrunch`", false);
		}
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
	
	public static void MedalUpgrades(MessageReceivedEvent e, JSONObject data, char prefix) {
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Medals: " + Numbers.FormatNumber(data.get("medals")) + "\nEarn medals from `" + prefix + "perform`, Blessings, and Lootboxes!", e.getJDA().getSelfUser().getAvatarUrl())
				.setTitle("**__Medal Upgrades__**")
				.addField("Extra Income (" + Numbers.FormatNumber(data.get("moreIncome")) + ")", "Price: " + Numbers.FormatNumber(((long) data.get("moreIncome") + 1)) + ":military_medal:\nEffect: +2000:violin:/hour\nID: `income`", false)
				.addField("Extra Command Income (" + Numbers.FormatNumber(data.get("moreCommandIncome")) + ")", "Price: " + Numbers.FormatNumber((long) Math.pow(2, (long) data.get("moreCommandIncome"))) + ":military_medal:\nEffect: Increases income from all commands except `!teach` by 25%\nID: `commandincome`", false)
				.addField("Higher Gamble Limit (" + Numbers.FormatNumber(data.get("moreMulti")) + ")", "Price: " + Numbers.FormatNumber(((long) data.get("moreMulti") + 1)) + ":military_medal:\nEffect: +1x Gamble Cap\nID: `gamblelimit`", false)
				.addField("Higher Rob Success Rate (" + Numbers.FormatNumber(data.get("moreRob")) + ")", "Price: " + Numbers.FormatNumber((long) Math.pow(2, (long) data.get("moreRob"))) + ":military_medal:\nEffect: Increases the chance of successfully robbing someone by 0.3%\nID: `robsuccess`", false);
		if((boolean) data.get("shield")) {
			builder.addField("Steal Shield :white_check_mark:", "Effect: Advanced technology takes back 50% of violins when you get robbed", false);
		} else {
			builder.addField("Steal Shield :x:", "Price: 10:military_medal:\nEffect: Advanced technology takes back 50% of violins when you get robbed\nID: `shield`", false);
		}
		if((boolean) data.get("duplicator")) {
			builder.addField("Violin Duplicator :white_check_mark:", "Effect: The Vengeful God of Ben Lee duplicates all violins stolen", false);
		} else {
			builder.addField("Violin Duplicator :x:", "Price: 15:military_medal:\nEffect: The Vengeful God of Ben Lee duplicates all violins stolen\nID: `duplicator`", false);
		}
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
	
	public static void Woodwinds(MessageReceivedEvent e, JSONObject data, char prefix) {
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("User balance: " + Numbers.FormatNumber(data.get("violins")) + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl());
		if((boolean) data.get("piccolo")) {
			builder.addField("Piccolo :white_check_mark:", "Effect: +30:violin:/hour\nID: `piccolo`", false);
		} else {
			builder.addField("Piccolo :x:", "Price: 200 000:violin:\nEffect: +30:violin:/hour\nID: `piccolo`", false);
		}
		builder.addField("Flutes (" + Numbers.FormatNumber(data.get("flute")) + "/4)", "Price: " + Numbers.FormatNumber(250000 * ((long) data.get("flute")) + 1) + ":violin:\nEffect: +60:violin:/hour\nID: `flute`", false)
				.addField("Oboes (" + Numbers.FormatNumber(data.get("oboe")) + "/4)", "Price: " + Numbers.FormatNumber(250000 * ((long) data.get("oboe")) + 1) + ":violin:\nEffect: +50:violin:/hour\nID: `oboe`", false)
				.addField("Clarinets (" + Numbers.FormatNumber(data.get("clarinet")) + "/4)", "Price: " + Numbers.FormatNumber(200000 * ((long) data.get("clarinet")) + 1) + ":violin:\nEffect: +40:violin:/hour\nID: `clarinet`", false)
				.addField("Bassoons (" + Numbers.FormatNumber(data.get("bassoon")) + "/4)", "Price: " + Numbers.FormatNumber(200000 * ((long) data.get("bassoon")) + 1) + ":violin:\nEffect: +40:violin:/hour\nID: `bassoon`", false)
				.setTitle("__**Woodwinds**__");
		if((boolean) data.get("contraBassoon")) {
			builder.addField("Contrabassoon :white_check_mark:", "Effect: +30:violin:/hour\nID: `contrabassoon`, `cb`", false);
		} else {
			builder.addField("Contrabassoon :x:", "Price: 250 000:violin:\nEffect: +30:violin:/hour\nID: `contrabassoon`, `cb`", false);
		}
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
	
	public static void Brass(MessageReceivedEvent e, JSONObject data, char prefix) {
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("User balance: " + Numbers.FormatNumber(data.get("violins")) + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
				.addField("French Horns (" + Numbers.FormatNumber(data.get("horn")) + "/8)", "Price: " + Numbers.FormatNumber(200000 * ((long) data.get("horn")) + 1) + ":violin:\nEffect: +40:violin:/hour\nID: `horn`", false)
				.addField("Trumpet (" + Numbers.FormatNumber(data.get("trumpet")) + "/4)", "Price: " + Numbers.FormatNumber(200000 * ((long) data.get("trumpet")) + 1) + ":violin:\nEffect: +30:violin:/hour\nID: `trumpet`", false)
				.addField("Trombone (" + Numbers.FormatNumber(data.get("trombone")) + "/6)", "Price: " + Numbers.FormatNumber(200000 * ((long) data.get("trombone")) + 1) + ":violin:\nEffect: +20:violin:/hour\nID: `trombone`", false)
				.addField("Tuba (" + Numbers.FormatNumber(data.get("tuba")) + "/2)", "Price: " + Numbers.FormatNumber(200000 * ((long) data.get("tuba")) + 1) + ":violin:\nEffect: +20:violin:/hour\nID: `tuba`", false)
				.addField("Timpani (" + Numbers.FormatNumber(data.get("timpani")) + "/2)", "Price: " + Numbers.FormatNumber(250000 * ((long) data.get("timpani")) + 1) + ":violin:\nEffect: +60:violin:/hour\nID: `timpani`", false)
				.addField("Percussionists (" + Numbers.FormatNumber(data.get("percussion")) + "/2)", "Price: " + Numbers.FormatNumber(100000 * ((long) data.get("percussion")) + 1) + ":violin:\nEffect: +10:violin:/hour\nID: `percussion`", false)
				.setTitle("__**Brass and Percussion**__");
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
	
	public static void Strings(MessageReceivedEvent e, JSONObject data, char prefix) {
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("User balance: " + Numbers.FormatNumber(data.get("violins")) + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
				.addField("Violin I (" + Numbers.FormatNumber(data.get("violin1")) + "/20)", "Price: " + Numbers.FormatNumber(450000 * (long) data.get("violin1")) + ":violin:\nEffect: +70:violin:/hour\nID: `first`", false)
				.addField("Violin II (" + Numbers.FormatNumber(data.get("violin2")) + "/20)", "Price: " + Numbers.FormatNumber(350000 * (long) data.get("violin2")) + ":violin:\nEffect: +60:violin:/hour\nID: `second`", false)
				.addField("Cellos (" + Numbers.FormatNumber(data.get("cello")) + "/15)", "Price: " + Numbers.FormatNumber(300000 * ((long) data.get("cello")) + 1) + ":violin:\nEffect: +50:violin:/hour\nID: `cello`", false)
				.addField("Double Basses (" + Numbers.FormatNumber(data.get("doubleBass")) + "/5)", "Price: " + Numbers.FormatNumber(300000 * ((long) data.get("doubleBass")) + 1) + ":violin:\nEffect: +50:violin:/hour\nID: `doublebass`, `db`", false)
				.addField("Pianists (" + Numbers.FormatNumber(data.get("piano")) + "/2)", "Price: " + Numbers.FormatNumber(750000 * ((long) data.get("piano")) + 1) + ":violin:\nEffect: +110:violin:/hour\nID: `piano`", false)
				.setTitle("__**Strings**__");
		if((boolean) data.get("harp")) {
			builder.addField("Harp :white_check_mark:", "Effect: +80:violin:/hour\nID: `harp`", false);
		} else {
			builder.addField("Harp :x:", "Price: 350 000:violin:\nEffect: +80:violin:/hour\nID: `harp`", false);
		}
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
	
	public static void Choir(MessageReceivedEvent e, JSONObject data, char prefix) {
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("User balance: " + Numbers.FormatNumber(data.get("violins")) + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
				.addField("Sopranos (" + Numbers.FormatNumber(data.get("soprano")) + "/20)", "Price: " + Numbers.FormatNumber(80000 * ((long) data.get("soprano")) + 1) + ":violin:\nEffect: +30:violin:/hour\nID: `soprano`", false)
				.addField("Altos (" + Numbers.FormatNumber(data.get("alto")) + "/20)", "Price: " + Numbers.FormatNumber(60000 * ((long) data.get("alto")) + 1) + ":violin:\nEffect: +20:violin:/hour\nID: `alto`", false)
				.addField("Tenors (" + Numbers.FormatNumber(data.get("tenor")) + "/20)", "Price: " + Numbers.FormatNumber(60000 * ((long) data.get("tenor")) + 1) + ":violin:\nEffect: +20:violin:/hour\nID: `tenor`", false)
				.addField("Basses (" + Numbers.FormatNumber(data.get("bass")) + "/20)", "Price: " + Numbers.FormatNumber(60000 * ((long) data.get("bass")) + 1) + ":violin:\nEffect: +20:violin:/hour\nID: `bass`", false)
				.addField("Vocal Soloists (" + Numbers.FormatNumber(data.get("soloist")) + "/4)", "Price: " + Numbers.FormatNumber(250000 * ((long) data.get("soloist")) + 1) + ":violin:\nEffect: +60:violin:/hour\nID: `soloist`", false)
				.setTitle("__**Choir**__");
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
	
	public static void TeacherUpgrades(MessageReceivedEvent e, JSONObject data, char prefix) {
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("User balance: " + Numbers.FormatNumber(data.get("violins")) + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
				.addField("More Students (" + Numbers.FormatNumber(data.get("students")) + ")", "Price: " + Numbers.FormatNumber(Numbers.ItemCost((long) data.get("students"), 2, 1000000)) + ":violin:\nEffect: +2 000:violin:/hour, +15% violins from `" + prefix + "teach`\nID: `students`", false)
				.addField("Higher Lesson Rates (" + Numbers.FormatNumber(data.get("lessonCharge")) + "/5)", "Price: " + Numbers.FormatNumber(3000000 * ((long) data.get("lessonCharge")) + 1) + ":violin:\nEffect: +3 000:violin:/hour, +10% violins from `" + prefix + "teach`\nID: `pricing`", false)
				.addField("Teacher Training (" + Numbers.FormatNumber(data.get("training")) + "/10)", "Price: " + Numbers.FormatNumber(2000000 * ((long) data.get("training")) + 1) + ":violin:\nEffect: +1 000:violin:/hour, +5% violins from `" + prefix + "teach`\nID: `training`", false);
		if((boolean) data.get("studio")) {
			builder.addField("Teaching Studio :white_check_mark:", "Effect: +5 000:violin:/hour\nID: `studio`", false);
		} else {
			builder.addField("Teaching Studio :x:", "Price: 20 000 000:violin\nEffect: +5 000:violin:/hour\nID: `studio`", false);
		}
		if((boolean) data.get("longerLessons")) {
			builder.addField("Longer Lessons :white_check_mark:", "Effect: +100% violins from `" + prefix + "teach`\nID: `longer`", false);
		} else {
			builder.addField("Longer Lessons :x:", "Price: 30 000 000:violin:\nEffect: +100% violins from `" + prefix + "teach`\nID: `longer`", false);
		}
		builder.setTitle("__**Teacher Upgrades**__");
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
	
	public static void BankUpgrades(MessageReceivedEvent e, JSONObject data, char prefix) {
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.BLUE)
				.setFooter("Medals: " + Numbers.FormatNumber(data.get("medals")) + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
				.addField("Storage Space (" + Numbers.FormatNumber(data.get("storage")) + ")", "Price: " + Numbers.FormatNumber(Numbers.FormatNumber(3 * (long) data.get("storage"))) + ":military_medal:\nEffect: +15 000 000 bankspace\nID: `space`", false);
		if((boolean) data.get("moreInterest")) {
			builder.addField("Higher Interest :white_check_mark:", "Effect: Gain 2% interest instead of 1%\nID: `interest`", false);
		} else {
			builder.addField("Higher Interest :x:", "Price: 15:military_medal:\nEffect: Gain 2% interest instead of 1%\nID: `interest`", false);
		}
		if((boolean) data.get("lessPenalty")) {
			builder.addField("Lower Loan Interest :white_check_mark:", "Effect: Decrease loan interest rate to 9%\nID: `lower`", false);
		} else {
			builder.addField("Lower Loan Interest :x:", "Price: 15:military_medal:\nEffect: Decrease loan interest rate to 9%\nID: `lower`", false);
		}
		builder.setTitle("__**Bank Upgrades**__");
		e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
	}
	
	public Upgrades(MessageReceivedEvent e) {
		char prefix = Prefix.GetPrefix(e);
		JSONObject data = LoadData.loadData(e);
		String[] message = e.getMessage().getContentRaw().split(" ");
		if(message.length == 0) {
			e.getMessage().reply("You must provide a page number!").mentionRepliedUser(false).queue();
		} else {
			try {
				if(!(boolean) data.get("orchestra")) {
					switch(message[1]) {
						case "1" -> IncomeUpgrades(e, data, prefix);
						case "2" -> OrchMiscUpgrades(e, data, prefix);
						case "3" -> OtherMiscUpgrades(e, data, prefix);
						case "4" -> MedalUpgrades(e, data, prefix);
						default -> e.getMessage().reply("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Miscellaneous Orchestra Items\n`3` for Other Miscellaneous Upgrades\n`4` for Medal Upgrades").mentionRepliedUser(false).queue();
					}
				} else if(!(boolean) data.get("certificate")) {
					switch(message[1]) {
						case "1" -> IncomeUpgrades(e, data, prefix);
						case "2" -> Woodwinds(e, data, prefix);
						case "3" -> Brass(e, data, prefix);
						case "4" -> Strings(e, data, prefix);
						case "5" -> Choir(e, data, prefix);
						case "6" -> OrchMiscUpgrades(e, data, prefix);
						case "7" -> OtherMiscUpgrades(e, data, prefix);
						case "8" -> MedalUpgrades(e, data, prefix);
						case "9" -> BankUpgrades(e, data, prefix);
						default -> e.getMessage().reply("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Woodwinds\n`3` for Brass and Percussion\n`4` for Strings\n`5` for Choir\n`6` for Miscellaneous Orchestra Items\n`7` for Other Miscellaneous Upgrades\n`8` for Medal Upgrades\n`9` for Bank Upgrades").mentionRepliedUser(false).queue();
					}
				} else {
					switch(message[1]) {
						case "1" -> IncomeUpgrades(e, data, prefix);
						case "2" -> Woodwinds(e, data, prefix);
						case "3" -> Brass(e, data, prefix);
						case "4" -> Strings(e, data, prefix);
						case "5" -> Choir(e, data, prefix);
						case "6" -> OrchMiscUpgrades(e, data, prefix);
						case "7" -> TeacherUpgrades(e, data, prefix);
						case "8" -> OtherMiscUpgrades(e, data, prefix);
						case "9" -> MedalUpgrades(e, data, prefix);
						case "10" -> BankUpgrades(e, data, prefix);
						default -> e.getMessage().reply("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Woodwinds\n`3` for Brass and Percussion\n`4` for Strings\n`5` for Choir\n`6` for Miscellaneous Orchestra Items\n`7` for Teacher Upgrades\n`8` for Other Miscellaneous Upgrades\n`9` for Medal Upgrades\n`10` for Bank Upgrades").mentionRepliedUser(false).queue();
					}
				}
			} catch(Exception exception) {
				if(!(boolean) data.get("orchestra")) {
					e.getMessage().reply("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Miscellaneous Orchestra Items\n`3` for Other Miscellaneous Upgrades\n`4` for Medal Upgrades").mentionRepliedUser(false).queue();
				} else if(!(boolean) data.get("certificate")) {
					e.getMessage().reply("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Woodwinds\n`3` for Brass and Percussion\n`4` for Strings\n`5` for Choir\n`6` for Miscellaneous Orchestra Items\n`7` for Other Miscellaneous Upgrades\n`8` for Medal Upgrades\n`9` for Bank Upgrades").mentionRepliedUser(false).queue();
				} else {
					e.getMessage().reply("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Woodwinds\n`3` for Brass and Percussion\n`4` for Strings\n`5` for Choir\n`6` for Miscellaneous Orchestra Items\n`7` for Teacher Upgrades\n`8` for Other Miscellaneous Upgrades\n`9` for Medal Upgrades\n`10` for Bank Upgrades").mentionRepliedUser(false).queue();
				}
			}
		}
	}
}