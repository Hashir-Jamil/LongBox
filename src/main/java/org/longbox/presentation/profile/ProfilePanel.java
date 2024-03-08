package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.domainobjects.dto.UserDTO;
import org.longbox.persistence.dao.ComicBookFinishedListDaoImpl;
import org.longbox.persistence.dao.ComicBookReadingListDaoImpl;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.persistence.entity.ComicBook;

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
	private JTextArea aboutMe;
	private JPanel panel;
	private UserSession user;
	private ReadingAndFinishedComicBookTableModel readingTableModel;
	private ReadingAndFinishedComicBookTableModel readTableModel;
	private JScrollPane readingPane;
	private JScrollPane readPane;
	private JTable readingTable;
	private JTable readTable;
	private JLabel currentlyReading;
	private JLabel currentlyRead;

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
		
		panel = new JPanel();
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
		
		aboutMe = new JTextArea();
		aboutMe.setEditable(false);
		aboutMe.setBounds(557, 141, 554, 127);
		aboutMe.setLineWrap(true);
		aboutMe.setWrapStyleWord(true);
		panel.add(aboutMe);
		
		JButton aboutMeEditButton = new JButton();
		aboutMeEditButton.setText("Edit");
		aboutMeEditButton.setEnabled(true);
		aboutMeEditButton.setBounds(1024, 281, 87, 16);
		panel.add(aboutMeEditButton);
		
		JButton aboutMeCancelButton = new JButton();
		aboutMeCancelButton.setText("Cancel");
		aboutMeCancelButton.setEnabled(false);
		aboutMeCancelButton.setBounds(927, 281, 87, 16);
		panel.add(aboutMeCancelButton);
		
		
		aboutMeEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (aboutMeEditButton.getText() == "Edit") {
					aboutMeEditButton.setText("Save");
					aboutMeCancelButton.setEnabled(true);
					aboutMe.setEditable(true);
				}
				
				else if (aboutMeEditButton.getText() == "Save") {
					UserDaoImpl userDaoImpl = new UserDaoImpl();
					userDaoImpl.updateAboutMeString(user, aboutMe.getText());
					aboutMeCancelButton.setEnabled(false);
					aboutMeEditButton.setText("Edit");
					aboutMe.setEditable(false);
				}
			}
		});
		
		aboutMeCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aboutMeCancelButton.setEnabled(false);
				aboutMe.setEditable(false);

				aboutMeEditButton.setEnabled(true);
				aboutMeEditButton.setText("Edit");
			}
		});

        try {
            reloadTable();
        } catch (UserIDDoesNotExistException e) {
            throw new RuntimeException(e);
        }

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

	public void reloadTable() throws UserIDDoesNotExistException {

		UserDaoImpl userDaoImpl = new UserDaoImpl();
		user.setUser(new UserDTO(userDaoImpl.getUserById(user.getUser().getId())));
		setFields();

		ComicBookReadingListDaoImpl readingListDaoImpl = new ComicBookReadingListDaoImpl();
		ComicBookFinishedListDaoImpl readListDaoImpl = new ComicBookFinishedListDaoImpl();

		List<ComicBook> readingList = readingListDaoImpl.getUsersReadingList(user.getUser().getId());
		List<ComicBookDTO> readingListDTO = new ArrayList<ComicBookDTO>();

		List<ComicBook> readList = readListDaoImpl.getUsersFinishedList(user.getUser().getId());
		List<ComicBookDTO> readListDTO = new ArrayList<ComicBookDTO>();

		for (ComicBook c : readingList) {
			ComicBookDTO comicBookDTO = new ComicBookDTO(c);
			readingListDTO.add(comicBookDTO);
		}

		for (ComicBook c : readList) {
			ComicBookDTO comicBookDTO = new ComicBookDTO(c);
			readListDTO.add(comicBookDTO);
		}

		readingTableModel = new ReadingAndFinishedComicBookTableModel(readingListDTO);
		readTableModel = new ReadingAndFinishedComicBookTableModel(readListDTO);

		readingTable = new JTable(readingTableModel);
		readingPane = new JScrollPane(readingTable);
		readingPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		readingPane.setBounds(47, 424, 407, 135);
		panel.add(readingPane);

		readTable = new JTable(readTableModel);
		readPane = new JScrollPane(readTable);
		readPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		readPane.setBounds(47, 617, 407, 135);
		panel.add(readPane);

		currentlyReading = new JLabel("Comics Currently Reading: ");
		currentlyReading.setBounds(47, 399, 200, 14);
		panel.add(currentlyReading);

		currentlyRead = new JLabel("Comics Previously Finished: ");
		currentlyRead.setBounds(47, 592, 200, 14);
		panel.add(currentlyRead);
		userName.setText(this.user.getUser().getUserName());
		firstName.setText(this.user.getUser().getFirstName());
		lastName.setText(this.user.getUser().getLastName());
		dateOfBirth.setText("" + this.user.getUser().getDob());
		email.setText(this.user.getUser().getEmail());
		country.setText(this.user.getUser().getCountry());
		joinDate.setText("" + this.user.getUser().getJoinDate());
//		comicsFinished.setText("" + this.user.getUser().getComicsFinished());
//		comicsReading.setText("" + this.user.getUser().getComicsReading());
//		aboutMe.setText(this.user.getUser().getAboutMe());
	}
}
