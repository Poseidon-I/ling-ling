package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;
import processes.WindowsExplorerStringComparator;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Objects;

public class Market {
	private static EmbedBuilder builder;
	private static JSONObject data;
	private static SlashCommandInteractionEvent e;
	private static String emoji;
	
	public static void itemSwitch(String input) {
		switch(input) {
			case "grains" -> emoji = Emoji.GRAINS;
			case "horseHair" -> emoji = Emoji.HORSE_HAIR;
			case "pineSap" -> emoji = Emoji.SAP;
			case "plastic" -> emoji = Emoji.PLASTIC;
			case "steel" -> emoji = Emoji.STEEL;
			case "teaBase" -> emoji = Emoji.TEABAG;
			case "water" -> emoji = Emoji.WATER;
			case "wood" -> emoji = Emoji.WOOD;
			case "none" -> emoji = "";
			default -> {
				e.reply("There are only 8 options: `grains` `horseHair` `pineSap` `plastic` `steel` `teaBase` `water` `wood`\nThey are case sensitive because it's easier to code that way, sorry not sorry.").setEphemeral(true).queue();
				emoji = "ERROR";
			}
		}
	}
	
	public static void showItemInfo(String item) {
		String[] files = new File("Ling Ling Bot Data\\Market\\" + item).list();
		builder.setTitle("Information for " + item + " " + emoji);
		if(files == null || files.length == 0) {
			builder.addField("No items found!", "Unfortunately, nobody is selling this item.  Check back later!", false);
		} else {
			Arrays.sort(files, new WindowsExplorerStringComparator());
			StringBuilder line = new StringBuilder();
			for(int i = 0; i < Math.min(10, files.length); i++) {
				String name = files[i];
				// price count ID
				String[] nameSplit = name.substring(0, name.lastIndexOf('.')).split(" ");
				line.append('`').append(Numbers.formatNumber(Long.parseLong(nameSplit[1]))).append("x` for `").append(Numbers.formatNumber(Long.parseLong(nameSplit[0]))).append("`" + Emoji.VIOLINS + "\n");
			}
			if(files.length > 10) {
				line.append("+`").append(files.length - 10).append("` more");
			}
			builder.addField("Best Sell Offers", line.toString(), false);
		}
		e.replyEmbeds(builder.build()).queue();
	}
	
	public static void showAllInfo() {
		builder.setTitle("Information for All Items");
		File[] files = new File("Ling Ling Bot Data\\Market").listFiles();
		assert files != null;
		for(File file : files) {
			itemSwitch(file.getName());
			String[] names = file.list();
			if(names == null || names.length == 0) {
				builder.addField(file.getName(), "No Sell Offers!", true);
			} else {
				Arrays.sort(names, new WindowsExplorerStringComparator());
				String[] fileName = names[0].split(" ");
				builder.addField(file.getName() + " " + emoji, "Price: `" + Numbers.formatNumber(Long.parseLong(fileName[0])) + "`" + Emoji.VIOLINS, true);
			}
		}
		e.replyEmbeds(builder.build()).queue();
	}
	
