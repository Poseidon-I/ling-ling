package eventListeners;

import net.dv8tion.jda.api.events.guild.invite.GuildInviteCreateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class NoInviteLinks extends ListenerAdapter {
	public void onGuildInviteCreate(GuildInviteCreateEvent e) {
		Objects.requireNonNull(e.getJDA().getUserById(Objects.requireNonNull(e.getInvite().getInviter()).getId())).openPrivateChannel().complete().sendMessage("You cannot create new invite links.  Please use the main invite link: https://discord.gg/gNfPwa8").queue();
		e.getInvite().delete().queue();
	}
}