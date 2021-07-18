package processes;

import java.io.*;

public class HourlyIncome {
    public HourlyIncome() {
        BufferedReader reader;
        File directory = new File("C:\\Users\\ying\\Desktop\\,\\Ling_Ling_Bot\\Ling Ling Bot Data\\Economy Data");
        File[] files = directory.listFiles();
        String[] data   ;
        assert files != null;
        for(File file : files) {
            try {
                reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                data = reader.readLine().split(" ");
                reader.close();
                if(data[0].equals("BANNED")) {
                    continue;
                }
            } catch (Exception exception) {
                continue;
            }
            try {
                long violins = Long.parseLong(data[0]);
                long income = Long.parseLong(data[12]);
                violins += income;
                StringBuilder newData = new StringBuilder("" + violins);
                for(int i = 1; i < data.length; i ++) {
                    if(i == 75) {
                        newData.append(" ").append(Long.parseLong(data[i]) + income);
                    } else {
                        newData.append(" ").append(data[i]);
                    }
                }
                PrintWriter pw;
                try {
                    pw = new PrintWriter(new BufferedWriter(new FileWriter(file.getAbsolutePath())));
                    pw.print(newData);
                    pw.close();
                } catch (Exception exception) {
                    //nothing here lol
                }
            } catch(Exception exception) {
                System.out.println("Problem File is " + file.getAbsolutePath());
            }
        }
    }
}