package org.longbox.presentation.profile;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;

public class SearchPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final String PANEL_LABEL = "Search Repository For Comic Books";
	private final String SEARCH_BY_TITLE = "Search for a comic book by title!";
	private final String SEARCH_BUTTON = "Search";
	private JTextField searchTextField;
	private JButton searchButton = new JButton(SEARCH_BUTTON);


	/**
	 * Create the panel.
	 */
	public SearchPanel() {
		initSearchPage();
	}

	private void initSearchPage() {
		
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

		//Search Text Box
		searchTextField = new JTextField();
		searchTextField.setToolTipText("");
		searchTextField.setText(SEARCH_BY_TITLE);
		searchTextField.setBounds(313, 75, 538, 20);
		panel.add(searchTextField);
		searchTextField.setColumns(10);

		//Separator
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(7, 100, 1150, 1);
		panel.add(separator);

		//Search Button
		searchButton.setBounds(518, 390, 117, 48);
		panel.add(searchButton);
	}

	public String getPANEL_LABEL() {
		return PANEL_LABEL;
	}

	public String getSEARCH_BY_TITLE() {
		return SEARCH_BY_TITLE;
	}

	public String getSEARCH_BUTTON() {
		return SEARCH_BUTTON;
	}

	public JTextField getSearchTextField() {
		return searchTextField;
	}

	public void setSearchTextField(JTextField searchTextField) {
		this.searchTextField = searchTextField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}
}
