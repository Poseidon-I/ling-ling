package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import processes.Numbers;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Use {
    public Use(MessageReceivedEvent e) {
        JSONObject data = LoadData.loadData(e);
        String[] message = e.getMessage().getContentRaw().split(" ");
        long income = (long) data.get("income");
        long violins = (long) data.get("violins");
        long violinsEarned = (long) data.get("earnings");
        long useAmount;
        try {
            useAmount = Long.parseLong(message[2]);
        } catch (Exception exception) {
            useAmount = 1;
        }
        try {
            switch (message[1]) {
                case "rice" -> {
                    if ((long) data.get("rice") <= 0) {
                        e.getMessage().reply("You scourge your pantry but find no rice.  Then you remember you don't have any more.").mentionRepliedUser(false).queue();
                    } else if (useAmount > (long) data.get("rice")) {
                        e.getMessage().reply("You cannot consume more rice than you have.").mentionRepliedUser(false).queue();
                    } else if (income == 0) {
                        e.getMessage().reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").mentionRepliedUser(false).queue();
                    } else {
                        data.replace("rice", (long) data.get("rice") - useAmount);
                        data.replace("violins", violins + income * 2 * useAmount);
                        data.replace("earnings", violinsEarned + income * 2 * useAmount);
                        new SaveData(e, data);
                        e.getMessage().reply("You ate " + Numbers.FormatNumber(useAmount) + " Blessed Rice.  The God of Rice gave you " + Numbers.FormatNumber(income * 2 * useAmount) + ":violin:").mentionRepliedUser(false).queue();
                    }
                }
                case "tea" -> {
                    if ((long) data.get("tea") <= 0) {
                        e.getMessage().reply("You scourge your fridge but find no more bubble tea.  Then you remember you don't have any more.").mentionRepliedUser(false).queue();
                    } else if (useAmount > (long) data.get("tea")) {
                        e.getMessage().reply("You cannot consume more bubble tea than you have.").mentionRepliedUser(false).queue();
                    } else if (income == 0) {
                        e.getMessage().reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").mentionRepliedUser(false).queue();
                    } else {
                        data.replace("tea", (long) data.get("tea") - useAmount);
                        data.replace("violins", violins + income * 6 * useAmount);
                        data.replace("earnings", violinsEarned + income * 6 * useAmount);
                        new SaveData(e, data);
                        e.getMessage().reply("You drank " + Numbers.FormatNumber(useAmount) + " Bubble Tea.  Brett and Eddy approved and gave you " + Numbers.FormatNumber(income * 6 * useAmount) + ":violin:").mentionRepliedUser(false).queue();
                    }
                }
                case "blessing" -> {
                    if ((long) data.get("blessings") <= 0) {
                        e.getMessage().reply("You already used all your blessings, run more commands to get back into Ling Ling's good graces!").mentionRepliedUser(false).queue();
                    } else if (income == 0) {
                        e.getMessage().reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").mentionRepliedUser(false).queue();
                    } else {
                        Random random = new Random();
                        double num = random.nextDouble();
                        if (num > 0.5) {
                            data.replace("medals", (long) data.get("medals") + 1);
                            e.getMessage().reply("Ling Ling liked your performance.  You walked away with 1:military_medal: and " + Numbers.FormatNumber(income * 24 * useAmount) + ":violin:").mentionRepliedUser(false).queue();
                        } else if (num > 0.2) {
                            data.replace("medals", (long) data.get("medals") + 2);
                            e.getMessage().reply("Ling Ling enjoyed your performance.  You walked away with 2:military_medal: and " + Numbers.FormatNumber(income * 24 * useAmount) + ":violin:").mentionRepliedUser(false).queue();
                        } else {
                            data.replace("medals", (long) data.get("medals") + 3);
                            e.getMessage().reply("Ling Ling greatly enjoyed your performance.  You walked away with **3**:military_medal: and " + Numbers.FormatNumber(income * 24 * useAmount) + ":violin:").mentionRepliedUser(false).queue();
                        }
                        data.replace("blessings", (long) data.get("blessings") - 1);
                        data.replace("violins", violins + income * 24 * useAmount);
                        data.replace("earnings", violinsEarned + income * 24 * useAmount);
                        new SaveData(e, data);
                    }
                }
                case "vote" -> {
                    if ((long) data.get("voteBox") <= 0) {
                        e.getMessage().reply("You already used all your vote boxes, vote for the bot to get more!").mentionRepliedUser(false).queue();
                    } else if (income == 0) {
                        e.getMessage().reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").mentionRepliedUser(false).queue();
                    } else {
                        data.replace("voteBox", (long) data.get("voteBox") - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if (rng < 0.1) {
                            data.replace("tea", (long) data.get("tea") + 1);
                            e.getMessage().reply("You received 1:bubble_tea: from your Vote Box!").mentionRepliedUser(false).queue();
                        } else if (rng < 0.4) {
                            int received = random.nextInt(3) + 1;
                            data.replace("rice", (long) data.get("rice") + received);
                            e.getMessage().reply("You received " + received + ":rice: from your Vote Box!").mentionRepliedUser(false).queue();
                        } else {
                            long hourly = (long) data.get("income");
                            long min = hourly * 4;
                            long received = random.nextInt((int) min) + min;
                            data.replace("violins", (long) data.get("violins") + received);
                            e.getMessage().reply("You received " + Numbers.FormatNumber(received) + ":violin: from your Vote Box!").mentionRepliedUser(false).queue();
                        }
                        new SaveData(e, data);
                    }
                }
                case "gift" -> {
                    if ((long) data.get("giftBox") <= 0) {
                        e.getMessage().reply("You already used all your gift boxes, wait for some generous people to get more!").mentionRepliedUser(false).queue();
                    } else if (income == 0) {
                        e.getMessage().reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").mentionRepliedUser(false).queue();
                    } else {
                        data.replace("giftBox", (long) data.get("giftBox") - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if ((boolean) data.get("medalToday")) {
                            if (rng < 0.2) {
                                int received = random.nextInt(3) + 1;
                                data.replace("tea", (long) data.get("tea") + received);
                                e.getMessage().reply("You received " + received + ":bubble_tea: from your Gift Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.5) {
                                int received = random.nextInt(4) + 2;
                                data.replace("rice", (long) data.get("rice") + received);
                                e.getMessage().reply("You received " + received + ":rice: from your Gift Box!").mentionRepliedUser(false).queue();
                            } else {
                                long hourly = (long) data.get("income");
                                long min = hourly * 6;
                                long received = random.nextInt((int) min) + min;
                                data.replace("violins", (long) data.get("violins") + received);
                                e.getMessage().reply("You received " + Numbers.FormatNumber(received) + ":violin: from your Gift Box!").mentionRepliedUser(false).queue();
                            }
                        } else {
                            if (rng < 0.05) {
                                data.replace("medalToday", true);
                                data.replace("medals", (long) data.get("medals") + 1);
                                e.getMessage().reply("You received 1:military_medal: from your Gift Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.2) {
                                int received = random.nextInt(3) + 1;
                                data.replace("tea", (long) data.get("tea") + received);
                                e.getMessage().reply("You received " + received + ":bubble_tea: from your Gift Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.5) {
                                int received = random.nextInt(4) + 2;
                                data.replace("rice", (long) data.get("rice") + received);
                                e.getMessage().reply("You received " + received + ":rice: from your Gift Box!").mentionRepliedUser(false).queue();
                            } else {
                                long hourly = (long) data.get("income");
                                long min = hourly * 6;
                                long received = random.nextInt((int) min) + min;
                                data.replace("violins", (long) data.get("violins") + received);
                                e.getMessage().reply("You received " + Numbers.FormatNumber(received) + ":violin: from your Gift Box!").mentionRepliedUser(false).queue();
                            }
                        }
                        new SaveData(e, data);
                    }
                }
                case "kit" -> {
                    if ((long) data.get("kits") <= 0) {
                        e.getMessage().reply("You already used all your Musician Kits, play the bot to earn more!").mentionRepliedUser(false).queue();
                    } else if (income == 0) {
                        e.getMessage().reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").mentionRepliedUser(false).queue();
                    } else {
                        data.replace("kits", (long) data.get("kits") - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if ((boolean) data.get("medalToday")) {
                            if (rng < 0.3) {
                                int received = random.nextInt(5) + 8;
                                data.replace("tea", (long) data.get("tea") + received);
                                e.getMessage().reply("You received " + received + ":bubble_tea: from your Musician Kit!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.6) {
                                int received = random.nextInt(6) + 10;
                                data.replace("rice", (long) data.get("rice") + received);
                                e.getMessage().reply("You received " + received + ":rice: from your Musician Kit!").mentionRepliedUser(false).queue();
                            } else {
                                long hourly = (long) data.get("income");
                                long min = hourly * 8;
                                long received = random.nextInt((int) min) + min;
                                data.replace("violins", (long) data.get("violins") + received);
                                e.getMessage().reply("You received " + Numbers.FormatNumber(received) + ":violin: from your Musician Kit!").mentionRepliedUser(false).queue();
                            }
                        } else {
                            if (rng < 0.33) {
                                data.replace("medalToday", true);
                                int received = random.nextInt(1) + 1;
                                data.replace("blessings", (long) data.get("blessings") + received);
                                e.getMessage().reply("You received " + received + ":angel: from your Musician Kit!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.34) {
                                data.replace("medalToday", true);
                                long max = (long) data.get("income") / 30000;
                                long min = (long) data.get("income") / 50000;
                                long received = random.nextInt((int) (max - min + 1)) + min;
                                if (received > 5) {
                                    received = 5;
                                }
                                data.replace("medals", (long) data.get("medals") + received);
                                e.getMessage().reply("You received " + received + ":military_medal: from your Musician Kit!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.67) {
                                int received = random.nextInt(5) + 8;
                                data.replace("tea", (long) data.get("tea") + received);
                                e.getMessage().reply("You received " + received + ":bubble_tea: from your Musician Kit!").mentionRepliedUser(false).queue();
                            } else {
                                int received = random.nextInt(6) + 10;
                                data.replace("rice", (long) data.get("rice") + received);
                                e.getMessage().reply("You received " + received + ":rice: from your Musician Kit!").mentionRepliedUser(false).queue();
                            }
                        }
                        new SaveData(e, data);
                    }
                }
                case "llbox" -> {
                    if ((long) data.get("linglingBox") <= 0) {
                        e.getMessage().reply("You already used all your Ling Ling Boxes, play the bot to get more!").mentionRepliedUser(false).queue();
                    } else if (income == 0) {
                        e.getMessage().reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").mentionRepliedUser(false).queue();
                    } else {
                        data.replace("linglingBox", (long) data.get("linglingBox") - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if ((boolean) data.get("medalToday")) {
                            if (rng < 0.39) {
                                long hourly = (long) data.get("income");
                                long min = hourly * 12;
                                long received = random.nextInt((int) min) + min;
                                data.replace("violins", (long) data.get("violins") + received);
                                e.getMessage().reply("You received " + Numbers.FormatNumber(received) + ":violin: from your Ling Ling Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.69) {
                                int received = random.nextInt(11) + 15;
                                data.replace("rice", (long) data.get("rice") + received);
                                e.getMessage().reply("You received " + received + ":rice: from your Ling Ling Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.99) {
                                int received = random.nextInt(6) + 10;
                                data.replace("tea", (long) data.get("tea") + received);
                                e.getMessage().reply("You received " + received + ":bubble_tea: from your Ling Ling Box!").mentionRepliedUser(false).queue();
                            } else {
                                if((boolean) data.get("mfLLBox")) {
                                    String link = e.getMessage().reply("You have received 1x Luthier!  Congratulations!  Please DM a Bot Admin or Stradivarius Violin#6156 to claim your prize.").mentionRepliedUser(true).complete().getJumpUrl();
                                    EmbedBuilder builder = new EmbedBuilder()
                                            .setColor(Color.BLUE)
                                            .setFooter("Ling Ling\nReact when Claimed", e.getJDA().getSelfUser().getAvatarUrl())
                                            .addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), "Message Link: " + link, false)
                                            .setTitle("__**Luthier Pulled from Ling Ling Box**__");
                                    Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
                                } else {
                                    data.replace("mfLLBox", true);
                                    data.replace("magicFind", (long) data.get("magicFind") + 1);
                                    e.getMessage().reply("You found +1 Magic Find!").mentionRepliedUser(false).queue();
                                }
                            }
                        } else {
                            if (rng < 0.09) {
                                data.replace("medalToday", true);
                                long max = (long) data.get("income") / 25000;
                                long min = (long) data.get("income") / 40000;
                                long received = random.nextInt((int) (max - min + 1)) + min;
                                if (received > 10) {
                                    received = 10;
                                }
                                data.replace("medals", (long) data.get("medals") + received);
                                e.getMessage().reply("You received " + received + ":military_medal: from your Ling Ling Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.39) {
                                int received = random.nextInt(11) + 15;
                                data.replace("rice", (long) data.get("rice") + received);
                                e.getMessage().reply("You received " + received + ":rice: from your Ling Ling Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.69) {
                                int received = random.nextInt(6) + 10;
                                data.replace("tea", (long) data.get("tea") + received);
                                e.getMessage().reply("You received " + received + ":bubble_tea: from your Ling Ling Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.99) {
                                int received = random.nextInt(3) + 2;
                                data.replace("blessings", (long) data.get("blessings") + received);
                                e.getMessage().reply("You received " + received + ":angel: from your Ling Ling Box!").mentionRepliedUser(false).queue();
                            } else {
                                if((boolean) data.get("mfLLBoox")) {
                                    String link = e.getMessage().reply("You have received 1x Luthier!  Congratulations!  Please DM a Bot Admin or Stradivarius Violin#6156 to claim your prize.").mentionRepliedUser(true).complete().getJumpUrl();
                                    EmbedBuilder builder = new EmbedBuilder()
                                            .setColor(Color.BLUE)
                                            .setFooter("Ling Ling\nReact when Claimed", e.getJDA().getSelfUser().getAvatarUrl())
                                            .addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), "Message Link: " + link, false)
                                            .setTitle("__**Luthier Pulled from Ling Ling Box**__");
                                    Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
                                } else {
                                    data.replace("mfLLBox", true);
                                    data.replace("magicFind", (long) data.get("magicFind") + 1);
                                    e.getMessage().reply("You found +1 Magic Find!").mentionRepliedUser(false).queue();
                                }
                            }
                        }
                        new SaveData(e, data);
                    }
                }
                case "crazybox" -> {
                    if ((long) data.get("crazyBox") <= 0) {
                        e.getMessage().reply("You already used all your Crazy Person Boxes, play the bot to get more!").mentionRepliedUser(false).queue();
                    } else if (income == 0) {
                        e.getMessage().reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").mentionRepliedUser(false).queue();
                    } else {
                        data.replace("crazyBox", (long) data.get("crazyBox") - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if ((boolean) data.get("medalToday")) {
                            if (rng < 0.3) {
                                long hourly = (long) data.get("income");
                                long min = hourly * 18;
                                long received = random.nextInt((int) min) + min;
                                data.replace("violins", (long) data.get("violins") + received);
                                e.getMessage().reply("You received " + Numbers.FormatNumber(received) + ":violin: from your Crazy Person Box!").mentionRepliedUser(false).queue();

                            } else if (rng < 0.6) {
                                int received = random.nextInt(11) + 20;
                                data.replace("rice", (long) data.get("rice") + received);
                                e.getMessage().reply("You received " + received + ":rice: from your Crazy Person Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.9) {
                                int received = random.nextInt(9) + 12;
                                data.replace("tea", (long) data.get("tea") + received);
                                e.getMessage().reply("You received " + received + ":bubble_tea: from your Crazy Person Box!").mentionRepliedUser(false).queue();
                            } else {
                                if((boolean) data.get("mfCrazyBox")) {
                                    String link = e.getMessage().reply("You have received 3x Luthier!  Congratulations!  Please DM a Bot Admin or Stradivarius Violin#6156 to claim your prize.").mentionRepliedUser(true).complete().getJumpUrl();
                                    EmbedBuilder builder = new EmbedBuilder()
                                            .setColor(Color.BLUE)
                                            .setFooter("Ling Ling\nReact when Claimed", e.getJDA().getSelfUser().getAvatarUrl())
                                            .addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), "Message Link: " + link, false)
                                            .setTitle("__**Luthier Pulled from Crazy Person Box**__");
                                    Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
                                } else {
                                    data.replace("mfCrazyBox", true);
                                    data.replace("magicFind", (long) data.get("magicFind") + 1);
                                    e.getMessage().reply("You found +1 Magic Find!").mentionRepliedUser(false).queue();
                                }
                            }
                        } else {
                            if (rng < 0.3) {
                                data.replace("medalToday", true);
                                long max = (long) data.get("income") / 20000;
                                long min = (long) data.get("income") / 30000;
                                long received = random.nextInt((int) (max - min + 1)) + min;
                                if (received > 15) {
                                    received = 15;
                                }
                                data.replace("medals", (long) data.get("medals") + received);
                                e.getMessage().reply("You received " + received + ":military_medal: from your Crazy Person Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.5) {
                                int received = random.nextInt(11) + 20;
                                data.replace("rice", (long) data.get("rice") + received);
                                e.getMessage().reply("You received " + received + ":rice: from your Crazy Person Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.7) {
                                int received = random.nextInt(9) + 12;
                                data.replace("tea", (long) data.get("tea") + received);
                                e.getMessage().reply("You received " + received + ":bubble_tea: from your Crazy Person Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.9) {
                                int received = random.nextInt(5) + 4;
                                data.replace("blessings", (long) data.get("blessings") + received);
                                e.getMessage().reply("You received " + received + ":angel: from your Crazy Person Box!").mentionRepliedUser(false).queue();
                            } else {
                                if((boolean) data.get("mfCrazyBox")) {
                                    String link = e.getMessage().reply("You have received 3x Luthier!  Congratulations!  Please DM a Bot Admin or Stradivarius Violin#6156 to claim your prize.").mentionRepliedUser(true).complete().getJumpUrl();
                                    EmbedBuilder builder = new EmbedBuilder()
                                            .setColor(Color.BLUE)
                                            .setFooter("Ling Ling\nReact when Claimed", e.getJDA().getSelfUser().getAvatarUrl())
                                            .addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), "Message Link: " + link, false)
                                            .setTitle("__**Luthier Pulled from Crazy Person Box**__");
                                    Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
                                } else {
                                    data.replace("mfCrazyBox", true);
                                    data.replace("magicFind", (long) data.get("magicFind") + 1);
                                    e.getMessage().reply("You found +1 Magic Find!").mentionRepliedUser(false).queue();
                                }
                            }
                        }
                        new SaveData(e, data);
                    }
                }
                case "rngesus" -> {
                    if ((long) data.get("RNGesusBox") <= 0) {
                        e.getMessage().reply("You already used all your RNGesus Boxes, pray to RNGesus to get more!").mentionRepliedUser(false).queue();
                    } else if (income == 0) {
                        e.getMessage().reply("Very unwise of you to use this item when you have zero income as you would get zero violins.  Grow a brain.").mentionRepliedUser(false).queue();
                    } else {
                        data.replace("RNGesusBox", (long) data.get("RNGesusBox") - 1);
                        Random random = new Random();
                        double rng = random.nextDouble();
                        if ((boolean) data.get("medalToday")) {
                            if (rng < 0.3) {
                                long hourly = (long) data.get("income");
                                long min = hourly * 24;
                                long received = random.nextInt((int) min) + min;
                                data.replace("violins", (long) data.get("violins") + received);
                                e.getMessage().reply("You received " + Numbers.FormatNumber(received) + ":violin: from your RNGesus Box!").mentionRepliedUser(false).queue();
                    
                            } else if (rng < 0.6) {
                                int received = random.nextInt(16) + 30;
                                data.replace("rice", (long) data.get("rice") + received);
                                e.getMessage().reply("You received " + received + ":rice: from your RNGesus Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.9) {
                                int received = random.nextInt(11) + 20;
                                data.replace("tea", (long) data.get("tea") + received);
                                e.getMessage().reply("You received " + received + ":bubble_tea: from your RNGesus Box!").mentionRepliedUser(false).queue();
                            } else {
                                if((boolean) data.get("mfRNGesusBox")) {
                                    String link = e.getMessage().reply("You have received 5x Luthier!  Congratulations!  Please DM a Bot Admin or Stradivarius Violin#6156 to claim your prize.").mentionRepliedUser(true).complete().getJumpUrl();
                                    EmbedBuilder builder = new EmbedBuilder()
                                            .setColor(Color.BLUE)
                                            .setFooter("Ling Ling\nReact when Claimed", e.getJDA().getSelfUser().getAvatarUrl())
                                            .addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), "Message Link: " + link, false)
                                            .setTitle("__**Luthier Pulled from RNGesus Box**__");
                                    Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
                                } else {
                                    data.replace("mfRNGesusBox", true);
                                    data.replace("magicFind", (long) data.get("magicFind") + 1);
                                    e.getMessage().reply("You found +1 Magic Find!").mentionRepliedUser(false).queue();
                                }
                            }
                        } else {
                            if (rng < 0.3) {
                                data.replace("medalToday", true);
                                long max = (long) data.get("income") / 15000;
                                long min = (long) data.get("income") / 20000;
                                long received = random.nextInt((int) (max - min + 1)) + min;
                                if (received > 25) {
                                    received = 25;
                                }
                                data.replace("medals", (long) data.get("medals") + received);
                                e.getMessage().reply("You received " + received + ":military_medal: from your RNGesus Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.5) {
                                int received = random.nextInt(16) + 30;
                                data.replace("rice", (long) data.get("rice") + received);
                                e.getMessage().reply("You received " + received + ":rice: from your RNGesus Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.7) {
                                int received = random.nextInt(11) + 20;
                                data.replace("tea", (long) data.get("tea") + received);
                                e.getMessage().reply("You received " + received + ":bubble_tea: from your RNGesus Box!").mentionRepliedUser(false).queue();
                            } else if (rng < 0.9) {
                                int received = random.nextInt(5) + 8;
                                data.replace("blessings", (long) data.get("blessings") + received);
                                e.getMessage().reply("You received " + received + ":angel: from your RNGesus Box!").mentionRepliedUser(false).queue();
                            } else {
                                if((boolean) data.get("mfRNGesusBox")) {
                                    String link = e.getMessage().reply("You have received 5x Luthier!  Congratulations!  Please DM a Bot Admin or Stradivarius Violin#6156 to claim your prize.").mentionRepliedUser(true).complete().getJumpUrl();
                                    EmbedBuilder builder = new EmbedBuilder()
                                            .setColor(Color.BLUE)
                                            .setFooter("Ling Ling\nReact when Claimed", e.getJDA().getSelfUser().getAvatarUrl())
                                            .addField("User: " + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), "Message Link: " + link, false)
                                            .setTitle("__**Luthier Pulled from RNGesus Box**__");
                                    Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessageEmbeds(builder.build()).queue();
                                } else {
                                    data.replace("mfRNGesusBox", true);
                                    data.replace("magicFind", (long) data.get("magicFind") + 1);
                                    e.getMessage().reply("You found +1 Magic Find!").mentionRepliedUser(false).queue();
                                }
                            }
                        }
                        new SaveData(e, data);
                    }
                }
                default -> e.getMessage().reply("You can't use something that doesn't exist, that doesn't make sense.").mentionRepliedUser(false).queue();
            }
        } catch (Exception exception) {
            e.getMessage().reply("You can't use nothing stupid.").mentionRepliedUser(false).queue();
        }
    }
}