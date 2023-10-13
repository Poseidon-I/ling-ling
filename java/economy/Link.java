package economy;

import eventListeners.GenericDiscordEvent;

public class Link {
	public static void link(GenericDiscordEvent e) {
		e.reply("This command is currently disabled and will be back in 3-5 business days.");
	/*
		JSONObject data = LoadData.loadData(e);
		if(!data.get("mcIGN").equals("")) {
			e.reply("You have already linked an account!  Account name: `" + data.get("mcIGN") + "`");
			return;
		}
		String name;
		try {
			name = Objects.requireNonNull(e.getOption("ign")).getAsString();
		} catch(Exception exception) {
			e.reply("You must provide your minecraft name.").setEphemeral(true).queue();
			return;
		}
		e.reply("Please click the button below to confirm that you want to link `" + name + "` to this Ling Ling profile.")
				.addActionRow(Button.primary("mc", "Confirm " + name))
				.setEphemeral(true)
				.queue();*/
	}
}