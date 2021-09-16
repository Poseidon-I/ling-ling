package dev;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;

public class SetPermLevel {
	public SetPermLevel(GuildMessageReceivedEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		String target = message[1];
		int newRank = Integer.parseInt(message[2]);
		if(newRank >= 0 && newRank <= 2) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt"));
				String[] data = reader.readLine().split(" ");
				reader.close();
				data[65] = String.valueOf(newRank);
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + target + ".txt")));
				writer.print(data[0]);
				for(int i = 1; i < data.length; i++) {
					writer.print(" " + data[i]);
				}
				writer.close();
				e.getChannel().sendMessage("Set the permission level for <@" + target + "> to `" + newRank + "`").queue();
			} catch(Exception exception) {
				e.getChannel().sendMessage("Invalid user provided!").queue();
			}
		} else {
			e.getChannel().sendMessage("Invalid level provided!\n\nValid levels:\n`0`: No Permissions\n`1`: Mod Permissions\n`2`: Admin Permissions").queue();
		}
	}
}