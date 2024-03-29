package org.longbox.businesslogic.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.presentation.comicbook.ComicBookSearchResultsFrame;

public class ComicBookSearchResultsController implements MouseListener {
	
	private ComicBookSearchResultsFrame comicBookSearchResultsFrame;
	private final String columnName = "Series Title";
	private UserSession userSession;

	public ComicBookSearchResultsController(ComicBookSearchResultsFrame comicBookSearchResultsFrame) {
		this.comicBookSearchResultsFrame = comicBookSearchResultsFrame;
		this.userSession = comicBookSearchResultsFrame.getUserSession();
		addListeners();
	}
	
	public void addListeners() {
		this.comicBookSearchResultsFrame.getComicBookTable().addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int headerCol = this.comicBookSearchResultsFrame.getHeader().columnAtPoint(e.getPoint());
		String selection = this.comicBookSearchResultsFrame.getComicBookTable().getColumnName(headerCol).toString();
		int row = this.comicBookSearchResultsFrame.getComicBookTable().rowAtPoint(e.getPoint());
		int col = this.comicBookSearchResultsFrame.getComicBookTable().columnAtPoint(e.getPoint());
		if (selection.equals(columnName) && e.getClickCount() == 2) {
			ComicBookDto comicBook = ComicBookSearchUtils.searchComicBook(this.comicBookSearchResultsFrame.getAllComicBooks(), this.comicBookSearchResultsFrame.getComicBookTable().getValueAt(row, col).toString());
			System.out.println("Clicked on: " + this.comicBookSearchResultsFrame.getComicBookTable().getValueAt(row, col).toString());
			org.longbox.businesslogic.utils.ComicBookSearchUtils.loadComicBookPage(comicBook, userSession);
			this.comicBookSearchResultsFrame.dispose();
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
