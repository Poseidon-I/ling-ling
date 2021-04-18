package processes;

import eventListeners.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StartBot {
    public StartBot() {
        File file = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\loadedservers.txt");
        try {
            file.delete();
            file.createNewFile();
        } catch (Exception exception1) {
            //nothing here lol
        }
        JDA jda;
        try (BufferedReader rdr = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\token.txt"))) {
            jda = JDABuilder.createDefault(rdr.readLine())
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .addEventListeners(new Autoroles())
                    .addEventListeners(new Autounrole())
                    .addEventListeners(new Disconnect())
                    .addEventListeners(new Join())
                    .addEventListeners(new Leave())
                    .addEventListeners(new Receiver())
                    .addEventListeners(new BotJoin())
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .build();
            jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
            jda.getPresence().setActivity(Activity.playing("violin forty hours a day."));
        } catch(Exception exception) {
            //nothing here lol
        }
    }
}