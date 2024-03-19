package org.longbox.presentation.authentication;


import com.toedter.calendar.JDateChooser;
import lombok.Getter;
import lombok.Setter;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.businesslogic.utils.RegistrationUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
public class RegistrationPanel extends JPanel {
	private static final String DEFAULT_FONT = "Calibri";
	private static final String HEADER_TEXT = "Sign Up and Start Reading Comics!";
	private static final String FIRST_NAME_TEXT = "Your first name:";
	private static final String LAST_NAME_TEXT = "Your last name:";
	private static final String USERNAME_TEXT = "Your username (must be unique and not contain '@'):";
	private static final String EMAIL_TEXT = "Your email (must be unique):";
	private static final String PASSWORD_TEXT = "Pick a password:";
	private static final String PASSWORD_SPEC_TEXT = "Use at least one lowercase letter, one capital letter, one numeral, one special character and 8 - 20 characters.";
	private static final String DOB_TEXT = "Your date of birth:";
	private static final String COUNTRY_TEXT = "Choose your country:";
	private static final String SIGN_UP_BUTTON_TEXT = "Sign Up for LongBox!";
	private static final String TnC_TEXT = "I agree to the terms and conditions of this app.";
	private static final String EMPTY = "";
	private static final String SIGN_IN_TEXT = "Already a member? Sign In!";
	private static final String SIGN_IN_BUTTON_TEXT = "Sign In!";
	private static final String MANDATORY_TEXT = "All fields are mandatory.";

	private static final int X_117 = 117;
	private static final int X_107 = 107;
	private static final int X_445 = 445;
	private static final int X_451 = 451;
	private static final int SIZE_16 = 16;
	private static final int SIZE_12 = 12;
	private static final int HEIGHT_26 = 26;
	private static final int Y_124 = 124;
	private static final int Y_141 = 141;

	private JTextField firstNameField, lastNameField, usernameField, emailAddress;
	private JPasswordField passwordField;
	private JButton signUpButton, signInButton;
	private JDateChooser dateChooser;
	private JComboBox countryField;
	private JCheckBox TnCCheckbox;
	private JLabel messageLabel, informationLabel, headerLabel, firstNameLabel , lastNameLabel, userNameLabel,
			emailLabel, pwdSpecLabel, dateLabel, passwordLabel, countryLabel, signInLabel;

	public RegistrationPanel() {
		initiateRegUI();
	}

