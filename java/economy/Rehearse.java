package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Random;

public class Rehearse {
	public static void rehearse(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		if((boolean) data.get("orchestra")) {
			long time = System.currentTimeMillis();
			Random random = new Random();
			long violins = (long) data.get("violins");
			long violinsEarned = (long) data.get("earnings");
			if(time < (long) data.get("rehearseCD")) {
				long milliseconds = (long) data.get("rehearseCD") - time;
				long hours = milliseconds / 3600000;
				milliseconds -= hours * 3600000;
				long minutes = milliseconds / 60000;
				milliseconds -= minutes * 60000;
				long seconds = milliseconds / 1000;
				milliseconds -= seconds * 1000;
				e.reply("You don't have the time to go to rehearsal that often, wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
			} else {
				boolean badEvent = false;
				long base = Numbers.calculateAmount(data, random.nextInt(401) + 1300);
				double num = random.nextDouble();
				if(num > 0.5) {
					e.reply("You rehearsed with your orchestra and earned " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
				} else if(num > 0.15) {
					num = random.nextDouble();
					if(num > 0.65) {
						e.reply("Your teacher approved your rehearsal.  Your tiger mom saw the comment, and gave you " + Numbers.formatNumber((long) (base * 0.1)) + Emoji.VIOLINS + " in addition to the " + Numbers.formatNumber(base) + Emoji.VIOLINS + " that you earned.").queue();
						base *= 1.1;
					} else if(num > 0.40) {
						e.reply("Your tiger mom approved your rehearsal.  She gave you " + Numbers.formatNumber((long) (base * 0.5)) + Emoji.VIOLINS + " in addition to the " + Numbers.formatNumber(base) + Emoji.VIOLINS + " that you earned.").queue();
						base *= 1.5;
					} else if(num > 0.2) {
						base *= 2;
						e.reply("Brett and Eddy approved of your rehearsal and doubled the amount of violins you earned.  You got " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
					} else if(num > 0.05) {
						base *= 5;
						e.reply("It's raining violins!  You earned " + Numbers.formatNumber(base) + Emoji.VIOLINS + " you lucky dog").queue();
					} else {
						base *= 15;
						e.reply("**Rare Drop!**\nLing Ling enjoyed your rehearsal but was displeased with some parts.  Nonetheless, he (she?) granted you with " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
						data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 1);
					}
				} else {
					long income = (long) data.get("income");
					num = random.nextDouble();
					if(num > 0.75) {
						base *= 0.95;
						base -= income / 100;
						e.reply("Oh no!  Your E String snapped during the rehearsal!  You had to borrow the concertmaster's violin, and only earned " + Numbers.formatNumber(base) + Emoji.VIOLINS + "  You eventually had to pay " + Numbers.formatNumber(income / 100) + Emoji.VIOLINS + " for a new E String.").queue();
					} else if(num > 0.55) {
						base *= 0.9;
						e.reply("Your violin randomly went out of tune during the rehearsal.  You had to spend 4 minutes tuning it and were only able to earn " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
					} else if(num > 0.4) {
						base *= 0.95;
						e.reply("The orchestra had music stand problems, and page turning wasn't the best either.  You only earned " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
					} else if(num > 0.25) {
						base *= 0.5;
						base -= income / 10;
						e.reply("You hurt your wrist during the rehearsal and only got half of the effectiveness.  You earned " + Numbers.formatNumber(base) + Emoji.VIOLINS + " but ended up paying " + Numbers.formatNumber(income / 10) + Emoji.VIOLINS + " in hospital fees.").queue();
					} else if(num > 0.15) {
						base *= 0.2;
						e.reply("**OOF**\nYour bridge fell off during the rehearsal.  You spend the next half-hour trying to get it back on, and you only earned " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
					} else if(num > 0.05) {
						base = -1 * income;
						e.reply("**OOF**\nYou had a memory blank during rehearsal.  Your tiger mom took all of your earnings, in addition to another " + Numbers.formatNumber(income) + Emoji.VIOLINS + " for shaming yourself.").queue();
					} else if(num > 0.015) {
						e.reply(":regional_indicator_l:\nYour chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise for 12 hours.").mentionRepliedUser(true).queue();
						time += 43200000;
						data.replace("scaleCD", time);
						data.replace("practiceCD", time);
						badEvent = true;
						base = 0;
					} else if(num > 0.005) {
						e.reply(":regional_indicator_l:\nYou decided to fake your solo.  Of course it didn't work and Ling Ling fined you " + Numbers.formatNumber((long) (violins * 0.9)) + Emoji.VIOLINS).mentionRepliedUser(true).queue();
						data.replace("violins", (long) (violins * 0.1));
						badEvent = true;
						base = 0;
					} else {
						e.reply(":skull:\nYou dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined " + Numbers.formatNumber((long) (violins * 0.95)) + Emoji.VIOLINS + " for being careless in public.").mentionRepliedUser(true).queue();
						time += 86400000;
						data.replace("violins", (long) (violins * 0.05));
						data.replace("scaleCD", time);
						data.replace("practiceCD", time);
						data.replace("rehearseCD", time);
						data.replace("performCD", time);
						badEvent = true;
						base = 0;
					}
					violinsEarned += base;
				}
				Numbers.calculateLoan(data, base);
				data.replace("earnings", violinsEarned + base);
				if(!badEvent) {
					if((boolean) data.get("timeCrunch")) {
						data.replace("rehearseCD", time + 28740000);
					} else {
						data.replace("rehearseCD", time + 43140000);
					}
				}
				data.replace("rehearsals", (long) data.get("rehearsals") + 1);
				RNGesus.lootbox(e, data);
				SaveData.saveData(e, data);
			}
		} else {
			e.reply("You must have an orchestra to rehearse with to use this command!").queue();
		}
	}
}