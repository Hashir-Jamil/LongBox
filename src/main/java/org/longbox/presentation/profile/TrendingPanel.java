package org.longbox.presentation.profile;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.tablemodels.TrendingAllTimeTableModel;

import javax.swing.JTable;

public class TrendingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable comicTable;

	/**
	 * Create the panel.
	 */
	public TrendingPanel() {
		initTrendingPanel();

	}
	
	private void initTrendingPanel() {
		
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel trendingTitle = new JLabel("Trending");
		trendingTitle.setHorizontalAlignment(SwingConstants.CENTER);
		trendingTitle.setFont(new Font("Calibri", Font.PLAIN, 30));
		trendingTitle.setBounds(396, 11, 372, 43);
		panel.add(trendingTitle);
			
		ComicBookDaoImpl comicBookDaoImpl = new ComicBookDaoImpl();
		TrendingAllTimeTableModel comicBookTableModel = new TrendingAllTimeTableModel(comicBookDaoImpl.getAllComicBooks());
		
		for(ComicBookDto c : comicBookDaoImpl.getAllComicBooks()) {
			System.out.println(c + " "+ c.getFavoritesCount());
		}
		
		comicTable = new JTable(comicBookTableModel);
		comicTable.setBounds(0, 0, 1, 1);
		panel.add(comicTable);
		
		JScrollPane scrollPane = new JScrollPane(comicTable);
		scrollPane.setBounds(10, 110, 1144, 683);
		panel.add(scrollPane);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 92, 1144, 14);
		panel.add(separator);
		
		
	}
}
