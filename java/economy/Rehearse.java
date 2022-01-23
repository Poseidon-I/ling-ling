package economy;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.util.Random;

public class Rehearse {
	public Rehearse(MessageReceivedEvent e) {
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
				e.getMessage().reply("You don't have the time to go to rehearsal that often, wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").mentionRepliedUser(false).queue();
			} else {
				boolean badEvent = false;
				long base = Numbers.CalculateAmount(data, random.nextInt(501) + 1750);
				double num = random.nextDouble();
				if(num > 0.7) {
					violins += base;
					e.getMessage().reply("You rehearsed with your orchestra and earned " + Numbers.FormatNumber(base)+ ":violin:").mentionRepliedUser(false).queue();
					violinsEarned += base;
				} else if(num > 0.4) {
					num = random.nextDouble();
					if(num > 0.5) {
						data.replace("rice", (long) data.get("rice") + 3);
						e.getMessage().reply("You found 3:rice: after rehearsal but didn't get any violins.").mentionRepliedUser(false).queue();
					} else if(num > 0.15) {
						data.replace("tea", (long) data.get("tea") + 2);
						e.getMessage().reply("You found 2:bubble_tea: after you went to rehearsal.").mentionRepliedUser(false).queue();
					} else {
						data.replace("blessings", (long) data.get("blessings") + 1);
						e.getMessage().reply("**Rare Drop!**\nLing Ling enjoyed your rehearsal session and blessed you.").mentionRepliedUser(false).queue();
					}
				} else if(num > 0.1) {
					num = random.nextDouble();
					if(num > 0.65) {
						e.getMessage().reply("Your teacher approved your rehearsal.  Your tiger mom saw the comment, and gave you " + Numbers.FormatNumber((long) (base * 0.1)) + ":violin: in addition to the " + Numbers.FormatNumber(base)+ ":violin: that you earned.").mentionRepliedUser(false).queue();
						base *= 1.1;
					} else if(num > 0.40) {
						e.getMessage().reply("Your tiger mom approved your rehearsal.  She gave you " + Numbers.FormatNumber((long) (base * 0.5)) + ":violin: in addition to the " + Numbers.FormatNumber(base)+ ":violin: that you earned.").mentionRepliedUser(false).queue();
						base *= 1.5;
					} else if(num > 0.2) {
						base *= 2;
						e.getMessage().reply("Brett and Eddy approved of your rehearsal and doubled the amount of violins you earned.  You got " + Numbers.FormatNumber(base)+ ":violin:").mentionRepliedUser(false).queue();
					} else if(num > 0.05) {
						base *= 5;
						e.getMessage().reply("It's raining violins!  You earned " + Numbers.FormatNumber(base)+ ":violin: you lucky dog").mentionRepliedUser(false).queue();
					} else {
						base *= 25;
						e.getMessage().reply("**Rare Drop!**\nLing Ling enjoyed your rehearsal but was displeased with some parts.  Nonetheless, he (she?) granted you with " + Numbers.FormatNumber(base)+ ":violin:").mentionRepliedUser(false).queue();
					}
					violins += base;
					violinsEarned += base;
				} else {
					long income = (long) data.get("income");
					num = random.nextDouble();
					if(num > 0.75) {
						violins -= income / 100;
						base *= 0.95;
						violins += base;
						e.getMessage().reply("Oh no!  Your E String snapped during the rehearsal!  You had to borrow the concertmaster's violin, and only earned " + Numbers.FormatNumber(base)+ ":violin:  You eventually had to pay " + Numbers.FormatNumber(income / 100) + ":violin: for a new E String.").mentionRepliedUser(false).queue();
					} else if(num > 0.55) {
						base *= 0.9;
						violins += base;
						e.getMessage().reply("Your violin randomly went out of tune during the rehearsal.  You had to spend 4 minutes tuning it and were only able to earn " + Numbers.FormatNumber(base)+ ":violin:").mentionRepliedUser(false).queue();
					} else if(num > 0.4) {
						base *= 0.95;
						e.getMessage().reply("The orchestra had music stand problems, and page turning wasn't the best either.  You only earned " + Numbers.FormatNumber(base)+ ":violin:").mentionRepliedUser(false).queue();
					} else if(num > 0.25) {
						base *= 0.5;
						violins += base;
						violins -= income / 10;
						e.getMessage().reply("You hurt your wrist during the rehearsal and only got half of the effectiveness.  You earned " + Numbers.FormatNumber(base)+ ":violin: but ended up paying " + Numbers.FormatNumber(income / 10) + ":violin: in hospital fees.").mentionRepliedUser(false).queue();
					} else if(num > 0.15) {
						base *= 0.2;
						violins += base;
						e.getMessage().reply("**OOF**\nYour bridge fell off during the rehearsal.  You spend the next half-hour trying to get it back on, and you only earned " + Numbers.FormatNumber(base)+ ":violin:").mentionRepliedUser(false).queue();
					} else if(num > 0.05) {
						violins -= income;
						e.getMessage().reply("**OOF**\nYou had a memory blank during rehearsal.  Your tiger mom took all of your earnings, in addition to another " + Numbers.FormatNumber(income)+ " for shaming yourself.").mentionRepliedUser(false).queue();
					} else if(num > 0.015) {
						e.getMessage().reply(":regional_indicator_l:\nYour chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise for 12 hours.").mentionRepliedUser(true).queue();
						time += 43200000;
						data.replace("scaleCD", time);
						data.replace("practiceCD", time);
						badEvent = true;
					} else if(num > 0.005) {
						e.getMessage().reply(":regional_indicator_l:\nYou decided to fake your solo.  Of course it didn't work and Ling Ling fined you " + Numbers.FormatNumber((long) (violins * 0.9)) + ":violin:").mentionRepliedUser(true).queue();
						violins *= 0.1;
						base = 0;
					} else {
						e.getMessage().reply(":skull:\nYou dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined " + Numbers.FormatNumber((long) (violins * 0.95))	 + ":violin: for being careless in public.").mentionRepliedUser(true).queue();
						violins *= 0.05;
						base = 0;
						time += 86400000;
						data.replace("scaleCD", time);
						data.replace("practiceCD", time);
						data.replace("rehearseCD", time);
						data.replace("performCD", time);
						badEvent = true;
					}
					violinsEarned += base;
				}
				violins += base;
				violinsEarned += base;
				data.replace("violins", violins);
				data.replace("earnings", violinsEarned);
				if(!badEvent) {
					if((boolean) data.get("timeCrunch")) {
						data.replace("rehearseCD", time + 57600000);
					} else {
						data.replace("rehearseCD", time + 86340000);
					}
				}
				data.replace("rehearsals", (long) data.get("rehearsals") + 1);
				RNGesus.Lootbox(e, data);
				new SaveData(e, data);
			}
		} else {
			e.getMessage().reply("You must have an orchestra to rehearse with to use this command!").mentionRepliedUser(false).queue();
		}
	}
}