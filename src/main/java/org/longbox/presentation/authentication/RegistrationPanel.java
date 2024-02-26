package org.longbox.presentation.authentication;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.longbox.domainobjects.dto.UserDTO;
import org.longbox.businesslogic.utils.RegistrationUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.Date;
import java.util.Locale;


import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegistrationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField usernameField;
	private JTextField emailAddress;
	private JPasswordField passwordField;
	private JButton signUpButton;
	private JDateChooser dateChooser;
	private JComboBox countryField;
	private JCheckBox TnCCheckbox;
	private JLabel messageLabel;
	private JButton signInButton;
	/**
	 * Create the panel.
	 */
	public RegistrationPanel() {
		initiateRegUI();
	}

	public void initiateRegUI() {


		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(null);

		//Header label
		JLabel HeaderLabel = new JLabel("Sign Up and Start Reading Comics!");
		HeaderLabel.setBounds(275, 34, 259, 16);
		HeaderLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));

		//first name label
		JLabel FirstNameLabel = new JLabel("Your first name:");
		FirstNameLabel.setBounds(117, 70, 94, 16);
		FirstNameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));

		firstNameField = new JTextField();
		firstNameField.setBounds(107, 86, 267, 26);
		firstNameField.setColumns(10);

		//last name label
		JLabel LastNameLabel = new JLabel("Your last name:");
		LastNameLabel.setBounds(451, 70, 83, 16);
		LastNameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));

		lastNameField = new JTextField();
		lastNameField.setBounds(445, 86, 260, 26);
		lastNameField.setColumns(10);

		//username label
		JLabel UsernameLabel = new JLabel("Your username(must be unique):");
		UsernameLabel.setBounds(117, 187, 181, 16);
		UsernameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));

		usernameField = new JTextField();
		usernameField.setBounds(107, 206, 598, 26);
		usernameField.setColumns(10);

		//email label
		JLabel EmailLabel = new JLabel("Your email:");
		EmailLabel.setBounds(117, 244, 61, 16);
		EmailLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));

		emailAddress = new JTextField();
		emailAddress.setBounds(107, 260, 598, 26);
		emailAddress.setColumns(10);

		//password label
		JLabel PasswordLabel = new JLabel("Pick a password:");
		PasswordLabel.setBounds(117, 298, 86, 16);
		PasswordLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));

		passwordField = new JPasswordField();
		passwordField.setBounds(107, 316, 598, 26);

		//password specification label
		JLabel PwdSpecLabel = new JLabel("Use at least one lowercase letter, one capital letter, one numeral, one special charachter and 8 - 20 characters.");
		PwdSpecLabel.setBounds(117, 354, 588, 13);
		PwdSpecLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 10));

		//date entering
		JLabel DateLabel = new JLabel("Your date of birth:");
		DateLabel.setBounds(117, 124, 94, 16);
		DateLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));

		dateChooser = new JDateChooser();
		dateChooser.setBounds(107, 141, 267, 26);

		// choose country
		JLabel CountryLabel = new JLabel("Choose your country:");
		CountryLabel.setBounds(451, 124, 110, 16);
		CountryLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));

		countryField = new JComboBox(getAllCountries());
		countryField.setBounds(445, 141, 260, 27);

		//sign up button
		signUpButton = new JButton("Sign Up for LongBox!");
		signUpButton.setBounds(107, 422, 608, 29);
		signUpButton.setFont(new Font("Bradley Hand", Font.PLAIN, 13));
		signUpButton.setEnabled(false);
		signUpButton.setFocusable(false);

		//Terms and Conditions check box
		TnCCheckbox = new JCheckBox("I agree to the terms and conditions of these app.");
		TnCCheckbox.setBounds(107, 387, 598, 23);
		TnCCheckbox.setFont(new Font("Bradley Hand", Font.PLAIN, 12));

		// message label to print invalid email and password
		messageLabel = new JLabel("");
		messageLabel.setVerticalTextPosition(SwingConstants.TOP);
		messageLabel.setBounds(117, 370, 588, 16);
		messageLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		messageLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));

		//sign in label
		JLabel signInLabel = new JLabel("Already a member? Sign In!");
		signInLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 13));
		signInLabel.setBounds(117, 463, 287, 16);


		//sign up label
		signInButton = new JButton("Sign In!");
		signInButton.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		signInButton.setBounds(451, 458, 264, 29);
		//	signInButton.addActionListener(this);

		// adding elements to the pane

		panel.add(HeaderLabel);
		panel.add(FirstNameLabel);
		panel.add(firstNameField);
		panel.add(LastNameLabel);
		panel.add(lastNameField);
		panel.add(UsernameLabel);
		panel.add(usernameField);
		panel.add(EmailLabel);
		panel.add(emailAddress);
		panel.add(PasswordLabel);
		panel.add(passwordField);
		panel.add(PwdSpecLabel);
		panel.add(DateLabel);
		panel.add(dateChooser);
		panel.add(CountryLabel);
		panel.add(countryField);
		panel.add(signUpButton);
		panel.add(TnCCheckbox);
		panel.add(messageLabel);
		panel.add(signInLabel);
		panel.add(signInButton);

		add(panel, BorderLayout.CENTER);

		// action listener
		firstNameField.addActionListener(fieldsListener);
		lastNameField.addActionListener(fieldsListener);
		usernameField.addActionListener(fieldsListener);
		emailAddress.addActionListener(fieldsListener);
		passwordField.addActionListener(fieldsListener);
		countryField.addActionListener(fieldsListener);
		TnCCheckbox.addActionListener(fieldsListener);

		// focus adapter
		firstNameField.addFocusListener(focusAdapter);
		emailAddress.addFocusListener(focusAdapter);
		passwordField.addFocusListener(focusAdapter);
		dateChooser.addFocusListener(focusAdapter);
		usernameField.addFocusListener(focusAdapter);
		lastNameField.addFocusListener(focusAdapter);


		//Document listener for fields
		firstNameField.getDocument().addDocumentListener(documentListener);
		lastNameField.getDocument().addDocumentListener(documentListener);
		usernameField.getDocument().addDocumentListener(documentListener);
		emailAddress.getDocument().addDocumentListener(documentListener);
		passwordField.getDocument().addDocumentListener(documentListener);

		updateButtonState();
	}

	// used the below code from https://stackoverflow.com/questions/19004303/populate-a-choice-menu-with-all-countries-to-in-java
	@SuppressWarnings("deprecation")
	public String[] getAllCountries() {
		String[] countries = new String[Locale.getISOCountries().length];
		String[] countryCodes = Locale.getISOCountries();
		for (int i = 0; i < countryCodes.length; i++) {
			Locale obj = new Locale("", countryCodes[i]);
			countries[i] = obj.getDisplayCountry();
		}
		return countries;
	}

	ActionListener fieldsListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateButtonState();
		}
	};

	FocusAdapter focusAdapter = new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent e) {
			updateButtonState();
		}
	};

	DocumentListener documentListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) { 	
			updateButtonState();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			updateButtonState();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			updateButtonState();
		}
	};

	private void updateButtonState() {
		// Enable the button only if all fields are non-empty
		boolean enableButton = !firstNameField.getText().isEmpty() &&
				!lastNameField.getText().isEmpty() &&
				!usernameField.getText().isEmpty() && 
				!emailAddress.getText().isEmpty() &&
				passwordField.getPassword().length > 0 &&
				countryField.getSelectedItem() != null &&
				dateChooser.getDate() != null &&
				RegistrationUtils.isValidEmailAddress(emailAddress.getText()) &&
				RegistrationUtils.isValidPassword(String.valueOf(passwordField.getPassword())) &&
				TnCCheckbox.isSelected();

				boolean validEmail = RegistrationUtils.isValidEmailAddress(emailAddress.getText());
				boolean validPassword = RegistrationUtils.isValidPassword(String.valueOf(passwordField.getPassword()));

				// prints the invalid mail and email message
				if(!validEmail && !validPassword) {
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Please enter a valid email and a valid password!");
				}else if(validEmail && !validPassword){
					messageLabel.setText("Please enter a valid password!");
				}else if(!validEmail && validPassword) {
					messageLabel.setText("Please enter a valid email!");
				}else {
					messageLabel.setText("");
				}
				
				//if all the conditions are matched enable the button
				signUpButton.setEnabled(enableButton);
	}

	public JButton getSignInButton() {
		return signInButton;
	}

	public JButton getSignUpButton() {
		return signUpButton;
	}

	public JLabel getMessageLabel() {
		return messageLabel;
	}

	public UserDTO getRegistrationDetails() {
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		Date dob = dateChooser.getDate();
		String username = usernameField.getText();
		String email = emailAddress.getText();
		String password = String.valueOf(passwordField.getPassword());
		String country = countryField.getSelectedItem().toString();

		return new UserDTO(username, firstName, lastName, dob, email, password, country);
	}

}
