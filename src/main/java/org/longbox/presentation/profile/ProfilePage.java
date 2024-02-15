package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.UserSession;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;

public class ProfilePage extends JPanel {

	private static final long serialVersionUID = 1L;
	private final String PANEL_LABEL = "Profile View";

	private UserSession userSession;
	private JTextPane userProfileInformationTextPane = new JTextPane();

	/**
	 * Create the panel.
	 */
	public ProfilePage() {
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

	public void setUserSession(UserSession userSession) {
		String htmlContent = String.format(
				"<html><body><div align='center'><h1>Username: %s</h1><br" +
						"<h2>First Name: %s</h2><br" +
						"<h2>Last Name: %s</h2><br" +
						"<h2>Email Address: %s</h2><br" +
						"<h2>Date of Birth: %s</h2><br" +
						"<h2>Country: %s</h2><br" +
						"<h2>Join Date: %s</h2><br" +
						"</div></body></html><br",
				userSession.getUser().getUserName(),
				userSession.getUser().getFirstName(),
				userSession.getUser().getLastName(),
				userSession.getUser().getEmail(),
				userSession.getUser().getDob().toString(),
				userSession.getUser().getCountry(),
				userSession.getUser().getJoinDate().toString()
		);

		HTMLEditorKit kit = new HTMLEditorKit();
		userProfileInformationTextPane.setEditorKit(kit);
		userProfileInformationTextPane.setText(htmlContent);
	}

}
