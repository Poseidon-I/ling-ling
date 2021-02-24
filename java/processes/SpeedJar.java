package processes;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class SpeedJar {
    public SpeedJar(GuildMessageReceivedEvent e, String word) {
        PrintWriter writer;
        Random random = new Random();
        StringBuilder target = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            target.append(word.charAt(i));
        }
        StringBuilder message = new StringBuilder();
        if(e.getMessage().getContentRaw().toLowerCase().equals(target.toString())) {
            String[] words = {"oatmeal", "tip jar", "customer", "microwave", "hard shell", "hard shell cole", "tip", "sausage", "sweet onion", "pan fry", "oven fry", "mashed potatoes", "french fry", "french fries", "egg", "eggplant", "broccoli", "mushroom", "hot sauce", "pepper", "john", "cole", "coal", "candy", "cherry", "bacon", "beets", "lettuce", "cinnamon", "sous chef", "executive chef", "baguette", "grape", "guacamole", "cook", "rice cooker", "drink", "sweet onion", "bell pepper", "tortilla", "chili", "hard boil", "hard boil cole", "boil", "boil cole", "milkshake", "potato", "raspberry", "mild salsa", "avocado", "pico de gallo", "meatball", "ham", "beans", "lasagna", "garlic bread", "soft shell", "pudding", "shack", "olives", "lime", "mile", "cabbage", "enchilada", "cheesecake", "sour cream", "tacoshack franchise", "churros", "refried beans", "fried rice", "tacoshack official server", "pork", "green onion", "onions", "fish", "lobster", "ice cubes", "chimichanga", "quesadilla", "chicken", "cheese", "franchise", "burrito", "corn", "turkey", "tamale", "spaghetti", "salad", "black beans", "taquitos", "taco sauce", "queso", "noodles", "fajita", "lemon", "melon", "coco", "cooc"};
            word = words[random.nextInt(words.length)];
            String original = word;
            char[] scrambler = new char[word.length()];
            int i = 0;
            StringBuilder send;
            message.append("**").append(e.getAuthor().getName()).append("** unscrambled `").append(target).append("`!");
            do {
                while (word.length() > 0) {
                    int num = random.nextInt(word.length());
                    StringBuilder newWord = new StringBuilder();
                    char temp = word.charAt(num);
                    scrambler[i] = temp;
                    for (int j = 0; j < word.length(); j++) {
                        if (j != num) {
                            newWord.append(word.charAt(j));
                        }
                    }
                    word = newWord.toString();
                    i++;
                }
                send = new StringBuilder();
                for (i = 0; i < scrambler.length; i++) {
                    send.append(scrambler[i]);
                }
            } while (send.toString().equals(original));
            message.append("\nCan you unscramble `").append(send).append("`?");
            e.getChannel().sendMessage(message).queue();
            try {
                writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\ying\\Desktop\\Ling Ling Bot Data\\SpeedJar.txt")));
                writer.print(original);
                writer.close();
            } catch(Exception exception) {
                //nothing here lol
            }
        } else {
            e.getChannel().addReactionById(e.getChannel().getLatestMessageId(), "U+274c").queue();
        }
    }
}