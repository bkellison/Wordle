package Wordle;

/**
 * This class represents the game logic for the game.
 * It manages the target word, player's attempts, and evaluates the guesses.
 * It also provides methods to check guesses, determine game state, and reset game.
 *
 * @version 1.1
 * @author Blake Kellison
 */
public class WordleGameLogic {

    /**
     * The word the player has to guess.
     */
    private String targetWord;

    /**
     * The number of attempts the player has left.
     */
    private int attemptsLeft;

    /**
     * Tracks if the player has won.
     */
    private boolean gameWon;

    /**
     * Instance of WordList to get random words.
     */
    private WordList wordList;

    /**
     * Constructor that initializes an object and game.
     * Selects a random target word and sets the initial number of attempts,
     */
    public WordleGameLogic() {
        wordList = new WordList();
        targetWord = wordList.getRandomWord();
        attemptsLeft = 6;
        gameWon = false;
    }

    /**
     * This method checks the player's guess and returns the result for each letter in the guess.
     * Each letter in the guess is compared to the target word, then assigned a color of
     * green, yellow, or gray based on the correctness of position and letter.
     *
     * @param guess The player's guess of 5 letters.
     * @return An array of strings representing the result for each letter in the guess.
     * @throws IllegalArgumentException if the guess is not exactly 5 letters.
     */
    public String[] checkGuess(String guess) {
        // Method implementation
        if (guess.length() != 5) {
            throw new IllegalArgumentException("Guess must be a 5-letter word.");
        }

        String[] result = new String[5];  // Array to holds result for each letter
        boolean[] targetLetterUsed = new boolean[5]; // Tracks if a letter in the target word has been matched
        guess = guess.toLowerCase();
        String target = targetWord.toLowerCase();

        // Check for green
        for (int i = 0; i < 5; i++) {
            char guessChar = guess.charAt(i);
            if (guessChar == target.charAt(i)) {
                result[i] = "green"; // Correct letter in the correct position
                targetLetterUsed[i] = true;
            }
        }

        // Check for yellow
        for (int i = 0; i < 5; i++) {
            // Skip already matched greens
            if (result[i] == null) {
                char guessChar = guess.charAt(i);
                for (int j = 0; j < 5; j++) {
                    if (guessChar == target.charAt(j) && !targetLetterUsed[j]) {
                        result[i] = "yellow"; // Correct letter in the wrong position
                        targetLetterUsed[j] = true;
                        break;
                    }
                }
            }
            if (result[i] == null) {
                result[i] = "gray"; // No match found
            }
        }

        // Check if the guess matches the target word
        if (guess.equals(target)) {
            gameWon = true;
        } else {
            attemptsLeft--;
        }

        return result;
    }

    /**
     * Method that gets the target word that the player needs to guess.
     *
     * @return The target word of the current game.
     */
    public String getTargetWord() {
        // Method implementation
        return targetWord;
    }

    /**
     * Method that checks if the game is over. The game is over if the player has won
     * or if the player has no remaining attempts.
     *
     * @return true if the game is over.
     */
    public boolean isGameOver() {
        // Method implementation
        return gameWon || attemptsLeft <= 0;
    }

    /**
     * Method that checks if the player has won the game.
     *
     * @return true if the player has won.
     */
    public boolean isGameWon() {
        // Method implementation
        return gameWon;
    }

    /**
     * Method that resets the game, the number of attempts,
     * and setting of game.
     */
    public void resetGame() {
        // Method implementation
        targetWord = wordList.getRandomWord();
        attemptsLeft = 6;
        gameWon = false;
    }
}
