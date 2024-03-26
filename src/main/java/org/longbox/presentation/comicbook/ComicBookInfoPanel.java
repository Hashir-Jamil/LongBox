package org.longbox.presentation.comicbook;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.service.CommentService;
import org.longbox.businesslogic.service.StarRatingService;
import org.longbox.businesslogic.utils.GenreUtils;
import org.longbox.businesslogic.utils.MultiLineCellRenderer;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.dto.CommentDto;
import org.longbox.domainobjects.dto.StarRatingDto;
import org.longbox.domainobjects.mapper.ComicBookMapper;
import org.longbox.persistence.dao.*;
import org.longbox.domainobjects.entity.ComicBook;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ComicBookInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_FONT = "Calibri";
	private static final String PANEL_LABEL = "Search Result";
	private static final String ADD_COMMENT_LABEL = "Add Comment";
	private static final String READING_LIST_LABEL = "Add To Reading";
	private static final String FINISHED_LIST_LABEL = "Add to Finished";
	private static final String FAVORITES_LIST_LABEL = "Add to Favourites";
	private static final String REMOVE_FAVORITES_LIST_LABEL = "Remove Favourite";
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
	private JLabel avgRating;
	private JLabel userRating;
	private JLabel inputRatingPrompt;
	private JTextField inputRating;
	private JButton addCommentButton;
	private JButton addToFavouritesButton;
	private JButton addToFinishedButton;
	private JButton addToReadingButton;
	private JButton removeFromFavouritesButton;
	private JButton removeFromFinishedButton;
	private JButton removeFromToReadingButton;
	private JTextArea commentBox;
	private DefaultListModel<CommentDto> commentListModel;
	private CommentDaoImpl commentDaoImpl;
	private StarRatingDaoImpl starRatingDaoImpl;
	private List<CommentDto> commentsOnCurrentComic;
	private List<StarRatingDto> avgRatingTotal;
	private UserSession userSession;
	private JList<CommentDto> commentList;
	private ComicBookDaoImpl comicBookDaoImpl;
	private ComicBookFavouritesListDaoImpl comicBookFavouritesListDaoImpl;
	private ComicBookFinishedListDaoImpl comicBookFinishedListDaoImpl;
	private ComicBookReadingListDaoImpl comicBookReadingListDaoImpl;
	private CommentService commentService;
	private StarRatingService starRatingService;
	private JScrollPane commentPane, addCommentPane;

	public ComicBookInfoPanel(ComicBookDto comicBookDTO, UserSession userSession) {
		this.comicBookDTO = comicBookDTO;
		this.userSession = userSession;

		commentDaoImpl = new CommentDaoImpl(HibernateUtils.getSessionFactory());
		commentService = new CommentService(commentDaoImpl);
		starRatingService = new StarRatingService(starRatingDaoImpl);

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
		ComicTitleLabel.setBounds(53, 135, 80, 16);
		panel.add(ComicTitleLabel);
		
		JLabel AuthorLabel = new JLabel("Author:");
		AuthorLabel.setBounds(53, 165, 80, 16);
		panel.add(AuthorLabel);
		
		JLabel ArtistLabel = new JLabel("Artist:");
		ArtistLabel.setBounds(53, 195, 80, 16);
		panel.add(ArtistLabel);
		
		JLabel GenreLabel = new JLabel("Genre:");
		GenreLabel.setBounds(53, 225, 80, 16);
		panel.add(GenreLabel);
		
		JLabel DescriptionLabel = new JLabel("Description: ");
		DescriptionLabel.setBounds(53, 395, 94, 16);
		panel.add(DescriptionLabel);
		
		JLabel IssuesLabel = new JLabel("Number of Issues:");
		IssuesLabel.setBounds(53, 275, 115, 16);
		panel.add(IssuesLabel);
		
		JLabel PublisherLabel = new JLabel("Publisher:");
		PublisherLabel.setBounds(53, 305, 80, 16);
		panel.add(PublisherLabel);
		
		JLabel YearPublishedLabel = new JLabel("Year Published: ");
		YearPublishedLabel.setBounds(53, 365, 115, 16);
		panel.add(YearPublishedLabel);
		
		JLabel DateAddedLabel = new JLabel("Date Added:");
		DateAddedLabel.setBounds(53, 335, 94, 16);
		panel.add(DateAddedLabel);
		
		JLabel avgRatingLabel = new JLabel("Average Rating: ");
		avgRatingLabel.setBounds(53, 560, 94, 16);
		panel.add(avgRatingLabel);
		
		JLabel userRatingLabel = new JLabel("Your Rating: ");
		userRatingLabel.setBounds(53, 590, 94, 16);
		panel.add(userRatingLabel);
		
		JLabel inputRatingPromptLabel = new JLabel("Input rating 1-5:");
		inputRatingPromptLabel.setBounds(53, 620, 115, 16);
		panel.add(inputRatingPromptLabel);
		
		comicSeries = new JLabel("");
		comicSeries.setBounds(182, 135, 373, 16);
		panel.add(comicSeries);
		
		author = new JLabel("");
		author.setBounds(182, 165, 373, 16);
		panel.add(author);
		
		artist = new JLabel("");
		artist.setBounds(182, 195, 373, 16);
		panel.add(artist);
		
		genre = new JLabel("");
		genre.setVerticalAlignment(SwingConstants.TOP);
		genre.setBounds(182, 225, 373, 36);
		panel.add(genre);
		
		description = new JLabel("");
		description.setVerticalAlignment(SwingConstants.TOP);
		description.setBounds(182, 395, 373, 153);
		panel.add(description);
		
		numberOfIssues = new JLabel("");
		numberOfIssues.setBounds(182, 275, 373, 16);
		panel.add(numberOfIssues);
		
		publisher = new JLabel("");
		publisher.setBounds(182, 305, 373, 16);
		panel.add(publisher);
		
		yearPublished = new JLabel("");
		yearPublished.setBounds(182, 365, 373, 16);
		panel.add(yearPublished);
		
		dateAdded = new JLabel("");
		dateAdded.setBounds(182, 335, 373, 16);
		panel.add(dateAdded);
		
		avgRating = new JLabel("");
		avgRating.setBounds(182, 560, 94, 16);
		panel.add(avgRating);
		
		userRating = new JLabel("");
		userRating.setBounds(182, 590, 94, 16);
		panel.add(userRating);
		
		inputRating = new JTextField("");
		inputRating.setBounds(182, 620, 94, 16);
		panel.add(inputRating);
		
		commentsTitle = new JLabel("Comments");
		commentsTitle.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		commentsTitle.setBounds(618, 135, 123, 34);
		panel.add(commentsTitle);
		
		commentBox = new JTextArea();
		commentBox.setBounds(618, 201, 517, 103);
		commentBox.setLineWrap(true);
		commentBox.setWrapStyleWord(true);
		
		addCommentPane = new JScrollPane(commentBox);
		addCommentPane.setBounds(618, 201, 517, 103);
		panel.add(addCommentPane);
		
		addCommentButton = new JButton(ADD_COMMENT_LABEL);
		addCommentButton.setBounds(1018, 311, 117, 29);
		panel.add(addCommentButton);

		addToFavouritesButton = new JButton(FAVORITES_LIST_LABEL);
		addToFavouritesButton.setBounds(10, 675, 175, 30);
		addToFavouritesButton.setEnabled(false);
		panel.add(addToFavouritesButton);

		addToFinishedButton = new JButton(FINISHED_LIST_LABEL);
		addToFinishedButton.setBounds(200, 675, 175, 30);
		addToFinishedButton.setEnabled(false);
		panel.add(addToFinishedButton);

		addToReadingButton = new JButton(READING_LIST_LABEL);
		addToReadingButton.setBounds(391, 675, 175, 30);
		addToReadingButton.setEnabled(false);
		panel.add(addToReadingButton);

		removeFromFavouritesButton = new JButton(REMOVE_FAVORITES_LIST_LABEL);
		removeFromFavouritesButton.setBounds(10, 710, 175, 30);
		removeFromFavouritesButton.setEnabled(false);
		panel.add(removeFromFavouritesButton);

		removeFromFinishedButton = new JButton(REMOVE_FINISHED_LIST_LABEL);
		removeFromFinishedButton.setBounds(200, 710, 175, 30);
		removeFromFinishedButton.setEnabled(false);
		panel.add(removeFromFinishedButton);

		removeFromToReadingButton = new JButton(REMOVE_READING_LIST_LABEL);
		removeFromToReadingButton.setBounds(391, 710, 175, 30);
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

		commentPane = new JScrollPane(commentList);
		commentPane.setBounds(618, 388, 517, 376);
		panel.add(commentPane);

		favouriteButtonStates();
		finishedButtonStates();
		readingButtonStates();
		setFields();
		displayComments();
		displayAvgRating();
		displayUserRating();
	}

	private void setFields() {
		comicSeries.setText(comicBookDTO.getSeriesTitle());
		author.setText(comicBookDTO.getAuthor());
		artist.setText(comicBookDTO.getArtist());
		genre.setText("<html>" + GenreUtils.genreListToString(comicBookDTO.getGenres()) + "</html>");
		description.setText("<html>" + comicBookDTO.getDescription() + "</html>");
		numberOfIssues.setText("" + comicBookDTO.getNumberOfIssues());
		publisher.setText(comicBookDTO.getPublisher());
		yearPublished.setText("" + comicBookDTO.getYearPublished());
		dateAdded.setText("" + comicBookDTO.getDateAdded());
	}
	
	public void displayAvgRating() {
		avgRatingTotal = starRatingService.getStarRatingsByComic(comicBookDTO.getId());
		int counter = 0;
		
		for (StarRatingDto s : avgRatingTotal) {
			counter += s.getRating();
		}

		avgRating.setText("" + (double) counter/avgRatingTotal.size());
	}

	public void displayUserRating() {
		int i = starRatingService.getStarRatingByID(userSession.getUser().getId(), comicBookDTO.getId()).getRating();
		userRating.setText("" + i);
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

	public boolean isComicInFavourites(long comicId) {
		comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());
		comicBookFavouritesListDaoImpl = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());
		ComicBook comicBook = comicBookDaoImpl.getComicBookById(comicId);
		ComicBookDto comicBookDto = ComicBookMapper.toDto(comicBook);
		//ComicBookDto comicBookDTO = new ComicBookDto(comicBook);
		System.out.println("the comic in favourites is " + comicBookFavouritesListDaoImpl.getAllFavouritesComicBooks().contains(comicBookDTO));
		return comicBookFavouritesListDaoImpl.getAllFavouritesComicBooks().contains(comicBookDTO);
	}

	public boolean isComicInFinished(Long userId, Long comicBookId) {
		comicBookFinishedListDaoImpl = new ComicBookFinishedListDaoImpl(HibernateUtils.getSessionFactory());
		return comicBookFinishedListDaoImpl.doesRecordExist(userId, comicBookId);
	}

	public boolean isComicInReading(Long userId, Long comicBookId) {
		comicBookReadingListDaoImpl = new ComicBookReadingListDaoImpl(HibernateUtils.getSessionFactory());
		return comicBookReadingListDaoImpl.doesRecordExist(userId, comicBookId);
	}

	public void favouriteButtonStates() {
		if (!isComicInFavourites(comicBookDTO.getId())) {
			addToFavouritesButton.setEnabled(true);
		}
		else {
			removeFromFavouritesButton.setEnabled(true);
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

	public CommentDto getComment(){

		String message = this.commentBox.getText();

		CommentDto newComment = new CommentDto();
		newComment.setUser(this.userSession.getUser());
		newComment.setComicBook(comicBookDTO);
		newComment.setMessage(message);
		newComment.setCommentDate(new Date());
		newComment.setComicBookId(this.comicBookDTO.getId());
		newComment.setUserId(this.userSession.getUser().getId());
		newComment.setUserName(this.userSession.getUser().getUserName());

		return newComment;
	}
}