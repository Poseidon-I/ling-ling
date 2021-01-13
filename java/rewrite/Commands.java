package rewrite;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class Commands extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        Random random = new Random();
        BufferedReader reader;
        PrintWriter writer = null;
        String[] message = e.getMessage().getContentRaw().split(" ");
        boolean isDev = false;
        if (e.getAuthor().getId().equals("619989388109152256") || e.getAuthor().getId().equals("488487157372157962") || e.getAuthor().getId().equals("706933826193981612") || e.getAuthor().getId().equals("733409243222507670")) {
            isDev = true;
        }
        //HOURLY
        long time = 0;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\hourly.txt"));
            time = Long.parseLong(reader.readLine());
            reader.close();
        } catch (Exception exception) {
            //nothing here lol
        }
        if(System.currentTimeMillis() > time) {
            time += 3600000;
            File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
            File[] files = directory.listFiles();
            String data = null;
            for(File file : files) {
                try {
                    reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                    data = reader.readLine();
                    reader.close();
                } catch (Exception exception) {
                    //nothing here lol
                }
                assert data != null;
                String[] array = data.split(" ");
                long violins = Integer.parseInt(array[0]);
                int income = Integer.parseInt(array[12]);
                violins += income;
                StringBuilder newData = new StringBuilder("" + violins);
                for(int i = 1; i < array.length; i ++) {
                    newData.append(" ").append(array[i]);
                }
                PrintWriter pw;
                try {
                    pw = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                    pw.print(newData);
                    pw.close();
                } catch (Exception exception) {
                    //nothing here lol
                }
            }
            try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\hourly.txt")));
                pw.print(time);
                pw.close();
            } catch(Exception exception) {
                //nothing here lol
            }
            String id = e.getJDA().getGuildById("709632179340312597").getTextChannelById("717484601328533565").sendMessage("!resetincomes").complete().getId();
            e.getJDA().getGuildById("709632179340312597").getTextChannelById("717484601328533565").deleteMessageById(id).queue();
        }

        //LUTHIER
        String[] data = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt"));
            data = reader.readLine().split(" ");
            reader.close();
        } catch(Exception exception) {
            //nothing here lol
        }
        try {
            assert data != null;
            if(data[3].equals("false")) {
                double chance = Double.parseDouble(data[2]);
                if(random.nextDouble() <= chance && !e.getChannel().getId().equals(data[0])) {
                    TextChannel channel = e.getGuild().getTextChannelById(data[0]);
                    String[] words = {"violin", "piano", "cello", "bass", "flute", "piccolo", "clarinet", "oboe", "bassoon", "trumpet", "french horn", "trombone", "tuba", "percussion", "orchestra", "conductor", "piece", "bach", "vivaldi", "handel", "mozart", "haydn", "beethoven", "chopin", "paganini", "mendelssohn", "rachmaninoff", "tchaikovsky", "sibelius", "mahler", "eroica", "pastoral", "ode to joy", "symphony", "concerto", "sacrilegious", "brett", "eddy", "twoset", "practice", "ling ling", "concertmaster", "ray chen", "hilary hahn", "lamentable", "amazing", "interesting", "r/lingling40hrs", "jacqueline", "stradivarius", "guarneri", "sacrinterestinglegious", "repertoire", "caprice", "col legno", "pianissimo", "forte", "fortissimo", "crescendo", "diminuendo", "ritardando", "accelerando", "waltz", "clair de lune", "prelude", "partita", "sonata", "fugue", "baroque", "classical", "romantic", "larghissimo", "largo", "andante", "moderato", "grave", "allegro", "vivace", "presto", "prestissimo", "appoggiatura", "breve", "semibreve", "minim", "crotchet", "quaver", "semiquaver", "demisemiquaver", "hemidemisemiquaver"};
                    String word = words[random.nextInt(words.length)];
                    String original = word;
                    int money = (random.nextInt(1000) + 1) * Integer.parseInt(data[1]);
                    String write = data[0] + " " + data[1] + " " + data[2] + " true " + word + " " + money;
                    char[] scrambler = new char[word.length()];
                    int i = 0;
                    StringBuilder send;
                    do {
                        while (word.length() > 0) {
                            int num = random.nextInt(word.length());
                            StringBuilder newWord = new StringBuilder();
                            char temp = word.charAt(num);
                            scrambler[i] = temp;
                            for (int j = 0; j < word.length(); j++) {
                                if (j != num) {
                                    newWord.append(word.charAt(j));
                                }
                            }
                            word = newWord.toString();
                            i++;
                        }
                        send = new StringBuilder();
                        for (i = 0; i < scrambler.length; i++) {
                            send.append(scrambler[i]);
                        }
                    } while (send.toString().equals(original));
                    assert channel != null;
                    channel.sendMessage("Olaf is giving away violins!  Unscramble `" + send + "` to get " + money + ":violin:").queue();
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt")));
                        writer.print(write);
                        writer.close();
                    } catch(Exception exception) {
                        //nothing here lol
                    }
                }
            } else if(data[3].equals("true") && !e.getAuthor().isBot() && e.getChannel().getId().equals(data[0])) {
                StringBuilder target = new StringBuilder();
                for (int i = 4; i < data.length - 1; i++) {
                    target.append(data[i]).append(" ");
                }
                target.deleteCharAt(target.length() - 1);
                int gain = Integer.parseInt(data[data.length - 1]);
                String user;
                if(e.getMessage().getContentRaw().toLowerCase().equals(target.toString())) {
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt."));
                        user = reader.readLine();
                        reader.close();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("You don't even have a profile, where would you store your violins???  Run `!start` in a bot command chanel to get one!").queue();
                        throw new IllegalArgumentException();
                    }
                    long violins = Integer.parseInt(user.split(" ")[0]);
                    e.getChannel().sendMessage("**" + e.getAuthor().getName() + "** unscrambled `" + target + "` and gained " + gain + ":violin:").queue();
                    violins += gain;
                    StringBuilder newData = new StringBuilder("" + violins);
                    for(int i = 1; i < user.split(" ").length; i ++) {
                        newData.append(" ").append(user.split(" ")[i]);
                    }
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt")));
                        writer.print(newData.toString());
                        writer.close();
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt")));
                        writer.print(data[0] + " " + data[1] + " " + data[2] + " false blank 0");
                        writer.close();
                    } catch(Exception exception) {
                        //nothing here lol
                    }
                } else {
                    e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+274c").queue();
                }
            }
        } catch(Exception exception) {
            //nothing here lol
        }

        //DEV COMMANDS
        if (isDev) {
            switch (message[0]) {
                case "!status" -> {
                    e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                    switch (message[1]) {
                        case "online" -> e.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
                        case "away" -> e.getJDA().getPresence().setStatus(OnlineStatus.IDLE);
                        case "dnd" -> e.getJDA().getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
                    }
                }
                case "!activity" -> {
                    e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                    StringBuilder activity = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        activity.append(message[i]).append(" ");
                    }
                    switch (message[1]) {
                        case "playing" -> e.getJDA().getPresence().setActivity(Activity.playing(activity.toString()));
                        case "listening" -> e.getJDA().getPresence().setActivity(Activity.listening(activity.toString()));
                        case "watching" -> e.getJDA().getPresence().setActivity(Activity.watching(activity.toString()));
                        case "streaming" -> e.getJDA().getPresence().setActivity(Activity.streaming(activity.toString(), "twitch.tv"));
                        case "nothing" -> e.getJDA().getPresence().setActivity(null);
                    }
                }
                case "!lookdata" -> {
                    String userData;
                    String target = message[1];
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                        userData = reader.readLine();
                        e.getChannel().sendMessage(Objects.requireNonNull(e.getJDA().getUserById(target)).getName() + "'s data: " + userData).queue();
                        reader.close();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("This user save doesn't exist!").queue();
                        throw new IllegalArgumentException();
                    }
                }
                case "!editdata" -> {
                    String id = message[1];
                    StringBuilder userData = new StringBuilder();
                    for (int i = 2; i < message.length; i++) {
                        userData.append(message[i]).append(" ");
                    }
                    userData.deleteCharAt(userData.length() - 1);
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt")));
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("This user save doesn't exist!").queue();
                        throw new IllegalArgumentException();
                    }
                    writer.print(userData);
                    writer.close();
                    e.getChannel().sendMessage("Successfully edited the data of " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName()).queue();
                }
                case "!give" -> {
                    String id = message[1];
                    int add = Integer.parseInt(message[2]);
                    long violins;
                    String userData;
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt"));
                        userData = reader.readLine();
                        violins = Integer.parseInt(userData.split(" ")[0]) + add;
                        reader.close();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("This user save doesn't exist!").queue();
                        throw new IllegalArgumentException();
                    }
                    StringBuilder newData = new StringBuilder();
                    newData.append(violins);
                    for (int i = 1; i < userData.split(" ").length; i++) {
                        newData.append(" ").append(userData.split(" ")[i]);
                    }
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + id + ".txt")));
                    } catch (Exception exception) {
                        throw new IllegalArgumentException();
                    }
                    writer.print(newData);
                    writer.close();
                    e.getChannel().sendMessage("Successfully gave " + add + ":violin: to " + Objects.requireNonNull(e.getJDA().getUserById(id)).getName()).queue();
                }
                case "!luthier" -> {
                    try {
                        if (message[1].equals("setup")) {
                            File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt");
                            try {
                                file.createNewFile();
                                writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt")));
                            } catch (Exception exception2) {
                                //nothing here lol
                            }
                            assert writer != null;
                            writer.print(e.getChannel().getId() + " 1 0.02 false blank 0");
                            writer.close();
                            e.getChannel().sendMessage("Successfully set up Luthier for " + e.getGuild().getName()).queue();
                        } else if (message[1].equals("edit")) {
                            String id = e.getGuild().getId();
                            StringBuilder luthierData = new StringBuilder();
                            for (int i = 2; i < message.length; i++) {
                                luthierData.append(message[i]).append(" ");
                            }
                            luthierData.deleteCharAt(luthierData.length() - 1);
                            try {
                                writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + id + ".txt")));
                                writer.print(luthierData);
                                writer.close();
                                e.getChannel().sendMessage("Successfully edited the data of Luthier for " + e.getGuild().getName()).queue();
                            } catch (Exception exception1) {
                                //nothing here lol
                            }
                        }
                    } catch (Exception exception) {
                        String luthierData;
                        try {
                            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt"));
                            luthierData = reader.readLine();
                            e.getChannel().sendMessage("Luthier Settings for " + e.getGuild().getName() + ": " + luthierData).queue();
                            reader.close();
                        } catch (Exception exception1) {
                            e.getChannel().sendMessage("Luthier has not been set up!").queue();
                        }
                    }
                }
                case "!updateservers" -> {
                    if (message.length > 1 && message[1].equals("confirm")) {
                        e.getChannel().sendMessage("Updating saves for all servers...").queue();
                        File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server");
                        File[] files = directory.listFiles();
                        StringBuilder append = new StringBuilder();
                        for (int i = 2; i < message.length; i++) {
                            append.append(" ").append(message[i]);
                        }
                        if (files != null) {
                            for (File file : files) {
                                try {
                                    reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                    String currentData = reader.readLine();
                                    reader.close();
                                    writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                                    writer.print(currentData + append);
                                    writer.close();
                                } catch (Exception exception) {
                                    //nothing here lol
                                }
                            }
                            e.getChannel().sendMessage("Successfully updated saves for all servers!").queue();
                        }
                    } else {
                        e.getChannel().sendMessage("Please type `!updateservers confirm` to confirm that you would like to update all data to the latest version.").queue();
                    }
                }
                case "!updateusers" -> {
                    if (message.length > 1 && message[1].equals("confirm")) {
                        e.getChannel().sendMessage("Updating saves for all users...").queue();
                        File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
                        File[] files = directory.listFiles();
                        StringBuilder append = new StringBuilder();
                        for (int i = 2; i < message.length; i++) {
                            append.append(" ").append(message[i]);
                        }
                        if (files != null) {
                            for (File file : files) {
                                try {
                                    reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                    String currentData = reader.readLine();
                                    reader.close();
                                    writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                                    writer.print(currentData + append);
                                    writer.close();
                                } catch (Exception exception) {
                                    //nothing here lol
                                }
                            }
                            e.getChannel().sendMessage("Successfully updated saves for all users!").queue();
                        }
                    } else {
                        e.getChannel().sendMessage("Please type `!updateusers confirm` to confirm that you would like to update all data to the latest version.").queue();
                    }
                }
                case "!purgeusers" -> {
                    e.getChannel().sendMessage("Purging saves for users with no violins...").queue();
                    File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
                    File[] files = directory.listFiles();
                    if (files != null) {
                        int deleted = 0;
                        for (File file : files) {
                            try {
                                reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                long violins = Integer.parseInt(reader.readLine().split(" ")[0]);
                                reader.close();
                                if (violins == 0) {
                                    file.delete();
                                    deleted++;
                                }
                            } catch (Exception exception) {
                                //nothing here lol
                            }
                        }
                        e.getChannel().sendMessage("Successfully purged " + deleted + " files!").queue();
                    }
                }
                case "!resetincomes" -> {
                    if(!e.getAuthor().isBot()) {
                        e.getChannel().sendMessage("Maually overriding automatic user save reset...").queue();
                    }
                    File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
                    File[] files = directory.listFiles();
                    if (files != null) {
                        for (File file : files) {
                            try {
                                reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                data = reader.readLine().split(" ");
                            } catch (Exception exception) {
                                continue;
                            }
                            int violinQuality = Integer.parseInt(data[13]);
                            int skillLevel = Integer.parseInt(data[14]);
                            int lessonQuality = Integer.parseInt(data[15]);
                            int stringQuality = Integer.parseInt(data[16]);
                            int bowQuality = Integer.parseInt(data[17]);
                            boolean hasMath = Boolean.parseBoolean(data[18]);
                            boolean hasOrchestra = Boolean.parseBoolean(data[19]);
                            boolean piccolo = Boolean.parseBoolean(data[20]);
                            int flute = Integer.parseInt(data[21]);
                            int oboe = Integer.parseInt(data[22]);
                            int clarinet = Integer.parseInt(data[23]);
                            int bassoon = Integer.parseInt(data[24]);
                            boolean contrabassoon = Boolean.parseBoolean(data[25]);
                            int horn = Integer.parseInt(data[26]);
                            int trumpet = Integer.parseInt(data[27]);
                            int trombone = Integer.parseInt(data[28]);
                            int tuba = Integer.parseInt(data[29]);
                            int timpani = Integer.parseInt(data[30]);
                            int percussion = Integer.parseInt(data[31]);
                            int first = Integer.parseInt(data[32]);
                            int second = Integer.parseInt(data[33]);
                            int cello = Integer.parseInt(data[34]);
                            int stringBass = Integer.parseInt(data[35]);
                            int piano = Integer.parseInt(data[36]);
                            boolean harp = Boolean.parseBoolean(data[37]);
                            int soprano = Integer.parseInt(data[38]);
                            int alto = Integer.parseInt(data[39]);
                            int tenor = Integer.parseInt(data[40]);
                            int bass = Integer.parseInt(data[41]);
                            int soloists = Integer.parseInt(data[42]);
                            int hallLevel = Integer.parseInt(data[43]);
                            int conductor = Integer.parseInt(data[44]);
                            int advertising = Integer.parseInt(data[45]);
                            int tickets = Integer.parseInt(data[46]);
                            boolean moreIncome = Boolean.parseBoolean(data[56]);
                            int income = violinQuality * 600 + skillLevel * 240 + lessonQuality * 150 + stringQuality * 100 + bowQuality * 200 + flute * 60 + oboe * 50 + clarinet * 40 + bassoon * 40 + horn * 40 + trumpet * 30 + trombone * 20 + tuba * 20 + timpani * 60 + percussion * 10 + first * 70 + second * 60 + cello * 50 + stringBass * 50 + piano * 110 + soprano * 30 + alto * 20 + tenor * 20 + bass * 20 + soloists * 60 + hallLevel * 300 + conductor * 200 + advertising * 100 + tickets * 1000;
                            if(hasMath) {
                                income += 2500;
                            }
                            if(hasOrchestra) {
                                income += 3000;
                            } else {
                                income -= 130;
                            }
                            if(piccolo) {
                                income += 30;
                            }
                            if(contrabassoon) {
                                income += 30;
                            }
                            if(harp) {
                                income += 80;
                            }
                            if(moreIncome) {
                                income += 8000;
                            }
                            data[12] = income + "";
                            StringBuilder write = new StringBuilder(data[0]);
                            for(int i = 1; i < data.length; i ++) {
                                write.append(" ").append(data[i]);
                            }
                            try {
                                writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                                writer.print(write);
                                writer.close();
                            } catch(Exception exception) {
                                //nothing here lol
                            }
                        }
                        e.getChannel().sendMessage("Incomes successfully reset!").queue();
                    }
                }
            }
        }
        String fullMessage = e.getMessage().getContentRaw();
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt"));
            data = reader.readLine().split(" ");
            reader.close();
        } catch (Exception exception) {
            File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt");
            try {
                file.createNewFile();
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt")));
                pw.print("true true false true true");
                pw.close();
            } catch(Exception exception1) {
                //nothing here lol
            }
        }
        if (!e.getAuthor().isBot()) {
            boolean ranCommand = false;
            String server = "";
            char prefix = '!';
            try {
                server = e.getGuild().getId();
                reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt"));
                prefix = (char) reader.read();
                reader.close();
            } catch (Exception exception) {
                File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt");
                try {
                    file.createNewFile();
                    writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt")));
                } catch (Exception exception1) {
                    //nothing here lol
                }
                assert writer != null;
                writer.print('!');
                writer.close();
            }
            if (isDev) {
                prefix = '!';
            }
            try {
                if (e.getMessage().getMentionedUsers().get(0).getId().equals("733409243222507670")) {
                    e.getChannel().sendMessage("Hello!  My prefix in this server is `" + prefix + "`\nIf you have other issues, run `" + prefix + "support` to get an invite to the support server!").queue();
                }
            } catch (Exception exception) {
                //nothing here lol
            }
            if (message[0].charAt(0) == prefix) {
                boolean hasBotId = false;
                boolean hasSameUser = false;
                boolean hasDevPing = false;
                try {
                    if (e.getMessage().getMentionedMembers().contains(e.getGuild().getMemberById(e.getAuthor().getId()))) {
                        hasSameUser = true;
                    }
                    if (e.getMessage().getMentionedMembers().contains(e.getGuild().getMemberById("733409243222507670"))) {
                        hasBotId = true;
                    }
                    if (e.getMessage().getMentionedMembers().contains(e.getGuild().getMemberById("619989388109152256")) || e.getMessage().getMentionedMembers().contains(e.getGuild().getMemberById("488487157372157962")) || e.getMessage().getMentionedMembers().contains(e.getGuild().getMemberById("706933826193981612"))) {
                        hasDevPing = true;
                    }
                } catch (Exception exception) {
                    //nothing here lol
                }
                String target = "";
                String targetPing = "";
                try {
                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt"));
                    data = reader.readLine().split(" ");
                    reader.close();
                } catch (Exception exception) {
                    //nothing here lol
                }
                try {
                    target = e.getMessage().getMentionedUsers().get(0).getId();
                    targetPing = e.getMessage().getMentionedUsers().get(0).getName();
                } catch (Exception exception) {
                    try {
                        target = message[1];
                        targetPing = Objects.requireNonNull(e.getJDA().getUserById(message[1])).getName();
                    } catch (Exception exception1) {
                        if (data[1].equals("true") && random.nextDouble() < 0.025) {
                            e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F3BB").queue();
                        }
                    }
                }
                if (e.getMessage().getContentRaw().contains("@everyone") && e.getMessage().getContentRaw().charAt(0) == prefix || e.getMessage().getContentRaw().contains("@here") && e.getMessage().getContentRaw().charAt(0) == prefix || e.getMessage().getContentRaw().contains("<@&") && e.getMessage().getContentRaw().charAt(0) == prefix) {
                    e.getChannel().sendMessage("why the hell did you ping here, everyone, or a role dumbass").queue();
                    throw new IllegalArgumentException();
                } else if (e.getAuthor().getName().contains("@everyone") && e.getMessage().getContentRaw().charAt(0) == prefix || e.getAuthor().getName().contains("@here") && e.getMessage().getContentRaw().charAt(0) == prefix || e.getAuthor().getName().contains("<@&") && e.getMessage().getContentRaw().charAt(0) == prefix) {
                    e.getChannel().sendMessage("Nice try but no").queue();
                    throw new IllegalArgumentException();
                } else if (targetPing.contains("@everyone") && e.getMessage().getContentRaw().charAt(0) == prefix || targetPing.contains("@here") && e.getMessage().getContentRaw().charAt(0) == prefix || targetPing.contains("<@&") && e.getMessage().getContentRaw().charAt(0) == prefix) {
                    e.getChannel().sendMessage("Nice try but no").queue();
                    throw new IllegalArgumentException();
                }
                message[0] = message[0].substring(1);
                switch (message[0]) {
                    case "prefix" -> {
                        if (message.length == 1) {
                            e.getChannel().sendMessage("The prefix is `" + prefix + "`").queue();
                        } else {
                            if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.ADMINISTRATOR) || isDev) {
                                char newPrefix = message[1].charAt(0);
                                try {
                                    writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Prefixes\\" + server + ".txt")));
                                } catch (Exception exception1) {
                                    //nothing here lol
                                }
                                assert writer != null;
                                writer.print(newPrefix);
                                writer.close();
                                e.getChannel().sendMessage("The prefix is now `" + newPrefix + "`").queue();
                            } else {
                                e.getChannel().sendMessage("You must have the `ADMINISTRATOR` permission or be a bot developer to change the prefix.").queue();
                            }
                        }
                        ranCommand = true;
                    }
                    case "help" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE).setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl()).setTitle("__**Ling Ling Bot Help**__");
                        try {
                            switch (message[1]) {
                                case "1" -> builder.addField("Help List Page 1 - Moderation\nRun `" + prefix + "help <commandName>` to view a command in depth", "`warn`\n`mute`\n`unmute`\n~~`kick`~~ Currently disabled.\n~~`ban`~~ Currently disabled.", false);
                                case "2" -> builder.addField("Help List Page 2 - Fun\nRun `" + prefix + "help <commandName>` to view a command in depth", "`joke`\n`insult`\n`kill`\n`emojify`", false);
                                case "3" -> builder.addField("Help List Page 3 - Utility\nRun `" + prefix + "help <commandName>` to view a command in depth",
                                        "`help`\n`suggest`\n`poll`\n`checkdm`\n`invite`\n`serversettings`\n`support`\n`prefix`", false);
                                case "4" -> builder.addField("Help List Page 4 - Economy Commands\nRun `" + prefix + "help <commandName>` to view a command in depth.",
                                        "`start`\n`practice`\n`rehearse`\n`perform`\n`daily`\n`gamble`\n`balance`\n`profile`\n`inventory`\n`cooldowns`\n`rob`\n`upgrades`\n`buy`\n`use`\n`leaderboard`", false);
                                case "5" -> builder.addField("Help List Page 5 - Dev Only Commands\nOnly Developers have access to these commands.\nThis page exists for people who are curious.",
                                        "`status`\n`activity`\n`lookdata`\n`editdata`\n`luthier`\n`updateservers`\n`updateusers`\n`give`\n`purgeusers`", false);
                                case "cooldowns" -> builder.addField("Cooldowns Command", "Syntax: `" + prefix + "cooldowns`\nUsage: Shows you the cooldowns of all commands that have cooldowns.\nAliases: `" + prefix + "c`", false);
                                case "use" -> builder.addField("Use Command", "Syntax: `" + prefix + "use <id>`\nUsage: Uses usable items like Ling Ling Insurance.\nExample: `" + prefix + "use 1`", false);
                                case "upgrades" -> builder.addField("Upgrades Command", "Syntax: `" + prefix + "upgrades [page]`\nUsage: Shows you a page of the shop.\nAliases: `" + prefix + "up`, `" + prefix + "u`, `" + prefix + "shop`\nExample: `" + prefix + "upgrades 2`", false);
                                case "buy" -> builder.addField("Buy Command", "Syntax: `" + prefix + "buy <itemID>`\nUsage: Buys an upgrade.\nExample: `" + prefix + "buy practising`", false);
                                case "orchestra" -> builder.addField("Orchestra Command", "Syntax: `" + prefix + "orchestra`\nUsage: Shows the statistics of your orchestra.\nAliases: `" + prefix + "o`", false);
                                case "editdata" -> builder.addField("EditData Command", "Syntax: `!editdata <userID> [New Data]`\nUsage: Edits the profile of a user.\nRestrictions: Usable only by Developers.", false);
                                case "updateusers" -> builder.addField("UpdateUsers Command", "Syntax: `!updateusers <confirm> [Data to Append]`\nUsage: Updates the economy save format.\nRestrictions: Usable only by Developers.", false);
                                case "updateservers" -> builder.addField("UpdateServers Command", "Syntax: `!updateservers <confirm> [Data to Append]`\nUsage: Updates the server settings save format.\nRestrictions: Usable only by Developers.", false);
                                case "purgeusers" -> builder.addField("PurgeUsers Command", "Syntax: `!purgeusers`\nUsage: Deletes all economy save files for users with 0 violins.\nRestricitons: Usable only by Developers.", false);
                                case "resetincomes" -> builder.addField("ResetIncomes Command", "Syntax: `!resetincomes`\nUsage: Manually resets all incomes.\nRestricitons: Usable only by Developers.", false);
                                case "give" -> builder.addField("Give Command", "Syntax: `!give <id> <amount>`\nUsage: Gives the user an amount of violins.\nRestrictions: Usable only by Developers.", false);
                                case "lookdata" -> builder.addField("LookData Command", "Syntax: `!lookdata <userID>`\nUsage: Shows the profile of a user.\nRestrictions: Usable only by Developers.", false);
                                case "luthier" -> builder.addField("Luthier Command", "Syntax: `!luthier <setup | edit> [New Data]`\nUsage: Sets up, or edits the settings of a server's Luthier.\nRestrictions: Usable only by Developers.", false);
                                case "practice" -> builder.addField("Practice Command", "Syntax: `" + prefix + "practice`\nUsage: Practise to earn some violins!\nCooldown: 45 Minutes\nAliases: `" + prefix + "p`", false);
                                case "rehearse" -> builder.addField("Rehearse Command", "Syntax: `" + prefix + "rehearse`\nUsage: Rehearse with an orchestra to earn loads of violins!\nRestrictions: Usable only by people with Orchestras.\nCooldown: 1 day\nAliases: `" + prefix + "r`", false);
                                case "perform" -> builder.addField("Perform Command", "Syntax: `" + prefix + "perform`\nUsage: Perform your solo to earn an INSANE amount of violins!  Gain even more by hiring an orchestra and upgrading your Concert Hall!\nCooldown: 1 week", false);
                                case "daily" -> builder.addField("Daily Command", "Syntax: `" + prefix + "daily`\nUsage: Get a daily dose of violins!  Run the command many days in a row to start gaining a streak and get even more violins!\nCooldown: 1 day\n~liases: `" + prefix + "d`", false);
                                case "rob" -> builder.addField("Rob Command", "Syntax: `" + prefix + "rob <user>`\nUsage: Robs the user.  Beware that if you have more violins than the target, the harder it is to succeed!\nCooldown: 1 day\nExample: `" + prefix + "rob 488487157372157962`", false);
                                case "start" -> builder.addField("Start Command", "Syntax: `" + prefix + "start`\nUsage: Creates a profile for the user.  Can only be used once.", false);
                                case "gamble" -> builder.addField("Gamble Command", "Syntax: `" + prefix + "gamble <amount>`\nUsage: Bets the amount with a chance of winning determined by the Lucky Musician upgrade.\nCooldown: 10 seconds\nAliases: `" + prefix + "bet`\nExample: `" + prefix + "gamble 100`", false);
                                case "balance" -> builder.addField("Balance Command", "Syntax: `" + prefix + "balance [user]`\nUsage: Shows your balance, or the balance of another user.\nAliases: `" + prefix + "bal`, `" + prefix + "b`", false);
                                case "inventory" -> builder.addField("Inventory Command", "Syntax: `" + prefix + "inventory`\nUsage: Shows your inventory.\nAliases: `" + prefix + "inv`", false);
                                case "profile" -> builder.addField("Profile Command", "Syntax: `" + prefix + "profile`\nUsage: Shows your full profile.", false);
                                case "leaderboard" -> builder.addField("Leaderboard Command", "Syntax: `" + prefix + "leaderboard`\nUsage: Shows the ten richest people in the world.\nAliases: `" + prefix + "lb`", false);
                                case "status" -> builder.addField("Status Command", "Syntax: `!status <online | idle | dnd>`\nUsage: Sets the status of the bot to Online, Idle, or DND.\nRestrictions: Usable only by Developers.", false);
                                case "activity" -> builder.addField("Activity Command", "Syntax: `!activity <playing | listening | watching> [activity]`\nUsage: Changes the custom status of the bot.\nRestrictions: Usable only by Developers.", false);
                                case "warn" -> builder.addField("Warn Command", "Syntax: `" + prefix + "warn <user> [reason]`\nUsage: Logs a warning in the moderation log for the mentioned user.\nRestrictions: Usable only by members with `Manage Messages` Permission.\nExample: `" + prefix + "warn 488487157372157962 not practisnig forty hours a day`", false);
                                //case "kick" -> builder.addField("Kick Command", "Syntax: `" + prefix + "kick <user> [reason]`\nUsage: Kicks the user from the server and logs a kick in the moderation log for the mentioned user.\nRestrictions: Usable only by members with `Kick Members` Permission.\nExample: `" + prefix + "kick 488487157372157962 shitposting bad viola jokes even after being told to stop`", false);
                                //case "ban" -> builder.addField("BAN Command", "Syntax: `" + prefix + "ban <user> [reason]`\nUsage: Bans the user from the server, and logs a ban in the moderation log for the mentioned user.\nRestrictions: Usable only by members with `Ban Members` Permission.\nExample: `" + prefix + "ban 488487157372157962 kept shitposting bad viola jokes even after being kicked three times`", false);
                                case "joke" -> builder.addField("Joke Command", "Syntax: `" + prefix + "joke`\nUsage: Returns a music-related joke", false);
                                case "insult" -> builder.addField("Insult Command", "Syntax: `" + prefix + "insult <user>`\nUsage: Insults the target.\nExample: `" + prefix + "insult 488487157372157962`", false);
                                case "kill" -> builder.addField("Kill Command", "Syntax: `" + prefix + "kill <user>`\nUsage: Totally kills the target\nExample: `" + prefix + "kill 488487157372157962`", false);
                                case "checkdm" -> builder.addField("CheckDM Command", "Syntax: `" + prefix + "checkdm <user>`\nUsage: Sends a pre-generated message telling the mentioned user to check their DM.  Highly effective.\nExample: `" + prefix + "checkdm 488487157372157962`", false);
                                case "poll" -> builder.addField("Poll Command", "Syntax: `" + prefix + "poll \"[Poll Name]\" \"[Options; separated; by; semicolons]`\"\nUsage: Creates a simple poll where reactions are used to vote.\nExample: `" + prefix + "poll \"Which instrument is better?\" \"Violin; Viola; Cello; Bass\"`", false);
                                case "mute" -> builder.addField("Mute Command", "Syntax: `" + prefix + "mute <user> [reason]`\nUsage: Mutes the user.\nRestrictions: Only usable by members with the `Manage Messages` Permission.\nExample: `" + prefix + "mute 488487157372157962 shitposting bad viola jokes`", false);
                                case "unmute" -> builder.addField("Unmute Command", "Syntax: `" + prefix + "unmute <user>`\nUsage: Unmutes the user.\nRestrictions: Only usable by members with the `Manage Messages` Permission.\nExample: `" + prefix + "unmute 488487157372157962 redeemed themselves by practising fifty hours a day`", false);
                                case "suggest" -> builder.addField("Suggest Command", "Syntax: `" + prefix + "suggest`\nUsage: Gives you the links to the suggestion pages..", false);
                                case "emojify" -> builder.addField("Emojify Command", "Syntax: `" + prefix + "emojify [message]`\nUsage: Returns your message except using regional indicator emojis.\nRestrictions: You can only use numbers, letters, spaces, question marks, and exclamation points.  Incompatible with mentions.\nExample: `" + prefix + "emojify go practise`", false);
                                case "invite" -> builder.addField("Invite Command", "Syntax: `" + prefix + "invite`\nUsage: Gives you instructions on how to invite the bot to your server.", false);
                                case "serversettings" -> builder.addField("ServerSettings Command", "Syntax: `" + prefix + "serversettings [autoresponse | reactions | logging | automod | modcommands] [true/on | false/off]`\nUsage: Toggles a setting to be ON or OFF.\nRestrictions: Usable only by members with the `ADMINISTRATOR` permission.\nExample: `" + prefix + "serversettings autoresponse false`", false);
                                case "support" -> builder.addField("Support Command", "Syntax: `" + prefix + "support`\nUsage: Gives a link to the support server.", false);
                                case "vote" -> builder.addField("Vote Command", "Syntax: `" + prefix + "vote`\nUsage: Gives a link to vote for the bot and the support server.", false);
                                case "prefix" -> builder.addField("Prefix Command", "Syntax: `!prefix [new]`\nUsage: Shows the current prefix.  Append a character to change the prefix.  This is the only command that will retain `!` across all servers.\nRestrictions: The prefix can only be changed by members with the `ADMINISTRATOR` permission.\nExample: `!prefix $`", false);
                                default -> builder.addField("Help List", "Page or command `" + message[1] + "` does not exist!  Run `" + prefix + "help` to see a list of pages.", false);
                            }
                            e.getChannel().sendMessage(builder.build()).queue();
                        } catch(Exception exception) {
                            builder.addField("Help List", "Use `" + prefix + "help {page}` to view further commands!" +
                                    "\n\n`1` - Moderation Commands\n`2` - Fun Commands\n`3` - Utility Commands\n`4` - Economy Commands\n`5` - Dev-Only Commands", false);
                            e.getChannel().sendMessage(builder.build()).queue();
                        }
                        ranCommand = true;
                    }
                    case "serversettings" -> {
                        if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.ADMINISTRATOR) || isDev) {
                            data = new String[0];
                            try {
                                reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt"));
                                data = reader.readLine().split(" ");
                                reader.close();
                            } catch (Exception exception) {
                                File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt");
                                try {
                                    file.createNewFile();
                                    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt")));
                                    pw.print("true true false true true");
                                    pw.close();
                                } catch(Exception exception1) {
                                    //nothing here lol
                                }
                            }
                            try {
                                switch (message[1]) {
                                    case "autoresponse" -> {
                                        try {
                                            writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt")));
                                            if (message[2].equals("false") || message[2].equals("off")) {
                                                writer.print("false " + data[1] + " " + data[2] + " " + data[3] + " " + data[4]);
                                                writer.close();
                                                e.getChannel().sendMessage("Turned off Autoresponse!").queue();
                                            } else if (message[2].equals("true") || message[2].equals("on")) {
                                                writer.print("true " + data[1] + " " + data[2] + " " + data[3] + " " + data[4]);
                                                writer.close();
                                                e.getChannel().sendMessage("Turned on Autoresponse!").queue();
                                            } else {
                                                e.getChannel().sendMessage("That format isn't supported!  Supported values are `true/false`, `on/off`").queue();
                                            }
                                        } catch (Exception exception1) {
                                            e.getChannel().sendMessage("That format isn't supported!  Supported values are `true/false`, `on/off`").queue();
                                        }
                                    }
                                    case "reactions" -> {
                                        try {
                                            writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt")));
                                            if (message[2].equals("false") || message[2].equals("off")) {
                                                writer.print(data[0] + " false " + data[2] + " " + data[3] + " " + data[4]);
                                                writer.close();
                                                e.getChannel().sendMessage("Turned off Reactions!").queue();
                                            } else if (message[2].equals("true") || message[2].equals("on")) {
                                                writer.print(data[0] + " true " + data[2] + " " + data[3] + " " + data[4]);
                                                writer.close();
                                                e.getChannel().sendMessage("Turned on Reactions!").queue();
                                            } else {
                                                e.getChannel().sendMessage("That format isn't supported!  Supported values are `true/false`, `on/off`").queue();
                                            }
                                        } catch (Exception exception1) {
                                            e.getChannel().sendMessage("You need to enter a value.  Currently supportad values: `true/false`, `on/off`").queue();
                                        }
                                    }
                                    case "logging" -> {
                                        try {
                                            writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt")));
                                            if (message[2].equals("false") || message[2].equals("off")) {
                                                writer.print(data[0] + " " + data[1] + " false " + data[3] + " " + data[4]);
                                                writer.close();
                                                e.getChannel().sendMessage("Turned off Message Logging!").queue();
                                            } else if (message[2].equals("true") || message[2].equals("on")) {
                                                writer.print(data[0] + " " + data[1] + " false " + data[3] + " " + data[4]);
                                                writer.close();
                                                e.getChannel().sendMessage("Turned on Message Logging!").queue();
                                            } else {
                                                e.getChannel().sendMessage("That format isn't supported!  Supported values are `true/false`, `on/off`").queue();
                                            }
                                        } catch (Exception exception1) {
                                            e.getChannel().sendMessage("You need to enter a value.  Currently supportad values: `true/false`, `on/off`").queue();
                                        }
                                    }
                                    case "modcommands" -> {
                                        try {
                                            writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + server + ".txt")));
                                            if (message[2].equals("false") || message[2].equals("off")) {
                                                writer.print(data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " false");
                                                writer.close();
                                                e.getChannel().sendMessage("Turned off Moderation Commands!").queue();
                                            } else if (message[2].equals("true") || message[2].equals("on")) {
                                                writer.print(data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " true");
                                                writer.close();
                                                e.getChannel().sendMessage("Turned on Moderation Commands!").queue();
                                            } else {
                                                e.getChannel().sendMessage("That format isn't supported!  Supported values are `true/false`, `on/off`").queue();
                                            }
                                        } catch (Exception exception1) {
                                            e.getChannel().sendMessage("You need to enter a value.  Currently supportad values: `true/false`, `on/off`").queue();
                                        }
                                    }
                                }
                            } catch (Exception exception) {
                                EmbedBuilder builder = new EmbedBuilder().setColor(Color.BLUE)
                                        .setTitle("Server Settings for " + e.getGuild().getName())
                                        .addField("Autoresponse", "A variety of more than forty triggers to add some pizzazz to your conversation!\nCurrent value: `" + data[0] + "`\nID: `autoresponse`", false)
                                        .addField("Reactions", "All messages sent in channels with \"announcement\" in their name will have a 100% chance to have the V I O L A reaction.\nAll other messages have a 2.5% chance of a V I O L A reactions.\nSome random messages will have :violin: reacted on them.\n Current value: `" + data[1] + "`\nID: `reactions`", false)
                                        .addField("Message Logging", "Will log Edited and Deleted Messages!  Work-in-progress, requires a channel named \"moderation-log\"\nCurrent value: `" + data[2] + "`\nID: `logging`", false)
                                        .addField("Moderation Commands", "Basic moderation commands such as `!warn`, `!mute`, `!kick`, and `!ban`.  This setting disables these commands for mods and admins.  Note that devs will always have the power to warn users for breaking bot rules.\nCurrent value: `" + data[4] + "`\nID: `modcommands`", false)
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
                                e.getChannel().sendMessage(builder.build()).queue();
                            }
                        } else {
                            e.getChannel().sendMessage("You must have the `ADMINISTRATOR` permission or be a bot developer to change the server settings.").queue();
                        }
                        ranCommand = true;
                    }
                    case "warn" -> {
                        if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.MESSAGE_MANAGE) && data[4].equals("true") || isDev) {
                            StringBuilder reason = new StringBuilder();
                            for (int i = 2; i < message.length; i++) {
                                reason.append(message[i]).append(" ");
                            }
                            if (hasSameUser) {
                                e.getChannel().sendMessage("why would you want to warn yourself, dumb?").queue();
                            } else if (hasBotId) {
                                e.getChannel().sendMessage("You cannot warn me, I am too powerful for you.").queue();
                            } else if (hasDevPing) {
                                e.getChannel().sendMessage("HAHA DEV ABOOZ").queue();
                            } else {
                                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                                User user;
                                try {
                                    user = e.getJDA().getUserById(target);
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You tried to warn a non-existant user.  What a waste of your time.").queue();
                                    break;
                                }
                                if (data[4].equals("true")) {
                                    assert user != null;
                                    EmbedBuilder builder = new EmbedBuilder()
                                            .setColor(Color.BLUE)
                                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                            .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                                            .setTitle("__**Warning Info**__");
                                    try {
                                        TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                                        textChannel.sendMessage(builder.build()).queue();
                                    } catch (Exception exception) {
                                        e.getChannel().sendMessage("The warning was not logged, you must have a channel named \"moderation log\"").queue();
                                    }
                                    user.openPrivateChannel().complete().sendMessage("You were warned in " + e.getGuild().getName() + " for " + reason + "!").queue();
                                    e.getChannel().sendMessage(":warning: " + targetPing + " was successfully warned!").queue();
                                }
                            }
                        } else if(data[4].equals("false")) {
                            e.getChannel().sendMessage("Moderation commands are disabled here!").queue();
                        } else {
                            e.getChannel().sendMessage(":x: You are not authorized to use this command!").queue();
                        }
                        ranCommand = true;
                    }
                    case "mute" -> {
                        if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.MESSAGE_MANAGE) && data[4].equals("true") || isDev && data[4].equals("true")) {
                            StringBuilder reason = new StringBuilder();
                            for (int i = 2; i < message.length; i++) {
                                reason.append(message[i]).append(" ");
                            }
                            if (hasSameUser) {
                                e.getChannel().sendMessage("why would you want to warn yourself, dumb?").queue();
                            } else if (hasBotId) {
                                e.getChannel().sendMessage("You cannot mute me, I am too powerful for you.").queue();
                            } else if (hasDevPing) {
                                e.getChannel().sendMessage("HAHA DEV ABOOZ").queue();
                            } else {
                                User user;
                                try {
                                    user = e.getJDA().getUserById(target);
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You tried to mute a non-existant user.  What a waste of your time.").queue();
                                    break;
                                }
                                if (server.equals("670725611207262219")) {
                                    try {
                                        assert user != null;
                                        e.getGuild().addRoleToMember(user.getId(), Objects.requireNonNull(e.getGuild().getRoleById("734697394389778462"))).queue();
                                    } catch (Exception exception) {
                                        e.getChannel().sendMessage("How are we going to mute nobody dum dum").queue();
                                        break;
                                    }
                                } else {
                                    try {
                                        assert user != null;
                                        e.getGuild().addRoleToMember(user.getId(), Objects.requireNonNull(e.getGuild().getRolesByName("Muted", true).get(0))).queue();
                                    } catch (Exception exception) {
                                        e.getChannel().sendMessage("Whoops!  I could not mute them!  Please check that I have `Manage Roles` and that the `Muted` role exists and is below my highest role.").queue();
                                        break;
                                    }
                                }
                                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                                        .setTitle("__**Mute Info**__");
                                e.getChannel().sendMessage(":zipper_mouth: " + targetPing + " was successfully muted!").queue();
                                try {
                                    TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                                    textChannel.sendMessage(builder.build()).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You do not have a channel named \"moderation-log\", the mute was not logged.").queue();
                                }
                                Objects.requireNonNull(e.getJDA().getUserById(target)).openPrivateChannel().complete().sendMessage("You were muted in " + e.getGuild().getName() + " for " + reason + "!").queue();

                            }
                        } else if(data[4].equals("false")) {
                            e.getChannel().sendMessage("Moderation commands are disabled here!").queue();
                        } else {
                            e.getChannel().sendMessage(":x: You are not authorized to use this command!").queue();
                        }
                        ranCommand = true;
                    }
                    case "unmute" -> {
                        if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.MESSAGE_MANAGE) && data[4].equals("true") || isDev && data[4].equals("true")) {
                            User user;
                            try {
                                user = e.getJDA().getUserById(target);
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You tried to unmute a non-existant user.  What a waste of your time.").queue();
                                break;
                            }
                            if (server.equals("670725611207262219")) {
                                try {
                                    assert user != null;
                                    e.getGuild().removeRoleFromMember(user.getId(), Objects.requireNonNull(e.getGuild().getRoleById("734697394389778462"))).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("How are we going to unmute nobody dum dum").queue();
                                    break;
                                }
                            } else {
                                try {
                                    assert user != null;
                                    e.getGuild().removeRoleFromMember(user.getId(), Objects.requireNonNull(e.getGuild().getRolesByName("Muted", true).get(0))).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("Whoops!  I could not unmute them!  Please check that I have `Manage Roles` and that the `Muted` role exists and is below my highest role and that the user is actually muted.").queue();
                                    break;
                                }
                            }
                            e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                            EmbedBuilder builder = new EmbedBuilder()
                                    .setColor(Color.BLUE)
                                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                    .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getName() + "#" + user.getDiscriminator(), false)
                                    .setTitle("__**Unmute Info**__");
                            e.getChannel().sendMessage(":white_check_mark: " + targetPing + " was successfully unmuted!").queue();
                            try {
                                TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                                textChannel.sendMessage(builder.build()).queue();
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You do not have a channel named \"moderation-log\", the mute was not logged.").queue();
                            }
                        } else if(data[4].equals("false")) {
                            e.getChannel().sendMessage("Moderation commands are disabled here!").queue();
                        } else {
                            e.getChannel().sendMessage(":x: You are not authorized to use this command!").queue();
                        }
                        ranCommand = true;
                    } /*
                    case "kick" -> {
                        if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.KICK_MEMBERS) && data[4].equals("true") || isDev && data[4].equals("true")) {
                            StringBuilder reason = new StringBuilder();
                            for (int i = 2; i < message.length; i++) {
                                reason.append(message[i]).append(" ");
                            }
                            if (hasSameUser) {
                                e.getChannel().sendMessage("why would you want to kick yourself, dumb?").queue();
                            } else if (hasBotId) {
                                e.getChannel().sendMessage("You cannot kick me, I am too powerful for you.").queue();
                            } else if (hasDevPing) {
                                e.getChannel().sendMessage("HAHA DEV ABOOZ").queue();
                            } else {
                                User user;
                                try {
                                    user = e.getJDA().getUserById(target);
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You tried to kick a non-existant user.  What a waste of your time.").queue();
                                    break;
                                }
                                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                                assert user != null;
                                user.openPrivateChannel().complete().sendMessage("You were kicked from " + e.getGuild().getName() + " for " + reason + "!").queue();
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getId() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                                        .setTitle("__**Kick Info**__");
                                try {
                                    e.getGuild().kick((Member) user, reason.toString()).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You failed to mention a specific user to kick, what are you, dumb?").queue();
                                    throw new IllegalArgumentException();
                                }
                                try {
                                    TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                                    textChannel.sendMessage(builder.build()).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("The kick was not logged, you must have a channel named \"moderation log\"").queue();
                                }
                                e.getChannel().sendMessage(":boot: " + targetPing + " was successfully kicked!").queue();
                            }
                        } else {
                            e.getChannel().sendMessage(":x: You are not authorized to use this command!").queue();
                        }
                    }
                    case "ban" -> {
                        if (Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).hasPermission(Permission.BAN_MEMBERS) && data[4].equals("true") || isDev && data[4].equals("true")) {
                            StringBuilder reason = new StringBuilder();
                            try {
                                for (int i = 2; i < message.length; i++) {
                                    reason.append(message[i]).append(" ");
                                }
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You must provide a reason!").queue();
                                throw new IllegalArgumentException();
                            }
                            if (hasSameUser) {
                                e.getChannel().sendMessage("why would you want to ban yourself, dumb?").queue();
                            } else if (hasBotId) {
                                e.getChannel().sendMessage("You cannot ban me, I am too powerful for you.").queue();
                            } else if (hasDevPing) {
                                e.getChannel().sendMessage("HAHA DEV ABOOZ").queue();
                            } else {
                                User user;
                                try {
                                    user = e.getJDA().getUserById(target);
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You tried ban kick a non-existant user.  What a waste of your time.").queue();
                                    break;
                                }
                                e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                                Objects.requireNonNull(e.getJDA().getUserById(target)).openPrivateChannel().complete().sendMessage("You were banned from " + e.getGuild().getName() + " for " + reason + "!").queue();
                                assert user != null;
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("Moderator: " + e.getAuthor().getName(), "User: " + user.getId() + "#" + user.getDiscriminator() + "\nReason: " + reason, false)
                                        .setTitle("__**BAN Info**__");
                                try {
                                    e.getGuild().ban((Member) user, 0, reason.toString()).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You failed to mention a specific user to ban, what are you, dumb?").queue();
                                    throw new IllegalArgumentException();
                                }

                                try {
                                    TextChannel textChannel = e.getGuild().getTextChannelsByName("moderation-log", true).get(0);
                                    textChannel.sendMessage(builder.build()).queue();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("The ban was not logged, you must have a channel named \"moderation log\"").queue();
                                }
                                e.getChannel().sendMessage(":hammer: **THE BAN HAMMER HAS SPOKEN** :hammer:\n" + targetPing + " was successfully banned!").queue();
                            }
                        } else {
                            e.getChannel().sendMessage(":x: You are not authorized to use this command!").queue();
                        }
                    }*/
                    case "suggest" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("Reporting a bug or requesting a new command?  Go to <https://github.com/Poseidon-I/ling-ling/wiki/This-Repository-is-for-Submitting-Bugs,-Feature-Requests,-or-Command-Requests> \n\nSuggesting a new autoresponse, command response, or word to blacklist?  Go to <https://forms.gle/LTqhVNdu7CgsrQzf7>").queue();
                        ranCommand = true;
                    }
                    case "support" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("Join the support server at discord.gg/gNfPwa8").queue();
                        ranCommand = true;
                    }

                    case "checkdm" -> {
                        e.getChannel().sendMessage("**OI <@" + target + ">, " + e.getAuthor().getName() + " WANTS YOU TO CHECK YOUR DMS.  DO IT NOW OR ELSE.**").queue();
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        try {
                            User send = e.getJDA().getUserById(target);
                            assert send != null;
                            send.openPrivateChannel().complete().sendMessage("**OI <@" + target + ">, " + e.getAuthor().getName() + " WANTS YOU TO CHECK YOUR DMS.  DO IT NOW OR ELSE.**").queue();
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("Either the recipient was being a n00b and didn't have their DMs open or you are smol brane and forgot to mention a user or you mentioned a bot.").queue();
                        }
                        ranCommand = true;
                    }
                    case "kill" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        if (e.getAuthor().getName().contains("@everyone") || e.getAuthor().getName().contains("@here") || e.getAuthor().getName().contains("<@&")) {
                            e.getChannel().sendMessage("Nice try but no").queue();
                            throw new IllegalArgumentException();
                        }
                        int i = random.nextInt(23);
                        if (i == 0) {
                            e.getChannel().sendMessage(targetPing + " thought it was a good idea to play the sousaphone after eating chili pepper").queue();
                        } else if (i == 1) {
                            e.getChannel().sendMessage(targetPing + "'s ears were blasted apart with a piccolo playing 10000 Hz at fortissimo").queue();
                        } else if (i == 2) {
                            e.getChannel().sendMessage("Ling Ling deemed " + targetPing + " unworthy of violin so he (she?) forced " + targetPing + " to play the viola.  Their ego was permanently damaged.").queue();
                        } else if (i == 3) {
                            e.getChannel().sendMessage("Ling Ling is a benevolent violinist god so nice try but no").queue();
                        } else if (i == 4) {
                            e.getChannel().sendMessage(targetPing + " was blinded because they used light mode").queue();
                        } else if (i == 5) {
                            e.getChannel().sendMessage(e.getAuthor().getName() + " started punching " + targetPing + ".").queue();
                        } else if (i == 6) {
                            e.getChannel().sendMessage(e.getAuthor().getName() + " somehow got " + targetPing + " into their Minecraft world and then " + targetPing + " was dumb enough to light TNT.").queue();
                        } else if (i == 7) {
                            e.getChannel().sendMessage(targetPing + " stuffed too much chili pepper down their throat and the results were rather explosive").queue();
                        } else if (i == 8) {
                            e.getChannel().sendMessage("A bomb detonated over " + targetPing + "'s head").queue();
                        } else if (i == 9) {
                            e.getChannel().sendMessage(targetPing + " destroyed Jacqueline's Stradivarius so Jacqueline destroyed " + targetPing).queue();
                        } else if (i == 10) {
                            e.getChannel().sendMessage(targetPing + " tripped over a tripwire and fell into the Void").queue();
                        } else if (i == 11) {
                            e.getChannel().sendMessage(targetPing + " went to the zoo and got trampled by 20 elephants").queue();
                        } else if (i == 12) {
                            e.getChannel().sendMessage(e.getAuthor().getName() + " played a very high harmonic on their violin and exploded " + targetPing + "'s eardrums.").queue();
                        } else if (i == 13) {
                            e.getChannel().sendMessage(targetPing + " was run over by " + e.getAuthor().getName() + " because " + targetPing + " was crossing the street while playing a sousaphone.").queue();
                        } else if (i == 14) {
                            e.getChannel().sendMessage(targetPing + " fell off a cliff because they were not paying attention to where they were going.  Reason?  Using Simply Piano app (sacrilegious)").queue();
                        } else if (i == 15) {
                            e.getChannel().sendMessage(targetPing + " was whacked on the head with a viola for displeasing the conductor.").queue();
                        } else if (i == 16) {
                            e.getChannel().sendMessage(targetPing + " tried to steal 2000 pounds worth of gold from " + e.getAuthor().getName() + " but they were caught and ended up crushing themselves under the gold.").queue();
                        } else if (i == 17) {
                            e.getChannel().sendMessage(targetPing + " was attacked by " + e.getAuthor().getName() + ".  It was that simple").queue();
                        } else if (i == 18) {
                            e.getChannel().sendMessage("Why would you want to wound " + targetPing + " they haven't done anything wrong").queue();
                        } else if (i == 19) {
                            e.getChannel().sendMessage(targetPing + " tripped over " + e.getAuthor().getName() + "'s foot while reading music and walking").queue();
                        } else if (i == 20) {
                            e.getChannel().sendMessage(e.getAuthor().getName() + " clobbers " + targetPing + " with a clarinet because " + targetPing + " was being dumb").queue();
                        } else if (i == 21) {
                            e.getChannel().sendMessage(e.getAuthor().getName() + " slapped " + targetPing + " for playing the VIOLA").queue();
                        } else {
                            e.getChannel().sendMessage(targetPing + " stepped on a landmine and suffered major injuries.").queue();
                        }
                        ranCommand = true;
                    }
                    case "insult" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        int i = random.nextInt(11);
                        if (i == 0) {
                            e.getChannel().sendMessage(targetPing + "'s violining skills are so bad even the violists were allowed to laugh at them").queue();
                        } else if (i == 1) {
                            e.getChannel().sendMessage("Why would you insult " + targetPing + ", they seem very nice").queue();
                        } else if (i == 2) {
                            e.getChannel().sendMessage(targetPing + " tried conducting an orchestra but their skills were so shitty not even Ling Ling could figure out what was going on").queue();
                        } else if (i == 3) {
                            e.getChannel().sendMessage(targetPing + " auditioned for Seattle Symphony but the audition panel just laughed at their horrible intonation").queue();
                        } else if (i == 4) {
                            e.getChannel().sendMessage(targetPing + " is so bad that they were forced to play the viola").queue();
                        } else if (i == 5) {
                            e.getChannel().sendMessage(targetPing + " played out of tune in front of the Berlin Philharmonic and they were ridiculed").queue();
                        } else if (i == 6) {
                            e.getChannel().sendMessage("During a music test " + targetPing + " was called out for cheating").queue();
                        } else if (i == 7) {
                            e.getChannel().sendMessage(targetPing + " abused a violin and Ling Ling came and punished them for their smol brane").queue();
                        } else if (i == 8) {
                            e.getChannel().sendMessage(targetPing + " ruined the annual orchestra concert and was ridiculed for playing a wrong note.").queue();
                        } else if (i == 9) {
                            e.getChannel().sendMessage(targetPing + " played the violin out of tune.  Ling Ling harassed them for the sin.").queue();
                        } else {
                            e.getChannel().sendMessage(targetPing + " tried playing their piccolo solo but it broke so many eardrums that the conductor threw them off the stage into the audience headfirst.  Everyone laughed.").queue();
                        }
                        ranCommand = true;
                    }
                    case "joke" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        if (e.getAuthor().getName().contains("@everyone") || e.getAuthor().getName().contains("@here") || e.getAuthor().getName().contains("<@&")) {
                            e.getChannel().sendMessage("Nice try but no").queue();
                            throw new IllegalArgumentException();
                        }
                        int i = random.nextInt(23);
                        if (i == 0) {
                            e.getChannel().sendMessage("Q: What do a viola and a lawsuit have in common?\nA: ||Everyone is happy when the case is closed.||").queue();
                        } else if (i == 1) {
                            e.getChannel().sendMessage("Q: What do you get when a viola gets run over by a car?\nA:|| Peace||").queue();
                        } else if (i == 2) {
                            e.getChannel().sendMessage("Q: What's the difference between a violin and a viola?\nA1: ||A viola burns longer.||\nA2: ||You can tune a violin.||").queue();
                        } else if (i == 3) {
                            e.getChannel().sendMessage("Q: What's the definition of a minor second?\nA: ||Two violists playing in unison.||").queue();
                        } else if (i == 4) {
                            e.getChannel().sendMessage("Q: How was the canon invented?\nA: ||Two violists were trying to play the same passage together.||").queue();
                        } else if (i == 5) {
                            e.getChannel().sendMessage("Q: How can you tell when a violist is playing out of tune?\nA: ||The bow is moving.||").queue();
                        } else if (i == 6) {
                            e.getChannel().sendMessage("Q: What's the difference between a washing machine and a violist?\nA: ||Vibrato.||").queue();
                        } else if (i == 7) {
                            e.getChannel().sendMessage("Q: How do you get a violin to sound like a viola?\nA: ||Play in the low register with a lot of wrong notes.||").queue();
                        } else if (i == 8) {
                            e.getChannel().sendMessage("Q: What is the range of a viola?\nA: ||As far as you can kick it.||").queue();
                        } else if (i == 9) {
                            e.getChannel().sendMessage("A violist and a cellist were standing on a sinking ship. \"Help!\" cried the cellist, \"I can't swim!\"\n\"Don't worry,\" said the violist, \"||Just fake it.||\"").queue();
                        } else if (i == 10) {
                            e.getChannel().sendMessage("Q: What's the difference between the first and last desk of the viola section?\nA: ||About half a bar.||").queue();
                        } else if (i == 11) {
                            e.getChannel().sendMessage("Q: How do you get two violists to play in tune with each other?\nA: ||Ask one of them to leave.||").queue();
                        } else if (i == 12) {
                            e.getChannel().sendMessage("A group of terrorists hijacked a plane full of violists. They called down to ground control with their list of demands and added that if their demands weren't met, they would ||release one violist every hour.||").queue();
                        } else if (i == 13) {
                            e.getChannel().sendMessage("Q: What's the difference between a viola and a trampoline?\nA: ||You take your shoes off to jump on a trampoline.||").queue();
                        } else if (i == 14) {
                            e.getChannel().sendMessage("Q: What's the difference between a viola and an onion?\nA: ||No one cries when you cut up a viola.||").queue();
                        } else if (i == 15) {
                            e.getChannel().sendMessage("Q: Anything\nA: Viola.\n||*intense wheezing*||").queue();
                        } else if (i == 16) {
                            e.getChannel().sendMessage("Q: What's the difference between a pizza and a violist? \nA: ||A pizza can feed a family of four.||").queue();
                        } else if (i == 17) {
                            e.getChannel().sendMessage("Q: Why don't violists play hide and seek? \nA: ||Because no one would look for them.||").queue();
                        } else if (i == 18) {
                            e.getChannel().sendMessage("Q : Why bAss and not viola?\nA: ||Because viola isn't badAss.||").queue();
                        } else if (i == 19) {
                            e.getChannel().sendMessage("Q: Did you hear about the violist who played in tune?\nA: ||Neither did I.||").queue();
                        } else if (i == 20) {
                            e.getChannel().sendMessage("Q: How do you get a violin to sound like a viola?\nA1: ||Sit in the back and don't play.||\nA2: ||Play in the front with a lot of wrong notes.||").queue();
                        } else if (i == 21) {
                            e.getChannel().sendMessage("Q: Why is viola illegal?\nA: ||It viola-ted the Terms of Service of the musician world.||").queue();
                        } else {
                            e.getChannel().sendMessage("Q: How make someone practice the viola?\nA: ||Tell them a violinist is getting better than them.||").queue();
                        }
                        ranCommand = true;
                    }
                    case "poll" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        int i = 7;
                        StringBuilder send = new StringBuilder("**POLL:  ");
                        try {
                            while (fullMessage.charAt(i) != '"' && fullMessage.charAt(i) != '“' && fullMessage.charAt(i) != '”') {
                                send.append(fullMessage.charAt(i));
                                i++;
                            }
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("You need to end your title with a `\"`, or you did not properly start your title with `\"`").queue();
                            break;
                        }
                        send.append("**\n`A:` ");
                        i += 3;
                        int options = 1;
                        char character = 'A';
                        try {
                            while (fullMessage.charAt(i) != '"' && fullMessage.charAt(i) != '“' && fullMessage.charAt(i) != '”') {
                                if (fullMessage.charAt(i) == ';') {
                                    i += 2;
                                    options++;
                                    character++;
                                    send.append("\n`").append(character).append(":` ");
                                } else {
                                    send.append(fullMessage.charAt(i));
                                    i++;
                                }
                            }
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("You need to end your options portion with a `\"`, or you did not properly start your options portion with `\"`").queue();
                            break;
                        }
                        String react = "";
                        if (options > 20) {
                            e.getChannel().sendMessage("Please limit your polls to 20 options or less.").queue();
                        } else {
                            react = e.getChannel().sendMessage(send.toString()).complete().getId();
                        }
                        int hex = 127462;
                        for (int j = 0; j < options; j++) {
                            String unicode = "U+" + Integer.toHexString(hex);
                            e.getChannel().addReactionById(react, unicode).queue();
                            hex++;
                        }
                        ranCommand = true;
                    }
                    case "emojify" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        StringBuilder convert = new StringBuilder("> ");
                        if (message.length == 1) {
                            e.getChannel().sendMessage("how are we going to emojify nothing dum dum").queue();
                        }
                        for (int i = 1; i < message.length; i++) {
                            if (i + 1 == message.length) {
                                convert.append(message[i]);
                            } else {
                                convert.append(message[i]).append(" ");
                            }
                        }
                        convert = new StringBuilder(convert.toString().toLowerCase());
                        StringBuilder send = new StringBuilder();
                        for (int i = 2; i < convert.length(); i++) {
                            char cur = convert.charAt(i);
                            if (cur == ' ') {
                                send.append("<:linglingclock:747499551451250730> ");
                            } else if (cur == '1') {
                                send.append(":one: ");
                            } else if (cur == '2') {
                                send.append(":two: ");
                            } else if (cur == '3') {
                                send.append(":three: ");
                            } else if (cur == '4') {
                                send.append(":four: ");
                            } else if (cur == '5') {
                                send.append(":five: ");
                            } else if (cur == '6') {
                                send.append(":six: ");
                            } else if (cur == '7') {
                                send.append(":seven: ");
                            } else if (cur == '8') {
                                send.append(":eight: ");
                            } else if (cur == '9') {
                                send.append(":nine: ");
                            } else if (cur == '0') {
                                send.append(":zero: ");
                            } else if (cur == '?') {
                                send.append(":grey_question: ");
                            } else if (cur == '!') {
                                send.append(":grey_exclamation: ");
                            } else {
                                send.append(":regional_indicator_").append(cur).append(": ");
                            }
                        }
                        send.append("\n<@").append(e.getAuthor().getId()).append(">");
                        try {
                            e.getChannel().sendMessage(send.toString()).queue();
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("Your message ended up being over 2000 characters, try shortening it.").queue();
                        }
                        ranCommand = true;
                    }
                    case "invite" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        EmbedBuilder builder = new EmbedBuilder()
                                .setColor(Color.BLUE)
                                .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                .addField("__How to invite the bot to your server.__", "You can add the bot to your server using the below link:\nhttps://discord.com/api/oauth2/authorize?client_id=733409243222507670&permissions=335899718&scope=bot", false);
                        e.getChannel().sendMessage(builder.build()).queue();
                        ranCommand = true;
                    }
                    case "vote" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("You can vote for the bot here: <https://top.gg/bot/733409243222507670/vote>.  Voting does not give a reward (nor does not voting negatively impact you) but helps the bot grow!\n\nWant to vote for the support server?  Vote at <https://top.gg/servers/670725611207262219/vote> for a 10% boost to `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` in the support server!").queue();
                        ranCommand = true;
                    }
                    /*case "website" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("Visit my webpage!  <https://lingling40hrs.weebly.com/>").queue();
                    }
                    case "costs" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("Need a table with all upgrade costs?  Here's one!  <https://lingling40hrs-guide.neocities.org/upgrades.html>").queue();
                    }
                    case "guide" -> {
                        e.getChannel().deleteMessageById(e.getChannel().getLatestMessageId()).queue();
                        e.getChannel().sendMessage("The guide is being worked on currently, this command will be updated as soon as it releases!").queue();
                    }*/
                }

                //ECONOMY
                if(!ranCommand) {
                    time = System.currentTimeMillis();
                    boolean hasData;
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt"));
                        hasData = true;
                        data = reader.readLine().split(" ");
                        reader.close();
                    } catch (IOException ioException) {
                        hasData = false;
                        data = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "false", "false", "0", "0", "0", "0", "0", "0", "0", "false", "false", "false", "0", "0", "0", "0", "false", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "false", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "false"};
                    }
                    long violins = Long.parseLong(data[0]);
                    long workC = Long.parseLong(data[1]);
                    int workL = Integer.parseInt(data[2]);
                    long gambleC = Long.parseLong(data[3]);
                    int gambleL = Integer.parseInt(data[4]);
                    long robC = Long.parseLong(data[5]);
                    int robL = Integer.parseInt(data[6]);
                    long rehearseC = Long.parseLong(data[7]);
                    long performC = Long.parseLong(data[8]);
                    boolean ownInsurance1 = Boolean.parseBoolean(data[9]);
                    boolean ownInsurance2 = Boolean.parseBoolean(data[10]);
                    int activeInsurance = Integer.parseInt(data[11]);
                    long hourlyIncome = Long.parseLong(data[12]);
                    int violinQuality = Integer.parseInt(data[13]);
                    int skillLevel = Integer.parseInt(data[14]);
                    int lessonQuality = Integer.parseInt(data[15]);
                    int stringQuality = Integer.parseInt(data[16]);
                    int bowQuality = Integer.parseInt(data[17]);
                    boolean hasMath = Boolean.parseBoolean(data[18]);
                    boolean hasOrchestra = Boolean.parseBoolean(data[19]);
                    boolean piccolo = Boolean.parseBoolean(data[20]);
                    int flute = Integer.parseInt(data[21]);
                    int oboe = Integer.parseInt(data[22]);
                    int clarinet = Integer.parseInt(data[23]);
                    int bassoon = Integer.parseInt(data[24]);
                    boolean contrabassoon = Boolean.parseBoolean(data[25]);
                    int horn = Integer.parseInt(data[26]);
                    int trumpet = Integer.parseInt(data[27]);
                    int trombone = Integer.parseInt(data[28]);
                    int tuba = Integer.parseInt(data[29]);
                    int timpani = Integer.parseInt(data[30]);
                    int percussion = Integer.parseInt(data[31]);
                    int first = Integer.parseInt(data[32]);
                    int second = Integer.parseInt(data[33]);
                    int cello = Integer.parseInt(data[34]);
                    int stringBass = Integer.parseInt(data[35]);
                    int piano = Integer.parseInt(data[36]);
                    boolean harp = Boolean.parseBoolean(data[37]);
                    int soprano = Integer.parseInt(data[38]);
                    int alto = Integer.parseInt(data[39]);
                    int tenor = Integer.parseInt(data[40]);
                    int bass = Integer.parseInt(data[41]);
                    int soloists = Integer.parseInt(data[42]);
                    int hallLevel = Integer.parseInt(data[43]);
                    int conductor = Integer.parseInt(data[44]);
                    int advertising = Integer.parseInt(data[45]);
                    int tickets = Integer.parseInt(data[46]);
                    int streak = Integer.parseInt(data[47]);
                    long dailyC = Long.parseLong(data[48]);
                    long dailyExp = Long.parseLong(data[49]);
                    boolean faster = Boolean.parseBoolean(data[50]);
                    int rice = Integer.parseInt(data[51]);
                    int thirdP = Integer.parseInt(data[52]);
                    int secondP = Integer.parseInt(data[53]);
                    int firstP = Integer.parseInt(data[54]);
                    int medals = Integer.parseInt(data[55]);
                    boolean extraIncome = Boolean.parseBoolean(data[56]);
                    boolean extraCommandIncome = Boolean.parseBoolean(data[57]);
                    boolean higherWinrate = Boolean.parseBoolean(data[58]);
                    boolean higherRobrate = Boolean.parseBoolean(data[59]);
                    boolean stealShield = Boolean.parseBoolean(data[60]);
                    boolean violinDuplicator = Boolean.parseBoolean(data[61]);
                    if (message[0].equals("start") && !hasData) {
                        File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt");
                        try {
                            file.createNewFile();
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("You already have a save, don't try to outsmart me").queue();
                        }
                        PrintWriter newData = null;
                        try {
                            newData = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt")));
                        } catch (Exception exception) {
                            e.getChannel().sendMessage("Something went wrong creating the file.").queue();
                        }
                        assert newData != null;
                        newData.print("0 " + time + " 0 " + time + " 0 " + time + " 0 " + time + " " + time + " false false 0 0 0 0 0 0 0 false false false 0 0 0 0 false 0 0 0 0 0 0 1 1 0 0 0 false 0 0 0 0 0 0 0 0 0 0 " + time + " " + (time + 86400000) + " false");
                        newData.close();
                        e.getChannel().sendMessage("Your profile has been created!  Run `" + prefix + "help 4` for a list of economy commands!").queue();
                        throw new IllegalArgumentException();
                    } else if (message[0].equals("start") && hasData) {
                        e.getChannel().sendMessage("You already have a save, don't try to outsmart me").queue();
                    }
                    boolean serverVote = false;
                    try {
                        if (e.getGuild().getId().equals("670725611207262219")) {
                            java.util.List<Role> roles = Objects.requireNonNull(e.getGuild().getMemberById(e.getAuthor().getId())).getRoles();
                            for (Role role : roles) {
                                if (role.getId().equals("755910677075460206")) {
                                    serverVote = true;
                                    break;
                                }
                            }
                        }
                    } catch (Exception exception) {
                        //nothing here lol
                    }
                    long workCost = (long) (Math.pow(1.2, workL)) * 75;
                    long gambleCost = (long) (Math.pow(1.35, gambleL)) * 800;
                    long robCost = (long) (Math.pow(1.5, robL)) * 5000;
                    long violinCost = (long) (Math.pow(10, violinQuality)) * 1000;
                    long skillCost = (long) (Math.pow(3, skillLevel)) * 500;
                    long lessonCost = (long) (Math.pow(4, lessonQuality)) * 700;
                    long stringCost = (long) (Math.pow(2, stringQuality)) * 400;
                    long bowCost = (long) (Math.pow(5, bowQuality)) * 500;
                    long hallCost = (long) (Math.pow(10, hallLevel)) * 10000;
                    long conductorCost = (long) (Math.pow(5, conductor)) * 100000;
                    long ticketCost = (long) (Math.pow(2, tickets)) * 2000000;
                    long fluteCost = 400000 * (flute + 1);
                    long oboeCost = 400000 * (oboe + 1);
                    long clarinetCost = 400000 * (clarinet + 1);
                    long bassoonCost = 400000 * (bassoon + 1);
                    long hornCost = 400000 * (horn + 1);
                    long trumpetCost = 300000 * (trumpet + 1);
                    long tromboneCost = 250000 * (trombone + 1);
                    long tubaCost = 250000 * (tuba + 1);
                    long timpaniCost = 400000 * (timpani + 1);
                    long percussionCost = 100000 * (percussion + 1);
                    long firstCost = 600000 * first;
                    long secondCost = 500000 * second;
                    long celloCost = 500000 * (cello + 1);
                    long doubleCost = 500000 * (stringBass + 1);
                    long pianoCost = 1000000 * (piano + 1);
                    long sopranoCost = 100000 * (soprano + 1);
                    long altoCost = 75000 * (alto + 1);
                    long tenorCost = 75000 * (tenor + 1);
                    long bassCost = 75000 * (tenor + 1);
                    long soloistCost = 400000 * (soloists + 1);
                    long advertisingCost = 100000 * (advertising + 1);
                    switch (message[0]) {
                        case "upgrades", "up", "u", "shop" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            try {
                                if(!hasOrchestra) {
                                    switch (message[1]) {
                                        case "1" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Violin Quality (" + violinQuality + "/5)", "Price: " + violinCost + "\nEffect: +600:violin:/hour\nID: `violin`, `v`", false)
                                                    .addField("Skill Level (" + skillLevel + "/10)", "Price: " + skillCost + "\nEffect: +240:violin:/hour\nID: `skill`, `s`", false)
                                                    .addField("Lesson Quality (" + lessonQuality + "/8)", "Price: " + lessonCost + "\nEffect: +150:violin:/hour\nID: `lesson`, `l`", false)
                                                    .addField("String Quality (" + stringQuality + "/15)", "Price: " + stringCost + "\nEffect: +100:violin:/hour\nID: `string`, `str`", false)
                                                    .addField("Bow Quality (" + bowQuality + "/7)", "Price: " + bowCost + "\nEffect: +200:violin:/hour\nID: `bow`, `b`", false)
                                                    .setTitle("__**Income Upgrades**__");
                                            if (hasMath) {
                                                builder.addField("Math Tutoring (1/1)", "Price: 5 000 000\nEffect: +2500:violin:/hour\nID: `math`", false);
                                            } else {
                                                builder.addField("Math Tutoring (0/1)", "Price: 5 000 000\nEffect: +2500:violin:/hour\nID: `math`", false);
                                            }
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "2" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("Uesr balance: " + violins + "\nUser `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Concert Hall Quality (" + hallLevel + "/2)", "Price: " + hallCost + "\nEffect: +300:violin:/hour, +20% violins from `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID: `concert`, `hall`", false)
                                                    .addField("Orchestra", "Price: 40 000 000\nEffect: +3000:violin:/hour, x2 violins from `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID:`orchestra`, `o`", false)
                                                    .setTitle("__**Miscellaneous Orchestra Items**__");
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "3" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Efficient Practising (" + workL + "/75)", "Price: " + workCost + "\nEffect: Increases your income from `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` by 4%.\nID: `efficient`, `practising`, `ep`", false)
                                                    .addField("Lucky Musician (" + gambleL + "/40)", "Price: " + gambleCost + "\nEffect: Increases your chance of winning at `" + prefix + "bet` by 0.75%.\nID: `lucky`, `lm`", false)
                                                    .addField("Sophisticated Robbing (" + robL + "/25)", "Price: " + robCost + "\nEffect: Increases your chance of a successful `" + prefix + "rob` by 0.5%.\nID: `sophisticated`, `robbing`, `sr`", false)
                                                    .setTitle("__**Other Miscellaneous Upgrades**__");
                                            if (ownInsurance1) {
                                                builder.addField("Ling Ling Insurance - Plan 1 - Full Security (1/1)", "Price: 2 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect all your violins from being stolen but you will pay 25% of what would have been stolen as insurance cost.\nID: `1`", false);
                                            } else {
                                                builder.addField("Ling Ling Insurance - Plan 1 - Full Security (0/1)", "Price: 2 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect all your violins from being stolen but you will pay 25% of what would have been stolen as insurance cost.\nID: `1`", false);
                                            }
                                            if (ownInsurance2) {
                                                builder.addField("Ling Ling Insurance - Plan 2 - Half Security (1/1)", "Price: 2 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect only 80% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
                                            } else {
                                                builder.addField("Ling Ling Insurance - Plan 2 - Half Security (0/1)", "Price: 2 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect only 80% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
                                            }
                                            if(faster) {
                                                builder.addField("Time Crunch (1/1)", "Price: 200 000 000\nEffect: Decreases `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `time`, `crunch`, `speed`, `crunch`", false);
                                            } else {
                                                builder.addField("Time Crunch (0/1)", "Price: 200 000 000\nEffect: Decreases `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `time`, `crunch`, `speed`, `crunch`", false);
                                            }
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "4" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("Medals: " + medals + "\nUse `" + prefix + "buy [ID]` tp buy something!", e.getJDA().getSelfUser().getAvatarUrl());
                                            if(extraIncome) {
                                                builder.addField("Extra Income :white_check_mark:", "Price: 5:military_medal:\nEffect: +8000:violin:/hour\nID: `income`", false);
                                            } else {
                                                builder.addField("Extra Income", "Price: 5:military_medal:\nEffect: +8000:violin:/hour\nID: `income`", false);
                                            }
                                            if(extraCommandIncome) {
                                                builder.addField("Extra Command Income :white_check_mark:", "Price: 5:military_medal:\nEffect: x2 income from commands\nID: `commandIncome`", false);
                                            } else {
                                                builder.addField("Extra Command Income", "Price: 5:military_medal:\nEffect: x2 income from commands\nID: `commandIncome`", false);
                                            }
                                            if(higherWinrate) {
                                                builder.addField("Higher Gamble Winrate :white_check_mark:", "Price: 5:military_medal:\nEffect: +5% chance to win at `" + prefix + "gamble`\nID: `gambleWinrate`", false);
                                            } else {
                                                builder.addField("Higher Gamble Winrate", "Price: 5:military_medal:\nEffect: +5% chance to win at `" + prefix + "gamble`\nID: `gambleWinrate`", false);
                                            }
                                            if(higherRobrate) {
                                                builder.addField("Higher Rob Winrate :white_check_mark:", "Price: 5:military_medal:\nEffect: +2.5% chance at successfully robbing someone.  Does NOT bypass Ling Ling Insurance\nID: `robWinrate`", false);
                                            } else {
                                                builder.addField("Higher Rob Winrate", "Price: 5:military_medal:\nEffect: +2.5% chance at successfully robbing someone.  Does NOT bypass Ling Ling Insurance\nID: `robWinrate`", false);
                                            }
                                            if(stealShield) {
                                                builder.addField("Steal Shield :white_check_mark:", "Price: 10:military_medal:\nEffect: Advanced technology takes back 50% of violins when you get robbed.  Does not stack with Violin Duplicator.\nID: `shield`", false);
                                            } else {
                                                builder.addField("Steal Shield", "Price: 10:military_medal:\nEffect: Advanced technology takes back 50% of violins when you get robbed.  Does not stack with Violin Duplicator.\nID: `shield`", false);
                                            }
                                            if(violinDuplicator) {
                                                builder.addField("Violin Duplicator :white_check_mark:", "Price: 10:military_medal:\nEffect: The Vengeful God of Ben Lee duplicates all violins stolen.\nID: `duplicator`", false);
                                            } else {
                                                builder.addField("Violin Duplicator", "Price: 10:military_medal:\nEffect: The Vengeful God of Ben Lee duplicates all violins stolen.\nID: `duplicator`", false);
                                            }
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        default -> e.getChannel().sendMessage("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Miscellaneous Orchestra Items\n`3` for Other Miscellaneous Upgrades\n`4` for Medal Upgrades").queue();
                                    }
                                } else {
                                    switch (message[1]) {
                                        case "1" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Violin Quality (" + violinQuality + "/5)", "Price: " + violinCost + "\nEffect: +600:violin:/hour\nID: `violin`, `v`", false)
                                                    .addField("Skill Level (" + skillLevel + "/10)", "Price: " + skillCost + "\nEffect: +240:violin:/hour\nID: `skill`, `s`", false)
                                                    .addField("Lesson Quality (" + lessonQuality + "/8)", "Price: " + lessonCost + "\nEffect: +150:violin:/hour\nID: `lesson`, `l`", false)
                                                    .addField("String Quality (" + stringQuality + "/15)", "Price: " + stringCost + "\nEffect: +100:violin:/hour\nID: `string`, `str`", false)
                                                    .addField("Bow Quality (" + bowQuality + "/7)", "Price: " + bowCost + "\nEffect: +200:violin:/hour\nID: `bow`, `b`", false)
                                                    .setTitle("__**Income Upgrades**__");
                                            if (hasMath) {
                                                builder.addField("Math Tutoring (1/1)", "Price: 5 000 000\nEffect: +2500:violin:/hour\nID: `math`", false);
                                            } else {
                                                builder.addField("Math Tutoring (0/1)", "Price: 5 000 000\nEffect: +2500:violin:/hour\nID: `math`", false);
                                            }
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "2" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl());
                                            if(piccolo) {
                                                builder.addField("Piccolo (1/1)", "Price: 300 000\nEffect: +30:violin:/hour\nID: `piccolo`", false);
                                            } else {
                                                builder.addField("Piccolo (0/1)", "Price: 300 000\nEffect: +30:violin:/hour\nID: `piccolo`", false);
                                            }
                                            builder.addField("Flutes (" + flute + "/4)", "Price: " + fluteCost + "\nEffect: +60:violin:/hour\nID: `flute`", false)
                                                    .addField("Oboes (" + oboe + "/4)", "Price: " + oboeCost + "\nEffect: +50:violin:/hour\nID: `oboe`", false)
                                                    .addField("Clarinets (" + clarinet + "/4)", "Price: " + clarinetCost + "\nEffect: +40:violin:/hour\nID: `clarinet`", false)
                                                    .addField("Bassoons (" + bassoon + "/4)", "Price: " + bassoonCost + "\nEffect: +40:violin:/hour\nID: `bassoon`", false)
                                                    .setTitle("__**Woodwinds**__");
                                            if (contrabassoon) {
                                                builder.addField("Contrabassoon (1/1)", "Price: 400 000\nEffect: +30:violin:/hour\nID: `contrabassoon`, `cb`", false);
                                            } else {
                                                builder.addField("Contrabassoon (0/1)", "Price: 400 000\nEffect: +30:violin:/hour\nID: `contrabassoon`, `cb`", false);
                                            }
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "3" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("French Horns (" + horn + "/8)", "Price: " + hornCost + "\nEffect: +40:violin:/hour\nID: `horn`", false)
                                                    .addField("Trumpet (" + trumpet + "/4)", "Price: " + oboeCost + "\nEffect: +30:violin:/hour\nID: `trumpet`", false)
                                                    .addField("Trombone (" + trombone + "/6)", "Price: " + tromboneCost + "\nEffect: +20:violin:/hour\nID: `trombone`", false)
                                                    .addField("Tuba (" + tuba + "/2)", "Price: " + tubaCost + "\nEffect: +20:violin:/hour\nID: `tuba`", false)
                                                    .addField("Timpani (" + timpani + "/2)", "Price: " + timpaniCost + "\nEffect: +60:violin:/hour\nID: `timpani`", false)
                                                    .addField("Percussionists (" + percussion + "/2)", "Price: " + percussionCost + "\nEffect: +10:violin:/hour\nID: `percussion`", false)
                                                    .setTitle("__**Brass and Percussion**__");
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "4" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Violin I (" + first + "/20)", "Price: " + firstCost + "\nEffect: +70:violin:/hour\nID: `first`", false)
                                                    .addField("Violin II (" + second + "/20)", "Price: " + secondCost + "\nEffect: +60:violin:/hour\nID: `second`", false)
                                                    .addField("Cellos (" + cello + "/15)", "Price: " + celloCost + "\nEffect: +50:violin:/hour\nID: `cello`", false)
                                                    .addField("Double Basses (" + stringBass + "/5)", "Price: " + doubleCost + "\nEffect: +50:violin:/hour\nID: `double`, `upright`, `doublebass`, `db`", false)
                                                    .addField("Pianists (" + piano + "/2)", "Price: " + timpaniCost + "\nEffect: +110:violin:/hour\nID: `piano`", false)
                                                    .setTitle("__**Strings**__");
                                            if(harp) {
                                                builder.addField("Harp (1/1)", "Price: 500 000\nEffect: +80:violin:/hour\nID: `harp`", false);
                                            } else {
                                                builder.addField("Harp (0/1)", "Price: 500 000\nEffect: +80:violin:/hour\nID: `harp`", false);
                                            }
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "5" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Sopranos (" + soprano + "/20)", "Price: " + sopranoCost + "\nEffect: +30:violin:/hour\nID: `soprano`", false)
                                                    .addField("Altos (" + alto + "/20)", "Price: " + altoCost + "\nEffect: +20:violin:/hour\nID: `alto`", false)
                                                    .addField("Tenors (" + tenor + "/20)", "Price: " + tenorCost + "\nEffect: +20:violin:/hour\nID: `tenor`", false)
                                                    .addField("Basses (" + bass + "/20)", "Price: " + bassCost + "\nEffect: +20:violin:/hour\nID: `bass`", false)
                                                    .addField("Vocal Soloists (" + soloists + "/4)", "Price: " + soloistCost + "\nEffect: +60:violin:/hour\nID: `soloist`", false)
                                                    .setTitle("__**Choir**__");
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "6" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Concert Hall Quality (" + hallLevel + "/5)", "Price: " + hallCost + "\nEffect: +300:violin:/hour, +20% violins from `" + prefix + "practice` `" + prefix + "rehearse` and `" + prefix + "perform`\nID: `concert`, `hall`", false)
                                                    .addField("Conductor Musicality (" + conductor + "/5)", "Price: " + conductorCost + "\nEffect: +200:violin:/hour\nID: `conductor`, `musicality`", false)
                                                    .addField("Advertisement (" + advertising + "/20)", "Price: " + advertisingCost + "\nEffect: +100:violin:/hour\nID: `advertisement`, `ad`", false)
                                                    .addField("Ticket Price (" + tickets + "/5)", "Price: " + ticketCost + "\nEffect: +1000:violin:/hour\nID: `tickets`", false)
                                                    .setTitle("__**Miscellaneous Orchestra Items**__");
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "7" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("User balance: " + violins + "\nUse `" + prefix + "buy [ID]` to buy an upgrade!", e.getJDA().getSelfUser().getAvatarUrl())
                                                    .addField("Efficient Practising (" + workL + "/75)", "Price: " + workCost + "\nEffect: Increases your income from `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` by 4%.\nID: `efficient`, `practising`, `ep`", false)
                                                    .addField("Lucky Musician (" + gambleL + "/40)", "Price: " + gambleCost + "\nEffect: Increases your chance of winning at `" + prefix + "bet` by 0.75%.\nID: `lucky`, `lm`", false)
                                                    .addField("Sophisticated Robbing (" + robL + "/25)", "Price: " + robCost + "\nEffect: Increases your chance of a successful `" + prefix + "rob` by 0.5%.\nID: `sophisticated`, `robbing`, `sr`", false)
                                                    .setTitle("__**Other Miscellaneous Upgrades**__");
                                            if (ownInsurance1) {
                                                builder.addField("Ling Ling Insurance - Plan 1 - Full Security (1/1)", "Price: 2 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect all your violins from being stolen but you will pay 25% of what would have been stolen as insurance cost.\nID: `1`", false);
                                            } else {
                                                builder.addField("Ling Ling Insurance - Plan 1 - Full Security (0/1)", "Price: 2 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect all your violins from being stolen but you will pay 25% of what would have been stolen as insurance cost.\nID: `1`", false);
                                            }
                                            if (ownInsurance2) {
                                                builder.addField("Ling Ling Insurance - Plan 2 - Half Security (1/1)", "Price: 2 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect only 80% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
                                            } else {
                                                builder.addField("Ling Ling Insurance - Plan 2 - Half Security (0/1)", "Price: 2 000 000\nEffect: When someone uses `" + prefix + "rob` on you, will protect only 80% of your violins from being stolen but you don't pay any insurance costs.\nID: `2`", false);
                                            }
                                            if(faster) {
                                                builder.addField("Time Crunch (1/1)", "Price: 200 000 000\nEffect: Decreases `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `time`, `crunch`, `speed`, `crunch`", false);
                                            } else {
                                                builder.addField("Time Crunch (0/1)", "Price: 200 000 000\nEffect: Decreases `" + prefix + "practice`, `" + prefix + "rehearse`, and `" + prefix + "perform` cooldowns.\nID: `time`, `crunch`, `speed`, `faster`", false);
                                            }
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        case "8" -> {
                                            EmbedBuilder builder = new EmbedBuilder()
                                                    .setColor(Color.BLUE)
                                                    .setFooter("Medals: " + medals + "\nUse `" + prefix + "buy [ID]` tp buy something!", e.getJDA().getSelfUser().getAvatarUrl());
                                            if(extraIncome) {
                                                builder.addField("Extra Income :white_check_mark:", "Price: 5:military_medal:\nEffect: +8000:violin:/hour\nID: `income`", false);
                                            } else {
                                                builder.addField("Extra Income", "Price: 5:military_medal:\nEffect: +8000:violin:/hour\nID: `income`", false);
                                            }
                                            if(extraCommandIncome) {
                                                builder.addField("Extra Command Income :white_check_mark:", "Price: 5:military_medal:\nEffect: x2 income from commands\nID: `commandIncome`", false);
                                            } else {
                                                builder.addField("Extra Command Income", "Price: 5:military_medal:\nEffect: x2 income from commands\nID: `commandIncome`", false);
                                            }
                                            if(higherWinrate) {
                                                builder.addField("Higher Gamble Winrate :white_check_mark:", "Price: 5:military_medal:\nEffect: +5% chance to win at `" + prefix + "gamble`\nID: `gambleWinrate`", false);
                                            } else {
                                                builder.addField("Higher Gamble Winrate", "Price: 5:military_medal:\nEffect: +5% chance to win at `" + prefix + "gamble`\nID: `gambleWinrate`", false);
                                            }
                                            if(higherRobrate) {
                                                builder.addField("Higher Rob Winrate :white_check_mark:", "Price: 5:military_medal:\nEffect: +2.5% chance at successfully robbing someone.  Does NOT bypass Ling Ling Insurance\nID: `robWinrate`", false);
                                            } else {
                                                builder.addField("Higher Rob Winrate", "Price: 5:military_medal:\nEffect: +2.5% chance at successfully robbing someone.  Does NOT bypass Ling Ling Insurance\nID: `robWinrate`", false);
                                            }
                                            if(stealShield) {
                                                builder.addField("Steal Shield :white_check_mark:", "Price: 10:military_medal:\nEffect: Advanced technology takes back 50% of violins when you get robbed.  Does not stack with Violin Duplicator.\nID: `shield`", false);
                                            } else {
                                                builder.addField("Steal Shield", "Price: 10:military_medal:\nEffect: Advanced technology takes back 50% of violins when you get robbed.  Does not stack with Violin Duplicator.\nID: `shield`", false);
                                            }
                                            if(violinDuplicator) {
                                                builder.addField("Violin Duplicator :white_check_mark:", "Price: 10:military_medal:\nEffect: The Vengeful God of Ben Lee duplicates all violins stolen.\nID: `duplicator`", false);
                                            } else {
                                                builder.addField("Violin Duplicator", "Price: 10:military_medal:\nEffect: The Vengeful God of Ben Lee duplicates all violins stolen.\nID: `duplicator`", false);
                                            }
                                            e.getChannel().sendMessage(builder.build()).queue();
                                        }
                                        default -> e.getChannel().sendMessage("You did not provide a valid page number!  Current Pages\n`1` for Income Upgrades\n`2` for Woodwinds\n`3` for Brass and Percussion\n`4` for Strings\n`5` for Choir\n`6` for Miscellaneous Orchestra Items\n`7` for Other Miscellaneous Upgrades\n`8` for Medal Upgrades").queue();
                                    }
                                }
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You must provide a page number!").queue();
                            }
                            ranCommand = true;
                        }
                        case "buy" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            switch (message[1]) {
                                case "1" -> {
                                    if (ownInsurance1) {
                                        e.getChannel().sendMessage("You already bought this insurance!  Run `" + prefix + "use 1` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 2.").queue();
                                    } else if (violins < 2000000) {
                                        e.getChannel().sendMessage("You are too poor to buy this insurance!").queue();
                                    } else {
                                        ownInsurance1 = true;
                                        violins -= 2000000;
                                        e.getChannel().sendMessage("You have successfully bought Ling Ling Insurance - Plan 1 - Full Security!  Run `" + prefix + "use 1` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 2.\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "2" -> {
                                    if (ownInsurance2) {
                                        e.getChannel().sendMessage("You already bought this insurance!  Run `" + prefix + "use 2` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 1.").queue();
                                    } else if (violins < 2000000) {
                                        e.getChannel().sendMessage("You are too poor to buy this insurance!").queue();
                                    } else {
                                        ownInsurance2 = true;
                                        violins -= 2000000;
                                        e.getChannel().sendMessage("You have successfully bought Ling Ling Insurance - Plan 2 - Half Security!  Run `" + prefix + "use 2` to apply it.  Remember that you can only have one insurance plan active at once but you can swap at any time for no cost if you have bought Plan 1.\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "time", "crunch", "speed", "faster" -> {
                                    if (faster) {
                                        e.getChannel().sendMessage("You have already sped up time!").queue();
                                    } else if (violins < 200000000) {
                                        e.getChannel().sendMessage("You are too poor to speed up time!").queue();
                                    } else {
                                        faster = true;
                                        violins -= 200000000;
                                        e.getChannel().sendMessage("You approach Ling Ling and pay him (her?) 200 million violins.  Ling Ling then does magic and you are back in your room.  You feel like you can do things faster, around 25% faster.\n`" + prefix + "practice` cooldown is now 30 minutes.\n`" + prefix + "rehearse` cooldown is now 18 hours.\n`" + prefix + "perform` cooldown is now 2.5 days.\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "efficient", "practising", "ep" -> {
                                    if (workL == 75) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < workCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= workCost;
                                        workL++;
                                        e.getChannel().sendMessage("Successfully upgraded Efficient Practising to Level " + workL + "\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "lucky", "lm" -> {
                                    if (gambleL == 40) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < gambleCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= gambleCost;
                                        gambleL++;
                                        e.getChannel().sendMessage("Successfully upgraded Lucky Musician to Level " + gambleL + "\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "sophisticated", "robbing", "sr" -> {
                                    if (robL == 25) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < robCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= robCost;
                                        robL++;
                                        e.getChannel().sendMessage("Successfully upgraded Sophisticated Robbing to Level " + robL + "\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "violin", "v" -> {
                                    if (violinQuality == 5) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < violinCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= violinCost;
                                        violinQuality++;
                                        hourlyIncome += 600;
                                        e.getChannel().sendMessage("Successfully upgraded Violin Quality to Level " + violinQuality + "\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "skill", "s" -> {
                                    if (skillLevel == 10) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < skillCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= skillCost;
                                        skillLevel++;
                                        hourlyIncome += 240;
                                        e.getChannel().sendMessage("Successfully upgraded Skill Level to Level " + skillLevel + "\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "lesson", "l" -> {
                                    if (lessonQuality == 8) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < lessonCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= lessonCost;
                                        lessonQuality++;
                                        hourlyIncome += 150;
                                        e.getChannel().sendMessage("Successfully upgraded Lesson Quality to Level " + lessonQuality + "\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "string", "str" -> {
                                    if (stringQuality == 15) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < stringCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= stringCost;
                                        stringQuality++;
                                        hourlyIncome += 100;
                                        e.getChannel().sendMessage("Successfully upgraded String Quality to Level " + stringQuality + "\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "bow", "b" -> {
                                    if (bowQuality == 7) {
                                        e.getChannel().sendMessage("You can't buy any more of this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < bowCost) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= bowCost;
                                        bowQuality++;
                                        hourlyIncome += 200;
                                        e.getChannel().sendMessage("Successfully upgraded Bow Quality to Level " + bowQuality + "\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "math" -> {
                                    if (hasMath) {
                                        e.getChannel().sendMessage("You already bought this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (violins < 5000000) {
                                        e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= 5000000;
                                        hasMath = true;
                                        hourlyIncome += 2500;
                                        e.getChannel().sendMessage("Successfully purchased Math Tutoring!\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "orchestra", "o" -> {
                                    if (hasOrchestra) {
                                        e.getChannel().sendMessage("You already hired an orchestra!").queue();
                                        throw new IllegalArgumentException();
                                    } else if (hourlyIncome < 9500) {
                                        e.getChannel().sendMessage("You don't have enough hourly income to hire an orchestra!").queue();
                                    } else if (violins < 40000000) {
                                        e.getChannel().sendMessage("You are too poor to hire an orchestra!").queue();
                                        throw new IllegalArgumentException();
                                    } else {
                                        violins -= 40000000;
                                        hasOrchestra = true;
                                        hourlyIncome += 3130;
                                        e.getChannel().sendMessage("Successfully hired an Orchestra!  You have automatically gained 1 Violin I and 1 Violin II for free.\nYou have " + violins + ":violin: left.").queue();
                                    }
                                }
                                case "concert", "hall" -> {
                                    if(!hasOrchestra) {
                                        if (hallLevel == 2) {
                                            e.getChannel().sendMessage("Hire an orchestra to unlock Levels 3-5!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < hallCost) {
                                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= hallCost;
                                            hallLevel++;
                                            hourlyIncome += 300;
                                            e.getChannel().sendMessage("Successfully upgarded Concert Hall to Level " + hallLevel + "!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    } else {
                                        if (hallLevel == 5) {
                                            e.getChannel().sendMessage("You can't buy more of this upgrade!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < hallCost) {
                                            e.getChannel().sendMessage("You are too poor to buy this upgrade!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= hallCost;
                                            hallLevel++;
                                            hourlyIncome += 300;
                                            e.getChannel().sendMessage("Successfully upgarded Concert Hall to Level " + hallLevel + "!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                }
                                case "income" -> {
                                    if(extraIncome) {
                                        e.getChannel().sendMessage("You already have extra income!").queue();
                                    } else if(medals < 5) {
                                        e.getChannel().sendMessage("You don't have enough medals to buy this!").queue();
                                    } else {
                                        medals -= 5;
                                        extraIncome = true;
                                        hourlyIncome += 8000;
                                    }
                                }
                                case "commandIncome" -> {
                                    if(extraCommandIncome) {
                                        e.getChannel().sendMessage("You already have extra command income!").queue();
                                    } else if(medals < 5) {
                                        e.getChannel().sendMessage("You don't have enough medals to buy this!").queue();
                                    } else {
                                        medals -= 5;
                                        extraCommandIncome = true;
                                    }
                                }
                                case "gambleWinrate" -> {
                                    if(higherWinrate) {
                                        e.getChannel().sendMessage("You already have a higher gamble winrate!").queue();
                                    } else if(medals < 5) {
                                        e.getChannel().sendMessage("You don't have enough medals to buy this!").queue();
                                    } else {
                                        medals -= 5;
                                        higherWinrate = true;
                                    }
                                }
                                case "robWinrate" -> {
                                    if(higherRobrate) {
                                        e.getChannel().sendMessage("You already have a higher rob winrate!").queue();
                                    } else if(medals < 5) {
                                        e.getChannel().sendMessage("You don't have enough medals to buy this!").queue();
                                    } else {
                                        medals -= 5;
                                        higherRobrate = true;
                                    }
                                }
                                case "shield" -> {
                                    if(stealShield) {
                                        e.getChannel().sendMessage("You already have a steal shield!").queue();
                                    } else if(medals < 5) {
                                        e.getChannel().sendMessage("You don't have enough medals to buy this!").queue();
                                    } else {
                                        medals -= 5;
                                        stealShield = true;
                                    }
                                }
                                case "duplicator" -> {
                                    if(violinDuplicator) {
                                        e.getChannel().sendMessage("You already have a violin duplicator!").queue();
                                    } else if(medals < 5) {
                                        e.getChannel().sendMessage("You don't have enough medals to buy this!").queue();
                                    } else {
                                        medals -= 5;
                                        violinDuplicator = true;
                                    }
                                }
                                default ->  {
                                    if(!hasOrchestra) {
                                        e.getChannel().sendMessage("You can't buy nothing, that would be quite redundant.").queue();
                                    }
                                }
                            }
                            if(hasOrchestra) {
                                switch(message[1]) {
                                    case "piccolo" -> {
                                        if (piccolo) {
                                            e.getChannel().sendMessage("You already hired hired a piccolo player!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < 300000) {
                                            e.getChannel().sendMessage("You are too poor to hire a piccolo player!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= 300000;
                                            piccolo = true;
                                            hourlyIncome += 30;
                                            e.getChannel().sendMessage("Successfully hired a piccolo player!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "contrabassoon", "cb" -> {
                                        if (contrabassoon) {
                                            e.getChannel().sendMessage("You already hired hired a contrabassonist!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < 400000) {
                                            e.getChannel().sendMessage("You are too poor to hire a contrabassonist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= 400000;
                                            contrabassoon = true;
                                            hourlyIncome += 30;
                                            e.getChannel().sendMessage("Successfully hired a contrabassoonist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "harp" -> {
                                        if (harp) {
                                            e.getChannel().sendMessage("You already hired hired a harpist!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < 500000) {
                                            e.getChannel().sendMessage("You are too poor to hire a harpist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= 500000;
                                            harp = true;
                                            hourlyIncome += 80;
                                            e.getChannel().sendMessage("Successfully hired a harpist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "flute" -> {
                                        if (flute == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of flautists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < fluteCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a flautist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= fluteCost;
                                            flute++;
                                            hourlyIncome += 60;
                                            e.getChannel().sendMessage("Successfully hired a flautist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "oboe" -> {
                                        if (oboe == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of oboists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < oboeCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a oboist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= oboeCost;
                                            oboe++;
                                            hourlyIncome += 50;
                                            e.getChannel().sendMessage("Successfully hired a oboist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "clarinet" -> {
                                        if (clarinet == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of clarinetists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < clarinetCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a clarinetist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= clarinetCost;
                                            clarinet++;
                                            hourlyIncome += 40;
                                            e.getChannel().sendMessage("Successfully hired a clarinetist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "bassoon" -> {
                                        if (bassoon == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of bassoonists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < bassoonCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a bassoonist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= bassoonCost;
                                            bassoon++;
                                            hourlyIncome += 40;
                                            e.getChannel().sendMessage("Successfully hired a bassoonist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "horn" -> {
                                        if (horn == 8) {
                                            e.getChannel().sendMessage("You already hired the max amount of hornists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < hornCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a hornist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= hornCost;
                                            horn++;
                                            hourlyIncome += 40;
                                            e.getChannel().sendMessage("Successfully hired a hornist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "trumpet" -> {
                                        if (trumpet == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of trumpeters!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < trumpetCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a trumpeter!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= trumpetCost;
                                            trumpet++;
                                            hourlyIncome += 30;
                                            e.getChannel().sendMessage("Successfully hired a trumpeter!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "trombone" -> {
                                        if (trombone == 6) {
                                            e.getChannel().sendMessage("You already hired the max amount of trombonists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < tromboneCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a trombonist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= tromboneCost;
                                            trombone++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired a trombonist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "tuba" -> {
                                        if (horn == 2) {
                                            e.getChannel().sendMessage("You already hired the max amount of tubists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < tubaCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a tubist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= tubaCost;
                                            tuba++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired a tubist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "timpani" -> {
                                        if (timpani == 2) {
                                            e.getChannel().sendMessage("You already hired the max amount of timpanists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < timpaniCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a timpanist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= timpaniCost;
                                            timpani++;
                                            hourlyIncome += 60;
                                            e.getChannel().sendMessage("Successfully hired a timpanist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "percussion" -> {
                                        if (percussion == 2) {
                                            e.getChannel().sendMessage("You already hired the max amount of percussionists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < percussionCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a percussionist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= percussionCost;
                                            percussion++;
                                            hourlyIncome += 10;
                                            e.getChannel().sendMessage("Successfully hired a percussionist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "first" -> {
                                        if (first == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of first violinists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < firstCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a first violinist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= firstCost;
                                            first++;
                                            hourlyIncome += 70;
                                            e.getChannel().sendMessage("Successfully hired a first violinist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "second" -> {
                                        if (second == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of second violinists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < secondCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a second violinist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= secondCost;
                                            second++;
                                            hourlyIncome += 60;
                                            e.getChannel().sendMessage("Successfully hired a second violinist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "cello" -> {
                                        if (cello == 15) {
                                            e.getChannel().sendMessage("You already hired the max amount of cellists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < celloCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a cellist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= celloCost;
                                            cello++;
                                            hourlyIncome += 50;
                                            e.getChannel().sendMessage("Successfully hired a cellist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "double", "upright", "db", "doublebass" -> {
                                        if (stringBass == 5) {
                                            e.getChannel().sendMessage("You already hired the max amount of double bassists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < doubleCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a double bassist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= doubleCost;
                                            stringBass++;
                                            hourlyIncome += 50;
                                            e.getChannel().sendMessage("Successfully hired a double bassist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "piano" -> {
                                        if (piano == 2) {
                                            e.getChannel().sendMessage("You already hired the max amount of pianists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < pianoCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a pianist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= pianoCost;
                                            piano++;
                                            hourlyIncome += 110;
                                            e.getChannel().sendMessage("Successfully hired a pianists!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "soprano" -> {
                                        if (soprano == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of sopranists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < sopranoCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a sopranist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= sopranoCost;
                                            soprano++;
                                            hourlyIncome += 30;
                                            e.getChannel().sendMessage("Successfully hired a sopranist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "alto" -> {
                                        if (alto == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of altos!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < altoCost) {
                                            e.getChannel().sendMessage("You are too poor to hire an alto!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= altoCost;
                                            alto++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired an alto!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "tenor" -> {
                                        if (tenor == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of tenors!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < tenorCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a tenor!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= tenorCost;
                                            tenor++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired a tenor!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "bass" -> {
                                        if (bass == 20) {
                                            e.getChannel().sendMessage("You already hired the max amount of bassists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < bassCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a bassist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= bassCost;
                                            bass++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired a bassist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "soloist" -> {
                                        if (soloists == 4) {
                                            e.getChannel().sendMessage("You already hired the max amount of soloists!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < soloistCost) {
                                            e.getChannel().sendMessage("You are too poor to hire a soloist!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= soloistCost;
                                            soloists++;
                                            hourlyIncome += 20;
                                            e.getChannel().sendMessage("Successfully hired a soloist!\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "conductor", "musicality" -> {
                                        if (conductor == 5) {
                                            e.getChannel().sendMessage("The conductor has been maxed in Musicality!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < conductorCost) {
                                            e.getChannel().sendMessage("You are too poor to train the conductor!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= conductorCost;
                                            conductor++;
                                            hourlyIncome += 200;
                                            e.getChannel().sendMessage("Successfully upgraded Conductor Musicality to Level " + conductor + "\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "advertisement", "ad" -> {
                                        if (advertising == 20) {
                                            e.getChannel().sendMessage("You can't advertise more!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < advertisingCost) {
                                            e.getChannel().sendMessage("You are too poor to buy more advertisements!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= advertisingCost;
                                            advertising++;
                                            hourlyIncome += 100;
                                            e.getChannel().sendMessage("Successfully upgraded Advertising to Level " + advertising + "\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    case "tickets" -> {
                                        if (tickets == 5) {
                                            e.getChannel().sendMessage("You can't make your tickets more expensive!").queue();
                                            throw new IllegalArgumentException();
                                        } else if (violins < ticketCost) {
                                            e.getChannel().sendMessage("You are too poor to make your tickets more expensive!").queue();
                                            throw new IllegalArgumentException();
                                        } else {
                                            violins -= ticketCost;
                                            tickets++;
                                            hourlyIncome += 1000;
                                            e.getChannel().sendMessage("Successfully upgraded Ticket Cost to Level " + tickets + "\nYou have " + violins + ":violin: left.").queue();
                                        }
                                    }
                                    default -> e.getChannel().sendMessage("You can't buy nothing, that would be quite redundant.").queue();
                                }
                            }
                            ranCommand = true;
                        }
                        case "orchestra", "o" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            if(hasOrchestra) {
                                int temp1 = 0;
                                int temp2 = 0;
                                int temp3 = 0;
                                if(piccolo) {
                                    temp1 = 1;
                                }
                                if(contrabassoon) {
                                    temp2 = 1;
                                }
                                if(harp) {
                                    temp3 = 1;
                                }
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setTitle("**__Orchestra Stats__**")
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                        .addField("**Woodwinds**", temp1 + " Piccolo\n" + flute + " Flutes\n" + oboe + " Oboes\n" + clarinet + " Clarinets\n" + bassoon + " Bassoons\n" + temp2 + " Contrabassoon", false)
                                        .addField("**Brass**", trumpet + " Trumpets\n" + horn + " French Horns\n" + trombone + " Trombones\n" + tuba + " Tubas", false)
                                        .addField("**Strings**", first + " Violin I\n" + second + " Violin II\n" + cello + " Celli\n" + stringBass + " Double Basses\n" + piano + " Pianists", false)
                                        .addField("**Choir**", soprano + " Sopranos\n" + alto + " Altos\n" + tenor + " Tenors\n" + bass + " Basses\n" + soloists + " Vocal Soloists", false)
                                        .addField("**Other**", temp3 + " Harp\n" + percussion + " Percussionists\n" + timpani + " Timpanists",false)
                                        .setColor(Color.BLUE);
                                e.getChannel().sendMessage(builder.build()).queue();
                            } else {
                                e.getChannel().sendMessage("You don't have an orchestra yet!  Run `" + prefix + "buy orchestra` to get one!  Remember that you need 9 500:violin:/hour and 40 000 000:violin: to hire one!").queue();
                            }
                            ranCommand = true;
                        }
                        case "cooldowns", "c" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            //gamble, rob, practise, rehearse, perform
                            EmbedBuilder builder = new EmbedBuilder()
                                    .setColor(Color.BLUE)
                                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
                            //gamble
                            long milliseconds = gambleC - time;
                            long seconds;
                            long minutes;
                            long hours;
                            long days;
                            if (milliseconds < 0) {
                                builder.addField("**Gamble**", "This command can be used now!", false);
                            } else {
                                seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                builder.addField("**Gamble**", seconds + " seconds " + milliseconds + " milliseconds", false);
                            }

                            //rob
                            milliseconds = robC - time;
                            if (milliseconds < 0) {
                                builder.addField("**Rob**", "This command can be used now!", false);
                            } else {
                                hours = milliseconds / 3600000;
                                milliseconds -= hours * 3600000;
                                minutes = milliseconds / 60000;
                                milliseconds -= minutes * 60000;
                                seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                builder.addField("**Rob**", hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds", false);
                            }

                            //practice
                            milliseconds = workC - time;
                            if (milliseconds < 0) {
                                builder.addField("**Practice**", "This command can be used now!", false);
                            } else {
                                minutes = milliseconds / 60000;
                                milliseconds -= minutes * 60000;
                                seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                builder.addField("**Practice**", minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds", false);
                            }

                            //rehearse
                            if(hasOrchestra) {
                                milliseconds = rehearseC - time;
                                if (milliseconds < 0) {
                                    builder.addField("**Rehearse**", "This command can be used now!", false);
                                } else {
                                    hours = milliseconds / 3600000;
                                    milliseconds -= hours * 3600000;
                                    minutes = milliseconds / 60000;
                                    milliseconds -= minutes * 60000;
                                    seconds = milliseconds / 1000;
                                    milliseconds -= seconds * 1000;
                                    builder.addField("**Rehearse**", hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds", false);
                                }
                            }

                            //perform
                            milliseconds = performC - time;
                            if (milliseconds < 0) {
                                builder.addField("**Perform**", "This command can be used now!", false);
                            } else {
                                days = milliseconds / 86400000;
                                milliseconds -= days * 86400000;
                                hours = milliseconds / 3600000;
                                milliseconds -= hours * 3600000;
                                minutes = milliseconds / 60000;
                                milliseconds -= minutes * 60000;
                                seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                builder.addField("**Perform**", days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds", false);
                            }

                            //daily
                            milliseconds = dailyC - time;
                            if(milliseconds < 0) {
                                builder.addField("**Daily**", "This command can be used now!", false);
                            } else {
                                hours = milliseconds / 3600000;
                                milliseconds -= hours * 3600000;
                                minutes = milliseconds / 60000;
                                milliseconds -= minutes * 60000;
                                seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                builder.addField("**Daily**", hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds", false);
                            }
                            builder.setTitle("__**Cooldowns**__");
                            e.getChannel().sendMessage(builder.build()).queue();
                            ranCommand = true;
                        }
                        case "use" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            switch (message[1]) {
                                case "1" -> {
                                    if (ownInsurance1) {
                                        activeInsurance = 1;
                                        e.getChannel().sendMessage("You have activated Plan 1 - Full Security").queue();
                                    } else {
                                        e.getChannel().sendMessage("You don't own this insurance!  Run `" + prefix + "shop` to see the details.").queue();
                                    }
                                }
                                case "2" -> {
                                    if (ownInsurance2) {
                                        activeInsurance = 2;
                                        e.getChannel().sendMessage("You have activated Plan 2 - Half Security").queue();
                                    } else {
                                        e.getChannel().sendMessage("You don't own this insurance!  Run `" + prefix + "shop` to see the details.").queue();
                                    }
                                }
                                case "0" -> {
                                    activeInsurance = 0;
                                    e.getChannel().sendMessage("You have deactivated all insurance.  Not a good idea...").queue();
                                }
                                case "rice" -> {
                                    if(hourlyIncome > 40000) {
                                        e.getChannel().sendMessage("Woah there, take a chill pill!  Your hourly income is already above the hard maximum of 40 000!").queue();
                                    } else if(rice == 0) {
                                        e.getChannel().sendMessage("You look for that last rice piece you had but can't find it.  Then you remember that you don't have any left.").queue();
                                    } else {
                                        rice --;
                                        hourlyIncome *= 2;
                                        e.getChannel().sendMessage("You consume a piece of rice.  It turns out to be Blessed Rice and God of Rice doubles your hourly income for 1 hour.").queue();
                                    }
                                }
                                default -> e.getChannel().sendMessage("You can't use nothing, that doesn't make sense.").queue();
                            }
                            ranCommand = true;
                        }
                        case "practice", "p" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            if (time < workC) {
                                long milliseconds = workC - time;
                                long minutes = milliseconds / 60000;
                                milliseconds -= minutes * 60000;
                                long seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                e.getChannel().sendMessage("Chill, you already practised this hour!  Wait " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                            } else {
                                int base = (int) ((random.nextInt(40) + 380) * Math.pow(1.04, workL) * ((0.2 * hallLevel) + 1));
                                if(serverVote) {
                                    base *= 1.1;
                                }
                                if(hasOrchestra) {
                                    base *= 2;
                                }
                                if(extraCommandIncome) {
                                    base *= 2;
                                }
                                double num = random.nextDouble();
                                if(num > 0.5) {
                                    violins += base;
                                    e.getChannel().sendMessage("You practised for 40 minutes and earned " + base + ":violin:").queue();
                                } else if(num > 0.25) {
                                    num = random.nextDouble();
                                    if(num > 0.5) {
                                        e.getChannel().sendMessage("Your teacher approved the results of your practise session.  Your tiger mom saw the comment, and gave you " + base * 0.1 + ":violin: in addition to the " + base + " that you earned.").queue();
                                        base *= 1.1;
                                        violins += base;
                                    } else if(num > 0.1) {
                                        e.getChannel().sendMessage("Your tiger mom approved your practise session.  She gave you " + base * 0.5 + ":violin: in addition to the " + base + " that you earned.").queue();
                                        base *= 1.5;
                                        violins += base;
                                    } else {
                                        base *= 2;
                                        e.getChannel().sendMessage("Ling Ling approved of your practise session and doubled the amount of violins you earned.  You got " + base + ":violin:").queue();
                                    }
                                } else {
                                    num = random.nextDouble();
                                    if(num > 0.8) {
                                        violins -= 500;
                                        e.getChannel().sendMessage("Oh no!  Your E String snapped while you were practising!  You had to go to the store to get it replaced, and were not able to get any practising done.  You earned 0:violin: and had to pay 500:violin: for a new E String.").queue();
                                    } else if(num > 0.65) {
                                        base *= 0.9;
                                        violins += base;
                                        e.getChannel().sendMessage("Your violin randomly went out of tune while you were practising.  You had to spend 4 minutes tuning it and were only able to earn " + base + ":violin:").queue();
                                    } else if(num > 0.5) {
                                        base *= 0.95;
                                        e.getChannel().sendMessage("You had problems with your music stand, and page turning wasn't the best this session.  You earned " + base + ":violin:").queue();
                                    } else if(num > 0.35) {
                                        rice ++;
                                        e.getChannel().sendMessage("You found 1:rice: while you were Practising but didn't get any violins.").queue();
                                    } else if(num > 0.25) {
                                        base *= 0.5;
                                        violins += base;
                                        violins -= 5000;
                                        e.getChannel().sendMessage("You hurt your wrist while practising and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying 5000:violin: in hospital fees.").queue();
                                    } else if(num > 0.15) {
                                        base *= 0.2;
                                        violins += base;
                                        e.getChannel().sendMessage("Your bridge fell off 10 minutes into your session.  You spend the next half-hour trying to get it back on, and you only earned " + base + ":violin:").queue();
                                    } else if(num > 0.05) {
                                        violins -= 10000;
                                        e.getChannel().sendMessage("You were caught playing Minecraft while practising.  Your tiger mom took all your earnings, in addition to another 10000 for being distracted.").queue();
                                    } else if(num > 0.015) {
                                        e.getChannel().sendMessage("Your chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise for 12 hours.").queue();
                                        time += 43200000;
                                    } else if(num > 0.005) {
                                        e.getChannel().sendMessage("You decided to fake your practise session.  Ling Ling caught you in the act, and fined you " + violins * 0.25 + ":violin:").queue();
                                        violins -= violins * 0.25;
                                    } else {
                                        e.getChannel().sendMessage("You dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined " + violins * 0.5 + ":violin: for being careless.").queue();
                                        violins -= violins * 0.5;
                                        time += 86400000;
                                        rehearseC += 86400000;
                                        performC += 86400000;
                                        robC += 86400000;
                                    }
                                }
                                if(faster) {
                                    workC = time + 1740000;
                                } else {
                                    workC = time + 2340000;
                                }
                            }
                            ranCommand = true;
                        }
                        case "rehearse", "r" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            if(hasOrchestra) {
                                if (time < rehearseC) {
                                    long milliseconds = rehearseC - time;
                                    long hours = milliseconds / 3600000;
                                    milliseconds -= hours * 3600000;
                                    long minutes = milliseconds / 60000;
                                    milliseconds -= minutes * 60000;
                                    long seconds = milliseconds / 1000;
                                    milliseconds -= seconds * 1000;
                                    e.getChannel().sendMessage("You don't have the time to go to rehearsal that often, wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                                } else {
                                    int base = (int) ((random.nextInt(200) + 1900) * Math.pow(1.04, workL) * ((0.2 * hallLevel) + 1));
                                    if(serverVote) {
                                        base *= 1.1;
                                    }
                                    if(hasOrchestra) {
                                        base *= 2;
                                    }
                                    if(extraCommandIncome) {
                                        base *= 2;
                                    }
                                    double num = random.nextDouble();
                                    if(num > 0.5) {
                                        violins += base;
                                        e.getChannel().sendMessage("You rehearsed with your orchestra and earned " + base + ":violin:").queue();
                                    } else if(num > 0.25) {
                                        num = random.nextDouble();
                                        if(num > 0.5) {
                                            e.getChannel().sendMessage("Your teacher approved the results of your rehearsal.  Your tiger mom saw the comment, and gave you " + base * 0.1 + ":violin: in addition to the " + base + " that you earned.").queue();
                                            base *= 1.1;
                                            violins += base;
                                        } else if(num > 0.1) {
                                            e.getChannel().sendMessage("Your tiger mom enjoyed your rehearsal.  She gave you " + base * 0.5 + ":violin: in addition to the " + base + " that you earned.").queue();
                                            base *= 1.5;
                                            violins += base;
                                        } else {
                                            base *= 2;
                                            e.getChannel().sendMessage("Ling Ling approved of your rehearsal and doubled the amount of violins you earned.  You got " + base + ":violin:").queue();
                                        }
                                    } else {
                                        num = random.nextDouble();
                                        if(num > 0.8) {
                                            violins -= 500;
                                            base *= 0.95;
                                            violins += base;
                                            e.getChannel().sendMessage("Oh no!  Your E String snapped during the rehearsal!  You had to borrow the concertmaster's violin, and only earned " + base + ":violin:  You eventually had to pay 500:violin: for a new E String.").queue();
                                        } else if(num > 0.65) {
                                            base *= 0.9;
                                            violins += base;
                                            e.getChannel().sendMessage("Your violin randomly went out of tune during the rehearsal.  You had to spend 4 minutes tuning it and were only able to earn " + base + ":violin:").queue();
                                        } else if(num > 0.5) {
                                            base *= 0.95;
                                            e.getChannel().sendMessage("The orchestra had music stand problems, and page turning wasn't the best either.  You only earned " + base + ":violin:").queue();
                                        } else if(num > 0.35) {
                                            rice += 3;
                                            e.getChannel().sendMessage("You found 3:rice: rehearsal but didn't get any violins.").queue();
                                        } else if(num > 0.25) {
                                            base *= 0.5;
                                            violins += base;
                                            violins -= 5000;
                                            e.getChannel().sendMessage("You hurt your wrist during the rehearsal and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying 5000:violin: in hospital fees.").queue();
                                        } else if(num > 0.15) {
                                            base *= 0.2;
                                            violins += base;
                                            e.getChannel().sendMessage("Your bridge fell off during the rehearsal.  You spend the next half-hour trying to get it back on, and you only earned " + base + ":violin:").queue();
                                        } else if(num > 0.05) {
                                            violins -= 20000;
                                            e.getChannel().sendMessage("You had a memory blank during rehearsal.  Your tiger mom took all of your earnings, in addition to another 20000 for shaming yourself.").queue();
                                        } else if(num > 0.015) {
                                            e.getChannel().sendMessage("Your chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise for 12 hours.").queue();
                                            workC += 43200000;
                                        } else if(num > 0.005) {
                                            e.getChannel().sendMessage("You decided to fake your solo.  Of course it didn't work and Ling Ling fined you " + violins * 0.35 + ":violin:").queue();
                                            violins -= violins * 0.35;
                                        } else {
                                            e.getChannel().sendMessage("You dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined " + violins * 0.75 + ":violin: for being careless in public.").queue();
                                            violins -= violins * 0.75;
                                            workC += 86400000;
                                            time += 86400000;
                                            performC += 86400000;
                                            robC += 86400000;
                                        }
                                    }
                                    if(faster) {
                                        rehearseC = time + 64740000;
                                    } else {
                                        rehearseC = time + 86340000;
                                    }
                                }
                            } else {
                                e.getChannel().sendMessage("You must hire an orchestra to use this command!").queue();
                            }
                            ranCommand = true;
                        }
                        case "perform" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            if (time < performC) {
                                long milliseconds = performC - time;
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
                                int base = (int) ((random.nextInt(2000) + 4000) * Math.pow(1.04, workL) * ((0.2 * hallLevel) + 1));
                                if(serverVote) {
                                    base *= 1.1;
                                }
                                if(hasOrchestra) {
                                    base *= 2;
                                }
                                if(extraCommandIncome) {
                                    base *= 2;
                                }
                                double num = random.nextDouble();
                                if(num > 0.5) {
                                    violins += base;
                                    e.getChannel().sendMessage("You performed your solo and earned " + base + ":violin:").queue();
                                } else if(num > 0.3) {
                                    num = random.nextDouble();
                                    if(num > 0.5) {
                                        e.getChannel().sendMessage("Your teacher approved the results of your practise session.  Your tiger mom saw the comment, and gave you " + base * 0.1 + ":violin: in addition to the " + base + " that you earned.").queue();
                                        base *= 1.1;
                                        violins += base;
                                    } else if(num > 0.1) {
                                        e.getChannel().sendMessage("Your tiger mom approved your practise session.  She gave you " + base * 0.5 + ":violin: in addition to the " + base + " that you earned.").queue();
                                        base *= 1.5;
                                        violins += base;
                                    } else {
                                        base *= 2;
                                        e.getChannel().sendMessage("Ling Ling approved of your practise session and doubled the amount of violins you earned.  You got " + base + ":violin:").queue();
                                    }
                                } else if(num > 0.05) {
                                    num = random.nextDouble();
                                    if(num > 0.85) {
                                        violins -= 500;
                                        base *= 0.5;
                                        violins += base;
                                        e.getChannel().sendMessage("Oh no!  Your E String snapped during the performance!  You couldn't go on, and only earned " + base + ":violin:  You eventually had to pay 500:violin: for a new E String.").queue();
                                    } else if(num > 0.7) {
                                        base *= 0.9;
                                        violins += base;
                                        e.getChannel().sendMessage("Your violin randomly went out of tune during the rehearsal.  You had to spend 4 minutes tuning it and were only able to earn " + base + ":violin:").queue();
                                    } else if(num > 0.55) {
                                        base *= 0.9;
                                        e.getChannel().sendMessage("You didn't memorize your piece and you had to use your music.  You only earned " + base + ":violin:").queue();
                                    } else if(num > 0.4) {
                                        rice += 9;
                                        e.getChannel().sendMessage("Your paycheck contained 9 :rice: instead of violins.").queue();
                                    } else if(num > 0.3) {
                                        base *= 0.4;
                                        violins += base;
                                        violins -= 5000;
                                        e.getChannel().sendMessage("You hurt your wrist during the performance and only got half of the effectiveness.  You earned " + base + ":violin: but ended up paying 5000:violin: in hospital fees.").queue();
                                    } else if(num > 0.2) {
                                        base *= 0.1;
                                        violins += base;
                                        e.getChannel().sendMessage("Your bridge fell off during the performance.  You spend the next half-hour trying to get it back on, and you only earned " + base + ":violin:").queue();
                                    } else if(num > 0.1) {
                                        violins -= 50000;
                                        e.getChannel().sendMessage("You had a memory blank during the performance.  Your tiger mom took all your earnings, in addition to another 50000 for shaming yourself.").queue();
                                    } else if(num > 0.05) {
                                        base *= 0.5;
                                        violins -= 10000;
                                        violins += base;
                                        e.getChannel().sendMessage("Your performance was for a competition, and you only won Honorable Mention.  Your tiger mom Kung-Paos your chicken and takes half your earnings, in addition to another 10000 for being so mediocre.").queue();
                                    } else if(num > 0.015) {
                                        e.getChannel().sendMessage("Your chin rest popped off your violin!  You take your violin to the luthier, who informs you that the violin will have to stay overnight.  You are not able to practise for 12 hours.").queue();
                                        workC += 43200000;
                                    } else if(num > 0.005) {
                                        e.getChannel().sendMessage("You decided to fake your performance.  Of course it didn't work and Ling Ling fined you " + violins * 0.5 + ":violin:").queue();
                                        violins -= violins * 0.5;
                                    } else {
                                        e.getChannel().sendMessage("You dropped your violin.  How shameful.  All cooldowns except daily and gamble have had one day added to them, and you were fined your entire balance for being careless on stage.").queue();
                                        violins = 0;
                                        workC += 86400000;
                                        rehearseC += 86400000;
                                        time += 86400000;
                                        robC += 86400000;
                                    }
                                } else {
                                    num = random.nextDouble();
                                    if(num > 0.5) {
                                        base *= 2;
                                        violins += base;
                                        medals ++;
                                        thirdP ++;
                                        e.getChannel().sendMessage(":trophy: Your performance won third place.  Your earnings were doubled, and you walked away with 1:military_medal: and a third place trophy :third_place:").queue();
                                    } else if(num > 0.1) {
                                        base *= 10;
                                        violins += base;
                                        medals += 2;
                                        secondP ++;
                                        e.getChannel().sendMessage(":trophy: Your performance won second place.  Your earnings were multiplied by 10, and you walked away with 2:military_medal: and a second place trophy :second_place:").queue();
                                    } else {
                                        base *= 100;
                                        violins += base;
                                        medals += 3;
                                        firstP += 3;
                                        e.getChannel().sendMessage(":trophy: Your performance won first place.  Congratulations!  Your earnings were multiplied by 100, and you walked away with **3**:military_medal: and a FIRST place trophy :first_place:").queue();
                                    }
                                }
                                if(faster) {
                                    performC = time + 215940000;
                                } else {
                                    performC = time + 302340000;
                                }
                            }
                            ranCommand = true;
                        }
                        case "daily", "d" -> {
                            if(!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                            }
                            if(!e.getMessage().getContentRaw().contains("!d bump")) {
                                if (time < dailyC) {
                                    long milliseconds = dailyC - time;
                                    long hours = milliseconds / 3600000;
                                    milliseconds -= hours * 3600000;
                                    long minutes = milliseconds / 60000;
                                    milliseconds -= minutes * 60000;
                                    long seconds = milliseconds / 1000;
                                    milliseconds -= seconds * 1000;
                                    e.getChannel().sendMessage("I can't give out violins that fast, wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                                } else {
                                    if(time > dailyExp) {
                                        e.getChannel().sendMessage("Oh no!  Your streak was reset!").queue();
                                        streak = 0;
                                    }
                                    int base = 4000 + (streak * 40);
                                    violins += base;
                                    dailyC = time + 85500000;
                                    dailyExp = time + 172800000;
                                    streak++;
                                    e.getChannel().sendMessage("You received a total of " + base + ":violin:, with " + (streak - 1) * 40 + ":violin: coming from your " + streak + "-day streak!").queue();
                                }
                            }
                            ranCommand = true;
                        }
                        case "rob" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            if (time < robC) {
                                long milliseconds = robC - time;
                                long hours = milliseconds / 3600000;
                                milliseconds -= hours * 3600000;
                                long minutes = milliseconds / 60000;
                                milliseconds -= minutes * 60000;
                                long seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                e.getChannel().sendMessage("Hey, Brett and Eddy are still looking for you after your last hit!  Wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                            } else {
                                String name = "";
                                try {
                                    name = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
                                } catch(Exception exception) {
                                    e.getChannel().sendMessage("You cannot rob nobody.  That doesn't make sense.").queue();
                                }
                                if (e.getAuthor().getId().equals(target)) {
                                    e.getChannel().sendMessage("Why would you rob yourself, are you actually that dumb?").queue();
                                    throw new IllegalArgumentException();
                                }
                                String[] targetdata;
                                try {
                                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                                    targetdata = reader.readLine().split(" ");
                                    reader.close();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("You did not mention a user or provide a valid User ID.  Doesn't make sense to rob a non-existant user, does it?").queue();
                                    throw new IllegalArgumentException();
                                }
                                long targetViolins;
                                targetViolins = Long.parseLong(targetdata[0]);
                                double failChance = (double) violins / (targetViolins + violins);
                                failChance -= 0.005 * robL;
                                if(higherRobrate) {
                                    failChance -= 0.025;
                                }
                                double num = random.nextDouble();
                                int insurance = Integer.parseInt(targetdata[11]);
                                boolean opponentShield = Boolean.parseBoolean(targetdata[60]);
                                if (num < failChance) {
                                    e.getChannel().sendMessage("Brett and Eddy caught you trying to rob " + name + "!  You paid " + name + " " + (long) (Long.parseLong(data[0]) * 0.1) + ":violin: for attempting to rob them.\n*The generator rolled " + num + ", you need at least " + failChance + " to succeed.*").queue();
                                    User send = e.getJDA().getUserById(target);
                                    assert send != null;
                                    send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") tried to rob you but failed!  They paid you " + (long) (Long.parseLong(data[0]) * 0.1) + ":violin: in fines.").queue();
                                    targetViolins += Long.parseLong(data[0]) * 0.1;
                                    violins -= Long.parseLong(data[0]) * 0.1;
                                } else {
                                    if (insurance == 1) {
                                        e.getChannel().sendMessage("You try to rob " + name + " only to notice that Ling Ling Security is present.  You try to escape but you are caught and kicked from the estate.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                        User send = e.getJDA().getUserById(target);
                                        assert send != null;
                                        if(opponentShield) {
                                            send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") tried to rob you!  Luckily, insurance protected you and you only paid " + (long) (Long.parseLong(targetdata[0]) * 0.03125) + ":violin: in insurance costs.  Your Steal Shield protected " + (long) (Long.parseLong(targetdata[0]) * 0.03125) + " +:violin: from being paid.").queue();
                                            targetViolins -= Long.parseLong(targetdata[0]) * 0.03125;
                                        } else {
                                            send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") tried to rob you!  Luckily, insurance protected you and you only paid " + (long) (Long.parseLong(targetdata[0]) * 0.0625) + ":violin: in insurance costs.").queue();
                                            targetViolins -= Long.parseLong(targetdata[0]) * 0.0625;
                                        }
                                    } else if (insurance == 2) {
                                        User send = e.getJDA().getUserById(target);
                                        assert send != null;
                                        if(opponentShield) {
                                            e.getChannel().sendMessage("You successfully robbed " + name + " but ran into a Steal Shield.  You only managed to get away with " + (long) (Long.parseLong(targetdata[0]) * 0.025) + ":violin: before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                            send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (Long.parseLong(targetdata[0]) * 0.025) + ":violin: from you!  Your Ling Ling insurance protected " + (long) (Long.parseLong(targetdata[0]) * 0.2) + ":violin: and your Steal Shield protected " + (long) ((Long.parseLong(targetdata[0])) * 0.025) + ":violin:").queue();
                                            targetViolins -= Long.parseLong(targetdata[0]) * 0.025;
                                            if(violinDuplicator) {
                                                e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
                                                violins += Long.parseLong(targetdata[0]) * 0.5;
                                            } else {
                                                violins += Long.parseLong(targetdata[0]) * 0.025;
                                            }
                                        } else {
                                            e.getChannel().sendMessage("You successfully robbed " + name + " but only managed to get away with " + (long) (Long.parseLong(targetdata[0]) * 0.05) + ":violin: before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                            send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (Long.parseLong(targetdata[0]) * 0.05) + ":violin: from you!  Your Ling Ling insurance protected " + (long) (Long.parseLong(targetdata[0]) * 0.2) + ":violin:").queue();
                                            targetViolins -= Long.parseLong(targetdata[0]) * 0.05;
                                            if(violinDuplicator) {
                                                e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
                                                violins += Long.parseLong(targetdata[0]) * 0.1;
                                            } else {
                                                violins += Long.parseLong(targetdata[0]) * 0.05;
                                            }
                                        }
                                    } else {
                                        User send = e.getJDA().getUserById(target);
                                        assert send != null;
                                        if(opponentShield) {
                                            e.getChannel().sendMessage("You successfully robbed " + name + " but a Steal Shield stopped your looting halfway through.  Your payout was " + (long) (Long.parseLong(targetdata[0]) * 0.125) + ":violin:\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                            send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (Long.parseLong(targetdata[0]) * 0.125) + ":violin: from you!  Your Steal Shield protected " + (long) ((Long.parseLong(targetdata[0])) * 0.125) + ":violin:").queue();
                                            targetViolins -= Long.parseLong(targetdata[0]) * 0.125;
                                            if(violinDuplicator) {
                                                e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
                                                violins += Long.parseLong(targetdata[0]) * 0.25;
                                            } else {
                                                violins += Long.parseLong(targetdata[0]) * 0.125;
                                            }
                                        } else {
                                            e.getChannel().sendMessage("You successfully robbed " + name + "!  Your payout was " + (long) (Long.parseLong(targetdata[0]) * 0.25) + ":violin:\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
                                            send.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (Long.parseLong(targetdata[0]) * 0.25) + ":violin: from you!").queue();
                                            targetViolins -= Long.parseLong(targetdata[0]) * 0.25;
                                            if(violinDuplicator) {
                                                e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
                                                violins += Long.parseLong(targetdata[0]) * 0.5;
                                            } else {
                                                violins += Long.parseLong(targetdata[0]) * 0.25;
                                            }
                                        }
                                    }
                                }
                                robC = time + 86340000;
                                try {
                                    writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt")));

                                    writer.print(targetViolins);
                                    for(int i = 1; i < targetdata.length; i ++) {
                                        writer.print(" " + targetdata[i]);
                                    }
                                    writer.close();
                                } catch (Exception exception) {
                                    //nothing here lol
                                }
                            }
                            ranCommand = true;
                        }
                        case "gamble", "bet" -> {
                            if (!hasData) {
                                e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                throw new IllegalArgumentException();
                            }
                            long bet;
                            try {
                                bet = Integer.parseInt(message[1]);
                            } catch (Exception exception) {
                                e.getChannel().sendMessage("You must bet something, how smol brane are you???").queue();
                                throw new IllegalArgumentException();
                            }
                            if (time < gambleC) {
                                long milliseconds = gambleC - time;
                                long seconds = milliseconds / 1000;
                                milliseconds -= seconds * 1000;
                                e.getChannel().sendMessage("Don't bet your violins like Paganini, wait " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
                            } else if (bet > violins) {
                                e.getChannel().sendMessage("You can't bet more than you have, don't try to outsmart me.").queue();
                            } else if (bet < 0) {
                                e.getChannel().sendMessage("You can't bet a negative number, don't try breaking me.").queue();
                            } else if (bet < 4000) {
                                e.getChannel().sendMessage("If you're going to bet less than 4000:violin:, go away and stop wasting my time.").queue();
                            } else if(bet > hourlyIncome * 5) {
                                e.getChannel().sendMessage("You cannot bet more than " + hourlyIncome * 5 + ":violin:  To raise this cap, upgrade your hourly income!").queue();
                            } else {
                                double chance = random.nextDouble();
                                gambleC = time + 59000;
                                double winrate = 0.35 + 0.0075 * gambleL;
                                if(higherWinrate) {
                                    winrate += 0.05;
                                }
                                if (chance > winrate) {
                                    violins -= bet;
                                    e.getChannel().sendMessage("You lost " + bet + ":violin:\n*The generator rolled " + chance + ", you need less than " + winrate + " to win.*\nYou now have " + violins + ":violin:").queue();
                                } else if (chance <= winrate) {
                                    violins += bet;
                                    e.getChannel().sendMessage("You won " + bet + ":violin:\n*The generator rolled " + chance + ".*\nYou now have " + violins + ":violin:").queue();
                                }
                                if (violins == 69) {
                                    e.getChannel().sendMessage("I'm not going to let you waste your time trying to get the funny number, I'm taking one violin away.").queue();
                                    violins--;
                                }
                            }
                            ranCommand = true;
                        }
                        case "balance", "bal", "b" -> {
                            if (message.length == 1) {
                                if (!hasData) {
                                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                    throw new IllegalArgumentException();
                                }
                                e.getChannel().sendMessage("You have " + Integer.parseInt(data[0]) + ":violin:\nHourly income: " + hourlyIncome + "\nLing Ling Medals: " + medals + ":military_medal:").queue();
                            } else {
                                String name = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
                                try {
                                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                                    String line = reader.readLine();
                                    e.getChannel().sendMessage(name + " has " + line.split(" ")[0] + ":violin:\nHourly income: " + line.split(" ")[12] + "\nLing Ling Medals: " + line.split(" ")[55] + ":military_medal:").queue();
                                    reader.close();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("This user profile doesn't exist!").queue();
                                }
                            }
                            ranCommand = true;
                        }
                        case "inventory", "inv" -> {
                            if (message.length == 1) {
                                if (!hasData) {
                                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                    throw new IllegalArgumentException();
                                }
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                        .setTitle(e.getAuthor().getName() + "'s Inventory")
                                        .addField("Rice :rice:", "Count: " + rice + "\nUsage: Doubles your hourly income for one hour.\nStacks until you exceed 40 000:violin:/hour\nID: `rice`", false);
                                e.getChannel().sendMessage(builder.build()).queue();
                            } else {
                                String name = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
                                try {
                                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                                    String line = reader.readLine();
                                    EmbedBuilder builder = new EmbedBuilder()
                                            .setColor(Color.BLUE)
                                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                            .setTitle(name + "'s Inventory")
                                            .addField("Rice :rice:", "Count: " + line.split(" ")[51], false);
                                    e.getChannel().sendMessage(builder.build()).queue();
                                    reader.close();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("This user profile doesn't exist!").queue();
                                }
                            }
                            ranCommand = true;
                        }
                        case "profile" -> {
                            if(message.length == 1) {
                                if (!hasData) {
                                    e.getChannel().sendMessage("You don't even have a save file, what are you doing???  Run `" + prefix + "start` to get one!").queue();
                                    throw new IllegalArgumentException();
                                }
                                EmbedBuilder builder = new EmbedBuilder()
                                        .setColor(Color.BLUE)
                                        .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                        .setTitle(e.getAuthor().getName() + "'s Profile")
                                        .addField("General Stats", "Balance: " + violins + ":violin:\nLing Ling Medals: " + medals + ":military_medal:\nHourly Income: " + hourlyIncome + ":violin:/hour", false)
                                        .addField("Medals", ":first_place:" + firstP + "\n:second_place:" + secondP + "\n:third_place:" + thirdP, false);
                                e.getChannel().sendMessage(builder.build()).queue();
                            } else {
                                String name = Objects.requireNonNull(e.getJDA().getUserById(target)).getName();
                                try {
                                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
                                    String line = reader.readLine();
                                    EmbedBuilder builder = new EmbedBuilder()
                                            .setColor(Color.BLUE)
                                            .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                            .setTitle(name + "'s Profile")
                                            .addField("General Stats", "Balance: " + line.split(" ")[0] + ":violin:\nLing Ling Medals: " + line.split(" ")[55] + ":military_medal:\nHourly Income: " + line.split(" ")[12] + ":violin:/hour", false)
                                            .addField("Medals", ":first_place:" + line.split(" ")[54] + "\n:second_place:" + line.split(" ")[53] + "\n:third_place:" + line.split(" ")[52], false);
                                    e.getChannel().sendMessage(builder.build()).queue();
                                    reader.close();
                                } catch (Exception exception) {
                                    e.getChannel().sendMessage("This user profile doesn't exist!").queue();
                                }
                            }
                            ranCommand = true;
                        }
                        case "leaderboard", "lb" -> {
                            File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
                            File[] files = directory.listFiles();
                            String[] entry = new String[0];
                            boolean isGlobal = false;
                            if (files != null) {
                                String temp2 = "REEEEE";
                                try {
                                    temp2 = message[1];
                                } catch (Exception exception) {
                                    //nothing here lol
                                }
                                if (temp2.equals("global")) {
                                    isGlobal = true;
                                    entry = new String[]{"<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n", "<@0>: 0 violins\n"};
                                    for (File file : files) {
                                        String currentData = null;
                                        String user;
                                        int pos;
                                        try {
                                            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                            currentData = reader.readLine();
                                            reader.close();
                                        } catch (Exception exception) {
                                            //nothing here lol
                                            //entry format: **Position.** user#9999 `userID`: num violins
                                        }
                                        user = file.getName();
                                        pos = user.lastIndexOf(".");
                                        user = user.substring(0, pos);
                                        assert currentData != null;
                                        String[] temp = currentData.split(" ");
                                        int numViolins = Integer.parseInt(temp[0]);
                                        if (numViolins == 0) {
                                            continue;
                                        }
                                        for (int i = 0; i < 10; i++) {
                                            if (numViolins > Integer.parseInt(entry[i].split(" ")[1])) {
                                                System.arraycopy(entry, i, entry, i + 1, 9 - i);
                                                entry[i] = "<@" + user + ">: " + numViolins + " :violin:\n";
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    entry = new String[]{"null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n", "null#0000 `0`: 0 violins\n"};
                                    List<Member> userlist = e.getGuild().getMembers();
                                    for (Member user : userlist) {
                                        String currentData;
                                        if (user.getUser().isBot()) {
                                            continue;
                                        }
                                        try {
                                            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt"));
                                            currentData = reader.readLine();
                                            reader.close();
                                        } catch (Exception exception) {
                                            //nothing here lol
                                            //entry format: **Position.** user#9999 `userID`: num violins
                                            continue;
                                        }
                                        assert currentData != null;
                                        String[] temp = currentData.split(" ");
                                        int numViolins = Integer.parseInt(temp[0]);
                                        if (numViolins == 0) {
                                            continue;
                                        }
                                        for (int i = 0; i < 10; i++) {
                                            if (numViolins > Integer.parseInt(entry[i].split(" ")[2])) {
                                                System.arraycopy(entry, i, entry, i + 1, 9 - i);
                                                String name = user.getUser().getName().replace(' ', '-');
                                                entry[i] = name + "#" + user.getUser().getDiscriminator() + " `" + user.getId() + "`: " + numViolins + " :violin:\n";
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            StringBuilder board = new StringBuilder();
                            for (int i = 0; i < 10; i++) {
                                if (entry[i].contains("#0000")) {
                                    break;
                                }
                                board.append("**").append(i + 1).append(".** ").append(entry[i]);
                            }
                            EmbedBuilder builder = new EmbedBuilder()
                                    .setColor(Color.BLUE)
                                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                    .setTitle("__**Economy Leaderboard**__");
                            if (isGlobal) {
                                builder.addField("Richest Users in the World", board.toString(), false);
                            } else {
                                builder.addField("Richest Users in " + e.getGuild().getName(), board.toString(), false);
                            }
                            e.getChannel().sendMessage(builder.build()).queue();
                            ranCommand = true;
                        }
                       /* case "streakleaderboard", "streaklb" -> {
                            File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
                            File[] files = directory.listFiles();
                            String[] entry = new String[0];
                            boolean isGlobal = false;
                            if (files != null) {
                                String temp2 = "REEEEE";
                                try {
                                    temp2 = message[1];
                                } catch (Exception exception) {
                                    //nothing here lol
                                }
                                if (temp2.equals("global")) {
                                    isGlobal = true;
                                    entry = new String[]{"<@0>: 0-day Streak\n", "<@0>: 0-day Streak\n", "<@0>: 0-day Streak\n", "<@0>: 0-day Streak\n", "<@0>: 0-day Streak\n", "<@0>: 0-day Streak\n", "<@0>: 0-day Streak\n", "<@0>: 0-day Streak\n", "<@0>: 0-day Streak\n", "<@0>: 0-day Streak\n"};
                                    for (File file : files) {
                                        String currentData = null;
                                        String user;
                                        int pos;
                                        try {
                                            br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                                            currentData = br.readLine();
                                            br.close();
                                        } catch (Exception exception) {
                                            //nothing here lol
                                            //entry format: **Position.** user#9999 `userID`: num violins
                                            continue;
                                        }
                                        user = file.getName();
                                        pos = user.lastIndexOf(".");
                                        user = user.substring(0, pos);
                                        assert currentData != null;
                                        String[] temp = currentData.split(" ");
                                        int numViolins = Integer.parseInt(temp[47]);
                                        if (streak == 0) {
                                            continue;
                                        }
                                        for (int i = 0; i < 10; i++) {
                                            if (numViolins > Integer.parseInt(entry[i].split(" ")[1])) {
                                                System.arraycopy(entry, i, entry, i + 1, 9 - i);
                                                entry[i] = "<@" + user + ">: " + numViolins + "-day Streak\n";
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    entry = new String[]{"null#0000 `0`: 0-day Streak\n", "null#0000 `0`: 0-day Streak\n", "null#0000 `0`: 0-day Streak\n", "null#0000 `0`: 0-day Streak\n", "null#0000 `0`: 0-day Streak\n", "null#0000 `0`: 0-day Streak\n", "null#0000 `0`: 0-day Streak\n", "null#0000 `0`: 0-day Streak\n", "null#0000 `0`: 0-day Streak\n", "null#0000 `0`: 0-day Streak\n"};
                                    List<Member> userlist = e.getGuild().getMembers();
                                    e.getChannel().sendMessage(userlist.size() + "").queue();
                                    for (int i = 0; i < userlist.size(); i ++) {
                                        Member user = userlist.get(i);
                                        e.getChannel().sendMessage(user.getNickname()).queue();
                                        String currentData;
                                        if (user.getUser().isBot()) {
                                            continue;
                                        }
                                        try {
                                            br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt"));
                                            currentData = br.readLine();
                                            br.close();
                                        } catch (Exception exception) {
                                            //nothing here lol
                                            //entry format: **Position.** user#9999 `userID`: num violins
                                            continue;
                                        }
                                        assert currentData != null;
                                        String[] temp = currentData.split(" ");
                                        int numViolins = Integer.parseInt(temp[47]);
                                        if (streak == 0) {
                                            continue;
                                        }
                                        for (int j = 0; j < 10; j++) {
                                            if (numViolins > Integer.parseInt(entry[i].split(" ")[2])) {
                                                System.arraycopy(entry, i, entry, i + 1, 9 - i);
                                                String name = user.getUser().getName().replace(' ', '-');
                                                entry[i] = name + "#" + user.getUser().getDiscriminator() + " `" + user.getId() + "`: " + numViolins + "-day Streak\n";
                                                break;
                                            }
                                        }
                                    }
                                    e.getChannel().sendMessage("for loop exited").queue();
                                }
                                e.getChannel().sendMessage("after global check").queue();
                            }
                            StringBuilder board = new StringBuilder();
                            for (int i = 0; i < 10; i++) {
                                if (entry[i].contains("#0000")) {
                                    break;
                                }
                                board.append("**").append(i + 1).append(".** ").append(entry[i]);
                            }
                            EmbedBuilder builder = new EmbedBuilder()
                                    .setColor(Color.BLUE)
                                    .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                                    .setTitle("__**Economy Leaderboard**__");
                            if (isGlobal) {
                                builder.addField("Longest Daily Streaks in the World", board.toString(), false);
                            } else {
                                builder.addField("Longest Daily Streaks in " + e.getGuild().getName(), board.toString(), false);
                            }
                            e.getChannel().sendMessage("after embed generation").queue();
                            e.getChannel().sendMessage(builder.build()).queue();
                        }*/
                    }
                    if (hasData) {
                        try {
                            writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt")));
                            writer.print(violins + " " + workC + " " + workL + " " + gambleC + " " + gambleL + " " + robC + " " + robL + " " + rehearseC + " " + performC + " " + ownInsurance1 + " " + ownInsurance2 + " " + activeInsurance + " " + hourlyIncome + " " + violinQuality + " " + skillLevel + " " + lessonQuality + " " + stringQuality + " " + bowQuality + " " + hasMath + " " + hasOrchestra + " " + piccolo + " " + flute + " " + oboe + " " + clarinet + " " + bassoon + " " + contrabassoon + " " + horn + " " + trumpet + " " + trombone + " " + tuba + " " + timpani + " " + percussion + " " + first + " " + second + " " + cello + " " + stringBass + " " + piano + " " + harp + " " + soprano + " " + alto + " " + tenor + " " + bass + " " + soloists + " " + hallLevel + " " + conductor + " " + advertising + " " + tickets + " " + streak + " " + dailyC + " " + dailyExp + " " + faster + " " + rice + " " + thirdP + " " + secondP + " " + firstP + " " + medals + " " + extraIncome + " " + extraCommandIncome + " " + higherWinrate + " " + higherRobrate + " " + stealShield + " " + violinDuplicator);
                            writer.close();
                        } catch (Exception exception) {
                            //nothing here lol
                        }
                    }
                }
            }
            //AUTORESPONSE
            if(!ranCommand) {
                List<String> array2 = Collections.singletonList(fullMessage);
                if (fullMessage.equals("no u")) {
                    e.getChannel().sendMessage("no u").queue();
                }
                String[] serverData = null;
                try {
                    reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Server\\" + e.getGuild().getId() + ".txt"));
                    serverData = reader.readLine().split(" ");
                    reader.close();
                } catch (Exception exception) {
                    //nothing here lol
                }
                if (isDev) {
                    switch (fullMessage) {
                        case "hush bot" -> e.getChannel().sendMessage("okay master :(").queue();
                        case "right bot?" -> e.getChannel().sendMessage("yes master").queue();
                        case "bad bot" -> e.getChannel().sendMessage("sowwy master :cry:").queue();
                    }
                }
                if (!e.getChannel().getId().equals("734697507153772604") && !e.getChannel().getId().equals("741709423860514867")) {
                    assert serverData != null;
                    if (serverData[0].equals("true")) {
                        if (fullMessage.contains("viola bad")) {
                            e.getChannel().sendMessage(":clap::clap::clap:").queue();
                        } else if (array2.contains("viola") && !e.getChannel().getId().equals("734697524631175168") && !e.getChannel().getId().equals("740085422453162007")) {
                            if (random.nextDouble() < 0.5) {
                                e.getChannel().sendMessage("Woah there that's a banned instrument.  Carry on and you'll find yourself in some serious trouble.").queue();
                            } else {
                                e.getChannel().sendMessage("At least better than davie504 and his bAss").queue();
                            }
                        } else if (fullMessage.contains("go practice") || array2.contains("practicing")) {
                            e.getChannel().sendMessage("**SPELL YOUR WORDS CORRECTLY**\nThe proper way to spell that word is __practi**s**e__, so do it or else.").queue();
                        } else if (array2.contains("jazz") && !e.getChannel().getId().equals("740085422453162007")) {
                            e.getChannel().sendMessage("Jazz is a sacrilegious style where people deliberately play wrong notes and it should be avoided at all costs.").queue();
                        } else if (fullMessage.contains("pls beg")) {
                            e.getChannel().sendMessage("oh look at <@" + e.getAuthor().getId() + "> begging for coins").queue();
                        } else if (fullMessage.contains("pls settings passive true")) {
                            e.getChannel().sendMessage("hey everyone come here and look at " + e.getAuthor().getName() + " being such a wimp").queue();
                        } else if (array2.contains("ritard")) {
                            e.getChannel().sendMessage("Unless you are referring to the musical term \"ritardando\" then you are in danger").queue();
                        } else if (fullMessage.contains("ben lee")) {
                            e.getChannel().sendMessage("Don't you DARE utter the name of Sacrilegious Boi").queue();
                        } else if (fullMessage.contains("flight of the bumblebee") && !e.getChannel().getId().equals("740085422453162007")) {
                            e.getChannel().sendMessage("That piece is not allowed here unless you're playing at the intended tempo.  You *are* playing it at the intended tempo right?").queue();
                        } else if (array2.contains("song")) {
                            e.getChannel().sendMessage("**IT'S A PIECE NOT A SONG!!!!!**").queue();
                        } else if (fullMessage.contains("vov dylan")) {
                            e.getChannel().sendMessage("Don't you DARE utter the name of Sacrilegious boi #2").queue();
                        } else if (fullMessage.contains("pop music") && !e.getChannel().getId().equals("740085422453162007")) {
                            e.getChannel().sendMessage("Pop music is shitty, don't listen to it.  Instead try the more wholesome Beethoven Symphony No. 9 in D Minor.").queue();
                        } else if (fullMessage.contains("canon in d") && !e.getChannel().getId().equals("740085422453162007")) {
                            e.getChannel().sendMessage("Why don't you try the less overplayed Beethoven Symphony No. 3 in E-Flat Major?").queue();
                        } else if (array2.contains("sax") && !e.getChannel().getId().equals("740085422453162007") || array2.contains("saxophone") && !e.getChannel().getId().equals("740085422453162007")) {
                            e.getChannel().sendMessage("Saxophone isn't an orchestral instrument, why don't you try playing the triangle as a healthy alternative.").queue();
                        } else if (array2.contains("band") && !e.getChannel().getId().equals("740085422453162007")) {
                            e.getChannel().sendMessage("Band is a subset of the full orchestra (and too loud as well) now get out of my face if you don't understand this simple fact.").queue();
                        } else if (array2.contains("fortnite")) {
                            e.getChannel().sendMessage("**Aiyooooo!  How many times do I need to tell you??  __Ling Ling is practising FORTY hours a day!  DO YOU THINK LING LING HAS TIME TO PLAY *FORTNITE*???  *HUH?!?!?!*  LING LING ALREADY WIN TWENTY COMPETITION, YOU PLAY *FORTNITE*?  *HUUUUUH?!?!?!?!?!?!?!?!?!?!*__**").queue();
                        } else if (array2.contains("wheat")) {
                            e.getChannel().sendMessage("Rice is the superior crop, don't you dare start about wheat").queue();
                        } else if (fullMessage.contains("there are only 24 hours in a day")) {
                            e.getChannel().sendMessage("stop making excuses and go practise").queue();
                        } else if (array2.contains("davie") || array2.contains("davie504")) {
                            e.getChannel().sendMessage("go watch the TRUE superior channel at https://www.youtube.com/TwoSetViolin").queue();
                        } else if (array2.contains("bass") && !e.getChannel().getId().equals("740085422453162007") && !fullMessage.contains("double bass") && !fullMessage.contains("string bass") && !fullMessage.contains("upright bass")) {
                            e.getChannel().sendMessage("the only bAss that is allowed here is the double bass, any other form is SACRILEGIOUS").queue();
                        } else if (fullMessage.contains("light mode")) {
                            e.getChannel().sendMessage("light mode is disgusting, go use dark mode").queue();
                        }
                    }
                }
            }
        }

        //V I O L A
        assert data != null;
        if (data[1].equals("true")) {
            if (e.getChannel().getName().contains("announcement")) {
                e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1FB").queue();
                e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1EE").queue();
                e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F4").queue();
                e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F1").queue();
                e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1E6").queue();
            } else {
                if (random.nextDouble() <= 0.025) {
                    try {
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1FB").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1EE").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F4").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1F1").queue();
                        e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+1F1E6").queue();
                    } catch (Exception exception1) {
                        e.getChannel().sendMessage("<@" + e.getAuthor().getName() + "> was probably being a pussy and blocked the bot.  Or I happened to try to react to a deleted message.").queue();
                    }
                }
            }
        }
    }
}