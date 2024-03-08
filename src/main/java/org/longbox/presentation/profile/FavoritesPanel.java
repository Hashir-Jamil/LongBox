package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.utils.ComicBookSearch;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.persistence.dao.ComicBookFavouritesListDaoImpl;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.presentation.comicbook.ComicBookSearchResultsFrame;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;
@Getter
@Setter
public class FavoritesPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_FONT = "Calibri";
	private static FavoritesPanel instance;
	private JPanel panel;
	private JLabel comicCollectionTitle;
	private JLabel lblNewLabel_1;
	private JSeparator separator;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private String currentItem;
	private JTable comicBookTable;
	private JTextField textField;
	private JComboBox<String> typeSelection;
	private List<ComicBookDTO> favoriteComicBooks = new ArrayList<>();
	private ComicBookTableModel comicBookTableModel;
	private JButton unfavoriteButton;
	private TableRowSorter<TableModel> sorter;
	private ComicBookFavouritesListDaoImpl comicBookFavouritesListDaoImp;
	private UserSession userSession;

	public FavoritesPanel() {
		initComicCollectionPage();
	}

	public void update(ComicBookDTO comicBook, long userId, long comicBookId) throws UserIDDoesNotExistException {
		ComicBookFavouritesListDaoImpl comicBookFavouritesListDaoImpl = new ComicBookFavouritesListDaoImpl();
		comicBookFavouritesListDaoImpl.saveToFavorites(userId, comicBookId);
		comicBookTableModel.addRow(new Object[]{
				comicBook.getSeriesTitle(),
				comicBook.getAuthor(),
				comicBook.getArtist(),
				ComicBookDTO.genreListToString(comicBook.getGenres()),
				comicBook.getNumberOfIssues(),
				comicBook.getPublisher(),
				comicBook.getYearPublished()
		});
	}

	private void initComicCollectionPage() {

		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());

		panel = new JPanel();
		panel.setLayout(null);

		comicCollectionTitle = new JLabel("User Favorites");
		comicCollectionTitle.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(396, 11, 372, 43);
		panel.add(comicCollectionTitle);

		separator = new JSeparator();
		separator.setBounds(10, 92, 1144, 14);
		panel.add(separator);

		add(panel, BorderLayout.CENTER);

		comicBookFavouritesListDaoImp = new ComicBookFavouritesListDaoImpl();

			comicBookTableModel = new ComicBookTableModel(comicBookFavouritesListDaoImp.getAllFavoritesComicBooks());

			comicBookTable = new JTable(comicBookTableModel);
			comicBookTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int row = comicBookTable.rowAtPoint(e.getPoint());
					int col = comicBookTable.columnAtPoint(e.getPoint());
					if (col == 0 && e.getClickCount() == 2) {
						ComicBookDTO comicBook = ComicBookSearch.searchComicBook(comicBookFavouritesListDaoImp.getAllFavoritesComicBooks(), comicBookTable.getValueAt(row, col).toString());
						System.out.println("Clicked on: " + comicBookTable.getValueAt(row, col).toString());
						ComicBookSearch.loadComicBookPage(comicBook, userSession);
					}
				}
			});
			sorter = new TableRowSorter<TableModel>(comicBookTable.getModel());
			comicBookTable.setRowSorter(sorter);

			scrollPane = new JScrollPane(comicBookTable);
			scrollPane.setViewportBorder(null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(10, 110, 1144, 683);
			panel.add(scrollPane);

			typeSelection = new JComboBox<String>();
			typeSelection.setBounds(333, 62, 160, 22);

			typeSelection.addItem("Title");
			typeSelection.addItem("Author");
			typeSelection.addItem("Artist");
			typeSelection.addItem("Genre");
			typeSelection.addItem("Publisher");
			typeSelection.addItem("Year");

			panel.add(typeSelection);

			textField = new JTextField();
			textField.setBounds(116, 62, 213, 22);
			panel.add(textField);
			textField.setColumns(10);

			textField.addActionListener(this);

			JLabel lblNewLabel = new JLabel("Search Favorites:");
			lblNewLabel.setBounds(10, 66, 120, 13);
			panel.add(lblNewLabel);

			unfavoriteButton = new JButton("Unfavorite");
			unfavoriteButton.addActionListener(this);
			unfavoriteButton.setEnabled(false); // Initially disabled
			unfavoriteButton.setBounds(1000, 60, 150, 25); // Adjust the position as needed
			panel.add(unfavoriteButton);

			comicBookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			comicBookTable.getSelectionModel().addListSelectionListener(e -> {
				boolean isRowSelected = comicBookTable.getSelectedRow() != -1;
				unfavoriteButton.setEnabled(isRowSelected);
			});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == unfavoriteButton) {
			int selectedRow = comicBookTable.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Please select a comic to unfavorite.", "No Comic Selected", JOptionPane.WARNING_MESSAGE);
			} else {
				int confirmUnfavorite = JOptionPane.showConfirmDialog(this, "Are you sure you want to unfavorite this comic?", "Unfavorite Confirmation", JOptionPane.YES_NO_OPTION);
				if (confirmUnfavorite == JOptionPane.YES_OPTION) {
					ComicBookDTO removedComic = favoriteComicBooks.remove(selectedRow);
					comicBookTableModel.removeRow(selectedRow);
					JOptionPane.showMessageDialog(this, "Comic '" + removedComic.getSeriesTitle() + "' has been unfavorited.", "Unfavorited", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		if (e.getSource() == textField && !textField.getText().isEmpty()) {
			String searchBy = typeSelection.getSelectedItem().toString();
			String target = textField.getText();
			List<ComicBookDTO> searchResults = null;
			searchResults = ComicBookSearch.comicAdvancedSearch(searchBy, target, searchResults, comicBookFavouritesListDaoImp.getAllFavoritesComicBooks(), this.userSession);
		}
	}
}
