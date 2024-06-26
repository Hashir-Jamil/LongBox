package org.longbox.presentation.comicbook;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.service.ComicBookService;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.presentation.tablemodels.ComicBookTableModel;

import java.awt.Font;
import java.util.List;

public class ComicBookSearchResultsFrame extends JFrame {
	
	private static final String DEFAULT_FONT = "Calibri";
	private JPanel contentPane;
	private JTable comicBookTable;
	private ComicBookTableModel comicBookTableModel;
	private UserSession userSession;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTableHeader header;
	private ComicBookService comicBookService = new ComicBookService(new ComicBookDaoImpl(HibernateUtils.getSessionFactory()));


	public ComicBookSearchResultsFrame(List<ComicBookDto> displayResults, String target, String searchBy, UserSession user) {
		this.userSession = user;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Results for " + target + " in " + searchBy);
		lblNewLabel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 814, 46);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 814, 342);
		contentPane.add(scrollPane);
		
		comicBookTable = new JTable();
		
		comicBookTableModel = new ComicBookTableModel(displayResults);

		comicBookTable = new JTable(comicBookTableModel);
		
		scrollPane.setViewportView(comicBookTable);
		
		header = comicBookTable.getTableHeader();
		header.setReorderingAllowed(false);
	}
	
	public UserSession getUserSession() {
		return this.userSession;
	}
	
	public List<ComicBookDto> getAllComicBooks() {
		return this.comicBookService.getAllComicBook();
	}
	
	public JTable getComicBookTable() {
		return this.comicBookTable;
	}
	
	public String getTitle() {
		return this.lblNewLabel.getText();
	}

	public JTableHeader getHeader() {
		return this.header;
	}
}
