package org.longbox.businesslogic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.service.ComicBookService;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.profile.TrendingPanel;
import org.longbox.presentation.tablemodels.TrendingAllTimeTableModel;
import org.longbox.presentation.tablemodels.TrendingRegionalTableModel;

public class TrendingController implements ActionListener, MouseListener {

	private TrendingPanel trendingPanel;
	private ComicBookService comicBookService = new ComicBookService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));
	private final String columnName = "Series Title";
	private UserSession userSession;
	
	public TrendingController(TrendingPanel trendingPanel) {
		this.trendingPanel = trendingPanel;
		this.userSession = trendingPanel.getUserSession();
		addListeners();
	}
	
	private void addListeners() {
		this.trendingPanel.getRegionBox().addActionListener(this);
		this.trendingPanel.getAllTimeFavouritesTable().addMouseListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.trendingPanel.getRegionBox()) {
			reloadRegional(this.trendingPanel.getRegionBox().getSelectedItem().toString());
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int headerCol = this.trendingPanel.getHeader().columnAtPoint(e.getPoint());
		String selection = this.trendingPanel.getAllTimeFavouritesTable().getColumnName(headerCol).toString();
		int row = this.trendingPanel.getAllTimeFavouritesTable().rowAtPoint(e.getPoint());
		int col = this.trendingPanel.getAllTimeFavouritesTable().columnAtPoint(e.getPoint());
		if (selection.equals(columnName) && e.getClickCount() == 2) {
			ComicBookDto comicBook = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(comicBookService.getAllComicBook(), this.trendingPanel.getAllTimeFavouritesTable().getValueAt(row, col).toString());
			ComicBookSearchUtils.loadComicBookPage(comicBook, userSession);
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
		
		this.trendingPanel.getRegionalFavouritesTable().addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				regionalLogic(e);
			}
		});
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
		
		this.trendingPanel.getAllTimeFavouritesTable().addMouseListener(this);
		this.trendingPanel.getRegionalFavouritesTable().addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				regionalLogic(e);
			}
		});
	}
	
	private void regionalLogic(MouseEvent e) {
		int headerCol = trendingPanel.getHeader().columnAtPoint(e.getPoint());
		String selection = trendingPanel.getRegionalFavouritesTable().getColumnName(headerCol).toString();
		int row = trendingPanel.getRegionalFavouritesTable().rowAtPoint(e.getPoint());
		int col = trendingPanel.getRegionalFavouritesTable().columnAtPoint(e.getPoint());
		if (selection.equals(columnName) && e.getClickCount() == 2) {
			ComicBookDto comicBook = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(comicBookService.getAllComicBook(), trendingPanel.getRegionalFavouritesTable().getValueAt(row, col).toString());
			ComicBookSearchUtils.loadComicBookPage(comicBook, userSession);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// No need to implement
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// No need to implement
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// No need to implement
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// No need to implement
	}
}
