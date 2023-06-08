package eventListeners;

import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
// BEETHOVEN-ONLY CLASS

public class Autounrole extends ListenerAdapter {
	public void onMessageReactionRemove(MessageReactionRemoveEvent e) {
		if(e.getChannel().getId().equals("734697501059448892") && !Objects.requireNonNull(e.getUser()).isBot()) {
			if(e.getReaction().getEmoji().toString().contains("U+1f7e5")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697401176162304"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f7e7")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697401805176903"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f7e8")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697402539311104"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f7e9")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697403063730177"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f7e6")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697403612921908"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+2b1c")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697404359508009"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f7ea")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697405030596620"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f36c")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697406213652500"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f3bb")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697414677626890"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f3b9")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697415348846592"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f62b")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697415550042174"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f914")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697416598618172"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f9bb")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697417227894925"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f3ba")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697417978675220"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f941")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697418746232833"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f3a4")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697419501207582"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f389")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697425595531285"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f4e3")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697426216026223"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f916")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697426635718667"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f4ca")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("747954053660540928"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f4a9")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("750876814842527754"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f468")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("846978680370757683"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f469")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("846978812335489104"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f9ea")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("863660610830729216"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f9d1")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("868958859690115113"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f4a1")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("930189721685094491"))).queue();
			} else if(e.getReaction().getEmoji().toString().contains("U+1f480")) {
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("934618203152347187"))).queue();
			} else if(e.getReaction().getEmoji().toString().equals("CustomEmoji:twoset(852784233819013150")) { //twoset
				e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), Objects.requireNonNull(e.getGuild().getRoleById("734697423703769129"))).queue();
			}
		}
	}
}