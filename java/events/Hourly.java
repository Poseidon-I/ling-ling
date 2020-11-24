package events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class Hourly extends ListenerAdapter {
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        BufferedReader br;
        long time = 0;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\hourly.txt"));
            time = Long.parseLong(br.readLine());
            br.close();
        } catch (Exception exception) {
            //nothing here lol
        }
        if(System.currentTimeMillis() > time) {
            time += 3600000;
            File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
            File[] files = directory.listFiles();
            String data = null;
            for(File file : files) {
                //0, 12
                try {
                    br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                    data = br.readLine();
                    br.close();
                } catch (Exception exception) {
                    //nothing here lol
                }
                assert data != null;
                String[] array = data.split(" ");
                int violins = Integer.parseInt(array[0]);
                int income = Integer.parseInt(array[12]);
                violins += income;
                StringBuilder newData = new StringBuilder("" + violins);
                for(int i = 1; i < array.length; i ++) {
                    newData.append(" ").append(array[i]);
                }
                PrintWriter pw;
                try {
                    pw = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                    pw.print(newData);
                    pw.close();
                } catch (Exception exception) {
                    //nothing here lol
                }
            }
            try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\hourly.txt")));
                pw.print(time);
                pw.close();
            } catch(Exception exception) {
                //nothing here lol
            }
        }
    }
}