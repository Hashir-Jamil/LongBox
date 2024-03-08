package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import org.longbox.businesslogic.UserSession;
import org.longbox.domainobjects.dto.UserDTO;

import javax.swing.JTextPane;
@Getter
@Setter
public class ProfilePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final String PANEL_LABEL = "Profile View";
	
	private JLabel userName;
	private JLabel firstName;
	private JLabel lastName;
	private JLabel dateOfBirth;
	private JLabel email;
	private JLabel country;
	private JLabel joinDate;
	private JLabel comicsReading;
	private JLabel comicsFinished;
	private JTextField aboutMe;
	
	private UserSession user;
	

	/**
	 * Create the panel.
	 */

	public ProfilePanel(UserSession user) {
		this.user = user;
		initProfilePage();
	}

	private void initProfilePage() {
		
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
	    panel.setLayout(null);
	    
	    JLabel comicCollectionTitle = new JLabel(PANEL_LABEL);
		comicCollectionTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(182, 11, 800, 43);
		panel.add(comicCollectionTitle);
		
		add(panel, BorderLayout.CENTER);
		
		//Top horizontal topSeparator
		JSeparator topSeparator = new JSeparator();
		topSeparator.setBackground(new Color(0, 0, 0));
		topSeparator.setForeground(new Color(0, 0, 0));
		topSeparator.setBounds(7, 100, 1150, 1);
		panel.add(topSeparator);
		
		//Middle vertical topSeparator
		JSeparator midSeparator = new JSeparator();
		midSeparator.setOrientation(SwingConstants.VERTICAL);
		midSeparator.setBackground(new Color(0, 0, 0));
		midSeparator.setForeground(new Color(0, 0, 0));
		midSeparator.setBounds(500, 100, 30, 700);
		panel.add(midSeparator);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(47, 113, 118, 16);
		panel.add(usernameLabel);
		
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(47, 141, 118, 16);
		panel.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setBounds(47, 169, 118, 16);
		panel.add(lastNameLabel);
		
		JLabel dobLabel = new JLabel("Date of Birth:");
		dobLabel.setBounds(47, 197, 118, 16);
		panel.add(dobLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(47, 225, 118, 16);
		panel.add(emailLabel);
		
		JLabel countryLabel = new JLabel("Country:");
		countryLabel.setBounds(47, 253, 118, 16);
		panel.add(countryLabel);
		
		JLabel joinDateLabel = new JLabel("Joined On:");
		joinDateLabel.setBounds(47, 281, 118, 16);
		panel.add(joinDateLabel);
		
		JLabel readingLabel = new JLabel("Comics Reading:");
		readingLabel.setBounds(47, 309, 118, 16);
		panel.add(readingLabel);
		
		JLabel finishedLabel = new JLabel("Comics Finished:");
		finishedLabel.setBounds(47, 337, 118, 16);
		panel.add(finishedLabel);
		
		JLabel aboutMeLabel = new JLabel("About Me:");
		aboutMeLabel.setBounds(557, 113, 118, 16);
		panel.add(aboutMeLabel);
		
		userName = new JLabel("");
		userName.setBounds(182, 113, 306, 16);
		panel.add(userName);
		
		firstName = new JLabel("");
		firstName.setBounds(182, 141, 306, 16);
		panel.add(firstName);
		
		lastName = new JLabel("");
		lastName.setBounds(182, 169, 306, 16);
		panel.add(lastName);
		
		dateOfBirth = new JLabel("");
		dateOfBirth.setBounds(182, 197, 306, 16);
		panel.add(dateOfBirth);
		
		email = new JLabel("");
		email.setBounds(182, 225, 306, 16);
		panel.add(email);
		
		country = new JLabel("");
		country.setBounds(182, 253, 306, 16);
		panel.add(country);
		
		joinDate = new JLabel("");
		joinDate.setBounds(182, 281, 306, 16);
		panel.add(joinDate);
		
		comicsReading = new JLabel("");
		comicsReading.setBounds(182, 309, 306, 16);
		panel.add(comicsReading);
		
		comicsFinished = new JLabel("");
		comicsFinished.setBounds(182, 337, 306, 16);
		panel.add(comicsFinished);
		
		aboutMe = new JTextField();
		aboutMe.setBounds(557, 141, 554, 127);
		panel.add(aboutMe);
		
		JButton aboutMeUpdateButton = new JButton();
		aboutMeUpdateButton.setText("Update");
		aboutMeUpdateButton.setBounds(1036, 281, 75, 16);
		aboutMeUpdateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				user.getUser().setAboutMe(aboutMe.getText());
			}
		});
		panel.add(aboutMeUpdateButton);
		
		setFields();
	}
	
	private void setFields() {

		userName.setText(this.user.getUser().getUserName());
		firstName.setText(this.user.getUser().getFirstName());
		lastName.setText(this.user.getUser().getLastName());
		dateOfBirth.setText("" + this.user.getUser().getDob());
		email.setText(this.user.getUser().getEmail());
		country.setText(this.user.getUser().getCountry());
		joinDate.setText("" + this.user.getUser().getJoinDate());
		comicsFinished.setText("" + this.user.getUser().getComicsFinished());
		comicsReading.setText("" + this.user.getUser().getComicsReading());
		aboutMe.setText(this.user.getUser().getAboutMe());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
}
