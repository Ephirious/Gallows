import java.util.Scanner;

public class GameReader {
    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static String getAnswerOfStartGame() {
        System.out.print("Do you want to start game (Yes\\No)?  - ");
        String answer = scanner.nextLine();
        while (!(answer.equals("Yes") || answer.equals("No"))) {
            System.out.println("Ynour answer is wrong");
            System.out.print("Please write correct answer (Yes\\No)?  - ");
            answer = scanner.next();
        }

        return answer;
    }

    public static char getLetterInGame() {
        Scanner scanner = new Scanner(System.in);
        String userLetter;

        do {
            System.out.print("Write your russian lowercase letter [а-я]: ");
            userLetter = scanner.nextLine();
        } while (isInvalidUserLetter(userLetter));

        return userLetter.charAt(0);
    }

    private static boolean isInvalidUserLetter(String userLetter) {
        if (userLetter.length() != 1) {
            System.out.println("Answer must has one letter");
            return true;
        }
        if ((userLetter.charAt(0) < 'а' || userLetter.charAt(0) > 'я') && userLetter.charAt(0) != 'ё') {
            System.out.println("Answer must be a russian lowercase letter");
            return true;
        }
        return false;
    }
}
