package processes;

import java.io.*;

public class HourlyIncome {
    public HourlyIncome() {
        BufferedReader reader;
        File directory = new File("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\Economy Data");
        File[] files = directory.listFiles();
        String data = null;
        for(File file : files) {
            try {
                reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                data = reader.readLine();
                reader.close();
            } catch (Exception exception) {
                //nothing here lol
            }
            assert data != null;
            String[] array;
            try {
                array = data.split(" ");
            } catch(Exception exception) {
                file.delete();
                continue;
            }
            long violins = Integer.parseInt(array[0]);
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
    }
}