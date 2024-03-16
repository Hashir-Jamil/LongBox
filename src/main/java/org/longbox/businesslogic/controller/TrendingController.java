package org.longbox.businesslogic.controller;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.service.UserService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.profile.TrendingPanel;
import org.longbox.presentation.tablemodels.TrendingAllTimeTableModel;

public class TrendingController {

	private TrendingPanel trendingPanel;
	
	public TrendingController(TrendingPanel trendingPanel) {
		this.trendingPanel = trendingPanel;
		addListeners();
	}
	
	private void addListeners() {
		
	}
	
	public void reloadTrending() {
		this.trendingPanel.getPanel().remove(this.trendingPanel.getAllTimeFavoritesScrollPane());
		this.trendingPanel.getPanel().remove(this.trendingPanel.getNationalFavoritesScrollPane());
		
		this.trendingPanel.setComicBookDaoImpl(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));
		
		this.trendingPanel.setComicBookTableModel(new TrendingAllTimeTableModel(this.trendingPanel.getComicBookDaoImpl().getAllComicBooks()));
		//comicBookTableModel = new TrendingAllTimeTableModel(comicBookDaoImpl.getAllComicBooks());
		
		this.trendingPanel.setAllTimeFavoritesTable(new JTable(this.trendingPanel.getComicBookTableModel()));
		this.trendingPanel.getAllTimeFavoritesTable().setBounds(0, 0, 1, 1);
		this.trendingPanel.getPanel().add(this.trendingPanel.getAllTimeFavoritesTable());
//		allTimeFavoritesTable = new JTable(comicBookTableModel);
//		allTimeFavoritesTable.setBounds(0, 0, 1, 1);
//		panel.add(allTimeFavoritesTable);
		
		this.trendingPanel.setNationalFavoritesTable(new JTable(this.trendingPanel.getComicBookTableModel()));
		this.trendingPanel.getNationalFavoritesTable().setBounds(0, 0, 1, 1);
		this.trendingPanel.getPanel().add(this.trendingPanel.getNationalFavoritesTable());
//		nationalFavoritesTable = new JTable(comicBookTableModel);
//		nationalFavoritesTable.setBounds(0, 0, 1, 1);
//		panel.add(nationalFavoritesTable);
		
		this.trendingPanel.setAllTimeFavoritesScrollPane(new JScrollPane(this.trendingPanel.getAllTimeFavoritesTable()));
		this.trendingPanel.getAllTimeFavoritesScrollPane().setBounds(10, 139, 1144, 300);
		this.trendingPanel.getPanel().add(this.trendingPanel.getAllTimeFavoritesScrollPane());
//		allTimeFavoritesScrollPane = new JScrollPane(allTimeFavoritesTable);
//		allTimeFavoritesScrollPane.setBounds(10, 139, 1144, 300);
//		panel.add(allTimeFavoritesScrollPane);
		
		this.trendingPanel.setNationalFavoritesScrollPane(new JScrollPane(this.trendingPanel.getNationalFavoritesTable()));
		this.trendingPanel.getNationalFavoritesScrollPane().setBounds(10, 483, 1144, 300);
		this.trendingPanel.getPanel().add(this.trendingPanel.getNationalFavoritesScrollPane());
//		nationalFavoritesScrollPane = new JScrollPane(nationalFavoritesTable);
//		nationalFavoritesScrollPane.setBounds(10, 483, 1144, 300);
//		panel.add(nationalFavoritesScrollPane);
	}

}
