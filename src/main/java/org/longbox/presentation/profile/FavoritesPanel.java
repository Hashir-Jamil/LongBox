package org.longbox.presentation.profile;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class FavoritesPanel extends JPanel {

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
		add(panel, BorderLayout.CENTER);
	}
}
