package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.mapper.UserMapper;
import org.longbox.persistence.dao.ComicBookFinishedListDaoImpl;
import org.longbox.persistence.dao.ComicBookReadingListDaoImpl;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.presentation.tablemodels.ReadingAndFinishedComicBookTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

@Getter
@Setter
public class ProfilePanel extends JPanel {
	private final String PANEL_LABEL = "Profile View";
	private JLabel comicCollectionTitle;
	private JSeparator topSeparator;
	private JSeparator midSeparator;
	private JLabel usernameLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel dobLabel;
	private JLabel emailLabel;
	private JLabel countryLabel;
	private JLabel joinDateLabel;
	private JLabel readingLabel;
	private JLabel finishedLabel;
	private JLabel aboutMeLabel;
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
	private ReadingAndFinishedComicBookTableModel finishedTableModel;
	private JScrollPane readingPane;
	private JScrollPane readPane;
	private JTable readingTable;
	private JTable readTable;
	private JLabel currentlyReading;
	private JLabel currentlyRead;
	private JButton aboutMeEditButton;
	private JButton aboutMeCancelButton;

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
	    
	    comicCollectionTitle = new JLabel(PANEL_LABEL);
		comicCollectionTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(182, 11, 800, 43);
		panel.add(comicCollectionTitle);
		
		add(panel, BorderLayout.CENTER);
		
		//Top horizontal topSeparator
		topSeparator = new JSeparator();
		topSeparator.setBackground(new Color(0, 0, 0));
		topSeparator.setForeground(new Color(0, 0, 0));
		topSeparator.setBounds(7, 100, 1150, 1);
		panel.add(topSeparator);
		
		//Middle vertical topSeparator
		midSeparator = new JSeparator();
		midSeparator.setOrientation(SwingConstants.VERTICAL);
		midSeparator.setBackground(new Color(0, 0, 0));
		midSeparator.setForeground(new Color(0, 0, 0));
		midSeparator.setBounds(500, 100, 30, 700);
		panel.add(midSeparator);
		
		usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(47, 113, 118, 16);
		panel.add(usernameLabel);
		
		firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(47, 141, 118, 16);
		panel.add(firstNameLabel);
		
		lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setBounds(47, 169, 118, 16);
		panel.add(lastNameLabel);
		
		dobLabel = new JLabel("Date of Birth:");
		dobLabel.setBounds(47, 197, 118, 16);
		panel.add(dobLabel);
		
		emailLabel = new JLabel("Email:");
		emailLabel.setBounds(47, 225, 118, 16);
		panel.add(emailLabel);
		
		countryLabel = new JLabel("Country:");
		countryLabel.setBounds(47, 253, 118, 16);
		panel.add(countryLabel);
		
		joinDateLabel = new JLabel("Joined On:");
		joinDateLabel.setBounds(47, 281, 118, 16);
		panel.add(joinDateLabel);
		
		readingLabel = new JLabel("Comics Reading:");
		readingLabel.setBounds(47, 309, 118, 16);
		panel.add(readingLabel);
		
		finishedLabel = new JLabel("Comics Finished:");
		finishedLabel.setBounds(47, 337, 118, 16);
		panel.add(finishedLabel);
		
		aboutMeLabel = new JLabel("About Me:");
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

		currentlyReading = new JLabel("Comics Currently Reading: ");
		currentlyReading.setBounds(557, 310, 200, 14);
		panel.add(currentlyReading);

		currentlyRead = new JLabel("Comics Previously Finished: ");
		currentlyRead.setBounds(557, 542, 200, 14);
		panel.add(currentlyRead);
		
		aboutMe = new JTextArea();
		aboutMe.setEditable(false);
		aboutMe.setBounds(557, 141, 554, 127);
		aboutMe.setLineWrap(true);
		aboutMe.setWrapStyleWord(true);
		panel.add(aboutMe);
		
		aboutMeEditButton = new JButton();
		aboutMeEditButton.setText("Edit");
		aboutMeEditButton.setEnabled(true);
		aboutMeEditButton.setBounds(1024, 281, 87, 16);
		panel.add(aboutMeEditButton);
		
		aboutMeCancelButton = new JButton();
		aboutMeCancelButton.setText("Cancel");
		aboutMeCancelButton.setEnabled(false);
		aboutMeCancelButton.setBounds(927, 281, 87, 16);
		panel.add(aboutMeCancelButton);
		
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

	public void reloadTable() throws UserIDDoesNotExistException {

		UserDaoImpl userDaoImpl = new UserDaoImpl(HibernateUtils.getSessionFactory());
		user.setUser(UserMapper.toDto(userDaoImpl.getUserById(user.getUser().getId())));
		setFields();

		ComicBookReadingListDaoImpl readingListDaoImpl = new ComicBookReadingListDaoImpl(HibernateUtils.getSessionFactory());
		ComicBookFinishedListDaoImpl readListDaoImpl = new ComicBookFinishedListDaoImpl(HibernateUtils.getSessionFactory());

		List<ComicBook> readingList = readingListDaoImpl.getUsersReadingList(user.getUser().getId());
		List<ComicBookDto> readingListDTO = new ArrayList<ComicBookDto>();

		List<ComicBook> readList = readListDaoImpl.getUsersFinishedList(user.getUser().getId());
		List<ComicBookDto> finishedListDTO = new ArrayList<ComicBookDto>();

		for (ComicBook c : readingList) {
			ComicBookDto comicBookDTO = new ComicBookDto(c);
			readingListDTO.add(comicBookDTO);
		}

		for (ComicBook c : readList) {
			ComicBookDto comicBookDTO = new ComicBookDto(c);
			finishedListDTO.add(comicBookDTO);
		}

		readingTableModel = new ReadingAndFinishedComicBookTableModel(readingListDTO);
		finishedTableModel = new ReadingAndFinishedComicBookTableModel(finishedListDTO);

		readingTable = new JTable(readingTableModel);
		readingTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = readingTable.rowAtPoint(e.getPoint());
				int col = readingTable.columnAtPoint(e.getPoint());
				if (col == 0 && e.getClickCount() == 2) {
					ComicBookDto comicBook = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(readingListDTO, readingTable.getValueAt(row, col).toString());
					ComicBookSearchUtils.loadComicBookPage(comicBook, user);
				}
			}
		});
		readingPane = new JScrollPane(readingTable);
		readingPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		readingPane.setBounds(557, 337, 554, 185);
		panel.add(readingPane);

		readTable = new JTable(finishedTableModel);
		readTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = readTable.rowAtPoint(e.getPoint());
				int col = readTable.columnAtPoint(e.getPoint());
				if (col == 0 && e.getClickCount() == 2) {
					ComicBookDto comicBook = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(finishedListDTO, readTable.getValueAt(row, col).toString());
					ComicBookSearchUtils.loadComicBookPage(comicBook, user);
				}
			}
		});
		readPane = new JScrollPane(readTable);
		readPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		readPane.setBounds(557, 567, 554, 185);
		panel.add(readPane);
	}
}
