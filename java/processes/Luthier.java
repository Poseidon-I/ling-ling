package processes;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.Random;

public class Luthier {
    public Luthier(GuildMessageReceivedEvent e, String[] data) {
        BufferedReader reader;
        PrintWriter writer;
        Random random = new Random();
        try {
            if(data[3].equals("false")) {
                double chance = Double.parseDouble(data[2]);
                if(random.nextDouble() <= chance && !e.getChannel().getId().equals(data[0])) {
                    TextChannel channel = e.getGuild().getTextChannelById(data[0]);
                    String[] words = {"pianississimo", "fortississimo", "lento", "fortepiano", "a tempo", "allargando", "piu mosso", "meno mosso", "arpeggio", "chromatic", "counterpoint", "basso continuo", "staccato", "legato", "accent", "violin", "piano", "cello", "bass", "flute", "piccolo", "clarinet", "debussy", "ravel", "prokofiev", "stravinsky", "oboe", "bassoon", "trumpet", "french horn", "trombone", "tuba", "percussion", "orchestra", "liszt", "conductor", "piece", "bach", "vivaldi", "adagio", "handel", "mozart", "haydn", "beethoven", "chopin", "paganini", "mendelssohn", "rachmaninoff", "tchaikovsky", "sibelius", "mahler", "eroica", "pastoral", "ode to joy", "schubert", "schumann", "symphony", "concerto", "sacrilegious", "brett", "eddy", "twoset", "practice", "ling ling", "concertmaster", "espressivo", "meno", "molto", "pesante", "piu", "poco", "scherzando", "sempre", "senza", "subito", "sostenuto", "tenuto", "ray chen", "hilary hahn", "lamentable", "amazing", "interesting", "r/lingling40hrs", "jacqueline", "stradivarius", "guarneri", "rubato", "con brio", "dolce", "sacrinterestinglegious", "repertoire", "caprice", "col legno", "pianissimo", "forte", "sonatina", "clementi", "czerny", "exposition", "development", "recapitulation", "rondo", "polyphony", "homophony", "brahms", "fortissimo", "crescendo", "andantino", "diminuendo", "allegretto", "turn", "ritardando", "accelerando", "waltz", "clair de lune", "prelude", "partita", "sonata", "fugue", "baroque", "classical", "romantic", "larghissimo", "mordent", "largo", "andante", "moderato", "grave", "allegro", "opus", "tre corde", "una corda", "con sordino", "senza sord", "vivace", "presto", "prestissimo", "appoggiatura", "breve", "semibreve", "minim", "crotchet", "quaver", "semiquaver", "demisemiquaver", "hemidemisemiquaver", "fermata", "tonic", "supertonic", "mediant", "subdominant", "dominant", "submediant", "leading tone", "major scale", "natural minor scale", "harmonic minor scale", "melodic minor scale", "major triad", "minor triad", "augmented triad", "devil's tritone", "interval", "major seventh", "dominant seventh", "minor seventh", "half diminished seventh", "diminished seventh", "suspended chord"};
                    String word = words[random.nextInt(words.length)];
                    String original = word;
                    long money = 0;
                    for(int i = 0; i < word.length(); i ++) {
                        money += random.nextInt(200) + 1;
                    }
                    money *= Long.parseLong(data[1]);
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
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt")));
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
                long gain = Long.parseLong(data[data.length - 1]);
                String[] user;
                if(e.getMessage().getContentRaw().toLowerCase().equals(target.toString())) {
                    try {
                        reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt."));
                        user = reader.readLine().split(" ");
                        reader.close();
                    } catch (Exception exception) {
                        e.getChannel().sendMessage("You don't even have a profile, where would you store your violins???  Run `!start` **in a bot command channel** to get one!\n\n**PLEASE REMEMBER TO USE THIS SERVER'S PREFIX INSTEAD OF `!`**").queue();
                        throw new IllegalArgumentException();
                    }
                    long violins = Long.parseLong(user[0]);
                    e.getChannel().sendMessage("**" + e.getAuthor().getName() + "** unscrambled `" + target + "` and gained " + gain + ":violin:").queue();
                    violins += gain;
                    StringBuilder newData = new StringBuilder("" + violins);
                    for(int i = 1; i < user.length; i ++) {
                        if(i != 75 && i != 77) {
                            newData.append(" ").append(user[i]);
                        } else if(i == 75) {
                            newData.append(" ").append((Long.parseLong(user[75]) + gain));
                        } else {
                            newData.append(" ").append((Long.parseLong(user[77]) + 1));
                        }
                    }
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt")));
                        writer.print(newData.toString());
                        writer.close();
                        writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".txt")));
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
    }
}
