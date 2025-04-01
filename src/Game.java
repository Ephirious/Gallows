public class Game {
    private final GameController controller;


    public Game() {
        controller = new GameController();
    }

    public void start() {
        String userAnswer = GameReader.getAnswerOfStartGame();
        while (userAnswer.equals("Yes")) {
            controller.setWord(Dictionary.getRandomWord());
            controller.play();
            controller.reset();
            userAnswer = GameReader.getAnswerOfStartGame();
        }
        System.out.println("Good bye!");
    }
}
