import java.io.*;

public class Painter {
    private StringBuilder[] gameScreen;
    private String word;
    private int numberOfMistakes;

    public Painter() {
        loadGameScreenFromFile();
    }

    public void paint() {
        for (int rowIndex = 0; rowIndex < 10; rowIndex++) {
            for (int colIndex = 0; colIndex < gameScreen[rowIndex].length(); colIndex++)
                System.out.print(gameScreen[rowIndex].charAt(colIndex));
            System.out.println();
        }
    }

    public void updateGameAttributes(int mistakes, char lastLetter) {
        gameScreen[8].setCharAt(27, lastLetter); // set last letter on screen

        if (numberOfMistakes == mistakes) {
            paintLetter(lastLetter);
        } else {
            gameScreen[5].setCharAt(24, (char)(mistakes + 48));
            numberOfMistakes = mistakes;
            paintHuman(numberOfMistakes);
        }
    }

    public void reset() {
        gameScreen[8].setCharAt(27, '_');
        gameScreen[5].setCharAt(24, '0');
        gameScreen[3].setCharAt(5, ' ');
        gameScreen[4].setCharAt(5, ' ');
        gameScreen[5].setCharAt(5, ' ');
        gameScreen[4].setCharAt(4, ' ');
        gameScreen[4].setCharAt(6, ' ');
        gameScreen[6].setCharAt(4, ' ');
        gameScreen[6].setCharAt(6, ' ');

        int wordOffset = 20;
        for (int index = wordOffset; index < gameScreen[2].length() - 2; index++)
            gameScreen[2].setCharAt(index , ' ');
    }

    public void setWord(String word) {
        this.word = word;

        int wordOffset = 20;
        for (int index = 0; index < word.length() * 2; index++) {
            gameScreen[2].setCharAt(wordOffset + index,
                    (index % 2 == 0) ? '_' : ' ');
        }
    }

    private void loadGameScreenFromFile() {
        gameScreen = new StringBuilder[10];

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream("template.txt"))))
        {
            for (int i = 0; i < 10; i++)
                gameScreen[i] = new StringBuilder(reader.readLine());
        } catch (NullPointerException exception) {
            System.out.println("'template.txt' hasn't found.");
        } catch (IOException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private void paintHuman(int mistakeNumber) {
        switch (mistakeNumber) {
            case 1 -> gameScreen[3].setCharAt(5, 'Ô');
            case 2 -> {
                gameScreen[4].setCharAt(5, '│');
                gameScreen[5].setCharAt(5, '│');
            }
            case 3 -> gameScreen[4].setCharAt(4, '\\');
            case 4 -> gameScreen[4].setCharAt(6, '/');
            case 5 -> gameScreen[6].setCharAt(4, '/');
            case 6 -> gameScreen[6].setCharAt(6, '\\');
        }
    }

    private void paintLetter(char letter) {
        int offsetCol = 20;
        for (int index = 0; index < word.length(); index++) {
            if (word.charAt(index) == letter)
                gameScreen[2].setCharAt(offsetCol + index * 2, letter);
        }
    }

}
