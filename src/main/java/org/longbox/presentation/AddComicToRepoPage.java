package org.longbox.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class AddComicToRepoPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private final String ADD_COMIC_TO_REPO = "Add a Comic Book To The LongBox Repository";

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
		comicCollectionTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(182, 11, 800, 43);
		panel.add(comicCollectionTitle);
				
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(7, 100, 1150, 1);
		panel.add(separator);
		
		add(panel, BorderLayout.CENTER);		
	}

}
