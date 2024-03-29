package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.mapper.ComicBookMapper;
import org.longbox.domainobjects.mapper.UserMapper;
import org.longbox.persistence.dao.ComicBookFinishedListDaoImpl;
import org.longbox.persistence.dao.ComicBookReadingListDaoImpl;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.presentation.tablemodels.ReadingAndFinishedComicBookTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

@Getter
@Setter
public class ProfilePanel extends JPanel {
	private static final String COMICS_PREVIOUSLY_FINISHED_TEXT = "Comics Previously Finished: ";
	private static final String COMICS_CURRENTLY_READING_TEXT = "Comics Currently Reading: ";
	private static final String EMPTY = "";
	private static final String ABOUT_ME_TEXT = "About Me:";
	private static final String COMICS_FINISHED_TEXT = "Comics Finished:";
	private static final String COMICS_READING_TEXT = "Comics Reading:";
	private static final String JOINED_ON_TEXT = "Joined On:";
	private static final String COUNTRY_TEXT = "Country:";
	private static final String EMAIL_TEXT = "Email:";
	private static final String DATE_OF_BIRTH_TEXT = "Date of Birth:";
	private static final String LAST_NAME_TEXT = "Last Name:";
	private static final String FIRST_NAME_TEXT = "First Name:";
	private static final String USERNAME_TEXT = "Username:";
	private static final long serialVersionUID = 1L;
	private final String PANEL_LABEL = "Profile View";

	private JSeparator topSeparator, midSeparator;
	private JLabel comicCollectionTitle, usernameLabel, firstNameLabel, lastNameLabel, dobLabel, emailLabel, countryLabel, joinDateLabel,
			readingLabel, finishedLabel, aboutMeLabel, userName, firstName, lastName, dateOfBirth, email, country, joinDate,
			comicsReading, comicsFinished, currentlyReading, currentlyRead;
	private JTextArea aboutMe;
	private JScrollPane scrollPane;
	private JPanel panel;
	private UserSession user;
	private ReadingAndFinishedComicBookTableModel readingTableModel, finishedTableModel;
	private JScrollPane readingPane, readPane;
	private JTable readingTable, readTable;
	private JButton aboutMeEditButton, aboutMeCancelButton;


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
		
		usernameLabel = new JLabel(USERNAME_TEXT);
		usernameLabel.setBounds(47, 281, 118, 16);
		panel.add(usernameLabel);
		
		firstNameLabel = new JLabel(FIRST_NAME_TEXT);
		firstNameLabel.setBounds(47, 309, 118, 16);
		panel.add(firstNameLabel);
		
		lastNameLabel = new JLabel(LAST_NAME_TEXT);
		lastNameLabel.setBounds(47, 338, 118, 16);
		panel.add(lastNameLabel);
		
		dobLabel = new JLabel(DATE_OF_BIRTH_TEXT);
		dobLabel.setBounds(47, 366, 118, 16);
		panel.add(dobLabel);
		
		emailLabel = new JLabel(EMAIL_TEXT);
		emailLabel.setBounds(47, 394, 118, 16);
		panel.add(emailLabel);
		
		countryLabel = new JLabel(COUNTRY_TEXT);
		countryLabel.setBounds(47, 422, 118, 16);
		panel.add(countryLabel);
		
		joinDateLabel = new JLabel(JOINED_ON_TEXT);
		joinDateLabel.setBounds(47, 450, 118, 16);
		panel.add(joinDateLabel);
		
		readingLabel = new JLabel(COMICS_READING_TEXT);
		readingLabel.setBounds(47, 478, 118, 16);
		panel.add(readingLabel);
		
		finishedLabel = new JLabel(COMICS_FINISHED_TEXT);
		finishedLabel.setBounds(47, 506, 118, 16);
		panel.add(finishedLabel);
		
		aboutMeLabel = new JLabel(ABOUT_ME_TEXT);
		aboutMeLabel.setBounds(557, 113, 118, 16);
		panel.add(aboutMeLabel);
		
