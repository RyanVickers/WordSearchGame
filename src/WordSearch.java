import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Scanner;

public class WordSearch {
    private char[][] gameBoard;
    private String[] words;
    private int row = getValidInput("rows");
    private int col = getValidInput("columns");

    /**
     * Initialization of word search
     */
    public WordSearch() {
        words = new String[row];
        gameBoard = new char[row][col];
        for (row = 0; row < gameBoard.length; row++)
            for (col = 0; col < gameBoard[row].length; col++)
                gameBoard[row][col] = ' ';
        wordFill();//calls wordFill method.
        fillBoard();//calls fillBoard method.
        randomColumn();//calls randomColumn method.
    }

    /**
     * This method receives a input from the scanner and validates it is between (2-15).
     * Used to validate column and row length.
     * also checks for invalid inputs letters,symbols.
     */
    public int getValidInput(String side) {
        Scanner key = new Scanner(System.in);
        int input = 0;
        while (input < 2 || input > 15) {
            System.out.printf("Enter the number of %s (2-15):", side);
            try {
                input = key.nextInt();
                if (input < 2 || input > 15) {//checks if number between 2 and 15.
                    System.out.printf("Must be between (2-15)%n");
                }
            } catch (Exception e) {
                System.out.printf("Only integers are allowed%n");//checks for letters and decimals.
                key.nextLine();  //clear the buffer
                input = 0;
            }
        }
        return input;
    }

    /**
     * This method accepts a length input based on the column length and validates word is between (2 and the column length)
     * Also checks for invalid input e.g numbers,spaces.
     */
    public String wordCheck(int length) {
        Scanner key1 = new Scanner(System.in);
        String word = "";
        while (word.length() < 2 || word.length() > length) {
            System.out.printf("Enter a word with less than %d characters%n", length);
            word = key1.nextLine().trim();//trims word of spaces
            if (word.length() < 2 || word.length() > length) {//check if word is between 2 and the column length.
                System.out.printf("Words must be less than %d characters%n", length);
            }
            if (word.matches(".*\\d.*") || word.contains(" ")) {//checks for spaces between words or numbers.
                System.out.printf("No Spaces or Numbers%n");
                word = "";
            }

        }
        return word.toUpperCase();//returns uppercase word.
    }

    /**
     * This method randomly generates a character of the alphabet
     * using secure random.
     */
    public char letters() {
        char c = 0;
        SecureRandom rng = new SecureRandom();
        int rand = rng.nextInt(91 - 65) + 65;
        c = (char) rand;
        return c;
    }

    /**
     * Method inserts random characters into the board
     * using letters method.
     */
    public void fillBoard() {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                gameBoard[row][col] = letters();//calls letter method
            }
        }
    }

    /**
     * Method prints gameboard to the console.
     */
    public void getWordSearchString() {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                System.out.printf("%5c ", gameBoard[row][col]);
            }
            System.out.println();
        }
    }

    /**
     * Method stores words entered using wordCheck method into array
     * to store String.
     */
    public void wordFill() {
        for (int x = 0; x < words.length; x++) {
            words[x] = wordCheck(col);
        }
    }

    /**
     * Method inserts stored words into gamebored array
     * also inserts words into columns in a random starting position.
     */
    public void randomColumn() {
        SecureRandom rng = new SecureRandom();

        for (int row = 0; row < words.length; row++) {
            int rand = rng.nextInt(col - words[row].length() + 1);
            for (int col = 0; col < words[row].length(); col++) {
                gameBoard[row][col + rand] = words[row].charAt(col);
            }
        }
    }

    /**
     * Method outputs each word as a list to the console.
     */
    public void getWordString() {
        System.out.printf("The words to find:%n");
        for (int x = 0; x < words.length; x++) {
            System.out.printf("%s%n", words[x]);
        }
    }

    /**
     * Method prints output to a puzzle.txt document.
     *
     * @throws IOException
     */
    public void usingPrintWriter() throws IOException {
        FileWriter fileWriter = new FileWriter("puzzle.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++)
                printWriter.printf("%5c ", gameBoard[row][col]);
            printWriter.println();
        }
        printWriter.printf("The words to find:%n");
        for (int x = 0; x < words.length; x++) {
            printWriter.printf("%s%n", words[x]);
        }
        printWriter.close();

    }
}



