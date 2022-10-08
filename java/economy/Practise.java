package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Random;

public class Practise {
	public static void practise(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		long time = System.currentTimeMillis();
		Random random = new Random();
		long violins = (long) data.get("violins");
		if(time < (long) data.get("practiceCD")) {
			long milliseconds = (long) data.get("practiceCD") - time;
			long minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			long seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			e.reply("Take a break, you already practised a lot!  Wait " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
		} else {
			boolean badEvent = false;
			long base = Numbers.calculateAmount(data, random.nextInt(101) + 400);
			double num = random.nextDouble();
			if(num > 0.5) {
				e.reply("You practised for 45 minutes and earned " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
				data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.75);
			} else if(num > 0.15) {
				num = random.nextDouble();
				if(num > 0.65) {
					e.reply("Your teacher approved your practise session.  Your tiger mom saw the comment, and gave you " + Numbers.formatNumber((long) (base * 0.1)) + Emoji.VIOLINS + " in addition to the " + Numbers.formatNumber(base) + Emoji.VIOLINS + " that you earned.").queue();
					base *= 1.1;
				} else if(num > 0.40) {
					e.reply("Your tiger mom approved your practise session.  She gave you " + Numbers.formatNumber((long) (base * 0.5)) + Emoji.VIOLINS + " in addition to the " + Numbers.formatNumber(base) + Emoji.VIOLINS + " that you earned.").queue();
					base *= 1.5;
				} else if(num > 0.2) {
					base *= 2;
					e.reply("Brett and Eddy approved of your practise session and doubled the amount of violins you earned.  You got " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
				} else if(num > 0.05) {
					base *= 5;
					e.reply("It's raining violins!  You earned " + Numbers.formatNumber(base) + Emoji.VIOLINS + " you lucky dog").queue();
				} else {
					base *= 15;
					e.reply("**Rare Drop!**\nLing Ling enjoyed your practise session but was displeased with some parts.  Nonetheless, he (she?) granted you with " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
					data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 1);
				}
				data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.75);
			} else {
				num = random.nextDouble();
				long income = (long) data.get("income");
				if(num > 0.75) {
					data.replace("violins", violins - income / 100);
					base = 0;
					e.reply("Oh no!  Your E String snapped while you were practising!  You had to go to the store to get it replaced, and were not able to get any practising done.  You earned 0" + Emoji.VIOLINS + " and had to pay " + Numbers.formatNumber(income / 100) + "<:violin:1019787510295048272> for a new E String.").queue();
				} else if(num > 0.55) {
					base *= 0.9;
					data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.675);
					e.reply("Your violin randomly went out of tune while you were practising.  You had to spend 4 minutes tuning it and were only able to earn " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
				} else if(num > 0.4) {
					base *= 0.95;
					data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.7);
					e.reply("You had problems with your music stand, and page turning wasn't the best this session.  You earned " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
				} else if(num > 0.25) {
					base *= 0.5;
					base -= income / 10;
					data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.375);
					e.reply("You hurt your wrist while practising and only got half of the effectiveness.  You earned " + Numbers.formatNumber(base) + Emoji.VIOLINS + " but ended up paying " + Numbers.formatNumber(income / 10) + Emoji.VIOLINS + " in hospital fees.").queue();
				} else if(num > 0.15) {
					base *= 0.2;
					data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.35);
					e.reply("**OOF**\nYour bridge fell off 15 minutes into your session.  You spend the next half-hour trying to get it back on, and you only earned " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
				} else if(num > 0.05) {
					base = -1 * income;
					e.reply("**OOF**\nYou were caught playing Minecraft while practising.  Your tiger mom took all your earnings, in addition to another " + Numbers.formatNumber(income) + Emoji.VIOLINS + " for being distracted.").queue();
				} else if(num > 0.015) {
					e.reply(":regional_indicator_l:\nYour chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise for 12 hours.").mentionRepliedUser(true).queue();
					time += 43200000;
					data.replace("scaleCD", time);
					data.replace("practiceCD", time);
					badEvent = true;
					base = 0;
				} else if(num > 0.005) {
					e.reply(":regional_indicator_l:\nYou decided to fake your practise session.  Ling Ling caught you in the act, and fined you " + Numbers.formatNumber((long) (violins * 0.8)) + Emoji.VIOLINS).mentionRepliedUser(true).queue();
					data.replace("violins", (long) (violins * 0.2));
					base = 0;
				} else {
					e.reply(":skull:\nYou dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined " + Numbers.formatNumber((long) (violins * 0.9)) + Emoji.VIOLINS + " for being careless.").mentionRepliedUser(true).queue();
					time += 86400000;
					data.replace("violins", (long) (violins * 0.1));
					data.replace("scaleCD", time);
					data.replace("practiceCD", time);
					data.replace("rehearseCD", time);
					data.replace("performCD", time);
					badEvent = true;
					base = 0;
				}
			}
			Numbers.calculateLoan(data, base);
			data.replace("earnings", (long) data.get("earnings") + base);
			if(!badEvent) {
				if((boolean) data.get("timeCrunch")) {
					data.replace("practiceCD", time + 1740000);
				} else {
					data.replace("practiceCD", time + 2640000);
				}
			}
			RNGesus.lootbox(e, data);
			SaveData.saveData(e, data);
		}
	}
}