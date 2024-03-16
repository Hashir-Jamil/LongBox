package org.longbox.presentation.profile;

import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.tablemodels.TrendingAllTimeTableModel;

import lombok.*;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTable;

@Setter
@Getter
public class TrendingPanel extends JPanel {
	
	private JTable allTimeFavoritesTable;
	private JTable nationalFavoritesTable;
	private JPanel panel;
	private JLabel trendingTitle;
	private JSeparator separator;
	private JLabel allTimeFavoritesLabel;
	private JLabel nationalFavorites;
	private ComicBookDaoImpl comicBookDaoImpl;
	private TrendingAllTimeTableModel comicBookTableModel;
	private JScrollPane allTimeFavoritesScrollPane;
	private JScrollPane nationalFavoritesScrollPane;

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
		
		nationalFavorites = new JLabel("National Favorites: ");
		nationalFavorites.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nationalFavorites.setBounds(10, 450, 117, 22);
		panel.add(nationalFavorites);
			
		comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());
		comicBookTableModel = new TrendingAllTimeTableModel(comicBookDaoImpl.getAllComicBooks());
		
		for(ComicBookDto c : comicBookDaoImpl.getAllComicBooks()) {
			System.out.println(c + " "+ c.getFavoritesCount());
		}
		
		allTimeFavoritesTable = new JTable(comicBookTableModel);
		allTimeFavoritesTable.setBounds(0, 0, 1, 1);
		panel.add(allTimeFavoritesTable);
		
		nationalFavoritesTable = new JTable(comicBookTableModel);
		nationalFavoritesTable.setBounds(0, 0, 1, 1);
		panel.add(nationalFavoritesTable);
		
		allTimeFavoritesScrollPane = new JScrollPane(allTimeFavoritesTable);
		allTimeFavoritesScrollPane.setBounds(10, 139, 1144, 300);
		panel.add(allTimeFavoritesScrollPane);
		
		nationalFavoritesScrollPane = new JScrollPane(nationalFavoritesTable);
		nationalFavoritesScrollPane.setBounds(10, 483, 1144, 300);
		panel.add(nationalFavoritesScrollPane);
	}
}
