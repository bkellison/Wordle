package Wordle;

/**
 * This class communicates with the WordleGameLogic class to process guesses
 * and provide game status.
 *
 * @version 1.0
 * @see WordleGameLogic
 * @see WordleGuess
 * @author Blake Kellison
 */
public class WordleInputHandler {
    // Class implementation

    /**
     * Instance of GameLogic to handle game logic
     */
    private WordleGameLogic gameLogic;


    /**
     * Constructor to initialize the game logic for handling operations.
     */
    public WordleInputHandler() {
        gameLogic = new WordleGameLogic();
    }

    /**
     * Method that processes a player's guess by interacting with the game logic.
     * Throws an IllegalStateException if the game is over.
     *
     * @param guess The player's guess, which is a string of letters.
     * @return An object containing the player's guess and the result from the game logic.
     * @throws IllegalStateException if the game is already over.
     */
    public WordleGuess evaluateGuess(String guess) {
        // Method implementation
        if (gameLogic.isGameOver()) {
            throw new IllegalStateException("Game is already over. Please reset to play again.");
        }

        String[] result = gameLogic.checkGuess(guess);
        return new WordleGuess(guess, result);
    }

    /**
     * This method checks if the current game has ended.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        // Method implementation
        return gameLogic.isGameOver();
    }

    /**
     * This method checks if the player has won the current game.
     *
     * @return true if the player has won, false otherwise.
     */
    public boolean isGameWon() {
        // Method implementation
        return gameLogic.isGameWon();
    }

    /**
     * This method resets the game to start a new game with a new target word.
     */
    public void resetGame() {
        // Method implementation
        gameLogic.resetGame();
    }

    /**
     * This method gets the target word that the player is trying to guess.
     *
     * @return The target word for the current game.
     */
    public String getTargetWord() {
        // Method implementation
        return gameLogic.getTargetWord();
    }
}
