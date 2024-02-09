package org.longbox.presentation;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ComicCollectionPage extends JPanel {

	private static final long serialVersionUID = 1L;

	ComicCollectionPage() {
		initComicCollectionPage();
	}
	

	/**
	 * Create the Panel.
	 */
	private void initComicCollectionPage() {

		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
	    panel.setLayout(null);
		
		JLabel comicCollectionTitle = new JLabel("Comic Collection");
		comicCollectionTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(396, 11, 372, 43);
		panel.add(comicCollectionTitle);
		
		JLabel lblNewLabel_1 = new JLabel("Sort By:");
		lblNewLabel_1.setBounds(922, 65, 60, 14);
		panel.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 86, 1144, 14);
		panel.add(separator);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(992, 61, 162, 22);
		panel.add(comboBox);
		
		comboBox.addItem("A-Z");
		comboBox.addItem("Z-A");
		comboBox.addItem("Date Added (Recent)");
		comboBox.addItem("Date Added (Oldest)");
		comboBox.addItem("Publication Date (Recent)");
		comboBox.addItem("Publication Date (Oldest)");
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(1137, 111, 17, 681);
		panel.add(scrollBar);
		
		JList list = new JList();
		list.setBounds(10, 111, 1144, 681);
		panel.add(list);
		
		add(panel, BorderLayout.CENTER);
		
	}

}
