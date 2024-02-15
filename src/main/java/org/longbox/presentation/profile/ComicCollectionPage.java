package org.longbox.presentation.profile;

import org.longbox.persistence.stubdatabase.ComicBookStubDB;
import org.longbox.presentation.authentication.AuthenticationPage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ComicCollectionPage extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JLabel comicCollectionTitle;
	private JLabel lblNewLabel_1;
	private JSeparator separator;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private String currentItem;
	private JTable comicBookTable;
	private JTextField textField;
	private ComicBookTableModel comicBookTableModel;
	TableRowSorter<TableModel> sorter;
	
	
	ComicCollectionPage() {
		initComicCollectionPage();
	}

	
	
	private void initComicCollectionPage() {

		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
	    panel.setLayout(null);
		
		comicCollectionTitle = new JLabel("Comic Collection");
		comicCollectionTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(396, 11, 372, 43);
		panel.add(comicCollectionTitle);
		
		lblNewLabel_1 = new JLabel("Sort By:");
		lblNewLabel_1.setBounds(935, 67, 60, 14);
		panel.add(lblNewLabel_1);
		
		separator = new JSeparator();
		separator.setBounds(10, 92, 1144, 14);
		panel.add(separator);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(992, 62, 162, 22);
		panel.add(comboBox);
		
		comboBox.addItem("A-Z");
		comboBox.addItem("Z-A");
		comboBox.addItem("Date Added (Recent)");
		comboBox.addItem("Date Added (Oldest)");
		comboBox.addItem("Publication Date (Recent)");
		comboBox.addItem("Publication Date (Oldest)");
		
		comboBox.addActionListener(this);
		
		add(panel, BorderLayout.CENTER);

		ComicBookStubDB comicBookStubDB = new ComicBookStubDB();
		comicBookStubDB.loadComicBooks();
		
		comicBookTableModel = new ComicBookTableModel(comicBookStubDB.getComicBookStubData());
				
		comicBookTable = new JTable(comicBookTableModel);		
		sorter = new TableRowSorter<TableModel>(comicBookTable.getModel());
		comicBookTable.setRowSorter(sorter);

		scrollPane = new JScrollPane(comicBookTable);
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 110, 1144, 683);
		panel.add(scrollPane);

		System.out.println(comicBookStubDB.getComicBookStubData());

		//scrollPane.setViewportView(comicBookTable);
		
		textField = new JTextField();
		textField.setBounds(116, 62, 213, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Collection:");
		lblNewLabel.setBounds(10, 66, 120, 13);
		panel.add(lblNewLabel);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		
		String option = (String)comboBox.getSelectedItem();

        if(e.getSource() == comboBox) {
        	option = (String)comboBox.getSelectedItem();
        	switch (option) {
			case "A-Z":
				System.out.println("A-Z");
				break;
			case "Z-A":
				System.out.println("Z-A");
				break;
			case "Date Added (Recent)":
				System.out.println("Date Added (Recent)");
				break;
			case "Date Added (Oldest)":
				System.out.println("Date Added (Oldest)");
				break;
			case "Publication Date (Recent)":
				System.out.println("Publication Date (Recent)");
				break;
			case "Publication Date (Oldest)":
				System.out.println("Publication Date (Oldest)");
				break;
			default:
				System.out.println("A-Z");
				break;
		}
        }
    }
}
