package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

@Getter
@Setter
public class ProfilePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final String PANEL_LABEL = "Profile View";
	private JTextPane userProfileInformationTextPane = new JTextPane();

	/**
	 * Create the panel.
	 */
	public ProfilePanel() {
		initProfilePage();
	}

	private void initProfilePage() {
		
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
	    panel.setLayout(null);
	    
	    JLabel comicCollectionTitle = new JLabel(PANEL_LABEL);
		comicCollectionTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(182, 11, 800, 43);
		panel.add(comicCollectionTitle);
		
		add(panel, BorderLayout.CENTER);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(7, 100, 1150, 1);
		panel.add(separator);
		
		//User profile information text pane
		userProfileInformationTextPane.setEditable(false);
		userProfileInformationTextPane.setBackground(Color.WHITE);
		userProfileInformationTextPane.setBounds(74, 153, 1016, 594);
		panel.add(userProfileInformationTextPane);
	}
}
