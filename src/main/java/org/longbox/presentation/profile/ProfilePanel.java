package org.longbox.presentation.profile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
public class ProfilePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final String PANEL_LABEL = "Profile View";
	private JTextPane userProfileInformationTextPane = new JTextPane();
	private JTextPane userStatsTextPane = new JTextPane();

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
		
		//Top horizontal topSeparator
		JSeparator topSeparator = new JSeparator();
		topSeparator.setBackground(new Color(0, 0, 0));
		topSeparator.setForeground(new Color(0, 0, 0));
		topSeparator.setBounds(7, 100, 1150, 1);
		panel.add(topSeparator);
		
		//Middle vertical topSeparator
		JSeparator midSeparator = new JSeparator();
		midSeparator.setOrientation(SwingConstants.VERTICAL);
		midSeparator.setBackground(new Color(0, 0, 0));
		midSeparator.setForeground(new Color(0, 0, 0));
		midSeparator.setBounds(500, 100, 30, 700);
		panel.add(midSeparator);
		
		//User profile information text pane
		userProfileInformationTextPane.setEditable(false);
		userProfileInformationTextPane.setBackground(Color.WHITE);
		userProfileInformationTextPane.setBounds(41, 138, 420, 620);
		panel.add(userProfileInformationTextPane);
		
		//User stats information text pane
		userStatsTextPane.setEditable(false);
		userStatsTextPane.setBackground(Color.WHITE);
		userStatsTextPane.setBounds(new Rectangle(540, 138, 583, 620));
		panel.add(userStatsTextPane);
		
	}

	public String getPANEL_LABEL() {
		return PANEL_LABEL;
	}

	public JTextPane getUserProfileInformationTextPane() {
		return userProfileInformationTextPane;
	}

	public void setUserProfileInformationTextPane(JTextPane userProfileInformationTextPane) {
		this.userProfileInformationTextPane = userProfileInformationTextPane;
	}

	public JTextPane getUserStatsTextPane() {
		return userStatsTextPane;
	}

	public void setUserStatsTextPane(JTextPane userStatsTextPane) {
		this.userStatsTextPane = userStatsTextPane;
	}
}
