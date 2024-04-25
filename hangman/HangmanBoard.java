package hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanBoard {
	// Define variables
	public static String hangman = ""; // The display output for the hangman
	public static String prompt = ""; // The prompt to display to the user each turn
	public static int score = 0; // The score of the game -- 7 is a loss
	public static String word = ""; // The word for the user to guess
	public static ArrayList<String> words = new ArrayList<String>(); // The array to hold the words
	
	
	// ENUM: for each turn
	public enum Score {
		START, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN
	}
	
	
	// METHOD: method to initialize the word
	public static void initialize() {
		// Initialize the hangman to the Start formation
		setHangman(Score.START);
		
		// Retrieve a random word from the text file
		BufferedReader buffReader;
		try {
			// Open the file
			buffReader = new BufferedReader(new FileReader("words.txt"));
			
			// Read the first line
			String line = buffReader.readLine();

			while (line != null) {
				// Save the word to the array
				words.add(line);
				
				// Read the next line from the file
				line = buffReader.readLine();
			}
			// Close the buffered reader
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Pick a random number between 0-length of the arraylist
		int randomWord = (int) Math.floor(Math.random() * (words.size() - 0));
		
		// Set the word to the random index
		word = words.get(randomWord);
		
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
			prompt = "GAME OVER!\nThat's strike 7. Better luck next time";
			break;
		default:
			break;
		}
	}
	
	
	// METHOD: method that sets the word spaces dynamically
	public static void setWordSpaces(char guess) {
		// Define variables
//		int spaces = word.length();
		
		// Put each char of the word into an array
		char[] ch = word.toCharArray();
		int index = 0;
		ArrayList<Character> charArray = new ArrayList<Character>();
		for(int i = 0; i < word.length(); i++) {
			charArray.add(ch[i]);
		}
		
		if(charArray.contains(guess)) {
//			charArray.remove();
		}
		System.out.println(charArray);
		
		
//		for(int i = 0; i < charArray.size(); i++) {
//			if(charArray.contains(guess)) {
//				System.out.print("it's in here");
//			} else {
//				System.out.print("it's NOT in here");
//			}
//		}
		
		
		
		
		
//		// Create a new array to tell if we have the letter yet
//		boolean[] guesses = new boolean[spaces];
//		// Set them all to false
//		for(int i = 0; i < guesses.length; i++) {
//			guesses[i] = false;
//		}
		
	}
	
	
	// METHOD: method that displays the word prompt
	public static void displayWord() {
		int spaces = word.length();
		
	}
	

	public static void main(String[] args) {
		// Print out the hangman
//		System.out.println(hangman);
//		displayWord();
//		System.out.print(prompt);
		
//		initialize();
		
		word = "HAPPY";
		System.out.println(word);
		char guess = 'A';
		setWordSpaces(guess);
	}

}
