import processes.StartBot;

public class App {
    public static void main(String[] args) {
        new StartBot();
    }
}

//bot helpers: blaze
//bot admins: tbd
//bot owners/developers: me, jacqueline

//helpers can warn users, give/take violins and items, and view full data.  helpers are basically bot mods and are selected through an extensive application process
//admins can take all actions helpers can, in addition to (later) banning users, edit raw data, and reset saves.  admins are handpicked
//yall know what owners/devs do, they are the only ones who can abuse their power :)

//serversettings format: autoresponse VIOLA leveling

//levelsettings format: minXP, maxXP, cooldown (sec)
//channelblacklist format: channelID, channelID, channelID, etc.
//rolerewards format: level, role\nlevel, role\nlevel, role\n etc.

// [0] violins workCooldown workLevel gambleCooldown gambleLevel robCooldown robLevel rehearseCooldown performCooldown own1 own2 activeInsurance
// [12] hourlyIncome violinQuality skillLevel lessonQuality stringQuality bowQuality hasMath
// [19] hasOrchestra, piccolo, flute, oboe, clarinet, bassoon, contrabassoon, horn, trumpet, trombone, tuba, timpani, percussion, first, second, cello, bass, piano, harp, S, A, T, B, soloists
// [43] hallLevel, conductor, advertising, ticket, streak, dailyCooldown, dailyExpire, faster
// [51] rice, thirdP, secondP, firstP, medals, extraIncome, extraCommandIncome, higherWinrate, higherRobrate, stealShield, violinDuplicator, tea, blessing, scaleCooldown, realIncome
// [66] netGambleWinnings, millionDrawn, robEarnings, amountLost, scales, hoursPractised, rehearsals, performances, maxDailyStreak, violinsEarned, violinsSpentOnUpgrades, luthiersUnscrambled, hoursTaught
// [79] hasCertificate, teachCooldown, teacherTraining, teachingStudio

//ex. 100 16000000000 50 16000000000 25 16000000000 15 16000000000 16000000000 true false 1 10000 5 5 5 5 5 true true true 1 1 1 1 true 1 1 1 1 1 1 20 20 15 5 2 true 20 20 20 20 4 5 5 20 5 0 16000000000 16000000000 true 0 3 2 1 10 true true false true true false 0 0 16000000000 10000 0 0 0 0 0 0.0 0 0 0 0 0 0 0