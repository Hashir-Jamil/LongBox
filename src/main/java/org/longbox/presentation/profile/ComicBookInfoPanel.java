package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
@Getter
@Setter
public class ComicBookInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String PANEL_LABEL = "Search Result";
	private static final String VIEW_COMMENTS = "View Comments";
	private JTextPane comicBookInfoTextPane = new JTextPane();
	private JButton commentsButton;

	/**
	 * Create the panel.
	 */
	public ComicBookInfoPanel() {
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
		
		//User profile information text pane
		comicBookInfoTextPane.setEditable(false);
		comicBookInfoTextPane.setBackground(Color.WHITE);
		comicBookInfoTextPane.setBounds(74, 153, 1016, 594);
		panel.add(comicBookInfoTextPane);
		
		commentsButton = new JButton(VIEW_COMMENTS);
		commentsButton.setBounds(517, 758, 130, 34);
		panel.add(commentsButton);
		
	}

}
