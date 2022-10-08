package processes;

import economy.Emoji;
import economy.RNGesus;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Random;

public class Luthier {
	public static void luthier(@NotNull SlashCommandInteractionEvent e, JSONObject data) {
		try {
			Random random = new Random();
			if(!(boolean) data.get("hasWord")) {
				double chance = (double) data.get("chance");
				if(random.nextDouble() <= chance && !e.getChannel().getId().equals(data.get("channel").toString())) {
					TextChannel channel = Objects.requireNonNull(e.getGuild()).getTextChannelById(data.get("channel").toString());
					String[] words = {"pianississimo", "fortississimo", "lento", "fortepiano", "a tempo", "allargando", "piu mosso", "meno mosso", "arpeggio", "chromatic", "counterpoint", "basso continuo", "staccato", "legato", "accent", "violin", "piano", "cello", "bass", "flute", "piccolo", "clarinet", "debussy", "ravel", "prokofiev", "stravinsky", "oboe", "bassoon", "trumpet", "french horn", "trombone", "tuba", "percussion", "orchestra", "liszt", "conductor", "piece", "bach", "vivaldi", "adagio", "handel", "mozart", "haydn", "beethoven", "chopin", "paganini", "mendelssohn", "rachmaninoff", "tchaikovsky", "sibelius", "mahler", "eroica", "pastoral", "ode to joy", "schubert", "schumann", "symphony", "concerto", "sacrilegious", "brett", "eddy", "twoset", "practice", "ling ling", "concertmaster", "espressivo", "meno", "molto", "pesante", "piu", "poco", "scherzando", "sempre", "senza", "subito", "sostenuto", "tenuto", "ray chen", "hilary hahn", "lamentable", "amazing", "interesting", "r/lingling40hrs", "jacqueline", "stradivarius", "guarneri", "rubato", "con brio", "dolce", "sacrinterestinglegious", "repertoire", "caprice", "col legno", "pianissimo", "forte", "sonatina", "clementi", "czerny", "exposition", "development", "recapitulation", "rondo", "polyphony", "homophony", "brahms", "fortissimo", "crescendo", "andantino", "diminuendo", "allegretto", "turn", "ritardando", "accelerando", "waltz", "clair de lune", "prelude", "partita", "sonata", "fugue", "baroque", "classical", "romantic", "larghissimo", "mordent", "largo", "andante", "moderato", "grave", "allegro", "opus", "tre corde", "una corda", "con sordino", "senza sord", "vivace", "presto", "prestissimo", "appoggiatura", "breve", "semibreve", "minim", "crotchet", "quaver", "semiquaver", "demisemiquaver", "hemidemisemiquaver", "fermata", "tonic", "supertonic", "mediant", "subdominant", "dominant", "submediant", "leading tone", "major scale", "natural minor scale", "harmonic minor scale", "melodic minor scale", "major triad", "minor triad", "augmented triad", "devil's tritone", "interval", "major seventh", "dominant seventh", "minor seventh", "half diminished seventh", "diminished seventh", "suspended chord"};
					String word = words[random.nextInt(words.length)];
					String original = word;
					long money = 0;
					for(int i = 0; i < word.length(); i++) {
						money += random.nextInt(100) + 101;
					}
					money *= (long) data.get("multiplier");
					char[] scrambler = new char[word.length()];
					int i = 0;
					StringBuilder send = new StringBuilder().append(original);
					while(send.toString().equals(original)) {
						while(word.length() > 0) {
							int num = random.nextInt(word.length());
							StringBuilder newWord = new StringBuilder();
							char temp = word.charAt(num);
							scrambler[i] = temp;
							for(int j = 0; j < word.length(); j++) {
								if(j != num) {
									newWord.append(word.charAt(j));
								}
							}
							word = newWord.toString();
							i++;
						}
						send = new StringBuilder();
						for(i = 0; i < scrambler.length; i++) {
							send.append(scrambler[i]);
						}
					}
					assert channel != null;
					channel.sendMessage("Olaf is giving away violins!  Unscramble `" + send + "` to get " + Numbers.formatNumber(money) + Emoji.VIOLINS).queue();
					data.replace("hasWord", true);
					data.replace("amount", money);
					data.replace("word", original);
					try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".json")) {
						writer.write(data.toJSONString());
						writer.close();
					} catch(Exception exception) {
						//nothing here lol
					}
				}
			} else if((boolean) data.get("hasWord") && !e.getUser().isBot() && e.getChannel().getId().equals(data.get("channel").toString()) && e.getName().equals("answer")) {
				String target = data.get("word").toString();
				long gain = (long) data.get("amount");
				JSONObject userData;
				try {
					if(Objects.requireNonNull(e.getOption("guess")).getAsString().equalsIgnoreCase(target)) {
						JSONParser parser = new JSONParser();
						try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + e.getUser().getId() + ".json")) {
							userData = (JSONObject) parser.parse(reader);
							reader.close();
						} catch(Exception exception) {
							e.reply("You don't even have a profile, where would you store your violins???  Run `/start` **in a bot command channel** to get one!").mentionRepliedUser(false).queue();
							return;
						}
						String name = e.getUser().getName();
						if(name.contains("@everyone") || name.contains("@here") || name.contains("<@&") || name.contains("nigg")) {
							name = "A user who thought they were trying to be funny";
						}
						userData.replace("violins", (long) userData.get("violins") + gain);
						userData.replace("earnings", (long) userData.get("earnings") + gain);
						userData.replace("luthiers", (long) userData.get("luthiers") + 1);
						e.reply("**" + name + "** unscrambled `" + target + "` and gained " + Numbers.formatNumber(gain) + Emoji.VIOLINS).mentionRepliedUser(false).queue();
						RNGesus.lootbox(e, userData);
						try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + e.getUser().getId() + ".json")) {
							writer.write(userData.toJSONString());
							writer.close();
						} catch(Exception exception) {
							exception.printStackTrace();
							//nothing here lol
						}
						data.replace("hasWord", false);
						data.replace("word", "blank");
						try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + Objects.requireNonNull(e.getGuild()).getId() + ".json")) {
							writer.write(data.toJSONString());
							writer.close();
						} catch(Exception exception) {
							exception.printStackTrace();
							//nothing here lol
						}
					} else {
						e.reply("Wrong answer!").setEphemeral(true).queue();
					}
				} catch(Exception exception) {
					e.reply("You didn't even give an answer!").setEphemeral(true).queue();
				}
			}
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
}
