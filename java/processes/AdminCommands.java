package processes;

import admin.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class AdminCommands {
    public AdminCommands(GuildMessageReceivedEvent e, String[] message, char prefix) {
        BufferedReader reader;
        PrintWriter writer;
        switch(message[0]) {
            case "prefix" -> new Prefix(e);
            case "serversettings" -> new ServerSettings(e, prefix);
            case "levelsettings" -> new LevelSettings(e, prefix);
            case "setlevel" -> new SetLevel(e);
            case "rolerewards" -> new RoleRewards(e);
        }
    }
}