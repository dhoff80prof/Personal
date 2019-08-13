package com.hoffmann.guessing;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {

		// Get ready for the player to use the keyboard
		Scanner scan = new Scanner(System.in);

		// Game loop condition
		String playAgain = "";

		// Game loop
		do {

			// Create a new random number from 1-100
			int theNumber = (int) (Math.random() * 100 + 1);

			int guess = 0;
			
			int tries = 0;

			while (guess != theNumber) {
				System.out.println("Choose a number between 1 and 100: ");

				// Try the scan.nextInt if the user puts anything other than an integer catch
				// the exception and display message
				try {
					// Get the user's guess
					guess = scan.nextInt();
					tries++;
				} catch (InputMismatchException e) {
					// e.printStackTrace();
					System.out.println("\nInvalid entry! Restarting game!\n");
					Driver.main(null);
				}

				// Compare user's guess equals the computer's guess
				if (guess < theNumber) {
					System.out.println("The number is higher than your guess. Try again.");
				} else if (guess > theNumber) {
					System.out.println("The number is lower than your guess. Try again.");
				} else {
					System.out.println(guess + " is correct! You win!");
					System.out.println("It only took you " + tries + " tries! Good job!");
				}

			} // End while loop

			// Ask the user to play again
			System.out.println("Would you like to play again? (y/n)");
			playAgain = scan.next();

		} while (playAgain.equalsIgnoreCase("y"));// End do loop

		// Exit message
		System.out.println("Thanks for playing! Goodbye!");
		
		scan.close();

	}

}
