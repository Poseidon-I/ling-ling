package eventListeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Receiver extends ListenerAdapter {
	public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
		if(/*!e.getChannel().getId().equals("867617298918670366") && !e.getChannel().getId().equals("733437327225127074")*/true) {
			String[] message = e.getMessage().getContentRaw().toLowerCase().split(" ");
			boolean isDev = e.getAuthor().getId().equals("619989388109152256") || e.getAuthor().getId().equals("488487157372157962") || e.getAuthor().getId().equals("706933826193981612");
			
			//LOAD SERVER MEMBERS ONLY ONCE
			try {
				BufferedReader reader = new BufferedReader(new FileReader("Ling Ling Bot Data\\loadedservers.txt"));
				String line = reader.readLine();
				reader.close();
				try {
					List<String> loaded = Arrays.asList(line.split(" "));
					if(!loaded.contains(e.getGuild().getId()) || e.getJDA().getSelfUser().getId().equals("772582345944334356")) {
						e.getGuild().loadMembers();
						line += " " + e.getGuild().getId();
					}
				} catch(Exception exception) {
					e.getGuild().loadMembers();
					line = e.getGuild().getId();
				}
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Ling Ling Bot Data\\loadedservers.txt")));
				writer.print(line);
				writer.close();
			} catch(Exception exception1) {
				//nothing here lol
			}
			
			//HOURLY
			long time = 0;
			try {
				BufferedReader reader = new BufferedReader(new FileReader("Ling Ling Bot Data\\hourly.txt"));
				time = Long.parseLong(reader.readLine());
				reader.close();
			} catch(Exception exception) {
				//nothing here lol
			}
			if(System.currentTimeMillis() > time) {
				new HourlyIncome();
				
				//RESET COOLDOWNS BOUND TO 12AM UTC
				if(time % 86400000 == 0) {
					new ResetDaily(e);
				}
				
				//BANK SHIT
				if(time % 259200000 == 0) {
					new InterestPenalty();
					Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessage("Loan penalties applied; Interest awarded!").queue();
				}
				
				time += 3600000;
				Objects.requireNonNull(Objects.requireNonNull(e.getJDA().getGuildById("670725611207262219")).getTextChannelById("863135059712409632")).sendMessage("Hourly Incomes Sent!").queue();
				try {
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Ling Ling Bot Data\\hourly.txt")));
					writer.print(time);
					writer.close();
				} catch(Exception exception) {
					//nothing here lol
				}
			}
			//LUTHIER
			if(!e.getAuthor().isBot()) {
				JSONParser parser = new JSONParser();
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".json")) {
					new Luthier(e, (JSONObject) parser.parse(reader));
					reader.close();
				} catch(Exception exception) {
					//nothing here lol
				}
				
				//AUTORESPONDER
				if(isDev) {
					String string = e.getMessage().getContentRaw();
					if(string.equalsIgnoreCase("bad bot")) {
						e.getChannel().sendMessage("sowwy strad").queue();
					} else if(string.equalsIgnoreCase("good bot")) {
						e.getChannel().sendMessage("senkyoo strad").queue();
					} else if(string.equalsIgnoreCase("right bot?")) {
						e.getChannel().sendMessage("yes strad").queue();
					}
				}
				
				new DevCommands(e, isDev);
				
				//ALL COMMANDS
				char prefix = Prefix.GetPrefix(e);
				if(e.getMessage().getContentRaw().equals("!prefix") || e.getMessage().getContentRaw().equals("<@733409243222507670>")) {
					e.getChannel().sendMessage("Hello!  My prefix in this server is `" + prefix + "`\nIf you have other issues, run `" + prefix + "support` to get an invite to the support server!").queue();
				}
				try {
					if(message[0].charAt(0) == prefix) {
						if(e.getMessage().getContentRaw().contains("@everyone") || e.getMessage().getContentRaw().contains("@here") || e.getMessage().getContentRaw().contains("<@&")) {
							e.getChannel().sendMessage("why the hell did you ping here, everyone, or a role").queue();
							throw new IllegalArgumentException();
						} else if(e.getAuthor().getName().contains("@everyone") || e.getAuthor().getName().contains("@here") || e.getAuthor().getName().contains("<@&") || e.getAuthor().getName().contains("nigg")) {
							e.getChannel().sendMessage("no using the bot unless you have a good name :)").queue();
							throw new IllegalArgumentException();
						} else {
							message[0] = message[0].substring(1);
							new RegularCommands(e, message, isDev);
						}
					}
				} catch(Exception exception) {
					//nothing here lol
				}
			}
		}
	}
}