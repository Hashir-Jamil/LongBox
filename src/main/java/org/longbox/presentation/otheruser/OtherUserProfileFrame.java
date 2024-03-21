package org.longbox.presentation.otheruser;

import lombok.Getter;
import lombok.Setter;

import org.longbox.businesslogic.service.CommentService;
import org.longbox.businesslogic.utils.GenreUtils;
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
	private static final String PREFERRED_GENRE_TEXT = "Preferred Genre:";
	private static final String COMMENTS_TEXT = "Comments:";
	private static final String COMICS_PREVIOUSLY_FINISHED_TEXT = "Comics Previously Finished: ";
	private static final String COMICS_CURRENTLY_READING_TEXT = "Comics Currently Reading: ";
	private static final String EMPTY = "";
	private static final String ABOUT_ME_TEXT = "About Me:";
	private static final String COMICS_FINISHED_TEXT = "Comics Finished:";
	private static final String COMICS_READING_TEXT = "Comics Reading:";
	private static final String JOINED_ON_TEXT = "Joined On:";
	private static final String COUNTRY_TEXT = "Country:";
	private static final String DATE_OF_BIRTH_TEXT = "Date of Birth:";
	private static final String LAST_NAME_TEXT = "Last Name:";
	private static final String FIRST_NAME_TEXT = "First Name:";
	private static final String USERNAME_TEXT = "Username:";
	private static final String PANEL_LABEL = "Other User";
	
	private static final long serialVersionUID = 1L;

	private JSeparator topSeparator, midSeparator;
	private JLabel comicCollectionTitle, usernameLabel, firstNameLabel, lastNameLabel, dobLabel, countryLabel, joinDateLabel,
			readingLabel, finishedLabel, aboutMeLabel, userNameValue, firstNameValue, lastNameValue, dateOfBirthValue, countryValue, joinDateValue,
			comicsReadingValue, comicsFinishedValue, currentlyReading, currentlyRead, aboutMeValue;
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
	private JLabel prefGenreLabel;
	private JLabel prefGenreValue;

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
		
		countryLabel = new JLabel(COUNTRY_TEXT);
		countryLabel.setBounds(47, 225, 118, 16);
		panel.add(countryLabel);
		
		joinDateLabel = new JLabel(JOINED_ON_TEXT);
		joinDateLabel.setBounds(47, 253, 118, 16);
		panel.add(joinDateLabel);
		
		readingLabel = new JLabel(COMICS_READING_TEXT);
		readingLabel.setBounds(47, 281, 118, 16);
		panel.add(readingLabel);
		
		finishedLabel = new JLabel(COMICS_FINISHED_TEXT);
		finishedLabel.setBounds(47, 309, 118, 16);
		panel.add(finishedLabel);
		
		aboutMeLabel = new JLabel(ABOUT_ME_TEXT);
		aboutMeLabel.setBounds(557, 113, 118, 16);
		panel.add(aboutMeLabel);
		
		userNameValue = new JLabel(EMPTY);
		userNameValue.setBounds(182, 113, 306, 16);
		panel.add(userNameValue);
		
		firstNameValue = new JLabel(EMPTY);
		firstNameValue.setBounds(182, 141, 306, 16);
		panel.add(firstNameValue);
		
		lastNameValue = new JLabel(EMPTY);
		lastNameValue.setBounds(182, 169, 306, 16);
		panel.add(lastNameValue);
		
		dateOfBirthValue = new JLabel(EMPTY);
		dateOfBirthValue.setBounds(182, 197, 306, 16);
		panel.add(dateOfBirthValue);
		
		countryValue = new JLabel(EMPTY);
		countryValue.setBounds(182, 225, 306, 16);
		panel.add(countryValue);
		
		joinDateValue = new JLabel(EMPTY);
		joinDateValue.setBounds(182, 253, 306, 16);
		panel.add(joinDateValue);
		
		comicsReadingValue = new JLabel(EMPTY);
		comicsReadingValue.setBounds(182, 281, 306, 16);
		panel.add(comicsReadingValue);
		
		comicsFinishedValue = new JLabel(EMPTY);
		comicsFinishedValue.setBounds(182, 309, 306, 16);
		panel.add(comicsFinishedValue);

		currentlyReading = new JLabel(COMICS_CURRENTLY_READING_TEXT);
		currentlyReading.setBounds(557, 310, 200, 14);
		panel.add(currentlyReading);

		currentlyRead = new JLabel(COMICS_PREVIOUSLY_FINISHED_TEXT);
		currentlyRead.setBounds(557, 542, 200, 14);
		panel.add(currentlyRead);
		
		aboutMeValue = new JLabel();
		aboutMeValue.setVerticalAlignment(SwingConstants.TOP);
		aboutMeValue.setBounds(557, 141, 554, 127);
		panel.add(aboutMeValue);
		
		prefGenreLabel = new JLabel(PREFERRED_GENRE_TEXT);
		prefGenreLabel.setBounds(47, 337, 118, 22);
		panel.add(prefGenreLabel);
		
		prefGenreValue = new JLabel(EMPTY);
		prefGenreValue.setVerticalAlignment(SwingConstants.TOP);
		prefGenreValue.setBounds(182, 342, 306, 50);
		panel.add(prefGenreValue);

		commentLabel = new JLabel(COMMENTS_TEXT);
		commentLabel.setBounds(47, 402, 103, 16);
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
		userNameValue.setText(this.user.getUser().getUserName());
		firstNameValue.setText(this.user.getUser().getFirstName());
		lastNameValue.setText(this.user.getUser().getLastName());
		dateOfBirthValue.setText(EMPTY + this.user.getUser().getDob());
		countryValue.setText(this.user.getUser().getCountry());
		joinDateValue.setText(EMPTY + this.user.getUser().getJoinDate());
		comicsFinishedValue.setText(EMPTY + this.user.getUser().getComicsFinished());
		comicsReadingValue.setText(EMPTY + this.user.getUser().getComicsReading());
		aboutMeValue.setText("<html>" + this.user.getUser().getAboutMe() + "</html>");
		prefGenreValue.setText("<htm>" + GenreUtils.genreListToString(this.user.getPreferredGenre()) + "</htm>");
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
