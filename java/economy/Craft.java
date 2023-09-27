package economy;

import eventListeners.GenericDiscordEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class Craft {
	public static void craft(GenericDiscordEvent e, long craftAmount, String item) {
		JSONObject data = LoadData.loadData(e);
		if(item.isEmpty()) {
			EmbedBuilder builder = new EmbedBuilder().setTitle("**All Crafting Recipes**")
					.addField("**Rice** " + Emoji.RICE, "`" + data.get("grains") + "/20`" + Emoji.GRAINS + "\n`" + data.get("wood") + "/10`" + Emoji.WOOD + "\n`" + data.get("water") + "/10`" + Emoji.WATER, true)
					.addField("**Bubble Tea** " + Emoji.TEA, "`" + data.get("plastic") + "/10`" + Emoji.PLASTIC + "\n`" + data.get("teaBase") + "/10`" + Emoji.TEABAG + "\n`" + data.get("water") + "/20`" + Emoji.WATER, true)
					.addField("**Rosin** " + Emoji.ROSIN, "`" + data.get("pineSap") + "/20`" + Emoji.SAP, true)
					.addField("**New Strings** " + Emoji.STRING, "`" + data.get("steel") + "/40`" + Emoji.STEEL, true)
					.addBlankField(true)
					.addField("**Bow Hair** " + Emoji.BOW_HAIR, "`" + data.get("horseHair") + "/60`" + Emoji.HORSE_HAIR, true)
					.addField("**Violin Service** " + Emoji.SERVICE, "`" + data.get("grains") + "/20`" + Emoji.GRAINS + "\n`" + data.get("plastic") + "/20`" + Emoji.PLASTIC + "\n`" + data.get("water") + "/20`" + Emoji.WATER + "\n`" + data.get("teaBase") + "/20`" + Emoji.TEABAG + "\n`" + data.get("wood") + "/80`" + Emoji.WOOD + "\n`" + data.get("pineSap") + "/20`" + Emoji.SAP + "\n`" + data.get("steel") + "/20`" + Emoji.STEEL + "\n`" + data.get("horseHair") + "/20`" + Emoji.HORSE_HAIR, true)
					.addBlankField(true)
					.addField("**1x Luthier** " + Emoji.SERVICE, "`" + data.get("grains") + "/250`" + Emoji.GRAINS + "\n`" + data.get("plastic") + "/250`" + Emoji.PLASTIC + "\n`" + data.get("water") + "/250`" + Emoji.WATER + "\n`" + data.get("teaBase") + "/250`" + Emoji.TEABAG + "\n`" + data.get("wood") + "/250`" + Emoji.WOOD + "\n`" + data.get("pineSap") + "/250`" + Emoji.SAP + "\n`" + data.get("steel") + "/250`" + Emoji.STEEL + "\n`" + data.get("horseHair") + "/250`" + Emoji.HORSE_HAIR, true);
			e.replyEmbeds(builder.build());
			return;
		}
		if(craftAmount < 1) {
			e.reply("You can't craft a negative amount of items.  Grow a brain.");
			return;
		}
		EmbedBuilder builder = new EmbedBuilder()
				.setColor(Color.decode((String) data.get("color")));
		switch(item) {
			case "rice" -> {
				long grains = (long) data.get("grains");
				long wood = (long) data.get("wood");
				long water = (long) data.get("water");
				long rice = (long) data.get("rice");
				long i = 0;
				for(; i < craftAmount; i++) {
					if(grains < 20 || water < 10 || wood < 10) {
						if(i == 0) {
							e.reply("You were unable to craft any Rice!  Check that you have enough Raw Materials:\n" + Numbers.formatNumber(grains) + "/25`" + Emoji.GRAINS + "\n`" + Numbers.formatNumber(wood) + "/10`" + Emoji.WOOD + "\n`" + Numbers.formatNumber(water) + "/10`" + Emoji.WATER);
							return;
						}
						break;
					}
					grains -= 20;
					water -= 10;
					wood -= 10;
					rice++;
				}
				data.replace("grains", grains);
				data.replace("wood", wood);
				data.replace("water", water);
				data.replace("rice", rice);
				e.reply("You crafted `" + Numbers.formatNumber(i) + "`" + Emoji.RICE + " for `" + Numbers.formatNumber(20 * i) + "`" + Emoji.GRAINS + ", `" + Numbers.formatNumber(10 * i) + "`" + Emoji.WOOD + ", `" + Numbers.formatNumber(10 * i) + "`" + Emoji.WATER);
			}
			case "tea" -> {
				long plastic = (long) data.get("plastic");
				long teaBase = (long) data.get("teaBase");
				long water = (long) data.get("water");
				long tea = (long) data.get("tea");
				long i = 0;
				for(; i < craftAmount; i++) {
					if(plastic < 10 || water < 20 || teaBase < 10) {
						if(i == 0) {
							e.reply("You were unable to craft any Tea!  Check that you have enough Raw Materials:\n" + Numbers.formatNumber(water) + "/20`" + Emoji.WATER + "\n`" + Numbers.formatNumber(plastic) + "/10`" + Emoji.PLASTIC + "\n`" + Numbers.formatNumber(teaBase) + "/10`" + Emoji.TEABAG);
							return;
						}
						break;
					}
					water -= 20;
					teaBase -= 10;
					plastic -= 10;
					tea++;
				}
				data.replace("plastic", plastic);
				data.replace("teaBase", teaBase);
				data.replace("water", water);
				data.replace("tea", tea);
				e.reply("You crafted `" + Numbers.formatNumber(i) + "`" + Emoji.TEA + " for `" + Numbers.formatNumber(10 * i) + "`" + Emoji.TEABAG + ", `" + Numbers.formatNumber(10 * i) + "`" + Emoji.PLASTIC + ", `" + Numbers.formatNumber(20 * i) + "`" + Emoji.WATER);
			}
			case "rosin" -> {
				long pineSap = (long) data.get("pineSap");
				long rosin = (long) data.get("rosin");
				long i = 0;
				for(; i < craftAmount; i++) {
					if(pineSap < 20) {
						if(i == 0) {
							e.reply("You were unable to craft any Rosin!  Check that you have enough Raw Materials:\n" + Numbers.formatNumber(pineSap) + "/20`" + Emoji.SAP);
							return;
						}
						break;
					}
					pineSap -= 20;
					rosin++;
				}
				data.replace("pineSap", pineSap);
				data.replace("rosin", rosin);
				e.reply("You crafted `" + Numbers.formatNumber(i) + "`" + Emoji.ROSIN + " for `" + Numbers.formatNumber(20 * i) + "`" + Emoji.SAP);
			}
			case "strings" -> {
				long steel = (long) data.get("steel");
				long strings = (long) data.get("string");
				long i = 0;
				for(; i < craftAmount; i++) {
					if(steel < 40) {
						if(i == 0) {
							e.reply("You were unable to craft any Strings!  Check that you have enough Raw Materials:\n" + Numbers.formatNumber(steel) + "/40`" + Emoji.STEEL);
							return;
						}
						break;
					}
					steel -= 40;
					strings++;
				}
				data.replace("steel", steel);
				data.replace("string", strings);
				e.reply("You crafted `" + Numbers.formatNumber(i) + "`" + Emoji.STRING + " for `" + Numbers.formatNumber(40 * i) + "`" + Emoji.STEEL);
			}
			case "bowHair" -> {
				long horseHairs = (long) data.get("horseHair");
				long hairs = (long) data.get("bowHair");
				long i = 0;
				for(; i < craftAmount; i++) {
					if(horseHairs < 60) {
						if(i == 0) {
							e.reply("You were unable to craft any Bow Hair!  Check that you have enough Raw Materials:\n" + Numbers.formatNumber(horseHairs) + "/60`" + Emoji.HORSE_HAIR);
							return;
						}
						break;
					}
					horseHairs -= 60;
					hairs++;
				}
				data.replace("horseHair", horseHairs);
				data.replace("bowHair", hairs);
				e.reply("You crafted `" + Numbers.formatNumber(i) + "`" + Emoji.BOW_HAIR + " for `" + Numbers.formatNumber(60 * i) + "`" + Emoji.HORSE_HAIR);
			}
			case "service" -> {
				long grains = (long) data.get("grains");
				long plastic = (long) data.get("plastic");
				long water = (long) data.get("water");
				long teaBase = (long) data.get("teaBase");
				long wood = (long) data.get("wood");
				long pineSap = (long) data.get("pineSap");
				long steel = (long) data.get("steel");
				long horseHairs = (long) data.get("horseHair");
				long services = (long) data.get("violinService");
				long i = 0;
				for(; i < craftAmount; i++) {
					if(grains < 20 || plastic < 20 || water < 20 || teaBase < 20 || wood < 80 || pineSap < 20 || steel < 20 || horseHairs < 20) {
						if(i == 0) {
							e.reply("You were unable to craft any Violin Service!  Check that you have enough Raw Materials:\n" + Numbers.formatNumber(wood) + "/80`" + Emoji.WOOD + "\n`" + Numbers.formatNumber(grains) + "/20`" + Emoji.GRAINS + "\n`" + Numbers.formatNumber(plastic) + "/20`" + Emoji.PLASTIC + "\n`" + Numbers.formatNumber(water) + "/20`" + Emoji.WATER + "\n`" + Numbers.formatNumber(teaBase) + "/20`" + Emoji.TEABAG + "\n`" + Numbers.formatNumber(pineSap) + "/20`" + Emoji.SAP + "\n`" + Numbers.formatNumber(steel) + "/20`" + Emoji.STEEL + "\n`" + Numbers.formatNumber(horseHairs) + "/20`" + Emoji.HORSE_HAIR);
							return;
						}
						break;
					}
					grains -= 20;
					plastic -= 20;
					water -= 20;
					teaBase -= 20;
					wood -= 80;
					pineSap -= 20;
					steel -= 20;
					horseHairs -= 20;
					services++;
				}
				data.replace("grains", grains);
				data.replace("plastic", plastic);
				data.replace("water", water);
				data.replace("teaBase", teaBase);
				data.replace("wood", wood);
				data.replace("pineSap", pineSap);
				data.replace("steel", steel);
				data.replace("horseHair", horseHairs);
				data.replace("violinService", services);
				e.reply("You crafted `" + Numbers.formatNumber(i) + "`" + Emoji.SERVICE + " for `" + Numbers.formatNumber(i * 20) + "`" + Emoji.GRAINS + ", `" + Numbers.formatNumber(i * 20) + "`" + Emoji.PLASTIC + ", `" + Numbers.formatNumber(i * 20) + "`" + Emoji.WATER + ", `" + Numbers.formatNumber(i * 20) + "`" + Emoji.TEABAG + ", `" + Numbers.formatNumber(i * 80) + "`" + Emoji.WOOD + ", `" + Numbers.formatNumber(i * 20) + "`" + Emoji.SAP + ", `" + Numbers.formatNumber(i * 20) + "`" + Emoji.STEEL + ", `" + Numbers.formatNumber(i * 20) + "`" + Emoji.HORSE_HAIR);
			}
			case "luthier" -> {
				if(Objects.requireNonNull(e.getGuild()).getId().equals("670725611207262219")) {
					e.reply("Strad find you trying to sneakily mess with the only buffed Luthier around.  He kicks you out, and slaps you with a fine equal to 24x your hourly income.\nDon't try to increase the Luthier of the support server.  It won't work or end well.");
					data.replace("violins", (long) data.get("violins") - (long) data.get("income") * 24);
					SaveData.saveData(e, data);
					return;
				}
				JSONParser parser = new JSONParser();
				JSONObject luthierData;
				try(FileReader reader = new FileReader("Ling Ling Bot Data\\Settings\\Luthier\\" + Objects.requireNonNull(e.getGuild()).getId() + ".json")) {
					luthierData = (JSONObject) parser.parse(reader);
					reader.close();
				} catch(Exception exception) {
					e.reply("You look for a luthier shop to donate to, but cannot find any.  Try another server!\nAlternatively, ask a Bot Mod/Bot Admin to set up Luthier for you.");
					return;
				}
				long grains = (long) data.get("grains");
				long plastic = (long) data.get("plastic");
				long water = (long) data.get("water");
				long teaBase = (long) data.get("teaBase");
				long wood = (long) data.get("wood");
				long pineSap = (long) data.get("pineSap");
				long steel = (long) data.get("steel");
				long horseHairs = (long) data.get("horseHair");
				long multiplier = (long) luthierData.get("multiplier");
				long i = 0;
				for(; i < craftAmount; i++) {
					if(grains < 250 || plastic < 250 || water < 250 || teaBase < 250 || wood < 250 || pineSap < 250 || steel < 250 || horseHairs < 250) {
						if(i == 0) {
							e.reply("You were unable to craft any Luthiers!  Check that you have enough Raw Materials:\n`" + Numbers.formatNumber(grains) + "/250`" + Emoji.GRAINS + "\n`" + Numbers.formatNumber(plastic) + "/250`" + Emoji.PLASTIC + "\n`" + Numbers.formatNumber(water) + "/250`" + Emoji.WATER + "\n`" + Numbers.formatNumber(teaBase) + "/250`" + Emoji.TEABAG + "\n`" + Numbers.formatNumber(pineSap) + "/250`" + Emoji.SAP + "\n`" + Numbers.formatNumber(wood) + "/250`" + Emoji.WOOD + "\n`" + Numbers.formatNumber(steel) + "/250`" + Emoji.STEEL + "\n`" + Numbers.formatNumber(horseHairs) + "/250`" + Emoji.HORSE_HAIR);
							return;
						}
						break;
					}
					grains -= 250;
					plastic -= 250;
					water -= 250;
					teaBase -= 250;
					wood -= 250;
					pineSap -= 250;
					steel -= 250;
					horseHairs -= 250;
					multiplier++;
				}
				data.replace("grains", grains);
				data.replace("plastic", plastic);
				data.replace("water", water);
				data.replace("teaBase", teaBase);
				data.replace("wood", wood);
				data.replace("pineSap", pineSap);
				data.replace("steel", steel);
				data.replace("horseHair", horseHairs);
				luthierData.replace("multiplier", multiplier);
				e.reply("You crafted and used `" + Numbers.formatNumber(i) + "`x Luthier for `" + Numbers.formatNumber(i * 250) + "`" + Emoji.GRAINS + ", `" + Numbers.formatNumber(i * 250) + "`" + Emoji.PLASTIC + ", `" + Numbers.formatNumber(i * 250) + "`" + Emoji.WATER + ", `" + Numbers.formatNumber(i * 250) + "`" + Emoji.TEABAG + ", `" + Numbers.formatNumber(i * 250) + "`" + Emoji.WOOD + ", `" + Numbers.formatNumber(i * 250) + "`" + Emoji.SAP + ", `" + Numbers.formatNumber(i * 250) + "`" + Emoji.STEEL + ", `" + Numbers.formatNumber(i * 250) + "`" + Emoji.HORSE_HAIR);
				Objects.requireNonNull(e.getGuild().getTextChannelById((String) luthierData.get("channel"))).sendMessage("**:tada: <@" + e.getAuthor().getId() + "> just buffed this server's luthier by `" + i + "`x!  New Multiplier: `" + multiplier + "x`** :tada:");
				try(FileWriter writer = new FileWriter("Ling Ling Bot Data\\Settings\\Luthier\\" + e.getGuild().getId() + ".json")) {
					writer.write(luthierData.toJSONString());
					writer.close();
				} catch(Exception exception) {
					//nothing here lol
				}
			}
			default ->
					e.reply("This crafting recipe does not exist!  Run `/craft` with no arguments to see all recipes.");
		}
		SaveData.saveData(e, data);
	}
}