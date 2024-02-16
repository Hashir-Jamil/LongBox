package org.longbox.presentation.authentication;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

import lombok.*;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Getter
@Setter

public class LoginPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField usernameText;
	private JPasswordField passwordField;
	private JButton signInButton;
	private JButton signUpButton;
	private JLabel errorLabel;
	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		initLoginPage();
	}
	
	public void initLoginPage() {
		
		setBounds(100, 100, 809, 554);

		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
	    panel.setLayout(null);
		
		//Welcome label
		JLabel welcomeLabel = new JLabel("Welcome Back to LongBox!");
		welcomeLabel.setForeground(new Color(0, 0, 0));
		welcomeLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		welcomeLabel.setBounds(300, 52, 208, 16);
		panel.add(welcomeLabel);
		
		//username entry
		JLabel usernameLabel = new JLabel("Please enter your username:");
		usernameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		usernameLabel.setBounds(205, 147, 215, 16);
		panel.add(usernameLabel);
		
		usernameText = new JTextField();
		usernameText.setBounds(205, 159, 398, 26);
		panel.add(usernameText);
		usernameText.setColumns(10);
		
		//password entry
		JLabel passwordLabel = new JLabel("Please enter your password:");
		passwordLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		passwordLabel.setVerticalAlignment(SwingConstants.TOP);
		passwordLabel.setBounds(205, 216, 153, 16);
		panel.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(205, 233, 398, 26);
		panel.add(passwordField);
		
		//Sign in button
		signInButton = new JButton("Sign in!");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		signInButton.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		signInButton.setBounds(345, 300, 117, 29);
		panel.add(signInButton);
		
		//sign up label
		JLabel signUpLabel = new JLabel("New user? Sign up now!");
		signUpLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 13));
		signUpLabel.setBounds(335, 337, 141, 16);
		panel.add(signUpLabel);
		signInButton.setFocusable(false);
		
		//sign up button
		signUpButton = new JButton("Sign Up!");
		signUpButton.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		signUpButton.setBounds(345, 361, 117, 29);
		panel.add(signUpButton);
		signUpButton.setFocusable(false);
		
		add(panel, BorderLayout.CENTER);
		
		// error label
		errorLabel = new JLabel("");
		errorLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		errorLabel.setBounds(205, 272, 398, 16);
		panel.add(errorLabel);
	}
	
	public JButton getSignUpButton() {
	    return signUpButton;
	}
	
	public JButton getSignInButton() {
		return signInButton;
	}
	
	public JLabel getErrorLabel() {
		return errorLabel;
	}
	
	public String getUsername() {
		return usernameText.getText();
	}
	
	public String getDecryptedPassword() {
		return String.valueOf(passwordField.getPassword());
	}
	
}
