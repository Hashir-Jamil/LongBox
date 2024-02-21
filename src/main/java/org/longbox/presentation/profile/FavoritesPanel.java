package org.longbox.presentation.profile;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class FavoritesPanel extends JPanel {

	private final String PANEL_LABEL = "User Favorites";

	/**
	 * Create the panel.
	 */
	public FavoritesPanel() {
		initEmptyPanel();
	}

	private void initEmptyPanel() {
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
	}
}