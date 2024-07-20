package eventListeners;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
// BEETHOVEN-ONLY CLASS

public class Join extends ListenerAdapter {
	public void onGuildMemberJoin(GuildMemberJoinEvent e) {
		TextChannel channel = e.getGuild().getTextChannelById("734697493828206662");
		assert channel != null;
		int members = e.getGuild().getMemberCount() - 5;
		channel.sendMessage("Hello <@" + e.getMember().getId() + ">, I am Beethoven.  Welcome to the support server for Ling Ling!" +
				"\n\nPlease take your time to read through the <#734697490661638155> and react to the message at the bottom of the channel to access the server." +
				"\n\nCheck out <#734697501059448892> to get yourself some color and ping roles!\n*Member " + members + "*").queue();
	}
}