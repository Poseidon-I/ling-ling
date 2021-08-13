import processes.StartBot;

public class App {
    public static void main(String[] args) {
        new StartBot();
    }
}

//helpers can warn users, give/take violins and items, and view full data.  helpers are basically bot mods and are selected through an extensive application process
//admins can take all actions helpers can, in addition to banning users, edit raw data, and reset saves.  admins are handpicked
//yall know what owners/devs do, they are the only ones who can abuse their power :)

// [0] violins workCooldown workLevel gambleCooldown gambleLevel robCooldown robLevel rehearseCooldown performCooldown own1 own2 activeInsurance
// [12] hourlyIncome violinQuality skillLevel lessonQuality stringQuality bowQuality hasMath
// [19] hasOrchestra, piccolo, flute, oboe, clarinet, bassoon, contrabassoon, horn, trumpet, trombone, tuba, timpani, percussion
// [32] first, second, cello, bass, piano, harp, S, A, T, B, soloists
// [43] hallLevel, conductor, advertising, ticket, streak, hasHadDailyToday, hasGiftedToday, faster
// [51] rice, thirdP, secondP, firstP, medals, extraIncome, extraCommandIncome, higherMulti, higherRobrate, stealShield, violinDuplicator, tea, blessing, scaleCooldown, permLevel
// [66] netGambleWinnings, millionDrawn, robEarnings, amountLost, scales, hoursPractised, rehearsals, performances, maxDailyStreak, violinsEarned, hoursTaught, luthiersUnscrambled
// [78] hasCertificate, teachCooldown, teacherTraining, students, lessonCharge, hasStudio, longerLessons
// [85] giftsGiven, giftsReceived, numGiftBoxes, numVotes, voteCooldown, numVoteBoxes
// [91] numKits, numLingLingBoxes, numCrazyBoxes, hasHadMedalToday, ranDailyYesterday, isBooster, levelTier

//ex. 100 16000000000 50 16000000000 25 16000000000 15 16000000000 16000000000 true false 1 10000 5 5 5 5 5 true true true 1 1 1  1 true 1 1 1 1 1 1 20 20 15 5 2 true 20 20 20 20 4 5 5 20 5 0 true false true 0 3 2 1 10 2 2 2 1 true false 0 0 16000000000 0 0 0 0 0 0 0.0 0 0 0 0 0 0 0 true 0 0 0 0 true true 1 1 1 1 16000000000 1 0 0 0 false true true 2