package org.longbox.presentation.comicbook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class ComicBookCommentsPanel extends JPanel {

	private final long serialVersionUID = 1L;
	private final String PANEL_LABEL = "Comments";
	private final String VIEW_COMIC_BOOK = "Back To Comic Book Information";
	private JButton viewComicBook;
	private JTextPane comicBookCommentsextPane = new JTextPane();

	/**
	 * Create the panel.
	 */
	public ComicBookCommentsPanel() {
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
	    panel.setLayout(null);
	    
	    JLabel comicCommentsTitle = new JLabel(PANEL_LABEL);
	    comicCommentsTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicCommentsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCommentsTitle.setBounds(182, 25, 800, 43);
		panel.add(comicCommentsTitle);
		
		add(panel, BorderLayout.CENTER);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(7, 100, 1150, 1);
		panel.add(separator);
		
		//User profile information text pane
		comicBookCommentsextPane.setEditable(false);
		comicBookCommentsextPane.setBackground(Color.WHITE);
		comicBookCommentsextPane.setBounds(74, 153, 1016, 594);
		panel.add(comicBookCommentsextPane);
		
		viewComicBook = new JButton(VIEW_COMIC_BOOK);
		viewComicBook.setBounds(473, 758, 218, 34);
		panel.add(viewComicBook);
	}

}
