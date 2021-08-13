package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.Objects;
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
                        data[0] = String.valueOf(violins);
                        data[75] = String.valueOf(violinsEarned);
                        new SaveData(e, data);
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
                        if (rng < 0.1) {
                            data[62] = String.valueOf(Long.parseLong(data[62]) + 1);
                            e.getChannel().sendMessage("You received 1:bubble_tea: from your Vote Box!").queue();
                        } else if (rng < 0.4) {
                            int received = random.nextInt(3) + 1;
                            data[51] = String.valueOf(Long.parseLong(data[51]) + received);
                            e.getChannel().sendMessage("You received " + received + ":rice: from your Vote Box!").queue();
                        } else {
                            long hourly = Long.parseLong(data[12]);
                            long min = hourly * 4;
                            long received = random.nextInt((int) min) + min;
                            data[0] = String.valueOf(Long.parseLong(data[0]) + received);
                            e.getChannel().sendMessage("You received " + received + ":violin: from your Vote Box!").queue();
                        }
                        new SaveData(e, data);
                    }
                }
                case "gift" -> {
                    if (Long.parseLong(data[87]) <= 0) {
                        e.getChannel().sendMessage("You already used all your gift boxes, wait for some generous people to get more!").queue();
                    } else if (income == 0) {
                        e.getChannel().sendMessage("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").queue();
                    } else {
                        data[87] = String.valueOf(Long.parseLong(data[87]) - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if (data[94].equals("true")) {
                            if (rng < 0.2) {
                                int received = random.nextInt(3) + 1;
                                data[62] = String.valueOf(Long.parseLong(data[62]) + received);
                                e.getChannel().sendMessage("You received " + received + ":bubble_tea: from your Gift Box!").queue();
                            } else if (rng < 0.5) {
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
                            if (rng < 0.05) {
                                data[94] = "true";
                                data[55] = String.valueOf(Long.parseLong(data[55]) + 1);
                                e.getChannel().sendMessage("You received 1:military_medal: from your Gift Box!").queue();
                            } else if (rng < 0.2) {
                                int received = random.nextInt(3) + 1;
                                data[62] = String.valueOf(Long.parseLong(data[62]) + received);
                                e.getChannel().sendMessage("You received " + received + ":bubble_tea: from your Gift Box!").queue();
                            } else if (rng < 0.5) {
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
                case "kit" -> {
                    if (Long.parseLong(data[91]) <= 0) {
                        e.getChannel().sendMessage("You already used all your Musician Kits, donate again to get more!").queue();
                    } else if (income == 0) {
                        e.getChannel().sendMessage("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").queue();
                    } else {
                        data[91] = String.valueOf(Long.parseLong(data[91]) - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if (data[94].equals("true")) {
                            if (rng < 0.3) {
                                int received = random.nextInt(5) + 8;
                                data[62] = String.valueOf(Long.parseLong(data[62]) + received);
                                e.getChannel().sendMessage("You received " + received + ":bubble_tea: from your Musician Kit!").queue();
                            } else if (rng < 0.6) {
                                int received = random.nextInt(6) + 10;
                                data[51] = String.valueOf(Long.parseLong(data[51]) + received);
                                e.getChannel().sendMessage("You received " + received + ":rice: from your Musician Kit!").queue();
                            } else {
                                long hourly = Long.parseLong(data[12]);
                                long min = hourly * 8;
                                long received = random.nextInt((int) min) + min;
                                data[0] = String.valueOf(Long.parseLong(data[0]) + received);
                                e.getChannel().sendMessage("You received " + received + ":violin: from your Musician Kit!").queue();
                            }
                        } else {
                            if (rng < 0.33) {
                                data[94] = "true";
                                int received = random.nextInt(1) + 1;
                                data[63] = String.valueOf(Long.parseLong(data[63]) + received);
                                e.getChannel().sendMessage("You received " + received + ":angel: from your Musician Kit!").queue();
                            } else if (rng < 0.34) {
                                data[94] = "true";
                                data[55] = String.valueOf(Long.parseLong(data[55]) + 1);
                                long max = Long.parseLong(data[12]) / 30000;
                                long min = Long.parseLong(data[12]) / 50000;
                                long received = random.nextInt((int) (max - min + 1)) + min;
                                if (received > 5) {
                                    received = 5;
                                }
                                e.getChannel().sendMessage("You received " + received + ":military_medal: from your Musician Kit!").queue();
                            } else if (rng < 0.67) {
                                int received = random.nextInt(5) + 8;
                                data[62] = String.valueOf(Long.parseLong(data[62]) + received);
                                e.getChannel().sendMessage("You received " + received + ":bubble_tea: from your Musician Kit!").queue();
                            } else {
                                int received = random.nextInt(6) + 10;
                                data[51] = String.valueOf(Long.parseLong(data[51]) + received);
                                e.getChannel().sendMessage("You received " + received + ":rice: from your Musician Kit!").queue();
                            }
                        }
                        new SaveData(e, data);
                    }
                }
                case "llbox" -> {
                    if (Long.parseLong(data[92]) <= 0) {
                        e.getChannel().sendMessage("You already used all your Ling Ling Boxes, donate again to get more!").queue();
                    } else if (income == 0) {
                        e.getChannel().sendMessage("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").queue();
                    } else {
                        data[92] = String.valueOf(Long.parseLong(data[92]) - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if (data[94].equals("true")) {
                            if (rng < 0.39) {
                                long hourly = Long.parseLong(data[12]);
                                long min = hourly * 12;
                                long received = random.nextInt((int) min) + min;
                                data[0] = String.valueOf(Long.parseLong(data[0]) + received);
                                e.getChannel().sendMessage("You received " + received + ":violin: from your Ling Ling Box!").queue();
                            } else if (rng < 0.69) {
                                int received = random.nextInt(11) + 15;
                                data[51] = String.valueOf(Long.parseLong(data[51]) + received);
                                e.getChannel().sendMessage("You received " + received + ":rice: from your Ling Ling Box!").queue();
                            } else if (rng < 0.99) {
                                int received = random.nextInt(6) + 10;
                                data[62] = String.valueOf(Long.parseLong(data[62]) + received);
                                e.getChannel().sendMessage("You received " + received + ":bubble_tea: from your Ling Ling Box!").queue();
                            } else {
                                String link = e.getChannel().sendMessage("You have received 1x Luthier!  Congratulations!  Please DM a Bot Admin or Stradivarius Violin#6156 to claim your prize.").complete().getJumpUrl();
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling\nReact when Claimed", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), "Message Link: " + link, false)
                                        .setTitle("__**Luthier Pulled from Ling Ling Box**__");
                                Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
                            }
                        } else {
                            if (rng < 0.09) {
                                data[94] = "true";
                                data[55] = String.valueOf(Long.parseLong(data[55]) + 1);
                                long max = Long.parseLong(data[12]) / 25000;
                                long min = Long.parseLong(data[12]) / 40000;
                                long received = random.nextInt((int) (max - min + 1)) + min;
                                if (received > 10) {
                                    received = 10;
                                }
                                e.getChannel().sendMessage("You received " + received + ":military_medal: from your Ling Ling Box!").queue();
                            } else if (rng < 0.39) {
                                int received = random.nextInt(11) + 15;
                                data[51] = String.valueOf(Long.parseLong(data[51]) + received);
                                e.getChannel().sendMessage("You received " + received + ":rice: from your Ling Ling Box!").queue();
                            } else if (rng < 0.69) {
                                int received = random.nextInt(6) + 10;
                                data[62] = String.valueOf(Long.parseLong(data[62]) + received);
                                e.getChannel().sendMessage("You received " + received + ":bubble_tea: from your Ling Ling Box!").queue();
                            } else if (rng < 0.99) {
                                int received = random.nextInt(3) + 2;
                                data[63] = String.valueOf(Long.parseLong(data[63]) + received);
                                e.getChannel().sendMessage("You received " + received + ":angel: from your Ling Ling Box!").queue();
                            } else {
                                String link = e.getChannel().sendMessage("You have received 1x Luthier!  Congratulations!  Please DM a Bot Admin or Stradivarius Violin#6156 to claim your prize.").complete().getJumpUrl();
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling\nReact when Claimed", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), "Message Link: " + link, false)
                                        .setTitle("__**Luthier Pulled from Ling Ling Box**__");
                                Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
                            }
                        }
                        new SaveData(e, data);
                    }
                }
                case "crazybox" -> {
                    if (Long.parseLong(data[93]) <= 0) {
                        e.getChannel().sendMessage("You already used all your Crazy Person Boxes, donate again to get more!").queue();
                    } else if (income == 0) {
                        e.getChannel().sendMessage("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").queue();
                    } else {
                        data[93] = String.valueOf(Long.parseLong(data[93]) - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if (data[94].equals("true")) {
                            if (rng < 0.3) {
                                long hourly = Long.parseLong(data[12]);
                                long min = hourly * 18;
                                long received = random.nextInt((int) min) + min;
                                data[0] = String.valueOf(Long.parseLong(data[0]) + received);
                                e.getChannel().sendMessage("You received " + received + ":violin: from your Crazy Person Box!").queue();

                            } else if (rng < 0.6) {
                                int received = random.nextInt(11) + 20;
                                data[51] = String.valueOf(Long.parseLong(data[51]) + received);
                                e.getChannel().sendMessage("You received " + received + ":rice: from your Crazy Person Box!").queue();
                            } else if (rng < 0.9) {
                                int received = random.nextInt(9) + 12;
                                data[62] = String.valueOf(Long.parseLong(data[62]) + received);
                                e.getChannel().sendMessage("You received " + received + ":bubble_tea: from your Crazy Person Box!").queue();
                            } else {
                                String link = e.getChannel().sendMessage("You have received 3x Luthier!  Congratulations!  Please DM a Bot Admin or Stradivarius Violin#6156 to claim your prize.").complete().getJumpUrl();
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling\nReact when Claimed", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), "Message Link: " + link, false)
                                        .setTitle("__**Luthier Pulled from Crazy Person Box**__");
                                Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
                            }
                        } else {
                            if (rng < 0.3) {
                                data[94] = "true";
                                data[55] = String.valueOf(Long.parseLong(data[55]) + 1);
                                long max = Long.parseLong(data[12]) / 20000;
                                long min = Long.parseLong(data[12]) / 30000;
                                long received = random.nextInt((int) (max - min + 1)) + min;
                                if (received > 15) {
                                    received = 15;
                                }
                                e.getChannel().sendMessage("You received " + received + ":military_medal: from your Crazy Person Box!").queue();
                            } else if (rng < 0.5) {
                                int received = random.nextInt(11) + 20;
                                data[51] = String.valueOf(Long.parseLong(data[51]) + received);
                                e.getChannel().sendMessage("You received " + received + ":rice: from your Crazy Person Box!").queue();
                            } else if (rng < 0.7) {
                                int received = random.nextInt(9) + 12;
                                data[62] = String.valueOf(Long.parseLong(data[62]) + received);
                                e.getChannel().sendMessage("You received " + received + ":bubble_tea: from your Crazy Person Box!").queue();
                            } else if (rng < 0.9) {
                                int received = random.nextInt(5) + 4;
                                data[63] = String.valueOf(Long.parseLong(data[63]) + received);
                                e.getChannel().sendMessage("You received " + received + ":angel: from your Crazy Person Box!").queue();
                            } else {
                                String link = e.getChannel().sendMessage("You have received 3x Luthier!  Congratulations!  Please DM a Bot Admin or Stradivarius Violin#6156 to claim your prize.").complete().getJumpUrl();
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling\nReact when Claimed", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), "Message Link: " + link, false)
                                        .setTitle("__**Luthier Pulled from Crazy Person Box**__");
                                Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
                            }
                            new SaveData(e, data);
                        }
                    }
                }
                default -> e.getChannel().sendMessage("You can't use something that doesn't exist, that doesn't make sense.").queue();
            }
        } catch(Exception exception) {
            e.getChannel().sendMessage("You can't use nothing stupid.").queue();
        }
    }
}
