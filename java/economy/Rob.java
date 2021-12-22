package economy;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Random;

public class Rob {
	public Rob(GuildMessageReceivedEvent e) {
		JSONObject data = LoadData.loadData(e);
		String[] message = e.getMessage().getContentRaw().split(" ");
		long time = System.currentTimeMillis();
		Random random = new Random();
		if(time < (long) data.get("robCD")) {
			long milliseconds = (long) data.get("robCD") - time;
			long hours = milliseconds / 3600000;
			milliseconds -= hours * 3600000;
			long minutes = milliseconds / 60000;
			milliseconds -= minutes * 60000;
			long seconds = milliseconds / 1000;
			milliseconds -= seconds * 1000;
			e.getChannel().sendMessage("Hey, Brett and Eddy are still looking for you after your last hit!  Wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
		} else {
			String user;
			try {
				user = message[1];
			} catch(Exception exception) {
				e.getChannel().sendMessage("You cannot rob nobody.  That doesn't make sense.").queue();
				throw new IllegalArgumentException();
			}
			if(e.getAuthor().getId().equals(user)) {
				e.getChannel().sendMessage("Why would you rob yourself, are you actually that dumb?").queue();
				throw new IllegalArgumentException();
			}
			JSONParser parser = new JSONParser();
			JSONObject targetdata;
			try (FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + user + ".json")) {
				targetdata = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				e.getChannel().sendMessage("You did not provide a valid User ID.  Doesn't make sense to rob someone nonexistant, does it?").queue();
				throw new IllegalArgumentException();
			}
			long violins = (long) data.get("violins");
			long targetViolins = (long) targetdata.get("violins");
			long targetLostToRob = (long) targetdata.get("lostToRob");
			double failChance = (double) violins / (targetViolins + violins);
			failChance -= 0.0025 * (long) data.get("sophistication") + 0.003 * (long) data.get("moreRob");
			double num = random.nextDouble();
			boolean insurance = (boolean) targetdata.get("insurance");
			boolean opponentShield = (boolean) targetdata.get("shield");
			long baseRob = (long) (targetViolins * 0.2);
			long robEarnings = (long) data.get("robbed");
			String name;
			try {
				name = Objects.requireNonNull(e.getJDA().getUserById(user)).getName();
			} catch(Exception exception) {
				name = "your target";
			}
			if(num < failChance) {
				baseRob = (long) (violins * 0.2);
				e.getChannel().sendMessage("Brett and Eddy caught you trying to rob " + name + "!  You paid " + name + " " + baseRob + ":violin: for attempting to rob them.\n*The generator rolled " + num + ", you need at least " + failChance + " to succeed.*").queue();
				try {
					Objects.requireNonNull(e.getJDA().getUserById(user)).openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") tried to rob you but failed!  They paid you " + baseRob + ":violin: in fines.").queue();
				} catch(Exception exception) {
					//nothing here lol
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
							Objects.requireNonNull(e.getJDA().getUserById(user)).openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (baseRob * 0.25) + ":violin: from you!  Your Ling Ling insurance protected " + (long) (baseRob * 0.5) + ":violin: and your Steal Shield protected " + (long) (baseRob * 0.25) + ":violin:").queue();
						} catch(Exception exception) {
							//nothing here lol
						}
						targetViolins -= baseRob * 0.25;
						if((boolean) data.get("duplicator")) {
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
							Objects.requireNonNull(e.getJDA().getUserById(user)).openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (baseRob * 0.5) + ":violin: from you!  Your Ling Ling insurance protected " + (long) (baseRob * 0.5) + ":violin:").queue();
						} catch(Exception exception) {
							//nothing here lol
						}
						targetViolins -= baseRob * 0.5;
						if((boolean) data.get("duplicator")) {
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
							Objects.requireNonNull(e.getJDA().getUserById(user)).openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + (long) (baseRob * 0.5) + ":violin: from you!  Your Steal Shield protected " + (long) (baseRob * 0.5) + ":violin:").queue();
						} catch(Exception exception) {
							//nothing here lol
						}
						targetViolins -= baseRob * 0.5;
						if((boolean) data.get("duplicator")) {
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
							Objects.requireNonNull(e.getJDA().getUserById(user)).openPrivateChannel().complete().sendMessage("<@" + e.getAuthor().getId() + "> (" + e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ") just robbed " + baseRob + ":violin: from you!").queue();
						} catch(Exception exception) {
							//nothing here lol
						}
						targetViolins -= baseRob;
						if((boolean) data.get("duplicator")) {
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
			targetdata.replace("violins", targetViolins);
			targetdata.replace("lostToRob", targetLostToRob);
			data.replace("robCD", time + 57540000);
			try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + user + ".json")) {
				writer.write(targetdata.toJSONString());
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
			data.replace("violins", violins);
			data.replace("robbed", robEarnings);
			new SaveData(e, data);
		}
	}
}