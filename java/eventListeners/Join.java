package eventListeners;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Join extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        if(e.getGuild().getId().equals("670725611207262219")) {
            TextChannel channel = e.getGuild().getTextChannelById("734697493828206662");
            assert channel != null;
            int members = e.getGuild().getMemberCount() - 10;
            channel.sendMessage("Hello <@" + e.getMember().getId() + ">, I am Ling Ling.  Welcome to " + e.getGuild().getName() + "!\n\nPlease take your time to read through the full <#734697490661638155> and react to the message at the bottom of the channel to access the server.\n\nCheck out <#734697501059448892> to get yourself some color and ping roles!\n*Member " + members + "*").queue();
        }
    }
}