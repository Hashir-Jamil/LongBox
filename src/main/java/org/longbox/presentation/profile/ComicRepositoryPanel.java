package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.UserSession;
import org.longbox.config.HibernateUtils;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.persistence.dao.ComicBookFavouritesListDaoImpl;
import org.longbox.presentation.tablemodels.ComicBookTableModel;

import java.awt.BorderLayout;
import java.awt.Font;

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
import javax.swing.JButton;
import java.awt.Color;
@Getter
@Setter
public class ComicRepositoryPanel extends JPanel {
	
	private static final String DEFAULT_FONT = "Calibri";
	public JPanel panel;
	private JLabel comicRepositoryTitle;
	private JLabel lblNewLabel_1;
	private JSeparator separator;
	private JButton refreshButton;
	private JComboBox<String> comboBox;
	public JScrollPane scrollPane;
	private String currentItem;
	private JTable comicBookTable;
	private JTextField textField;
	private JComboBox<String> typeSelection;
	private ComicBookTableModel comicBookTableModel;
	private TableRowSorter<TableModel> sorter;
	private ComicBookDaoImpl comicBookDaoImpl;
	private UserSession userSession;
	private JButton addToFavouritesButton;
	private ComicBookFavouritesListDaoImpl comicBookFavouritesListDaoImpl = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());

	public ComicRepositoryPanel() {
		initComicCollectionPage();
	}

	private void initComicCollectionPage() {

		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
	    panel.setLayout(null);
		
		comicRepositoryTitle = new JLabel("Comic Repository");
		comicRepositoryTitle.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 30));
		comicRepositoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicRepositoryTitle.setBounds(396, 11, 372, 43);
		panel.add(comicRepositoryTitle);

		separator = new JSeparator();
		separator.setBounds(10, 92, 1144, 14);
		panel.add(separator);

		add(panel, BorderLayout.CENTER);
		
		comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());

		comicBookTableModel = new ComicBookTableModel(comicBookDaoImpl.getAllComicBooks());

		comicBookTable = new JTable(comicBookTableModel);
		sorter = new TableRowSorter<TableModel>(comicBookTable.getModel());
		comicBookTable.setRowSorter(sorter);

		scrollPane = new JScrollPane(comicBookTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 110, 1144, 683);
		panel.add(scrollPane);
			
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
		
		JLabel lblNewLabel = new JLabel("Search Repo:");
		lblNewLabel.setBounds(10, 66, 120, 13);
		panel.add(lblNewLabel);
		
		refreshButton = new JButton("Refresh");
		refreshButton.setForeground(Color.BLACK);
		refreshButton.setBounds(1065, 62, 89, 23);
		panel.add(refreshButton);

		addToFavouritesButton = new JButton("Add to Favourites");
		addToFavouritesButton.setBounds(930, 62, 129, 23);
		addToFavouritesButton.setEnabled(false); // Initially inactive
		panel.add(addToFavouritesButton);
	}
}
