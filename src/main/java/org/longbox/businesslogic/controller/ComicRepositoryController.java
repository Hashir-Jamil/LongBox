package org.longbox.businesslogic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.service.ComicBookService;
import org.longbox.businesslogic.service.UserComicBookCollectionService;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.persistence.dao.ComicBookFavouritesListDaoImpl;
import org.longbox.persistence.dao.ComicBookFinishedListDaoImpl;
import org.longbox.persistence.dao.ComicBookReadingListDaoImpl;
import org.longbox.presentation.tablemodels.ComicBookTableModel;
import org.longbox.presentation.profile.ComicRepositoryPanel;
import org.longbox.presentation.profile.FavouritesPanel;

public class ComicRepositoryController implements ActionListener, MouseListener {
	
	private UserComicBookCollectionService userComicBookCollectionService = new UserComicBookCollectionService(
            new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory()),
            new ComicBookReadingListDaoImpl(HibernateUtils.getSessionFactory()),
            new ComicBookFinishedListDaoImpl(HibernateUtils.getSessionFactory()));
	private ComicBookService comicBookService = new ComicBookService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));
	private ComicRepositoryPanel comicRepositoryPanel;
	private UserSession userSession;
	
	public ComicRepositoryController(ComicRepositoryPanel comicRepositoryPanel) {
		this.comicRepositoryPanel = comicRepositoryPanel;
		this.userSession = comicRepositoryPanel.getUserSession();
		addListeners();
	}
	
	private void addListeners() {
		this.comicRepositoryPanel.getRefreshButton().addActionListener(this);
		this.comicRepositoryPanel.getComicBookTable().addMouseListener(this);
		this.comicRepositoryPanel.getTextField().addActionListener(this);
		this.comicRepositoryPanel.getAddToFavouritesButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.comicRepositoryPanel.getRefreshButton()) {
			this.comicRepositoryPanel.getPanel().remove(comicRepositoryPanel.getScrollPane());
			this.reloadTable();
		}
		
		if(e.getSource() == this.comicRepositoryPanel.getTextField() && !this.comicRepositoryPanel.getTextField().getText().isEmpty()) {
			String searchBy = this.comicRepositoryPanel.getTypeSelection().getSelectedItem().toString();
			String target = this.comicRepositoryPanel.getTextField().getText();
			List<ComicBookDto> searchResults = null;
			searchResults = org.longbox.businesslogic.utils.ComicBookSearchUtils.comicAdvancedSearch(searchBy, target, searchResults, comicBookService.getAllComicBook(), this.userSession);
		}
		
		if(e.getSource() == this.comicRepositoryPanel.getAddToFavouritesButton()) {
			int selectedRow = this.comicRepositoryPanel.getComicBookTable().getSelectedRow();
			if (selectedRow != -1) {
				int row = this.comicRepositoryPanel.getComicBookTable().getSelectedRow();
				int col = this.comicRepositoryPanel.getComicBookTable().getSelectedColumn();
				String name = this.comicRepositoryPanel.getComicBookTable().getValueAt(row, col).toString();
				long comicId = comicBookService.getComicBookBySeriesName(name).getId();
				// Check if the comic book is already in favourites list
				if (!isComicInFavourites(comicId)) {
                    try {
                        addComicToFavourites(comicId);
                    } catch (UserIDDoesNotExistException ex) {
                        throw new RuntimeException(ex);
                    }
                    // Disable the button after adding to favourites
                    this.comicRepositoryPanel.getAddToFavouritesButton().setEnabled(false);
				}
			}
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int selectedRow = this.comicRepositoryPanel.getComicBookTable().getSelectedRow();
		if (selectedRow != -1) {
			this.comicRepositoryPanel.getAddToFavouritesButton().setEnabled(true);
		}
		int row = this.comicRepositoryPanel.getComicBookTable().rowAtPoint(e.getPoint());
		int col = this.comicRepositoryPanel.getComicBookTable().columnAtPoint(e.getPoint());
		if (col == 0 && e.getClickCount() == 2) {
			ComicBookDto comicBook = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(comicBookService.getAllComicBook(), this.comicRepositoryPanel.getComicBookTable().getValueAt(row, col).toString());
			ComicBookSearchUtils.loadComicBookPage(comicBook, userSession);
		}
	}
	
	private boolean isComicInFavourites(long comicId) {
		ComicBookDto comicBookDTO = comicBookService.getComicBookById(comicId);
		//return this.comicRepositoryPanel.getComicBookFavouritesListDaoImpl().getAllFavouritesComicBooks().contains(comicBookDTO);
		return userComicBookCollectionService.getAllFavouritesComicBooks().contains(comicBookDTO);
	}

	private void addComicToFavourites(long comicId) throws UserIDDoesNotExistException {
		FavouritesPanel favouritesPanel = new FavouritesPanel();
		favouritesPanel.update(this.userSession.getUser().getId(), comicId);
		favouritesPanel.reloadData();
	}
	
	public void reloadTable() {
		this.comicRepositoryPanel.getPanel().remove(this.comicRepositoryPanel.getScrollPane());
		
		this.comicRepositoryPanel.setComicBookTableModel(new ComicBookTableModel(comicBookService.getAllComicBook()));

		this.comicRepositoryPanel.setComicBookTable(new JTable(this.comicRepositoryPanel.getComicBookTableModel()));

		this.comicRepositoryPanel.setSorter(new TableRowSorter<TableModel>(this.comicRepositoryPanel.getComicBookTable().getModel()));
		this.comicRepositoryPanel.getComicBookTable().setRowSorter(this.comicRepositoryPanel.getSorter());

		this.comicRepositoryPanel.setScrollPane(new JScrollPane(this.comicRepositoryPanel.getComicBookTable()));
		this.comicRepositoryPanel.getScrollPane().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.comicRepositoryPanel.getScrollPane().setViewportBorder(null);
		this.comicRepositoryPanel.getScrollPane().setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.comicRepositoryPanel.getScrollPane().setBounds(10, 110, 1144, 683);
		this.comicRepositoryPanel.getPanel().add(this.comicRepositoryPanel.getScrollPane());
		
		this.comicRepositoryPanel.getComicBookTable().addMouseListener(this);
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
