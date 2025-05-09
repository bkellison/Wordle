package Wordle;

/**
 * This class represents a player's guess in the game, including the guessed word
 * and the results for each letter.
 *
 * @version 1.0
 * @author Blake Kellison
 */
public class WordleGuess {
    // Class implementation

    /**
     * The guessed word.
     */
    private String guess;

    /**
     * Results for each letter in the guessed word.
     */
    private String[] results;

    /**
     * Constructor that initializes an object with the specified guess and results.
     *
     * @param guess   The guessed word.
     * @param results An array of strings representing the results for each letter in the guess.
     */
    public WordleGuess(String guess, String[] results) {
        this.guess = guess;
        this.results = results;
    }

    /**
     * Method that gets the results of the guess.
     *
     * @return An array of strings representing the results for each letter
     *         in the guess.
     */
    public String[] getResults() {
        // Method implementation
        return results;
    }
}