		userName = new JLabel(EMPTY);
		userName.setBounds(182, 281, 306, 16);
		panel.add(userName);
		
		firstName = new JLabel(EMPTY);
		firstName.setBounds(182, 309, 306, 16);
		panel.add(firstName);
		
		lastName = new JLabel(EMPTY);
		lastName.setBounds(182, 338, 306, 16);
		panel.add(lastName);
		
		dateOfBirth = new JLabel(EMPTY);
		dateOfBirth.setBounds(182, 366, 306, 16);
		panel.add(dateOfBirth);
		
		email = new JLabel(EMPTY);
		email.setBounds(182, 394, 306, 16);
		panel.add(email);
		
		country = new JLabel(EMPTY);
		country.setBounds(182, 422, 306, 16);
		panel.add(country);
		
		joinDate = new JLabel(EMPTY);
		joinDate.setBounds(182, 450, 306, 16);
		panel.add(joinDate);
		
		comicsReading = new JLabel(EMPTY);
		comicsReading.setBounds(182, 478, 306, 16);
		panel.add(comicsReading);
		
		comicsFinished = new JLabel(EMPTY);
		comicsFinished.setBounds(182, 506, 306, 16);
		panel.add(comicsFinished);

		currentlyReading = new JLabel(COMICS_CURRENTLY_READING_TEXT);
		currentlyReading.setBounds(557, 310, 200, 14);
		panel.add(currentlyReading);

		currentlyRead = new JLabel(COMICS_PREVIOUSLY_FINISHED_TEXT);
		currentlyRead.setBounds(557, 542, 200, 14);
		panel.add(currentlyRead);
		
		aboutMe = new JTextArea();
		aboutMe.setEditable(false);
		aboutMe.setBounds(557, 141, 554, 127);
		aboutMe.setLineWrap(true);
		aboutMe.setWrapStyleWord(true);
		panel.add(aboutMe);
		
		scrollPane = new JScrollPane(aboutMe);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(557, 141, 554, 127);
		panel.add(scrollPane);
		
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
		dateOfBirth.setText(EMPTY + this.user.getUser().getDob());
		email.setText(this.user.getUser().getEmail());
		country.setText(this.user.getUser().getCountry());
		joinDate.setText(EMPTY + this.user.getUser().getJoinDate());
		comicsFinished.setText(EMPTY + this.user.getUser().getComicsFinished());
		comicsReading.setText(EMPTY + this.user.getUser().getComicsReading());
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

		readingListDTO = ComicBookMapper.toDtoList(readingList);
		finishedListDTO = ComicBookMapper.toDtoList(readList);

		readingTableModel = new ReadingAndFinishedComicBookTableModel(readingListDTO);
		finishedTableModel = new ReadingAndFinishedComicBookTableModel(finishedListDTO);

		readingTable = new JTable(readingTableModel);
		List<ComicBookDto> finalReadingListDTO = readingListDTO;
		readingTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = readingTable.rowAtPoint(e.getPoint());
				int col = readingTable.columnAtPoint(e.getPoint());
				if (col == 0 && e.getClickCount() == 2) {
					ComicBookDto comicBook = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(finalReadingListDTO, readingTable.getValueAt(row, col).toString());
					ComicBookSearchUtils.loadComicBookPage(comicBook, user);
				}
			}
		});
		readingPane = new JScrollPane(readingTable);
		readingPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		readingPane.setBounds(557, 337, 554, 185);
		panel.add(readingPane);

		readTable = new JTable(finishedTableModel);
		List<ComicBookDto> finalFinishedListDTO = finishedListDTO;
		readTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = readTable.rowAtPoint(e.getPoint());
				int col = readTable.columnAtPoint(e.getPoint());
				if (col == 0 && e.getClickCount() == 2) {
					ComicBookDto comicBook = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(finalFinishedListDTO, readTable.getValueAt(row, col).toString());
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
