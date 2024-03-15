package org.longbox.businesslogic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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
import org.longbox.businesslogic.service.UserService;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.domainobjects.entity.ComicBook;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.profile.ComicBookTableModel;
import org.longbox.presentation.profile.ComicRepositoryPanel;
import org.longbox.presentation.profile.FavoritesPanel;

public class ComicRepositoryController implements ActionListener, MouseListener {
	
	private ComicRepositoryPanel comicRepositoryPanel;
	private UserSession userSession;
    private UserService userService;
	
	public ComicRepositoryController(ComicRepositoryPanel comicRepositoryPanel) {
		this.comicRepositoryPanel = comicRepositoryPanel;
		this.userSession = comicRepositoryPanel.getUserSession();
		addListeners();
	}
	
	private void addListeners() {
		this.comicRepositoryPanel.getRefreshButton().addActionListener(this);
		this.comicRepositoryPanel.getComicBookTable().addMouseListener(this);
		this.comicRepositoryPanel.getTextField().addActionListener(this);
		this.comicRepositoryPanel.getAddToFavoritesButton().addActionListener(this);
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
			searchResults = org.longbox.businesslogic.utils.ComicBookSearchUtils.comicAdvancedSearch(searchBy, target, searchResults, this.comicRepositoryPanel.getComicBookDaoImpl().getAllComicBooks(), this.userSession);
		}
		
		if(e.getSource() == this.comicRepositoryPanel.getAddToFavoritesButton()) {
			int selectedRow = this.comicRepositoryPanel.getComicBookTable().getSelectedRow();
			if (selectedRow != -1) {
				long comicId = this.comicRepositoryPanel.getComicBookTableModel().getComicIdAtRow(selectedRow);
				// Check if the comic book is already in favorites list
				if (!isComicInFavorites(comicId)) {
                    try {
                        addComicToFavorites(comicId);
                    } catch (UserIDDoesNotExistException ex) {
                        throw new RuntimeException(ex);
                    }
                    // Disable the button after adding to favorites
                    this.comicRepositoryPanel.getAddToFavoritesButton().setEnabled(false);
				}
			}
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int selectedRow = this.comicRepositoryPanel.getComicBookTable().getSelectedRow();
		if (selectedRow != -1) {
			this.comicRepositoryPanel.getAddToFavoritesButton().setEnabled(true);
		}
		int row = this.comicRepositoryPanel.getComicBookTable().rowAtPoint(e.getPoint());
		int col = this.comicRepositoryPanel.getComicBookTable().columnAtPoint(e.getPoint());
		if (col == 0 && e.getClickCount() == 2) {
			ComicBookDto comicBook = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(this.comicRepositoryPanel.getComicBookDaoImpl().getAllComicBooks(), this.comicRepositoryPanel.getComicBookTable().getValueAt(row, col).toString());
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
	
	
	
	public void reloadTable() {
		this.comicRepositoryPanel.getPanel().remove(this.comicRepositoryPanel.getScrollPane());
		
		this.comicRepositoryPanel.setComicBookDaoImpl(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));

		this.comicRepositoryPanel.setComicBookTableModel(new ComicBookTableModel(this.comicRepositoryPanel.getComicBookDaoImpl().getAllComicBooks()));

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
	
	private boolean isComicInFavorites(long comicId) {
		ComicBook comicBook = this.comicRepositoryPanel.getComicBookDaoImpl().getComicBookById(comicId);
		ComicBookDto comicBookDTO = new ComicBookDto(comicBook);
		System.out.println("the comic in favorites is " + this.comicRepositoryPanel.getComicBookFavouritesListDaoImpl().getAllFavoritesComicBooks().contains(comicBookDTO));
		return this.comicRepositoryPanel.getComicBookFavouritesListDaoImpl().getAllFavoritesComicBooks().contains(comicBookDTO);
	}

	private void addComicToFavorites(long comicId) throws UserIDDoesNotExistException {
		FavoritesPanel favoritesPanel = new FavoritesPanel();
		this.comicRepositoryPanel.getComicBookDaoImpl().favoriteComicBook(comicId);
		favoritesPanel.update(this.userSession.getUser().getId(), comicId);
		favoritesPanel.reloadData();
	}
}
