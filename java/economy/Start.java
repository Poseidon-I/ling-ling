package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Start {
	public Start(GuildMessageReceivedEvent e, char prefix) {
		File file = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + e.getAuthor().getId() + ".txt");
		File file2 = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Bank Data\\" + e.getAuthor().getId() + ".txt");
		try {
			file.createNewFile();
			file2.createNewFile();
		} catch(Exception exception) {
			e.getChannel().sendMessage("You already have a save, don't try to outsmart me").queue();
			throw new IllegalArgumentException();
		}
		try {
			PrintWriter newData = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
			newData.print("0 0 0 0 0 0 0 0 0 false 0 0 0 0 0 0 0 0 false false false 0 0 0 0 false 0 0 0 0 0 0 1 1 0 0 0 false 0 0 0 0 0 0 0 0 0 0 false false false 0 0 0 0 0 0 0 0 0 false false 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 false 0 0 0 0 false false 0 0 0 0 0 0 0 0 0 false false false 1");
			newData.close();
			newData = new PrintWriter(new BufferedWriter(new FileWriter(file2.getAbsolutePath())));
			newData.print("0 0 false 0 false");
			newData.close();
			e.getChannel().sendMessage("Your profile has been created!  Run `" + prefix + "help 3` for a list of economy commands!").queue();
		} catch(Exception exception) {
			e.getChannel().sendMessage("Something went wrong creating the file.  Run `" + prefix + "support` for a link to the support server to get in contact with the developer.").queue();
		}
	}
}