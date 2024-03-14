package org.longbox.businesslogic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.service.UserService;
import org.longbox.presentation.profile.ComicRepositoryPanel;

public class ComicRepositoryController implements ActionListener {
	
	private ComicRepositoryPanel comicRepositoryPanel;
	private UserSession userSession;
    private UserService userService;
	
	public ComicRepositoryController(ComicRepositoryPanel comicRepositoryPanel) {
		this.comicRepositoryPanel = comicRepositoryPanel;
		this.userSession = comicRepositoryPanel.getUserSession();
	}
	
	private void addListeners() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
