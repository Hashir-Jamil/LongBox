package org.longbox.presentation.comicbook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.*;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.service.CommentService;
import org.longbox.businesslogic.utils.MultiLineCellRenderer;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.persistence.dao.*;
import org.longbox.domainobjects.entity.ComicBook;

@Getter
@Setter
public class ComicBookInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_FONT = "Calibri";
	private static final String PANEL_LABEL = "Search Result";
	private static final String ADD_COMMENT_LABEL = "Add Comment";
	private static final String READING_LIST_LABEL = "Add To Reading";
	private static final String FINISHED_LIST_LABEL = "Add to Finished";
	private static final String FAVORITES_LIST_LABEL = "Add to Favorites";
	private static final String REMOVE_FAVORITES_LIST_LABEL = "Remove Favorite";
	private static final String REMOVE_FINISHED_LIST_LABEL = "Remove From Finished";
	private static final String REMOVE_READING_LIST_LABEL = "Remove From Reading";
	private ComicBookDto comicBookDTO;
	private JPanel panel;
	private JLabel comicSeries;
	private JLabel author;
	private JLabel artist;
	private JLabel genre;
	private JLabel description;
	private JLabel numberOfIssues;
	private JLabel publisher;
	private JLabel yearPublished;
	private JLabel dateAdded;
	private JLabel commentsTitle;
	private JLabel addCommentLabel;
	private JLabel viewCommentsLabel;
	private JButton addCommentButton;
	private JButton addToFavoritesButton;
	private JButton addToFinishedButton;
	private JButton addToReadingButton;
	private JButton removeFromFavoritesButton;
	private JButton removeFromFinishedButton;
	private JButton removeFromToReadingButton;
	private JTextArea commentBox;
	private DefaultListModel<CommentDto> commentListModel;
	private CommentDaoImpl commentDaoImpl;
	private List<CommentDto> commentsOnCurrentComic;
	private UserSession userSession;
	private JList<CommentDto> commentList;
	private ComicBookDaoImpl comicBookDaoImpl;
	private ComicBookFavouritesListDaoImpl comicBookFavouritesListDaoImpl;
	private ComicBookFinishedListDaoImpl comicBookFinishedListDaoImpl;
	private ComicBookReadingListDaoImpl comicBookReadingListDaoImpl;
	private CommentService commentService;

	public ComicBookInfoPanel(ComicBookDto comicBookDTO, UserSession userSession) {
		this.comicBookDTO = comicBookDTO;
		this.userSession = userSession;

		commentDaoImpl = new CommentDaoImpl(HibernateUtils.getSessionFactory());
		commentService = new CommentService(commentDaoImpl);

		this.commentsOnCurrentComic = commentService.getCommentsByComic(this.comicBookDTO.getId());

		initComicBookInfoPage();
	}

	private void initComicBookInfoPage() {
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
	    panel.setLayout(null);
	    
	    JLabel comicCollectionTitle = new JLabel(PANEL_LABEL);
		comicCollectionTitle.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(182, 25, 800, 43);
		panel.add(comicCollectionTitle);
		
		add(panel, BorderLayout.CENTER);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(7, 100, 1150, 1);
		panel.add(separator);
		
		JSeparator midSeparator = new JSeparator();
		midSeparator.setOrientation(SwingConstants.VERTICAL);
		midSeparator.setBackground(new Color(0, 0, 0));
		midSeparator.setForeground(new Color(0, 0, 0));
		midSeparator.setBounds(576, 77, 12, 702);
		panel.add(midSeparator);
		
		JLabel ComicTitleLabel = new JLabel("Series Title:");
		ComicTitleLabel.setBounds(53, 240, 80, 16);
		panel.add(ComicTitleLabel);
		
		JLabel AuthorLabel = new JLabel("Author:");
		AuthorLabel.setBounds(53, 268, 80, 16);
		panel.add(AuthorLabel);
		
		JLabel ArtistLabel = new JLabel("Artist:");
		ArtistLabel.setBounds(53, 296, 80, 16);
		panel.add(ArtistLabel);
		
		JLabel GenreLabel = new JLabel("Genre:");
		GenreLabel.setBounds(53, 324, 80, 16);
		panel.add(GenreLabel);
		
		JLabel DescriptionLabel = new JLabel("Description: ");
		DescriptionLabel.setBounds(53, 484, 94, 16);
		panel.add(DescriptionLabel);
		
		JLabel IssuesLabel = new JLabel("Number of Issues:");
		IssuesLabel.setBounds(53, 372, 115, 16);
		panel.add(IssuesLabel);
		
		JLabel PublisherLabel = new JLabel("Publisher:");
		PublisherLabel.setBounds(53, 400, 80, 16);
		panel.add(PublisherLabel);
		
		JLabel YearPublishedLabel = new JLabel("Year Published: ");
		YearPublishedLabel.setBounds(53, 428, 115, 16);
		panel.add(YearPublishedLabel);
		
		JLabel DateAddedLabel = new JLabel("Date Added:");
		DateAddedLabel.setBounds(53, 456, 94, 16);
		panel.add(DateAddedLabel);
		
		comicSeries = new JLabel("");
		comicSeries.setBounds(182, 240, 373, 16);
		panel.add(comicSeries);
		
		author = new JLabel("");
		author.setBounds(182, 268, 373, 16);
		panel.add(author);
		
		artist = new JLabel("");
		artist.setBounds(182, 296, 373, 16);
		panel.add(artist);
		
		genre = new JLabel("");
		genre.setVerticalAlignment(SwingConstants.TOP);
		genre.setBounds(182, 324, 373, 36);
		panel.add(genre);
		
		description = new JLabel("");
		description.setVerticalAlignment(SwingConstants.TOP);
		description.setBounds(182, 484, 373, 153);
		panel.add(description);
		
		numberOfIssues = new JLabel("");
		numberOfIssues.setBounds(182, 372, 373, 16);
		panel.add(numberOfIssues);
		
		publisher = new JLabel("");
		publisher.setBounds(182, 400, 373, 16);
		panel.add(publisher);
		
		yearPublished = new JLabel("");
		yearPublished.setBounds(182, 428, 373, 16);
		panel.add(yearPublished);
		
		dateAdded = new JLabel("");
		dateAdded.setBounds(182, 456, 373, 16);
		panel.add(dateAdded);
		
		commentsTitle = new JLabel("Comments");
		commentsTitle.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		commentsTitle.setBounds(618, 135, 123, 34);
		panel.add(commentsTitle);
		
		commentBox = new JTextArea();
		commentBox.setBounds(618, 201, 517, 103);
		commentBox.setLineWrap(true);
		commentBox.setWrapStyleWord(true);
		panel.add(commentBox);
		
		addCommentButton = new JButton(ADD_COMMENT_LABEL);
		addCommentButton.setBounds(1018, 311, 117, 29);
		panel.add(addCommentButton);

		addToFavoritesButton = new JButton(FAVORITES_LIST_LABEL);
		addToFavoritesButton.setBounds(10, 655, 175, 30);
		addToFavoritesButton.setEnabled(false);
		panel.add(addToFavoritesButton);

		addToFinishedButton = new JButton(FINISHED_LIST_LABEL);
		addToFinishedButton.setBounds(200, 655, 175, 30);
		addToFinishedButton.setEnabled(false);
		panel.add(addToFinishedButton);

		addToReadingButton = new JButton(READING_LIST_LABEL);
		addToReadingButton.setBounds(391, 655, 175, 30);
		addToReadingButton.setEnabled(false);
		panel.add(addToReadingButton);

		removeFromFavoritesButton = new JButton(REMOVE_FAVORITES_LIST_LABEL);
		removeFromFavoritesButton.setBounds(10, 690, 175, 30);
		removeFromFavoritesButton.setEnabled(false);
		panel.add(removeFromFavoritesButton);

		removeFromFinishedButton = new JButton(REMOVE_FINISHED_LIST_LABEL);
		removeFromFinishedButton.setBounds(200, 690, 175, 30);
		removeFromFinishedButton.setEnabled(false);
		panel.add(removeFromFinishedButton);

		removeFromToReadingButton = new JButton(REMOVE_READING_LIST_LABEL);
		removeFromToReadingButton.setBounds(391, 690, 175, 30);
		removeFromToReadingButton.setEnabled(false);
		panel.add(removeFromToReadingButton);
		
		addCommentLabel = new JLabel("Share your thoughts:");
		addCommentLabel.setBounds(618, 173, 143, 16);
		panel.add(addCommentLabel);
		
		viewCommentsLabel = new JLabel("What others think about this comic:");
		viewCommentsLabel.setBounds(618, 360, 241, 16);
		panel.add(viewCommentsLabel);

		commentListModel = new DefaultListModel<CommentDto>();
		commentList = new JList<CommentDto>(commentListModel);

		commentList.setCellRenderer(new MultiLineCellRenderer());

		JScrollPane commentPane = new JScrollPane(commentList);
		commentPane.setBounds(618, 388, 517, 376);
		panel.add(commentPane);

		favoriteButtonStates();
		finishedButtonStates();
		readingButtonStates();
		setFields();
		displayComments();
	}


	private void setFields() {
		comicSeries.setText(comicBookDTO.getSeriesTitle());
		author.setText(comicBookDTO.getAuthor());
		artist.setText(comicBookDTO.getArtist());
		genre.setText("<html>" + ComicBookDto.genreListToString(comicBookDTO.getGenres()) + "</html>");
		description.setText("<html>" + comicBookDTO.getDescription() + "</html>");
		numberOfIssues.setText("" + comicBookDTO.getNumberOfIssues());
		publisher.setText(comicBookDTO.getPublisher());
		yearPublished.setText("" + comicBookDTO.getYearPublished());
		dateAdded.setText("" + comicBookDTO.getDateAdded());
	}

	public void displayComments(){
		commentsOnCurrentComic = commentService.getCommentsByComic(comicBookDTO.getId());
		commentListModel.removeAllElements();

		for (CommentDto c : commentsOnCurrentComic) {
			commentListModel.addElement(c);
		}

		commentList.setModel(commentListModel);
		commentBox.setText("");
	}

	public boolean isComicInFavorites(long comicId) {
		comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());
		comicBookFavouritesListDaoImpl = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());
		ComicBook comicBook = comicBookDaoImpl.getComicBookById(comicId);
		ComicBookDto comicBookDTO = new ComicBookDto(comicBook);
		System.out.println("the comic in favorites is " + comicBookFavouritesListDaoImpl.getAllFavoritesComicBooks().contains(comicBookDTO));
		return comicBookFavouritesListDaoImpl.getAllFavoritesComicBooks().contains(comicBookDTO);
	}

	public boolean isComicInFinished(Long userId, Long comicBookId) {
		comicBookFinishedListDaoImpl = new ComicBookFinishedListDaoImpl(HibernateUtils.getSessionFactory());
		return comicBookFinishedListDaoImpl.doesRecordExist(userId, comicBookId);
	}

	public boolean isComicInReading(Long userId, Long comicBookId) {
		comicBookReadingListDaoImpl = new ComicBookReadingListDaoImpl(HibernateUtils.getSessionFactory());
		return comicBookReadingListDaoImpl.doesRecordExist(userId, comicBookId);
	}

	public void favoriteButtonStates() {
		if (!isComicInFavorites(comicBookDTO.getId())) {
			addToFavoritesButton.setEnabled(true);
		}
		else {
			removeFromFavoritesButton.setEnabled(true);
		}
	}

	public void finishedButtonStates() {
		if (!isComicInFinished(userSession.getUser().getId(), comicBookDTO.getId())) {
			addToFinishedButton.setEnabled(true);
		}
		else {
			removeFromFinishedButton.setEnabled(true);
		}
	}

	public void readingButtonStates() {
		if (!isComicInReading(userSession.getUser().getId(), comicBookDTO.getId())) {
			addToReadingButton.setEnabled(true);
		}
		else {
			removeFromToReadingButton.setEnabled(true);
		}
	}
}
