package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;

import java.util.Random;

public class Practise {
	public Practise(GuildMessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		long time = System.currentTimeMillis();
		Random random = new Random();
		long violins = (long) data.get("violins");
		long violinsEarned = (long) data.get("earnings");
		if(time < (long) data.get("practiceCD")) {
			long milliseconds = (long) data.get("practiceCD") - time;
			long minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			long seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			e.getChannel().sendMessage("Take a break, you already practised a lot!  Wait " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
		} else {
			boolean badEvent = false;
			long base = Calculate.CalculateAmount(data, random.nextInt(101) + 400);
			double num = random.nextDouble();
			if(num > 0.7) {
				violins += base;
				e.getChannel().sendMessage("You practised for 45 minutes and earned " + base + ":violin:").queue();
				data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.75);
				violinsEarned += base;
			} else if(num > 0.4) {
				num = random.nextDouble();
				if(num > 0.5) {
					data.replace("rice", (long) data.get("rice") + 1);
					e.getChannel().sendMessage("You found 1:rice: while you were practising but didn't get any violins.").queue();
				} else if(num > 0.15) {
					data.replace("tea", (long) data.get("tea") + 1);
					e.getChannel().sendMessage("You found 1:bubble_tea: after you practised.").queue();
				} else {
					data.replace("blessings", (long) data.get("blessings") + 1);
					e.getChannel().sendMessage("Ling Ling enjoyed your practise session and blessed you.").queue();
				}
				data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.75);
			} else if(num > 0.1) {
				num = random.nextDouble();
				if(num > 0.65) {
					e.getChannel().sendMessage("Your teacher approved your practise session.  Your tiger mom saw the comment, and gave you " + (long) (base * 0.1) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
					base *= 1.1;
				} else if(num > 0.40) {
					e.getChannel().sendMessage("Your tiger mom approved your practise session.  She gave you " + (long) (base * 0.5) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
					base *= 1.5;
				} else if(num > 0.2) {
					base *= 2;
					e.getChannel().sendMessage("Brett and Eddy approved of your practise session and doubled the amount of violins you earned.  You got " + base + ":violin:").queue();
				} else if(num > 0.05) {
					base *= 5;
					e.getChannel().sendMessage("It's raining violins!  You earned " + base + ":violin: you lucky dog").queue();
				} else {
					base *= 25;
					e.getChannel().sendMessage("Ling Ling enjoyed your practise session but was displeased with some parts.  Nonetheless, he (she?) granted you with " + base + ":violin:").queue();
				}
				data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.75);
				violins += base;
				violinsEarned += base;
			} else {
				num = random.nextDouble();
				long income = (long) data.get("income");
				if(num > 0.75) {
					violins -= income / 100;
					e.getChannel().sendMessage("Oh no!  Your E String snapped while you were practising!  You had to go to the store to get it replaced, and were not able to get any practising done.  You earned 0:violin: and had to pay " + income / 100 + ":violin: for a new E String.").queue();
				} else if(num > 0.55) {
					base *= 0.9;
					violins += base;
					data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.675);
					e.getChannel().sendMessage("Your violin randomly went out of tune while you were practising.  You had to spend 4 minutes tuning it and were only able to earn " + base + ":violin:").queue();
				} else if(num > 0.4) {
					base *= 0.95;
					violins += base;
					data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.7);
					e.getChannel().sendMessage("You had problems with your music stand, and page turning wasn't the best this session.  You earned " + base + ":violin:").queue();
				} else if(num > 0.25) {
					base *= 0.5;
					violins += base;
					violins -= income / 10;
					data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.375);
					e.getChannel().sendMessage("You hurt your wrist while practising and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying " + income / 10 + ":violin: in hospital fees.").queue();
				} else if(num > 0.15) {
					base *= 0.2;
					violins += base;
					data.replace("hoursPractised", (double) data.get("hoursPractised") + 0.35);
					e.getChannel().sendMessage("Your bridge fell off 15 minutes into your session.  You spend the next half-hour trying to get it back on, and you only earned " + base + ":violin:").queue();
				} else if(num > 0.05) {
					violins -= income;
					e.getChannel().sendMessage("You were caught playing Minecraft while practising.  Your tiger mom took all your earnings, in addition to another " + income + " for being distracted.").queue();
				} else if(num > 0.015) {
					e.getChannel().sendMessage("Your chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise for 12 hours.").queue();
					time += 43200000;
					data.replace("scaleCD", time);
					data.replace("practiceCD", time);
					badEvent = true;
				} else if(num > 0.005) {
					e.getChannel().sendMessage("You decided to fake your practise session.  Ling Ling caught you in the act, and fined you " + (long) (violins * 0.8) + ":violin:").queue();
					violins *= 0.2;
					base = 0;
				} else {
					e.getChannel().sendMessage("You dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined " + (long) (violins * 0.9) + ":violin: for being careless.").queue();
					violins *= 0.1;
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
					data.replace("practiceCD", time + 1740000);
				} else {
					data.replace("practiceCD", time + 2640000);
				}
			}
		}
		new SaveData(e, data);
	}
}