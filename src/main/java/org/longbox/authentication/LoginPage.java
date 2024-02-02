package org.longbox.authentication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameText;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.setLayout(null);
		
		//Welcome label
		JLabel welcomeLabel = new JLabel("Welcome Back to LongBox!");
		welcomeLabel.setForeground(new Color(0, 0, 0));
		welcomeLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		welcomeLabel.setBounds(300, 52, 208, 16);
		contentPane.add(welcomeLabel);
		
		//username entry
		JLabel usernameLabel = new JLabel("Please enter your username:");
		usernameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		usernameLabel.setBounds(205, 147, 215, 16);
		contentPane.add(usernameLabel);
		
		usernameText = new JTextField();
		usernameText.setBounds(205, 159, 398, 26);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		//password entry
		JLabel passwordLabel = new JLabel("Please enter your password:");
		passwordLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		passwordLabel.setVerticalAlignment(SwingConstants.TOP);
		passwordLabel.setBounds(205, 229, 153, 16);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(205, 240, 398, 26);
		contentPane.add(passwordField);
		
		//Sign in button
		JButton signInButton = new JButton("Sign in");
		signInButton.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		signInButton.setBounds(354, 304, 117, 29);
		contentPane.add(signInButton);
		
	}
}
