package processes;

import eventListeners.Disconnect;
import eventListeners.Receiver;
import eventListeners.RoleAdded;
import eventListeners.RoleRemoved;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StartBot {
    public StartBot() {
        File file = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\loadedservers.txt");
        try {
            file.delete();
            file.createNewFile();
        } catch (Exception exception1) {
            //nothing here lol
        }
        JDA jda;
        try (BufferedReader rdr = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\token.txt"))) {
            jda = JDABuilder.createDefault(rdr.readLine(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGE_REACTIONS)
                    .disableCache(CacheFlag.ACTIVITY, CacheFlag.VOICE_STATE, CacheFlag.EMOTE, CacheFlag.CLIENT_STATUS, CacheFlag.ONLINE_STATUS)
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .addEventListeners(new Disconnect())
                    .addEventListeners(new Receiver())
                    .addEventListeners(new RoleAdded())
                    .addEventListeners(new RoleRemoved())
                    .useSharding(0, 1)
                    .build();
            jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
            jda.getPresence().setActivity(Activity.playing("violin forty hours a day."));
        } catch(Exception exception) {
            //nothing here lol
        }
    }
}