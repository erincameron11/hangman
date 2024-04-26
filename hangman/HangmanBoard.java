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
	public static String word = ""; // The word for the user to guess
	public static int strikes = 0; // The score of the game -- 7 is a loss
	public static int exclamations = 0; // Track the number of exclamations
	public static ArrayList<String> words = new ArrayList<String>(); // The array to hold the words form file
	public static ArrayList<Character> lettersGuessed = new ArrayList<Character>(); // The letters guessed by user in char array format
	public static char[] wordChars; // The array to update the word spaces
	public static char[] answer; // The answer key in char array format
	public static boolean win = false; // The flag to signify when a user has won
	
	
	// METHOD: method to initialize the application
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
		
		// Pick a random number between 0 -> length of the arraylist
		int randomWord = (int) Math.floor(Math.random() * (words.size() - 0));
		
		// Set the word to the random index
		word = words.get(randomWord);
		
		// Set the answer key
		answer = word.toCharArray();
		
		// Turn the chosen word into a char array
		wordChars = word.toCharArray();
	}
	
	
	// METHOD: method that displays a hangman based on the current player strikes
	public static void setHangman(int strikes) {
		switch(strikes) {
		case 0:
			hangman = " __________\n|          |\n|\n|\n|\n|\n|\n|____________________";
			break;
		case 1:
			hangman = " __________\n|          |\n|          O\n|\n|\n|\n|\n|____________________";
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
			break;
		default:
			break;
		}
	}

	
	// METHOD: method that sets the word spaces dynamically
	public static void setWordSpaces(char guess) {	
		// Define variables
		int count = 0;
		
		// Loop through the new char array
		for(int i = 0; i < wordChars.length; i++) {
			// If the value equals the guess
			if(wordChars[i] == guess) {
				// Replace the value with our signifier (exclamation mark)
				wordChars[i] = '!';
				count++;
				exclamations++;
			}
		}
		
		// If the user does not guess correctly
		if(count == 0) {
			// Increase the strikes
			strikes++;
		}
		
		// If the user has guessed all letters
		if(exclamations == word.length()) {
			// They win!
			win = true;
		}
		
		// Update the prompt and call the hangman to draw the figure
		prompt = "\n\nStrikes = " + strikes + "\nLetters Guessed: " + lettersGuessed + "\nGuess another letter: ";
		setHangman(strikes);
	}
	
	
	// METHOD: method that displays the word prompt to the console dynamically
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
	

	// METHOD: Main method for running the application
	public static void main(String[] args) {
		// Create a Scanner
		Scanner input = new Scanner(System.in);
		
		// Define variables
		char guess;
		
		// Initialize the game
		initialize();
		
		System.out.print(word + "\n\n\n\n\n\n");
		
		// Print out a title
		System.out.println("\nHANGMAN\n=================================\n" + hangman);
		
		// Display the word spaces for guessing
		displayWord();
		
		// Display a prompt
		prompt = "\n\nWelcome to Hangman! You have 7 strikes before you're out.\n\nGuess a letter: ";
		System.out.println(prompt);
		
		// Loop until we have a winner, or a loser
		while(true) {			
			// Get the guess from the user
			guess = input.next().toUpperCase().charAt(0);
			
			// Add the guess to our guess list
			lettersGuessed.add(guess);
			
			// Set the display of letters guessed
			setWordSpaces(guess);
			
			// Print the hangman
			System.out.println("\n\n\n" + hangman);
			
			// Display the letters guessed
			displayWord();
			
			// If we have a winner
			if(win) {
				prompt = "\n\nWINNER!\nStrikes = " + strikes + "\nLetters Guessed: " + lettersGuessed + "\n\n\nThe answer was: " + word;
				System.out.print(prompt);
				break;
			}
			
			// If we have a loser
			if(strikes == 7) {
				prompt = "\n\nGAME OVER!\nStrikes = " + strikes + "\nLetters Guessed: " + lettersGuessed + "\nBetter luck next time.\n\nThe answer was: " + word;
				System.out.print(prompt);
				break;
			}
			
			// Display the prompt
			System.out.println(prompt);
		}
		
		// Close the input
		input.close();
	}
}
