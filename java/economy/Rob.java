package economy;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Random;

public class Rob {
	public static void rob(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data = LoadData.loadData(e);
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
			e.reply("Hey, Brett and Eddy are still looking for you after your last hit!  Wait " + hours + " hours " + minutes + " minutes " + seconds + " seconds " + milliseconds + " milliseconds!").queue();
		} else {
			String user;
			try {
				user = Objects.requireNonNull(e.getOption("target")).getAsString();
			} catch(Exception exception) {
				e.reply("You cannot rob nobody.  That doesn't make sense.").setEphemeral(true).queue();
				return;
			}
			if(e.getUser().getId().equals(user)) {
				e.reply("Why would you rob yourself, are you actually that dumb?").setEphemeral(true).queue();
				return;
			}
			if(e.getUser().getId().equals("733409243222507670")) {
				e.reply("How DARE you attempt to rob me.  I'm fining you 1% of your balance.").queue();
				data.replace("violins", (long) data.get("violins") * 0.99);
				SaveData.saveData(e, data);
				return;
			}
			JSONParser parser = new JSONParser();
			JSONObject targetdata;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + user + ".json")) {
				targetdata = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				e.reply("You did not provide a valid User ID.  Doesn't make sense to rob someone nonexistent, does it?").queue();
				return;
			}
			long violins = (long) data.get("violins");
			long targetViolins = (long) targetdata.get("violins");
			long targetLostToRob = (long) targetdata.get("lostToRob");
			double failChance = (double) violins / (targetViolins + violins + 1) - 0.0025 * (long) data.get("sophistication") + 0.003 * (long) data.get("moreRob");
			double num = random.nextDouble();
			boolean insurance = (boolean) targetdata.get("insurance");
			boolean opponentShield = (boolean) targetdata.get("shield");
			boolean hasDuplicator = (boolean) data.get("duplicator");
			long baseRob = (long) (targetViolins * 0.2);
			String name;
			try {
				name = Objects.requireNonNull(e.getJDA().getUserById(user)).getName();
			} catch(Exception exception) {
				name = "your target";
			}
			boolean DM = (boolean) targetdata.get("DMs");
			boolean extraInfo = (boolean) data.get("extraInfo");
			String message = "";
			if(num < failChance) {
				baseRob = (long) (violins * 0.2);
				if(extraInfo) {
					message += "Brett and Eddy caught you trying to rob " + name + "!  You paid " + name + " `" + Numbers.formatNumber(baseRob) + "`" + Emoji.VIOLINS + " for attempting to rob them.\n*The generator rolled `" + num + "` you needed at least `" + failChance + "` to succeed.*";
				} else {
					message += "Brett and Eddy caught you trying to rob " + name + "!  You paid " + name + " `" + Numbers.formatNumber(baseRob) + "`" + Emoji.VIOLINS + " for attempting to rob them.";
				}
				try {
					if(DM) {
						long finalBaseRob = baseRob;
						Objects.requireNonNull(e.getJDA().getUserById(user)).openPrivateChannel().queue((channel) -> channel.sendMessage("<@" + e.getUser().getId() + "> (" + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + ") tried to rob you but failed!  They paid you `" + Numbers.formatNumber(finalBaseRob) + "`" + Emoji.VIOLINS + " in fines.").queue());
					}
				} catch(Exception exception) {
					//nothing here lol
				}
				targetViolins += baseRob;
				data.replace("violins", (long) data.get("violins") - baseRob);
			} else {
				if(baseRob > 5000000) {
					baseRob = 5000000;
				}
				if(insurance) {
					if(opponentShield) {
						if(extraInfo) {
							message += "You successfully robbed " + name + " but ran into a Steal Shield.  You only managed to get away with `" + Numbers.formatNumber((long) (baseRob * 0.25)) + "`" + Emoji.VIOLINS + " before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.\n*The generator rolled `" + num + "` you needed at least `" + failChance + "` to succeed.*";
						} else {
							message += "You successfully robbed " + name + " but ran into a Steal Shield.  You only managed to get away with `" + Numbers.formatNumber((long) (baseRob * 0.25)) + "`" + Emoji.VIOLINS + " before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.";
						}
						try {
							if(DM) {
								long finalBaseRob1 = baseRob;
								Objects.requireNonNull(e.getJDA().getUserById(user)).openPrivateChannel().queue((channel) -> channel.sendMessage("<@" + e.getUser().getId() + "> (" + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + ") just robbed `" + Numbers.formatNumber((long) (finalBaseRob1 * 0.25)) + "`" + Emoji.VIOLINS + " from you!  Your Ling Ling insurance protected `" + Numbers.formatNumber((long) (finalBaseRob1 * 0.5)) + "`" + Emoji.VIOLINS + " and your Steal Shield protected `" + Numbers.formatNumber((long) (finalBaseRob1 * 0.25)) + "`" + Emoji.VIOLINS).queue());
							}
						} catch(Exception exception) {
							//nothing here lol
						}
						if(hasDuplicator) {
							message += "\nYour violin duplicator doubled your earnings to `" + Numbers.formatNumber((long) (baseRob * 0.5)) + "`" + Emoji.VIOLINS;
							baseRob *= 0.5;
						} else {
							baseRob *= 0.25;
						}
					} else {
						if(extraInfo) {
							message += "You successfully robbed " + name + " but only managed to get away with `" + Numbers.formatNumber((long) (baseRob * 0.5)) + "`" + Emoji.VIOLINS + " before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.\n*The generator rolled `" + num + "` you needed at least `" + failChance + "` to succeed.*";
						} else {
							message += "You successfully robbed " + name + " but only managed to get away with `" + Numbers.formatNumber((long) (baseRob * 0.5)) + "`" + Emoji.VIOLINS + " before Ling Ling Security was called.  You evade capture by being like Ben Lee and faking.";
						}
						try {
							if(DM) {
								long finalBaseRob2 = baseRob;
								Objects.requireNonNull(e.getJDA().getUserById(user)).openPrivateChannel().queue((channel) -> channel.sendMessage("<@" + e.getUser().getId() + "> (" + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + ") just robbed `" + Numbers.formatNumber((long) (finalBaseRob2 * 0.5)) + "`" + Emoji.VIOLINS + " from you!  Your Ling Ling insurance protected `" + Numbers.formatNumber((long) (finalBaseRob2 * 0.5)) + "`" + Emoji.VIOLINS).queue());
							}
						} catch(Exception exception) {
							//nothing here lol
						}
						if(hasDuplicator) {
							message += "\nYour violin duplicator doubled your earnings to`" + Numbers.formatNumber(baseRob) + "`" + Emoji.VIOLINS;
						} else {
							baseRob *= 0.5;
						}
					}
				} else {
					if(opponentShield) {
						if(extraInfo) {
							message += "You successfully robbed " + name + " but a Steal Shield stopped your looting halfway through.  Your payout was `" + Numbers.formatNumber((long) (baseRob * 0.5)) + "`" + Emoji.VIOLINS + "\n*The generator rolled `" + num + "` you needed at least `" + failChance + "` to succeed.*";
						} else {
							message += "You successfully robbed " + name + " but a Steal Shield stopped your looting halfway through.  Your payout was `" + Numbers.formatNumber((long) (baseRob * 0.5)) + "`" + Emoji.VIOLINS;
						}
						try {
							if(DM) {
								long finalBaseRob3 = baseRob;
								Objects.requireNonNull(e.getJDA().getUserById(user)).openPrivateChannel().queue((channel) -> channel.sendMessage("<@" + e.getUser().getId() + "> (" + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + ") just robbed `" + Numbers.formatNumber((long) (finalBaseRob3 * 0.5)) + "`" + Emoji.VIOLINS + " from you!  Your Steal Shield protected `" + Numbers.formatNumber((long) (finalBaseRob3 * 0.5)) + "`" + Emoji.VIOLINS).queue());
							}
						} catch(Exception exception) {
							//nothing here lol
						}
						if(hasDuplicator) {
							message += "\nYour violin duplicator doubled your earnings to `" + Numbers.formatNumber(baseRob) + "`" + Emoji.VIOLINS;
						} else {
							baseRob *= 0.5;
						}
					} else {
						if(extraInfo) {
							message += "You successfully robbed " + name + "!  Your payout was `" + Numbers.formatNumber(baseRob) + "`" + Emoji.VIOLINS + "\n*The generator rolled `" + num + "` you needed at least `" + failChance + "` to succeed.*";
						} else {
							message += "You successfully robbed " + name + "!  Your payout was `" + Numbers.formatNumber(baseRob) + "`" + Emoji.VIOLINS;
						}
						try {
							if(DM) {
								long finalBaseRob4 = baseRob;
								Objects.requireNonNull(e.getJDA().getUserById(user)).openPrivateChannel().queue((channel) -> channel.sendMessage("<@" + e.getUser().getId() + "> (" + e.getUser().getName() + "#" + e.getUser().getDiscriminator() + ") just robbed `" + Numbers.formatNumber(finalBaseRob4) + "`" + Emoji.VIOLINS + " from you!").queue());
							}
						} catch(Exception exception) {
							//nothing here lol
						}
						if(hasDuplicator) {
							message += "\nYour violin duplicator doubled your earnings to `" + Numbers.formatNumber(baseRob * 2) + "`" + Emoji.VIOLINS;
							baseRob *= 2;
						}
					}
				}
				if(hasDuplicator) {
					targetViolins -= baseRob * 0.5;
					targetLostToRob += baseRob * 0.5;
				} else {
					targetViolins -= baseRob;
					targetLostToRob += baseRob;
				}
				Numbers.calculateLoan(data, baseRob);
			}
			targetdata.replace("violins", targetViolins);
			targetdata.replace("lostToRob", targetLostToRob);
			try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + user + ".json")) {
				writer.write(targetdata.toJSONString());
				writer.close();
			} catch(Exception exception) {
				//nothing here lol
			}
			RNGesus.lootbox(e, data);
			data.replace("robCD", time + 57540000);
			data.replace("robbed", (long) data.get("robbed") + baseRob);
			SaveData.saveData(e, data);
			e.reply(message).queue();
		}
	}
}