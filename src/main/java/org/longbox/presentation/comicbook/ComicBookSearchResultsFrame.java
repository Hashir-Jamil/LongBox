package org.longbox.presentation.comicbook;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.utils.ComicBookSearchUtils;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.tablemodels.ComicBookTableModel;

import lombok.Getter;
import lombok.Setter;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ComicBookSearchResultsFrame extends JFrame {
	private static final String DEFAULT_FONT = "Calibri";
	private JPanel contentPane;
	private JTable comicBookTable;
	private ComicBookDaoImpl comicBookDaoImpl;
	private ComicBookTableModel comicBookTableModel;
	private UserSession userSession;

	public ComicBookSearchResultsFrame(List<ComicBookDto> displayResults, String target, String searchBy, UserSession user) {
		this.userSession = user;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Results for " + target + " in " + searchBy);
		lblNewLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 814, 46);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 814, 342);
		contentPane.add(scrollPane);
		
		comicBookTable = new JTable();
		
		comicBookDaoImpl = new ComicBookDaoImpl(HibernateUtils.getSessionFactory());

		comicBookTableModel = new ComicBookTableModel(displayResults);

		comicBookTable = new JTable(comicBookTableModel);
		
//		comicBookTable.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int row = comicBookTable.rowAtPoint(e.getPoint());
//				int col = comicBookTable.columnAtPoint(e.getPoint());
//				if (col == 0) {
//					ComicBookDto comicBook = ComicBookSearchUtils.searchComicBook(comicBookDaoImpl.getAllComicBooks(), comicBookTable.getValueAt(row, col).toString());
//					System.out.println("Clicked on: " + comicBookTable.getValueAt(row, col).toString());
//					org.longbox.businesslogic.utils.ComicBookSearchUtils.loadComicBookPage(comicBook, userSession);
//					dispose();
//				}
//			}
//		});
		scrollPane.setViewportView(comicBookTable);
	}
	
	public UserSession getUserSession() {
		return this.userSession;
	}
}
