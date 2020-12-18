package events;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Random;

public class Luthier extends ListenerAdapter {
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        BufferedReader reader;
        PrintWriter writer;
        String data = null;
        // read file, if false, set to a list
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt"));
            data = reader.readLine();
            reader.close();
        } catch(Exception exception) {
            //nothing here lol
        }
        String[] arr;
        try {
            assert data != null;
            arr = data.split(" ");
            if(arr[3].equals("false")) {
                Random random = new Random();
                double chance = Double.parseDouble(arr[2]);
                if(random.nextDouble() <= chance && !e.getChannel().getId().equals(arr[0])) {
                    TextChannel channel = e.getGuild().getTextChannelById(arr[0]);
                    String[] words = {"violin", "piano", "cello", "bass", "flute", "piccolo", "clarinet", "oboe", "bassoon", "trumpet", "french horn", "trombone", "tuba", "percussion", "orchestra", "conductor", "piece", "bach", "vivaldi", "handel", "mozart", "haydn", "beethoven", "chopin", "paganini", "mendelssohn", "rachmaninoff", "tchaikovsky", "sibelius", "mahler", "eroica", "pastoral", "ode to joy", "symphony", "concerto", "sacrilegious", "brett", "eddy", "twoset", "practice", "ling ling", "concertmaster", "ray chen", "hilary hahn", "lamentable", "amazing", "interesting", "r/lingling40hrs", "jacqueline", "stradivarius", "guarneri", "sacrinterestinglegious", "repertoire", "caprice", "col legno", "pianissimo", "forte", "fortissimo", "crescendo", "diminuendo", "ritardando", "accelerando", "waltz", "clair de lune", "prelude", "partita", "sonata", "fugue", "baroque", "classical", "romantic", "larghissimo", "largo", "andante", "moderato", "grave", "allegro", "vivace", "presto", "prestissimo", "appoggiatura", "breve", "semibreve", "minim", "crotchet", "quaver", "semiquaver", "demisemiquaver", "hemidemisemiquaver"};
                    String word = words[random.nextInt(words.length)];
                    String original = word;
                    int money = (random.nextInt(1000) + 1) * Integer.parseInt(arr[1]);
                    String write = arr[0] + " " + arr[1] + " " + arr[2] + " true " + word + " " + money;
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
                    channel.sendMessage("Olaf is giving away violins!  Unscramble `" + send + "` to get " + money + " violins!").queue();
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt")));
                        writer.print(write);
                        writer.close();
                    } catch(Exception exception) {
                        //nothing here lol
                    }
                }
            } else if(arr[3].equals("true") && !e.getAuthor().isBot() && e.getChannel().getId().equals(arr[0])) {
                StringBuilder target = new StringBuilder();
                for (int i = 4; i < arr.length - 1; i++) {
                    target.append(arr[i]).append(" ");
                }
                target.deleteCharAt(target.length() - 1);
                int gain = Integer.parseInt(arr[arr.length - 1]);
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
                    int violins = Integer.parseInt(user.split(" ")[0]);
                    e.getChannel().sendMessage("**" + e.getAuthor().getName() + "** unscrambled `" + target + "` and gained " + gain + " violins!").queue();
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
                        writer.print(arr[0] + " " + arr[1] + " " + arr[2] + " false blank 0");
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
    }
}