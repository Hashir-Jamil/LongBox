package org.longbox.presentation.comicbook;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import lombok.Getter;
import lombok.Setter;
import org.longbox.domainobjects.dto.ComicBookDTO;

import java.awt.CardLayout;
@Getter
@Setter
public class ComicBookFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String FRAME_TITLE = "Search Result";
	private final String COMIC_BOOK_COMMENTS = "Comic Book Comments";
	private final String COMIC_BOOK_INFO = "Comic Book Info";
	private final String VIEW_COMMENTS = "View Comments";
	private JPanel comicBookPane;
	private ComicBookInfoPanel comicBookInfoPane = new ComicBookInfoPanel();
	private ComicBookCommentsPanel comicBookCommentsPane = new ComicBookCommentsPanel();
	private CardLayout cardLayout;
	private ComicBookDTO comicBookResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComicBookFrame frame = new ComicBookFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ComicBookFrame() {
		initComicBookPage();        
	}
	
	public ComicBookFrame(ComicBookDTO comicBook) {
		initComicBookPage(); 
		this.comicBookResult = comicBook;
	}
	
	private void initComicBookPage() {
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
	    comicBookPane.add(comicBookCommentsPane, COMIC_BOOK_COMMENTS);
	    		
	}
}
