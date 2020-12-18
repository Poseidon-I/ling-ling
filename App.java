import commands.CommandHub;
import commands.DevCommands;
import commands.Economy;
import commands.Help;
import events.*;
import joinLeave.BotJoin;
import joinLeave.Join;
import joinLeave.Leave;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {
    public static void main(String[] args) throws Exception{
        JDA jda;
        try (BufferedReader rdr = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\token.txt"))) {
            /*jda = JDABuilder.createDefault(rdr.readLine())
                    .enableIntents(GatewayIntent.GUILD_PRESENCES)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .addEventListeners(new PublicAutomod())
                    .addEventListeners(new Join())
                    .addEventListeners(new Leave())
                    .addEventListeners(new Autoroles())
                    .addEventListeners(new Autounrole())
                    .addEventListeners(new CommandHub())
                    .addEventListeners(new Help())
                    .addEventListeners(new Autoresponse())
                    .addEventListeners(new Economy())
                    .addEventListeners(new Luthier())
                    .build();
            jda.awaitReady();*/
            jda = new JDABuilder(rdr.readLine()).build();
        } catch(Exception e) {
            throw new FileNotFoundException();
        }
        
        jda.addEventListener(new PublicAutomod());
        jda.addEventListener(new Join());
        jda.addEventListener(new Leave());
        jda.addEventListener(new Autoroles());
        jda.addEventListener(new Autounrole());
        jda.addEventListener(new CommandHub());
        jda.addEventListener(new Help());
        jda.addEventListener(new Autoresponse());
        jda.addEventListener(new Economy());
        jda.addEventListener(new Luthier());
        jda.addEventListener(new BotJoin());
        jda.addEventListener(new Hourly());
        jda.addEventListener(new DevCommands());
        //jda.addEventListener(new Deleted());
        //jda.addEventListener(new Edited());
    }
}

//serversettings format: autoresponse VIOLA logging automod
//save format: violins workCooldown workLevel gambleCooldown gambleLevel robCooldown robLevel rehearseCooldown performCooldown own1 own2 activeInsurance hourlyIncome violinQuality skillLevel lessonQuality stringQuality bowQuality hasMath
//hasOrchestra, piccolo, flute, oboe, clarinet, bassoon, contrabassoon, horn, trumpet, trombone, tuba, timpani, percussion, first, second, cello, bass, piano, harp, S, A, T, B, soloists, hallLevel, conductor, advertising, ticket, streak, dailyCooldown, dailyExpire

//ex. 100 16000000000 50 16000000000 25 16000000000 15 16000000000 16000000000 true false 1 10000 5 5 5 5 5 true true true 1 1 1 1 true 1 1 1 1 1 1 20 20 15 5 2 true 20 20 20 20 4 5 5 20 5 0 16000000000 16000000000