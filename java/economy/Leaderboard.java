package economy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import processes.Numbers;

import java.awt.*;
import java.io.File;
import java.io.FileReader;

public class Leaderboard {
    public Leaderboard(String emoji, String what, MessageReceivedEvent e, String index, long userNum) {
        File directory = new File("Ling Ling Bot Data\\Economy Data");
        File[] files = directory.listFiles();
        long place = 1;
        String[] entry = {e.getAuthor().getAsMention() + ": " + userNum + " " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n", "<@0>: 0 " + emoji + "\n"};
        assert files != null;
        for (File file : files) {
            JSONParser parser = new JSONParser();
            JSONObject data;
            try (FileReader reader = new FileReader(file.getAbsolutePath())) {
                data = (JSONObject) parser.parse(reader);
                reader.close();
                if ((boolean) data.get("banned")) {
                    continue;
                }
            } catch (Exception exception) {
                continue;
            }
            String user = file.getName().substring(0, file.getName().lastIndexOf("."));
            long num;
            if (index.equals("medals")) {
                num = (long) data.get("thirdPlace") + 2 * (long) data.get("secondPlace") + 3 * (long) data.get("firstPlace");
            } else if (index.equals("hoursPractised") || index.equals("hoursTaught")) {
                double temp = (double) data.get(index);
                num = (long) temp;
            } else {
                num = (long) data.get(index);
            }
            if (num == 0) {
                continue;
            }
            for (int i = 0; i < 10; i++) {
                if (num > Long.parseLong(entry[i].split(" ")[1]) && !user.equals(e.getAuthor().getId())) {
                    System.arraycopy(entry, i, entry, i + 1, 9 - i);
                    entry[i] = "<@" + user + ">: " + num + " " + emoji + "\n";
                    if (num > userNum) {
                        place++;
                    }
                    break;
                }
            }
        }
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (entry[i].contains("<@0>")) {
                break;
            }
            try {
                String id = entry[i].split(" ")[0];
                User user = e.getJDA().getUserById(id.substring(2, id.length() - 2));
                assert user != null;
                board.append("**").append(i + 1).append(". ").append(user.getName()).append("**#").append(user.getDiscriminator()).append(" `").append(user.getId()).append("`: ").append(Numbers.FormatNumber(Long.parseLong(entry[i].split(" ")[1]))).append(emoji).append("\n");
            } catch (Exception exception) {
                String[] temp = entry[i].split(" ");
                board.append("**").append(temp[0]).append(Numbers.FormatNumber(Long.parseLong(temp[1]))).append(temp[2]);
            }
        }
        if (place >= 11) {
            board.append("\n**").append(place).append(". You**: ").append(Numbers.FormatNumber(userNum)).append(" ").append(emoji);
        }
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.BLUE)
                .setFooter("Ling Ling", e.getJDA().getSelfUser().getAvatarUrl())
                .setTitle("__**Global Leaderboard**__")
                .addField("**" + what + " in the World**", board.toString(), false);
        e.getMessage().replyEmbeds(builder.build()).mentionRepliedUser(false).queue();
    }
}