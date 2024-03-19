package org.longbox.presentation.profile;

import org.longbox.config.HibernateUtils;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.tablemodels.TrendingAllTimeTableModel;
import org.longbox.presentation.tablemodels.TrendingRegionalTableModel;

import lombok.*;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JComboBox;

@Setter
@Getter
public class TrendingPanel extends JPanel {
	
	private JTable allTimeFavoritesTable;
	private JTable regionalFavoritesTable;
	private JPanel panel;
	private JLabel trendingTitle;
	private JSeparator separator;
	private JLabel allTimeFavoritesLabel;
	private JLabel regionalFavorites;
	private ComicBookDaoImpl comicBookDaoImpl;
	private TrendingAllTimeTableModel comicBookTableModel;
	private TrendingRegionalTableModel regionalComicBookTableModel;
	private JScrollPane allTimeFavoritesScrollPane;
	private JScrollPane regionalFavoritesScrollPane;
	private JComboBox<String> regionBox;

	public TrendingPanel() {
		initTrendingPanel();
	}
	
	private void initTrendingPanel() {
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		trendingTitle = new JLabel("Trending");
		trendingTitle.setHorizontalAlignment(SwingConstants.CENTER);
		trendingTitle.setFont(new Font("Calibri", Font.PLAIN, 30));
		trendingTitle.setBounds(396, 11, 372, 43);
		panel.add(trendingTitle);
		
		separator = new JSeparator();
		separator.setBounds(10, 92, 1144, 14);
		panel.add(separator);
		
		allTimeFavoritesLabel = new JLabel("All Time Favorites:");
		allTimeFavoritesLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		allTimeFavoritesLabel.setBounds(10, 106, 117, 22);
		panel.add(allTimeFavoritesLabel);
		
		regionalFavorites = new JLabel("Regional Favorites: ");
		regionalFavorites.setFont(new Font("Tahoma", Font.PLAIN, 14));
		regionalFavorites.setBounds(10, 450, 127, 22);
		panel.add(regionalFavorites);
			
		comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());

		comicBookTableModel = new TrendingAllTimeTableModel(comicBookDaoImpl.getAllComicBooks());		
		regionalComicBookTableModel = new TrendingRegionalTableModel(comicBookDaoImpl.getAllComicBooks(), "North America");
		
		allTimeFavoritesTable = new JTable(comicBookTableModel);
		allTimeFavoritesTable.setBounds(0, 0, 1, 1);
		panel.add(allTimeFavoritesTable);
		
		regionalFavoritesTable = new JTable(regionalComicBookTableModel);
		regionalFavoritesTable.setBounds(0, 0, 1, 1);
		panel.add(regionalFavoritesTable);
		
		allTimeFavoritesScrollPane = new JScrollPane(allTimeFavoritesTable);
		allTimeFavoritesScrollPane.setBounds(10, 139, 1144, 300);
		panel.add(allTimeFavoritesScrollPane);
		
		regionalFavoritesScrollPane = new JScrollPane(regionalFavoritesTable);
		regionalFavoritesScrollPane.setBounds(10, 483, 1144, 300);
		panel.add(regionalFavoritesScrollPane);
		
		regionBox = new JComboBox<String>();
		regionBox.setBounds(130, 452, 117, 22);
		regionBox.addItem("North America");
		regionBox.addItem("South America");
		regionBox.addItem("Europe");
		regionBox.addItem("Asia");
		regionBox.addItem("Africa");
		regionBox.addItem("Oceania");
		regionBox.addItem("Antarctica");
		panel.add(regionBox);
	}
}
