package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;

public class Gift {
	public Gift(GuildMessageReceivedEvent e) {
		String[] data = LoadData.loadData(e, "Economy Data");
		if(data[49].equals("true")) {
			e.getChannel().sendMessage("I appreciate your generosity, but I can't let you give away too much.  Wait until 00:00 UTC!").queue();
		} else {
			String target;
			try {
				target = e.getMessage().getMentionedUsers().get(0).getId();
			} catch(Exception exception) {
				try {
					target = e.getMessage().getContentRaw().split(" ")[1];
				} catch(Exception exception1) {
					e.getChannel().sendMessage("You have to gift someone for this to work.").queue();
					throw new IllegalArgumentException();
				}
			}
			assert target != null;
			if(target.equals(e.getAuthor().getId())) {
				e.getChannel().sendMessage("Hey you greedy mf!  Don't gift yourself!").queue();
			} else {
				String[] targetdata;
				try {
					BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
					targetdata = reader.readLine().split(" ");
					reader.close();
				} catch(Exception exception) {
					e.getChannel().sendMessage("You did not provide a valid User ID.  Doesn't make sense to gift someone nonexistant, does it?").queue();
					throw new IllegalArgumentException();
				}
				data[85] = String.valueOf(Long.parseLong(data[85]) + 1);
				targetdata[86] = String.valueOf(Long.parseLong(targetdata[86]) + 1);
				targetdata[87] = String.valueOf(Long.parseLong(targetdata[87]) + 1);
				data[49] = "true";
				new SaveData(e, data, "Economy Data");
				try {
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt")));
					writer.print(targetdata[0]);
					for(int i = 1; i < targetdata.length; i++) {
						writer.print(" " + targetdata[i]);
					}
					writer.close();
				} catch(Exception exception) {
					//nothing here lol
				}
				e.getChannel().sendMessage("Successfully gifted 1 Gift Box to <@" + target + ">").queue();
			}
		}
	}
}