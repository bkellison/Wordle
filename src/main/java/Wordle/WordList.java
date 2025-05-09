package Wordle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a list of words used in the game and
 * will randomly select one.
 *
 * @version 1.0
 * @author Blake Kellison
 */
public class WordList {
    // Class implementation

    /**
     *  A list that stores the available words for the game.
     */
    private List<String> words;

    /**
     * Constructor that initializes a WordList object.
     */
    public WordList() {
        words = new ArrayList<>();
        initializeWords();
    }

    /**
     * This method Initializes the word list with a set of predefined 5-letter words.
     */
    private void initializeWords() {
        // Method implementation
        words.add("stone");
        words.add("flock");
        words.add("grasp");
        words.add("brave");
        words.add("chalk");
        words.add("swift");
        words.add("craft");
        words.add("blaze");
        words.add("alert");
        words.add("quiet");
        words.add("climb");
        words.add("march");
        words.add("trail");
        words.add("blend");
        words.add("drift");
        words.add("forge");
        words.add("glide");
        words.add("hover");
        words.add("shine");
        words.add("flash");
        words.add("claim");
        words.add("shift");
        words.add("frame");
        words.add("stand");
        words.add("whirl");
        words.add("brood");
        words.add("lever");
        words.add("teeth");
        words.add("pasta");
        words.add("apple");
    }

    /**
     * This method randomly selects a word from the list of available words.
     *
     * @return A randomly selected word from the word list.
     */
    public String getRandomWord() {
        // Method implementation
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}
