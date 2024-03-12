package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.persistence.dao.ComicBookFavouritesListDaoImpl;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;
@Getter
@Setter
public class FavoritesPanel extends JPanel {
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
	private ComicBookTableModel comicBookTableModel;
	private JButton unfavoriteButton;
	private TableRowSorter<TableModel> sorter;
	private ComicBookFavouritesListDaoImpl comicBookFavouritesListDaoImp;
	private UserSession userSession;
	private JButton refreshButton;
	private JLabel lblNewLabel;

	public FavoritesPanel() {
		initComicCollectionPage();
		userSession = UserSession.getActiveUser();
	}

	public void update(long userId, long comicBookId) throws UserIDDoesNotExistException {
		comicBookFavouritesListDaoImp = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());
		comicBookFavouritesListDaoImp.saveToFavorites(userId, comicBookId);
		List<ComicBookDto> updatedFavoriteComicBooks = comicBookFavouritesListDaoImp.getAllFavoritesComicBooks();
		comicBookTableModel.updateData(updatedFavoriteComicBooks);
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

		comicBookFavouritesListDaoImp = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());

			comicBookTableModel = new ComicBookTableModel(comicBookFavouritesListDaoImp.getAllFavoritesComicBooks());

			comicBookTable = new JTable(comicBookTableModel);
			comicBookTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int row = comicBookTable.rowAtPoint(e.getPoint());
					int col = comicBookTable.columnAtPoint(e.getPoint());
					if (col == 0 && e.getClickCount() == 2) {
						ComicBookDto comicBook = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(comicBookFavouritesListDaoImp.getAllFavoritesComicBooks(), comicBookTable.getValueAt(row, col).toString());
						System.out.println("Clicked on: " + comicBookTable.getValueAt(row, col).toString());
						org.longbox.businesslogic.utils.ComicBookSearchUtils.loadComicBookPage(comicBook, userSession);
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

//			textField.addActionListener(this);

			lblNewLabel = new JLabel("Search Favorites:");
			lblNewLabel.setBounds(10, 66, 120, 13);
			panel.add(lblNewLabel);

			unfavoriteButton = new JButton("Unfavorite");
//			unfavoriteButton.addActionListener(this);
			unfavoriteButton.setEnabled(false); // Initially disabled
			unfavoriteButton.setBounds(930, 62, 129, 23);
			panel.add(unfavoriteButton);

			refreshButton = new JButton("Refresh");
//			refreshButton.addActionListener(e -> {
//				reloadData();
//			});

			refreshButton.setBounds(1065, 62, 89, 23);
			panel.add(refreshButton);

			comicBookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			comicBookTable.getSelectionModel().addListSelectionListener(e -> {
				boolean isRowSelected = comicBookTable.getSelectedRow() != -1;
				unfavoriteButton.setEnabled(isRowSelected);
			});
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == unfavoriteButton) {
//			int selectedRow = comicBookTable.getSelectedRow();
//			if (selectedRow == -1) {
//				JOptionPane.showMessageDialog(this, "Please select a comic to unfavorite.", "No Comic Selected", JOptionPane.WARNING_MESSAGE);
//			} else {
//				int confirmUnfavorite = JOptionPane.showConfirmDialog(this, "Are you sure you want to unfavorite this comic?", "Unfavorite Confirmation", JOptionPane.YES_NO_OPTION);
//				if (confirmUnfavorite == JOptionPane.YES_OPTION) {
//					ComicBookDaoImpl comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());
//					long userId = userSession.getUser().getId();
//					long comicId = comicBookTableModel.getComicIdAtRow(selectedRow);
//					comicBookFavouritesListDaoImp.removeFromFavorites(userId, comicId);
//					comicBookDaoImpl.unfavoriteComicBook(comicId);
//					comicBookTableModel.removeRow(selectedRow);
//					JOptionPane.showMessageDialog(this, "Comic has been unfavorited.", "Unfavorited", JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		}
//		if (e.getSource() == textField && !textField.getText().isEmpty()) {
//			String searchBy = typeSelection.getSelectedItem().toString();
//			String target = textField.getText();
//			List<ComicBookDto> searchResults = null;
//			ComicBookSearchUtils.comicAdvancedSearch(searchBy, target, searchResults, comicBookFavouritesListDaoImp.getAllFavoritesComicBooks(), this.userSession);
//		}
//	}
	public void reloadData() {
		comicBookTableModel.updateData(comicBookFavouritesListDaoImp.getAllFavoritesComicBooks());
	}
}
