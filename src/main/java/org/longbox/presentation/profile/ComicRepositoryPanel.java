package org.longbox.presentation.profile;

import org.longbox.businesslogic.utils.ComicBookSearch;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.comicbook.ComicBookSearchResultsFrame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import java.awt.Color;

public class ComicRepositoryPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel comicRepositoryTitle;
	private JLabel lblNewLabel_1;
	private JSeparator separator;
	private JButton refreshButton;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private String currentItem;
	private JTable comicBookTable;
	private JTextField textField;
	private JComboBox<String> typeSelection;
	private ComicBookTableModel comicBookTableModel;
	TableRowSorter<TableModel> sorter;	
	ComicBookDaoImpl comicBookDaoImpl;

	public ComicRepositoryPanel() {
		initComicCollectionPage();
	}

	private void initComicCollectionPage() {

		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
	    panel.setLayout(null);
		
		comicRepositoryTitle = new JLabel("Comic Repository");
		comicRepositoryTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicRepositoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicRepositoryTitle.setBounds(396, 11, 372, 43);
		panel.add(comicRepositoryTitle);

		separator = new JSeparator();
		separator.setBounds(10, 92, 1144, 14);
		panel.add(separator);

		add(panel, BorderLayout.CENTER);
		
		this.reloadTable();
			
		typeSelection = new JComboBox<String>();
		typeSelection.setBounds(307, 62, 160, 22);
		
		typeSelection.addItem("Title");
		typeSelection.addItem("Author");
		typeSelection.addItem("Artist");
		typeSelection.addItem("Genre");
		typeSelection.addItem("Publisher");
		typeSelection.addItem("Year");
		
		panel.add(typeSelection);
		
		textField = new JTextField();
		textField.setBounds(90, 62, 213, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		textField.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Search Repo:");
		lblNewLabel.setBounds(10, 66, 120, 13);
		panel.add(lblNewLabel);
		
		refreshButton = new JButton("Refresh");
		refreshButton.setForeground(Color.BLUE);
		refreshButton.setBounds(1065, 62, 89, 23);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.remove(scrollPane);
				reloadTable();
			}
			
		});
		panel.add(refreshButton);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == textField && !textField.getText().isEmpty()) {
			String searchBy = typeSelection.getSelectedItem().toString();
			String target = textField.getText();
			List<ComicBookDTO> searchResults = null;
			System.out.println("Search for: " + textField.getText() + " in "  + searchBy);
			switch(searchBy) {
				case "Title":
					searchResults = ComicBookSearch.searchComicBookByTitle(comicBookDaoImpl.getAllComicBooks(), textField.getText());
					break;
				case "Author":
					searchResults = ComicBookSearch.searchComicBookByAuthor(comicBookDaoImpl.getAllComicBooks(), textField.getText());
					break;
				case "Artist":
					searchResults = ComicBookSearch.searchComicBookByArtist(comicBookDaoImpl.getAllComicBooks(), textField.getText());
					break;
				case "Genre":
					searchResults = ComicBookSearch.searchComicBookByGenre(comicBookDaoImpl.getAllComicBooks(), textField.getText());
					break;
				case "Publisher":
					searchResults = ComicBookSearch.searchComicBookByPublisher(comicBookDaoImpl.getAllComicBooks(), textField.getText());
					break;
				case "Year":
					searchResults = ComicBookSearch.searchComicBookByYear(comicBookDaoImpl.getAllComicBooks(), textField.getText());
					break;
				default:
					searchResults = ComicBookSearch.searchComicBookByPublisher(comicBookDaoImpl.getAllComicBooks(), "");
					break;
			}
			loadComicBookResultsPage(searchResults, target, searchBy);
		}
    }
	
	private void loadComicBookResultsPage(List<ComicBookDTO> displayResults, String target, String searchBy) {
		ComicBookSearchResultsFrame resultsPage = new ComicBookSearchResultsFrame(displayResults, target, searchBy);
		resultsPage.setVisible(true);
	}
	
	public void reloadTable() {
		comicBookDaoImpl = new ComicBookDaoImpl();
		
		comicBookTableModel = new ComicBookTableModel(comicBookDaoImpl.getAllComicBooks());

		comicBookTable = new JTable(comicBookTableModel);
		comicBookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = comicBookTable.rowAtPoint(e.getPoint());
				int col = comicBookTable.columnAtPoint(e.getPoint());
				if (col == 0) {
					ComicBookDTO comicBook = ComicBookSearch.searchComicBook(comicBookDaoImpl.getAllComicBooks(), comicBookTable.getValueAt(row, col).toString());
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
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getComicRepositoryTitle() {
		return comicRepositoryTitle;
	}

	public void setComicRepositoryTitle(JLabel comicRepositoryTitle) {
		this.comicRepositoryTitle = comicRepositoryTitle;
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

	public ComicBookTableModel getComicBookTableModel() {
		return comicBookTableModel;
	}

	public void setComicBookTableModel(ComicBookTableModel comicBookTableModel) {
		this.comicBookTableModel = comicBookTableModel;
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
