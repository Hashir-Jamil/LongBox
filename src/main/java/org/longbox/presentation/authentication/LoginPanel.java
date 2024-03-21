package org.longbox.presentation.authentication;

import lombok.Getter;
import lombok.Setter;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Getter
@Setter
public class LoginPanel extends JPanel{
	private static final String DEFAULT_FONT = "Calibri";
	private static final String WELCOME_TEXT = "Welcome Back to LongBox!";
	private static final String USERNAME_TEXT = "Please enter your username:";
	private static final String PASSWORD_TEXT = "Please enter your password:";
	private static final String SIGN_IN_BUTTON_TEXT = "Sign in!";
	private static final String SIGN_UP_TEXT = "New user? Sign up now!";
	private static final String SIGN_UP_BUTTON_TEXT = "Sign Up!";
	private static final String EMPTY = "";

	private static final int X_208 = 208;
	private static final int X_205 = 205;
	private static final int X_345 = 345;

	private static final int HEIGHT_16 = 16;
	private static final int HEIGHT_26 = 26;

	private static final int WIDTH_215 = 215;
	private static final int WIDTH_398 = 398;
	private static final int WIDTH_117 = 117;

	private JTextField usernameText;
	private JPasswordField passwordField;
	private JButton signInButton, signUpButton;
	private JLabel errorLabel, welcomeLabel, usernameLabel, passwordLabel, signUpLabel;

	public LoginPanel() {
		initLoginPage();
	}
	
	public void initLoginPage() {
		setBounds(100, 100, 809, 664);

		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
	    panel.setLayout(null);
		
		//Welcome label
		welcomeLabel = new JLabel(WELCOME_TEXT);
		welcomeLabel.setForeground(new Color(0, 0, 0));
		welcomeLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		welcomeLabel.setBounds(297, 164, WIDTH_215, HEIGHT_26);
		panel.add(welcomeLabel);
		
		//username entry
		usernameLabel = new JLabel(USERNAME_TEXT);
		usernameLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 12));
		usernameLabel.setBounds(208, 243, WIDTH_215, HEIGHT_16);
		panel.add(usernameLabel);
		
		usernameText = new JTextField();
		usernameText.setBounds(205, 272, WIDTH_398, HEIGHT_26);
		panel.add(usernameText);
		usernameText.setColumns(10);
		
		//password entry
		passwordLabel = new JLabel(PASSWORD_TEXT);
		passwordLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 12));
		passwordLabel.setVerticalAlignment(SwingConstants.TOP);
		passwordLabel.setBounds(208, 326, 172, HEIGHT_16);
		panel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(205, 347, WIDTH_398, HEIGHT_26);
		panel.add(passwordField);
		
		//Sign in button
		signInButton = new JButton(SIGN_IN_BUTTON_TEXT);
		signInButton.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 12));
		signInButton.setBounds(345, 396, WIDTH_117, 29);
		panel.add(signInButton);
		
		//sign up label
		signUpLabel = new JLabel(SIGN_UP_TEXT);
		signUpLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 13));
		signUpLabel.setBounds(331, 437, 148, HEIGHT_16);
		panel.add(signUpLabel);
		signInButton.setFocusable(false);
		
		//sign up button
		signUpButton = new JButton(SIGN_UP_BUTTON_TEXT);
		signUpButton.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 12));
		signUpButton.setBounds(345, 465, WIDTH_117, 29);
		panel.add(signUpButton);
		signUpButton.setFocusable(false);
		
		add(panel, BorderLayout.CENTER);
		
		// error label
		errorLabel = new JLabel(EMPTY);
		errorLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 12));
		errorLabel.setBounds(208, 373, 395, 16);
		panel.add(errorLabel);
	}

	public String getUsername() {
		return usernameText.getText();
	}

	public String getDecryptedPassword() {
		return String.valueOf(passwordField.getPassword());
	}
}
