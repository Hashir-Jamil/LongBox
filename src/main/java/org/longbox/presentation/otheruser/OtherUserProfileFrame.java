package org.longbox.presentation.otheruser;

import lombok.Getter;
import lombok.Setter;

import org.longbox.businesslogic.service.CommentService;
import org.longbox.businesslogic.utils.MultiLineCellRendererForUser;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.dto.UserDto;
import org.longbox.domainobjects.mapper.ComicBookMapper;
import org.longbox.persistence.dao.ComicBookFinishedListDaoImpl;
import org.longbox.persistence.dao.ComicBookReadingListDaoImpl;
import org.longbox.persistence.dao.CommentDaoImpl;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.presentation.tablemodels.ReadingAndFinishedComicBookTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

@Getter
@Setter
public class OtherUserProfileFrame extends JFrame {
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
	private final String PANEL_LABEL = "Other User";

	private JSeparator topSeparator, midSeparator;
	private JLabel comicCollectionTitle, usernameLabel, firstNameLabel, lastNameLabel, dobLabel, emailLabel, countryLabel, joinDateLabel,
			readingLabel, finishedLabel, aboutMeLabel, userName, firstName, lastName, dateOfBirth, email, country, joinDate,
			comicsReading, comicsFinished, currentlyReading, currentlyRead, aboutMe;
	private JPanel panel;
	private UserDto user;
	private ReadingAndFinishedComicBookTableModel readingTableModel, finishedTableModel;
	private JScrollPane readingPane, readPane, commentPane;
	private JTable readingTable, readTable;
	private JLabel commentLabel;
	private List<CommentDto> commentsbyUser;
	private CommentService commentService;
	private DefaultListModel<CommentDto> commentListModel;
	private JList<CommentDto> commentList;

	/**
	 * Create the panel.
	 */
	public OtherUserProfileFrame(UserDto user) {
		this.user = user;
		commentService = new CommentService(new CommentDaoImpl(HibernateUtils.getSessionFactory()));
		this.commentsbyUser = commentService.getCommentsByUser(this.user.getId());
		initProfilePage();
	}

	private void initProfilePage() {
		setBounds(10, 47, 1164, 803);
		getContentPane().setLayout(new BorderLayout());
		setVisible(true);
		
		panel = new JPanel();
	    panel.setLayout(null);
	    
	    comicCollectionTitle = new JLabel(PANEL_LABEL);
		comicCollectionTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(182, 11, 800, 43);
		panel.add(comicCollectionTitle);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
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
		usernameLabel.setBounds(47, 113, 118, 16);
		panel.add(usernameLabel);
		
		firstNameLabel = new JLabel(FIRST_NAME_TEXT);
		firstNameLabel.setBounds(47, 141, 118, 16);
		panel.add(firstNameLabel);
		
		lastNameLabel = new JLabel(LAST_NAME_TEXT);
		lastNameLabel.setBounds(47, 169, 118, 16);
		panel.add(lastNameLabel);
		
		dobLabel = new JLabel(DATE_OF_BIRTH_TEXT);
		dobLabel.setBounds(47, 197, 118, 16);
		panel.add(dobLabel);
		
		emailLabel = new JLabel(EMAIL_TEXT);
		emailLabel.setBounds(47, 225, 118, 16);
		panel.add(emailLabel);
		
		countryLabel = new JLabel(COUNTRY_TEXT);
		countryLabel.setBounds(47, 253, 118, 16);
		panel.add(countryLabel);
		
		joinDateLabel = new JLabel(JOINED_ON_TEXT);
		joinDateLabel.setBounds(47, 281, 118, 16);
		panel.add(joinDateLabel);
		
		readingLabel = new JLabel(COMICS_READING_TEXT);
		readingLabel.setBounds(47, 309, 118, 16);
		panel.add(readingLabel);
		
		finishedLabel = new JLabel(COMICS_FINISHED_TEXT);
		finishedLabel.setBounds(47, 337, 118, 16);
		panel.add(finishedLabel);
		
		aboutMeLabel = new JLabel(ABOUT_ME_TEXT);
		aboutMeLabel.setBounds(557, 113, 118, 16);
		panel.add(aboutMeLabel);
		
		userName = new JLabel(EMPTY);
		userName.setBounds(182, 113, 306, 16);
		panel.add(userName);
		
		firstName = new JLabel(EMPTY);
		firstName.setBounds(182, 141, 306, 16);
		panel.add(firstName);
		
		lastName = new JLabel(EMPTY);
		lastName.setBounds(182, 169, 306, 16);
		panel.add(lastName);
		
		dateOfBirth = new JLabel(EMPTY);
		dateOfBirth.setBounds(182, 197, 306, 16);
		panel.add(dateOfBirth);
		
		email = new JLabel(EMPTY);
		email.setBounds(182, 225, 306, 16);
		panel.add(email);
		
		country = new JLabel(EMPTY);
		country.setBounds(182, 253, 306, 16);
		panel.add(country);
		
		joinDate = new JLabel(EMPTY);
		joinDate.setBounds(182, 281, 306, 16);
		panel.add(joinDate);
		
		comicsReading = new JLabel(EMPTY);
		comicsReading.setBounds(182, 309, 306, 16);
		panel.add(comicsReading);
		
		comicsFinished = new JLabel(EMPTY);
		comicsFinished.setBounds(182, 337, 306, 16);
		panel.add(comicsFinished);

		currentlyReading = new JLabel(COMICS_CURRENTLY_READING_TEXT);
		currentlyReading.setBounds(557, 310, 200, 14);
		panel.add(currentlyReading);

		currentlyRead = new JLabel(COMICS_PREVIOUSLY_FINISHED_TEXT);
		currentlyRead.setBounds(557, 542, 200, 14);
		panel.add(currentlyRead);
		
		aboutMe = new JLabel();
		aboutMe.setVerticalAlignment(SwingConstants.TOP);
		aboutMe.setBounds(557, 141, 554, 127);
		panel.add(aboutMe);

		commentLabel = new JLabel("Comments:");
		commentLabel.setBounds(47, 379, 103, 16);
		panel.add(commentLabel);
		
		commentListModel = new DefaultListModel<CommentDto>();
		commentList = new JList<CommentDto>(commentListModel);
		
		commentList.setCellRenderer(new MultiLineCellRendererForUser());
		
		commentPane = new JScrollPane(commentList);
		commentPane.setBounds(32, 430, 429, 322);
		panel.add(commentPane);
		
		reloadTable();
		setFields();
		displayComments();
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
		aboutMe.setText("<html>" + this.user.getUser().getAboutMe() + "</html>");
	}

	public void reloadTable() {

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
		
		readingPane = new JScrollPane(readingTable);
		readingPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		readingPane.setBounds(557, 337, 554, 185);
		panel.add(readingPane);

		readTable = new JTable(finishedTableModel);
		
		readPane = new JScrollPane(readTable);
		readPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		readPane.setBounds(557, 567, 554, 185);
		panel.add(readPane);
	}
	
	public void displayComments(){
		commentListModel.removeAllElements();

		for (CommentDto c : commentsbyUser) {
			commentListModel.addElement(c);
		}

		commentList.setModel(commentListModel);
	}
}
