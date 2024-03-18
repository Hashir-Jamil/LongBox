package org.longbox.businesslogic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.longbox.config.HibernateUtils;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.profile.TrendingPanel;
import org.longbox.presentation.tablemodels.TrendingAllTimeTableModel;

public class TrendingController implements ActionListener {

	private TrendingPanel trendingPanel;
	
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
			System.out.println(this.trendingPanel.getRegionBox().getSelectedItem().toString());
		}
	}
	
	public void reloadRegionalTrending(String region) {
		this.trendingPanel.getPanel().remove(this.trendingPanel.getRegionalFavoritesScrollPane());
	}
	
	public void reloadTrending() {
		this.trendingPanel.getPanel().remove(this.trendingPanel.getAllTimeFavoritesScrollPane());
		this.trendingPanel.getPanel().remove(this.trendingPanel.getRegionalFavoritesScrollPane());
		
		this.trendingPanel.setComicBookDaoImpl(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));
		
		this.trendingPanel.setComicBookTableModel(new TrendingAllTimeTableModel(this.trendingPanel.getComicBookDaoImpl().getAllComicBooks()));
		
		this.trendingPanel.setRegionalComicBookTableModel(new TrendingAllTimeTableModel(this.trendingPanel.getComicBookDaoImpl().getAllComicBooks())); // regional
		
		this.trendingPanel.setAllTimeFavoritesTable(new JTable(this.trendingPanel.getComicBookTableModel()));
		this.trendingPanel.getAllTimeFavoritesTable().setBounds(0, 0, 1, 1);
		this.trendingPanel.getPanel().add(this.trendingPanel.getAllTimeFavoritesTable());
		
		this.trendingPanel.setRegionalFavoritesTable(new JTable(this.trendingPanel.getComicBookTableModel()));
		this.trendingPanel.getRegionalFavoritesTable().setBounds(0, 0, 1, 1);
		this.trendingPanel.getPanel().add(this.trendingPanel.getRegionalFavoritesTable());
		
		this.trendingPanel.setAllTimeFavoritesScrollPane(new JScrollPane(this.trendingPanel.getAllTimeFavoritesTable()));
		this.trendingPanel.getAllTimeFavoritesScrollPane().setBounds(10, 139, 1144, 300);
		this.trendingPanel.getPanel().add(this.trendingPanel.getAllTimeFavoritesScrollPane());
		
		this.trendingPanel.setRegionalFavoritesScrollPane(new JScrollPane(this.trendingPanel.getRegionalFavoritesTable()));
		this.trendingPanel.getRegionalFavoritesScrollPane().setBounds(10, 483, 1144, 300);
		this.trendingPanel.getPanel().add(this.trendingPanel.getRegionalFavoritesScrollPane());
	}
}
