package org.longbox.presentation.profile;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class TrendingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TrendingPanel() {
		initTrendingPanel();

	}
	
	private void initTrendingPanel() {
		
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
	}

}
