package org.longbox.businesslogic.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.profile.RecommendationsPanel;

public class RecommendationsController implements MouseListener {
	
	private ComicBookDaoImpl comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());
	private RecommendationsPanel recommentationsPanel;
	private final String columnName = "Series Title";
	private UserSession userSession;

	public RecommendationsController(RecommendationsPanel recommentationsPanel) {
		this.recommentationsPanel = recommentationsPanel;
		this.userSession = recommentationsPanel.getUserSession();
		loadListeners();
	}
	
	private void loadListeners() {
		this.recommentationsPanel.getRecommendationsTable().addMouseListener(this);		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int headerCol = this.recommentationsPanel.getHeader().columnAtPoint(e.getPoint());
		String selection = this.recommentationsPanel.getRecommendationsTable().getColumnName(headerCol).toString();
		int row = this.recommentationsPanel.getRecommendationsTable().rowAtPoint(e.getPoint());
		int col = this.recommentationsPanel.getRecommendationsTable().columnAtPoint(e.getPoint());
		if (selection.equals(columnName) && e.getClickCount() == 2) {
			ComicBookDto comicBook = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(comicBookDaoImpl.getAllComicBooks(), this.recommentationsPanel.getRecommendationsTable().getValueAt(row, col).toString());
			ComicBookSearchUtils.loadComicBookPage(comicBook, userSession);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
