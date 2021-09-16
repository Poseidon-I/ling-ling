package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;

public class LoadData {
	public static String[] loadData(GuildMessageReceivedEvent e, String folder) {
		String[] data;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\" + folder + "\\" + e.getAuthor().getId() + ".txt"));
			data = reader.readLine().split(" ");
			reader.close();
		} catch(Exception exception) {
			e.getChannel().sendMessage("You don't have a save file!  Run `!start` to get a profile!\n\n**USE THIS SERVER'S PREFIX INSTEAD OF THE DEFAULT**").queue();
			throw new IllegalArgumentException();
		}
		return data;
	}
}