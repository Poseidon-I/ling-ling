package joinLeave;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class Leave extends ListenerAdapter {
    public void onGuildMemberRemove(GuildMemberRemoveEvent e) {
        if (e.getGuild().getId().equals("670725611207262219")) {
            TextChannel channel = e.getGuild().getTextChannelById("734697493828206662");
            assert channel != null;
            int members = e.getGuild().getMemberCount() - 12;
            channel.sendMessage("<@" + Objects.requireNonNull(e.getMember()).getId() + "> just left the server.  I guess they can be considered a VIOLIST now.\n*" + members + " members remain good musicians*").queue();
        }
    }
}