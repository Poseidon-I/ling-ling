package admin;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class SetPrefix {
	public SetPrefix(MessageReceivedEvent e) {
		char newPrefix = e.getMessage().getContentRaw().split(" ")[1].charAt(0);
		try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Ling Ling Bot Data\\Settings\\Prefixes\\" + e.getGuild().getId() + ".txt")))) {
			writer.write(newPrefix);
			writer.close();
			e.getMessage().reply("The prefix is now `" + newPrefix + "`").mentionRepliedUser(false).queue();
		} catch(Exception exception) {
			try {
				File file = new File("Ling Ling Bot Data\\Settings\\Prefixes\\" + e.getGuild().getId() + ".txt");
				file.createNewFile();
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
				writer.write(newPrefix);
				writer.close();
				e.getMessage().reply("The prefix is now `" + newPrefix + "`").mentionRepliedUser(false).queue();
			} catch(Exception exception1) {
				//nothing here lol
			}
		}
	}
}