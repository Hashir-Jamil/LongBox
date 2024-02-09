package org.longbox.presentation;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JList;

public class SearchPage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private final String PANEL_LABEL = "Search Repository For Comic Books";

	/**
	 * Create the panel.
	 */
	public SearchPage() {
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
		
		txtSearch = new JTextField();
		txtSearch.setToolTipText("");
		txtSearch.setText("Search");
		txtSearch.setBounds(956, 75, 201, 20);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(7, 100, 1150, 1);
		panel.add(separator);
		
		JList list = new JList();
		list.setBounds(518, 417, 1, 1);
		panel.add(list);
		
	}
}
