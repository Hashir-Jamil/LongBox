package org.longbox.presentation.profile;

import org.longbox.businesslogic.utils.ComicBookSearch;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.dao.ComicBookDaoImpl;
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

public class FavoritesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
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
	private ComicBookDaoImpl comicBookDaoImpl;

	public FavoritesPanel() {
		initComicCollectionPage();
	}

	public void update(ComicBookDTO comicBook) {
		favoriteComicBooks.add(comicBook);
		comicBookTableModel.addRow(new Object[]{
				comicBook.getSeriesTitle(),
				comicBook.getAuthor(),
				comicBook.getArtist(),
				comicBook.getGenres(),
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
		comicCollectionTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(396, 11, 372, 43);
		panel.add(comicCollectionTitle);

		separator = new JSeparator();
		separator.setBounds(10, 92, 1144, 14);
		panel.add(separator);

		add(panel, BorderLayout.CENTER);

		comicBookDaoImpl = new ComicBookDaoImpl();

		if (favoriteComicBooks != null) {

			comicBookTableModel = new ComicBookTableModel(favoriteComicBooks);

			comicBookTable = new JTable(comicBookTableModel);
			comicBookTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int row = comicBookTable.rowAtPoint(e.getPoint());
					int col = comicBookTable.columnAtPoint(e.getPoint());
					if (col == 0) {
						ComicBookDTO comicBook = ComicBookSearch.searchComicBook(comicBookDaoImpl.getAllComicBooks(), comicBookTable.getValueAt(row, col).toString());
						System.out.println("Clicked on: " + comicBookTable.getValueAt(row, col).toString());
						ComicBookSearch.loadComicBookPage(comicBook);
					}
				}
			});
			sorter = new TableRowSorter<TableModel>(comicBookTable.getModel());
			comicBookTable.setRowSorter(sorter);

			scrollPane = new JScrollPane(comicBookTable);
			scrollPane.setViewportBorder(null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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

			JLabel lblNewLabel = new JLabel("Search Collection:");
			lblNewLabel.setBounds(10, 66, 120, 13);
			panel.add(lblNewLabel);

			unfavoriteButton = new JButton("Unfavorite");
			unfavoriteButton.setEnabled(false); // Initially disabled
			unfavoriteButton.setBounds(1000, 60, 150, 25); // Adjust the position as needed
			panel.add(unfavoriteButton);

			comicBookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			comicBookTable.getSelectionModel().addListSelectionListener(e -> {
				boolean isRowSelected = comicBookTable.getSelectedRow() != -1;
				unfavoriteButton.setEnabled(isRowSelected);
			});
		}
	}
	public static FavoritesPanel getInstance() {
		return instance;
	}

	public static void setInstance(FavoritesPanel instance) {
		FavoritesPanel.instance = instance;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getComicCollectionTitle() {
		return comicCollectionTitle;
	}

	public void setComicCollectionTitle(JLabel comicCollectionTitle) {
		this.comicCollectionTitle = comicCollectionTitle;
	}

	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}

	public void setLblNewLabel_1(JLabel lblNewLabel_1) {
		this.lblNewLabel_1 = lblNewLabel_1;
	}

	public JSeparator getSeparator() {
		return separator;
	}

	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public String getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(String currentItem) {
		this.currentItem = currentItem;
	}

	public JTable getComicBookTable() {
		return comicBookTable;
	}

	public void setComicBookTable(JTable comicBookTable) {
		this.comicBookTable = comicBookTable;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JComboBox<String> getTypeSelection() {
		return typeSelection;
	}

	public void setTypeSelection(JComboBox<String> typeSelection) {
		this.typeSelection = typeSelection;
	}

	public List<ComicBookDTO> getFavoriteComicBooks() {
		return favoriteComicBooks;
	}

	public void setFavoriteComicBooks(List<ComicBookDTO> favoriteComicBooks) {
		this.favoriteComicBooks = favoriteComicBooks;
	}

	public ComicBookTableModel getComicBookTableModel() {
		return comicBookTableModel;
	}

	public void setComicBookTableModel(ComicBookTableModel comicBookTableModel) {
		this.comicBookTableModel = comicBookTableModel;
	}

	public JButton getUnfavoriteButton() {
		return unfavoriteButton;
	}

	public void setUnfavoriteButton(JButton unfavoriteButton) {
		this.unfavoriteButton = unfavoriteButton;
	}

	public TableRowSorter<TableModel> getSorter() {
		return sorter;
	}

	public void setSorter(TableRowSorter<TableModel> sorter) {
		this.sorter = sorter;
	}

	public ComicBookDaoImpl getComicBookDaoImpl() {
		return comicBookDaoImpl;
	}

	public void setComicBookDaoImpl(ComicBookDaoImpl comicBookDaoImpl) {
		this.comicBookDaoImpl = comicBookDaoImpl;
	}
}
