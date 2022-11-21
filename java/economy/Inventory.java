package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.awt.*;
import java.io.FileReader;
import java.util.Objects;

public class Inventory {
	public static void inventory(@NotNull SlashCommandInteractionEvent e) {
		JSONObject data;
		String user;
		int page;
		try {
			user = Objects.requireNonNull(e.getOption("otheruser")).getAsString();
		} catch(NullPointerException exception) {
			user = e.getUser().getId();
		}
		
		try {
			page = Objects.requireNonNull(e.getOption("page")).getAsInt();
		} catch(NullPointerException exception) {
			e.reply("You have to provide a page number.").setEphemeral(true).queue();
			return;
		}
		
		JSONParser parser = new JSONParser();
		try(FileReader reader = new FileReader("Ling Ling Bot Data\\Economy Data\\" + user + ".json")) {
			data = (JSONObject) parser.parse(reader);
			reader.close();
		} catch(Exception exception) {
			e.reply("This save file does not exist!").queue();
			return;
		}
		
		if(user.equals("768056391814086676")) {
			user = "**NARWHAL**";
		} else {
			try {
				user = Objects.requireNonNull(e.getJDA().getUserById(user)).getName();
			} catch(Exception exception) {
				user = "Someone";
			}
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.decode((String) data.get("color")))
				.setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl());
		switch(page) {
			case 1 -> builder.setTitle(user + "'s Inventory - Page 1\nRaw Materials")
					.addField("Rice Grains " + Emoji.GRAINS, "Count: `" + Numbers.formatNumber(data.get("grains")) + "`\nCrafting Ingredient", true)
					.addField("Plastic " + Emoji.PLASTIC, "Count: `" + Numbers.formatNumber(data.get("plastic")) + "`\nCrafting Ingredient", true)
					.addField("Water " + Emoji.WATER, "Count: `" + Numbers.formatNumber(data.get("water")) + "`\nCrafting Ingredient", true)
					.addField("Tea Base " + Emoji.TEABAG, "Count: `" + Numbers.formatNumber(data.get("teaBase")) + "`\nCrafting Ingredient", true)
					.addBlankField(true)
					.addField("Wood " + Emoji.WOOD, "Count: `" + Numbers.formatNumber(data.get("wood")) + "`\nCrafting Ingredient", true)
					.addField("Pine Sap " + Emoji.SAP, "Count: `" + Numbers.formatNumber(data.get("pineSap")) + "`\nCrafting Ingredient", true)
					.addField("Steel " + Emoji.STEEL, "Count: `" + Numbers.formatNumber(data.get("steel")) + "`\nCrafting Ingredient", true)
					.addField("Horse Hair " + Emoji.HORSE_HAIR, "Count: `" + Numbers.formatNumber(data.get("horseHair")) + "`\nCrafting Ingredient", true);
			case 2 -> builder.setTitle(user + "'s Inventory - Page 2\nConsumables")
					.addField("Rice " + Emoji.RICE, "Count: `" + Numbers.formatNumber(data.get("rice")) + "`\nUsage: Gives you 2 hours of income", true)
					.addField("Bubble Tea " + Emoji.TEA, "Count: `" + Numbers.formatNumber(data.get("tea")) + "`\nUsage: Gives you 6 hours of income", true)
					.addField("Ling Ling Blessing " + Emoji.BLESSING, "Count: `" + Numbers.formatNumber(data.get("blessings")) + "`\nUsage: Gives you 24 hours of income and 1-3 Ling Ling Medals", true)
					.addField("Rosin " + Emoji.ROSIN, "Count: `" + Numbers.formatNumber(data.get("rosin")) + "`\nUsage: Allows you to make 25% of your income for 50 hours.  Stackable", true)
					.addBlankField(true)
					.addField("New Strings " + Emoji.STRING, "Count: `" + Numbers.formatNumber(data.get("string")) + "`\nUsage: Allows you to make 25% of your income for 100 hours.  Stackable", true)
					.addField("Bow Hair " + Emoji.BOW_HAIR, "Count: `" + Numbers.formatNumber(data.get("bowHair")) + "`\nUsage: Allows you to make 25% of your income for 150 hours.  Stackable", true)
					.addBlankField(true)
					.addField("Violin Service " + Emoji.SERVICE, "Count: `" + Numbers.formatNumber(data.get("violinService")) + "`\nUsage: Allows you to make 25% of your income for 250 hours.  Stackable", true);
			case 3 -> builder.setTitle(user + "'s Inventory - Page 3\nLootboxes")
					.addField("Free Box " + Emoji.FREE_BOX, "Count: `" + Numbers.formatNumber(data.get("voteBox")) + "`\nUsage: Gives you bad random items, as decided by RNGesus", true)
					.addField("Gift Box " + Emoji.GIFT_BOX, "Count: `" + Numbers.formatNumber(data.get("giftBox")) + "`\nUsage: Gives you miniscully valuable random items, as decided by RNGesus", true)
					.addField("Musician Kit " + Emoji.MUSICIAN_KIT, "Count: `" + Numbers.formatNumber(data.get("kits")) + "`\nUsage: Gives you somewhat valuable random items, as decided by RNGesus", true)
					.addField("Ling Ling Box " + Emoji.LING_LING_BOX, "Count: `" + Numbers.formatNumber(data.get("linglingBox")) + "`\nUsage: Gives you valuable random items, as decided by RNGesus", true)
					.addField("Crazy Person Box " + Emoji.CRAZY_BOX, "Count: `" + Numbers.formatNumber(data.get("crazyBox")) + "`\nUsage: Gives you very valuable random items, as decided by RNGesus", true)
					.addField("RNGesus Box " + Emoji.RNGESUS_BOX, "Count: `" + Numbers.formatNumber(data.get("RNGesusBox")) + "`\nUsage: Gives you EXTREMELY valuable random items, as decided by RNGesus", true);
		}
		e.replyEmbeds(builder.build()).queue();
	}
}