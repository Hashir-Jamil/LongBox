package org.longbox.presentation.comicbook;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.longbox.businesslogic.UserSession;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.domainobjects.dto.CommentDTO;

import java.awt.CardLayout;

public class ComicBookFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String FRAME_TITLE = "Search Result";
	private final String COMIC_BOOK_INFO = "Comic Book Info";
	
	private JPanel comicBookPane;
	private ComicBookInfoPanel comicBookInfoPane;
	private CardLayout cardLayout;
	private ComicBookDTO comicBookResult;
	private UserSession userSession;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ComicBookFrame frame = new ComicBookFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public ComicBookFrame() {
//		initComicBookPage();        
//	}

	public ComicBookFrame(ComicBookDTO comicBook, UserSession user) {
		this.userSession = user;
		this.comicBookResult = comicBook;
		initComicBookPage(); 
	}
	
	private void initComicBookPage() {
		comicBookInfoPane = new ComicBookInfoPanel(this.comicBookResult, this.userSession);
		
		setTitle(FRAME_TITLE);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setBounds(100, 100, 1200, 900);
	    comicBookPane = new JPanel();
	    comicBookPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	    setLocationRelativeTo(null);
	    setContentPane(comicBookPane);

	    // adding elements to the pane
	    cardLayout = new CardLayout();
	    comicBookPane.setLayout(cardLayout);	
	    comicBookPane.add(comicBookInfoPane, COMIC_BOOK_INFO);
	}

	public String getFRAME_TITLE() {
		return FRAME_TITLE;
	}

	public String getCOMIC_BOOK_INFO() {
		return COMIC_BOOK_INFO;
	}

	public JPanel getComicBookPane() {
		return comicBookPane;
	}

	public void setComicBookPane(JPanel comicBookPane) {
		this.comicBookPane = comicBookPane;
	}

	public ComicBookInfoPanel getComicBookInfoPane() {
		return comicBookInfoPane;
	}

	public void setComicBookInfoPane(ComicBookInfoPanel comicBookInfoPane) {
		this.comicBookInfoPane = comicBookInfoPane;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public ComicBookDTO getComicBookResult() {
		return comicBookResult;
	}

	public void setComicBookResult(ComicBookDTO comicBookResult) {
		this.comicBookResult = comicBookResult;
	}
}
