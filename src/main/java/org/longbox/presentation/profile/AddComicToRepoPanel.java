package org.longbox.presentation.profile;

import lombok.Getter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@Getter
public class AddComicToRepoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final String ADD_COMIC_TO_REPO = "Add a Comic Book To The LongBox Repository";
	private final String SERIES_TITLE = "Series Title";
	private final String AUTHOR_NAME = "Author Name";
	private final String ARTIST_NAME = "Artist Name";
	private final String GENRES = "Genres";
	private final String DESCRIPTION = "Description";
	private final String NUMBER_OF_ISSUES = "Number of Issues";
	private final String PUBLISHER = "Publisher";
	private final String YEAR_PUBLISHED = "Year Published";
	private final String ENTER_COMIC_BOOK_BUTTON = "Enter Comic Book";
	private JTextField comicSeriesTitleTextField;
	private JTextField comicBookAuthorTextField;
	private JTextField comicBookArtistTextField;
	private JTextField genresTextField;
	private JTextField descriptionTextField;
	private JTextField numberOfIssuesTextField;
	private JTextField publisherTextField;
	private JTextField yearPublishedTextField;
	private JButton enterComicBookButton;
	private JCheckBox favoriteCheckbox;

	/**
	 * Create the panel.
	 */
	public AddComicToRepoPanel() {
		initAddComicToRepoPage();
	}

	private void initAddComicToRepoPage() {
		
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
	    panel.setLayout(null);
	    
	    //Panel Label
	    JLabel comicCollectionTitle = new JLabel(ADD_COMIC_TO_REPO);
		comicCollectionTitle.setFont(new Font("Bradley Hand", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(182, 11, 800, 43);
		panel.add(comicCollectionTitle);
				
		//Form Separator
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(7, 100, 1150, 1);
		panel.add(separator);
		
		add(panel, BorderLayout.CENTER);
		
		//Field 1 Series Title Label
		JLabel enterComicBookNameLabel = new JLabel(SERIES_TITLE);
		panel.add(enterComicBookNameLabel);
		enterComicBookNameLabel.setForeground(new Color(0, 0, 0));
		enterComicBookNameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		enterComicBookNameLabel.setBounds(111, 150, 174, 20);
		
		//Field 1 Series Title Input Box
		comicSeriesTitleTextField = new JTextField();
		comicSeriesTitleTextField.setBounds(287, 150, 590, 20);
		panel.add(comicSeriesTitleTextField);
		comicSeriesTitleTextField.setColumns(50);
		
		//Field 2 Author Name Label
		JLabel authorNameLabel = new JLabel(AUTHOR_NAME);
		authorNameLabel.setForeground(Color.BLACK);
		authorNameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		authorNameLabel.setBounds(111, 212, 174, 20);
		panel.add(authorNameLabel);
		
		//Field 2 Author Name Input Box
		comicBookAuthorTextField = new JTextField();
		comicBookAuthorTextField.setColumns(50);
		comicBookAuthorTextField.setBounds(287, 212, 590, 20);
		panel.add(comicBookAuthorTextField);

		//Field 3 Artist Name Label
		JLabel artistLabel = new JLabel(ARTIST_NAME);
		artistLabel.setForeground(Color.BLACK);
		artistLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		artistLabel.setBounds(111, 274, 174, 20);
		panel.add(artistLabel);

		//Field 3 Artist Name Input Box
		comicBookArtistTextField = new JTextField();
		comicBookArtistTextField.setColumns(50);
		comicBookArtistTextField.setBounds(287, 274, 590, 20);
		panel.add(comicBookArtistTextField);

		//Field 4 Genres Label
		JLabel genresLabel = new JLabel(GENRES);
		genresLabel.setForeground(Color.BLACK);
		genresLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		genresLabel.setBounds(111, 336, 174, 20);
		panel.add(genresLabel);

		//Field 4 Genres Input Box
		genresTextField = new JTextField();
		genresTextField.setColumns(50);
		genresTextField.setBounds(287, 336, 590, 20);
		panel.add(genresTextField);
		
		//Field 5 Description Label
		JLabel descriptionLabel = new JLabel(DESCRIPTION);
		descriptionLabel.setForeground(Color.BLACK);
		descriptionLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		descriptionLabel.setBounds(111, 386, 174, 20);
		panel.add(descriptionLabel);

		//Field 5 Description Input Box
		descriptionTextField = new JTextField();
		descriptionTextField.setColumns(50);
		descriptionTextField.setBounds(287, 386, 590, 20);
		panel.add(descriptionTextField);

		//Field 6 Number of Issues Label
		JLabel numberOfIssuesLabel = new JLabel(NUMBER_OF_ISSUES);
		numberOfIssuesLabel.setForeground(Color.BLACK);
		numberOfIssuesLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		numberOfIssuesLabel.setBounds(111, 436, 174, 20);
		panel.add(numberOfIssuesLabel);

		//Field 6 Number of Issues Text Box
		numberOfIssuesTextField = new JTextField();
		numberOfIssuesTextField.setColumns(50);
		numberOfIssuesTextField.setBounds(287, 436, 590, 20);
		panel.add(numberOfIssuesTextField);

		//Field 7 Publisher Label
		JLabel publisherLabel = new JLabel(PUBLISHER);
		publisherLabel.setForeground(Color.BLACK);
		publisherLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		publisherLabel.setBounds(111, 486, 174, 20);
		panel.add(publisherLabel);

		//Field 7 Publisher Input Box
		publisherTextField = new JTextField();
		publisherTextField.setColumns(50);
		publisherTextField.setBounds(287, 486, 590, 20);
		panel.add(publisherTextField);

		//Field 8 Year Published Label
		JLabel yearPublishedLabel = new JLabel(YEAR_PUBLISHED);
		yearPublishedLabel.setForeground(Color.BLACK);
		yearPublishedLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		yearPublishedLabel.setBounds(111, 536, 174, 20);
		panel.add(yearPublishedLabel);

		//Field 8 Year Published Input Box
		yearPublishedTextField = new JTextField();
		yearPublishedTextField.setColumns(50);
		yearPublishedTextField.setBounds(287, 536, 590, 20);
		panel.add(yearPublishedTextField);

		enterComicBookButton = new JButton(ENTER_COMIC_BOOK_BUTTON);
		enterComicBookButton.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		enterComicBookButton.setBounds(482, 650, 200, 40);

		favoriteCheckbox = new JCheckBox("Is Favorite");
		favoriteCheckbox.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		favoriteCheckbox.setBounds(287, 600, 590, 20);
		panel.add(favoriteCheckbox);

		enterComicBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(enterComicBookButton);
		enterComicBookButton.setFocusable(false);
	}
}