package com.hoffmann.gggui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

@SuppressWarnings("serial")
public class GuessingGameGUI extends JFrame {
	// Text field for the user input
	private JTextField txtInput;

	// Label for the output
	private JLabel lblOutput;

	// Computer's number that user is trying to guess
	private int theNumber;
	private int tries;
	
	private String message = "";

	public GuessingGameGUI() {
		getContentPane().setBackground(new Color(51, 204, 0));
		setBackground(new Color(51, 204, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\OxygenWorkSpaces\\GuessingGameGUI\\images\\myIcon.png"));
		setTitle("Bet You Can't Guess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblDavesHiloGuessing = new JLabel("Dave's Hi-Lo Guessing Game");
		lblDavesHiloGuessing.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDavesHiloGuessing.setBounds(46, 27, 340, 26);
		lblDavesHiloGuessing.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblDavesHiloGuessing);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 0));
		panel.setBounds(70, 80, 293, 49);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblGuessANumber = new JLabel("Guess a number between 1 and 100: ");
		lblGuessANumber.setBounds(12, 16, 216, 16);
		panel.add(lblGuessANumber);
		lblGuessANumber.setHorizontalAlignment(SwingConstants.CENTER);

		txtInput = new JTextField();
		txtInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkGuess();
			}
		});
		txtInput.setBounds(228, 13, 52, 22);
		panel.add(txtInput);
		txtInput.setHorizontalAlignment(SwingConstants.CENTER);
		txtInput.setColumns(10);

		JButton btnGuess = new JButton("Guess");
		btnGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkGuess();
			}
		});
		btnGuess.setBounds(168, 156, 97, 25);
		getContentPane().add(btnGuess);
				
						lblOutput = new JLabel("Enter a number into the field, you have 7 tries left.");
						lblOutput.setBounds(12, 208, 408, 16);
						getContentPane().add(lblOutput);
						lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public static void main(String[] args) {

		GuessingGameGUI theGame = new GuessingGameGUI();

		theGame.newGame();
		theGame.setSize(new Dimension(430, 330));
		theGame.setVisible(true);

	}

	// Method for checking the user's input
	public void checkGuess() {
		// User's guess
		String guessText = txtInput.getText();
		
		try {

			// Check the user's guess for too high/low
			int guess = Integer.parseInt(guessText);
			
			// Count this as a try
			tries--;
			
			// Conditional to let the user know the proper message
			if (guess > theNumber) {
				message = guess + " was too high. Guess again.";
				message += "You have " + tries + " tries left.";
				lblOutput.setText(message);
			} else if (guess < theNumber) {
				message = guess + " was too low. Guess again.";
				message += "You have " + tries + " tries left.";
				lblOutput.setText(message);
			} else {
				message = guess + " is correct! You won! Let's play again!";
				message += " You had " + tries + " tries left.";
				lblOutput.setText(message);
				txtInput.setText("");
				newGame();
			}
			
			if (tries <= 0) {
				
				int option = javax.swing.JOptionPane.showConfirmDialog(this, "Sorry, you ran out of tries. The number was " + theNumber + ". Play again?", "Play again?", 0);
				
				if (option == 0) {
					newGame();
				}else {
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				}
				
				
			}
		} catch (Exception e) {
			lblOutput.setText("Please enter a whole number between 1-100. Try again.");
		} finally {
			txtInput.requestFocus();
			txtInput.selectAll();
		}

	}

	// A new random number 1...100
	public void newGame() {

		theNumber = (int) (Math.random() * 100 + 1);

		tries = 7;
		
		txtInput.setText("");
		
		message = "Enter a number into the field, you have " + tries + " tries left.";
		lblOutput.setText(message);
		
	}
}
