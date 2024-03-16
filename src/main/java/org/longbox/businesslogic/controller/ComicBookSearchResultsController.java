package org.longbox.businesslogic.controller;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.service.UserService;
import org.longbox.presentation.comicbook.ComicBookSearchResultsFrame;

public class ComicBookSearchResultsController {
	
	ComicBookSearchResultsFrame comicBookSearchResultsFrame;
	private UserSession userSession;
    private UserService userService;

	public ComicBookSearchResultsController(ComicBookSearchResultsFrame comicBookSearchResultsFrame) {
		this.comicBookSearchResultsFrame = comicBookSearchResultsFrame;
		this.userSession = comicBookSearchResultsFrame.getUserSession();
	}

}
