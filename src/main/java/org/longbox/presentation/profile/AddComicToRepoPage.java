package org.longbox.presentation.profile;

import lombok.Getter;
import org.longbox.domainobjects.dto.ComicBookDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

@Getter
public class AddComicToRepoPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private final String ADD_COMIC_TO_REPO = "Add a Comic Book To The LongBox Repository";
	private final String COMIC_BOOK_NAME = "Comic Series Title";
	private final String AUTHOR_NAME = "Author Name";
	private final String PUBLISHER = "Publisher";
	private final String YEAR = "Year";
	private final String ENTER_COMIC_BOOK_BUTTON = "Enter Comic Book";
	private JTextField comicBookNameTextField;
	private JTextField comicBookAuthorTextField;
	private JTextField publisherTextField;
	private JTextField yearTextField;
	private JButton enterComicBookButton;

	/**
	 * Create the panel.
	 */
	public AddComicToRepoPage() {
		initAddComicToRepoPage();
	}

	private void initAddComicToRepoPage() {
		
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
	    panel.setLayout(null);
	    
	    JLabel comicCollectionTitle = new JLabel(ADD_COMIC_TO_REPO);
		comicCollectionTitle.setFont(new Font("Bradley Hand", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(182, 11, 800, 43);
		panel.add(comicCollectionTitle);
				
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(7, 100, 1150, 1);
		panel.add(separator);
		
		add(panel, BorderLayout.CENTER);
		
		JLabel enterComicBookNameLabel = new JLabel(COMIC_BOOK_NAME);
		panel.add(enterComicBookNameLabel);
		enterComicBookNameLabel.setForeground(new Color(0, 0, 0));
		enterComicBookNameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		enterComicBookNameLabel.setBounds(194, 147, 174, 20);
		
		comicBookNameTextField = new JTextField();
		comicBookNameTextField.setBounds(378, 150, 590, 20);
		panel.add(comicBookNameTextField);
		comicBookNameTextField.setColumns(50);
		
		JLabel authorNameLabel = new JLabel(AUTHOR_NAME);
		authorNameLabel.setForeground(Color.BLACK);
		authorNameLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		authorNameLabel.setBounds(194, 212, 174, 20);
		panel.add(authorNameLabel);
		
		comicBookAuthorTextField = new JTextField();
		comicBookAuthorTextField.setColumns(50);
		comicBookAuthorTextField.setBounds(378, 212, 590, 20);
		panel.add(comicBookAuthorTextField);

		JLabel publisherLabel = new JLabel(PUBLISHER);
		publisherLabel.setForeground(Color.BLACK);
		publisherLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		publisherLabel.setBounds(194, 274, 174, 20);
		panel.add(publisherLabel);

		publisherTextField = new JTextField();
		publisherTextField.setColumns(50);
		publisherTextField.setBounds(378, 274, 590, 20);
		panel.add(publisherTextField);

		JLabel yearLabel = new JLabel(YEAR);
		yearLabel.setForeground(Color.BLACK);
		yearLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		yearLabel.setBounds(194, 336, 174, 20);
		panel.add(yearLabel);

		yearTextField = new JTextField();
		yearTextField.setColumns(50);
		yearTextField.setBounds(378, 336, 590, 20);
		panel.add(yearTextField);

		enterComicBookButton = new JButton(ENTER_COMIC_BOOK_BUTTON);
		enterComicBookButton.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		enterComicBookButton.setBounds(482, 434, 200, 40);
		enterComicBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(enterComicBookButton);
		enterComicBookButton.setFocusable(false);
	}
}
