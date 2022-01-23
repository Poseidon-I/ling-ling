package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Random;

public class Perform {
	public Perform(MessageReceivedEvent e) {
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
			e.getMessage().reply("Don't tire yourself with two performances a week!  Wait " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").mentionRepliedUser(false).queue();
		} else {
			boolean badEvent = false;
			long base = Numbers.CalculateAmount(data, random.nextInt(1001) + 4500);
			double num = random.nextDouble();
			if(num > 0.7) {
				violins += base;
				violinsEarned += base;
				e.getMessage().reply("You performed your solo and earned " + Numbers.FormatNumber(base) + ":violin:").mentionRepliedUser(false).queue();
			} else if(num > 0.45) {
				num = random.nextDouble();
				if(num > 0.5) {
					data.replace("rice", (long) data.get("rice") + 9);
					e.getMessage().reply("Your paycheck contained 9:rice: instead of violins.").mentionRepliedUser(false).queue();
				} else if(num > 0.15) {
					data.replace("tea", (long) data.get("tea") + 9);
					e.getMessage().reply("You found 4:bubble_tea: after you performed.").mentionRepliedUser(false).queue();
				} else {
					data.replace("blessings", (long) data.get("blessings") + 9);
					e.getMessage().reply("**Rare Drop!**\nLing Ling enjoyed your performance and blessed you.").mentionRepliedUser(false).queue();
				}
			} else if(num > 0.15) {
				num = random.nextDouble();
				if(num > 0.65) {
					e.getMessage().reply("Your teacher approved your performance.  Your tiger mom saw the comment, and gave you " + Numbers.FormatNumber((long) (base * 0.1)) + ":violin: in addition to the " + Numbers.FormatNumber(base) + ":violin: that you earned.").mentionRepliedUser(false).queue();
					base *= 1.1;
					violins += base;
				} else if(num > 0.40) {
					e.getMessage().reply("Your tiger mom approved your performance.  She gave you " + Numbers.FormatNumber((long) (base * 0.5)) + ":violin: in addition to the " + Numbers.FormatNumber(base) + ":violin: that you earned.").mentionRepliedUser(false).queue();
					base *= 1.5;
					violins += base;
				} else if(num > 0.2) {
					base *= 2;
					violins += base;
					e.getMessage().reply("Brett and Eddy approved of your performance and doubled the amount of violins you earned.  You got " + Numbers.FormatNumber(base) + ":violin:").mentionRepliedUser(false).queue();
				} else if(num > 0.05) {
					base *= 5;
					violins += base;
					e.getMessage().reply("It's raining violins!  You earned " + Numbers.FormatNumber(base) + ":violin: you lucky dog").mentionRepliedUser(false).queue();
				} else if(num > 0.01) {
					base *= 10;
					violins += base;
					e.getMessage().reply("**Rare Drop!**\nThe Berlin Philharmonic posted your performance on their homepage.  You got a HUGE " + Numbers.FormatNumber(base) + ":violin:.\n\nDisclaimer: This does not guarantee a post IRL.").mentionRepliedUser(false).queue();
				} else {
					base *= 25;
					violins += base;
					e.getMessage().reply("**Rare Drop!**\nLing Ling enjoyed your performance but was displeased with some parts.  Nontheless, he (she?) granted you with " + Numbers.FormatNumber(base) + ":violin:").mentionRepliedUser(false).queue();
				}
				violinsEarned += base;
			} else if(num > 0.05) {
				num = random.nextDouble();
				long income = (long) data.get("income");
				if(num > 0.8) {
					violins -= income / 100;
					base *= 0.5;
					violins += base;
					e.getMessage().reply("Oh no!  Your E String snapped during the performance!  You couldn't go on, and only earned " + Numbers.FormatNumber(base) + ":violin:  You eventually had to pay " + Numbers.FormatNumber(income / 100) + ":violin: for a new E String.").mentionRepliedUser(false).queue();
				} else if(num > 0.6) {
					base *= 0.9;
					violins += base;
					e.getMessage().reply("Your violin randomly went out of tune during the rehearsal.  You had to spend 4 minutes tuning it and were only able to earn " + Numbers.FormatNumber(base) + ":violin:").mentionRepliedUser(false).queue();
				} else if(num > 0.45) {
					base *= 0.9;
					e.getMessage().reply("You didn't memorize your piece and you had to use your music.  You only earned " + Numbers.FormatNumber(base) + ":violin:").mentionRepliedUser(false).queue();
				} else if(num > 0.3) {
					base *= 0.5;
					violins += base;
					violins -= income / 10;
					e.getMessage().reply("You hurt your wrist during the performance and only got half of the effectiveness.  You earned " + Numbers.FormatNumber(base) + ":violin: but ended up paying " + Numbers.FormatNumber(income / 10) + ":violin: in hospital fees.").mentionRepliedUser(false).queue();
				} else if(num > 0.2) {
					base *= 0.1;
					violins += base;
					e.getMessage().reply("**OOF**\nYour bridge fell off during the performance.  You spend the next half-hour trying to get it back on, and you only earned " + Numbers.FormatNumber(base) + ":violin:").mentionRepliedUser(false).queue();
				} else if(num > 0.1) {
					violins -= income;
					e.getMessage().reply("**OOF**\nYou had a memory blank during the performance.  Your tiger mom took all your earnings, in addition to another " + Numbers.FormatNumber(income) + " for shaming yourself.").mentionRepliedUser(false).queue();
				} else if(num > 0.05) {
					base *= 0.5;
					violins -= income;
					violins += base;
					e.getMessage().reply("**OOF**\nYour performance was for a competition, and you only won Honorable Mention.  Your tiger mom Kung-Paos your chicken and takes half your earnings, in addition to another " + Numbers.FormatNumber(income) + " for being so mediocre.").mentionRepliedUser(true).queue();
				} else if(num > 0.015) {
					e.getMessage().reply(":regional_indicator_l:]nYour chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise anything for 12 hours.").mentionRepliedUser(true).queue();
					time += 43200000;
					data.replace("scaleCD", time);
					data.replace("practiceCD", time);
					badEvent = true;
				} else if(num > 0.005) {
					e.getMessage().reply(":regional_indicator_l:\nYou decided to fake your performance.  Of course it didn't work and Ling Ling fined you " + Numbers.FormatNumber((long) (violins * 0.95)) + ":violin:").mentionRepliedUser(true).queue();
					violins *= 0.05;
					base = 0;
				} else {
					e.getMessage().reply(":skull:\nYou dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined pretty much your entire balance for being careless on stage.").mentionRepliedUser(true).queue();
					violins *= 0.01;
					base = 0;
					time += 86400000;
					data.replace("scaleCD", time);
					data.replace("practiceCD", time);
					data.replace("rehearseCD", time);
					data.replace("performCD", time);
					badEvent = true;
				}
				violinsEarned += base;
			} else {
				num = random.nextDouble();
				if(num > 0.5) {
					base *= 5;
					data.replace("medals", (long) data.get("medals") + 1);
					data.replace("thirdPlace", (long) data.get("thirdPlace") + 1);
					e.getMessage().reply(":trophy: **Rare Drop!**\nYour performance won third place in the Ling Ling Competition.  Your earnings were multiplied by 5 to " + Numbers.FormatNumber(base) + " and you walked away with 1:military_medal: and a third place trophy :third_place:").mentionRepliedUser(true).queue();
				} else if(num > 0.15) {
					base *= 10;
					data.replace("medals", (long) data.get("medals") + 2);
					data.replace("secondPlace", (long) data.get("secondPlace") + 1);
					e.getMessage().reply(":trophy: **Rare Drop!**\nYour performance won second place.  Your earnings were multiplied by 10 to " + Numbers.FormatNumber(base) + " and you walked away with 2:military_medal: and a second place trophy :second_place:").mentionRepliedUser(true).queue();
				} else {
					base *= 20;
					data.replace("medals", (long) data.get("medals") + 3);
					data.replace("firstPlace", (long) data.get("firstPlace") + 1);
					e.getMessage().reply(":trophy: **CRAZY RARE DROP!**\nYour performance won first place.  Congratulations!  Your earnings were multiplied by 20 to " + Numbers.FormatNumber(base) + " and you walked away with **3**:military_medal: and a FIRST place trophy :first_place:").mentionRepliedUser(true).queue();
				}
			}
			violins += base;
			violinsEarned += base;
			data.replace("violins", violins);
			data.replace("earnings", violinsEarned);
			if(!badEvent) {
				if((boolean) data.get("timeCrunch")) {
					data.replace("performCD", time + 201600000);
				} else {
					data.replace("performCD", time + 302340000);
				}
			}
			data.replace("performances", (long) data.get("performances") + 1);
			RNGesus.Lootbox(e, data);
			new SaveData(e, data);
		}
	}
}