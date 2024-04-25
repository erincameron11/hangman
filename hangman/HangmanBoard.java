package hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanBoard {
	// Define variables
	public static String hangman = "";
	public static String prompt = "";
	public static int score = 0;
	public static String word = "";
	public static ArrayList<String> words = new ArrayList<String>();
	
	
	// ENUM: for each turn
	public enum Score {
		START, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, END
	}
	
	
	// METHOD: method to initialize the word
	public static void initialize() {
		// Initialize the hangman to the Start formation
		setHangman(Score.START);
		
		// Retrieve a random word from the text file
		BufferedReader reader;
		try {
			// Open the file
			reader = new BufferedReader(new FileReader("words.txt"));
			
			// Read the first line
			String line = reader.readLine();

			while (line != null) {
				// Save the word to the array
				words.add(line);
				
				// Read the next line from the file
				line = reader.readLine();
			}
			// Close the reader
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// METHOD: method that displays a hangman based on the current player score
	public static void setHangman(Score score) {
		switch(score) {
		case START:
			hangman = " __________\n|          |\n|\n|\n|\n|\n|\n|____________________";
			prompt = "Welcome to Hangman! You have Seven strikes before you're out.\nGuess a letter: ";
			break;
		case ONE:
			hangman = " __________\n|          |\n|          O\n|\n|\n|\n|\n|____________________";
			break;
		case TWO:
			hangman = " __________\n|          |\n|          O\n|          |\n|\n|\n|\n|____________________";
			break;
		case THREE:
			hangman = " __________\n|          |\n|          O\n|          |\n|        --\n|\n|\n|____________________";
			break;
		case FOUR:
			hangman = " __________\n|          |\n|          O\n|          |\n|        -----\n|\n|\n|____________________";
			break;
		case FIVE:
			hangman = " __________\n|          |\n|          O\n|          |\n|        -----\n|          |\n|\n|____________________";
			break;
		case SIX:
			hangman = " __________\n|          |\n|          O\n|          |\n|        -----\n|          |\n|         /\n|____________________";
			break;
		case SEVEN:
			hangman = " __________\n|          |\n|          O\n|          |\n|        -----\n|          |\n|         / \\\n|____________________";
			break;
		case END:
			// Do something
			break;
		default:
			break;
		}
	}
	
	
	// METHOD: method that displays the word prompt
	public static void displayWord() {
		System.out.println("\n\n_ _ _ _ _\n\n");
	}
	

	public static void main(String[] args) {
		// Print out the hangman
//		System.out.println(hangman);
//		displayWord();
//		System.out.print(prompt);
		initialize();
		System.out.print(words);
	}

}
