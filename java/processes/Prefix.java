package processes;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class Prefix {
	public static char GetPrefix(GuildMessageReceivedEvent e) {
		try(BufferedReader reader = new BufferedReader(new FileReader("Ling Ling Bot Data\\Settings\\Prefixes\\" + e.getGuild().getId() + ".txt"))) {
			char prefix = reader.readLine().charAt(0);
			reader.close();
			return prefix;
		} catch(Exception exception) {
			try {
				File file = new File("Ling Ling Bot Data\\Settings\\Prefixes\\" + e.getGuild().getId() + ".txt");
				file.createNewFile();
				PrintWriter writer = new PrintWriter(file);
				writer.write('!');
				writer.close();
				return '!';
			} catch(Exception exception1) {
				return '!';
			}
		}
	}
}