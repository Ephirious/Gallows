import java.io.*;
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

    public static String getRandomWord() throws NullPointerException {
        if (words.isEmpty())
            throw new NullPointerException();
        return words.get(wordNumber.nextInt(words.size()));
    }

    private static void loadWordsFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream("classes/russian_nouns.txt"))))  {
            while (reader.ready())
                words.add(reader.readLine());
        } catch (NullPointerException exception) {
            System.out.println("'russian_nouns.txt' hasn't found.");
        } catch (IOException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
