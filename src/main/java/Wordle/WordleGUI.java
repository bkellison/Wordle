package Wordle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The WordleGUI class creates a graphical user interface (GUI) using swing components.
 * It allows the user to enter guesses, displays the results, and shows incorrect guesses.
 * The GUI includes a reset feature and handles game state messages.
 *
 * @author Blake Kellison
 * @version 1.0
 */
public class WordleGUI extends JFrame {
    // Class implementation

    /**
     * Handles game logic and communication between the GUI and the game.
     */
    private WordleInputHandler controller;

    /**
     * Panel to display game grid.
     */
    private JPanel grid;

    /**
     * Text field where the player enters their guesses.
     */
    private JTextField guessInput;

    /**
     * Label to display the game's current status.
     */
    private JLabel statusLabel;

    /**
     * Text area to display the incorrect guesses made by the player.
     */
    private JTextArea incorrectGuesses;

    /**
     * Tracks the number of guesses the player has made.
     */
    private int currentAttempt;

    /**
     * 2D array of JLabels to display each letter in the grid for each guess.
     */
    private JLabel[][] letterLabels;

    /**
     * Constructor initializes the GUI, sets up the layout,
     * and prepares the grid for the game.
     */
    public WordleGUI() {
        controller = new WordleInputHandler(); // Initialize the controller
        currentAttempt = 0;

        // Set up the main frame layout and properties
        setTitle("Wordle Game");
        setSize(600, 660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        setLocationRelativeTo(null);

        // Initialize input field for guesses
        guessInput = new JTextField(10);
        guessInput.setToolTipText("Enter your 5-letter guess");

        // Create a button to submit guesses
        JButton submitButton = new JButton("Submit Guess");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processUserGuess();
            }
        });

        // Create a button to reset the game
        JButton resetButton = new JButton("Reset Game");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        // Set up the game grid panel 6 attempts, 5 letters each
        grid = new JPanel();
        grid.setLayout(new GridLayout(6, 5, 5, 5));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 0));

        // Initialize 2D array of labels for the game grid
        letterLabels = new JLabel[6][5];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                letterLabels[i][j] = new JLabel("", SwingConstants.CENTER);
                letterLabels[i][j].setOpaque(true); // Make background visible
                letterLabels[i][j].setFont(new Font("Arial", Font.BOLD, 24));
                grid.add(letterLabels[i][j]); // Add label to the grid
            }
        }

        // status label to show messages
        statusLabel = new JLabel("Welcome to Wordle!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // text area to show incorrect guesses
        incorrectGuesses = new JTextArea(5, 15);
        incorrectGuesses.setEditable(false);
        incorrectGuesses.setLineWrap(true);
        incorrectGuesses.setWrapStyleWord(true);
        incorrectGuesses.setToolTipText("Incorrect guesses will appear here...");

        // Create panel for input and buttons
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(guessInput);
        inputPanel.add(submitButton);
        inputPanel.add(resetButton);

        // Create label for incorrect guesses
        JLabel incorrectGuessesHeader = new JLabel("Incorrect Guesses", SwingConstants.CENTER);
        incorrectGuessesHeader.setFont(new Font("Arial", Font.BOLD,10));

        // Panel to hold incorrect guesses
        JPanel incorrectGuessesPanel = new JPanel();
        incorrectGuessesPanel.setLayout(new BoxLayout(incorrectGuessesPanel, BoxLayout.Y_AXIS));
        incorrectGuessesPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        incorrectGuessesPanel.add(incorrectGuessesHeader);
        incorrectGuessesPanel.add(incorrectGuesses);

        // Add components to the main frame layout
        add(inputPanel, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        add(incorrectGuessesPanel, BorderLayout.EAST);

        setVisible(true);
    }

    /**
     * This method handles the player's guess input and updates the game state.
     * It processes the guess, displays the result in the grid, and checks if the game is over.
     */
    private void processUserGuess() {
        // Method implementation
        String guess = guessInput.getText().trim().toUpperCase(); // Gets word, trims white space, and capitalizes all characters
        if (guess.length() != 5) {
            showAlert("Invalid Input", "Please enter exactly 5 letters.");
            return;
        }

        try {
            // Call controller to handle the guess
            WordleGuess wordleGuess = controller.evaluateGuess(guess);

            // Get results from the guess and display them
            String[] result = wordleGuess.getResults();
            displayResult(result, guess);
            guessInput.setText(""); // Clear input field after submission

            // Check if game is over and displays final message
            if (controller.isGameOver()) {
                showFinalMessage();
            }
        } catch (IllegalStateException e) {
            showAlert("Game Over", e.getMessage());
        }
    }

    /**
     * This method displays the result of the player's guess on the grid.
     * The result includes color-coding of grey, yellow, and green for the letters.
     *
     * @param result The array containing the color of each letter.
     * @param guess  The guess entered by the player.
     */
    private void displayResult(String[] result, String guess) {
        // Method implementation
        for (int i = 0; i < result.length; i++) {
            JLabel letterLabel = letterLabels[currentAttempt][i];
            letterLabel.setText(String.valueOf(guess.charAt(i)));

            switch (result[i]) {
                case "green":
                    letterLabel.setBackground(Color.GREEN);
                    letterLabel.setForeground(Color.WHITE);
                    break;
                case "yellow":
                    letterLabel.setBackground(Color.YELLOW);
                    break;
                case "gray":
                    letterLabel.setBackground(Color.GRAY);
                    letterLabel.setForeground(Color.WHITE);
                    addIncorrectGuess(guess.charAt(i));
                    break;
            }
        }
        currentAttempt++;
    }

    /**
     * This method adds an incorrect guess to the incorrect guesses text area.
     *
     * @param letter The incorrect letter to be added.
     */
    private void addIncorrectGuess(char letter) {
        // Method implementation
        String currentText = incorrectGuesses.getText();

        // Converts the char value letter to a string, so it can be checked with in currentText
        if (!currentText.contains(String.valueOf(letter))) {
            incorrectGuesses.append(letter + " "); // Appends the incorrect letter in text area
        }
    }

    /**
     * This method displays the final message at the end of the game
     */
    private void showFinalMessage() {
        // Method implementation
        if (controller.isGameWon()) {
            statusLabel.setText("Congratulations! You've guessed the word: " + controller.getTargetWord());
        } else {
            statusLabel.setText("Game Over! The correct word was: " + controller.getTargetWord());
        }
    }

    /**
     * This method resets the game, controller and game logic.
     */
    private void resetGame() {
        // Method implementation
        controller.resetGame();
        currentAttempt = 0;

        // Clear the grid labels and reset colors
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                letterLabels[i][j].setText("");
                letterLabels[i][j].setBackground(Color.WHITE);
                letterLabels[i][j].setForeground(Color.BLACK);
            }
        }

        // Clears text that was changed
        guessInput.setText("");
        statusLabel.setText("Welcome to Wordle!");
        incorrectGuesses.setText("");
    }

    /**
     * This method displays an alert with a specific message.
     *
     * @param title   The title of the alert.
     * @param message The message to be displayed in the alert.
     */
    private void showAlert(String title, String message) {
        // Method implementation
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * The main method to launch the Wordle game GUI.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WordleGUI());
    }
}