	public void initiateRegUI() {
		setBounds(100, 100, 809, 554);
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(null);

		//Header label
		headerLabel = new JLabel(HEADER_TEXT);
		headerLabel.setBounds(265, 17, 279, HEIGHT_26);
		headerLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, SIZE_16));

		//first name label
		firstNameLabel = new JLabel(FIRST_NAME_TEXT);
		firstNameLabel.setBounds(X_117, 70, 94, SIZE_16);
		firstNameLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, SIZE_12));

		firstNameField = new JTextField();
		firstNameField.setBounds(X_107, 86, 267, HEIGHT_26);
		firstNameField.setColumns(10);

		//last name label
		lastNameLabel = new JLabel(LAST_NAME_TEXT);
		lastNameLabel.setBounds(X_451, 70, 94, SIZE_16);
		lastNameLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, SIZE_12));

		lastNameField = new JTextField();
		lastNameField.setBounds(X_445, 86, 260, HEIGHT_26);
		lastNameField.setColumns(10);

		//username label
		userNameLabel = new JLabel(USERNAME_TEXT);
		userNameLabel.setBounds(X_117, 187, 335, SIZE_16);
		userNameLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, SIZE_12));

		usernameField = new JTextField();
		usernameField.setBounds(X_107, 206, 598, HEIGHT_26);
		usernameField.setColumns(10);

		//email label
		emailLabel = new JLabel(EMAIL_TEXT);
		emailLabel.setBounds(X_117, 244, 176, SIZE_16);
		emailLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, SIZE_12));

		emailAddress = new JTextField();
		emailAddress.setBounds(X_107, 260, 598, HEIGHT_26);
		emailAddress.setColumns(10);

		//password label
		passwordLabel = new JLabel(PASSWORD_TEXT);
		passwordLabel.setBounds(X_117, 298, 176, SIZE_16);
		passwordLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, SIZE_12));

		passwordField = new JPasswordField();
		passwordField.setBounds(X_107, 316, 598, HEIGHT_26);

		//password specification label
		pwdSpecLabel = new JLabel(PASSWORD_SPEC_TEXT);
		pwdSpecLabel.setBounds(X_117, 354, 588, 13);
		pwdSpecLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 10));

		//date entering
		dateLabel = new JLabel(DOB_TEXT);
		dateLabel.setBounds(X_117, Y_124, 106, SIZE_16);
		dateLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, SIZE_12));

		dateChooser = new JDateChooser();
		dateChooser.setBounds(X_107, Y_141, 267, HEIGHT_26);

		// choose country
		countryLabel = new JLabel(COUNTRY_TEXT);
		countryLabel.setBounds(X_451, Y_124, 134, SIZE_16);
		countryLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, SIZE_12));

		countryField = new JComboBox(getAllCountries());
		countryField.setBounds(X_445, Y_141, 260, 27);

		//sign up button
		signUpButton = new JButton(SIGN_UP_BUTTON_TEXT);
		signUpButton.setBounds(X_107, 422, 608, 29);
		signUpButton.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 13));
		signUpButton.setEnabled(false);
		signUpButton.setFocusable(false);

		//Terms and Conditions check box
		TnCCheckbox = new JCheckBox(TnC_TEXT);
		TnCCheckbox.setBounds(X_107, 387, 598, 23);
		TnCCheckbox.setFont(new Font(DEFAULT_FONT, Font.PLAIN, SIZE_12));

		// message label to print invalid email and password
		messageLabel = new JLabel(EMPTY);
		messageLabel.setVerticalTextPosition(SwingConstants.TOP);
		messageLabel.setBounds(X_117, 370, 588, SIZE_16);
		messageLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		messageLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, SIZE_12));

		//sign in label
		signInLabel = new JLabel(SIGN_IN_TEXT);
		signInLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 13));
		signInLabel.setBounds(X_117, 463, 287, SIZE_16);

		//sign up button
		signInButton = new JButton(SIGN_IN_BUTTON_TEXT);
		signInButton.setFont(new Font(DEFAULT_FONT, Font.PLAIN, SIZE_12));
		signInButton.setBounds(X_451, 458, 264, 29);

		//info label
		informationLabel = new JLabel(MANDATORY_TEXT);
		informationLabel.setFont(new Font("Lucida Grande", Font.ITALIC, 11));
		informationLabel.setBounds(339, 43, 131, SIZE_16);

		// adding elements to the pane
		panel.add(headerLabel);
		panel.add(firstNameLabel);
		panel.add(firstNameField);
		panel.add(lastNameLabel);
		panel.add(lastNameField);
		panel.add(userNameLabel);
		panel.add(usernameField);
		panel.add(emailLabel);
		panel.add(emailAddress);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(pwdSpecLabel);
		panel.add(dateLabel);
		panel.add(dateChooser);
		panel.add(countryLabel);
		panel.add(countryField);
		panel.add(signUpButton);
		panel.add(TnCCheckbox);
		panel.add(messageLabel);
		panel.add(signInLabel);
		panel.add(signInButton);
		panel.add(informationLabel);

		add(panel, BorderLayout.CENTER);

		// field listener
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
				RegistrationUtils.isValidUserName(usernameField.getText()) &&
				RegistrationUtils.isValidEmailAddress(emailAddress.getText()) &&
				RegistrationUtils.isValidPassword(String.valueOf(passwordField.getPassword())) &&
				TnCCheckbox.isSelected();

		boolean validUsername = RegistrationUtils.isValidUserName(usernameField.getText());
		boolean validEmail = RegistrationUtils.isValidEmailAddress(emailAddress.getText());
		boolean validPassword = RegistrationUtils.isValidPassword(String.valueOf(passwordField.getPassword()));

		// prints the invalid mail and email message
		if(!validEmail && !validPassword && !validUsername) {
			messageLabel.setForeground(Color.red);
			messageLabel.setText("Please enter a valid user name, a valid email and a valid password!");
		} else if(!validEmail && !validPassword && validUsername) {
			messageLabel.setForeground(Color.red);
			messageLabel.setText("Please enter a valid email and a valid password!");
		}else if(validEmail && !validPassword && !validUsername) {
			messageLabel.setForeground(Color.red);
			messageLabel.setText("Please enter a valid user name and a valid password!");
		}else if(!validEmail && validPassword && !validUsername) {
			messageLabel.setForeground(Color.red);
			messageLabel.setText("Please enter a valid user name and a valid email!");
		}else if(validEmail && !validPassword && validUsername){
			messageLabel.setText("Please enter a valid password!");
		}else if(!validEmail && validPassword && validUsername) {
			messageLabel.setText("Please enter a valid email!");
		} else if(validEmail && validPassword && !validUsername) {
			messageLabel.setText("Please enter a valid user name!");
		}else {
			messageLabel.setText("");
		}

		//if all the conditions are matched enable the button
		signUpButton.setEnabled(enableButton);
	}

	public UserDto getRegistrationDetails() {
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		Date dob = dateChooser.getDate();
		String username = usernameField.getText();
		String email = emailAddress.getText();
		String password = String.valueOf(passwordField.getPassword());
		String country = countryField.getSelectedItem().toString();

		return new UserDto(username, firstName, lastName, dob, email, password, country);
	}
}
