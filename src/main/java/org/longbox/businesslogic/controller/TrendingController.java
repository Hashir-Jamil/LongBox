package org.longbox.businesslogic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.longbox.businesslogic.service.ComicBookService;
import org.longbox.config.HibernateUtils;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.profile.TrendingPanel;
import org.longbox.presentation.tablemodels.TrendingAllTimeTableModel;
import org.longbox.presentation.tablemodels.TrendingRegionalTableModel;

public class TrendingController implements ActionListener {

	private TrendingPanel trendingPanel;
	private ComicBookService comicBookService = new ComicBookService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));
	
	public TrendingController(TrendingPanel trendingPanel) {
		this.trendingPanel = trendingPanel;
		addListeners();
	}
	
	private void addListeners() {
		this.trendingPanel.getRegionBox().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.trendingPanel.getRegionBox()) {
			reloadRegional(this.trendingPanel.getRegionBox().getSelectedItem().toString());
		}
	}
	
	public void reloadRegional(String region) {
		this.trendingPanel.getPanel().remove(this.trendingPanel.getRegionalFavouritesScrollPane());
				
		this.trendingPanel.setRegionalComicBookTableModel(new TrendingRegionalTableModel(comicBookService.getAllComicBook(), region));
		
		this.trendingPanel.setRegionalFavouritesTable(new JTable(this.trendingPanel.getRegionalComicBookTableModel()));
		this.trendingPanel.getRegionalFavouritesTable().setBounds(0, 0, 1, 1);
		this.trendingPanel.getPanel().add(this.trendingPanel.getRegionalFavouritesTable());
		
		this.trendingPanel.setRegionalFavouritesScrollPane(new JScrollPane(this.trendingPanel.getRegionalFavouritesTable()));
		this.trendingPanel.getRegionalFavouritesScrollPane().setBounds(10, 483, 1144, 300);
		this.trendingPanel.getPanel().add(this.trendingPanel.getRegionalFavouritesScrollPane());
	}
	
	public void reloadTrending() {
		this.trendingPanel.getPanel().remove(this.trendingPanel.getAllTimeFavouritesScrollPane());
		this.trendingPanel.getPanel().remove(this.trendingPanel.getRegionalFavouritesScrollPane());
				
		this.trendingPanel.setComicBookTableModel(new TrendingAllTimeTableModel(comicBookService.getAllComicBook()));
		this.trendingPanel.setRegionalComicBookTableModel(new TrendingRegionalTableModel(comicBookService.getAllComicBook(), "North America"));
		
		this.trendingPanel.setAllTimeFavouritesTable(new JTable(this.trendingPanel.getComicBookTableModel()));
		this.trendingPanel.getAllTimeFavouritesTable().setBounds(0, 0, 1, 1);
		this.trendingPanel.getPanel().add(this.trendingPanel.getAllTimeFavouritesTable());
		
		this.trendingPanel.setRegionalFavouritesTable(new JTable(this.trendingPanel.getRegionalComicBookTableModel()));
		this.trendingPanel.getRegionalFavouritesTable().setBounds(0, 0, 1, 1);
		this.trendingPanel.getPanel().add(this.trendingPanel.getRegionalFavouritesTable());
		
		this.trendingPanel.setAllTimeFavouritesScrollPane(new JScrollPane(this.trendingPanel.getAllTimeFavouritesTable()));
		this.trendingPanel.getAllTimeFavouritesScrollPane().setBounds(10, 139, 1144, 300);
		this.trendingPanel.getPanel().add(this.trendingPanel.getAllTimeFavouritesScrollPane());
		
		this.trendingPanel.setRegionalFavouritesScrollPane(new JScrollPane(this.trendingPanel.getRegionalFavouritesTable()));
		this.trendingPanel.getRegionalFavouritesScrollPane().setBounds(10, 483, 1144, 300);
		this.trendingPanel.getPanel().add(this.trendingPanel.getRegionalFavouritesScrollPane());
	}
}
