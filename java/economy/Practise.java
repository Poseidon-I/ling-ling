package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class Practise {
	public Practise(GuildMessageReceivedEvent e) {
		String[] data = LoadData.loadData(e, "Economy Data");
		long time = System.currentTimeMillis();
		Random random = new Random();
		long violins = Long.parseLong(data[0]);
		long violinsEarned = Long.parseLong(data[75]);
		if(time < Long.parseLong(data[1])) {
			long milliseconds = Long.parseLong(data[1]) - time;
			long minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			long seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			e.getChannel().sendMessage("Take a break, you already practised a lot!  Wait " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
		} else {
			boolean badEvent = false;
			long base = Calculate.CalculateAmount(e, data, random.nextInt(101) + 400);
			double num = random.nextDouble();
			if(num > 0.75) {
				violins += base;
				e.getChannel().sendMessage("You practised for 45 minutes and earned " + base + ":violin:").queue();
				data[71] = String.valueOf(Double.parseDouble(data[71]) + 0.75);
				violinsEarned += base;
			} else if(num > 0.5) {
				num = random.nextDouble();
				if(num > 0.45) {
					data[51] = String.valueOf(Long.parseLong(data[51]) + 1);
					e.getChannel().sendMessage("You found 1:rice: while you were practising but didn't get any violins.").queue();
				} else if(num > 0.1) {
					data[62] = String.valueOf(Long.parseLong(data[62]) + 1);
					e.getChannel().sendMessage("You found 1:bubble_tea: after you practised.").queue();
				} else {
					data[63] = String.valueOf(Long.parseLong(data[63]) + 1);
					e.getChannel().sendMessage("Ling Ling enjoyed your practise session and blessed you.").queue();
				}
				data[71] = String.valueOf(Double.parseDouble(data[71]) + 0.75);
			} else if(num > 0.15) {
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
				data[71] = String.valueOf(Double.parseDouble(data[71]) + 0.75);
				violins += base;
				violinsEarned += base;
			} else {
				num = random.nextDouble();
				long income = Long.parseLong(data[12]);
				if(num > 0.75) {
					violins -= income / 100;
					e.getChannel().sendMessage("Oh no!  Your E String snapped while you were practising!  You had to go to the store to get it replaced, and were not able to get any practising done.  You earned 0:violin: and had to pay " + income / 100 + ":violin: for a new E String.").queue();
				} else if(num > 0.55) {
					base *= 0.9;
					violins += base;
					data[71] = String.valueOf(Double.parseDouble(data[71]) + 0.675);
					e.getChannel().sendMessage("Your violin randomly went out of tune while you were practising.  You had to spend 4 minutes tuning it and were only able to earn " + base + ":violin:").queue();
				} else if(num > 0.4) {
					base *= 0.95;
					violins += base;
					data[71] = String.valueOf(Double.parseDouble(data[71]) + 0.7);
					e.getChannel().sendMessage("You had problems with your music stand, and page turning wasn't the best this session.  You earned " + base + ":violin:").queue();
				} else if(num > 0.25) {
					base *= 0.5;
					violins += base;
					violins -= income / 10;
					data[71] = String.valueOf(Double.parseDouble(data[71]) + 0.375);
					e.getChannel().sendMessage("You hurt your wrist while practising and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying " + income / 10 + ":violin: in hospital fees.").queue();
				} else if(num > 0.15) {
					base *= 0.2;
					violins += base;
					data[71] = String.valueOf(Double.parseDouble(data[71]) + 0.35);
					e.getChannel().sendMessage("Your bridge fell off 15 minutes into your session.  You spend the next half-hour trying to get it back on, and you only earned " + base + ":violin:").queue();
				} else if(num > 0.05) {
					violins -= income;
					e.getChannel().sendMessage("You were caught playing Minecraft while practising.  Your tiger mom took all your earnings, in addition to another " + income + " for being distracted.").queue();
				} else if(num > 0.015) {
					e.getChannel().sendMessage("Your chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise for 12 hours.").queue();
					data[64] = String.valueOf(Long.parseLong(data[64]) + 43200000);
					data[1] = String.valueOf(Long.parseLong(data[1]) + 43200000);
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
					data[1] = String.valueOf(time + 86400000);
					data[7] = String.valueOf(time + 86400000);
					data[8] = String.valueOf(time + 86400000);
					data[64] = String.valueOf(time + 86400000);
					badEvent = true;
				}
				violinsEarned += base;
			}
			violins += base;
			violinsEarned += base;
			data[0] = String.valueOf(violins);
			data[75] = String.valueOf(violinsEarned);
			if(!badEvent)
				if(Boolean.parseBoolean(data[50])) {
					data[1] = String.valueOf(time + 1740000);
				} else {
					data[1] = String.valueOf(time + 2640000);
				}
		}
		new SaveData(e, data, "Economy Data");
	}
}