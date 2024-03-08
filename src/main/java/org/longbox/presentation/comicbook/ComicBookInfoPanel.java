package org.longbox.presentation.comicbook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.utils.MultiLineCellRenderer;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.domainobjects.dto.CommentDTO;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.persistence.dao.ComicBookFavouritesListDaoImpl;
import org.longbox.persistence.dao.CommentDaoImpl;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.presentation.profile.FavoritesPanel;

@Getter
@Setter
public class ComicBookInfoPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_FONT = "Calibri";
	private static final String PANEL_LABEL = "Search Result";
	private static final String VIEW_COMMENTS = "View Comments";
	private ComicBookDTO comicBookDTO;
	private JPanel panel;
	//text labels
	private JLabel comicSeries;
	private JLabel author;
	private JLabel artist;
	private JLabel genre;
	private JLabel description;
	private JLabel numberOfIssues;
	private JLabel publisher;
	private JLabel yearPublished;
	private JLabel dateAdded;
	private JButton addCommentButton;
	private JButton addToFavoritesButton;
	private JTextArea commentBox;
	private DefaultListModel<CommentDTO> commentListModel;
	private CommentDaoImpl commentDaoImpl;
	private List<CommentDTO> commentsOnCurrentComic;
	private UserSession userSession;
	private JList<CommentDTO> commentList;
	private ComicBookDaoImpl comicBookDaoImpl;
	private ComicBookFavouritesListDaoImpl comicBookFavouritesListDaoImpl;
	
	/**
	 * Create the panel.
	 */
//	public ComicBookInfoPanel() {
//		initComicBookInfoPage();
//	}
	
	public ComicBookInfoPanel(ComicBookDTO comicBookDTO, UserSession userSession) {
		this.comicBookDTO = comicBookDTO;
		this.userSession = userSession;

		commentDaoImpl = new CommentDaoImpl();
		this.commentsOnCurrentComic = commentDaoImpl.getCommentsByComic(this.comicBookDTO.getId());

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
		
		JLabel CommentsTitle = new JLabel("Comments");
		CommentsTitle.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		CommentsTitle.setBounds(618, 135, 123, 34);
		panel.add(CommentsTitle);
		
		commentBox = new JTextArea();
		commentBox.setBounds(618, 201, 517, 103);
		commentBox.setLineWrap(true);
		commentBox.setWrapStyleWord(true);
		panel.add(commentBox);
		
		addCommentButton = new JButton("Add Comment");
		addCommentButton.setBounds(1018, 311, 117, 29);
		addCommentButton.addActionListener(this);
		panel.add(addCommentButton);

		addToFavoritesButton = new JButton("Add to Favorites");
		addToFavoritesButton.setBounds(53, 600, 150, 29);
		addToFavoritesButton.setEnabled(false);
		addToFavoritesButton.addActionListener(this);
		panel.add(addToFavoritesButton);

		if (!isComicInFavorites(comicBookDTO.getId())) {
			addToFavoritesButton.setEnabled(true);
		}
		
		JLabel addCommentLabel = new JLabel("Share your thoughts:");
		addCommentLabel.setBounds(618, 173, 143, 16);
		panel.add(addCommentLabel);
		
		JLabel viewCommentsLabel = new JLabel("What other think about this comic:");
		viewCommentsLabel.setBounds(618, 360, 241, 16);
		panel.add(viewCommentsLabel);

		commentListModel = new DefaultListModel<CommentDTO>();
		commentList = new JList<CommentDTO>(commentListModel);

		commentList.setCellRenderer(new MultiLineCellRenderer());

		JScrollPane commentPane = new JScrollPane(commentList);
		commentPane.setBounds(618, 388, 517, 376);
		panel.add(commentPane);

		setFields();
		displayComments();
	}


	private void setFields() {
		comicSeries.setText(comicBookDTO.getSeriesTitle());
		author.setText(comicBookDTO.getAuthor());
		artist.setText(comicBookDTO.getArtist());
		genre.setText("<html>" + ComicBookDTO.genreListToString(comicBookDTO.getGenres()) + "</html>");
		description.setText("<html>" + comicBookDTO.getDescription() + "</html>");
		numberOfIssues.setText("" + comicBookDTO.getNumberOfIssues());
		publisher.setText(comicBookDTO.getPublisher());
		yearPublished.setText("" + comicBookDTO.getYearPublished());
		dateAdded.setText("" + comicBookDTO.getDateAdded());
	}

	private void displayComments(){
		commentsOnCurrentComic = commentDaoImpl.getCommentsByComic(comicBookDTO.getId());
		commentListModel.removeAllElements();

		for (CommentDTO c : commentsOnCurrentComic) {
			commentListModel.addElement(c);
		}

		commentList.setModel(commentListModel);
		commentBox.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addCommentButton){
			String message = commentBox.getText();
			CommentDTO newComment = new CommentDTO(message, this.userSession.getUser(), this.comicBookDTO);
			commentDaoImpl.saveComment(newComment);
			displayComments();
		} else if (e.getSource() == addToFavoritesButton) {
			ComicBookFavouritesListDaoImpl favoritesListDaoImpl = new ComicBookFavouritesListDaoImpl();
            try {
                favoritesListDaoImpl.saveToFavorites(userSession.getUser().getId(), comicBookDTO.getId());
            } catch (UserIDDoesNotExistException ex) {
                throw new RuntimeException(ex);
            }
			addToFavoritesButton.setEnabled(false); // Disable the button after adding
		}
	}

	private boolean isComicInFavorites(long comicId) {
		comicBookDaoImpl = new ComicBookDaoImpl();
		comicBookFavouritesListDaoImpl = new ComicBookFavouritesListDaoImpl();
		ComicBook comicBook = comicBookDaoImpl.getComicBookById(comicId);
		ComicBookDTO comicBookDTO = new ComicBookDTO(comicBook);
		System.out.println("the comic in favorites is " + comicBookFavouritesListDaoImpl.getAllFavoritesComicBooks().contains(comicBookDTO));
		return comicBookFavouritesListDaoImpl.getAllFavoritesComicBooks().contains(comicBookDTO);
	}

}
