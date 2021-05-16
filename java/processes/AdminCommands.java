package processes;

import admin.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class AdminCommands {
    public static boolean ACommands(GuildMessageReceivedEvent e, String[] message, char prefix) {
        boolean ranCommand = true;
        switch(message[0]) {
            case "prefix" -> new Prefix(e);
            case "serversettings" -> new ServerSettings(e, prefix);
            case "levelsettings" -> new LevelSettings(e, prefix);
            case "setlevel" -> new SetLevel(e);
            case "rolerewards" -> new RoleRewards(e);
            default -> ranCommand = false;
        }
        return ranCommand;
    }
}