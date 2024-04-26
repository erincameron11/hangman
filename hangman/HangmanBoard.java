package hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HangmanBoard {
	// Define variables
	public static String hangman = ""; // The display output for the hangman
	public static String prompt = ""; // The prompt to display to the user each turn
	public static int score = 0; // The score of the game -- 7 is a loss
	public static String word = ""; // The word for the user to guess
	public static ArrayList<String> words = new ArrayList<String>(); // The array to hold the words
	public static char[] wordChars; // The array to update the word spaces
	public static char[] answer; // The answer key in char array format
	
	// ENUM: for each turn
	public enum Score {
		START, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN
	}
	
	
	// METHOD: method to initialize the word
	public static void initialize() {
		// Initialize the hangman to the Start formation
		setHangman(0);
		
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
		
		// Set the answer key
		answer = word.toCharArray();
		
		// Turn the chosen word into a char array
		wordChars = word.toCharArray();
	}
	
	
	// METHOD: method that displays a hangman based on the current player score
	public static void setHangman(int score) {
		switch(score) {
		case 0:
			hangman = " __________\n|          |\n|\n|\n|\n|\n|\n|____________________";
			prompt = "\n\n======================================\nGood work! Guess another letter: ";
			break;
		case 1:
			hangman = " __________\n|          |\n|          O\n|\n|\n|\n|\n|____________________";
			prompt = "\n\nOuch! Strike 1\nGuess another letter: ";
			break;
		case 2:
			hangman = " __________\n|          |\n|          O\n|          |\n|\n|\n|\n|____________________";
			break;
		case 3:
			hangman = " __________\n|          |\n|          O\n|          |\n|        --\n|\n|\n|____________________";
			break;
		case 4:
			hangman = " __________\n|          |\n|          O\n|          |\n|        -----\n|\n|\n|____________________";
			break;
		case 5:
			hangman = " __________\n|          |\n|          O\n|          |\n|        -----\n|          |\n|\n|____________________";
			break;
		case 6:
			hangman = " __________\n|          |\n|          O\n|          |\n|        -----\n|          |\n|         /\n|____________________";
			break;
		case 7:
			hangman = " __________\n|          |\n|          O\n|          |\n|        -----\n|          |\n|         / \\\n|____________________";
			prompt = "\nGAME OVER!\nThat's strike 7. Better luck next time";
			break;
		default:
			break;
		}
	}
	
	
	// METHOD: method that sets the word spaces dynamically
	public static void setWordSpaces(char guess) {	
		// Define variables
		int exclamations = 0;
		
		// Loop through the new char array
		for(int i = 0; i < wordChars.length; i++) {
			// If the value equals the guess
			if(wordChars[i] == guess) {
				// Replace the value with our signifier (exclamation mark)
				wordChars[i] = '!';
				exclamations++;
			}
		}
		
		// If we have a mismatched number of exclamations to score values
		if(exclamations == 0) {
			// Increase the score
			score++;
		}
		
		// Call the hangman
		setHangman(score);
	}
	
	
	// METHOD: method that displays the word prompt
	public static void displayWord() {
		// Define variables
		System.out.println();
		
		// Loop through the array
		for(int i = 0; i < wordChars.length; i++) {
			// If we have a ! value
			if(wordChars[i] == '!') {
				System.out.print(answer[i] + " ");
			} else {
				System.out.print("_ ");
			}
		}
	}
	

	public static void main(String[] args) {
		// Create a Scanner
		Scanner input = new Scanner(System.in);
		
		// Define variables
		String strGuess;
		char guess;
		
		// Initialize the game
		initialize();
//		System.out.println(word);
		displayWord();
		System.out.println("\n\n" + hangman);
		System.out.println("\nWelcome to Hangman! You have Seven strikes before you're out.\n\nGuess a letter: ");
		
		while(true) {
			strGuess = input.next().toUpperCase();
			guess = strGuess.charAt(0);
			setWordSpaces(guess);
			
			System.out.println("\n" + hangman);
			displayWord();
			System.out.println(prompt);
			
			if(score == 7) {
				break;
			}
		}
		
//		word = "HAPPY";
//		answer = word.toCharArray();
//		System.out.println(word);
//		char guess = 'Y';
//		setWordSpaces(guess);
		
		// Close the input
		input.close();
	}

}
