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
	
	private JTable allTimeFavouritesTable;
	private JTable regionalFavouritesTable;
	private JPanel panel;
	private JLabel trendingTitle;
	private JSeparator separator;
	private JLabel allTimeFavouritesLabel;
	private JLabel regionalFavourites;
	private ComicBookDaoImpl comicBookDaoImpl;
	private TrendingAllTimeTableModel comicBookTableModel;
	private TrendingRegionalTableModel regionalComicBookTableModel;
	private JScrollPane allTimeFavouritesScrollPane;
	private JScrollPane regionalFavouritesScrollPane;
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
		
		allTimeFavouritesLabel = new JLabel("All Time Favourites:");
		allTimeFavouritesLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		allTimeFavouritesLabel.setBounds(10, 106, 117, 22);
		panel.add(allTimeFavouritesLabel);
		
		regionalFavourites = new JLabel("Regional Favourites: ");
		regionalFavourites.setFont(new Font("Tahoma", Font.PLAIN, 14));
		regionalFavourites.setBounds(10, 450, 127, 22);
		panel.add(regionalFavourites);
			
		comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());

		comicBookTableModel = new TrendingAllTimeTableModel(comicBookDaoImpl.getAllComicBooks());		
		regionalComicBookTableModel = new TrendingRegionalTableModel(comicBookDaoImpl.getAllComicBooks(), "North America");
		
		allTimeFavouritesTable = new JTable(comicBookTableModel);
		allTimeFavouritesTable.setBounds(0, 0, 1, 1);
		panel.add(allTimeFavouritesTable);
		
		regionalFavouritesTable = new JTable(regionalComicBookTableModel);
		regionalFavouritesTable.setBounds(0, 0, 1, 1);
		panel.add(regionalFavouritesTable);
		
		allTimeFavouritesScrollPane = new JScrollPane(allTimeFavouritesTable);
		allTimeFavouritesScrollPane.setBounds(10, 139, 1144, 300);
		panel.add(allTimeFavouritesScrollPane);
		
		regionalFavouritesScrollPane = new JScrollPane(regionalFavouritesTable);
		regionalFavouritesScrollPane.setBounds(10, 483, 1144, 300);
		panel.add(regionalFavouritesScrollPane);
		
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
