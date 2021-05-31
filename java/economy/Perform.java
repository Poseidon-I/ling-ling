package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class Perform {
    public Perform(GuildMessageReceivedEvent e, String[] data) {
        long time = System.currentTimeMillis();
        Random random = new Random();
        long violins = Long.parseLong(data[0]);
        long violinsEarned = Long.parseLong(data[75]);
        String[] newData = data;
        if (time < Long.parseLong(data[8])) {
            long milliseconds = Long.parseLong(data[8]) - time;
            long days = milliseconds / 86400000;
            milliseconds -= days * 86400000;
            long hours = milliseconds / 3600000;
            milliseconds -= hours * 3600000;
            long minutes = milliseconds / 60000;
            milliseconds -= minutes * 60000;
            long seconds = milliseconds / 1000;
            milliseconds -= seconds * 1000;
            e.getChannel().sendMessage("Don't tire yourself with two performances a week!  Wait " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
        } else {
            long base = Calculate.CalculateAmount(e, data, random.nextInt(1001) + 4500);
            double num = random.nextDouble();
            if (num > 0.8) {
                violins += base;
                violinsEarned += base;
                e.getChannel().sendMessage("You performed your solo and earned " + base + ":violin:").queue();
            } else if (num > 0.55) {
                num = random.nextDouble();
                if (num > 0.5) {
                    newData[51] = String.valueOf(Long.parseLong(data[51]) + 9);
                    e.getChannel().sendMessage("Your paycheck contained 9:rice: instead of violins.").queue();
                } else if (num > 0.1) {
                    newData[62] = String.valueOf(Long.parseLong(data[62]) + 4);
                    e.getChannel().sendMessage("You found 4:bubble_tea: after you performed.").queue();
                } else {
                    newData[63] = String.valueOf(Long.parseLong(data[63]) + 1);
                    e.getChannel().sendMessage("Ling Ling enjoyed your performance and blessed you.").queue();
                }
            } else if (num > 0.25) {
                num = random.nextDouble();
                if (num > 0.65) {
                    e.getChannel().sendMessage("Your teacher approved your performance.  Your tiger mom saw the comment, and gave you " + (long) (base * 0.1) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                    base *= 1.1;
                    violins += base;
                } else if (num > 0.40) {
                    e.getChannel().sendMessage("Your tiger mom approved your performance.  She gave you " + (long) (base * 0.5) + ":violin: in addition to the " + base + ":violin: that you earned.").queue();
                    base *= 1.5;
                    violins += base;
                } else if (num > 0.2) {
                    base *= 2;
                    violins += base;
                    e.getChannel().sendMessage("Brett and Eddy approved of your performance and doubled the amount of violins you earned.  You got " + base + ":violin:").queue();
                } else if (num > 0.05) {
                    base *= 5;
                    violins += base;
                    e.getChannel().sendMessage("It's raining violins!  You earned " + base + ":violin: you lucky dog").queue();
                } else if (num > 0.01) {
                    base *= 10;
                    violins += base;
                    e.getChannel().sendMessage("The Berlin Philharmonic posted your performance on their homepage.  You got a HUGE " + base + ":violin:.\n\nDisclaimer: This does not guarantee a post IRL.").queue();
                } else {
                    base *= 25;
                    violins += base;
                    e.getChannel().sendMessage("Ling Ling enjoyed your performance but was displeased with some parts.  Nontheless, he (she?) granted you with " + base + ":violin:").queue();
                }
                violinsEarned += base;
            } else if (num > 0.05) {
                num = random.nextDouble();
                long income = Long.parseLong(data[12]);
                if (num > 0.8) {
                    violins -= income / 100;
                    base *= 0.5;
                    violins += base;
                    e.getChannel().sendMessage("Oh no!  Your E String snapped during the performance!  You couldn't go on, and only earned " + base + ":violin:  You eventually had to pay " + (income / 100) + ":violin: for a new E String.").queue();
                } else if (num > 0.6) {
                    base *= 0.9;
                    violins += base;
                    e.getChannel().sendMessage("Your violin randomly went out of tune during the rehearsal.  You had to spend 4 minutes tuning it and were only able to earn " + base + ":violin:").queue();
                } else if (num > 0.45) {
                    base *= 0.9;
                    e.getChannel().sendMessage("You didn't memorize your piece and you had to use your music.  You only earned " + base + ":violin:").queue();
                } else if (num > 0.3) {
                    base *= 0.5;
                    violins += base;
                    violins -= income / 10;
                    e.getChannel().sendMessage("You hurt your wrist during the performance and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying " + (income / 10) + ":violin: in hospital fees.").queue();
                } else if (num > 0.2) {
                    base *= 0.1;
                    violins += base;
                    e.getChannel().sendMessage("Your bridge fell off during the performance.  You spend the next half-hour trying to get it back on, and you only earned " + base + ":violin:").queue();
                } else if (num > 0.1) {
                    violins -= income;
                    e.getChannel().sendMessage("You had a memory blank during the performance.  Your tiger mom took all your earnings, in addition to another " + income + " for shaming yourself.").queue();
                } else if (num > 0.05) {
                    base *= 0.5;
                    violins -= income;
                    violins += base;
                    e.getChannel().sendMessage("Your performance was for a competition, and you only won Honorable Mention.  Your tiger mom Kung-Paos your chicken and takes half your earnings, in addition to another " + income + " for being so mediocre.").queue();
                } else if (num > 0.015) {
                    e.getChannel().sendMessage("Your chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise anything for 12 hours.").queue();
                    newData[64] = String.valueOf(Long.parseLong(data[64]) + 43200000);
                    newData[1] = String.valueOf(Long.parseLong(data[1]) + 43200000);
                } else if (num > 0.005) {
                    e.getChannel().sendMessage("You decided to fake your performance.  Of course it didn't work and Ling Ling fined you " + violins * 0.95 + ":violin:").queue();
                    violins *= 0.05;
                    base = 0;
                } else {
                    e.getChannel().sendMessage("You dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined pretty much your entire balance for being careless on stage.").queue();
                    violins *= 0.01;
                    base = 0;
                    time += 86400000;
                    newData[1] = String.valueOf(time);
                    newData[7] = String.valueOf(time);
                    newData[8] = String.valueOf(time);
                    newData[64] = String.valueOf(time);
                }
                violinsEarned += base;
            } else {
                num = random.nextDouble();
                if (num > 0.5) {
                    base *= 10;
                    newData[55] = String.valueOf(Long.parseLong(data[55]) + 1);
                    newData[52] = String.valueOf(Long.parseLong(data[52]) + 1);
                    e.getChannel().sendMessage(":trophy: Your performance won third place in the Ling Ling Competition.  Your earnings were tripled to " + base + " and you walked away with 1:military_medal: and a third place trophy :third_place:").queue();
                } else if (num > 0.2) {
                    base *= 25;
                    newData[55] = String.valueOf(Long.parseLong(data[55]) + 2);
                    newData[53] = String.valueOf(Long.parseLong(data[53]) + 1);
                    e.getChannel().sendMessage(":trophy: Your performance won second place.  Your earnings were multiplied by 15 to " + base + " and you walked away with 2:military_medal: and a second place trophy :second_place:").queue();
                } else {
                    base *= 100;
                    newData[55] = String.valueOf(Long.parseLong(data[55]) + 3);
                    newData[54] = String.valueOf(Long.parseLong(data[54]) + 1);
                    e.getChannel().sendMessage(":trophy: Your performance won first place.  Congratulations!  Your earnings were multiplied by 100 to " + base + " and you walked away with **3**:military_medal: and a FIRST place trophy :first_place:").queue();
                }
            }
            violins += base;
            violinsEarned += base;
            //noinspection ConstantConditions
            newData = CheckMedals.Medals(e, newData);
            newData[0] = String.valueOf(violins);
            newData[75] = String.valueOf(violinsEarned);
            if (Boolean.parseBoolean(data[50])) {
                newData[8] = String.valueOf(time + 215940000);
            } else {
                newData[8] = String.valueOf(time + 302340000);
            }
            newData[73] = String.valueOf(Long.parseLong(data[73]) + 1);
            new SaveData(e, newData);
        }
    }
}