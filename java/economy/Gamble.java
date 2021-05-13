package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.Random;

public class Gamble {
    public Gamble(GuildMessageReceivedEvent e, String[] data) {
        long bet;
        long time = System.currentTimeMillis();
        long violins = Long.parseLong(data[0]);
        long gambleL = Long.parseLong(data[4]);
        long income = Long.parseLong(data[12]);
        boolean hasHigherMulti = Boolean.parseBoolean(data[58]);
        //long violinsEarned = Long.parseLong(data[66]);
        Random random = new Random();
        String[] message = e.getMessage().getContentRaw().split(" ");
        try {
            bet = Long.parseLong(message[2]);
        } catch (Exception exception) {
            e.getChannel().sendMessage("You must bet something, I'm not giving away free violins.\nCommand format: `gamble [type] [amount]`").queue();
            throw new IllegalArgumentException();
        }
        if (time < Long.parseLong(data[3])) {
            long milliseconds = Long.parseLong(data[3]) - time;
            long seconds = milliseconds / 1000;
            milliseconds -= seconds * 1000;
            e.getChannel().sendMessage("Don't bet your violins like Paganini, wait " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
        } else if (bet > violins) {
            e.getChannel().sendMessage("You can't bet more than you have, don't try to outsmart me.").queue();
        } else if (bet < 0) {
            e.getChannel().sendMessage("You can't bet a negative number, don't try breaking me.").queue();
        } else if (bet < 4000) {
            e.getChannel().sendMessage("If you're going to bet less than 4000:violin:, go away and stop wasting my time.").queue();
        } else if (bet > income * 10) {
            e.getChannel().sendMessage("You cannot bet more than " + income * 10 + ":violin:  To raise this cap, upgrade your hourly income!").queue();
        } else {
            try {
                double multi = 0.005 * gambleL;
                if (hasHigherMulti) {
                    multi += 0.05;
                }
                data[3] = String.valueOf(time + 9500);
                switch (message[1]) {
                    case "rng" -> {
                        double chance = random.nextDouble();
                        if (chance > 0.5) {
                            violins -= bet;
                            //violinsEarned -= bet;
                            e.getChannel().sendMessage("You lost " + bet + ":violin:\n*The generator rolled " + chance + ", you need less than 0.5 to win.*\nYou now have " + violins + ":violin:").queue();
                        } else if (chance <= 0.5) {
                            violins += bet * (1 + multi);
                            //violinsEarned += bet * (1 + multi);
                            e.getChannel().sendMessage("You won " + bet + ":violin:\nYour " + multi * 100 + "% multiplier earned you an extra " + (long) (bet * multi) + ":violin:\n*The generator rolled " + chance + ".*\nYou now have " + violins + ":violin:").queue();
                        }
                    }
                    case "slots" -> {
                        long[] slots = {random.nextInt(6), random.nextInt(6), random.nextInt(6)};
                        String[] emojis = new String[3];
                        for (int i = 0; i < slots.length; i++) {
                            if (slots[i] == 0) {
                                emojis[i] = ":trumpet:";
                            } else if (slots[i] == 1) {
                                emojis[i] = ":violin:";
                            } else if (slots[i] == 2) {
                                emojis[i] = "<:lingling40hrs:688449820410773532>";
                            } else if (slots[i] == 3) {
                                emojis[i] = "<:twoset:688452024009883669>";
                            } else if (slots[i] == 4) {
                                emojis[i] = "<:linglingclock:747499551451250730>";
                            } else {
                                emojis[i] = "<a:StradSpam:772894512154279945>";
                            }
                        }
                        long payout = bet;
                        if (slots[0] == slots[1] && slots[1] == slots[2]) {
                            if (slots[0] == 0) {
                                payout *= 2.5;
                            } else if (slots[0] == 1) {
                                payout *= 5;
                            } else if (slots[0] == 2) {
                                payout *= 10;
                            } else if (slots[0] == 3) {
                                payout *= 15;
                            } else if (slots[0] == 4) {
                                payout *= 25;
                            } else {
                                payout *= 40;
                            }
                        } else if (slots[0] == slots[1] || slots[1] == slots[2] || slots[2] == slots[0]) {
                            payout *= 1.1;
                        } else {
                            payout = -1;
                        }
                        EmbedBuilder builder = new EmbedBuilder()
                                .setColor(Color.BLUE)
                                .setFooter("Ling Ling Bot", e.getJDA().getSelfUser().getAvatarUrl())
                                .setTitle("__**Slots for " + e.getAuthor().getName() + "**__");
                        String name = ":arrow_right: " + emojis[0] + " " + emojis[1] + " " + emojis[2] + " :arrow_left:\n_ _";
                        if (payout != -1) {
                            builder.addField(name, ":white_check_mark: You **win**!  Payout: " + payout + ":violin:\nYour " + multi * 100 + "% multiplier earned you an extra " + (long) (payout * multi) + ":violin:", false);
                            violins += payout * (1 + multi);
                            //violinsEarned += payout * (1 + multi);

                        } else {
                            builder.addField(name, ":x: You **lose**!  You lost " + bet + ":violin:", false);
                            violins -= bet;
                            //violinsEarned -= bet;
                        }
                        e.getChannel().sendMessage(builder.build()).queue();
                    }
                    case "scratch" -> {
                        long[] payouts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                        if (bet % 50 != 0) {
                            e.getChannel().sendMessage("You must bet a multiple of 50 because scratch tickets cost 50:violin: each.").queue();
                        } else {
                            long numTickets = bet / 50;
                            long payout = 0;
                            for (long i = 0; i < numTickets; i++) {
                                double chance = random.nextDouble();
                                if (chance > 0.5) {
                                    payout -= 5;
                                    payouts[0]++;
                                } else if (chance > 0.25) {
                                    payouts[1]++;
                                } else if (chance > 0.1) {
                                    payout += 2;
                                    payouts[2]++;
                                } else if (chance > 0.05) {
                                    payout += 5;
                                    payouts[3]++;
                                } else if (chance > 0.02) {
                                    payout += 10;
                                    payouts[4]++;
                                } else if (chance > 0.01) {
                                    payout += 25;
                                    payouts[5]++;
                                } else if (chance > 0.005) {
                                    payout += 50;
                                    payouts[6]++;
                                } else if (chance > 0.002) {
                                    payout += 100;
                                    payouts[7]++;
                                } else if (chance > 0.001) {
                                    payout += 200;
                                    payouts[8]++;
                                } else if (chance > 0.000025) {
                                    payout += 500;
                                    payouts[9]++;
                                } else {
                                    payout += 1000000;
                                    e.getChannel().sendMessage("You hit the 1 000 000:violin: jackpot!").queue();
                                    data[67] = String.valueOf(Long.parseLong(data[67]) + 1);
                                }
                            }
                            StringBuilder breakdown = new StringBuilder().append("Lose 5:violin:: ").append(payouts[0]).append("\nNo Prize: ").append(payouts[1]).append("\nGain 2:violin:: ").append(payouts[2]).append("\nGain 5:violin:: ").append(payouts[3]).append("\nGain 10:violin:: ").append(payouts[4]).append("\nGain 25:violin:: ").append(payouts[5]).append("\nGain 50:violin:: ").append(payouts[6]).append("\nGain 100:violin:: ").append(payouts[7]).append("\nGain 200:violin:: ").append(payouts[8]).append("\nGain 500:violin:: ").append(payouts[9]);
                            if (payout > 0) {
                                violins += payout * (1 + multi);
                                //violinsEarned += payout * (1 + multi);
                                e.getChannel().sendMessage("You scratched " + numTickets + " tickets and gained " + payout + ":violin:\nYour " + multi * 100 + "% multiplier earned you an extra " + (long) (payout * multi) + ":violin:\n\n**__Ticket Breakdown__**\n" + breakdown).queue();
                            } else {
                                violins += payout;
                                //violinsEarned += payout;
                                e.getChannel().sendMessage("You scratched " + numTickets + " tickets and lost " + payout * -1 + ":violin:\n\n**__Ticket Breakdown__**\n" + breakdown).queue();
                            }
                        }
                    }
                    default -> e.getChannel().sendMessage("You must choose one of the three gambling options: `rng`, `scratch`, or `slots`").queue();
                }
            } catch (Exception exception) {
                e.getChannel().sendMessage("You must choose one of the three gambling options: `rng`, `scratch`, or `slots`").queue();
            }
            data[0] = String.valueOf(violins);
            //data[66] = String.valueOf(violinsEarned);
            new SaveData(e, data);
        }
    }
}
