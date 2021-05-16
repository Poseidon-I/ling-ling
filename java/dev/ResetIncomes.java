package dev;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;

public class ResetIncomes {
    public ResetIncomes(GuildMessageReceivedEvent e) {

        if (!e.getAuthor().isBot()) {
            e.getChannel().sendMessage("Maually overriding automatic user save reset...").queue();
        }
        File directory = new File("C:\\Users\\ying\\Desktop\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                String[] data;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                    data = reader.readLine().split(" ");
                    reader.close();
                    if(data[0].equals("BANNED")) {
                        continue;
                    }
                } catch (Exception exception) {
                    continue;
                }
                long income = 0;
                try {
                    long violinQuality = Long.parseLong(data[13]);
                    long skillLevel = Long.parseLong(data[14]);
                    long lessonQuality = Long.parseLong(data[15]);
                    long stringQuality = Long.parseLong(data[16]);
                    long bowQuality = Long.parseLong(data[17]);
                    boolean hasMath = Boolean.parseBoolean(data[18]);
                    boolean hasOrchestra = Boolean.parseBoolean(data[19]);
                    boolean piccolo = Boolean.parseBoolean(data[20]);
                    long flute = Long.parseLong(data[21]);
                    long oboe = Long.parseLong(data[22]);
                    long clarinet = Long.parseLong(data[23]);
                    long bassoon = Long.parseLong(data[24]);
                    boolean contrabassoon = Boolean.parseBoolean(data[25]);
                    long horn = Long.parseLong(data[26]);
                    long trumpet = Long.parseLong(data[27]);
                    long trombone = Long.parseLong(data[28]);
                    long tuba = Long.parseLong(data[29]);
                    long timpani = Long.parseLong(data[30]);
                    long percussion = Long.parseLong(data[31]);
                    long first = Long.parseLong(data[32]);
                    long second = Long.parseLong(data[33]);
                    long cello = Long.parseLong(data[34]);
                    long stringBass = Long.parseLong(data[35]);
                    long piano = Long.parseLong(data[36]);
                    boolean harp = Boolean.parseBoolean(data[37]);
                    long soprano = Long.parseLong(data[38]);
                    long alto = Long.parseLong(data[39]);
                    long tenor = Long.parseLong(data[40]);
                    long bass = Long.parseLong(data[41]);
                    long soloists = Long.parseLong(data[42]);
                    long hallLevel = Long.parseLong(data[43]);
                    long conductor = Long.parseLong(data[44]);
                    long advertising = Long.parseLong(data[45]);
                    long tickets = Long.parseLong(data[46]);
                    boolean moreIncome = Boolean.parseBoolean(data[56]);
                    boolean hasCertificate = Boolean.parseBoolean(data[78]);
                    long training = Long.parseLong(data[80]);
                    long students = Long.parseLong(data[81]);
                    long pricing = Long.parseLong(data[82]);
                    boolean hasStudio = Boolean.parseBoolean(data[83]);
                    income = violinQuality * 600 + skillLevel * 240 + lessonQuality * 150 + stringQuality * 100 + bowQuality * 200 + flute * 60 + oboe * 50 + clarinet * 40 + bassoon * 40 + horn * 40 + trumpet * 30 + trombone * 20 + tuba * 20 + timpani * 60 + percussion * 10 + first * 70 + second * 60 + cello * 50 + stringBass * 50 + piano * 110 + soprano * 30 + alto * 20 + tenor * 20 + bass * 20 + soloists * 60 + hallLevel * 300 + conductor * 200 + advertising * 100 + tickets * 1000 + training * 1500 + students * 2500 + pricing * 4000;
                    if (hasMath) {
                        income += 6500;
                    }
                    if (hasOrchestra) {
                        income += 3100;
                    } else {
                        income -= 130;
                    }
                    if (piccolo) {
                        income += 30;
                    }
                    if (contrabassoon) {
                        income += 30;
                    }
                    if (harp) {
                        income += 80;
                    }
                    if (moreIncome) {
                        income += 8000;
                    }
                    if (hasCertificate) {
                        income += 10000;
                    }
                    if (hasStudio) {
                        income += 5000;
                    }
                } catch (Exception exception) {
                    System.out.println("Problem file is " + file.getAbsolutePath());
                    continue;
                }
                data[12] = income + "";
                StringBuilder write = new StringBuilder(data[0]);
                for (int i = 1; i < data.length; i++) {
                    write.append(" ").append(data[i]);
                }
                try {
                    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                    writer.print(write);
                    writer.close();
                } catch (Exception exception) {
                    //nothing here lol
                }
            }
            e.getChannel().sendMessage("Incomes successfully reset!").queue();
        }
    }
}