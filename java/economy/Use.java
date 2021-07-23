package economy; 

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class Use {
    public Use(GuildMessageReceivedEvent e, String[] data, char prefix) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        long income = Long.parseLong(data[12]);
        long violins = Long.parseLong(data[0]);
        long violinsEarned = Long.parseLong(data[75]);
        long useAmount;
        try {
            useAmount = Long.parseLong(message[2]);
        } catch(Exception exception) {
            useAmount = 1;
        }
        try {
            switch (message[1]) {
                case "1" -> {
                    if (Boolean.parseBoolean(data[9])) {
                        data[11] = "1";
                        e.getChannel().sendMessage("You have activated Plan 1 - Full Security").queue();
                        new SaveData(e, data);
                    } else {
                        e.getChannel().sendMessage("You don't own this insurance!  Run `" + prefix + "shop` to see the details.").queue();
                    }
                }
                case "2" -> {
                    if (Boolean.parseBoolean(data[10])) {
                        data[11] = "2";
                        e.getChannel().sendMessage("You have activated Plan 2 - Half Security").queue();
                        new SaveData(e, data);
                    } else {
                        e.getChannel().sendMessage("You don't own this insurance!  Run `" + prefix + "shop` to see the details.").queue();
                    }
                }
                case "0" -> {
                    data[11] = "0";
                    e.getChannel().sendMessage("You have deactivated all insurance.  Not a good idea...").queue();
                    new SaveData(e, data);
                }
                case "rice" -> {
                    if (Long.parseLong(data[51]) <= 0) {
                        e.getChannel().sendMessage("You scourge your pantry but find no rice.  Then you remember you don't have any more.").queue();
                    } else if (useAmount > Long.parseLong(data[51])) {
                        e.getChannel().sendMessage("You cannot consume more rice than you have.").queue();
                    } else if (income == 0) {
                        e.getChannel().sendMessage("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").queue();
                    } else {
                        data[51] = String.valueOf(Long.parseLong(data[51]) - useAmount);
                        data[0] = String.valueOf(violins + income * 2 * useAmount);
                        data[75] = String.valueOf(violinsEarned + income * 2 * useAmount);
                        new SaveData(e, data);
                        e.getChannel().sendMessage("You ate " + useAmount + " Blessed Rice.  The God of Rice gave you " + income * 2 * useAmount + ":violin:").queue();
                    }
                }
                case "tea" -> {
                    if (Long.parseLong(data[62]) <= 0) {
                        e.getChannel().sendMessage("You scourge your pantry but find no more bubble tea.  Then you remember you don't have any more.").queue();
                    } else if (useAmount > Long.parseLong(data[62])) {
                        e.getChannel().sendMessage("You cannot consume more bubble tea than you have.").queue();
                    } else if (income == 0) {
                        e.getChannel().sendMessage("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").queue();
                    } else {
                        data[62] = String.valueOf(Long.parseLong(data[62]) - useAmount);
                        data[0] = String.valueOf(violins + income * 6 * useAmount);
                        data[75] = String.valueOf(violinsEarned + income * 6 * useAmount);
                        new SaveData(e, data);
                        e.getChannel().sendMessage("You drank " + useAmount + " Bubble Tea.  Brett and Eddy approved and gave you " + income * 6 * useAmount + ":violin:").queue();
                    }
                }
                case "blessing" -> {
                    if (Long.parseLong(data[63]) <= 0) {
                        e.getChannel().sendMessage("You already used all your blessings, run more commands to get back into Ling Ling's good graces!").queue();
                    } else if (useAmount > Long.parseLong(data[63])) {
                        e.getChannel().sendMessage("You cannot use more blessings than you have.").queue();
                    } else if (income == 0) {
                        e.getChannel().sendMessage("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").queue();
                    } else {
                        for (int i = 0; i < useAmount; i++) {
                            Random random = new Random();
                            double num = random.nextDouble();
                            if (num > 0.5) {
                                data[55] = String.valueOf(Long.parseLong(data[55]) + 1);
                                data[52] = String.valueOf(Long.parseLong(data[52]) + 1);
                                e.getChannel().sendMessage(":trophy: Your performance won third place in the Ling Ling Competition.  You walked away with 1:military_medal: and a third place trophy :third_place:").queue();
                            } else if (num > 0.2) {
                                data[55] = String.valueOf(Long.parseLong(data[55]) + 2);
                                data[53] = String.valueOf(Long.parseLong(data[53]) + 1);
                                e.getChannel().sendMessage(":trophy: Your performance won second place.  You walked away with 2:military_medal: and a second place trophy :second_place:").queue();
                            } else {
                                data[55] = String.valueOf(Long.parseLong(data[55]) + 3);
                                data[54] = String.valueOf(Long.parseLong(data[54]) + 1);
                                e.getChannel().sendMessage(":trophy: Your performance won first place.  Congratulations!  You walked away with **3**:military_medal: and a FIRST place trophy :first_place:").queue();
                            }
                        }
                        data[63] = String.valueOf(Long.parseLong(data[63]) - useAmount);
                        violins += income * 24 * useAmount;
                        violinsEarned += income * 24 * useAmount;
                        String[] newData = CheckMedals.Medals(e, data);
                        newData[0] = String.valueOf(violins);
                        newData[75] = String.valueOf(violinsEarned);
                        new SaveData(e, newData);
                        e.getChannel().sendMessage("Ling Ling blessed you and you received " + income * 24 * useAmount + ":violin:").queue();
                    }
                }
                case "vote" -> {
                    if (Long.parseLong(data[90]) <= 0) {
                        e.getChannel().sendMessage("You already used all your vote boxes, vote for the bot to get more!").queue();
                    } else if (income == 0) {
                        e.getChannel().sendMessage("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").queue();
                    } else {
                        data[90] = String.valueOf(Long.parseLong(data[90]) - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if(rng < 0.1) {
                            data[62] = String.valueOf(Long.parseLong(data[62]) + 1);
                            e.getChannel().sendMessage("You received 1:tea: from your Vote Box!").queue();
                        } else if(rng < 0.4) {
                            int received = random.nextInt(3) + 1;
                            data[51] = String.valueOf(Long.parseLong(data[51]) + received);
                            e.getChannel().sendMessage("You received " + received + ":rice: from your Vote Box!").queue();
                        } else {
                            long hourly = Long.parseLong(data[12]);
                            long min = hourly * 3;
                            long received = random.nextInt((int) min) + min;
                            data[0] = String.valueOf(Long.parseLong(data[0]) + received);
                            e.getChannel().sendMessage("You received " + received + ":violin: from your Vote Box!").queue();
                        }
                        new SaveData(e, data);
                    }
                }
                case "gift" -> {
                    if (Long.parseLong(data[87]) <= 0) {
                        e.getChannel().sendMessage("You already used all your vote boxes, vote for the bot to get more!").queue();
                    } else if (income == 0) {
                        e.getChannel().sendMessage("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").queue();
                    } else {
                        data[87] = String.valueOf(Long.parseLong(data[87]) - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if(data[92].equals("true")) {
                            if(rng < 0.2) {
                                int received = random.nextInt(3) + 1;
                                data[62] = String.valueOf(Long.parseLong(data[62]) + received);
                                e.getChannel().sendMessage("You received " + received + ":tea: from your Gift Box!").queue();
                            } else if(rng < 0.5) {
                                int received = random.nextInt(4) + 2;
                                data[51] = String.valueOf(Long.parseLong(data[51]) + received);
                                e.getChannel().sendMessage("You received " + received + ":rice: from your Gift Box!").queue();
                            } else {
                                long hourly = Long.parseLong(data[12]);
                                long min = hourly * 6;
                                long received = random.nextInt((int) min) + min;
                                data[0] = String.valueOf(Long.parseLong(data[0]) + received);
                                e.getChannel().sendMessage("You received " + received + ":violin: from your Gift Box!").queue();
                            }
                        } else {
                            if(rng < 0.05) {
                                data[92] = "true";
                                data[55] = String.valueOf(Long.parseLong(data[55]) + 1);
                                e.getChannel().sendMessage("You received 1:military_medal: from your Gift Box!").queue();
                                data = CheckMedals.Medals(e, data);
                            } else if(rng < 0.2) {
                                int received = random.nextInt(3) + 1;
                                data[62] = String.valueOf(Long.parseLong(data[62]) + received);
                                e.getChannel().sendMessage("You received " + received + ":tea: from your Gift Box!").queue();
                            } else if(rng < 0.5) {
                                int received = random.nextInt(4) + 2;
                                data[51] = String.valueOf(Long.parseLong(data[51]) + received);
                                e.getChannel().sendMessage("You received " + received + ":rice: from your Gift Box!").queue();
                            } else {
                                long hourly = Long.parseLong(data[12]);
                                long min = hourly * 6;
                                long received = random.nextInt((int) min) + min;
                                data[0] = String.valueOf(Long.parseLong(data[0]) + received);
                                e.getChannel().sendMessage("You received " + received + ":violin: from your Gift Box!").queue();
                            }
                        }
                        new SaveData(e, data);
                    }
                }
                case "god" -> {
                    // [ redacted ]
                }
                default -> e.getChannel().sendMessage("You can't use something that doesn't exist, that doesn't make sense.").queue();
            }
        } catch(Exception exception) {
            e.getChannel().sendMessage("You can't use nothing stupid.").queue();
        }
    }
}
