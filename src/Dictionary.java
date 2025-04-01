import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Dictionary {
    private static final List<String> words;
    private static final Random wordNumber;

    static {
        words = new ArrayList<>();
        wordNumber = new Random();
        loadWordsFromFile();
    }

    public static String getRandomWord() {
        return words.get(wordNumber.nextInt(words.size()));
    }

    private static void loadWordsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("russian_nouns.txt"))) {
            while (reader.ready())
                words.add(reader.readLine());
        } catch (FileNotFoundException exception) {
            System.out.println("'russian_nouns.txt' hasn't found.");
        } catch (IOException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
