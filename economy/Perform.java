package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Random;

public class Perform {
	public static void perform(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
		long time = System.currentTimeMillis();
		Random random = new Random();
		long violins = (long) data.get("violins");
		long violinsEarned = (long) data.get("earnings");
		if(time < (long) data.get("performCD")) {
			long milliseconds = (long) data.get("performCD") - time;
			long days = milliseconds / 86400000;
			milliseconds -= days * 86400000;
			long hours = milliseconds / 3600000;
			milliseconds -= hours * 3600000;
			long minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			long seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			e.reply("Don't tire yourself with two performances a week!  Wait " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
		} else {
			boolean badEvent = false;
			long base = Numbers.calculateAmount(data, random.nextInt(1001) + 4500);
			double num = random.nextDouble();
			if(num > 0.55) {
				e.reply("You performed your solo and earned " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
			} else if(num > 0.2) {
				num = random.nextDouble();
				if(num > 0.65) {
					e.reply("Your teacher approved your performance.  Your tiger mom saw the comment, and gave you " + Numbers.formatNumber((long) (base * 0.1)) + Emoji.VIOLINS + " in addition to the " + Numbers.formatNumber(base) + Emoji.VIOLINS + " that you earned.").queue();
					base *= 1.1;
				} else if(num > 0.40) {
					e.reply("Your tiger mom approved your performance.  She gave you " + Numbers.formatNumber((long) (base * 0.5)) + Emoji.VIOLINS + " in addition to the " + Numbers.formatNumber(base) + Emoji.VIOLINS + " that you earned.").queue();
					base *= 1.5;
				} else if(num > 0.2) {
					base *= 2;
					e.reply("Brett and Eddy approved of your performance and doubled the amount of violins you earned.  You got " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
				} else if(num > 0.05) {
					base *= 5;
					e.reply("It's raining violins!  You earned " + Numbers.formatNumber(base) + Emoji.VIOLINS + " you lucky dog").queue();
				} else if(num > 0.01) {
					base *= 10;
					e.reply("**Rare Drop!**\nThe Berlin Philharmonic posted your performance on their homepage.  You got a HUGE " + Numbers.formatNumber(base) + Emoji.VIOLINS + "\n\nDisclaimer: This does not guarantee a post IRL.").queue();
					data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 1);
				} else {
					base *= 15;
					e.reply("**Very Rare Drop!**\nLing Ling enjoyed your performance but was displeased with some parts.  Nontheless, he (she?) granted you with " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
					data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 3);
				}
				violinsEarned += base;
			} else if(num > 0.05) {
				num = random.nextDouble();
				long income = (long) data.get("income");
				if(num > 0.8) {
					base *= 0.5;
					base -= income / 100;
					e.reply("Oh no!  Your E String snapped during the performance!  You couldn't go on, and only earned " + Numbers.formatNumber(base) + Emoji.VIOLINS + "  You eventually had to pay " + Numbers.formatNumber(income / 100) + Emoji.VIOLINS + " for a new E String.").queue();
				} else if(num > 0.6) {
					base *= 0.9;
					e.reply("Your violin randomly went out of tune during the rehearsal.  You had to spend 4 minutes tuning it and were only able to earn " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
				} else if(num > 0.45) {
					base *= 0.9;
					e.reply("You didn't memorize your piece and you had to use your music.  You only earned " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
				} else if(num > 0.3) {
					base *= 0.5;
					base -= income / 10;
					e.reply("You hurt your wrist during the performance and only got half of the effectiveness.  You earned " + Numbers.formatNumber(base) + Emoji.VIOLINS + " but ended up paying " + Numbers.formatNumber(income / 10) + Emoji.VIOLINS + " in hospital fees.").queue();
				} else if(num > 0.2) {
					base *= 0.1;
					e.reply("**OOF**\nYour bridge fell off during the performance.  You spend the next half-hour trying to get it back on, and you only earned " + Numbers.formatNumber(base) + Emoji.VIOLINS).queue();
				} else if(num > 0.1) {
					base = -1 * income;
					e.reply("**OOF**\nYou had a memory blank during the performance.  Your tiger mom took all your earnings, in addition to another " + Numbers.formatNumber(income) + Emoji.VIOLINS + " for shaming yourself.").queue();
				} else if(num > 0.05) {
					base *= 0.5;
					base -= income;
					e.reply("**OOF**\nYour performance was for a competition, and you only won Honorable Mention.  Your tiger mom Kung-Paos your chicken and takes half your earnings, in addition to another " + Numbers.formatNumber(income) + Emoji.VIOLINS + " for being so mediocre.").mentionRepliedUser(true).queue();
				} else if(num > 0.015) {
					e.reply(":regional_indicator_l:]nYour chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise anything for 12 hours.").mentionRepliedUser(true).queue();
					time += 43200000;
					data.replace("scaleCD", time);
					data.replace("practiceCD", time);
					badEvent = true;
					base = 0;
				} else if(num > 0.005) {
					e.reply(":regional_indicator_l:\nYou decided to fake your performance.  Of course it didn't work and Ling Ling fined you " + Numbers.formatNumber((long) (violins * 0.95)) + Emoji.VIOLINS).mentionRepliedUser(true).queue();
					data.replace("violins", (long) (violins * 0.05));
					base = 0;
				} else {
					e.reply("**wow RNGesus *__really__* hates you**\nYou dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined pretty much your entire balance for being careless on stage.").mentionRepliedUser(true).queue();
					time += 86400000;
					data.replace("violins", (long) (violins * 0.01));
					data.replace("scaleCD", time);
					data.replace("practiceCD", time);
					data.replace("rehearseCD", time);
					data.replace("performCD", time);
					badEvent = true;
					base = 0;
				}
				violinsEarned += base;
			} else {
				num = random.nextDouble();
				if(num > 0.5) {
					base *= 5;
					data.replace("blessings", (long) data.get("blessings") + 1);
					data.replace("thirdPlace", (long) data.get("thirdPlace") + 1);
					e.reply(":trophy: **Rare Drop!**\nYour performance won third place in the Ling Ling Competition.  Your earnings were multiplied by 5 to " + Numbers.formatNumber(base) + " and you walked away with 1" + Emoji.BLESSING + " and a third place medal " + Emoji.THIRD_PLACE).mentionRepliedUser(true).queue();
					data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 1);
				} else if(num > 0.15) {
					base *= 10;
					data.replace("blessings", (long) data.get("blessings") + 2);
					data.replace("secondPlace", (long) data.get("secondPlace") + 1);
					e.reply(":trophy: **Rare Drop!**\nYour performance won second place.  Your earnings were multiplied by 10 to " + Numbers.formatNumber(base) + " and you walked away with 2" + Emoji.BLESSING + " and a second place medal " + Emoji.SECOND_PLACE).mentionRepliedUser(true).queue();
					data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 1);
				} else {
					base *= 20;
					data.replace("blessings", (long) data.get("blessings") + 3);
					data.replace("firstPlace", (long) data.get("firstPlace") + 1);
					e.reply(":trophy: **Very Rare Drop!**\nYour performance won first place.  Congratulations!  Your earnings were multiplied by 20 to " + Numbers.formatNumber(base) + " and you walked away with **3**" + Emoji.BLESSING + " and a FIRST place medal " + Emoji.FIRST_PLACE).mentionRepliedUser(true).queue();
					data.replace("RNGesusWeight", (long) data.get("RNGesusWeight") + 3);
				}
			}
			Numbers.calculateLoan(data, base);
			data.replace("earnings", violinsEarned + base);
			if(!badEvent) {
				if((boolean) data.get("timeCrunch")) {
					data.replace("performCD", time + 201600000);
				} else {
					data.replace("performCD", time + 302340000);
				}
			}
			data.replace("performances", (long) data.get("performances") + 1);
			RNGesus.lootbox(e, data);
			SaveData.saveData(e, data);
		}
	}
}