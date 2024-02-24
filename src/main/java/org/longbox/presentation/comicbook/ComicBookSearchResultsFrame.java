package org.longbox.presentation.comicbook;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.longbox.businesslogic.utils.ComicBookSearch;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.persistence.stubdatabase.ComicBookStubDB;
import org.longbox.presentation.profile.ComicBookTableModel;

public class ComicBookSearchResultsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable comicBookTable;
	ComicBookDaoImpl comicBookDaoImpl;
	private ComicBookTableModel comicBookTableModel;

	/**
	 * Create the frame.
	 */
	public ComicBookSearchResultsFrame(List<ComicBookDTO> displayResults, String target, String searchBy) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Results for " + target + " in " + searchBy);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 814, 46);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 814, 342);
		contentPane.add(scrollPane);
		
		comicBookTable = new JTable();
		
		comicBookDaoImpl = new ComicBookDaoImpl();

		comicBookTableModel = new ComicBookTableModel(displayResults);

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
					dispose();
				}
			}
		});
		
		scrollPane.setViewportView(comicBookTable);
	}
}