	public static void buy(String item) {
		JSONParser parser = new JSONParser();
		long amount;
		try {
			amount = Objects.requireNonNull(e.getOption("amount")).getAsLong();
		} catch(Exception exception) {
			amount = 1;
		}
		String[] files = new File("Ling Ling Bot Data\\Market\\" + item).list();
		if(files == null || files.length == 0) {
			e.reply("Nobody is selling this item!").queue();
			return;
		}
		Arrays.sort(files, new WindowsExplorerStringComparator());
		long paid = 0;
		long gained = 0;
		long violins = (long) data.get("violins");
		boolean ranOutOfOrders = false;
		boolean ranOutOfViolins = false;
		for(int i = 0; amount > 0; i++) {
			if(i == files.length) {
				ranOutOfOrders = true;
				break;
			}
			String realFileName = files[i];
			File file = new File("Ling Ling Bot Data\\Market\\" + item + "\\" + realFileName);
			String[] fileName = realFileName.split(" ");
			long offerAmount = Long.parseLong(fileName[1]);
			long offerPrice = Long.parseLong(fileName[0]);
			String offererID = fileName[2].substring(0, fileName[2].lastIndexOf('.'));
			JSONObject tempData;
			try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + offererID + ".json")) {
				tempData = (JSONObject) parser.parse(reader);
				reader.close();
			} catch(Exception exception) {
				exception.printStackTrace();
				continue;
			}
			if(amount < offerAmount) {
				long price = amount * offerPrice;
				if(price > violins) {
					ranOutOfViolins = true;
					break;
				}
				offerAmount -= amount;
				gained += amount;
				paid += price;
				file.renameTo(new File("Ling Ling Bot Data\\Market\\" + item + "\\" + offerPrice + " " + offerAmount + " " + offererID + ".txt"));
				if((boolean) tempData.get("DMs")) {
					long finalAmount = amount;
					Objects.requireNonNull(e.getJDA().getUserById(offererID)).openPrivateChannel().queue((channel) -> channel.sendMessage("Someone just purchased `" + Numbers.formatNumber(finalAmount) + "`" + emoji + " at `" + Numbers.formatNumber(offerPrice) + "`" + Emoji.VIOLINS + " each.  You made `" + Numbers.formatNumber(offerPrice * finalAmount) + "`" + Emoji.VIOLINS + "!").queue());
				}
				tempData.replace("violins", (long) tempData.get("violins") + amount * offerPrice);
				amount = 0;
			} else {
				long price = offerAmount * offerPrice;
				if(price > violins) {
					ranOutOfViolins = true;
					break;
				}
				amount -= offerAmount;
				gained += offerAmount;
				paid += price;
				file.delete();
				if((boolean) tempData.get("DMs")) {
					long finalOfferAmount = offerAmount;
					Objects.requireNonNull(e.getJDA().getUserById(offererID)).openPrivateChannel().queue((channel) -> channel.sendMessage("Someone just purchased `" + Numbers.formatNumber(finalOfferAmount) + "x` " + item + " at `" + Numbers.formatNumber(offerPrice) + "`" + Emoji.VIOLINS + " each.  You made `" + Numbers.formatNumber(finalOfferAmount * offerPrice) + "`" + Emoji.VIOLINS + "!").queue());
				}
				tempData.replace("violins", (long) tempData.get("violins") + offerAmount * offerPrice);
			}
			try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Economy Data\\" + offererID + ".json")) {
				writer.write(tempData.toJSONString());
				writer.close();
			} catch(Exception exception) {
				// nothing here lmao
			}
		}
		data.replace("violins", (long) data.get("violins") - paid);
		data.replace(item, (long) data.get(item) + gained);
		if(ranOutOfOrders) {
			e.reply("You purchased `" + gained + "`" + emoji + " for `" + Numbers.formatNumber(paid) + "`" + Emoji.VIOLINS + "\nAverage price paid: `" + Numbers.formatNumber(paid / gained) + "`" + Emoji.VIOLINS + "\n*you kind of bought out everything...*").queue();
		} else if(ranOutOfViolins) {
			e.reply("You purchased `" + gained + "`" + emoji + " for `" + Numbers.formatNumber(paid) + "`" + Emoji.VIOLINS + "\nAverage price paid: `" + Numbers.formatNumber(paid / gained)+ "`" + Emoji.VIOLINS + "\n*you ran out of violins though...*").queue();
		} else {
			e.reply("You purchased `" + gained + "`" + emoji + " for `" + Numbers.formatNumber(paid) + "`" + Emoji.VIOLINS + "\nAverage price paid: `" + Numbers.formatNumber(paid / gained) + "`" + Emoji.VIOLINS).queue();
		}
		SaveData.saveData(e, data);
	}
	
	public static void sell(String item) {
		long userAmount = (long) data.get(item);
		long amount;
		try {
			amount = Objects.requireNonNull(e.getOption("amount")).getAsLong();
			if(amount <= 0) {
				e.reply("You cannot sell a negative amount of items, don't try to fool me.").setEphemeral(true).queue();
				return;
			}
			if(amount > userAmount) {
				e.reply("You cannot sell more items than you have, dummy.").setEphemeral(true).queue();
				return;
			}
		} catch(Exception exception) {
			amount = 1;
		}
		long price;
		try {
			price = Objects.requireNonNull(e.getOption("price")).getAsLong();
			if(price <= 0) {
				e.reply("You cannot sell items for a negative amount of violins, I know that scamming trick.").setEphemeral(true).queue();
				return;
			}
		} catch(Exception exception) {
			String[] files = new File("Ling Ling Bot Data\\Market\\" + item).list();
			if(files == null || files.length == 0) {
				e.reply("Nobody is selling this item!  Be the one to set the price!").queue();
				return;
			}
			Arrays.sort(files, new WindowsExplorerStringComparator());
			price = Long.parseLong(files[0].split(" ")[0]) - 1;
		}
		File file = new File("Ling Ling Bot Data\\Market\\" + item + "\\" + price + " " + amount + " " + e.getUser().getId() + ".txt");
		try {
			file.createNewFile();
		} catch(Exception exception) {
			file.renameTo(new File("Ling Ling Bot Data\\Market\\" + item + "\\" + price + " " + (amount + file.getName().split(" ")[1]) + " " + e.getUser().getId() + ".txt"));
		}
		data.replace(item, (long) data.get(item) - amount);
		e.reply("You set up a Sell Offer for `" + Numbers.formatNumber(amount) + "`" + emoji + " at `" + Numbers.formatNumber(price) + "`" + Emoji.VIOLINS + " per!").queue();
		SaveData.saveData(e, data);
	}
	
	public static void viewOffers() {
		try {
			File[] directories = new File("Ling Ling Bot Data\\Market").listFiles();
			assert directories != null;
			for(File file : directories) {
				String[] orders = file.list();
				if(orders == null || orders.length == 0) {
					builder.addField(file.getName() + " " + emoji, "No offers!", true);
					continue;
				}
				itemSwitch(file.getName());
				StringBuilder stringBuilder = new StringBuilder();
				for(String name : orders) {
					String[] nameArray = name.substring(0, name.lastIndexOf('.')).split(" ");
					if(nameArray[2].equals(e.getUser().getId())) {
						stringBuilder.append("`").append(Numbers.formatNumber(Long.parseLong(nameArray[1]))).append("`").append(emoji).append(" for `").append(Numbers.formatNumber(Long.parseLong(nameArray[0]))).append("`" + Emoji.VIOLINS + " per").append("\n");
					}
				}
				if(stringBuilder.isEmpty()) {
					stringBuilder.append("No offers!");
				}
				builder.addField(file.getName() + emoji, stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString(), true);
			}
			builder.setTitle("Your Sell Offers");
			e.replyEmbeds(builder.build()).queue();
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public static void market(SlashCommandInteractionEvent event) {
		e = event;
		data = LoadData.loadData(e);
		String item;
		try {
			item = Objects.requireNonNull(e.getOption("item")).getAsString();
		} catch(Exception exception) {
			item = "none";
		}
		itemSwitch(item);
		if(emoji.equals("ERROR")) {
			return;
		}
		builder = new EmbedBuilder()
				.setColor(Color.decode((String) data.get("color")))
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
		try {
			switch(Objects.requireNonNull(e.getOption("action")).getAsString()) {
				// Viewing an item will return the lowest price for that specific item, how many items are in stock, and the 5 best offers.
				// In the future, more statistics may be included in the detailed view.
				// Viewing in general will only return the lowest price for items.
				case "view" -> {
					if(item.equals("none")) {
						showAllInfo();
					} else {
						showItemInfo(item);
					}
				}
				
				// Buying requires the Item field.  Not providing an Amount defaults to 1 item.
				// Buy will automatically take the cheapest offers.
				case "buy" -> {
					if(emoji.equals("")) {
						e.reply("You have to tell me what item you want to buy.").setEphemeral(true).queue();
					} else {
						buy(item);
					}
				}
				
				// Sell requires the Item field.  Not providing an Amount defaults to 1 item.
				// Not providing a Price will automatically put up your offer for 1 violin lower than the lowest offer.
				case "sell" -> {
					if(emoji.equals("")) {
						e.reply("You have to tell me what item you want to sell.").setEphemeral(true).queue();
					} else {
						sell(item);
					}
				}
				case "offers" -> viewOffers();
				default ->
						e.reply("You only have four options: `view` `buy` `sell` `offers`").setEphemeral(true).queue();
			}
		} catch(Exception exception) {
			e.reply("You must provide an action: `view` `buy` `sell` `offers`").setEphemeral(true).queue();
		}
	}
}