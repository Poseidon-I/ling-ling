package economy;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.Objects;
import java.util.Random;

public class Rob {
	public Rob(GuildMessageReceivedEvent e) {
		String[] data = LoadData.loadData(e, "Economy Data");
		String[] message = e.getMessage().getContentRaw().split(" ");
		long time = System.currentTimeMillis();
		Random random = new Random();
		if(time < Long.parseLong(data[5])) {
			long milliseconds = Long.parseLong(data[5]) - time;
			long hours = milliseconds / 3600000;
			milliseconds -= hours * 3600000;
			long minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			long seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			e.getChannel().sendMessage("Hey, Brett and Eddy are still looking for you after your last hit!  Wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
		} else {
			User user;
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(message[1]));
			} catch(Exception exception) {
				e.getChannel().sendMessage("You cannot rob nobody.  That doesn't make sense.").queue();
				throw new IllegalArgumentException();
			}
			if(e.getAuthor().equals(user)) {
				e.getChannel().sendMessage("Why would you rob yourself, are you actually that dumb?").queue();
				throw new IllegalArgumentException();
			}
			String[] targetdata;
			try {
				BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt"));
				targetdata = reader.readLine().split(" ");
				reader.close();
			} catch(Exception exception) {
				e.getChannel().sendMessage("You did not provide a valid User ID.  Doesn't make sense to rob someone nonexistant, does it?").queue();
				throw new IllegalArgumentException();
			}
			long violins = Long.parseLong(data[0]);
			long targetViolins = Long.parseLong(targetdata[0]);
			long targetLostToRob = Long.parseLong(targetdata[69]);
			long extraChance = Long.parseLong(data[59]);
			double failChance = (double) violins / (targetViolins + violins);
			failChance -= 0.004 * (Long.parseLong(data[6]) + extraChance);
			double num = random.nextDouble();
			boolean insurance = Boolean.parseBoolean(targetdata[9]);
			boolean opponentShield = Boolean.parseBoolean(targetdata[60]);
			long baseRob = (long) (targetViolins * 0.2);
			String name = user.getName();
			long robEarnings = Long.parseLong(data[68]);
			if(num < failChance) {
				baseRob = (long) (violins * 0.2);
				e.getChannel().sendMessage("Brett and Eddy caught you trying to rob " + name + "!  You paid " + name + " " + baseRob + ":violin: for attempting to rob them.\n*The generator rolled " + num + ", you need at least " + failChance + " to succeed.*").queue();
				try {
					user.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") tried to rob you but failed!  They paid you " + baseRob + ":violin: in fines.").queue();
				} catch(Exception exception) {
					e.getChannel().sendMessage(name + " had their DMs closed and got fined 1:violin: as a resource-wasting fee.").queue();
					targetViolins--;
				}
				targetViolins += baseRob;
				violins -= baseRob;
			} else {
				if(baseRob > 5000000) {
					baseRob = 5000000;
				}
				if(insurance) {
					if(opponentShield) {
						e.getChannel().sendMessage("You successfully robbed " + name + " but ran into a Steal Shield.  You only managed to get away with " + (long) (baseRob * 0.25) + ":violin: before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
						try {
							user.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (baseRob * 0.25) + ":violin: from you!  Your Ling Ling insurance protected " + (long) (baseRob * 0.5) + ":violin: and your Steal Shield protected " + (long) (baseRob * 0.25) + ":violin:").queue();
						} catch(Exception exception) {
							e.getChannel().sendMessage(name + " had their DMs closed and got fined 1:violin: as a resource-wasting fee.").queue();
							targetViolins--;
						}
						targetViolins -= baseRob * 0.25;
						if(Boolean.parseBoolean(data[61])) {
							e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
							violins += baseRob * 0.5;
							robEarnings += baseRob * 0.5;
						} else {
							violins += baseRob * 0.25;
							robEarnings += baseRob * 0.25;
						}
						targetLostToRob += baseRob * 0.25;
					} else {
						e.getChannel().sendMessage("You successfully robbed " + name + " but only managed to get away with " + (long) (baseRob * 0.5) + ":violin: before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
						try {
							user.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (baseRob * 0.5) + ":violin: from you!  Your Ling Ling insurance protected " + (long) (baseRob * 0.5) + ":violin:").queue();
						} catch(Exception exception) {
							e.getChannel().sendMessage(name + " had their DMs closed and got fined 1:violin: as a resource-wasting fee.").queue();
							targetViolins--;
						}
						targetViolins -= baseRob * 0.5;
						if(Boolean.parseBoolean(data[61])) {
							e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
							violins += baseRob;
							robEarnings += baseRob;
						} else {
							violins += baseRob * 0.5;
							robEarnings += baseRob * 0.5;
						}
						targetLostToRob += baseRob * 0.5;
					}
				} else {
					if(opponentShield) {
						e.getChannel().sendMessage("You successfully robbed " + name + " but a Steal Shield stopped your looting halfway through.  Your payout was " + (long) (baseRob * 0.5) + ":violin:\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
						try {
							user.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (baseRob * 0.5) + ":violin: from you!  Your Steal Shield protected " + (long) (baseRob * 0.5) + ":violin:").queue();
						} catch(Exception exception) {
							e.getChannel().sendMessage(name + " had their DMs closed and got fined 1:violin: as a resource-wasting fee.").queue();
							targetViolins--;
						}
						targetViolins -= baseRob * 0.5;
						if(Boolean.parseBoolean(data[61])) {
							e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
							violins += baseRob;
							robEarnings += baseRob;
						} else {
							violins += baseRob * 0.5;
							robEarnings += baseRob * 0.5;
						}
						targetLostToRob += baseRob * 0.5;
					} else {
						e.getChannel().sendMessage("You successfully robbed " + name + "!  Your payout was " + baseRob + ":violin:\n*The generator rolled " + num + " you needed at least " + failChance + " to succeed.*").queue();
						try {
							user.openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + baseRob + ":violin: from you!").queue();
						} catch(Exception exception) {
							e.getChannel().sendMessage(name + " had their DMs closed and got fined 1:violin: as a resource-wasting fee.").queue();
							targetViolins--;
						}
						targetViolins -= baseRob;
						if(Boolean.parseBoolean(data[61])) {
							e.getChannel().sendMessage("Your violin duplicator doubled your earnings.").queue();
							violins += baseRob * 2;
							robEarnings += baseRob * 2;
						} else {
							violins += baseRob;
							robEarnings += baseRob;
						}
						targetLostToRob += baseRob;
					}
				}
			}
			data[5] = String.valueOf(time + 57540000);
			try {
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data\\" + user.getId() + ".txt")));
				writer.print(targetViolins);
				for(int i = 1; i < targetdata.length; i++) {
					if(i != 69) {
						writer.print(" " + targetdata[i]);
					} else {
						writer.print(" " + targetLostToRob);
					}
				}
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
			data[0] = String.valueOf(violins);
			data[68] = String.valueOf(robEarnings);
			new SaveData(e, data, "Economy Data");
		}
	}
}