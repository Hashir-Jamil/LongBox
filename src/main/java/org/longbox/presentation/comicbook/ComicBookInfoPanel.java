package org.longbox.presentation.comicbook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.*;

import org.longbox.businesslogic.utils.MultiLineCellRenderer;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.domainobjects.dto.CommentDTO;
import org.longbox.persistence.dao.CommentDaoImpl;
import org.longbox.persistence.entity.Comment;

public class ComicBookInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String PANEL_LABEL = "Search Result";
	private static final String VIEW_COMMENTS = "View Comments";
	private ComicBookDTO comicBookDTO;

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
	private DefaultListModel<Comment> commentListModel;
	private CommentDaoImpl commentDaoImpl;
	private List<CommentDTO> commentsOnCurrentComic;
	/**
	 * Create the panel.
	 */
//	public ComicBookInfoPanel() {
//		initComicBookInfoPage();
//	}
	
	public ComicBookInfoPanel(ComicBookDTO comicBookDTO) {
		this.comicBookDTO = comicBookDTO;
		commentDaoImpl = new CommentDaoImpl();
		this.commentsOnCurrentComic = commentDaoImpl.getCommentsByComic(this.comicBookDTO.getId());

		System.out.print("this is the id: ");
		System.out.println(this.comicBookDTO.getId());

		initComicBookInfoPage();
	}

	private void initComicBookInfoPage() {
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
	    panel.setLayout(null);
	    
	    JLabel comicCollectionTitle = new JLabel(PANEL_LABEL);
		comicCollectionTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
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
		CommentsTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		CommentsTitle.setBounds(618, 135, 123, 34);
		panel.add(CommentsTitle);
		
		JTextArea commentBox = new JTextArea();
		commentBox.setBounds(618, 201, 517, 103);
		commentBox.setLineWrap(true);
		commentBox.setWrapStyleWord(true);
		panel.add(commentBox);
		
		JButton addCommentButton = new JButton("Add Comment");
		addCommentButton.setBounds(1018, 311, 117, 29);
		panel.add(addCommentButton);
		
		JLabel addCommentLabel = new JLabel("Share your thoughts:");
		addCommentLabel.setBounds(618, 173, 143, 16);
		panel.add(addCommentLabel);
		
		JLabel viewCommentsLabel = new JLabel("What other think about this comic:");
		viewCommentsLabel.setBounds(618, 360, 241, 16);
		panel.add(viewCommentsLabel);

		commentListModel = new DefaultListModel<>();
		JList<Comment> commentList = new JList<>(commentListModel);

		commentList.setCellRenderer(new MultiLineCellRenderer());

		for(CommentDTO c: commentsOnCurrentComic){
			commentListModel.addElement(new Comment(c));
		}

		JScrollPane commentPane = new JScrollPane(commentList);
		commentPane.setBounds(618, 388, 517, 376);
		panel.add(commentPane);

		setFields();
	}


	public void setFields() {
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

}
