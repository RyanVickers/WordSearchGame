import java.security.SecureRandom;
import java.util.Scanner;

public class WordSearch {
    private char[][] gameBoard;
    private String[] words;
    private int row = getValidInput("rows");
    private int col = getValidInput("columns");

    public WordSearch() {
        words = new String[row];
        gameBoard = new char[row][col];
        for (row = 0; row < gameBoard.length; row++)
            for (col = 0; col < gameBoard[row].length; col++)
                gameBoard[row][col] = ' ';
    }

    public int getValidInput(String x) {
        Scanner key = new Scanner(System.in);
        int input = 0;
        int counter = 0;
        while (input < 2 || input > 15) {
            if (counter >= 1) {
                System.out.printf("Must be between (2-15)%n");
            }
            System.out.printf("Enter the number of %s (2-15):", x);
            counter++;
            try {
                input = key.nextInt();
            } catch (Exception e) {
                System.out.printf("Only integers are allowed%n");
                key.nextLine();  //clear the buffer
                input = 0;
            }
        }
        return input;
    }

    public String wordCheck(int length) {
        Scanner key1 = new Scanner(System.in);
        String word = "";
        int counter = 0;
        while (word.length() < 2 || word.length() > length) {
            if (counter >= 1) {
                System.out.printf("Words must be less than %d characters%n", length);
            }
            System.out.printf("Enter a word with less than %d characters", length);
            counter++;
            try {
                word = key1.next();
            } catch (Exception e) {
                System.out.printf("Only letters allowed%n");
                key1.nextLine();  //clear the buffer
                word = "";
            }
        }
        return word.trim().toUpperCase();
    }

    public char letters() {
        char c = 0;
        SecureRandom rng = new SecureRandom();
        for (int i = 0; i < 91; i++) {
            int rand = rng.nextInt(91 - 65) + 65;
            c = (char) rand;
        }
        return c;
    }

    public void fillBoard() {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                gameBoard[row][col] = letters();
            }
        }
    }

    public void displayBoard() {
        wordFill();
        fillBoard();
        Board();
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                System.out.printf("%5c ", gameBoard[row][col]);
            }
            System.out.println();
        }
    }

    public void wordFill() {
        for (int x = 0; x < words.length; x++) {
            words[x] = wordCheck(col);
        }
    }
    public void Board() {
        for (int row = 0; row < words.length; row++) {
            for (int col = 0; col < words[row].length(); col++) {
                gameBoard[row][col] = words[row].charAt(col);

            }
        }
    }
    }



