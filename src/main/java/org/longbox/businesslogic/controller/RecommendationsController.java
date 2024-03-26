package org.longbox.businesslogic.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.service.UserService;
import org.longbox.config.HibernateUtils;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.profile.RecommendationsPanel;

public class RecommendationsController implements MouseListener {
	
	private ComicBookDaoImpl comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());
	private RecommendationsPanel recommentationsPanel;
	private UserSession userSession;
    private UserService userService;

	public RecommendationsController(RecommendationsPanel recommentationsPanel) {
		this.recommentationsPanel = recommentationsPanel;
		this.userSession = recommentationsPanel.getUserSession();
		loadListeners();
	}
	
	private void loadListeners() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
