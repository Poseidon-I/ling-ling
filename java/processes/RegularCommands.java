package processes;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import regular.*;

import java.util.Objects;

public class RegularCommands {
    public RegularCommands(GuildMessageReceivedEvent e, String[] message, char prefix, boolean isDev, String target, String targetPing, String[] serverSettings) {
        boolean ranCommand = true;
        switch (message[0]) {
            case "help" -> new Help(e, prefix);
            case "faq" -> new FAQ(e, prefix);
            case "code" -> e.getChannel().sendMessage("The open source GitHub page is at <https://github.com/Poseidon-I/ling-ling>").queue();
            case "support" -> e.getChannel().sendMessage("Join the support server at discord.gg/gNfPwa8").queue();
            case "checkdm" -> {
                e.getChannel().sendMessage("**OI <@" + target + ">, " + e.getAuthor().getName() + " WANTS YOU TO CHECK YOUR DMS.  DO IT NOW OR ELSE.**").queue();
                try {
                    User send = e.getJDA().getUserById(target);
                    assert send != null;
                    send.openPrivateChannel().complete().sendMessage("**OI <@" + target + ">, " + e.getAuthor().getName() + " WANTS YOU TO CHECK YOUR DMS.  DO IT NOW OR ELSE.**").queue();
                } catch (Exception exception) {
                    e.getChannel().sendMessage("Either the recipient was being a n00b and didn't have their DMs open or you are smol brane and forgot to mention a user or you mentioned a bot.").queue();
                }
            }
            case "kill" -> new Kill(e, targetPing);
            case "joke" -> new Joke(e);
            case "poll" -> new Poll(e);
            case "emojify" -> new Emojify(e);
            case "invite" -> e.getChannel().sendMessage("You can add the bot to your server using the below link:\n<https://discord.com/api/oauth2/authorize?client_id=733409243222507670&permissions=268725312&scope=bot>").queue();
            case "vote" -> e.getChannel().sendMessage("You can vote for the bot here: <https://top.gg/bot/733409243222507670/vote>.\nYou can vote for the support server here to get a 10% command boost in the server: <https://top.gg/servers/670725611207262219/vote>").queue();
            case "rules" -> e.getChannel().sendMessage("```ini\n[ LING LING BOT RULES ]```\n1. Do not spam commands, spam autoresponse triggers, excessively ping users, or send messages to trigger luthier or any sort of action that may cause the bot to crash.  This is punishable by warn and up to a save reset.\n\n2. Do not abuse bugs or exploits.  If a bug/exploit is found, **IMMEDIATELY** report it to @Stradivarius Violin#6156.  Any instance of bug/exploit abuse can warrant an immediate save reset, and in some cases or for repeat offenders, a permanent bot ban.\n\n3. Some parts of the bot were written to poke fun at others.  However, if taken too far, you will be punished based on how severe your actions were.\n\n4. Read the #update-log before bothering anyone as to why something changed or why the bot is offline.\n\n***All bot mods are allowed to take bot moderation actions in any server and can give users bot warnings even if the action was not against the server rules.***").queue();
            default -> ranCommand = false;
        }

        if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.ADMINISTRATOR) && !ranCommand || isDev && !ranCommand) {
            ranCommand = AdminCommands.ACommands(e, message, prefix);
        }
        if(serverSettings[2].equals("true") && !ranCommand) {
            ranCommand = LevelCommands.LCommands(e, message);
        }
        if(!ranCommand) {
            new Economy(e, message, prefix);
        }
    }
}