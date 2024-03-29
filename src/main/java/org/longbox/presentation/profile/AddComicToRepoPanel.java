package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@Getter
@Setter
public class AddComicToRepoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_FONT = "Calibri";
	private final String ADD_COMIC_TO_REPO = "Add a Comic Book To The LongBox Repository";
	private final String SERIES_TITLE = "Series Title";
	private final String AUTHOR_NAME = "Author Name";
	private final String ARTIST_NAME = "Artist Name";
	private final String GENRES = "Genres";
	private final String DESCRIPTION = "Description";
	private final String NUMBER_OF_ISSUES = "Number of Issues (Positive number)";
	private final String PUBLISHER = "Publisher";
	private final String YEAR_PUBLISHED = "Year Published (Positive number)";
	private final String ENTER_COMIC_BOOK_BUTTON = "Enter Comic Book";
	private final String SUCCESS_MESSAGE = "Comic Book Successfully Added!";
	private JTextField comicSeriesTitleTextField;
	private JTextField comicBookAuthorTextField;
	private JTextField comicBookArtistTextField;
	private JTextField genresTextField;
	private JTextField descriptionTextField;
	private JTextField numberOfIssuesTextField;
	private JTextField publisherTextField;
	private JTextField yearPublishedTextField;
	private JTextField successMessage;
	private JButton enterComicBookButton;
	private JCheckBox favouriteCheckbox;
	private JPanel panel;

	public AddComicToRepoPanel() {
		initAddComicToRepoPage();
	}
	private void initAddComicToRepoPage() {
		
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
	    panel.setLayout(null);
	    
	    //Panel Label
	    JLabel comicCollectionTitle = new JLabel(ADD_COMIC_TO_REPO);
		comicCollectionTitle.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 30));
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
		enterComicBookNameLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		enterComicBookNameLabel.setBounds(111, 150, 174, 20);
		
		//Field 1 Series Title Input Box
		comicSeriesTitleTextField = new JTextField();
		comicSeriesTitleTextField.setBounds(400, 150, 590, 20);
		panel.add(comicSeriesTitleTextField);
		comicSeriesTitleTextField.setColumns(50);
		
		//Field 2 Author Name Label
		JLabel authorNameLabel = new JLabel(AUTHOR_NAME);
		authorNameLabel.setForeground(Color.BLACK);
		authorNameLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		authorNameLabel.setBounds(111, 212, 174, 20);
		panel.add(authorNameLabel);
		
		//Field 2 Author Name Input Box
		comicBookAuthorTextField = new JTextField();
		comicBookAuthorTextField.setColumns(50);
		comicBookAuthorTextField.setBounds(400, 212, 590, 20);
		panel.add(comicBookAuthorTextField);

		//Field 3 Artist Name Label
		JLabel artistLabel = new JLabel(ARTIST_NAME);
		artistLabel.setForeground(Color.BLACK);
		artistLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		artistLabel.setBounds(111, 274, 174, 20);
		panel.add(artistLabel);

		//Field 3 Artist Name Input Box
		comicBookArtistTextField = new JTextField();
		comicBookArtistTextField.setColumns(50);
		comicBookArtistTextField.setBounds(400, 274, 590, 20);
		panel.add(comicBookArtistTextField);

		//Field 4 Genres Label
		JLabel genresLabel = new JLabel(GENRES);
		genresLabel.setForeground(Color.BLACK);
		genresLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		genresLabel.setBounds(111, 336, 174, 20);
		panel.add(genresLabel);

		//Field 4 Genres Input Box
		genresTextField = new JTextField();
		genresTextField.setColumns(50);
		genresTextField.setBounds(400, 336, 590, 20);
		panel.add(genresTextField);
		
		//Field 5 Description Label
		JLabel descriptionLabel = new JLabel(DESCRIPTION);
		descriptionLabel.setForeground(Color.BLACK);
		descriptionLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		descriptionLabel.setBounds(111, 386, 174, 20);
		panel.add(descriptionLabel);

		//Field 5 Description Input Box
		descriptionTextField = new JTextField();
		descriptionTextField.setColumns(50);
		descriptionTextField.setBounds(400, 386, 590, 20);
		panel.add(descriptionTextField);

		//Field 6 Number of Issues Label
		JLabel numberOfIssuesLabel = new JLabel(NUMBER_OF_ISSUES);
		numberOfIssuesLabel.setForeground(Color.BLACK);
		numberOfIssuesLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		numberOfIssuesLabel.setBounds(111, 436, 289, 20);
		panel.add(numberOfIssuesLabel);

		//Field 6 Number of Issues Text Box
		numberOfIssuesTextField = new JTextField();
		numberOfIssuesTextField.setColumns(50);
		numberOfIssuesTextField.setBounds(400, 436, 590, 20);
		panel.add(numberOfIssuesTextField);

		//Field 7 Publisher Label
		JLabel publisherLabel = new JLabel(PUBLISHER);
		publisherLabel.setForeground(Color.BLACK);
		publisherLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		publisherLabel.setBounds(111, 486, 174, 20);
		panel.add(publisherLabel);

		//Field 7 Publisher Input Box
		publisherTextField = new JTextField();
		publisherTextField.setColumns(50);
		publisherTextField.setBounds(400, 486, 590, 20);
		panel.add(publisherTextField);

		//Field 8 Year Published Label
		JLabel yearPublishedLabel = new JLabel(YEAR_PUBLISHED);
		yearPublishedLabel.setForeground(Color.BLACK);
		yearPublishedLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		yearPublishedLabel.setBounds(111, 536, 289, 20);
		panel.add(yearPublishedLabel);

		//Field 8 Year Published Input Box
		yearPublishedTextField = new JTextField();
		yearPublishedTextField.setColumns(50);
		yearPublishedTextField.setBounds(400, 536, 590, 20);
		panel.add(yearPublishedTextField);

		//Button to send the form input
		enterComicBookButton = new JButton(ENTER_COMIC_BOOK_BUTTON);
		enterComicBookButton.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		enterComicBookButton.setBounds(482, 650, 200, 40);
		enterComicBookButton.setEnabled(false);

		//Radio button to
		favouriteCheckbox = new JCheckBox("Is Favourite");
		favouriteCheckbox.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 16));
		favouriteCheckbox.setBounds(287, 600, 590, 20);
		panel.add(favouriteCheckbox);

		// Action listeners for each field's text input box
		comicSeriesTitleTextField.addActionListener(fieldsListener);
		comicBookAuthorTextField.addActionListener(fieldsListener);
		comicBookArtistTextField.addActionListener(fieldsListener);
		genresTextField.addActionListener(fieldsListener);
		descriptionTextField.addActionListener(fieldsListener);
		numberOfIssuesTextField.addActionListener(fieldsListener);
		publisherTextField.addActionListener(fieldsListener);
		yearPublishedTextField.addActionListener(fieldsListener);

		// Focus adapters
		comicSeriesTitleTextField.addFocusListener(focusAdapter);
		comicBookAuthorTextField.addFocusListener(focusAdapter);
		comicBookArtistTextField.addFocusListener(focusAdapter);
		genresTextField.addFocusListener(focusAdapter);
		descriptionTextField.addFocusListener(focusAdapter);
		numberOfIssuesTextField.addFocusListener(focusAdapter);
		publisherTextField.addFocusListener(focusAdapter);
		yearPublishedTextField.addFocusListener(focusAdapter);

		//Document listener for each field
		comicSeriesTitleTextField.getDocument().addDocumentListener(documentListener);
		comicBookAuthorTextField.getDocument().addDocumentListener(documentListener);
		comicBookArtistTextField.getDocument().addDocumentListener(documentListener);
		genresTextField.getDocument().addDocumentListener(documentListener);
		descriptionTextField.getDocument().addDocumentListener(documentListener);
		numberOfIssuesTextField.getDocument().addDocumentListener(documentListener);
		publisherTextField.getDocument().addDocumentListener(documentListener);
		yearPublishedTextField.getDocument().addDocumentListener(documentListener);

		enterComicBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(enterComicBookButton);
		enterComicBookButton.setFocusable(false);
	}

	ActionListener fieldsListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateButtonState();
		}
	};

	FocusAdapter focusAdapter = new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent e) {
			updateButtonState();
		}
	};

	DocumentListener documentListener = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
			updateButtonState();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			updateButtonState();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			updateButtonState();
		}
	};

	private void updateButtonState() {
		if (!comicSeriesTitleTextField.getText().isEmpty() &&
			!comicBookAuthorTextField.getText().isEmpty() &&
			!comicBookArtistTextField.getText().isEmpty() &&
			!genresTextField.getText().isEmpty() &&
			!numberOfIssuesTextField.getText().isEmpty() &&
			numberOfIssuesTextField.getText().matches("\\d+") &&
			!publisherTextField.getText().isEmpty() &&
			!yearPublishedTextField.getText().isEmpty() &&
			yearPublishedTextField.getText().matches("\\d+")
		) {
			enterComicBookButton.setEnabled(true);
		}
	}
}