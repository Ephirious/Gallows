public class GameController {
    private String word;
    private final Painter painter;
    private final boolean[] bitMaskOfWord;
    private final boolean[] bitMaskOfOpenedLetters;
    private int numberOfMistakes;
    private char lastLetter;

    public GameController() {
        bitMaskOfWord = new boolean[33];
        bitMaskOfOpenedLetters = new boolean[33];
        painter = new Painter();
    }

    public void play() {
        setWordBitMask();
        painter.setWord(word);
        painter.paint();

        while (checkGameEnd()) {
            getUniqueLetter();
            updateNumberOfMistakes();
            updateOpenedBitMask();
            painter.updateGameAttributes(numberOfMistakes, lastLetter);
            painter.paint();
        }
        writeGameOverMessage();
    }

    public void reset() {
        word = null;
        painter.reset();
        numberOfMistakes = lastLetter = 0;

        for (int index = 0; index < 33; index++)
            bitMaskOfWord[index] = bitMaskOfOpenedLetters[index] = false;
    }

    public void setWord(String word) {
        this.word = word;
    }

    private void getUniqueLetter() {
        lastLetter = GameReader.getLetterInGame();
        while ((lastLetter == 'ё' && bitMaskOfOpenedLetters[32]) || (lastLetter != 'ё' && bitMaskOfOpenedLetters[lastLetter - 'а'])) {
            System.out.println("This letter has already written");
            lastLetter = GameReader.getLetterInGame();
        }
    }

    private void updateOpenedBitMask() {
        if (lastLetter != 'ё')
            bitMaskOfOpenedLetters[lastLetter - 'а'] = true;
        else
            bitMaskOfOpenedLetters[32] = true;
    }

    private void updateNumberOfMistakes() {
        if (word.indexOf(lastLetter) == -1)
            numberOfMistakes++;
    }

    private void setWordBitMask() {
        for (int index = 0; index < word.length(); index++) {
            if (word.charAt(index) != 'ё')
                bitMaskOfWord[word.charAt(index) - 'а'] = true;
            else
                bitMaskOfWord[32] = true;
        }
    }

    private boolean isAllLettersOpened() {
        for (int index = 0; index < 33; index++) {
            if (bitMaskOfWord[index] != bitMaskOfOpenedLetters[index])
                return false;
        }
        return true;
    }

    private void writeGameOverMessage() {
        System.out.println((numberOfMistakes == 6) ? "You have lost game" : "You have won game");
        System.out.println("The hidden word was " + word);
    }

    private boolean checkGameEnd() {
        if (numberOfMistakes == 6)
            return false;
        return !isAllLettersOpened();
    }





}
