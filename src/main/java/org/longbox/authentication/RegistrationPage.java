package org.longbox.authentication;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;



public class RegistrationPage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField username;
	private JTextField emailAddress;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationPage frame = new RegistrationPage();
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
	public RegistrationPage() {
		initiateRegUI();
	}

	public void initiateRegUI() {
		//initializing the main frame
		setTitle("LongBox - Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);

		setContentPane(contentPane);

		contentPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.setLayout(null);


		//Header label
		JLabel HeaderLabel = new JLabel("Sign Up and Start Reading Comics!");
		HeaderLabel.setBounds(275, 56, 259, 16);
		HeaderLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		contentPane.add(HeaderLabel);

		//first name label
		JLabel FirstNameLabel = new JLabel("Your first name:");
		FirstNameLabel.setBounds(117, 107, 94, 16);
		FirstNameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		contentPane.add(FirstNameLabel);

		firstName = new JTextField();
		firstName.setBounds(107, 121, 267, 26);
		contentPane.add(firstName);
		firstName.setColumns(10);

		//last name label
		JLabel LastNameLabel = new JLabel("Your last name:");
		LastNameLabel.setBounds(452, 107, 83, 16);
		LastNameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		contentPane.add(LastNameLabel);

		lastName = new JTextField();
		lastName.setBounds(445, 121, 260, 26);
		contentPane.add(lastName);
		lastName.setColumns(10);

		//username label
		JLabel UsernameLabel = new JLabel("Your username:");
		UsernameLabel.setBounds(117, 214, 83, 16);
		UsernameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		contentPane.add(UsernameLabel);

		username = new JTextField();
		username.setBounds(107, 231, 598, 26);
		contentPane.add(username);
		username.setColumns(10);

		//email label
		JLabel EmailLabel = new JLabel("Your email:");
		EmailLabel.setBounds(117, 269, 61, 16);
		EmailLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		contentPane.add(EmailLabel);

		emailAddress = new JTextField();
		emailAddress.setBounds(107, 288, 598, 26);
		contentPane.add(emailAddress);
		emailAddress.setColumns(10);

		//password label
		JLabel PasswordLabel = new JLabel("Pick a password:");
		PasswordLabel.setBounds(116, 326, 86, 16);
		PasswordLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		contentPane.add(PasswordLabel);

		password = new JPasswordField();
		password.setBounds(107, 345, 598, 26);
		contentPane.add(password);

		//password specification label
		JLabel PwdSpecLabel = new JLabel("Use at least one letter, one numeral and seven characters.");
		PwdSpecLabel.setBounds(117, 376, 257, 13);
		PwdSpecLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 10));
		contentPane.add(PwdSpecLabel);

		//date entering
		JLabel DateLabel = new JLabel("Your date of birth:");
		DateLabel.setBounds(117, 160, 94, 16);
		DateLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		contentPane.add(DateLabel);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(107, 176, 267, 26);
		contentPane.add(dateChooser);

		// choose country
		JLabel CountryLabel = new JLabel("Choose your country:");
		CountryLabel.setBounds(455, 159, 110, 16);
		CountryLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		contentPane.add(CountryLabel);

		JComboBox comboBox = new JComboBox(getAllCountries());
		comboBox.setBounds(445, 176, 260, 27);
		contentPane.add(comboBox);

		//sign up button
		JButton signUpButton = new JButton("Sign Up for LongBox");
		signUpButton.setBounds(107, 449, 598, 29);
		signUpButton.setFont(new Font("Bradley Hand", Font.PLAIN, 13));
		contentPane.add(signUpButton);
		signUpButton.setEnabled(false);

		//Terms and Conditions check box
		JCheckBox TnCCheckbox = new JCheckBox("I agree to the terms and conditions of these app.");
		TnCCheckbox.setBounds(107, 411, 598, 23);
		TnCCheckbox.setFont(new Font("Bradley Hand", Font.PLAIN, 12));
		contentPane.add(TnCCheckbox);
		
		// makes sure sign up button can only be pressed if the checkbox is ticked
		TnCCheckbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				signUpButton.setEnabled(TnCCheckbox.isSelected());
			}
		});

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
